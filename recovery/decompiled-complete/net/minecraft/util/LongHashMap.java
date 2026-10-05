package net.minecraft.util;

import net.minecraft.block.BlockCactus;
import net.minecraft.command.PlayerSelector$2;
import net.minecraft.entity.monster.EntitySlime$AISlimeHop;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class LongHashMap<V> {
   public S08PacketPlayerPosLook field_0004;
   public int mask;
   public int capacity;
   public EntitySlime$AISlimeHop field_0006;
   public transient LongHashMap$Entry<V>[] hashArray = new LongHashMap$Entry[4096];
   public BlockCactus field_0001;
   public PlayerSelector$2 field_0008;
   public float percentUseable;
   public transient volatile int modCount;
   public transient int numHashElements;

   public V getValueByKey(long var1) {
      int var3 = getHashedKey(var1);

      for (LongHashMap$Entry var4 = this.hashArray[getHashIndex(var3, this.mask)]; var4 != null; var4 = var4.nextEntry) {
         if (var4.key == var1) {
            return var4.value;
         }
      }

      return null;
   }

   public void copyHashTableTo(LongHashMap$Entry<V>[] var1) {
      LongHashMap$Entry[] var2 = this.hashArray;
      int var3 = var1.length;

      for (int var4 = 0; var4 < var2.length; var4++) {
         LongHashMap$Entry var5 = var2[var4];
         if (var5 != null) {
            var2[var4] = null;

            while (true) {
               LongHashMap$Entry var6 = var5.nextEntry;
               int var7 = getHashIndex(var5.hash, var3 - 1);
               var5.nextEntry = var1[var7];
               var1[var7] = var5;
               var5 = var6;
               if (var6 == null) {
                  break;
               }
            }
         }
      }
   }

   public V remove(long var1) {
      LongHashMap$Entry var3 = this.removeKey(var1);
      return var3 == null ? null : var3.value;
   }

   public static int getHashIndex(int var0, int var1) {
      return var0 & var1;
   }

   public static int hash(int var0) {
      var0 = var0 ^ var0 >>> 20 ^ var0 >>> 12;
      return var0 ^ var0 >>> 7 ^ var0 >>> 4;
   }

   public boolean containsItem(long var1) {
      return this.getEntry(var1) != null;
   }

   public double getKeyDistribution() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.hashArray.length; var2++) {
         if (this.hashArray[var2] != null) {
            var1++;
         }
      }

      return 1.0 * var1 / this.numHashElements;
   }

   public void resizeTable(int var1) {
      LongHashMap$Entry[] var2 = this.hashArray;
      int var3 = var2.length;
      if (var3 == 1073741824) {
         this.capacity = Integer.MAX_VALUE;
      } else {
         LongHashMap$Entry[] var4 = new LongHashMap$Entry[var1];
         this.copyHashTableTo(var4);
         this.hashArray = var4;
         this.mask = this.hashArray.length - 1;
         float var5 = var1;
         this.getClass();
         this.capacity = (int)(var5 * 0.75F);
      }
   }

   public LongHashMap() {
      this.capacity = 3072;
      this.percentUseable = 0.75F;
      this.mask = this.hashArray.length - 1;
   }

   public void createKey(int var1, long var2, V var4, int var5) {
      LongHashMap$Entry var6 = this.hashArray[var5];
      this.hashArray[var5] = new LongHashMap$Entry<>(var1, var2, (V)var4, var6);
      if (this.numHashElements++ >= this.capacity) {
         this.resizeTable(2 * this.hashArray.length);
      }
   }

   public static int getHashedKey(long var0) {
      return (int)(var0 ^ var0 >>> 27);
   }

   public LongHashMap$Entry<V> removeKey(long var1) {
      int var3 = getHashedKey(var1);
      int var4 = getHashIndex(var3, this.mask);
      LongHashMap$Entry var5 = this.hashArray[var4];
      LongHashMap$Entry var6 = var5;

      while (var6 != null) {
         LongHashMap$Entry var7 = var6.nextEntry;
         if (var6.key == var1) {
            this.modCount++;
            this.numHashElements--;
            if (var5 == var6) {
               this.hashArray[var4] = var7;
            } else {
               var5.nextEntry = var7;
            }

            return var6;
         }

         var5 = var6;
         var6 = var7;
      }

      return var6;
   }

   public int getNumHashElements() {
      return this.numHashElements;
   }

   public void add(long var1, V var3) {
      int var4 = getHashedKey(var1);
      int var5 = getHashIndex(var4, this.mask);

      for (LongHashMap$Entry var6 = this.hashArray[var5]; var6 != null; var6 = var6.nextEntry) {
         if (var6.key == var1) {
            var6.value = (V)var3;
            return;
         }
      }

      this.modCount++;
      this.createKey(var4, var1, (V)var3, var5);
   }

   public LongHashMap$Entry<V> getEntry(long var1) {
      int var3 = getHashedKey(var1);

      for (LongHashMap$Entry var4 = this.hashArray[getHashIndex(var3, this.mask)]; var4 != null; var4 = var4.nextEntry) {
         if (var4.key == var1) {
            return var4;
         }
      }

      return null;
   }
}
