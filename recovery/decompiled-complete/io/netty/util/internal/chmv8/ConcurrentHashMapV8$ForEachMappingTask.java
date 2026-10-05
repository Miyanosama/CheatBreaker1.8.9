package io.netty.util.internal.chmv8;

import io.netty.util.internal.RecyclableArrayList;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.entity.RenderZombie$1;
import net.minecraft.stats.StatBase$1;
import net.optifine.entity.model.ModelAdapterMinecartTnt;

public class ConcurrentHashMapV8$ForEachMappingTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Void> {
   public RenderZombie$1 __junk8300175302543675125;
   public ConcurrentHashMapV8$BiAction<? super K, ? super V> action;
   public StatBase$1 __junk6535176509610288133;
   public ModelAdapterMinecartTnt __junk2970000060410676272;
   public RenderMinecart __junk8730840876637354335;
   public RecyclableArrayList __junk6242280244547964600;

   @Override
   public void compute() {
      ConcurrentHashMapV8$BiAction var1 = this.action;
      if (this.action != null) {
         int var2 = this.baseIndex;

         while (this.batch > 0) {
            int var3 = this.baseLimit;
            int var4;
            if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
               break;
            }

            this.addToPendingCount(1);
            new ConcurrentHashMapV8$ForEachMappingTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, var1).fork();
         }

         while ((var5 = this.advance()) != null) {
            var1.apply(var5.key, var5.val);
         }

         this.propagateCompletion();
      }
   }

   public ConcurrentHashMapV8$ForEachMappingTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$BiAction<? super K, ? super V> var6
   ) {
      super(var1, var2, var3, var4, var5);
      this.action = var6;
   }
}
