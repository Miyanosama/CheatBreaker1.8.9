package io.netty.channel;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public interface ChannelFuture extends Future<Void> {
   ChannelFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1);

   ChannelFuture awaitUninterruptibly();

   ChannelFuture removeListener(GenericFutureListener<? extends Future<? super Void>> var1);

   ChannelFuture await();

   Channel channel();

   ChannelFuture syncUninterruptibly();

   ChannelFuture sync();

   ChannelFuture addListener(GenericFutureListener<? extends Future<? super Void>> var1);

   ChannelFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... var1);
}
