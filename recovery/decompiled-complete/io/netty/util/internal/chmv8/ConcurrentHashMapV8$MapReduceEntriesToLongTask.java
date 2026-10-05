package io.netty.util.internal.chmv8;

import io.netty.handler.codec.compression.ZlibUtil;
import java.util.Map.Entry;
import net.minecraft.client.renderer.entity.RenderZombie$1;
import net.minecraft.entity.monster.EntityEnderman$1;

public class ConcurrentHashMapV8$MapReduceEntriesToLongTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Long> {
   public ConcurrentHashMapV8$MapReduceEntriesToLongTask<K, V> nextRight;
   public ConcurrentHashMapV8$LongByLongToLong reducer;
   public RenderZombie$1 __junk191580764145701058;
   public long result;
   public ZlibUtil __junk5154253685802922026;
   public ConcurrentHashMapV8$ObjectToLong<Entry<K, V>> transformer;
   public ConcurrentHashMapV8$MapReduceEntriesToLongTask<K, V> rights;
   public EntityEnderman$1 __junk4991166303739596727;
   public long basis;

   public Long getRawResult() {
      return this.result;
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
               (this.rights = new ConcurrentHashMapV8$MapReduceEntriesToLongTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var8 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var8));
            }

            this.result = var3;

            for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
               ConcurrentHashMapV8$MapReduceEntriesToLongTask var10 = (ConcurrentHashMapV8$MapReduceEntriesToLongTask)var9;

               for (ConcurrentHashMapV8$MapReduceEntriesToLongTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                  var10.result = var2.apply(var10.result, var11.result);
               }
            }
         }
      }
   }

   public ConcurrentHashMapV8$MapReduceEntriesToLongTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceEntriesToLongTask<K, V> var6,
      ConcurrentHashMapV8$ObjectToLong<Entry<K, V>> var7,
      long var8,
      ConcurrentHashMapV8$LongByLongToLong var10
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.basis = var8;
      this.reducer = var10;
   }
}
