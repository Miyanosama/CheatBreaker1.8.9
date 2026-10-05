package io.netty.util.internal.chmv8;

import net.minecraft.client.model.ModelSign;
import org.apache.log4j.chainsaw.LoggingReceiver$Slurper;

public class ConcurrentHashMapV8$MapReduceKeysToLongTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Long> {
   public ConcurrentHashMapV8$LongByLongToLong reducer;
   public ModelSign __junk2499844756720329898;
   public long basis;
   public ConcurrentHashMapV8$MapReduceKeysToLongTask<K, V> rights;
   public LoggingReceiver$Slurper __junk7449419754241936369;
   public ConcurrentHashMapV8$MapReduceKeysToLongTask<K, V> nextRight;
   public ConcurrentHashMapV8$ObjectToLong<? super K> transformer;
   public long result;

   public Long getRawResult() {
      return this.result;
   }

   public ConcurrentHashMapV8$MapReduceKeysToLongTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceKeysToLongTask<K, V> var6,
      ConcurrentHashMapV8$ObjectToLong<? super K> var7,
      long var8,
      ConcurrentHashMapV8$LongByLongToLong var10
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.basis = var8;
      this.reducer = var10;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$ObjectToLong var1 = this.transformer;
      if (this.transformer != null) {
         ConcurrentHashMapV8$LongByLongToLong var2 = this.reducer;
         if (this.reducer != null) {
            long var3 = this.basis;
            int var5 = this.baseIndex;

            while (this.batch > 0) {
               int var6 = this.baseLimit;
               int var7;
               if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                  break;
               }

               this.addToPendingCount(1);
               (this.rights = new ConcurrentHashMapV8$MapReduceKeysToLongTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var8 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var8.key));
            }

            this.result = var3;

            for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
               ConcurrentHashMapV8$MapReduceKeysToLongTask var10 = (ConcurrentHashMapV8$MapReduceKeysToLongTask)var9;

               for (ConcurrentHashMapV8$MapReduceKeysToLongTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                  var10.result = var2.apply(var10.result, var11.result);
               }
            }
         }
      }
   }
}
