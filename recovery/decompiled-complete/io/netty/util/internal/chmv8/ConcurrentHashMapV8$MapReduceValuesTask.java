package io.netty.util.internal.chmv8;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem$9;

public class ConcurrentHashMapV8$MapReduceValuesTask<K, V, U> extends ConcurrentHashMapV8$BulkTask<K, V, U> {
   public RenderItem$9 __junk1087218916889207549;
   public ConcurrentHashMapV8$MapReduceValuesTask<K, V, U> nextRight;
   public U result;
   public ConcurrentHashMapV8$MapReduceValuesTask<K, V, U> rights;
   public ConcurrentHashMapV8$BiFun<? super U, ? super U, ? extends U> reducer;
   public OpenGlHelper __junk8007306070887555802;
   public ConcurrentHashMapV8$Fun<? super V, ? extends U> transformer;

   @Override
   public U getRawResult() {
      return this.result;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$Fun var1 = this.transformer;
      if (this.transformer != null) {
         ConcurrentHashMapV8$BiFun var2 = this.reducer;
         if (this.reducer != null) {
            int var3 = this.baseIndex;

            while (this.batch > 0) {
               int var4 = this.baseLimit;
               int var5;
               if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                  break;
               }

               this.addToPendingCount(1);
               (this.rights = new ConcurrentHashMapV8$MapReduceValuesTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, this.rights, var1, var2
                  ))
                  .fork();
            }

            Object var9 = null;

            ConcurrentHashMapV8$Node var10;
            while ((var10 = this.advance()) != null) {
               Object var12;
               if ((var12 = var1.apply(var10.val)) != null) {
                  var9 = var9 == null ? var12 : var2.apply(var9, var12);
               }
            }

            this.result = (U)var9;

            for (CountedCompleter var11 = this.firstComplete(); var11 != null; var11 = var11.nextComplete()) {
               ConcurrentHashMapV8$MapReduceValuesTask var13 = (ConcurrentHashMapV8$MapReduceValuesTask)var11;

               for (ConcurrentHashMapV8$MapReduceValuesTask var6 = var13.rights; var6 != null; var6 = var13.rights = var6.nextRight) {
                  Object var8 = var6.result;
                  if (var6.result != null) {
                     Object var7 = var13.result;
                     var13.result = (U)(var13.result == null ? var8 : var2.apply(var7, var8));
                  }
               }
            }
         }
      }
   }

   public ConcurrentHashMapV8$MapReduceValuesTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceValuesTask<K, V, U> var6,
      ConcurrentHashMapV8$Fun<? super V, ? extends U> var7,
      ConcurrentHashMapV8$BiFun<? super U, ? super U, ? extends U> var8
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.reducer = var8;
   }
}
