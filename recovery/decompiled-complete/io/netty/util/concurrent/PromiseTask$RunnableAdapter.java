package io.netty.util.concurrent;

import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.AdaptiveRecvByteBufAllocator$HandleImpl;
import java.util.concurrent.Callable;
import org.java_websocket.WebSocketImpl;

public class PromiseTask$RunnableAdapter<T> implements Callable<T> {
   public T result;
   public AbstractChannel$AbstractUnsafe __junk6434722888461435047;
   public AdaptiveRecvByteBufAllocator$HandleImpl __junk8921385800867404446;
   public Runnable task;
   public WebSocketImpl __junk2284225369347730286;

   @Override
   public T call() {
      this.task.run();
      return this.result;
   }

   @Override
   public String toString() {
      return "Callable(task: " + this.task + ", result: " + this.result + ')';
   }

   public PromiseTask$RunnableAdapter(Runnable var1, T var2) {
      this.task = var1;
      this.result = (T)var2;
   }
}
