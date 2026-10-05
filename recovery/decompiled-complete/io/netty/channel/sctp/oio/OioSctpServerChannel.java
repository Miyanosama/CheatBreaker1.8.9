package io.netty.channel.sctp.oio;

import com.sun.nio.sctp.SctpChannel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe;
import io.netty.channel.oio.AbstractOioMessageChannel;
import io.netty.channel.sctp.SctpServerChannel;
import io.netty.channel.sctp.SctpServerChannelConfig;
import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.tileentity.TileEntityFlowerPot;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$24;

public class OioSctpServerChannel extends AbstractOioMessageChannel implements SctpServerChannel {
   public com.sun.nio.sctp.SctpServerChannel sch;
   public SctpServerChannelConfig config;
   public Selector selector;
   public AbstractNioByteChannel$NioByteUnsafe __junk466456554985445393;
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public TileEntityFlowerPot __junk2662509001699570965;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(OioSctpServerChannel.class);
   public Base64Decoder __junk608440691097755168;
   public LogBrokerMonitor$24 __junk4793378211014041146;

   public OioSctpServerChannel(com.sun.nio.sctp.SctpServerChannel var1) {
      super(null);
      if (var1 == null) {
         throw new NullPointerException("sctp server channel");
      } else {
         this.sch = var1;
         boolean var2 = false;

         try {
            var1.configureBlocking(false);
            this.selector = Selector.open();
            var1.register(this.selector, 16);
            this.config = new OioSctpServerChannel$OioSctpServerChannelConfig(this, this, var1, null);
            var2 = true;
         } catch (Exception var11) {
            throw new ChannelException("failed to initialize a sctp server channel", var11);
         } finally {
            if (!var2) {
               try {
                  var1.close();
               } catch (IOException var10) {
                  logger.warn("Failed to close a sctp server channel.", (Throwable)var10);
               }
            }
         }
      }
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChannelFuture unbindAddress(InetAddress var1, ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.sch.unbindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new OioSctpServerChannel$2(this, var1, var2));
      }

      return var2;
   }

   public static com.sun.nio.sctp.SctpServerChannel newServerSocket() {
      try {
         return com.sun.nio.sctp.SctpServerChannel.open();
      } catch (IOException var1) {
         throw new ChannelException("failed to create a sctp server channel", var1);
      }
   }

   public InetSocketAddress remoteAddress() {
      return null;
   }

   @Override
   public void doConnect(SocketAddress var1, SocketAddress var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChannelFuture bindAddress(InetAddress var1, ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.sch.bindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new OioSctpServerChannel$1(this, var1, var2));
      }

      return var2;
   }

   @Override
   public Set<InetSocketAddress> allLocalAddresses() {
      try {
         Set var1 = this.sch.getAllLocalAddresses();
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
   public boolean isActive() {
      return this.isOpen() && this.localAddress0() != null;
   }

   @Override
   public ChannelFuture unbindAddress(InetAddress var1) {
      return this.unbindAddress(var1, this.newPromise());
   }

   @Override
   public SocketAddress localAddress0() {
      try {
         Iterator var1 = this.sch.getAllLocalAddresses().iterator();
         if (var1.hasNext()) {
            return (SocketAddress)var1.next();
         }
      } catch (IOException var2) {
      }

      return null;
   }

   @Override
   public int doReadMessages(List<Object> var1) {
      if (!this.isActive()) {
         return -1;
      } else {
         SctpChannel var2 = null;
         int var3 = 0;

         try {
            int var4 = this.selector.select(722626536L & 3933454463687300076L);
            if (var4 > 0) {
               Iterator var5 = this.selector.selectedKeys().iterator();

               do {
                  SelectionKey var6 = (SelectionKey)var5.next();
                  var5.remove();
                  if (var6.isAcceptable()) {
                     var2 = this.sch.accept();
                     if (var2 != null) {
                        var1.add(new OioSctpChannel(this, var2));
                        var3++;
                     }
                  }
               } while (var5.hasNext());

               return var3;
            }
         } catch (Throwable var8) {
            logger.warn("Failed to create a new channel from an accepted sctp channel.", var8);
            if (var2 != null) {
               try {
                  var2.close();
               } catch (Throwable var7) {
                  logger.warn("Failed to close a sctp channel.", var7);
               }
            }
         }

         return var3;
      }
   }

   @Override
   public ChannelFuture bindAddress(InetAddress var1) {
      return this.bindAddress(var1, this.newPromise());
   }

   @Override
   public void doBind(SocketAddress var1) {
      this.sch.bind(var1, this.config.getBacklog());
   }

   @Override
   public SocketAddress remoteAddress0() {
      return null;
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   @Override
   public void doClose() {
      try {
         this.selector.close();
      } catch (IOException var2) {
         logger.warn("Failed to close a selector.", (Throwable)var2);
      }

      this.sch.close();
   }

   @Override
   public boolean isOpen() {
      return this.sch.isOpen();
   }

   @Override
   public SctpServerChannelConfig config() {
      return this.config;
   }

   @Override
   public void doDisconnect() {
      throw new UnsupportedOperationException();
   }

   public OioSctpServerChannel() {
      this(newServerSocket());
   }

   @Override
   public Object filterOutboundMessage(Object var1) {
      throw new UnsupportedOperationException();
   }
}
