package io.netty.channel.epoll;

import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.ServerSocketChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import net.minecraft.util.HttpUtil;
import recovered.unidentified.UnidentifiedClass4816;

public class EpollServerSocketChannel extends AbstractEpollChannel implements ServerSocketChannel {
   public UnidentifiedClass4816 __junk7223834435212619465;
   public HttpUtil __junk8577216729617611895;
   public volatile InetSocketAddress local;
   public EpollServerSocketChannelConfig config = new EpollServerSocketChannelConfig(this);

   public EpollServerSocketChannel() {
      super(Native.socketStreamFd(), 4);
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public AbstractEpollChannel$AbstractEpollUnsafe newUnsafe() {
      return new EpollServerSocketChannel$EpollServerSocketUnsafe(this);
   }

   @Override
   public Object filterOutboundMessage(Object var1) {
      throw new UnsupportedOperationException();
   }

   public InetSocketAddress remoteAddress0() {
      return null;
   }

   @Override
   public void doBind(SocketAddress var1) {
      InetSocketAddress var2 = (InetSocketAddress)var1;
      checkResolvable(var2);
      Native.bind(this.fd, var2.getAddress(), var2.getPort());
      this.local = Native.localAddress(this.fd);
      Native.listen(this.fd, this.config.getBacklog());
      this.active = true;
   }

   @Override
   public boolean isCompatible(EventLoop var1) {
      return var1 instanceof EpollEventLoop;
   }

   public InetSocketAddress localAddress0() {
      return this.local;
   }

   public EpollServerSocketChannelConfig config() {
      return this.config;
   }
}
