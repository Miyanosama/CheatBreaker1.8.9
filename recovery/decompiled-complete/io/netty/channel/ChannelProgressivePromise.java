package io.netty.channel;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ProgressivePromise;

public interface ChannelProgressivePromise extends ChannelProgressiveFuture, ChannelPromise, ProgressivePromise<Void> {
   ChannelProgressivePromise setProgress(long var1, long var3);

   ChannelProgressivePromise setFailure(Throwable var1);

   ChannelProgressivePromise syncUninterruptibly();

   ChannelProgressivePromise addListeners(GenericFutureListener<? extends Future<? super Void>>... var1);

   ChannelProgressivePromise removeListener(GenericFutureListener<? extends Future<? super Void>> var1);

   ChannelProgressivePromise sync();

   ChannelProgressivePromise setSuccess();

   ChannelProgressivePromise awaitUninterruptibly();

   ChannelProgressivePromise addListener(GenericFutureListener<? extends Future<? super Void>> var1);

   ChannelProgressivePromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1);

   ChannelProgressivePromise await();

   ChannelProgressivePromise setSuccess(Void var1);
}
