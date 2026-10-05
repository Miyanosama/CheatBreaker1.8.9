package io.netty.channel.socket;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import java.net.InetAddress;
import java.net.NetworkInterface;

public interface DatagramChannelConfig extends ChannelConfig {
   DatagramChannelConfig setConnectTimeoutMillis(int var1);

   DatagramChannelConfig setAllocator(ByteBufAllocator var1);

   InetAddress getInterface();

   DatagramChannelConfig setBroadcast(boolean var1);

   DatagramChannelConfig setReuseAddress(boolean var1);

   DatagramChannelConfig setAutoClose(boolean var1);

   boolean isBroadcast();

   boolean isLoopbackModeDisabled();

   DatagramChannelConfig setTimeToLive(int var1);

   DatagramChannelConfig setNetworkInterface(NetworkInterface var1);

   int getTrafficClass();

   int getTimeToLive();

   DatagramChannelConfig setInterface(InetAddress var1);

   DatagramChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1);

   boolean isReuseAddress();

   DatagramChannelConfig setAutoRead(boolean var1);

   NetworkInterface getNetworkInterface();

   int getSendBufferSize();

   DatagramChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1);

   DatagramChannelConfig setSendBufferSize(int var1);

   DatagramChannelConfig setMaxMessagesPerRead(int var1);

   DatagramChannelConfig setTrafficClass(int var1);

   DatagramChannelConfig setLoopbackModeDisabled(boolean var1);

   DatagramChannelConfig setWriteSpinCount(int var1);

   int getReceiveBufferSize();

   DatagramChannelConfig setReceiveBufferSize(int var1);
}
