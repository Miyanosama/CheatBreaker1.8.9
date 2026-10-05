package io.netty.util.internal.chmv8;

import java.util.concurrent.atomic.AtomicReference;
import net.optifine.entity.model.ModelAdapterEnderCrystal;

public class ConcurrentHashMapV8$SearchKeysTask<K, V, U> extends ConcurrentHashMapV8$BulkTask<K, V, U> {
   public ModelAdapterEnderCrystal __junk2100470558904660195;
   public AtomicReference<U> result;
   public ConcurrentHashMapV8$Fun<? super K, ? extends U> searchFunction;

   public ConcurrentHashMapV8$SearchKeysTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$Fun<? super K, ? extends U> var6,
      AtomicReference<U> var7
   ) {
      super(var1, var2, var3, var4, var5);
      this.searchFunction = var6;
      this.result = var7;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$Fun var1 = this.searchFunction;
      if (this.searchFunction != null) {
         AtomicReference var2 = this.result;
         if (this.result != null) {
            int var3 = this.baseIndex;

            while (this.batch > 0) {
               int var4 = this.baseLimit;
               int var5;
               if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                  break;
               }

               if (var2.get() != null) {
                  return;
               }

               this.addToPendingCount(1);
               new ConcurrentHashMapV8$SearchKeysTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
            }

            while (var2.get() == null) {
               ConcurrentHashMapV8$Node var7;
               if ((var7 = this.advance()) == null) {
                  this.propagateCompletion();
                  break;
               }

               Object var6;
               if ((var6 = var1.apply(var7.key)) != null) {
                  if (var2.compareAndSet(null, var6)) {
                     this.quietlyCompleteRoot();
                  }
                  break;
               }
            }
         }
      }
   }

   @Override
   public U getRawResult() {
      return this.result.get();
   }
}
