package io.netty.channel.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import java.net.InetSocketAddress;

public interface SocketChannel extends Channel {
   ChannelFuture shutdownOutput(ChannelPromise var1);

   ChannelFuture shutdownOutput();

   boolean isInputShutdown();

   InetSocketAddress remoteAddress();

   ServerSocketChannel parent();

   InetSocketAddress localAddress();

   boolean isOutputShutdown();

   SocketChannelConfig config();
}
