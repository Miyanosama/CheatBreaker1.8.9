package net.minecraft.network;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.client.network.NetHandlerHandshakeMemory;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.NetHandlerHandshakeTCP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;
import net.minecraft.util.ReportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkSystem {
   public static Logger logger = LogManager.getLogger();
   public static LazyLoadBase<NioEventLoopGroup> eventLoops = new LazyLoadBase<NioEventLoopGroup>() {
      public NioEventLoopGroup load() {
         return new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Server IO #%d").setDaemon(true).build());
      }
   };
   public static LazyLoadBase<EpollEventLoopGroup> SERVER_EPOLL_EVENTLOOP = new LazyLoadBase<EpollEventLoopGroup>() {
      public EpollEventLoopGroup load() {
         return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build());
      }
   };
   public List<ChannelFuture> endpoints = Collections.synchronizedList(Lists.newArrayList());
   public MinecraftServer mcServer;
   public List<NetworkManager> networkManagers = Collections.synchronizedList(Lists.newArrayList());
   public volatile boolean isAlive;
   public static LazyLoadBase<LocalEventLoopGroup> SERVER_LOCAL_EVENTLOOP = new LazyLoadBase<LocalEventLoopGroup>() {
      public LocalEventLoopGroup load() {
         return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
      }
   };

   public MinecraftServer getServer() {
      return this.mcServer;
   }

   public void networkTick() {
      synchronized (this.networkManagers) {
         Iterator var2 = this.networkManagers.iterator();

         while (var2.hasNext()) {
            final NetworkManager var3 = (NetworkManager)var2.next();
            if (!var3.hasNoChannel()) {
               if (!var3.isChannelOpen()) {
                  var2.remove();
                  var3.checkDisconnected();
               } else {
                  try {
                     var3.processReceivedPackets();
                  } catch (Exception var8) {
                     if (var3.isLocalChannel()) {
                        CrashReport var10 = CrashReport.makeCrashReport(var8, "Ticking memory connection");
                        CrashReportCategory var6 = var10.makeCategory("Ticking connection");
                        var6.addCrashSectionCallable("Connection", new Callable<String>() {
                           public String call() throws java.lang.Exception {
                              return var3.toString();
                           }
                        });
                        throw new ReportedException(var10);
                     }

                     logger.warn("Failed to handle packet for " + var3.getRemoteAddress(), var8);
                     final ChatComponentText var5 = new ChatComponentText("Internal server error");
                     var3.sendPacket(new S40PacketDisconnect(var5), new GenericFutureListener<Future<? super Void>>() {
                        @Override
                        public void operationComplete(Future<? super Void> var1) throws java.lang.Exception {
                           var3.closeChannel(var5);
                        }
                     });
                     var3.disableAutoRead();
                  }
               }
            }
         }
      }
   }

   public void terminateEndpoints() {
      this.isAlive = false;

      for (ChannelFuture var2 : this.endpoints) {
         try {
            var2.channel().close().sync();
         } catch (InterruptedException var4) {
            logger.error("Interrupted whilst closing channel");
         }
      }
   }

   public void addLanEndpoint(InetAddress var1, int var2) throws java.io.IOException {
      synchronized (this.endpoints) {
         Class<? extends io.netty.channel.ServerChannel> var4;
         LazyLoadBase var5;
         if (Epoll.isAvailable() && this.mcServer.method_06818()) {
            var4 = EpollServerSocketChannel.class;
            var5 = SERVER_EPOLL_EVENTLOOP;
            logger.info("Using epoll channel type");
         } else {
            var4 = NioServerSocketChannel.class;
            var5 = eventLoops;
            logger.info("Using default channel type");
         }

         this.endpoints
            .add(
               new ServerBootstrap()
                  .channel(var4)
                  .childHandler(
                     new ChannelInitializer<Channel>() {
                        @Override
                        public void initChannel(Channel var1) throws java.lang.Exception {
                           try {
                              var1.config().setOption(ChannelOption.TCP_NODELAY, true);
                           } catch (ChannelException var3) {
                           }

                           var1.pipeline()
                              .addLast("timeout", new ReadTimeoutHandler(30))
                              .addLast("legacy_query", new PingResponseHandler(NetworkSystem.this))
                              .addLast("splitter", new MessageDeserializer2())
                              .addLast("decoder", new MessageDeserializer(EnumPacketDirection.SERVERBOUND))
                              .addLast("prepender", new MessageSerializer2())
                              .addLast("encoder", new MessageSerializer(EnumPacketDirection.CLIENTBOUND));
                           NetworkManager var2x = new NetworkManager(EnumPacketDirection.SERVERBOUND);
                           NetworkSystem.this.networkManagers.add(var2x);
                           var1.pipeline().addLast("packet_handler", var2x);
                           var2x.setNetHandler(new NetHandlerHandshakeTCP(NetworkSystem.this.mcServer, var2x));
                        }
                     }
                  )
                  .group((EventLoopGroup)var5.getValue())
                  .localAddress(var1, var2)
                  .bind()
                  .syncUninterruptibly()
            );
      }
   }

   public NetworkSystem(MinecraftServer var1) {
      this.mcServer = var1;
      this.isAlive = true;
   }

   public SocketAddress addLocalEndpoint() {
      ChannelFuture var1;
      synchronized (this.endpoints) {
         var1 = new ServerBootstrap().channel(LocalServerChannel.class).childHandler(new ChannelInitializer<Channel>() {
            @Override
            public void initChannel(Channel var1) throws java.lang.Exception {
               NetworkManager var2 = new NetworkManager(EnumPacketDirection.SERVERBOUND);
               var2.setNetHandler(new NetHandlerHandshakeMemory(NetworkSystem.this.mcServer, var2));
               NetworkSystem.this.networkManagers.add(var2);
               var1.pipeline().addLast("packet_handler", var2);
            }
         }).group(eventLoops.getValue()).localAddress(LocalAddress.ANY).bind().syncUninterruptibly();
         this.endpoints.add(var1);
      }

      return var1.channel().localAddress();
   }
}
