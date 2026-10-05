package io.netty.util.collection;

import io.netty.channel.socket.nio.NioServerSocketChannel$NioServerSocketChannelConfig;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.client.gui.spectator.SpectatorMenu$1;
import net.minecraft.util.StatCollector;
import recovered.unidentified.UnidentifiedClass1135;

public class IntObjectHashMap<V> implements IntObjectMap<V>, Iterable<IntObjectMap$Entry<V>> {
   public V[] values;
   public StatCollector __junk5943579045535512953;
   public int maxSize;
   public int size;
   public int[] keys;
   public float loadFactor;
   public UnidentifiedClass1135 __junk4834191480416508695;
   public static float DEFAULT_LOAD_FACTOR;
   public static Object NULL_VALUE = new Object();
   public NioServerSocketChannel$NioServerSocketChannelConfig __junk9078250865664325760;
   public SpectatorMenu$1 __junk6941275191279665544;
   public static int DEFAULT_CAPACITY;

   @Override
   public V get(int var1) {
      int var2 = this.indexOf(var1);
      return var2 == -1 ? null : toExternal(this.values[var2]);
   }

   @Override
   public String toString() {
      if (this.size == 0) {
         return "{}";
      } else {
         StringBuilder var1 = new StringBuilder(4 * this.size);

         for (int var2 = 0; var2 < this.values.length; var2++) {
            Object var3 = this.values[var2];
            if (var3 != null) {
               var1.append(var1.length() == 0 ? "{" : ", ");
               var1.append(this.keys[var2]).append('=').append(var3 == this ? "(this Map)" : var3);
            }
         }

         return var1.append('}').toString();
      }
   }

   public void growSize() {
      this.size++;
      if (this.size > this.maxSize) {
         this.rehash(adjustCapacity((int)Math.min(this.keys.length * 2.0, 2.147483639E9)));
      } else if (this.size == this.keys.length) {
         this.rehash(this.keys.length);
      }
   }

   @Override
   public int hashCode() {
      int var1 = this.size;

      for (int var2 = 0; var2 < this.keys.length; var2++) {
         var1 ^= this.keys[var2];
      }

      return var1;
   }

   public IntObjectHashMap() {
      this(11, 0.5F);
   }

   @Override
   public V remove(int var1) {
      int var2 = this.indexOf(var1);
      if (var2 == -1) {
         return null;
      } else {
         Object var3 = this.values[var2];
         this.removeAt(var2);
         return toExternal((V)var3);
      }
   }

   @Override
   public void clear() {
      Arrays.fill(this.keys, 0);
      Arrays.fill(this.values, null);
      this.size = 0;
   }

   @Override
   public boolean containsValue(V var1) {
      Object var2 = toInternal(var1);

      for (int var3 = 0; var3 < this.values.length; var3++) {
         if (this.values[var3] != null && this.values[var3].equals(var2)) {
            return true;
         }
      }

      return false;
   }

   public static int adjustCapacity(int var0) {
      return var0 | 1;
   }

   public void removeAt(int var1) {
      this.size--;
      this.keys[var1] = 0;
      this.values[var1] = null;
      int var2 = var1;

      for (int var3 = this.probeNext(var1); this.values[var3] != null; var3 = this.probeNext(var3)) {
         int var4 = this.hashIndex(this.keys[var3]);
         if (var3 < var4 && (var4 <= var2 || var2 <= var3) || var4 <= var2 && var2 <= var3) {
            this.keys[var2] = this.keys[var3];
            this.values[var2] = this.values[var3];
            this.keys[var3] = 0;
            this.values[var3] = null;
            var2 = var3;
         }
      }
   }

   public int hashIndex(int var1) {
      return var1 % this.keys.length;
   }

   @Override
   public V[] values(Class<V> var1) {
      Object[] var2 = (Object[])Array.newInstance(var1, this.size());
      int var3 = 0;

      for (int var4 = 0; var4 < this.values.length; var4++) {
         if (this.values[var4] != null) {
            var2[var3++] = this.values[var4];
         }
      }

      return (V[])var2;
   }

   public static <T> T toInternal(T var0) {
      return (T)(var0 == null ? NULL_VALUE : var0);
   }

   @Override
   public Iterator<IntObjectMap$Entry<V>> iterator() {
      return new IntObjectHashMap$IteratorImpl(this, null);
   }

   public int indexOf(int var1) {
      int var2 = this.hashIndex(var1);
      int var3 = var2;

      while (this.values[var3] != null) {
         if (var1 == this.keys[var3]) {
            return var3;
         }

         if ((var3 = this.probeNext(var3)) == var2) {
            return -1;
         }
      }

      return -1;
   }

   @Override
   public boolean isEmpty() {
      return this.size == 0;
   }

   @Override
   public int[] keys() {
      int[] var1 = new int[this.size()];
      int var2 = 0;

      for (int var3 = 0; var3 < this.values.length; var3++) {
         if (this.values[var3] != null) {
            var1[var2++] = this.keys[var3];
         }
      }

      return var1;
   }

   @Override
   public boolean containsKey(int var1) {
      return this.indexOf(var1) >= 0;
   }

   public static <T> T toExternal(T var0) {
      return (T)(var0 == NULL_VALUE ? null : var0);
   }

   @Override
   public Iterable<IntObjectMap$Entry<V>> entries() {
      return this;
   }

   @Override
   public void putAll(IntObjectMap<V> var1) {
      if (var1 instanceof IntObjectHashMap) {
         IntObjectHashMap var5 = (IntObjectHashMap)var1;

         for (int var6 = 0; var6 < var5.values.length; var6++) {
            Object var4 = var5.values[var6];
            if (var4 != null) {
               this.put(var5.keys[var6], (V)var4);
            }
         }
      } else {
         for (IntObjectMap$Entry var3 : var1.entries()) {
            this.put(var3.key(), (V)var3.value());
         }
      }
   }

   @Override
   public V put(int var1, V var2) {
      int var3 = this.hashIndex(var1);
      int var4 = var3;

      while (this.values[var4] != null) {
         if (this.keys[var4] == var1) {
            Object var5 = this.values[var4];
            this.values[var4] = toInternal((V)var2);
            return toExternal((V)var5);
         }

         if ((var4 = this.probeNext(var4)) == var3) {
            throw new IllegalStateException("Unable to insert");
         }
      }

      this.keys[var4] = var1;
      this.values[var4] = toInternal((V)var2);
      this.growSize();
      return null;
   }

   public IntObjectHashMap(int var1, float var2) {
      if (var1 < 1) {
         throw new IllegalArgumentException("initialCapacity must be >= 1");
      } else if (!(var2 <= 0.0F) && !(var2 > 1.0F)) {
         this.loadFactor = var2;
         int var3 = adjustCapacity(var1);
         this.keys = new int[var3];
         Object[] var4 = new Object[var3];
         this.values = (V[])var4;
         this.maxSize = this.calcMaxSize(var3);
      } else {
         throw new IllegalArgumentException("loadFactor must be > 0 and <= 1");
      }
   }

   @Override
   public int size() {
      return this.size;
   }

   public IntObjectHashMap(int var1) {
      this(var1, 0.5F);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof IntObjectMap)) {
         return false;
      } else {
         IntObjectMap var2 = (IntObjectMap)var1;
         if (this.size != var2.size()) {
            return false;
         } else {
            for (int var3 = 0; var3 < this.values.length; var3++) {
               Object var4 = this.values[var3];
               if (var4 != null) {
                  int var5 = this.keys[var3];
                  Object var6 = var2.get(var5);
                  if (var4 == NULL_VALUE) {
                     if (var6 != null) {
                        return false;
                     }
                  } else if (!var4.equals(var6)) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   public void rehash(int var1) {
      int[] var2 = this.keys;
      Object[] var3 = this.values;
      this.keys = new int[var1];
      Object[] var4 = new Object[var1];
      this.values = (V[])var4;
      this.maxSize = this.calcMaxSize(var1);

      for (int var5 = 0; var5 < var3.length; var5++) {
         Object var6 = var3[var5];
         if (var6 != null) {
            int var7 = var2[var5];
            int var8 = this.hashIndex(var7);
            int var9 = var8;

            while (this.values[var9] != null) {
               var9 = this.probeNext(var9);
            }

            this.keys[var9] = var7;
            this.values[var9] = toInternal((V)var6);
         }
      }
   }

   public int probeNext(int var1) {
      return var1 == this.values.length - 1 ? 0 : var1 + 1;
   }

   public int calcMaxSize(int var1) {
      int var2 = var1 - 1;
      return Math.min(var2, (int)(var1 * this.loadFactor));
   }
}
