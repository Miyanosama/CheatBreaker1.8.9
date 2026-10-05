package io.netty.channel.nio;

import io.netty.channel.Channel$Unsafe;
import java.nio.channels.SelectableChannel;

public interface AbstractNioChannel$NioUnsafe extends Channel$Unsafe {
   void finishConnect();

   void read();

   SelectableChannel ch();

   void forceFlush();
}
