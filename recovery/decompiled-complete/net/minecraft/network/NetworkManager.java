package net.minecraft.network;

import com.google.common.collect.Queues;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.UnsafeDirectSwappedByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
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
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.crypto.SecretKey;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.command.CommandResultStats$Type;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.CryptManager;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Stairs;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import recovered.unidentified.UnidentifiedClass4951;

public class NetworkManager extends SimpleChannelInboundHandler<Packet> {
   public INetHandler packetListener;
   public static Logger logger = LogManager.getLogger();
   public static LazyLoadBase<LocalEventLoopGroup> field_0007 = new NetworkManager$3();
   public UnsafeDirectSwappedByteBuf field_0014;
   public SocketAddress socketAddress;
   public static Marker field_0003 = MarkerManager.getMarker("NETWORK");
   public Channel channel;
   public Queue<NetworkManager$InboundHandlerTuplePacketListener> field_0012 = Queues.newConcurrentLinkedQueue();
   public static Marker logMarkerPackets = MarkerManager.getMarker("NETWORK_PACKETS", field_0003);
   public CommandResultStats$Type field_0019;
   public static AttributeKey<EnumConnectionState> field_0001 = AttributeKey.valueOf("protocol");
   public static LazyLoadBase<NioEventLoopGroup> field_0009 = new NetworkManager$1();
   public boolean disconnected;
   public static LazyLoadBase<EpollEventLoopGroup> field_0006 = new NetworkManager$2();
   public EnumPacketDirection field_0013;
   public IChatComponent terminationReason;
   public DestroyBlockProgress field_0000;
   public ReentrantReadWriteLock field_0005 = new ReentrantReadWriteLock();
   public boolean isEncrypted;
   public StructureNetherBridgePieces$Stairs field_0015;

   public void dispatchPacket(Packet var1, GenericFutureListener<? extends Future<? super Void>>[] var2) {
      EnumConnectionState var3 = EnumConnectionState.getFromPacket(var1);
      EnumConnectionState var4 = this.channel.attr(field_0001).get();
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
         this.channel.eventLoop().execute(new NetworkManager$4(this, var3, var4, var1, var2));
      }
   }

   public boolean getIsencrypted() {
      return this.isEncrypted;
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
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

   public void channelRead0(ChannelHandlerContext var1, Packet var2) {
      if (this.channel.isOpen()) {
         try {
            var2.processPacket(this.packetListener);
         } catch (ThreadQuickExitException var4) {
         }
      }
   }

   public void setConnectionState(EnumConnectionState var1) {
      this.channel.attr(field_0001).set(var1);
      this.channel.config().setAutoRead(true);
      logger.debug("Enabled auto read");
   }

   public void sendPacket(Packet var1) {
      if (this.isChannelOpen()) {
         this.method_25539();
         this.dispatchPacket(var1, (GenericFutureListener<? extends Future<? super Void>>[])null);
      } else {
         this.field_0005.writeLock().lock();

         try {
            this.field_0012.add(new NetworkManager$InboundHandlerTuplePacketListener(var1, (GenericFutureListener<? extends Future<? super Void>>[])null));
         } finally {
            this.field_0005.writeLock().unlock();
         }
      }
   }

   public void method_25539() {
      if (this.channel != null && this.channel.isOpen()) {
         this.field_0005.readLock().lock();

         try {
            while (!this.field_0012.isEmpty()) {
               NetworkManager$InboundHandlerTuplePacketListener var1 = this.field_0012.poll();
               this.dispatchPacket(
                  NetworkManager$InboundHandlerTuplePacketListener.method_01005(var1), NetworkManager$InboundHandlerTuplePacketListener.method_01006(var1)
               );
            }
         } finally {
            this.field_0005.readLock().unlock();
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
      NetworkManager var1 = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
      new Bootstrap().group(field_0007.getValue()).handler(new NetworkManager$6(var1)).channel(LocalChannel.class).connect(var0).syncUninterruptibly();
      return var1;
   }

   public void setNetHandler(INetHandler var1) {
      Validate.notNull(var1, "packetListener", new Object[0]);
      logger.debug("Set listener of {} to {}", new Object[]{this, var1});
      this.packetListener = var1;
   }

   public SocketAddress getRemoteAddress() {
      return this.socketAddress;
   }

   @Override
   public void channelActive(ChannelHandlerContext var1) {
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
         this.dispatchPacket(var1, (GenericFutureListener<? extends Future<? super Void>>[])ArrayUtils.add(var3, 0, var2));
      } else {
         this.field_0005.writeLock().lock();

         try {
            this.field_0012
               .add(
                  new NetworkManager$InboundHandlerTuplePacketListener(
                     var1, (GenericFutureListener<? extends Future<? super Void>>[])ArrayUtils.add(var3, 0, var2)
                  )
               );
         } finally {
            this.field_0005.writeLock().unlock();
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

         if (this.channel.pipeline().get("compress") instanceof UnidentifiedClass4951) {
            ((UnidentifiedClass4951)this.channel.pipeline().get("decompress")).method_29533(var1);
         } else {
            this.channel.pipeline().addBefore("encoder", "compress", new UnidentifiedClass4951(var1));
         }
      } else {
         if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
            this.channel.pipeline().remove("decompress");
         }

         if (this.channel.pipeline().get("compress") instanceof UnidentifiedClass4951) {
            this.channel.pipeline().remove("compress");
         }
      }
   }

   public boolean isLocalChannel() {
      return this.channel instanceof LocalChannel || this.channel instanceof LocalServerChannel;
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      this.closeChannel(new ChatComponentTranslation("disconnect.endOfStream"));
   }

   public boolean isChannelOpen() {
      return this.channel != null && this.channel.isOpen();
   }

   public NetworkManager(EnumPacketDirection var1) {
      this.field_0013 = var1;
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
      NetworkManager var3 = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
      Class<EpollSocketChannel> var4;
      LazyLoadBase var5;
      if (Epoll.isAvailable() && var2) {
         var4 = EpollSocketChannel.class;
         var5 = field_0006;
      } else {
         var4 = NioSocketChannel.class;
         var5 = field_0009;
      }

      new Bootstrap().group((EventLoopGroup)var5.getValue()).handler(new NetworkManager$5(var3)).channel(var4).connect(var0, var1).syncUninterruptibly();
      return var3;
   }

   public void processReceivedPackets() {
      this.method_25539();
      if (this.packetListener instanceof ITickable) {
         ((ITickable)this.packetListener).update();
      }

      this.channel.flush();
   }
}
