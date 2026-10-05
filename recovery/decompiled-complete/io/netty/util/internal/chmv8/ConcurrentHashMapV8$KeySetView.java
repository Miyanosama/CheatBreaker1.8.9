package io.netty.util.internal.chmv8;

import io.netty.handler.timeout.WriteTimeoutHandler$2;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.block.BlockDoubleStoneSlab;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.command.PlayerSelector$2;

public class ConcurrentHashMapV8$KeySetView<K, V> extends ConcurrentHashMapV8$CollectionView<K, V, K> implements Serializable, Set<K> {
   public static long serialVersionUID;
   public V value;
   public ResourcePackRepository$Entry __junk1278379647351597085;
   public BlockDoubleStoneSlab __junk7221023890945910234;
   public PlayerSelector$2 __junk4091160556303188119;
   public WriteTimeoutHandler$2 __junk5332129238630753235;

   @Override
   public boolean addAll(Collection<? extends K> var1) {
      boolean var2 = false;
      Object var3 = this.value;
      if (this.value == null) {
         throw new UnsupportedOperationException();
      } else {
         for (Object var5 : var1) {
            if (this.map.putVal((K)var5, (V)var3, true) == null) {
               var2 = true;
            }
         }

         return var2;
      }
   }

   @Override
   public boolean remove(Object var1) {
      return this.map.remove(var1) != null;
   }

   @Override
   public boolean equals(Object var1) {
      Set var2;
      return var1 instanceof Set && ((var2 = (Set)var1) == this || this.containsAll(var2) && var2.containsAll(this));
   }

   @Override
   public boolean add(K var1) {
      Object var2 = this.value;
      if (this.value == null) {
         throw new UnsupportedOperationException();
      } else {
         return this.map.putVal((K)var1, (V)var2, true) == null;
      }
   }

   @Override
   public boolean contains(Object var1) {
      return this.map.containsKey(var1);
   }

   public V getMappedValue() {
      return this.value;
   }

   public ConcurrentHashMapV8$KeySetView(ConcurrentHashMapV8<K, V> var1, V var2) {
      super(var1);
      this.value = (V)var2;
   }

   public void forEach(ConcurrentHashMapV8$Action<? super K> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node[] var2 = this.map.table;
         if (this.map.table != null) {
            ConcurrentHashMapV8$Traverser var3 = new ConcurrentHashMapV8$Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8$Node var4;
            while ((var4 = var3.advance()) != null) {
               var1.apply(var4.key);
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int var1 = 0;

      for (Object var3 : this) {
         var1 += var3.hashCode();
      }

      return var1;
   }

   public ConcurrentHashMapV8$ConcurrentHashMapSpliterator<K> spliterator166() {
      ConcurrentHashMapV8 var2 = this.map;
      long var3 = var2.sumCount();
      ConcurrentHashMapV8$Node[] var1 = var2.table;
      int var5 = var2.table == null ? 0 : var1.length;
      return new ConcurrentHashMapV8$KeySpliterator<>(
         var1, var5, 0, var5, var3 < (-5350952322891307388L & 537397546L) ? 859972144L & 2273087389736112130L : var3
      );
   }

   @Override
   public Iterator<K> iterator() {
      ConcurrentHashMapV8 var2 = this.map;
      ConcurrentHashMapV8$Node[] var1 = var2.table;
      int var3 = var2.table == null ? 0 : var1.length;
      return new ConcurrentHashMapV8$KeyIterator<>(var1, var3, 0, var3, var2);
   }
}
