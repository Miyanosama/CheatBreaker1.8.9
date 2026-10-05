package io.netty.util.internal.chmv8;

import net.minecraft.entity.boss.EntityWither$1;

public class ConcurrentHashMapV8$KeySpliterator<K, V> extends ConcurrentHashMapV8$Traverser<K, V> implements ConcurrentHashMapV8$ConcurrentHashMapSpliterator<K> {
   public long est;
   public EntityWither$1 __junk5228468067710322972;

   @Override
   public boolean tryAdvance(ConcurrentHashMapV8$Action<? super K> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node var2;
         if ((var2 = this.advance()) == null) {
            return false;
         } else {
            var1.apply(var2.key);
            return true;
         }
      }
   }

   public ConcurrentHashMapV8$KeySpliterator(ConcurrentHashMapV8$Node<K, V>[] var1, int var2, int var3, int var4, long var5) {
      super(var1, var2, var3, var4);
      this.est = var5;
   }

   @Override
   public void forEachRemaining(ConcurrentHashMapV8$Action<? super K> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node var2;
         while ((var2 = this.advance()) != null) {
            var1.apply(var2.key);
         }
      }
   }

   @Override
   public ConcurrentHashMapV8$ConcurrentHashMapSpliterator<K> trySplit() {
      int var1 = this.baseIndex;
      int var2 = this.baseLimit;
      int var3;
      return (var3 = this.baseIndex + this.baseLimit >>> 1) <= var1
         ? null
         : new ConcurrentHashMapV8$KeySpliterator<>(this.tab, this.baseSize, this.baseLimit = var3, var2, this.est >>>= 1);
   }

   @Override
   public long estimateSize() {
      return this.est;
   }
}
