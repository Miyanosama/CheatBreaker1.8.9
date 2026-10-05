package io.netty.util.internal.chmv8;

import net.minecraft.block.BlockFire;

public class ConcurrentHashMapV8$ForEachKeyTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Void> {
   public BlockFire __junk3120209326937729655;
   public ConcurrentHashMapV8$Action<? super K> action;

   @Override
   public void compute() {
      ConcurrentHashMapV8$Action var1 = this.action;
      if (this.action != null) {
         int var2 = this.baseIndex;

         while (this.batch > 0) {
            int var3 = this.baseLimit;
            int var4;
            if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
               break;
            }

            this.addToPendingCount(1);
            new ConcurrentHashMapV8$ForEachKeyTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, var1).fork();
         }

         while ((var5 = this.advance()) != null) {
            var1.apply(var5.key);
         }

         this.propagateCompletion();
      }
   }

   public ConcurrentHashMapV8$ForEachKeyTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$Action<? super K> var6
   ) {
      super(var1, var2, var3, var4, var5);
      this.action = var6;
   }
}
