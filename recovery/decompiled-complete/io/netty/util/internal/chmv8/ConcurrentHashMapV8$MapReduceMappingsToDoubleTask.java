package io.netty.util.internal.chmv8;

public class ConcurrentHashMapV8$MapReduceMappingsToDoubleTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Double> {
   public ConcurrentHashMapV8$ObjectByObjectToDouble<? super K, ? super V> transformer;
   public ConcurrentHashMapV8$DoubleByDoubleToDouble reducer;
   public double result;
   public ConcurrentHashMapV8$MapReduceMappingsToDoubleTask<K, V> rights;
   public ConcurrentHashMapV8$MapReduceMappingsToDoubleTask<K, V> nextRight;
   public double basis;

   public Double getRawResult() {
      return this.result;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$ObjectByObjectToDouble var1 = this.transformer;
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
               (this.rights = new ConcurrentHashMapV8$MapReduceMappingsToDoubleTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var8 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var8.key, var8.val));
            }

            this.result = var3;

            for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
               ConcurrentHashMapV8$MapReduceMappingsToDoubleTask var10 = (ConcurrentHashMapV8$MapReduceMappingsToDoubleTask)var9;

               for (ConcurrentHashMapV8$MapReduceMappingsToDoubleTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                  var10.result = var2.apply(var10.result, var11.result);
               }
            }
         }
      }
   }

   public ConcurrentHashMapV8$MapReduceMappingsToDoubleTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceMappingsToDoubleTask<K, V> var6,
      ConcurrentHashMapV8$ObjectByObjectToDouble<? super K, ? super V> var7,
      double var8,
      ConcurrentHashMapV8$DoubleByDoubleToDouble var10
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.basis = var8;
      this.reducer = var10;
   }
}
