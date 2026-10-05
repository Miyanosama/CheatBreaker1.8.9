package io.netty.util.internal.chmv8;

import net.minecraft.enchantment.EnchantmentArrowFire;

public class ConcurrentHashMapV8$BaseIterator<K, V> extends ConcurrentHashMapV8$Traverser<K, V> {
   public EnchantmentArrowFire __junk2677511835782767270;
   public ConcurrentHashMapV8$Node<K, V> lastReturned;
   public ConcurrentHashMapV8<K, V> map;

   public boolean hasMoreElements() {
      return this.next != null;
   }

   public boolean hasNext() {
      return this.next != null;
   }

   public void remove() {
      ConcurrentHashMapV8$Node var1 = this.lastReturned;
      if (this.lastReturned == null) {
         throw new IllegalStateException();
      } else {
         this.lastReturned = null;
         this.map.replaceNode(var1.key, null, null);
      }
   }

   public ConcurrentHashMapV8$BaseIterator(ConcurrentHashMapV8$Node<K, V>[] var1, int var2, int var3, int var4, ConcurrentHashMapV8<K, V> var5) {
      super(var1, var2, var3, var4);
      this.map = var5;
      this.advance();
   }
}
