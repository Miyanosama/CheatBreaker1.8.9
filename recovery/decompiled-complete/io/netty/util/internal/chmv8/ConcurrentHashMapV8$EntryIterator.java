package io.netty.util.internal.chmv8;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.gui.inventory.GuiContainerCreative$CreativeSlot;

public class ConcurrentHashMapV8$EntryIterator<K, V> extends ConcurrentHashMapV8$BaseIterator<K, V> implements Iterator<Entry<K, V>> {
   public GuiAchievement __junk8566904556012817450;
   public GuiContainerCreative$CreativeSlot __junk8207365367518955936;

   public ConcurrentHashMapV8$EntryIterator(ConcurrentHashMapV8$Node<K, V>[] var1, int var2, int var3, int var4, ConcurrentHashMapV8<K, V> var5) {
      super(var1, var2, var3, var4, var5);
   }

   public Entry<K, V> next() {
      ConcurrentHashMapV8$Node var1 = this.next;
      if (this.next == null) {
         throw new NoSuchElementException();
      } else {
         Object var2 = var1.key;
         Object var3 = var1.val;
         this.lastReturned = var1;
         this.advance();
         return new ConcurrentHashMapV8$MapEntry<>((K)var2, (V)var3, this.map);
      }
   }
}
