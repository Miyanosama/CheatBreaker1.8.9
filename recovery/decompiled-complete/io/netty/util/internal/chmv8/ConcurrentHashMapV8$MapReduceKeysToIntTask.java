package io.netty.util.internal.chmv8;

import io.netty.buffer.AbstractByteBuf;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Library;
import org.java_websocket.SSLSocketChannel$1;

public class ConcurrentHashMapV8$MapReduceKeysToIntTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Integer> {
   public ConcurrentHashMapV8$ObjectToInt<? super K> transformer;
   public ConcurrentHashMapV8$MapReduceKeysToIntTask<K, V> rights;
   public ConcurrentHashMapV8$IntByIntToInt reducer;
   public StructureStrongholdPieces$Library __junk5664608995531397372;
   public ConcurrentHashMapV8$MapReduceKeysToIntTask<K, V> nextRight;
   public int result;
   public AbstractByteBuf __junk5548144254064102514;
   public int basis;
   public SSLSocketChannel$1 __junk195431418894534441;

   public ConcurrentHashMapV8$MapReduceKeysToIntTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceKeysToIntTask<K, V> var6,
      ConcurrentHashMapV8$ObjectToInt<? super K> var7,
      int var8,
      ConcurrentHashMapV8$IntByIntToInt var9
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.basis = var8;
      this.reducer = var9;
   }

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
               (this.rights = new ConcurrentHashMapV8$MapReduceKeysToIntTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var6, var5, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var7 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var7.key));
            }

            this.result = var3;

            for (CountedCompleter var8 = this.firstComplete(); var8 != null; var8 = var8.nextComplete()) {
               ConcurrentHashMapV8$MapReduceKeysToIntTask var9 = (ConcurrentHashMapV8$MapReduceKeysToIntTask)var8;

               for (ConcurrentHashMapV8$MapReduceKeysToIntTask var10 = var9.rights; var10 != null; var10 = var9.rights = var10.nextRight) {
                  var9.result = var2.apply(var9.result, var10.result);
               }
            }
         }
      }
   }
}
