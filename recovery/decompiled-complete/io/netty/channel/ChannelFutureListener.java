package io.netty.channel;

import io.netty.util.concurrent.GenericFutureListener;

public interface ChannelFutureListener extends GenericFutureListener<ChannelFuture> {
   ChannelFutureListener CLOSE_ON_FAILURE = new ChannelFutureListener$2();
   ChannelFutureListener FIRE_EXCEPTION_ON_FAILURE = new ChannelFutureListener$3();
   ChannelFutureListener CLOSE = new ChannelFutureListener$1();
}
