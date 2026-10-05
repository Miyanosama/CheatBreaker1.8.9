package io.netty.util.internal.chmv8;

import net.minecraft.client.Minecraft$2;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms$Deserializer;
import net.minecraft.item.ItemDoor;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$Feature;

public class ConcurrentHashMapV8$MapReduceMappingsToIntTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Integer> {
   public ConcurrentHashMapV8$IntByIntToInt reducer;
   public ItemDoor __junk8093040476328246765;
   public int basis;
   public ComponentScatteredFeaturePieces$Feature __junk8913213274367553103;
   public ConcurrentHashMapV8$ObjectByObjectToInt<? super K, ? super V> transformer;
   public ConcurrentHashMapV8$MapReduceMappingsToIntTask<K, V> nextRight;
   public ItemCameraTransforms$Deserializer __junk1688473032272555897;
   public int result;
   public ConcurrentHashMapV8$MapReduceMappingsToIntTask<K, V> rights;
   public Minecraft$2 __junk5333608249712020522;

   public ConcurrentHashMapV8$MapReduceMappingsToIntTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceMappingsToIntTask<K, V> var6,
      ConcurrentHashMapV8$ObjectByObjectToInt<? super K, ? super V> var7,
      int var8,
      ConcurrentHashMapV8$IntByIntToInt var9
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.basis = var8;
      this.reducer = var9;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$ObjectByObjectToInt var1 = this.transformer;
      if (this.transformer != null) {
         ConcurrentHashMapV8$IntByIntToInt var2 = this.reducer;
         if (this.reducer != null) {
            int var3 = this.basis;
            int var4 = this.baseIndex;

            while (this.batch > 0) {
               int var5 = this.baseLimit;
               int var6;
               if ((var6 = this.baseLimit + var4 >>> 1) <= var4) {
                  break;
               }

               this.addToPendingCount(1);
               (this.rights = new ConcurrentHashMapV8$MapReduceMappingsToIntTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var6, var5, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var7 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var7.key, var7.val));
            }

            this.result = var3;

            for (CountedCompleter var8 = this.firstComplete(); var8 != null; var8 = var8.nextComplete()) {
               ConcurrentHashMapV8$MapReduceMappingsToIntTask var9 = (ConcurrentHashMapV8$MapReduceMappingsToIntTask)var8;

               for (ConcurrentHashMapV8$MapReduceMappingsToIntTask var10 = var9.rights; var10 != null; var10 = var9.rights = var10.nextRight) {
                  var9.result = var2.apply(var9.result, var10.result);
               }
            }
         }
      }
   }

   public Integer getRawResult() {
      return this.result;
   }
}
