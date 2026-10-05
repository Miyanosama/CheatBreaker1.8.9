package io.netty.util.internal.chmv8;

import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.optifine.shaders.ItemAliases;
import recovered.unidentified.UnidentifiedClass4913;

public class ConcurrentHashMapV8$ForEachTransformedKeyTask<K, V, U> extends ConcurrentHashMapV8$BulkTask<K, V, Void> {
   public ConcurrentHashMapV8$Action<? super U> action;
   public UnidentifiedClass4913 __junk5901975007665530439;
   public NoiseGeneratorSimplex __junk5690560202816379897;
   public ConcurrentHashMapV8$Fun<? super K, ? extends U> transformer;
   public ItemAliases __junk4390048664344665438;

   public ConcurrentHashMapV8$ForEachTransformedKeyTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$Fun<? super K, ? extends U> var6,
      ConcurrentHashMapV8$Action<? super U> var7
   ) {
      super(var1, var2, var3, var4, var5);
      this.transformer = var6;
      this.action = var7;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$Fun var1 = this.transformer;
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
               new ConcurrentHashMapV8$ForEachTransformedKeyTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
            }

            while ((var6 = this.advance()) != null) {
               Object var7;
               if ((var7 = var1.apply(var6.key)) != null) {
                  var2.apply(var7);
               }
            }

            this.propagateCompletion();
         }
      }
   }
}
