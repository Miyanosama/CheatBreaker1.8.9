package io.netty.channel.sctp;

import com.sun.nio.sctp.Association;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Set;

public interface SctpChannel extends Channel {
   InetSocketAddress remoteAddress();

   InetSocketAddress localAddress();

   ChannelFuture unbindAddress(InetAddress var1, ChannelPromise var2);

   Set<InetSocketAddress> allRemoteAddresses();

   Association association();

   SctpChannelConfig config();

   Set<InetSocketAddress> allLocalAddresses();

   ChannelFuture unbindAddress(InetAddress var1);

   ChannelFuture bindAddress(InetAddress var1);

   SctpServerChannel parent();

   ChannelFuture bindAddress(InetAddress var1, ChannelPromise var2);
}
