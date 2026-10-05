package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.realms.RealmsScrolledSelectionList;

public class GuiSlotRealmsProxy extends GuiSlot {
   public RealmsScrolledSelectionList recoveredField435;

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.recoveredField435.renderItem(var1, var2, var3, var4, var5, var6);
   }

   public int getMouseY() {
      return super.mouseY;
   }

   @Override
   public int getScrollBarX() {
      return this.recoveredField435.getScrollbarPosition();
   }

   @Override
   public int getContentHeight() {
      return this.recoveredField435.method_06324();
   }

   @Override
   public boolean isSelected(int var1) {
      return this.recoveredField435.isSelectedItem(var1);
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.recoveredField435.selectItem(var1, var2, var3, var4);
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
   }

   @Override
   public void drawBackground() {
      this.recoveredField435.renderBackground();
   }

   public int method_29928() {
      return super.b;
   }

   public int getMouseX() {
      return super.mouseX;
   }

   @Override
   public int getSize() {
      return this.recoveredField435.method_06323();
   }

   public GuiSlotRealmsProxy(RealmsScrolledSelectionList var1, int var2, int var3, int var4, int var5, int var6) {
      super(Minecraft.getMinecraft(), var2, var3, var4, var5, var6);
      this.recoveredField435 = var1;
   }
}
