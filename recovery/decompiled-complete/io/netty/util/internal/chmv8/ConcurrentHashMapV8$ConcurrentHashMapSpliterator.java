package io.netty.util.internal.chmv8;

public interface ConcurrentHashMapV8$ConcurrentHashMapSpliterator<T> {
   boolean tryAdvance(ConcurrentHashMapV8$Action<? super T> var1);

   long estimateSize();

   ConcurrentHashMapV8$ConcurrentHashMapSpliterator<T> trySplit();

   void forEachRemaining(ConcurrentHashMapV8$Action<? super T> var1);
}
