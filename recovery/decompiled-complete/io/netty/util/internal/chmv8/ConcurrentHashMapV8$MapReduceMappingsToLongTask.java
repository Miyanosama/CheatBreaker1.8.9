package io.netty.util.internal.chmv8;

import io.netty.channel.AbstractChannel;
import io.netty.handler.codec.spdy.SpdyHeaders$1;
import net.minecraft.creativetab.CreativeTabs$8;
import recovered.unidentified.UnidentifiedClass0429;

public class ConcurrentHashMapV8$MapReduceMappingsToLongTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Long> {
   public UnidentifiedClass0429 __junk3571596391581505299;
   public CreativeTabs$8 __junk3299756660964958317;
   public long result;
   public long basis;
   public SpdyHeaders$1 __junk4325432265319464627;
   public ConcurrentHashMapV8$MapReduceMappingsToLongTask<K, V> rights;
   public ConcurrentHashMapV8$ObjectByObjectToLong<? super K, ? super V> transformer;
   public ConcurrentHashMapV8$MapReduceMappingsToLongTask<K, V> nextRight;
   public AbstractChannel __junk8400971300122059728;
   public ConcurrentHashMapV8$LongByLongToLong reducer;

   public Long getRawResult() {
      return this.result;
   }

   public ConcurrentHashMapV8$MapReduceMappingsToLongTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceMappingsToLongTask<K, V> var6,
      ConcurrentHashMapV8$ObjectByObjectToLong<? super K, ? super V> var7,
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
      ConcurrentHashMapV8$ObjectByObjectToLong var1 = this.transformer;
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
               (this.rights = new ConcurrentHashMapV8$MapReduceMappingsToLongTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var8 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var8.key, var8.val));
            }

            this.result = var3;

            for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
               ConcurrentHashMapV8$MapReduceMappingsToLongTask var10 = (ConcurrentHashMapV8$MapReduceMappingsToLongTask)var9;

               for (ConcurrentHashMapV8$MapReduceMappingsToLongTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                  var10.result = var2.apply(var10.result, var11.result);
               }
            }
         }
      }
   }
}
