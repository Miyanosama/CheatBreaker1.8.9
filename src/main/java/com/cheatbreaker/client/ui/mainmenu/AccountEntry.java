package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.util.ResourceLocation;

public class AccountEntry {
   public String recoveredField1686;
   public ResourceLocation recoveredField1687;
   public String recoveredField1688;
   public String recoveredField1689;
   public String recoveredField1690;
   public String recoveredField1691;

   public void method_09051(String var1) {
      this.recoveredField1689 = var1;
   }

   public String method_09050() {
      return this.recoveredField1689;
   }

   public String method_09053() {
      return this.recoveredField1686;
   }

   public String method_09049() {
      return this.recoveredField1688;
   }

   public AccountEntry(String var1, String var2, String var3, String var4, String var5, ResourceLocation var6) {
      this.recoveredField1686 = var1;
      this.recoveredField1690 = var2;
      this.recoveredField1689 = var3;
      this.recoveredField1691 = var4;
      this.recoveredField1688 = var5;
      this.recoveredField1687 = var6;
   }

   public ResourceLocation method_09052() {
      return this.recoveredField1687;
   }

   public AccountEntry(String var1, String var2, String var3, String var4, String var5) {
      this.recoveredField1690 = var1;
      this.recoveredField1686 = var2;
      this.recoveredField1689 = var3;
      this.recoveredField1691 = var4;
      this.recoveredField1688 = var5;
      this.recoveredField1687 = CheatBreaker.getInstance().method_19810(var4);
   }

   public String method_09047() {
      return this.recoveredField1691;
   }

   public String method_09048() {
      return this.recoveredField1690;
   }
}
