package io.netty.util.internal.chmv8;

import io.netty.util.internal.MpscLinkedQueue$DefaultNode;
import java.util.Map.Entry;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.BlockStairs;
import net.optifine.shaders.uniform.ShaderUniformBase;
import org.apache.log4j.net.JMSSink;

public class ConcurrentHashMapV8$MapReduceEntriesToDoubleTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Double> {
   public ShaderUniformBase __junk6619196208276001731;
   public BlockStairs __junk3747781205907072777;
   public BlockSourceImpl __junk8895491056227658353;
   public double result;
   public JMSSink __junk1422375781978065178;
   public ConcurrentHashMapV8$MapReduceEntriesToDoubleTask<K, V> nextRight;
   public double basis;
   public BlockLever __junk8296756589464440219;
   public ConcurrentHashMapV8$ObjectToDouble<Entry<K, V>> transformer;
   public MpscLinkedQueue$DefaultNode __junk6876603209831457209;
   public ConcurrentHashMapV8$DoubleByDoubleToDouble reducer;
   public ConcurrentHashMapV8$MapReduceEntriesToDoubleTask<K, V> rights;

   public ConcurrentHashMapV8$MapReduceEntriesToDoubleTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceEntriesToDoubleTask<K, V> var6,
      ConcurrentHashMapV8$ObjectToDouble<Entry<K, V>> var7,
      double var8,
      ConcurrentHashMapV8$DoubleByDoubleToDouble var10
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.basis = var8;
      this.reducer = var10;
   }

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
               (this.rights = new ConcurrentHashMapV8$MapReduceEntriesToDoubleTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var8 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var8));
            }

            this.result = var3;

            for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
               ConcurrentHashMapV8$MapReduceEntriesToDoubleTask var10 = (ConcurrentHashMapV8$MapReduceEntriesToDoubleTask)var9;

               for (ConcurrentHashMapV8$MapReduceEntriesToDoubleTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                  var10.result = var2.apply(var10.result, var11.result);
               }
            }
         }
      }
   }

   public Double getRawResult() {
      return this.result;
   }
}
