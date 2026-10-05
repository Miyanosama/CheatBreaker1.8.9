package net.minecraft.client.gui;

import com.cheatbreaker.client.util.dash.DashPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs$12;
import net.minecraft.world.gen.GeneratorBushFeature;

public abstract class GuiListExtended extends GuiSlot {
   public GeneratorBushFeature field_0001;
   public DashPlayer field_0002;
   public CreativeTabs$12 field_0000;

   public GuiListExtended(Minecraft var1, int var2, int var3, int var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   public abstract GuiListExtended$IGuiListEntry getListEntry(int var1);

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
   }

   @Override
   public void func_178040_a(int var1, int var2, int var3) {
      this.getListEntry(var1).setSelected(var1, var2, var3);
   }

   public boolean c(int var1, int var2, int var3) {
      for (int var4 = 0; var4 < this.getSize(); var4++) {
         int var5 = this.left + this.b / 2 - this.v_() / 2 + 2;
         int var6 = this.d + 4 - this.getAmountScrolled() + var4 * this.slotHeight + this.headerPadding;
         int var7 = var1 - var5;
         int var8 = var2 - var6;
         this.getListEntry(var4).mouseReleased(var4, var1, var2, var3, var7, var8);
      }

      this.setEnabled(true);
      return false;
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.getListEntry(var1).drawEntry(var1, var2, var3, this.v_(), var4, var5, var6, this.c(var5, var6) == var1);
   }

   @Override
   public boolean isSelected(int var1) {
      return false;
   }

   @Override
   public void drawBackground() {
   }

   public boolean b(int var1, int var2, int var3) {
      if (this.isMouseYWithinSlotBounds(var2)) {
         int var4 = this.c(var1, var2);
         if (var4 >= 0) {
            int var5 = this.left + this.b / 2 - this.v_() / 2 + 2;
            int var6 = this.d + 4 - this.getAmountScrolled() + var4 * this.slotHeight + this.headerPadding;
            int var7 = var1 - var5;
            int var8 = var2 - var6;
            if (this.getListEntry(var4).mousePressed(var4, var1, var2, var3, var7, var8)) {
               this.setEnabled(false);
               return true;
            }
         }
      }

      return false;
   }
}
