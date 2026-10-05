package io.netty.util.internal.chmv8;

import net.minecraft.tileentity.TileEntityPiston;

public class ConcurrentHashMapV8$ForwardingNode<K, V> extends ConcurrentHashMapV8$Node<K, V> {
   public TileEntityPiston __junk9169966257203331262;
   public ConcurrentHashMapV8$Node<K, V>[] nextTable;

   @Override
   public ConcurrentHashMapV8$Node<K, V> find(int var1, Object var2) {
      ConcurrentHashMapV8$Node[] var3 = this.nextTable;

      ConcurrentHashMapV8$Node var4;
      int var5;
      label41:
      while (var2 != null && var3 != null && (var5 = var3.length) != 0 && (var4 = ConcurrentHashMapV8.tabAt(var3, var5 - 1 & var1)) != null) {
         do {
            int var6 = var4.hash;
            if (var4.hash == var1) {
               Object var7 = var4.key;
               if (var4.key == var2 || var7 != null && var2.equals(var7)) {
                  return var4;
               }
            }

            if (var6 < 0) {
               if (!(var4 instanceof ConcurrentHashMapV8$ForwardingNode)) {
                  return var4.find(var1, var2);
               }

               var3 = ((ConcurrentHashMapV8$ForwardingNode)var4).nextTable;
               continue label41;
            }
         } while ((var4 = var4.next) != null);

         return null;
      }

      return null;
   }

   public ConcurrentHashMapV8$ForwardingNode(ConcurrentHashMapV8$Node<K, V>[] var1) {
      super(-1, null, null, null);
      this.nextTable = var1;
   }
}
