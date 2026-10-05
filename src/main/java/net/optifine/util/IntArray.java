package net.optifine.util;

public class IntArray {
   public int[] array = null;
   public int limit;
   public int position = 0;

   public IntArray(int var1) {
      this.limit = 0;
      this.array = new int[var1];
   }

   public void put(int var1) {
      this.array[this.position] = var1;
      this.position++;
      if (this.limit < this.position) {
         this.limit = this.position;
      }
   }

   public int getLimit() {
      return this.limit;
   }

   public void put(int var1, int var2) {
      this.array[var1] = var2;
      if (this.limit < var1) {
         this.limit = var1;
      }
   }

   public void clear() {
      this.position = 0;
      this.limit = 0;
   }

   public void position(int var1) {
      this.position = var1;
   }

   public int[] getArray() {
      return this.array;
   }

   public int get(int var1) {
      return this.array[var1];
   }

   public void put(int[] var1) {
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         this.array[this.position] = var1[var3];
         this.position++;
      }

      if (this.limit < this.position) {
         this.limit = this.position;
      }
   }

   public int getPosition() {
      return this.position;
   }
}
