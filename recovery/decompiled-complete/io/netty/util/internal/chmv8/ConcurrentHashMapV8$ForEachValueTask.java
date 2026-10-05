package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.ui.element.module.ModulesGuiButtonElement;
import recovered.unidentified.UnidentifiedClass0440;
import recovered.unidentified.UnidentifiedClass1595;

public class ConcurrentHashMapV8$ForEachValueTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Void> {
   public ConcurrentHashMapV8$Action<? super V> action;
   public UnidentifiedClass1595 __junk2579465233246325573;
   public UnidentifiedClass0440 __junk4500995022223095727;
   public ModulesGuiButtonElement __junk3651440936726579909;

   public ConcurrentHashMapV8$ForEachValueTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$Action<? super V> var6
   ) {
      super(var1, var2, var3, var4, var5);
      this.action = var6;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$Action var1 = this.action;
      if (this.action != null) {
         int var2 = this.baseIndex;

         while (this.batch > 0) {
            int var3 = this.baseLimit;
            int var4;
            if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
               break;
            }

            this.addToPendingCount(1);
            new ConcurrentHashMapV8$ForEachValueTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, var1).fork();
         }

         while ((var5 = this.advance()) != null) {
            var1.apply(var5.val);
         }

         this.propagateCompletion();
      }
   }
}
