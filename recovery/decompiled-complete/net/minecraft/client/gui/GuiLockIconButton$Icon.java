package net.minecraft.client.gui;

import javazoom.jl.converter.jlc;
import net.minecraft.client.renderer.GlStateManager$StencilState;
import net.minecraft.creativetab.CreativeTabs$9;

public enum GuiLockIconButton$Icon {
   LOCKED_DISABLED(0, 186),
   UNLOCKED_DISABLED(20, 186),
   UNLOCKED_HOVER(20, 166),
   LOCKED_HOVER(0, 166),
   LOCKED(0, 146),
   UNLOCKED(20, 146);
   public jlc field_0005;
   // $VF: synthetic field
   public static GuiLockIconButton$Icon[] $VALUES = new GuiLockIconButton$Icon[]{
      GuiLockIconButton$Icon.LOCKED,
      GuiLockIconButton$Icon.LOCKED_HOVER,
      GuiLockIconButton$Icon.LOCKED_DISABLED,
      GuiLockIconButton$Icon.UNLOCKED,
      GuiLockIconButton$Icon.UNLOCKED_HOVER,
      GuiLockIconButton$Icon.UNLOCKED_DISABLED
   };
   public int field_178920_h;
   public int field_178914_g;
   public GlStateManager$StencilState field_0007;
   public CreativeTabs$9 field_0011;

   public GuiLockIconButton$Icon(int var3, int var4) {
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
