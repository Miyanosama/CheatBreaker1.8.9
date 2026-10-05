package io.netty.channel.sctp.oio;

import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.NotificationHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.RecvByteBufAllocator$Handle;
import io.netty.channel.oio.AbstractOioMessageChannel;
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
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.gui.GuiFlatPresets$ListSlot;
import net.minecraft.network.NetworkSystem$5;
import net.optifine.reflect.ReflectorRaw;

public class OioSctpChannel extends AbstractOioMessageChannel implements SctpChannel {
   public static String EXPECTED_TYPE = " (expected: " + StringUtil.simpleClassName(SctpMessage.class) + ')';
   public RecvByteBufAllocator$Handle allocHandle;
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public Selector connectSelector;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(OioSctpChannel.class);
   public SctpChannelConfig config;
   public ReflectorRaw __junk4055820461026203381;
   public Selector writeSelector;
   public NotificationHandler<?> notificationHandler;
   public GuiFlatPresets$ListSlot __junk3169704160323325314;
   public NetworkSystem$5 __junk1141010178644258936;
   public Selector readSelector;
   public com.sun.nio.sctp.SctpChannel ch;

   @Override
   public void doBind(SocketAddress var1) {
      this.ch.bind(var1);
   }

   @Override
   public ChannelFuture bindAddress(InetAddress var1) {
      return this.bindAddress(var1, this.newPromise());
   }

   public OioSctpChannel(Channel var1, com.sun.nio.sctp.SctpChannel var2) {
      super(var1);
      this.ch = var2;
      boolean var3 = false;

      try {
         var2.configureBlocking(false);
         this.readSelector = Selector.open();
         this.writeSelector = Selector.open();
         this.connectSelector = Selector.open();
         var2.register(this.readSelector, 1);
         var2.register(this.writeSelector, 4);
         var2.register(this.connectSelector, 8);
         this.config = new OioSctpChannel$OioSctpChannelConfig(this, this, var2, null);
         this.notificationHandler = new SctpNotificationHandler(this);
         var3 = true;
      } catch (Exception var12) {
         throw new ChannelException("failed to initialize a sctp channel", var12);
      } finally {
         if (!var3) {
            try {
               var2.close();
            } catch (IOException var11) {
               logger.warn("Failed to close a sctp channel.", (Throwable)var11);
            }
         }
      }
   }

   @Override
   public ChannelFuture unbindAddress(InetAddress var1) {
      return this.unbindAddress(var1, this.newPromise());
   }

   @Override
   public void doConnect(SocketAddress var1, SocketAddress var2) {
      if (var2 != null) {
         this.ch.bind(var2);
      }

      boolean var3 = false;

      try {
         this.ch.connect(var1);
         boolean var4 = false;

         while (!var4) {
            if (this.connectSelector.select(269659118L & 1325944865620055016L) >= 0) {
               Set var5 = this.connectSelector.selectedKeys();
               Iterator var6 = var5.iterator();

               while (true) {
                  if (var6.hasNext()) {
                     SelectionKey var7 = (SelectionKey)var6.next();
                     if (!var7.isConnectable()) {
                        continue;
                     }

                     var5.clear();
                     var4 = true;
                  }

                  var5.clear();
                  break;
               }
            }
         }

         var3 = this.ch.finishConnect();
      } finally {
         if (!var3) {
            this.doClose();
         }
      }
   }

   @Override
   public SctpServerChannel parent() {
      return (SctpServerChannel)super.parent();
   }

   @Override
   public Association association() {
      try {
         return this.ch.association();
      } catch (IOException var2) {
         return null;
      }
   }

   @Override
   public Set<InetSocketAddress> allLocalAddresses() {
      try {
         Set var1 = this.ch.getAllLocalAddresses();
         LinkedHashSet var2 = new LinkedHashSet(var1.size());

         for (SocketAddress var4 : var1) {
            var2.add((InetSocketAddress)var4);
         }

         return var2;
      } catch (Throwable var5) {
         return Collections.emptySet();
      }
   }

   @Override
   public void doDisconnect() {
      this.doClose();
   }

   @Override
   public ChannelFuture unbindAddress(InetAddress var1, ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.ch.unbindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new OioSctpChannel$2(this, var1, var2));
      }

      return var2;
   }

   public OioSctpChannel() {
      this(openChannel());
   }

   public static com.sun.nio.sctp.SctpChannel openChannel() {
      try {
         return com.sun.nio.sctp.SctpChannel.open();
      } catch (IOException var1) {
         throw new ChannelException("Failed to open a sctp channel.", var1);
      }
   }

   @Override
   public ChannelFuture bindAddress(InetAddress var1, ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.ch.bindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new OioSctpChannel$1(this, var1, var2));
      }

      return var2;
   }

   @Override
   public int doReadMessages(List<Object> var1) {
      if (!this.readSelector.isOpen()) {
         return 0;
      } else {
         int var2 = 0;
         int var3 = this.readSelector.select(-1920759348573621252L & 170394600L);
         boolean var4 = var3 > 0;
         if (!var4) {
            return var2;
         } else {
            Set var5 = this.readSelector.selectedKeys();

            try {
               for (SelectionKey var7 : var5) {
                  RecvByteBufAllocator$Handle var8 = this.allocHandle;
                  if (var8 == null) {
                     this.allocHandle = var8 = this.config().getRecvByteBufAllocator().newHandle();
                  }

                  ByteBuf var9 = var8.allocate(this.config().getAllocator());
                  boolean var10 = true;

                  try {
                     ByteBuffer var11 = var9.nioBuffer(var9.writerIndex(), var9.writableBytes());
                     MessageInfo var12 = this.ch.receive(var11, null, this.notificationHandler);
                     if (var12 == null) {
                        return var2;
                     }

                     ((Buffer)var11).flip();
                     var1.add(new SctpMessage(var12, var9.writerIndex(var9.writerIndex() + var11.remaining())));
                     var10 = false;
                     var2++;
                  } catch (Throwable var24) {
                     PlatformDependent.throwException(var24);
                  } finally {
                     int var16 = var9.readableBytes();
                     var8.record(var16);
                     if (var10) {
                        var9.release();
                     }
                  }
               }

               return var2;
            } finally {
               var5.clear();
            }
         }
      }
   }

   public static void closeSelector(String var0, Selector var1) {
      try {
         var1.close();
      } catch (IOException var3) {
         logger.warn("Failed to close a " + var0 + " selector.", (Throwable)var3);
      }
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public boolean isOpen() {
      return this.ch.isOpen();
   }

   public OioSctpChannel(com.sun.nio.sctp.SctpChannel var1) {
      this(null, var1);
   }

   @Override
   public SocketAddress remoteAddress0() {
      try {
         Iterator var1 = this.ch.getRemoteAddresses().iterator();
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
         Set var1 = this.ch.getRemoteAddresses();
         LinkedHashSet var2 = new LinkedHashSet(var1.size());

         for (SocketAddress var4 : var1) {
            var2.add((InetSocketAddress)var4);
         }

         return var2;
      } catch (Throwable var5) {
         return Collections.emptySet();
      }
   }

   @Override
   public SctpChannelConfig config() {
      return this.config;
   }

   @Override
   public Object filterOutboundMessage(Object var1) {
      if (var1 instanceof SctpMessage) {
         return var1;
      } else {
         throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(var1) + EXPECTED_TYPE);
      }
   }

   @Override
   public void doClose() {
      closeSelector("read", this.readSelector);
      closeSelector("write", this.writeSelector);
      closeSelector("connect", this.connectSelector);
      this.ch.close();
   }

   @Override
   public SocketAddress localAddress0() {
      try {
         Iterator var1 = this.ch.getAllLocalAddresses().iterator();
         if (var1.hasNext()) {
            return (SocketAddress)var1.next();
         }
      } catch (IOException var2) {
      }

      return null;
   }

   @Override
   public boolean isActive() {
      return this.isOpen() && this.association() != null;
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
      if (this.writeSelector.isOpen()) {
         int var2 = var1.size();
         int var3 = this.writeSelector.select(53151721L & 809559018L);
         if (var3 > 0) {
            Set var4 = this.writeSelector.selectedKeys();
            if (!var4.isEmpty()) {
               Iterator var5 = var4.iterator();
               int var6 = 0;

               while (var6 != var2) {
                  var5.next();
                  var5.remove();
                  SctpMessage var7 = (SctpMessage)var1.current();
                  if (var7 == null) {
                     return;
                  }

                  ByteBuf var8 = var7.content();
                  int var9 = var8.readableBytes();
                  ByteBuffer var10;
                  if (var8.nioBufferCount() != -1) {
                     var10 = var8.nioBuffer();
                  } else {
                     var10 = ByteBuffer.allocate(var9);
                     var8.getBytes(var8.readerIndex(), var10);
                     ((Buffer)var10).flip();
                  }

                  MessageInfo var11 = MessageInfo.createOutgoing(this.association(), null, var7.streamIdentifier());
                  var11.payloadProtocolID(var7.protocolIdentifier());
                  var11.streamNumber(var7.streamIdentifier());
                  this.ch.send(var10, var11);
                  var6++;
                  var1.remove();
                  if (!var5.hasNext()) {
                     return;
                  }
               }
            }
         }
      }
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }
}
