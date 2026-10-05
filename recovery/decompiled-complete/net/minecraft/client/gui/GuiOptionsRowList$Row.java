package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings$Options;
import net.optifine.config.ConnectedParser$2;
import recovered.unidentified.UnidentifiedClass4377;

public class GuiOptionsRowList$Row implements GuiListExtended$IGuiListEntry {
   public GuiButton field_148323_b;
   public UnidentifiedClass4377 field_0004;
   public GuiButton field_148324_c;
   public ConnectedParser$2 field_0003;
   public Minecraft field_148325_a = Minecraft.getMinecraft();

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (this.field_148323_b.mousePressed(this.field_148325_a, var2, var3)) {
         if (this.field_148323_b instanceof GuiOptionButton) {
            this.field_148325_a.gameSettings.setOptionValue(((GuiOptionButton)this.field_148323_b).returnEnumOptions(), 1);
            this.field_148323_b.j = this.field_148325_a.gameSettings.getKeyBinding(GameSettings$Options.getEnumOptions(this.field_148323_b.k));
         }

         return true;
      } else if (this.field_148324_c != null && this.field_148324_c.mousePressed(this.field_148325_a, var2, var3)) {
         if (this.field_148324_c instanceof GuiOptionButton) {
            this.field_148325_a.gameSettings.setOptionValue(((GuiOptionButton)this.field_148324_c).returnEnumOptions(), 1);
            this.field_148324_c.j = this.field_148325_a.gameSettings.getKeyBinding(GameSettings$Options.getEnumOptions(this.field_148324_c.k));
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (this.field_148323_b != null) {
         this.field_148323_b.mouseReleased(var2, var3);
      }

      if (this.field_148324_c != null) {
         this.field_148324_c.mouseReleased(var2, var3);
      }
   }

   @Override
   public void setSelected(int var1, int var2, int var3) {
   }

   public GuiOptionsRowList$Row(GuiButton var1, GuiButton var2) {
      this.field_148323_b = var1;
      this.field_148324_c = var2;
   }

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      if (this.field_148323_b != null) {
         this.field_148323_b.i = var3;
         this.field_148323_b.drawButton(this.field_148325_a, var6, var7);
      }

      if (this.field_148324_c != null) {
         this.field_148324_c.i = var3;
         this.field_148324_c.drawButton(this.field_148325_a, var6, var7);
      }
   }
}
