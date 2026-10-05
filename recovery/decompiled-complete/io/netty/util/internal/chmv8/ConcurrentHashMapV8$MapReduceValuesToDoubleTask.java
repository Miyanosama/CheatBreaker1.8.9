package io.netty.util.internal.chmv8;

import net.minecraft.util.MouseHelper;
import org.scijava.nativelib.NativeLibraryUtil$Processor;

public class ConcurrentHashMapV8$MapReduceValuesToDoubleTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Double> {
   public ConcurrentHashMapV8$DoubleByDoubleToDouble reducer;
   public double basis;
   public ConcurrentHashMapV8$ObjectToDouble<? super V> transformer;
   public double result;
   public NativeLibraryUtil$Processor __junk338088037993442238;
   public MouseHelper __junk2411645310662193862;
   public ConcurrentHashMapV8$MapReduceValuesToDoubleTask<K, V> nextRight;
   public ConcurrentHashMapV8$MapReduceValuesToDoubleTask<K, V> rights;

   @Override
   public void compute() {
      ConcurrentHashMapV8$ObjectToDouble var1 = this.transformer;
      if (this.transformer != null) {
         ConcurrentHashMapV8$DoubleByDoubleToDouble var2 = this.reducer;
         if (this.reducer != null) {
            double var3 = this.basis;
            int var5 = this.baseIndex;

            while (this.batch > 0) {
               int var6 = this.baseLimit;
               int var7;
               if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                  break;
               }

               this.addToPendingCount(1);
               (this.rights = new ConcurrentHashMapV8$MapReduceValuesToDoubleTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var8 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var8.val));
            }

            this.result = var3;

            for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
               ConcurrentHashMapV8$MapReduceValuesToDoubleTask var10 = (ConcurrentHashMapV8$MapReduceValuesToDoubleTask)var9;

               for (ConcurrentHashMapV8$MapReduceValuesToDoubleTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                  var10.result = var2.apply(var10.result, var11.result);
               }
            }
         }
      }
   }

   public ConcurrentHashMapV8$MapReduceValuesToDoubleTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceValuesToDoubleTask<K, V> var6,
      ConcurrentHashMapV8$ObjectToDouble<? super V> var7,
      double var8,
      ConcurrentHashMapV8$DoubleByDoubleToDouble var10
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.basis = var8;
      this.reducer = var10;
   }

   public Double getRawResult() {
      return this.result;
   }
}
