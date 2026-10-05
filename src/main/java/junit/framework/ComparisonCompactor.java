package junit.framework;

import junit.framework.Assert;

public class ComparisonCompactor {
   public String recoveredField1802;
   public static final String recoveredField1803 = "[";
   public static final String recoveredField1804 = "]";
   public String recoveredField1805;
   public int recoveredField1806;
   public int recoveredField1807;
   public int recoveredField1808;
   public static final String recoveredField1809 = "...";

   public String compactString(String var1) {
      String var2 = "[" + var1.substring(this.recoveredField1808, var1.length() - this.recoveredField1807 + 1) + "]";
      if (this.recoveredField1808 > 0) {
         var2 = this.computeCommonPrefix() + var2;
      }

      if (this.recoveredField1807 > 0) {
         var2 = var2 + this.computeCommonSuffix();
      }

      return var2;
   }

   public String computeCommonPrefix() {
      return (this.recoveredField1808 > this.recoveredField1806 ? "..." : "")
         + this.recoveredField1802.substring(Math.max(0, this.recoveredField1808 - this.recoveredField1806), this.recoveredField1808);
   }

   public void findCommonPrefix() {
      this.recoveredField1808 = 0;
      int var1 = Math.min(this.recoveredField1802.length(), this.recoveredField1805.length());

      while (
         this.recoveredField1808 < var1 && this.recoveredField1802.charAt(this.recoveredField1808) == this.recoveredField1805.charAt(this.recoveredField1808)
      ) {
         this.recoveredField1808++;
      }
   }

   public boolean areStringsEqual() {
      return this.recoveredField1802.equals(this.recoveredField1805);
   }

   public String computeCommonSuffix() {
      int var1 = Math.min(this.recoveredField1802.length() - this.recoveredField1807 + 1 + this.recoveredField1806, this.recoveredField1802.length());
      return this.recoveredField1802.substring(this.recoveredField1802.length() - this.recoveredField1807 + 1, var1)
         + (this.recoveredField1802.length() - this.recoveredField1807 + 1 < this.recoveredField1802.length() - this.recoveredField1806 ? "..." : "");
   }

   public void findCommonSuffix() {
      int var1 = this.recoveredField1802.length() - 1;

      for (int var2 = this.recoveredField1805.length() - 1;
         var2 >= this.recoveredField1808 && var1 >= this.recoveredField1808 && this.recoveredField1802.charAt(var1) == this.recoveredField1805.charAt(var2);
         var1--
      ) {
         var2--;
      }

      this.recoveredField1807 = this.recoveredField1802.length() - var1;
   }

   public String compact(String var1) {
      if (this.recoveredField1802 != null && this.recoveredField1805 != null && !this.areStringsEqual()) {
         this.findCommonPrefix();
         this.findCommonSuffix();
         String var2 = this.compactString(this.recoveredField1802);
         String var3 = this.compactString(this.recoveredField1805);
         return Assert.format(var1, var2, var3);
      } else {
         return Assert.format(var1, this.recoveredField1802, this.recoveredField1805);
      }
   }

   public ComparisonCompactor(int var1, String var2, String var3) {
      this.recoveredField1806 = var1;
      this.recoveredField1802 = var2;
      this.recoveredField1805 = var3;
   }
}
