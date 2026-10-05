package io.netty.util.internal.chmv8;

import java.util.Map.Entry;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Variants;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import recovered.unidentified.UnidentifiedClass1784;

public class ConcurrentHashMapV8$ForEachTransformedEntryTask<K, V, U> extends ConcurrentHashMapV8$BulkTask<K, V, Void> {
   public UnidentifiedClass1784 __junk6545180291622480926;
   public ConcurrentHashMapV8$Action<? super U> action;
   public ModelBlockDefinition$Variants __junk4084657179349285794;
   public ConcurrentHashMapV8$Fun<Entry<K, V>, ? extends U> transformer;
   public WorldGenMegaJungle __junk9047199485508633819;

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
               new ConcurrentHashMapV8$ForEachTransformedEntryTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
            }

            while ((var6 = this.advance()) != null) {
               Object var7;
               if ((var7 = var1.apply(var6)) != null) {
                  var2.apply(var7);
               }
            }

            this.propagateCompletion();
         }
      }
   }

   public ConcurrentHashMapV8$ForEachTransformedEntryTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$Fun<Entry<K, V>, ? extends U> var6,
      ConcurrentHashMapV8$Action<? super U> var7
   ) {
      super(var1, var2, var3, var4, var5);
      this.transformer = var6;
      this.action = var7;
   }
}
