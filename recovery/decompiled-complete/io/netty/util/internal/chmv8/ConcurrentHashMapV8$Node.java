package io.netty.util.internal.chmv8;

import java.util.Map.Entry;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.item.ItemArmor$ArmorMaterial;

public class ConcurrentHashMapV8$Node<K, V> implements Entry<K, V> {
   public volatile V val;
   public EntityChicken __junk8251592258228549024;
   public K key;
   public TileEntityBeaconRenderer __junk5398042963002463509;
   public BlockEndPortal __junk1898079279829988981;
   public ItemArmor$ArmorMaterial __junk3446961019525310564;
   public int hash;
   public volatile ConcurrentHashMapV8$Node<K, V> next;

   @Override
   public String toString() {
      return this.key + "=" + this.val;
   }

   public ConcurrentHashMapV8$Node(int var1, K var2, V var3, ConcurrentHashMapV8$Node<K, V> var4) {
      this.hash = var1;
      this.key = (K)var2;
      this.val = (V)var3;
      this.next = var4;
   }

   @Override
   public V setValue(V var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public V getValue() {
      return this.val;
   }

   @Override
   public int hashCode() {
      return this.key.hashCode() ^ this.val.hashCode();
   }

   @Override
   public K getKey() {
      return this.key;
   }

   public ConcurrentHashMapV8$Node<K, V> find(int var1, Object var2) {
      ConcurrentHashMapV8$Node var3 = this;
      if (var2 != null) {
         do {
            if (var3.hash == var1) {
               Object var4 = var3.key;
               if (var3.key == var2 || var4 != null && var2.equals(var4)) {
                  return var3;
               }
            }
         } while ((var3 = var3.next) != null);
      }

      return null;
   }

   @Override
   public boolean equals(Object var1) {
      Object var2;
      Object var3;
      Entry var5;
      if (var1 instanceof Entry
         && (var2 = (var5 = (Entry)var1).getKey()) != null
         && (var3 = var5.getValue()) != null
         && (var2 == this.key || var2.equals(this.key))) {
         Object var4 = this.val;
         if (var3 == this.val || var3.equals(var4)) {
            return true;
         }
      }

      return false;
   }
}
