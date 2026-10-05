package io.netty.channel.group;

import com.cheatbreaker.client.ui.element.KeybindElement;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.BlockingOperationException;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ImmediateEventExecutor;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.client.gui.achievement.GuiStats$StatsItem$1;
import org.apache.log4j.chainsaw.MyTableModel$1;

public class DefaultChannelGroupFuture extends DefaultPromise<Void> implements ChannelGroupFuture {
   public Map<Channel, ChannelFuture> futures;
   public int successCount;
   public MyTableModel$1 __junk1733331370925729012;
   public ChannelGroup group;
   public GuiStats$StatsItem$1 __junk6859535494093369308;
   public KeybindElement __junk6337549034384516454;
   public ChannelFutureListener childListener = new DefaultChannelGroupFuture$1(this);
   public int failureCount;

   public DefaultChannelGroupFuture await() {
      super.await();
      return this;
   }

   public DefaultChannelGroupFuture awaitUninterruptibly() {
      super.awaitUninterruptibly();
      return this;
   }

   public boolean trySuccess(Void var1) {
      throw new IllegalStateException();
   }

   public DefaultChannelGroupFuture syncUninterruptibly() {
      super.syncUninterruptibly();
      return this;
   }

   public void setFailure0(ChannelGroupException var1) {
      super.setFailure(var1);
   }

   public DefaultChannelGroupFuture(ChannelGroup var1, Map<Channel, ChannelFuture> var2, EventExecutor var3) {
      super(var3);
      this.group = var1;
      this.futures = Collections.unmodifiableMap(var2);

      for (ChannelFuture var5 : this.futures.values()) {
         var5.addListener(this.childListener);
      }

      if (this.futures.isEmpty()) {
         this.setSuccess0();
      }
   }

   @Override
   public ChannelGroupException cause() {
      return (ChannelGroupException)super.cause();
   }

   @Override
   public Iterator<ChannelFuture> iterator() {
      return this.futures.values().iterator();
   }

   public DefaultChannelGroupFuture removeListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      super.removeListener(var1);
      return this;
   }

   public DefaultChannelGroupFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      super.removeListeners(var1);
      return this;
   }

   @Override
   public synchronized boolean isPartialSuccess() {
      return this.successCount != 0 && this.successCount != this.futures.size();
   }

   @Override
   public void checkDeadLock() {
      EventExecutor var1 = this.executor();
      if (var1 != null && var1 != ImmediateEventExecutor.INSTANCE && var1.inEventLoop()) {
         throw new BlockingOperationException();
      }
   }

   public DefaultChannelGroupFuture setFailure(Throwable var1) {
      throw new IllegalStateException();
   }

   public DefaultChannelGroupFuture sync() {
      super.sync();
      return this;
   }

   @Override
   public ChannelFuture find(Channel var1) {
      return this.futures.get(var1);
   }

   public void setSuccess0() {
      super.setSuccess(null);
   }

   @Override
   public synchronized boolean isPartialFailure() {
      return this.failureCount != 0 && this.failureCount != this.futures.size();
   }

   @Override
   public ChannelGroup group() {
      return this.group;
   }

   public DefaultChannelGroupFuture setSuccess(Void var1) {
      throw new IllegalStateException();
   }

   public DefaultChannelGroupFuture(ChannelGroup var1, Collection<ChannelFuture> var2, EventExecutor var3) {
      super(var3);
      if (var1 == null) {
         throw new NullPointerException("group");
      } else if (var2 == null) {
         throw new NullPointerException("futures");
      } else {
         this.group = var1;
         LinkedHashMap var4 = new LinkedHashMap();

         for (ChannelFuture var6 : var2) {
            var4.put(var6.channel(), var6);
         }

         this.futures = Collections.unmodifiableMap(var4);

         for (ChannelFuture var8 : this.futures.values()) {
            var8.addListener(this.childListener);
         }

         if (this.futures.isEmpty()) {
            this.setSuccess0();
         }
      }
   }

   public DefaultChannelGroupFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      super.addListeners(var1);
      return this;
   }

   public DefaultChannelGroupFuture addListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      super.addListener(var1);
      return this;
   }

   @Override
   public boolean tryFailure(Throwable var1) {
      throw new IllegalStateException();
   }
}
