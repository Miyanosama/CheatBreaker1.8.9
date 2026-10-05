package io.netty.util.internal.chmv8;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.client.model.ModelPig;
import net.minecraft.world.biome.BiomeColorHelper$1;
import org.java_websocket.client.WebSocketClient$1;
import recovered.unidentified.UnidentifiedClass1852;

public class ConcurrentHashMapV8$ValuesView<K, V> extends ConcurrentHashMapV8$CollectionView<K, V, V> implements Serializable, Collection<V> {
   public ModelPig __junk2747805666039527883;
   public static long serialVersionUID;
   public UnidentifiedClass1852 __junk8604892678673815656;
   public BiomeColorHelper$1 __junk1304789281476908807;
   public WebSocketClient$1 __junk5958910668433025009;

   public void forEach(ConcurrentHashMapV8$Action<? super V> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node[] var2 = this.map.table;
         if (this.map.table != null) {
            ConcurrentHashMapV8$Traverser var3 = new ConcurrentHashMapV8$Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8$Node var4;
            while ((var4 = var3.advance()) != null) {
               var1.apply(var4.val);
            }
         }
      }
   }

   @Override
   public boolean contains(Object var1) {
      return this.map.containsValue(var1);
   }

   @Override
   public Iterator<V> iterator() {
      ConcurrentHashMapV8 var1 = this.map;
      ConcurrentHashMapV8$Node[] var2 = var1.table;
      int var3 = var1.table == null ? 0 : var2.length;
      return new ConcurrentHashMapV8$ValueIterator<>(var2, var3, 0, var3, var1);
   }

   @Override
   public boolean addAll(Collection<? extends V> var1) {
      throw new UnsupportedOperationException();
   }

   public ConcurrentHashMapV8$ValuesView(ConcurrentHashMapV8<K, V> var1) {
      super(var1);
   }

   @Override
   public boolean add(V var1) {
      throw new UnsupportedOperationException();
   }

   public ConcurrentHashMapV8$ConcurrentHashMapSpliterator<V> spliterator166() {
      ConcurrentHashMapV8 var2 = this.map;
      long var3 = var2.sumCount();
      ConcurrentHashMapV8$Node[] var1 = var2.table;
      int var5 = var2.table == null ? 0 : var1.length;
      return new ConcurrentHashMapV8$ValueSpliterator<>(
         var1, var5, 0, var5, var3 < (8051916028576274432L & -8051916030378213374L) ? 1375867015L & -4000233950053356968L : var3
      );
   }

   @Override
   public boolean remove(Object var1) {
      if (var1 != null) {
         Iterator var2 = this.iterator();

         while (var2.hasNext()) {
            if (var1.equals(var2.next())) {
               var2.remove();
               return true;
            }
         }
      }

      return false;
   }
}
