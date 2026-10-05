package io.netty.channel.nio;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;

public interface NioTask<C extends SelectableChannel> {
   void channelUnregistered(C var1, Throwable var2);

   void channelReady(C var1, SelectionKey var2);
}
