package io.netty.util.internal.chmv8;

import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.entity.player.InventoryPlayer$1;
import recovered.unidentified.UnidentifiedClass0000;

public class ConcurrentHashMapV8$ForEachTransformedMappingTask<K, V, U> extends ConcurrentHashMapV8$BulkTask<K, V, Void> {
   public UnidentifiedClass0000 __junk3567371196096951702;
   public AbstractResourcePack __junk4998104865714230855;
   public ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends U> transformer;
   public InventoryPlayer$1 __junk6112175693421562606;
   public ConcurrentHashMapV8$Action<? super U> action;

   public ConcurrentHashMapV8$ForEachTransformedMappingTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends U> var6,
      ConcurrentHashMapV8$Action<? super U> var7
   ) {
      super(var1, var2, var3, var4, var5);
      this.transformer = var6;
      this.action = var7;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$BiFun var1 = this.transformer;
      if (this.transformer != null) {
         ConcurrentHashMapV8$Action var2 = this.action;
         if (this.action != null) {
            int var3 = this.baseIndex;

            while (this.batch > 0) {
               int var4 = this.baseLimit;
               int var5;
               if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                  break;
               }

               this.addToPendingCount(1);
               new ConcurrentHashMapV8$ForEachTransformedMappingTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
            }

            while ((var6 = this.advance()) != null) {
               Object var7;
               if ((var7 = var1.apply(var6.key, var6.val)) != null) {
                  var2.apply(var7);
               }
            }

            this.propagateCompletion();
         }
      }
   }
}
