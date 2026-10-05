package net.minecraft.network;

import com.google.common.collect.Lists;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AbstractChannel$AbstractUnsafe$5;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDoor$EnumHingePosition;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ReportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recovered.unidentified.UnidentifiedClass0429;
import recovered.unidentified.UnidentifiedClass1285;

public class NetworkSystem {
   public static LazyLoadBase<NioEventLoopGroup> eventLoops = new NetworkSystem$1();
   public BlockDoor$EnumHingePosition field_0011;
   public static Logger logger = LogManager.getLogger();
   public BlockBed field_0010;
   public ShapelessRecipes field_0001;
   public static LazyLoadBase<LocalEventLoopGroup> SERVER_LOCAL_EVENTLOOP = new NetworkSystem$3();
   public UnidentifiedClass0429 field_0012;
   public UnidentifiedClass1285 field_0009;
   public List<ChannelFuture> endpoints = Collections.synchronizedList(Lists.newArrayList());
   public MinecraftServer mcServer;
   public List<NetworkManager> networkManagers = Collections.synchronizedList(Lists.newArrayList());
   public volatile boolean isAlive;
   public AbstractChannel$AbstractUnsafe$5 field_0008;
   public static LazyLoadBase<EpollEventLoopGroup> SERVER_EPOLL_EVENTLOOP = new NetworkSystem$2();

   public MinecraftServer getServer() {
      return this.mcServer;
   }

   public void networkTick() {
      synchronized (this.networkManagers) {
         Iterator var2 = this.networkManagers.iterator();

         while (var2.hasNext()) {
            NetworkManager var3 = (NetworkManager)var2.next();
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
                        var6.addCrashSectionCallable("Connection", new NetworkSystem$6(this, var3));
                        throw new ReportedException(var10);
                     }

                     logger.warn("Failed to handle packet for " + var3.getRemoteAddress(), var8);
                     ChatComponentText var5 = new ChatComponentText("Internal server error");
                     var3.sendPacket(new S40PacketDisconnect(var5), new NetworkSystem$7(this, var3, var5));
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

   public void addLanEndpoint(InetAddress var1, int var2) {
      synchronized (this.endpoints) {
         Class<EpollServerSocketChannel> var4;
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
                  .childHandler(new NetworkSystem$4(this))
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
         var1 = new ServerBootstrap()
            .channel(LocalServerChannel.class)
            .childHandler(new NetworkSystem$5(this))
            .group(eventLoops.getValue())
            .localAddress(LocalAddress.ANY)
            .bind()
            .syncUninterruptibly();
         this.endpoints.add(var1);
      }

      return var1.channel().localAddress();
   }
}
