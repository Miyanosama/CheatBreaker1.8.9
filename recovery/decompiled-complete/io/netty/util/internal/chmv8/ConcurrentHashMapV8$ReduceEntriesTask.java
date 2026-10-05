package io.netty.util.internal.chmv8;

import java.util.Map.Entry;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.optifine.entity.model.ModelAdapterEnderChest;
import net.optifine.entity.model.ModelAdapterSnowman;
import recovered.unidentified.UnidentifiedClass4185;

public class ConcurrentHashMapV8$ReduceEntriesTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Entry<K, V>> {
   public RenderSkeleton __junk8074528355714372438;
   public UnidentifiedClass4185 __junk5423830408259967926;
   public ConcurrentHashMapV8$BiFun<Entry<K, V>, Entry<K, V>, ? extends Entry<K, V>> reducer;
   public ConcurrentHashMapV8$ReduceEntriesTask<K, V> nextRight;
   public ConcurrentHashMapV8$ReduceEntriesTask<K, V> rights;
   public ModelAdapterSnowman __junk4751035879498813514;
   public Entry<K, V> result;
   public ModelAdapterEnderChest __junk217874880272427058;

   public ConcurrentHashMapV8$ReduceEntriesTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$ReduceEntriesTask<K, V> var6,
      ConcurrentHashMapV8$BiFun<Entry<K, V>, Entry<K, V>, ? extends Entry<K, V>> var7
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.reducer = var7;
   }

   public Entry<K, V> getRawResult() {
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
            (this.rights = new ConcurrentHashMapV8$ReduceEntriesTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, this.rights, var1))
               .fork();
         }

         Object var8 = null;

         ConcurrentHashMapV8$Node var9;
         while ((var9 = this.advance()) != null) {
            var8 = var8 == null ? var9 : (Entry)var1.apply(var8, var9);
         }

         this.result = (Entry<K, V>)var8;

         for (CountedCompleter var10 = this.firstComplete(); var10 != null; var10 = var10.nextComplete()) {
            ConcurrentHashMapV8$ReduceEntriesTask var11 = (ConcurrentHashMapV8$ReduceEntriesTask)var10;

            for (ConcurrentHashMapV8$ReduceEntriesTask var5 = var11.rights; var5 != null; var5 = var11.rights = var5.nextRight) {
               Entry var7 = var5.result;
               if (var5.result != null) {
                  Entry var6 = var11.result;
                  var11.result = var11.result == null ? var7 : (Entry)var1.apply(var6, var7);
               }
            }
         }
      }
   }
}
