package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.module.type.MiniMapModule;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.server.integrated.IntegratedServer$3;
import net.minecraft.util.EnumFacing;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryElement;

public abstract class ConcurrentHashMapV8$CollectionView<K, V, E> implements Serializable, Collection<E> {
   public CategoryElement __junk6319171162505345338;
   public static long serialVersionUID;
   public static String oomeMsg;
   public ConcurrentHashMapV8<K, V> map;
   public IntegratedServer$3 __junk1471627742767488650;
   public MiniMapModule __junk1602925575295747734;
   public EnumFacing __junk156759092165740283;

   @Override
   public boolean containsAll(Collection<?> var1) {
      if (var1 != this) {
         for (Object var3 : var1) {
            if (var3 == null || !this.contains(var3)) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public int size() {
      return this.map.size();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append('[');
      Iterator var2 = this.iterator();
      if (var2.hasNext()) {
         while (true) {
            Object var3 = var2.next();
            var1.append(var3 == this ? "(this Collection)" : var3);
            if (!var2.hasNext()) {
               break;
            }

            var1.append(',').append(' ');
         }
      }

      return var1.append(']').toString();
   }

   public ConcurrentHashMapV8<K, V> getMap() {
      return this.map;
   }

   @Override
   public void clear() {
      this.map.clear();
   }

   public ConcurrentHashMapV8$CollectionView(ConcurrentHashMapV8<K, V> var1) {
      this.map = var1;
   }

   @Override
   public Object[] toArray() {
      long var1 = this.map.mappingCount();
      if (var1 > (8794632445564551167L & 2147483639L)) {
         throw new OutOfMemoryError("Required array size too large");
      } else {
         int var3 = (int)var1;
         Object[] var4 = new Object[var3];
         int var5 = 0;

         for (Object var7 : this) {
            if (var5 == var3) {
               if (var3 >= 2147483639) {
                  throw new OutOfMemoryError("Required array size too large");
               }

               if (var3 >= 1073741819) {
                  var3 = 2147483639;
               } else {
                  var3 += (var3 >>> 1) + 1;
               }

               var4 = Arrays.copyOf(var4, var3);
            }

            var4[var5++] = var7;
         }

         return var5 == var3 ? var4 : Arrays.copyOf(var4, var5);
      }
   }

   @Override
   public <T> T[] toArray(T[] var1) {
      long var2 = this.map.mappingCount();
      if (var2 > (2147483639L & 2147483647L)) {
         throw new OutOfMemoryError("Required array size too large");
      } else {
         int var4 = (int)var2;
         Object[] var5 = var1.length >= var4 ? var1 : (Object[])Array.newInstance(var1.getClass().getComponentType(), var4);
         int var6 = var5.length;
         int var7 = 0;

         for (Object var9 : this) {
            if (var7 == var6) {
               if (var6 >= 2147483639) {
                  throw new OutOfMemoryError("Required array size too large");
               }

               if (var6 >= 1073741819) {
                  var6 = 2147483639;
               } else {
                  var6 += (var6 >>> 1) + 1;
               }

               var5 = Arrays.copyOf(var5, var6);
            }

            var5[var7++] = var9;
         }

         if (var1 == var5 && var7 < var6) {
            var5[var7] = null;
            return (T[])var5;
         } else {
            return (T[])(var7 == var6 ? var5 : Arrays.copyOf(var5, var7));
         }
      }
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      boolean var2 = false;
      Iterator var3 = this.iterator();

      while (var3.hasNext()) {
         if (!var1.contains(var3.next())) {
            var3.remove();
            var2 = true;
         }
      }

      return var2;
   }

   @Override
   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   @Override
   public abstract Iterator<E> iterator();

   @Override
   public abstract boolean contains(Object var1);

   @Override
   public abstract boolean remove(Object var1);

   @Override
   public boolean removeAll(Collection<?> var1) {
      boolean var2 = false;
      Iterator var3 = this.iterator();

      while (var3.hasNext()) {
         if (var1.contains(var3.next())) {
            var3.remove();
            var2 = true;
         }
      }

      return var2;
   }
}
