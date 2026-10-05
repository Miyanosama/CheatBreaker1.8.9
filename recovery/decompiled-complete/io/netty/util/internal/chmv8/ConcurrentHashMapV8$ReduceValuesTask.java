package io.netty.util.internal.chmv8;

import io.netty.channel.ConnectTimeoutException;

public class ConcurrentHashMapV8$ReduceValuesTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, V> {
   public V result;
   public ConnectTimeoutException __junk6084862525844136943;
   public ConcurrentHashMapV8$ReduceValuesTask<K, V> nextRight;
   public ConcurrentHashMapV8$BiFun<? super V, ? super V, ? extends V> reducer;
   public ConcurrentHashMapV8$ReduceValuesTask<K, V> rights;

   public ConcurrentHashMapV8$ReduceValuesTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$ReduceValuesTask<K, V> var6,
      ConcurrentHashMapV8$BiFun<? super V, ? super V, ? extends V> var7
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.reducer = var7;
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
            (this.rights = new ConcurrentHashMapV8$ReduceValuesTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, this.rights, var1))
               .fork();
         }

         Object var8 = null;

         ConcurrentHashMapV8$Node var9;
         while ((var9 = this.advance()) != null) {
            Object var11 = var9.val;
            var8 = var8 == null ? var11 : var1.apply(var8, var11);
         }

         this.result = (V)var8;

         for (CountedCompleter var10 = this.firstComplete(); var10 != null; var10 = var10.nextComplete()) {
            ConcurrentHashMapV8$ReduceValuesTask var12 = (ConcurrentHashMapV8$ReduceValuesTask)var10;

            for (ConcurrentHashMapV8$ReduceValuesTask var5 = var12.rights; var5 != null; var5 = var12.rights = var5.nextRight) {
               Object var7 = var5.result;
               if (var5.result != null) {
                  Object var6 = var12.result;
                  var12.result = (V)(var12.result == null ? var7 : var1.apply(var6, var7));
               }
            }
         }
      }
   }

   @Override
   public V getRawResult() {
      return this.result;
   }
}
