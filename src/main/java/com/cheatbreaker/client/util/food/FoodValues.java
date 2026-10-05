package com.cheatbreaker.client.util.food;

public class FoodValues {
   public int recoveredField48;
   public float recoveredField49;

   @Override
   public int hashCode() {
      int var1 = this.recoveredField48;
      return 31 * var1 + (this.recoveredField49 != 0.0F ? Float.floatToIntBits(this.recoveredField49) : 0);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof FoodValues)) {
         return false;
      } else {
         FoodValues var2 = (FoodValues)var1;
         return this.recoveredField48 == var2.recoveredField48 && Float.compare(var2.recoveredField49, this.recoveredField49) == 0;
      }
   }

   public float method_00007() {
      return this.recoveredField48 * this.recoveredField49 * 2.0F;
   }

   public FoodValues(int var1, float var2) {
      this.recoveredField48 = var1;
      this.recoveredField49 = var2;
   }
}
