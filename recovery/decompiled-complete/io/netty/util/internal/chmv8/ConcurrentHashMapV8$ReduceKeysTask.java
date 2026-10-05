package io.netty.util.internal.chmv8;

import net.minecraft.util.MinecraftError;

public class ConcurrentHashMapV8$ReduceKeysTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, K> {
   public K result;
   public ConcurrentHashMapV8$ReduceKeysTask<K, V> nextRight;
   public ConcurrentHashMapV8$BiFun<? super K, ? super K, ? extends K> reducer;
   public ConcurrentHashMapV8$ReduceKeysTask<K, V> rights;
   public MinecraftError __junk6274303482100395023;

   public ConcurrentHashMapV8$ReduceKeysTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$ReduceKeysTask<K, V> var6,
      ConcurrentHashMapV8$BiFun<? super K, ? super K, ? extends K> var7
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.reducer = var7;
   }

   @Override
   public K getRawResult() {
      return this.result;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$BiFun var1 = this.reducer;
      if (this.reducer != null) {
         int var2 = this.baseIndex;

         while (this.batch > 0) {
            int var3 = this.baseLimit;
            int var4;
            if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
               break;
            }

            this.addToPendingCount(1);
            (this.rights = new ConcurrentHashMapV8$ReduceKeysTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, this.rights, var1)).fork();
         }

         Object var8 = null;

         ConcurrentHashMapV8$Node var9;
         while ((var9 = this.advance()) != null) {
            Object var11 = var9.key;
            var8 = var8 == null ? var11 : (var11 == null ? var8 : var1.apply(var8, var11));
         }

         this.result = (K)var8;

         for (CountedCompleter var10 = this.firstComplete(); var10 != null; var10 = var10.nextComplete()) {
            ConcurrentHashMapV8$ReduceKeysTask var12 = (ConcurrentHashMapV8$ReduceKeysTask)var10;

            for (ConcurrentHashMapV8$ReduceKeysTask var5 = var12.rights; var5 != null; var5 = var12.rights = var5.nextRight) {
               Object var7 = var5.result;
               if (var5.result != null) {
                  Object var6 = var12.result;
                  var12.result = (K)(var12.result == null ? var7 : var1.apply(var6, var7));
               }
            }
         }
      }
   }
}
