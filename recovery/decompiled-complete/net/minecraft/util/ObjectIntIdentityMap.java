package net.minecraft.util;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumUsage;
import net.minecraft.network.play.server.S23PacketBlockChange;
import recovered.unidentified.UnidentifiedClass1187;

public class ObjectIntIdentityMap<T> implements IObjectIntIterable<T> {
   public IdentityHashMap<T, Integer> identityMap = new IdentityHashMap<>(512);
   public S23PacketBlockChange field_0004;
   public UnidentifiedClass1187 field_0001;
   public List<T> objectList = Lists.newArrayList();
   public VertexFormatElement$EnumUsage field_0000;

   @Override
   public Iterator<T> iterator() {
      return Iterators.filter(this.objectList.iterator(), Predicates.notNull());
   }

   public void put(T var1, int var2) {
      this.identityMap.put((T)var1, var2);

      while (this.objectList.size() <= var2) {
         this.objectList.add(null);
      }

      this.objectList.set(var2, (T)var1);
   }

   public int get(T var1) {
      Integer var2 = this.identityMap.get(var1);
      return var2 == null ? -1 : var2;
   }

   public T getByValue(int var1) {
      return var1 >= 0 && var1 < this.objectList.size() ? this.objectList.get(var1) : null;
   }
}
