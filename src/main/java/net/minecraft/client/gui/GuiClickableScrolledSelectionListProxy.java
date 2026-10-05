package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.realms.RealmsClickableScrolledSelectionList;
import net.minecraft.realms.Tezzelator;
import org.lwjgl.input.Mouse;

public class GuiClickableScrolledSelectionListProxy extends GuiSlot {
   public RealmsClickableScrolledSelectionList field_178046_u;

   @Override
   public int getSize() {
      return this.field_178046_u.method_26272();
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_178046_u.renderItem(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.field_178046_u.selectItem(var1, var2, var3, var4);
   }

   public GuiClickableScrolledSelectionListProxy(RealmsClickableScrolledSelectionList var1, int var2, int var3, int var4, int var5, int var6) {
      super(Minecraft.getMinecraft(), var2, var3, var4, var5, var6);
      this.field_178046_u = var1;
   }

   @Override
   public void drawSelectionBox(int var1, int var2, int var3, int var4) {
      int var5 = this.getSize();

      for (int var6 = 0; var6 < var5; var6++) {
         int var7 = var2 + var6 * this.slotHeight + this.headerPadding;
         int var8 = this.slotHeight - 4;
         if (var7 > this.bottom || var7 + var8 < this.d) {
            this.func_178040_a(var6, var1, var7);
         }

         if (this.showSelectionBox && this.isSelected(var6)) {
            this.func_178043_a(this.b, var7, var8, Tezzelator.instance);
         }

         this.drawSlot(var6, var1, var7, var8, var3, var4);
      }
   }

   @Override
   public int getScrollBarX() {
      return this.field_178046_u.getScrollbarPosition();
   }

   public void func_178043_a(int var1, int var2, int var3, Tezzelator var4) {
      this.field_178046_u.renderSelected(var1, var2, var3, var4);
   }

   @Override
   public void drawBackground() {
      this.field_178046_u.renderBackground();
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      if (this.scrollMultiplier > 0.0F && Mouse.getEventButtonState()) {
         this.field_178046_u.customMouseEvent(this.d, this.bottom, this.headerPadding, this.amountScrolled, this.slotHeight);
      }
   }

   public int func_178044_e() {
      return super.b;
   }

   public int func_178045_g() {
      return super.mouseX;
   }

   @Override
   public boolean isSelected(int var1) {
      return this.field_178046_u.isSelectedItem(var1);
   }

   public int func_178042_f() {
      return super.mouseY;
   }

   @Override
   public int getContentHeight() {
      return this.field_178046_u.method_26257();
   }
}
