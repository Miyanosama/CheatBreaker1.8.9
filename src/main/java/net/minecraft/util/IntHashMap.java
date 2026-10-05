package net.minecraft.util;

public class IntHashMap<V> {
   public transient int count;
   public int threshold;
   public transient IntHashMap.Entry<V>[] slots = new IntHashMap.Entry[16];
   public float recoveredField2492;

   public void grow(int var1) {
      IntHashMap.Entry[] var2 = this.slots;
      int var3 = var2.length;
      if (var3 == 1073741824) {
         this.threshold = Integer.MAX_VALUE;
      } else {
         IntHashMap.Entry[] var4 = new IntHashMap.Entry[var1];
         this.copyTo(var4);
         this.slots = var4;
         this.threshold = (int)(var1 * 0.75F);
      }
   }

   public IntHashMap.Entry<V> removeEntry(int var1) {
      int var2 = computeHash(var1);
      int var3 = getSlotIndex(var2, this.slots.length);
      IntHashMap.Entry var4 = this.slots[var3];
      IntHashMap.Entry var5 = var4;

      while (var5 != null) {
         IntHashMap.Entry var6 = var5.nextEntry;
         if (var5.hashEntry == var1) {
            this.count--;
            if (var4 == var5) {
               this.slots[var3] = var6;
            } else {
               var4.nextEntry = var6;
            }

            return var5;
         }

         var4 = var5;
         var5 = var6;
      }

      return var5;
   }

   public static int computeHash(int var0) {
      var0 = var0 ^ var0 >>> 20 ^ var0 >>> 12;
      return var0 ^ var0 >>> 7 ^ var0 >>> 4;
   }

   public IntHashMap.Entry<V> lookupEntry(int var1) {
      int var2 = computeHash(var1);

      for (IntHashMap.Entry var3 = this.slots[getSlotIndex(var2, this.slots.length)]; var3 != null; var3 = var3.nextEntry) {
         if (var3.hashEntry == var1) {
            return var3;
         }
      }

      return null;
   }

   public V removeObject(int var1) {
      IntHashMap.Entry var2 = this.removeEntry(var1);
      return var2 == null ? null : (V)var2.valueEntry;
   }

   public static int getSlotIndex(int var0, int var1) {
      return var0 & var1 - 1;
   }

   public boolean containsItem(int var1) {
      return this.lookupEntry(var1) != null;
   }

   public void addKey(int var1, V var2) {
      int var3 = computeHash(var1);
      int var4 = getSlotIndex(var3, this.slots.length);

      for (IntHashMap.Entry var5 = this.slots[var4]; var5 != null; var5 = var5.nextEntry) {
         if (var5.hashEntry == var1) {
            var5.valueEntry = (V)var2;
            return;
         }
      }

      this.insert(var3, var1, (V)var2, var4);
   }

   public void insert(int var1, int var2, V var3, int var4) {
      IntHashMap.Entry var5 = this.slots[var4];
      this.slots[var4] = new IntHashMap.Entry<>(var1, var2, (V)var3, var5);
      if (this.count++ >= this.threshold) {
         this.grow(2 * this.slots.length);
      }
   }

   public V lookup(int var1) {
      int var2 = computeHash(var1);

      for (IntHashMap.Entry var3 = this.slots[getSlotIndex(var2, this.slots.length)]; var3 != null; var3 = var3.nextEntry) {
         if (var3.hashEntry == var1) {
            return (V)var3.valueEntry;
         }
      }

      return null;
   }

   public void clearMap() {
      IntHashMap.Entry[] var1 = this.slots;

      for (int var2 = 0; var2 < var1.length; var2++) {
         var1[var2] = null;
      }

      this.count = 0;
   }

   public IntHashMap() {
      this.threshold = 12;
      this.recoveredField2492 = 0.75F;
   }

   public void copyTo(IntHashMap.Entry<V>[] var1) {
      IntHashMap.Entry[] var2 = this.slots;
      int var3 = var1.length;

      for (int var4 = 0; var4 < var2.length; var4++) {
         IntHashMap.Entry var5 = var2[var4];
         if (var5 != null) {
            var2[var4] = null;

            while (true) {
               IntHashMap.Entry var6 = var5.nextEntry;
               int var7 = getSlotIndex(var5.slotHash, var3);
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

   public static class Entry<V> {
      public int slotHash;
      public IntHashMap.Entry<V> nextEntry;
      public V valueEntry;
      public int hashEntry;

      @Override
      public String toString() {
         return this.getHash() + "=" + this.getValue();
      }

      @Override
      public int hashCode() {
         return IntHashMap.computeHash(this.hashEntry);
      }

      public V getValue() {
         return this.valueEntry;
      }

      @Override
      public boolean equals(Object var1) {
         if (!(var1 instanceof IntHashMap.Entry)) {
            return false;
         } else {
            IntHashMap.Entry var2 = (IntHashMap.Entry)var1;
            Integer var3 = this.getHash();
            Integer var4 = var2.getHash();
            if (var3 == var4 || var3 != null && var3.equals(var4)) {
               Object var5 = this.getValue();
               Object var6 = var2.getValue();
               if (var5 == var6 || var5 != null && var5.equals(var6)) {
                  return true;
               }
            }

            return false;
         }
      }

      public int getHash() {
         return this.hashEntry;
      }

      public Entry(int var1, int var2, V var3, IntHashMap.Entry<V> var4) {
         this.valueEntry = (V)var3;
         this.nextEntry = var4;
         this.hashEntry = var2;
         this.slotHash = var1;
      }
   }
}
