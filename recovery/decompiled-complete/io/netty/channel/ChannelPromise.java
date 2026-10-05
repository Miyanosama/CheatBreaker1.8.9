package io.netty.channel;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;

public interface ChannelPromise extends ChannelFuture, Promise<Void> {
   ChannelPromise addListener(GenericFutureListener<? extends Future<? super Void>> var1);

   ChannelPromise await();

   ChannelPromise setSuccess(Void var1);

   @Override
   Channel channel();

   ChannelPromise syncUninterruptibly();

   ChannelPromise addListeners(GenericFutureListener<? extends Future<? super Void>>... var1);

   ChannelPromise awaitUninterruptibly();

   ChannelPromise setFailure(Throwable var1);

   ChannelPromise setSuccess();

   boolean trySuccess();

   ChannelPromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1);

   ChannelPromise sync();

   ChannelPromise removeListener(GenericFutureListener<? extends Future<? super Void>> var1);
}
