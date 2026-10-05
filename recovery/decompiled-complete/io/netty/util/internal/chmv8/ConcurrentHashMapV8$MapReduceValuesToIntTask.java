package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.optifine.shaders.Shaders$1;

public class ConcurrentHashMapV8$MapReduceValuesToIntTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Integer> {
   public int basis;
   public ConcurrentHashMapV8$ObjectToInt<? super V> transformer;
   public ConcurrentHashMapV8$MapReduceValuesToIntTask<K, V> rights;
   public AbstractScrollableElement __junk4861675851867727502;
   public ConcurrentHashMapV8$IntByIntToInt reducer;
   public EntitySpawnPlacementRegistry __junk8597854211850139720;
   public int result;
   public ConcurrentHashMapV8$MapReduceValuesToIntTask<K, V> nextRight;
   public Shaders$1 __junk5670842255617455315;

   public Integer getRawResult() {
      return this.result;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$ObjectToInt var1 = this.transformer;
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
               (this.rights = new ConcurrentHashMapV8$MapReduceValuesToIntTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var6, var5, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var7 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var7.val));
            }

            this.result = var3;

            for (CountedCompleter var8 = this.firstComplete(); var8 != null; var8 = var8.nextComplete()) {
               ConcurrentHashMapV8$MapReduceValuesToIntTask var9 = (ConcurrentHashMapV8$MapReduceValuesToIntTask)var8;

               for (ConcurrentHashMapV8$MapReduceValuesToIntTask var10 = var9.rights; var10 != null; var10 = var9.rights = var10.nextRight) {
                  var9.result = var2.apply(var9.result, var10.result);
               }
            }
         }
      }
   }

   public ConcurrentHashMapV8$MapReduceValuesToIntTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceValuesToIntTask<K, V> var6,
      ConcurrentHashMapV8$ObjectToInt<? super V> var7,
      int var8,
      ConcurrentHashMapV8$IntByIntToInt var9
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.basis = var8;
      this.reducer = var9;
   }
}
