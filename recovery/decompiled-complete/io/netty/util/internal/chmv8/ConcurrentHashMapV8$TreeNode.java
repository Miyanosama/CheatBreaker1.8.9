package io.netty.util.internal.chmv8;

import io.netty.channel.oio.OioByteStreamChannel;
import javax.vecmath.GMatrix;

public class ConcurrentHashMapV8$TreeNode<K, V> extends ConcurrentHashMapV8$Node<K, V> {
   public ConcurrentHashMapV8$TreeNode<K, V> left;
   public ConcurrentHashMapV8$TreeNode<K, V> prev;
   public OioByteStreamChannel __junk2784095071412178872;
   public ConcurrentHashMapV8$TreeNode<K, V> parent;
   public GMatrix __junk3819068514615673495;
   public boolean red;
   public ConcurrentHashMapV8$TreeNode<K, V> right;

   @Override
   public ConcurrentHashMapV8$Node<K, V> find(int var1, Object var2) {
      return this.findTreeNode(var1, var2, null);
   }

   public ConcurrentHashMapV8$TreeNode<K, V> findTreeNode(int var1, Object var2, Class<?> var3) {
      if (var2 != null) {
         ConcurrentHashMapV8$TreeNode var4 = this;

         do {
            ConcurrentHashMapV8$TreeNode var9 = var4.left;
            ConcurrentHashMapV8$TreeNode var10 = var4.right;
            int var5 = var4.hash;
            if (var4.hash > var1) {
               var4 = var9;
            } else if (var5 < var1) {
               var4 = var10;
            } else {
               Object var7 = var4.key;
               if (var4.key == var2 || var7 != null && var2.equals(var7)) {
                  return var4;
               }

               if (var9 == null && var10 == null) {
                  break;
               }

               int var6;
               if ((var3 != null || (var3 = ConcurrentHashMapV8.comparableClassFor(var2)) != null)
                  && (var6 = ConcurrentHashMapV8.compareComparables(var3, var2, var7)) != 0) {
                  var4 = var6 < 0 ? var9 : var10;
               } else if (var9 == null) {
                  var4 = var10;
               } else {
                  ConcurrentHashMapV8$TreeNode var8;
                  if (var10 != null && (var8 = var10.findTreeNode(var1, var2, var3)) != null) {
                     return var8;
                  }

                  var4 = var9;
               }
            }
         } while (var4 != null);
      }

      return null;
   }

   public ConcurrentHashMapV8$TreeNode(int var1, K var2, V var3, ConcurrentHashMapV8$Node<K, V> var4, ConcurrentHashMapV8$TreeNode<K, V> var5) {
      super(var1, (K)var2, (V)var3, var4);
      this.parent = var5;
   }
}
