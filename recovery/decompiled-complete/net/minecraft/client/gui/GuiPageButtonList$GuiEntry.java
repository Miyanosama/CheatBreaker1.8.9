package net.minecraft.client.gui;

import com.cheatbreaker.client.ui.element.module.ModulesGuiButtonElement;
import net.minecraft.client.Minecraft;

public class GuiPageButtonList$GuiEntry implements GuiListExtended$IGuiListEntry {
   public Gui field_178030_c;
   public Gui field_178028_d;
   public ModulesGuiButtonElement field_0001;
   public Gui field_178029_b;
   public Minecraft field_178031_a = Minecraft.getMinecraft();

   public void func_178027_a(GuiTextField var1, int var2, boolean var3) {
      var1.yPosition = var2;
      if (!var3) {
         var1.drawTextBox();
      }
   }

   public void func_178017_a(Gui var1, int var2, int var3, int var4, boolean var5) {
      if (var1 != null) {
         if (var1 instanceof GuiButton) {
            this.func_178024_a((GuiButton)var1, var2, var3, var4, var5);
         } else if (var1 instanceof GuiTextField) {
            this.func_178027_a((GuiTextField)var1, var2, var5);
         } else if (var1 instanceof GuiLabel) {
            this.func_178025_a((GuiLabel)var1, var2, var3, var4, var5);
         }
      }
   }

   public void func_178025_a(GuiLabel var1, int var2, int var3, int var4, boolean var5) {
      var1.field_146174_h = var2;
      if (!var5) {
         var1.drawLabel(this.field_178031_a, var3, var4);
      }
   }

   @Override
   public void setSelected(int var1, int var2, int var3) {
      this.func_178017_a(this.field_178029_b, var3, 0, 0, true);
      this.func_178017_a(this.field_178030_c, var3, 0, 0, true);
   }

   public boolean func_178023_a(GuiButton var1, int var2, int var3, int var4) {
      boolean var5 = var1.mousePressed(this.field_178031_a, var2, var3);
      if (var5) {
         this.field_178028_d = var1;
      }

      return var5;
   }

   public void func_178024_a(GuiButton var1, int var2, int var3, int var4, boolean var5) {
      var1.i = var2;
      if (!var5) {
         var1.drawButton(this.field_178031_a, var3, var4);
      }
   }

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      boolean var7 = this.func_178026_a(this.field_178029_b, var2, var3, var4);
      boolean var8 = this.func_178026_a(this.field_178030_c, var2, var3, var4);
      return var7 || var8;
   }

   public Gui func_178021_b() {
      return this.field_178030_c;
   }

   public void func_178019_b(GuiButton var1, int var2, int var3, int var4) {
      var1.mouseReleased(var2, var3);
   }

   public void func_178018_a(GuiTextField var1, int var2, int var3, int var4) {
      var1.mouseClicked(var2, var3, var4);
      if (var1.isFocused()) {
         this.field_178028_d = var1;
      }
   }

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      this.func_178017_a(this.field_178029_b, var3, var6, var7, false);
      this.func_178017_a(this.field_178030_c, var3, var6, var7, false);
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.func_178016_b(this.field_178029_b, var2, var3, var4);
      this.func_178016_b(this.field_178030_c, var2, var3, var4);
   }

   public GuiPageButtonList$GuiEntry(Gui var1, Gui var2) {
      this.field_178029_b = var1;
      this.field_178030_c = var2;
   }

   public Gui func_178022_a() {
      return this.field_178029_b;
   }

   public void func_178016_b(Gui var1, int var2, int var3, int var4) {
      if (var1 != null && var1 instanceof GuiButton) {
         this.func_178019_b((GuiButton)var1, var2, var3, var4);
      }
   }

   public boolean func_178026_a(Gui var1, int var2, int var3, int var4) {
      if (var1 == null) {
         return false;
      } else if (var1 instanceof GuiButton) {
         return this.func_178023_a((GuiButton)var1, var2, var3, var4);
      } else {
         if (var1 instanceof GuiTextField) {
            this.func_178018_a((GuiTextField)var1, var2, var3, var4);
         }

         return false;
      }
   }
}
