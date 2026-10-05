package com.cheatbreaker.client.module.type.armourstatus;

import java.util.List;

public class ArmourStatusDamageComparable implements Comparable<ArmourStatusDamageComparable> {
   public String colorCode;
   public int percent;

   public int compare(ArmourStatusDamageComparable var1) {
      return Integer.compare(this.percent, var1.percent);
   }

   public ArmourStatusDamageComparable(int var1, String var2) {
      this.percent = var1;
      this.colorCode = var2;
   }

   public static String getDamageColor(List<ArmourStatusDamageComparable> var0, int var1) {
      for (ArmourStatusDamageComparable var3 : var0) {
         if (var1 <= var3.percent) {
            return var3.colorCode;
         }
      }

      return "f";
   }

   public int compareTo(ArmourStatusDamageComparable var1) {
      return this.compare(var1);
   }
}
