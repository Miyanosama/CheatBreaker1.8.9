package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.ServerSocketChannelUDT;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.udt.DefaultUdtServerChannelConfig;
import io.netty.channel.udt.UdtServerChannel;
import io.netty.channel.udt.UdtServerChannelConfig;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.apache.log4j.xml.XMLLayout;

public abstract class NioUdtAcceptorChannel extends AbstractNioMessageChannel implements UdtServerChannel {
   public UdtServerChannelConfig config;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(NioUdtAcceptorChannel.class);

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   public NioUdtAcceptorChannel(ServerSocketChannelUDT var1) {
      super(null, var1, 16);

      try {
         var1.configureBlocking(false);
         this.config = new DefaultUdtServerChannelConfig(this, var1, true);
      } catch (Exception var5) {
         try {
            var1.close();
         } catch (Exception var4) {
            if (logger.isWarnEnabled()) {
               logger.warn("Failed to close channel.", (Throwable)var4);
            }
         }

         throw new ChannelException("Failed to configure channel.", var5);
      }
   }

   @Override
   public void doClose() throws java.lang.Exception {
      this.javaChannel().close();
   }

   @Override
   public void doFinishConnect() throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean doConnect(SocketAddress var1, SocketAddress var2) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   public NioUdtAcceptorChannel(TypeUDT var1) {
      this(NioUdtProvider.newAcceptorChannelUDT(var1));
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return null;
   }

   @Override
   public SocketAddress remoteAddress0() {
      return null;
   }

   @Override
   public SocketAddress localAddress0() {
      return this.javaChannel().socket().getLocalSocketAddress();
   }

   @Override
   public void doBind(SocketAddress var1) throws java.lang.Exception {
      this.javaChannel().socket().bind(var1, this.config.getBacklog());
   }

   @Override
   public boolean doWriteMessage(Object var1, ChannelOutboundBuffer var2) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   public UdtServerChannelConfig config() {
      return this.config;
   }

   @Override
   public void doDisconnect() throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   public ServerSocketChannelUDT javaChannel() {
      return (ServerSocketChannelUDT)super.javaChannel();
   }

   @Override
   public Object filterOutboundMessage(Object var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isActive() {
      return this.javaChannel().socket().isBound();
   }
}
