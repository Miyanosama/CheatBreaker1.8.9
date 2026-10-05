package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class GuiLockIconButton extends GuiButton {
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
         GuiLockIconButton.Icon var5;
         if (this.field_175231_o) {
            if (!this.l) {
               var5 = GuiLockIconButton.Icon.LOCKED_DISABLED;
            } else if (var4) {
               var5 = GuiLockIconButton.Icon.LOCKED_HOVER;
            } else {
               var5 = GuiLockIconButton.Icon.LOCKED;
            }
         } else if (!this.l) {
            var5 = GuiLockIconButton.Icon.UNLOCKED_DISABLED;
         } else if (var4) {
            var5 = GuiLockIconButton.Icon.UNLOCKED_HOVER;
         } else {
            var5 = GuiLockIconButton.Icon.UNLOCKED;
         }

         this.drawTexturedModalRect(this.h, this.i, var5.func_178910_a(), var5.func_178912_b(), this.f, this.height);
      }
   }

   public boolean func_175230_c() {
      return this.field_175231_o;
   }

   public static enum Icon {
      LOCKED(0, 146),
      LOCKED_HOVER(0, 166),
      LOCKED_DISABLED(0, 186),
      UNLOCKED(20, 146),
      UNLOCKED_HOVER(20, 166),
      UNLOCKED_DISABLED(20, 186);
      // $VF: synthetic field
      public static GuiLockIconButton.Icon[] $VALUES = new GuiLockIconButton.Icon[]{
         GuiLockIconButton.Icon.LOCKED,
         GuiLockIconButton.Icon.LOCKED_HOVER,
         GuiLockIconButton.Icon.LOCKED_DISABLED,
         GuiLockIconButton.Icon.UNLOCKED,
         GuiLockIconButton.Icon.UNLOCKED_HOVER,
         GuiLockIconButton.Icon.UNLOCKED_DISABLED
      };
      public int field_178920_h;
      public int field_178914_g;

      Icon(int var3, int var4) {
         this.field_178914_g = var3;
         this.field_178920_h = var4;
      }

      public int func_178912_b() {
         return this.field_178920_h;
      }

      public int func_178910_a() {
         return this.field_178914_g;
      }
   }
}
