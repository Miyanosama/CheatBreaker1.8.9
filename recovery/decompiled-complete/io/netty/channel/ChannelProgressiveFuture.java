package io.netty.channel;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ProgressiveFuture;

public interface ChannelProgressiveFuture extends ChannelFuture, ProgressiveFuture<Void> {
   ChannelProgressiveFuture await();

   ChannelProgressiveFuture syncUninterruptibly();

   ChannelProgressiveFuture addListener(GenericFutureListener<? extends Future<? super Void>> var1);

   ChannelProgressiveFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1);

   ChannelProgressiveFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... var1);

   ChannelProgressiveFuture awaitUninterruptibly();

   ChannelProgressiveFuture removeListener(GenericFutureListener<? extends Future<? super Void>> var1);

   ChannelProgressiveFuture sync();
}
