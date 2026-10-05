package io.netty.util.internal.chmv8;

import io.netty.channel.embedded.EmbeddedSocketAddress;
import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe$1;
import io.netty.handler.codec.socks.SocksAuthResponseDecoder$1;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.client.gui.GuiCommandBlock;

public class ConcurrentHashMapV8$EntrySetView<K, V> extends ConcurrentHashMapV8$CollectionView<K, V, Entry<K, V>> implements Serializable, Set<Entry<K, V>> {
   public EpollSocketChannel$EpollSocketUnsafe$1 __junk5991205043792816492;
   public GuiCommandBlock __junk5316473842593914700;
   public SocksAuthResponseDecoder$1 __junk1291435265642630048;
   public static long serialVersionUID;
   public EmbeddedSocketAddress __junk5917298366987139916;

   public ConcurrentHashMapV8$ConcurrentHashMapSpliterator<Entry<K, V>> spliterator166() {
      ConcurrentHashMapV8 var2 = this.map;
      long var3 = var2.sumCount();
      ConcurrentHashMapV8$Node[] var1 = var2.table;
      int var5 = var2.table == null ? 0 : var1.length;
      return new ConcurrentHashMapV8$EntrySpliterator<>(
         var1, var5, 0, var5, var3 < (1078311424L & 218235006L) ? 587070945030619528L & -587070946598840828L : var3, var2
      );
   }

   public ConcurrentHashMapV8$EntrySetView(ConcurrentHashMapV8<K, V> var1) {
      super(var1);
   }

   @Override
   public boolean contains(Object var1) {
      Object var2;
      Object var3;
      Object var4;
      Entry var5;
      return var1 instanceof Entry
         && (var2 = (var5 = (Entry)var1).getKey()) != null
         && (var4 = this.map.get(var2)) != null
         && (var3 = var5.getValue()) != null
         && (var3 == var4 || var3.equals(var4));
   }

   @Override
   public boolean remove(Object var1) {
      Object var2;
      Object var3;
      Entry var4;
      return var1 instanceof Entry && (var2 = (var4 = (Entry)var1).getKey()) != null && (var3 = var4.getValue()) != null && this.map.remove(var2, var3);
   }

   public void forEach(ConcurrentHashMapV8$Action<? super Entry<K, V>> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node[] var2 = this.map.table;
         if (this.map.table != null) {
            ConcurrentHashMapV8$Traverser var3 = new ConcurrentHashMapV8$Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8$Node var4;
            while ((var4 = var3.advance()) != null) {
               var1.apply(new ConcurrentHashMapV8$MapEntry<>(var4.key, var4.val, this.map));
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int var1 = 0;
      ConcurrentHashMapV8$Node[] var2 = this.map.table;
      if (this.map.table != null) {
         ConcurrentHashMapV8$Traverser var3 = new ConcurrentHashMapV8$Traverser(var2, var2.length, 0, var2.length);

         ConcurrentHashMapV8$Node var4;
         while ((var4 = var3.advance()) != null) {
            var1 += var4.hashCode();
         }
      }

      return var1;
   }

   @Override
   public boolean equals(Object var1) {
      Set var2;
      return var1 instanceof Set && ((var2 = (Set)var1) == this || this.containsAll(var2) && var2.containsAll(this));
   }

   @Override
   public boolean addAll(Collection<? extends Entry<K, V>> var1) {
      boolean var2 = false;

      for (Entry var4 : var1) {
         if (this.add(var4)) {
            var2 = true;
         }
      }

      return var2;
   }

   @Override
   public Iterator<Entry<K, V>> iterator() {
      ConcurrentHashMapV8 var1 = this.map;
      ConcurrentHashMapV8$Node[] var2 = var1.table;
      int var3 = var1.table == null ? 0 : var2.length;
      return new ConcurrentHashMapV8$EntryIterator<>(var2, var3, 0, var3, var1);
   }

   public boolean add(Entry<K, V> var1) {
      return this.map.putVal((K)var1.getKey(), (V)var1.getValue(), false) == null;
   }
}
