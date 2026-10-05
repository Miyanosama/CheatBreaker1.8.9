package net.minecraft.client.gui;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class GuiLockIconButton extends GuiButton {
   public AbstractElement field_0000;
   public boolean field_175231_o = false;

   public void func_175229_b(boolean var1) {
      this.field_175231_o = var1;
   }

   public GuiLockIconButton(int var1, int var2, int var3) {
      super(var1, var2, var3, 20, 20, "");
   }

   @Override
   public void drawButton(Minecraft var1, int var2, int var3) {
      if (this.m) {
         var1.getTextureManager().bindTexture(GuiButton.a);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         boolean var4 = var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
         GuiLockIconButton$Icon var5;
         if (this.field_175231_o) {
            if (!this.l) {
               var5 = GuiLockIconButton$Icon.LOCKED_DISABLED;
            } else if (var4) {
               var5 = GuiLockIconButton$Icon.LOCKED_HOVER;
            } else {
               var5 = GuiLockIconButton$Icon.LOCKED;
            }
         } else if (!this.l) {
            var5 = GuiLockIconButton$Icon.UNLOCKED_DISABLED;
         } else if (var4) {
            var5 = GuiLockIconButton$Icon.UNLOCKED_HOVER;
         } else {
            var5 = GuiLockIconButton$Icon.UNLOCKED;
         }

         this.drawTexturedModalRect(this.h, this.i, var5.func_178910_a(), var5.func_178912_b(), this.f, this.height);
      }
   }

   public boolean func_175230_c() {
      return this.field_175231_o;
   }
}
