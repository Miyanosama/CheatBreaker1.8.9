package io.netty.channel.sctp.oio;

import com.sun.nio.sctp.SctpChannel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.nio.AbstractNioByteChannel;
import io.netty.channel.oio.AbstractOioMessageChannel;
import io.netty.channel.sctp.DefaultSctpServerChannelConfig;
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
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.chunk.ChunkRenderWorker;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.optifine.entity.model.anim.ModelUpdater;
import net.optifine.expr.ExpressionParser;
import org.apache.log4j.jmx.LayoutDynamicMBean;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$24;

public class OioSctpServerChannel extends AbstractOioMessageChannel implements SctpServerChannel {
   public com.sun.nio.sctp.SctpServerChannel sch;
   public SctpServerChannelConfig config;
   public Selector selector;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(OioSctpServerChannel.class);
   public static ChannelMetadata METADATA = new ChannelMetadata(false);

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
            this.config = new OioSctpServerChannel.OioSctpServerChannelConfig(this, var1);
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
   public void doWrite(ChannelOutboundBuffer var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChannelFuture unbindAddress(final InetAddress var1, final ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.sch.unbindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new Runnable() {

            @Override
            public void run() {
               OioSctpServerChannel.this.unbindAddress(var1, var2);
            }
         });
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
   public void doConnect(SocketAddress var1, SocketAddress var2) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChannelFuture bindAddress(final InetAddress var1, final ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.sch.bindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new Runnable() {

            @Override
            public void run() {
               OioSctpServerChannel.this.bindAddress(var1, var2);
            }
         });
      }

      return var2;
   }

   @Override
   public Set<InetSocketAddress> allLocalAddresses() {
      try {
         Set var1 = this.sch.getAllLocalAddresses();
         LinkedHashSet var2 = new LinkedHashSet(var1.size());

         for (SocketAddress var4 : (Iterable<SocketAddress>)(Iterable<?>)(var1)) {
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
   public int doReadMessages(List<Object> var1) throws java.lang.Exception {
      if (!this.isActive()) {
         return -1;
      } else {
         SctpChannel var2 = null;
         int var3 = 0;

         try {
            int var4 = this.selector.select(1000L);
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
   public void doBind(SocketAddress var1) throws java.lang.Exception {
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
   public void doClose() throws java.lang.Exception {
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
   public void doDisconnect() throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   public OioSctpServerChannel() {
      this(newServerSocket());
   }

   @Override
   public Object filterOutboundMessage(Object var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   public final class OioSctpServerChannelConfig extends DefaultSctpServerChannelConfig {

      public OioSctpServerChannelConfig(OioSctpServerChannel var2, com.sun.nio.sctp.SctpServerChannel var3) {
         super(var2, var3);
      }

      @Override
      public void autoReadCleared() {
         OioSctpServerChannel.this.setReadPending(false);
      }
   }
}
