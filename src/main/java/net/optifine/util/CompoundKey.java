package net.optifine.util;

import net.minecraft.src.Config;

public class CompoundKey {
   public int hashcode = 0;
   public Object[] keys;

   public CompoundKey(Object[] var1) {
      this.keys = (Object[])var1.clone();
   }

   public CompoundKey(Object var1, Object var2) {
      this(new Object[]{var1, var2});
   }

   @Override
   public int hashCode() {
      if (this.hashcode == 0) {
         this.hashcode = 7;

         for (int var1 = 0; var1 < this.keys.length; var1++) {
            Object var2 = this.keys[var1];
            if (var2 != null) {
               this.hashcode = 31 * this.hashcode + var2.hashCode();
            }
         }
      }

      return this.hashcode;
   }

   @Override
   public String toString() {
      return "[" + Config.arrayToString(this.keys) + "]";
   }

   public Object[] getKeysCopy() {
      return (Object[])this.keys.clone();
   }

   public static boolean compareKeys(Object var0, Object var1) {
      return var0 == var1 ? true : (var0 == null ? false : (var1 == null ? false : var0.equals(var1)));
   }

   public CompoundKey(Object var1, Object var2, Object var3) {
      this(new Object[]{var1, var2, var3});
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else if (var1 == this) {
         return true;
      } else if (!(var1 instanceof CompoundKey)) {
         return false;
      } else {
         CompoundKey var2 = (CompoundKey)var1;
         Object[] var3 = var2.getKeys();
         if (var3.length != this.keys.length) {
            return false;
         } else {
            for (int var4 = 0; var4 < this.keys.length; var4++) {
               if (!compareKeys(this.keys[var4], var3[var4])) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public Object[] getKeys() {
      return this.keys;
   }
}
