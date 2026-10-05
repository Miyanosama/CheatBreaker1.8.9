package net.minecraft.network;

import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.crypto.SecretKey;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.CryptManager;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import net.minecraft.network.NettyCompressionEncoder;

public class NetworkManager extends SimpleChannelInboundHandler<Packet> {
   public INetHandler packetListener;
   public static Logger logger = LogManager.getLogger();
   public static Marker recoveredField2249 = MarkerManager.getMarker("NETWORK");
   public SocketAddress socketAddress;
   public static Marker logMarkerPackets = MarkerManager.getMarker("NETWORK_PACKETS", recoveredField2249);
   public Channel channel;
   public Queue<NetworkManager.InboundHandlerTuplePacketListener> recoveredField2250 = Queues.newConcurrentLinkedQueue();
   public static AttributeKey<EnumConnectionState> recoveredField2251 = AttributeKey.valueOf("protocol");
   public static LazyLoadBase<NioEventLoopGroup> recoveredField2252 = new LazyLoadBase<NioEventLoopGroup>() {
      public NioEventLoopGroup load() {
         return new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Client IO #%d").setDaemon(true).build());
      }
   };
   public static LazyLoadBase<EpollEventLoopGroup> recoveredField2253 = new LazyLoadBase<EpollEventLoopGroup>() {
      public EpollEventLoopGroup load() {
         return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build());
      }
   };
   public boolean disconnected;
   public static LazyLoadBase<LocalEventLoopGroup> recoveredField2248 = new LazyLoadBase<LocalEventLoopGroup>() {
      public LocalEventLoopGroup load() {
         return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
      }
   };
   public EnumPacketDirection recoveredField2254;
   public IChatComponent terminationReason;
   public ReentrantReadWriteLock recoveredField2255 = new ReentrantReadWriteLock();
   public boolean isEncrypted;

   public void dispatchPacket(final Packet var1, final GenericFutureListener<? extends Future<? super Void>>[] var2) {
      final EnumConnectionState var3 = EnumConnectionState.getFromPacket(var1);
      final EnumConnectionState var4 = this.channel.attr(recoveredField2251).get();
      if (var4 != var3) {
         logger.debug("Disabled auto read");
         this.channel.config().setAutoRead(false);
      }

      if (this.channel.eventLoop().inEventLoop()) {
         if (var3 != var4) {
            this.setConnectionState(var3);
         }

         ChannelFuture var5 = this.channel.writeAndFlush(var1);
         if (var2 != null) {
            var5.addListeners(var2);
         }

         var5.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
      } else {
         this.channel.eventLoop().execute(new Runnable() {
            @Override
            public void run() {
               if (var3 != var4) {
                  NetworkManager.this.setConnectionState(var3);
               }

               ChannelFuture var1x = NetworkManager.this.channel.writeAndFlush(var1);
               if (var2 != null) {
                  var1x.addListeners(var2);
               }

               var1x.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
            }
         });
      }
   }

   public boolean getIsencrypted() {
      return this.isEncrypted;
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception {
      ChatComponentTranslation var3;
      if (var2 instanceof TimeoutException) {
         var3 = new ChatComponentTranslation("disconnect.timeout");
      } else {
         var3 = new ChatComponentTranslation("disconnect.genericReason", "Internal Exception: " + var2);
      }

      this.closeChannel(var3);
   }

   public INetHandler getNetHandler() {
      return this.packetListener;
   }

   public void channelRead0(ChannelHandlerContext var1, Packet var2) throws java.lang.Exception {
      if (this.channel.isOpen()) {
         try {
            var2.processPacket(this.packetListener);
         } catch (ThreadQuickExitException var4) {
         }
      }
   }

   public void setConnectionState(EnumConnectionState var1) {
      this.channel.attr(recoveredField2251).set(var1);
      this.channel.config().setAutoRead(true);
      logger.debug("Enabled auto read");
   }

   public void sendPacket(Packet var1) {
      if (this.isChannelOpen()) {
         this.method_25539();
         this.dispatchPacket(var1, (GenericFutureListener<? extends Future<? super Void>>[])null);
      } else {
         this.recoveredField2255.writeLock().lock();

         try {
            this.recoveredField2250
               .add(new NetworkManager.InboundHandlerTuplePacketListener(var1, (GenericFutureListener<? extends Future<? super Void>>[])null));
         } finally {
            this.recoveredField2255.writeLock().unlock();
         }
      }
   }

   public void method_25539() {
      if (this.channel != null && this.channel.isOpen()) {
         this.recoveredField2255.readLock().lock();

         try {
            while (!this.recoveredField2250.isEmpty()) {
               NetworkManager.InboundHandlerTuplePacketListener var1 = this.recoveredField2250.poll();
               this.dispatchPacket(var1.recoveredField1233, var1.recoveredField1232);
            }
         } finally {
            this.recoveredField2255.readLock().unlock();
         }
      }
   }

   public void checkDisconnected() {
      if (this.channel != null && !this.channel.isOpen()) {
         if (!this.disconnected) {
            this.disconnected = true;
            if (this.getExitMessage() != null) {
               this.getNetHandler().onDisconnect(this.getExitMessage());
            } else if (this.getNetHandler() != null) {
               this.getNetHandler().onDisconnect(new ChatComponentText("Disconnected"));
            }
         } else {
            logger.warn("handleDisconnection() called twice");
         }
      }
   }

   public static NetworkManager provideLocalClient(SocketAddress var0) {
      final NetworkManager var1 = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
      new Bootstrap().group(recoveredField2248.getValue()).handler(new ChannelInitializer<Channel>() {
         @Override
         public void initChannel(Channel var1x) throws java.lang.Exception {
            var1x.pipeline().addLast("packet_handler", var1);
         }
      }).channel(LocalChannel.class).connect(var0).syncUninterruptibly();
      return var1;
   }

   public void setNetHandler(INetHandler var1) {
      Validate.notNull(var1, "packetListener");
      logger.debug("Set listener of {} to {}", this, var1);
      this.packetListener = var1;
   }

   public SocketAddress getRemoteAddress() {
      return this.socketAddress;
   }

   @Override
   public void channelActive(ChannelHandlerContext var1) throws java.lang.Exception {
      super.channelActive(var1);
      this.channel = var1.channel();
      this.socketAddress = this.channel.remoteAddress();

      try {
         this.setConnectionState(EnumConnectionState.HANDSHAKING);
      } catch (Throwable var3) {
         logger.fatal(var3);
      }
   }

   public boolean hasNoChannel() {
      return this.channel == null;
   }

   public void closeChannel(IChatComponent var1) {
      if (this.channel.isOpen()) {
         this.channel.close().awaitUninterruptibly();
         this.terminationReason = var1;
      }
   }

   public Channel method_25557() {
      return this.channel;
   }

   public void sendPacket(
      Packet var1, GenericFutureListener<? extends Future<? super Void>> var2, GenericFutureListener<? extends Future<? super Void>>... var3
   ) {
      if (this.isChannelOpen()) {
         this.method_25539();
         this.dispatchPacket(var1, ArrayUtils.add(var3, 0, var2));
      } else {
         this.recoveredField2255.writeLock().lock();

         try {
            this.recoveredField2250.add(new NetworkManager.InboundHandlerTuplePacketListener(var1, ArrayUtils.add(var3, 0, var2)));
         } finally {
            this.recoveredField2255.writeLock().unlock();
         }
      }
   }

   public void disableAutoRead() {
      this.channel.config().setAutoRead(false);
   }

   public void setCompressionTreshold(int var1) {
      if (var1 >= 0) {
         if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
            ((NettyCompressionDecoder)this.channel.pipeline().get("decompress")).setCompressionTreshold(var1);
         } else {
            this.channel.pipeline().addBefore("decoder", "decompress", new NettyCompressionDecoder(var1));
         }

         if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder) {
            ((NettyCompressionEncoder)this.channel.pipeline().get("decompress")).setCompressionTreshold(var1);
         } else {
            this.channel.pipeline().addBefore("encoder", "compress", new NettyCompressionEncoder(var1));
         }
      } else {
         if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
            this.channel.pipeline().remove("decompress");
         }

         if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder) {
            this.channel.pipeline().remove("compress");
         }
      }
   }

   public boolean isLocalChannel() {
      return this.channel instanceof LocalChannel || this.channel instanceof LocalServerChannel;
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      this.closeChannel(new ChatComponentTranslation("disconnect.endOfStream"));
   }

   public boolean isChannelOpen() {
      return this.channel != null && this.channel.isOpen();
   }

   public NetworkManager(EnumPacketDirection var1) {
      this.recoveredField2254 = var1;
   }

   public IChatComponent getExitMessage() {
      return this.terminationReason;
   }

   public void enableEncryption(SecretKey var1) {
      this.isEncrypted = true;
      this.channel.pipeline().addBefore("splitter", "decrypt", new NettyEncryptingDecoder(CryptManager.createNetCipherInstance(2, var1)));
      this.channel.pipeline().addBefore("prepender", "encrypt", new NettyEncryptingEncoder(CryptManager.createNetCipherInstance(1, var1)));
   }

   public static NetworkManager createNetworkManagerAndConnect(InetAddress var0, int var1, boolean var2) {
      final NetworkManager var3 = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
      Class<? extends Channel> var4;
      LazyLoadBase var5;
      if (Epoll.isAvailable() && var2) {
         var4 = EpollSocketChannel.class;
         var5 = recoveredField2253;
      } else {
         var4 = NioSocketChannel.class;
         var5 = recoveredField2252;
      }

      new Bootstrap()
         .group((EventLoopGroup)var5.getValue())
         .handler(
            new ChannelInitializer<Channel>() {
               @Override
               public void initChannel(Channel var1) throws java.lang.Exception {
                  try {
                     var1.config().setOption(ChannelOption.TCP_NODELAY, true);
                  } catch (ChannelException var3x) {
                  }

                  var1.pipeline()
                     .addLast("timeout", new ReadTimeoutHandler(30))
                     .addLast("splitter", new MessageDeserializer2())
                     .addLast("decoder", new MessageDeserializer(EnumPacketDirection.CLIENTBOUND))
                     .addLast("prepender", new MessageSerializer2())
                     .addLast("encoder", new MessageSerializer(EnumPacketDirection.SERVERBOUND))
                     .addLast("packet_handler", var3);
               }
            }
         )
         .channel(var4)
         .connect(var0, var1)
         .syncUninterruptibly();
      return var3;
   }

   public void processReceivedPackets() {
      this.method_25539();
      if (this.packetListener instanceof ITickable) {
         ((ITickable)this.packetListener).update();
      }

      this.channel.flush();
   }

   public static class InboundHandlerTuplePacketListener {
      public GenericFutureListener<? extends Future<? super Void>>[] recoveredField1232;
      public Packet recoveredField1233;

      public InboundHandlerTuplePacketListener(Packet var1, GenericFutureListener<? extends Future<? super Void>>... var2) {
         this.recoveredField1233 = var1;
         this.recoveredField1232 = var2;
      }
   }
}
