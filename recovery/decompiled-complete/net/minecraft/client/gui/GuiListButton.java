package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityTracker;
import net.minecraft.world.Explosion;

public class GuiListButton extends GuiButton {
   public Explosion field_0002;
   public GuiPageButtonList$GuiResponder guiResponder;
   public boolean field_175216_o;
   public EntityTracker field_0003;
   public String localizationStr;

   @Override
   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      if (super.mousePressed(var1, var2, var3)) {
         this.field_175216_o = !this.field_175216_o;
         this.j = this.buildDisplayString();
         this.guiResponder.func_175321_a(this.k, this.field_175216_o);
         return true;
      } else {
         return false;
      }
   }

   public GuiListButton(GuiPageButtonList$GuiResponder var1, int var2, int var3, int var4, String var5, boolean var6) {
      super(var2, var3, var4, 150, 20, "");
      this.localizationStr = var5;
      this.field_175216_o = var6;
      this.j = this.buildDisplayString();
      this.guiResponder = var1;
   }

   public void func_175212_b(boolean var1) {
      this.field_175216_o = var1;
      this.j = this.buildDisplayString();
      this.guiResponder.func_175321_a(this.k, var1);
   }

   public String buildDisplayString() {
      return I18n.format(this.localizationStr) + ": " + (this.field_175216_o ? I18n.format("gui.yes") : I18n.format("gui.no"));
   }
}
