package net.minecraft.realms;

import net.minecraft.client.gui.GuiSimpleScrolledSelectionListProxy;

public class RealmsSimpleScrolledSelectionList {
   public GuiSimpleScrolledSelectionListProxy recoveredField1816;

   public int method_01316() {
      return this.recoveredField1816.method_22572();
   }

   public int method_01325() {
      return this.recoveredField1816.method_22573();
   }

   public void selectItem(int var1, boolean var2, int var3, int var4) {
   }

   public int getScrollbarPosition() {
      return this.recoveredField1816.method_22572() / 2 + 124;
   }

   public int method_01324() {
      return this.recoveredField1816.method_22576();
   }

   public int method_01312() {
      return this.recoveredField1816.getAmountScrolled();
   }

   public boolean isSelectedItem(int var1) {
      return false;
   }

   public void renderBackground() {
   }

   public void renderItem(int var1, int var2, int var3, int var4, Tezzelator var5, int var6, int var7) {
   }

   public RealmsSimpleScrolledSelectionList(int var1, int var2, int var3, int var4, int var5) {
      this.recoveredField1816 = new GuiSimpleScrolledSelectionListProxy(this, var1, var2, var3, var4, var5);
   }

   public void renderList(int var1, int var2, int var3, int var4) {
   }

   public int method_01327() {
      return 0;
   }

   public void renderItem(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.renderItem(var1, var2, var3, var4, Tezzelator.instance, var5, var6);
   }

   public void mouseEvent() {
      this.recoveredField1816.handleMouseInput();
   }

   public void render(int var1, int var2, float var3) {
      this.recoveredField1816.a(var1, var2, var3);
   }

   public void scroll(int var1) {
      this.recoveredField1816.scrollBy(var1);
   }

   public int method_01314() {
      return 0;
   }
}
