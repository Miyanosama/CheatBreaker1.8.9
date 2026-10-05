package io.netty.channel.group;

import com.cheatbreaker.client.ui.element.KeybindElement;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.BlockingOperationException;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ImmediateEventExecutor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.chainsaw.MyTableModel$1;

public class DefaultChannelGroupFuture extends DefaultPromise<Void> implements ChannelGroupFuture {
   public Map<Channel, ChannelFuture> futures;
   public int successCount;
   public ChannelGroup group;
   public ChannelFutureListener childListener = new ChannelFutureListener() {
      // $VF: synthetic field
      public final boolean $assertionsDisabled = !DefaultChannelGroupFuture.class.desiredAssertionStatus();

      public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
         boolean var2 = var1.isSuccess();
         boolean var3;
         synchronized (DefaultChannelGroupFuture.this) {
            if (var2) {
               DefaultChannelGroupFuture.this.successCount++;
            } else {
               DefaultChannelGroupFuture.this.failureCount++;
            }

            var3 = DefaultChannelGroupFuture.this.successCount + DefaultChannelGroupFuture.this.failureCount == DefaultChannelGroupFuture.this.futures.size();
            if (!$assertionsDisabled
               && DefaultChannelGroupFuture.this.successCount + DefaultChannelGroupFuture.this.failureCount > DefaultChannelGroupFuture.this.futures.size()) {
               throw new AssertionError();
            }
         }

         if (var3) {
            if (DefaultChannelGroupFuture.this.failureCount > 0) {
               ArrayList var8 = new ArrayList(DefaultChannelGroupFuture.this.failureCount);

               for (ChannelFuture var6 : DefaultChannelGroupFuture.this.futures.values()) {
                  if (!var6.isSuccess()) {
                     var8.add(new DefaultChannelGroupFuture.DefaultEntry<>(var6.channel(), var6.cause()));
                  }
               }

               DefaultChannelGroupFuture.this.setFailure0(new ChannelGroupException(var8));
            } else {
               DefaultChannelGroupFuture.this.setSuccess0();
            }
         }
      }
   };
   public int failureCount;

   public DefaultChannelGroupFuture await() throws java.lang.InterruptedException {
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

   public DefaultChannelGroupFuture sync() throws java.lang.InterruptedException {
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

   public static final class DefaultEntry<K, V> implements Entry<K, V> {
      public V value;
      public K key;

      @Override
      public V getValue() {
         return this.value;
      }

      @Override
      public K getKey() {
         return this.key;
      }

      public DefaultEntry(K var1, V var2) {
         this.key = (K)var1;
         this.value = (V)var2;
      }

      @Override
      public V setValue(V var1) {
         throw new UnsupportedOperationException("read-only");
      }
   }
}
