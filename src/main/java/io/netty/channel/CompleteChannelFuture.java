package io.netty.channel;

import io.netty.handler.codec.socks.SocksCmdResponse;
import io.netty.util.concurrent.CompleteFuture;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import junit.extensions.TestDecorator;
import org.apache.log4j.RollingCalendar;
import org.apache.log4j.pattern.NameAbbreviator;

public abstract class CompleteChannelFuture extends CompleteFuture<Void> implements ChannelFuture {
   public Channel channel;

   public Void getNow() {
      return null;
   }

   @Override
   public ChannelFuture removeListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      super.removeListener(var1);
      return this;
   }

   @Override
   public ChannelFuture awaitUninterruptibly() {
      return this;
   }

   @Override
   public ChannelFuture await() throws java.lang.InterruptedException {
      return this;
   }

   @Override
   public ChannelFuture sync() throws java.lang.InterruptedException {
      return this;
   }

   @Override
   public ChannelFuture addListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      super.addListener(var1);
      return this;
   }

   @Override
   public ChannelFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      super.removeListeners(var1);
      return this;
   }

   @Override
   public ChannelFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      super.addListeners(var1);
      return this;
   }

   @Override
   public Channel channel() {
      return this.channel;
   }

   @Override
   public ChannelFuture syncUninterruptibly() {
      return this;
   }

   @Override
   public EventExecutor executor() {
      EventExecutor var1 = super.executor();
      return (EventExecutor)(var1 == null ? this.channel().eventLoop() : var1);
   }

   public CompleteChannelFuture(Channel var1, EventExecutor var2) {
      super(var2);
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         this.channel = var1;
      }
   }
}
