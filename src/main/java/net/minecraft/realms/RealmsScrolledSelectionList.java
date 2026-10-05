package net.minecraft.realms;

import net.minecraft.client.gui.GuiSlotRealmsProxy;

public class RealmsScrolledSelectionList {
   public GuiSlotRealmsProxy recoveredField698;

   public int method_06327() {
      return this.recoveredField698.getAmountScrolled();
   }

   public int method_06336() {
      return this.recoveredField698.getMouseX();
   }

   public void selectItem(int var1, boolean var2, int var3, int var4) {
   }

   public int getScrollbarPosition() {
      return this.recoveredField698.method_29928() / 2 + 124;
   }

   public void renderBackground() {
   }

   public void scroll(int var1) {
      this.recoveredField698.scrollBy(var1);
   }

   public void renderList(int var1, int var2, int var3, int var4) {
   }

   public RealmsScrolledSelectionList(int var1, int var2, int var3, int var4, int var5) {
      this.recoveredField698 = new GuiSlotRealmsProxy(this, var1, var2, var3, var4, var5);
   }

   public int method_06323() {
      return 0;
   }

   public int method_06324() {
      return 0;
   }

   public boolean isSelectedItem(int var1) {
      return false;
   }

   public void mouseEvent() {
      this.recoveredField698.handleMouseInput();
   }

   public void render(int var1, int var2, float var3) {
      this.recoveredField698.a(var1, var2, var3);
   }

   public void renderItem(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.renderItem(var1, var2, var3, var4, Tezzelator.instance, var5, var6);
   }

   public void renderItem(int var1, int var2, int var3, int var4, Tezzelator var5, int var6, int var7) {
   }

   public int method_06334() {
      return this.recoveredField698.getMouseY();
   }

   public int method_06325() {
      return this.recoveredField698.method_29928();
   }
}
