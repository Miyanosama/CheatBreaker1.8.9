package io.netty.channel.sctp.nio;

import com.cheatbreaker.client.module.type.ChatModule;
import com.cheatbreaker.client.module.type.IconTextHudModule;
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.NotificationHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.sctp.DefaultSctpChannelConfig;
import io.netty.channel.sctp.SctpChannel;
import io.netty.channel.sctp.SctpChannelConfig;
import io.netty.channel.sctp.SctpMessage;
import io.netty.channel.sctp.SctpNotificationHandler;
import io.netty.channel.sctp.SctpServerChannel;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.stream.NullStream;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.util.StringTranslate;
import net.optifine.entity.model.ModelAdapterBlaze;
import net.optifine.http.HttpPipelineSender;
import net.optifine.shaders.config.ShaderOptionScreen;
import com.cheatbreaker.client.ui.element.type.HoverBackgroundElement;
import com.cheatbreaker.client.module.type.BlockOverlayModule$EnumSwitch;

public class NioSctpChannel extends AbstractNioMessageChannel implements SctpChannel {
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public SctpChannelConfig config;
   public RecvByteBufAllocator.Handle allocHandle;
   public NotificationHandler<?> notificationHandler;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(NioSctpChannel.class);

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   @Override
   public void doBind(SocketAddress var1) throws java.lang.Exception {
      this.javaChannel().bind(var1);
   }

   @Override
   public void doDisconnect() throws java.lang.Exception {
      this.doClose();
   }

   @Override
   public Set<InetSocketAddress> allLocalAddresses() {
      try {
         Set var1 = this.javaChannel().getAllLocalAddresses();
         LinkedHashSet var2 = new LinkedHashSet(var1.size());

         for (SocketAddress var4 : (Iterable<SocketAddress>)(Iterable<?>)(var1)) {
            var2.add((InetSocketAddress)var4);
         }

         return var2;
      } catch (Throwable var5) {
         return Collections.emptySet();
      }
   }

   public NioSctpChannel(com.sun.nio.sctp.SctpChannel var1) {
      this(null, var1);
   }

   @Override
   public SctpServerChannel parent() {
      return (SctpServerChannel)super.parent();
   }

   public static com.sun.nio.sctp.SctpChannel newSctpChannel() {
      try {
         return com.sun.nio.sctp.SctpChannel.open();
      } catch (IOException var1) {
         throw new ChannelException("Failed to open a sctp channel.", var1);
      }
   }

   @Override
   public Association association() {
      try {
         return this.javaChannel().association();
      } catch (IOException var2) {
         return null;
      }
   }

   @Override
   public boolean doWriteMessage(Object var1, ChannelOutboundBuffer var2) throws java.lang.Exception {
      SctpMessage var3 = (SctpMessage)var1;
      ByteBuf var4 = var3.content();
      int var5 = var4.readableBytes();
      if (var5 == 0) {
         return true;
      } else {
         ByteBufAllocator var6 = this.alloc();
         boolean var7 = var4.nioBufferCount() != 1;
         if (!var7 && !var4.isDirect() && var6.isDirectBufferPooled()) {
            var7 = true;
         }

         ByteBuffer var8;
         if (!var7) {
            var8 = var4.nioBuffer();
         } else {
            var4 = var6.directBuffer(var5).writeBytes(var4);
            var8 = var4.nioBuffer();
         }

         MessageInfo var9 = MessageInfo.createOutgoing(this.association(), null, var3.streamIdentifier());
         var9.payloadProtocolID(var3.protocolIdentifier());
         var9.streamNumber(var3.streamIdentifier());
         int var10 = this.javaChannel().send(var8, var9);
         return var10 > 0;
      }
   }

   @Override
   public void doClose() throws java.lang.Exception {
      this.javaChannel().close();
   }

   @Override
   public SctpChannelConfig config() {
      return this.config;
   }

   @Override
   public int doReadMessages(List<Object> var1) throws java.lang.Exception {
      com.sun.nio.sctp.SctpChannel var2 = this.javaChannel();
      RecvByteBufAllocator.Handle var3 = this.allocHandle;
      if (var3 == null) {
         this.allocHandle = var3 = this.config().getRecvByteBufAllocator().newHandle();
      }

      ByteBuf var4 = var3.allocate(this.config().getAllocator());
      boolean var5 = true;

      byte var9;
      try {
         ByteBuffer var6 = var4.internalNioBuffer(var4.writerIndex(), var4.writableBytes());
         int var17 = var6.position();
         MessageInfo var8 = var2.receive(var6, null, this.notificationHandler);
         if (var8 != null) {
            var1.add(new SctpMessage(var8, var4.writerIndex(var4.writerIndex() + var6.position() - var17)));
            var5 = false;
            return 1;
         }

         var9 = 0;
      } catch (Throwable var15) {
         PlatformDependent.throwException(var15);
         return -1;
      } finally {
         int var12 = var4.readableBytes();
         var3.record(var12);
         if (var5) {
            var4.release();
         }
      }

      return var9;
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public ChannelFuture bindAddress(InetAddress var1) {
      return this.bindAddress(var1, this.newPromise());
   }

   @Override
   public boolean isActive() {
      com.sun.nio.sctp.SctpChannel var1 = this.javaChannel();
      return var1.isOpen() && this.association() != null;
   }

   @Override
   public SocketAddress remoteAddress0() {
      try {
         Iterator var1 = this.javaChannel().getRemoteAddresses().iterator();
         if (var1.hasNext()) {
            return (SocketAddress)var1.next();
         }
      } catch (IOException var2) {
      }

      return null;
   }

   @Override
   public boolean doConnect(SocketAddress var1, SocketAddress var2) throws java.lang.Exception {
      if (var2 != null) {
         this.javaChannel().bind(var2);
      }

      boolean var3 = false;

      boolean var5;
      try {
         boolean var4 = this.javaChannel().connect(var1);
         if (!var4) {
            this.selectionKey().interestOps(8);
         }

         var3 = true;
         var5 = var4;
      } finally {
         if (!var3) {
            this.doClose();
         }
      }

      return var5;
   }

   @Override
   public void doFinishConnect() throws java.lang.Exception {
      if (!this.javaChannel().finishConnect()) {
         throw new Error();
      }
   }

   @Override
   public ChannelFuture unbindAddress(InetAddress var1) {
      return this.unbindAddress(var1, this.newPromise());
   }

   @Override
   public ChannelFuture unbindAddress(final InetAddress var1, final ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.javaChannel().unbindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new Runnable() {

            @Override
            public void run() {
               NioSctpChannel.this.unbindAddress(var1, var2);
            }
         });
      }

      return var2;
   }

   @Override
   public Object filterOutboundMessage(Object var1) throws java.lang.Exception {
      if (var1 instanceof SctpMessage) {
         SctpMessage var2 = (SctpMessage)var1;
         ByteBuf var3 = var2.content();
         return var3.isDirect() && var3.nioBufferCount() == 1
            ? var2
            : new SctpMessage(var2.protocolIdentifier(), var2.streamIdentifier(), this.newDirectBuffer(var2, var3));
      } else {
         throw new UnsupportedOperationException(
            "unsupported message type: " + StringUtil.simpleClassName(var1) + " (expected: " + StringUtil.simpleClassName(SctpMessage.class)
         );
      }
   }

   public com.sun.nio.sctp.SctpChannel javaChannel() {
      return (com.sun.nio.sctp.SctpChannel)super.javaChannel();
   }

   @Override
   public ChannelFuture bindAddress(final InetAddress var1, final ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.javaChannel().bindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new Runnable() {

            @Override
            public void run() {
               NioSctpChannel.this.bindAddress(var1, var2);
            }
         });
      }

      return var2;
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   public NioSctpChannel() {
      this(newSctpChannel());
   }

   public NioSctpChannel(Channel var1, com.sun.nio.sctp.SctpChannel var2) {
      super(var1, var2, 1);

      try {
         var2.configureBlocking(false);
         this.config = new NioSctpChannel.NioSctpChannelConfig(this, var2);
         this.notificationHandler = new SctpNotificationHandler(this);
      } catch (IOException var6) {
         try {
            var2.close();
         } catch (IOException var5) {
            if (logger.isWarnEnabled()) {
               logger.warn("Failed to close a partially initialized sctp channel.", (Throwable)var5);
            }
         }

         throw new ChannelException("Failed to enter non-blocking mode.", var6);
      }
   }

   @Override
   public SocketAddress localAddress0() {
      try {
         Iterator var1 = this.javaChannel().getAllLocalAddresses().iterator();
         if (var1.hasNext()) {
            return (SocketAddress)var1.next();
         }
      } catch (IOException var2) {
      }

      return null;
   }

   @Override
   public Set<InetSocketAddress> allRemoteAddresses() {
      try {
         Set var1 = this.javaChannel().getRemoteAddresses();
         HashSet var2 = new HashSet(var1.size());

         for (SocketAddress var4 : (Iterable<SocketAddress>)(Iterable<?>)(var1)) {
            var2.add((InetSocketAddress)var4);
         }

         return var2;
      } catch (Throwable var5) {
         return Collections.emptySet();
      }
   }

   public final class NioSctpChannelConfig extends DefaultSctpChannelConfig {

      public NioSctpChannelConfig(NioSctpChannel var2, com.sun.nio.sctp.SctpChannel var3) {
         super(var2, var3);
      }

      @Override
      public void autoReadCleared() {
         NioSctpChannel.this.setReadPending(false);
      }
   }
}
