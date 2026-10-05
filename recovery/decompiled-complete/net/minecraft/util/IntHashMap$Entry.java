package net.minecraft.util;

import recovered.unidentified.UnidentifiedClass4110;

public class IntHashMap$Entry<V> {
   public int slotHash;
   public IntHashMap$Entry<V> nextEntry;
   public V valueEntry;
   public UnidentifiedClass4110 field_0003;
   public int hashEntry;

   @Override
   public String toString() {
      return this.getHash() + "=" + this.getValue();
   }

   @Override
   public int hashCode() {
      return IntHashMap.access$000(this.hashEntry);
   }

   public V getValue() {
      return this.valueEntry;
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof IntHashMap$Entry)) {
         return false;
      } else {
         IntHashMap$Entry var2 = (IntHashMap$Entry)var1;
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

   public IntHashMap$Entry(int var1, int var2, V var3, IntHashMap$Entry<V> var4) {
      this.valueEntry = (V)var3;
      this.nextEntry = var4;
      this.hashEntry = var2;
      this.slotHash = var1;
   }
}
