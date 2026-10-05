package io.netty.channel.group;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.Iterator;

public interface ChannelGroupFuture extends Future<Void>, Iterable<ChannelFuture> {
   ChannelGroupException cause();

   ChannelGroupFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... var1);

   ChannelGroupFuture awaitUninterruptibly();

   ChannelGroupFuture syncUninterruptibly();

   ChannelFuture find(Channel var1);

   boolean isPartialFailure();

   ChannelGroupFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1);

   boolean isPartialSuccess();

   ChannelGroupFuture sync();

   ChannelGroupFuture removeListener(GenericFutureListener<? extends Future<? super Void>> var1);

   ChannelGroup group();

   @Override
   boolean isSuccess();

   @Override
   Iterator<ChannelFuture> iterator();

   ChannelGroupFuture await();

   ChannelGroupFuture addListener(GenericFutureListener<? extends Future<? super Void>> var1);
}
