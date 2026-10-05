package io.netty.util.internal.chmv8;

import io.netty.util.concurrent.FailedFuture;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.creativetab.CreativeTabs$3;
import net.optifine.model.BlockModelUtils;
import net.optifine.reflect.FieldLocatorTypes;

public abstract class ConcurrentHashMapV8$BulkTask<K, V, R> extends CountedCompleter<R> {
   public ConcurrentHashMapV8$Node<K, V> next;
   public BlockHelper __junk4503338295324306637;
   public int batch;
   public int baseSize;
   public int index;
   public CreativeTabs$3 __junk1395579819651779464;
   public ConcurrentHashMapV8$Node<K, V>[] tab;
   public int baseIndex;
   public GlobalEventExecutor __junk9536953202926029;
   public int baseLimit;
   public BlockModelUtils __junk5187408408313951759;
   public FieldLocatorTypes __junk4505902296881721143;
   public FailedFuture __junk2268309129232380808;

   public ConcurrentHashMapV8$BulkTask(ConcurrentHashMapV8$BulkTask<K, V, ?> var1, int var2, int var3, int var4, ConcurrentHashMapV8$Node<K, V>[] var5) {
      super(var1);
      this.batch = var2;
      this.index = this.baseIndex = var3;
      if ((this.tab = var5) == null) {
         this.baseSize = this.baseLimit = 0;
      } else if (var1 == null) {
         this.baseSize = this.baseLimit = var5.length;
      } else {
         this.baseLimit = var4;
         this.baseSize = var1.baseSize;
      }
   }

   public ConcurrentHashMapV8$Node<K, V> advance() {
      Object var1 = this.next;
      if (this.next != null) {
         var1 = ((ConcurrentHashMapV8$Node)var1).next;
      }

      while (true) {
         if (var1 != null) {
            return this.next = (ConcurrentHashMapV8$Node<K, V>)var1;
         }

         if (this.baseIndex >= this.baseLimit) {
            break;
         }

         ConcurrentHashMapV8$Node[] var2 = this.tab;
         if (this.tab == null) {
            break;
         }

         int var4;
         int var10000 = var4 = var2.length;
         int var3 = this.index;
         if (var10000 <= this.index || var3 < 0) {
            break;
         }

         if ((var1 = ConcurrentHashMapV8.tabAt(var2, this.index)) != null && ((ConcurrentHashMapV8$Node)var1).hash < 0) {
            if (var1 instanceof ConcurrentHashMapV8$ForwardingNode) {
               this.tab = ((ConcurrentHashMapV8$ForwardingNode)var1).nextTable;
               var1 = null;
               continue;
            }

            if (var1 instanceof ConcurrentHashMapV8$TreeBin) {
               var1 = ((ConcurrentHashMapV8$TreeBin)var1).first;
            } else {
               var1 = null;
            }
         }

         if ((this.index = this.index + this.baseSize) >= var4) {
            this.index = ++this.baseIndex;
         }
      }

      return this.next = null;
   }
}
