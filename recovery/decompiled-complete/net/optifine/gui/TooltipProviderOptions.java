package net.optifine.gui;

import java.awt.Rectangle;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.item.ItemMap;
import net.optifine.Lang;
import net.optifine.texture.InternalFormat;

public class TooltipProviderOptions implements TooltipProvider {
   public InternalFormat field_0000;
   public ItemMap field_0001;

   @Override
   public boolean isRenderBorder() {
      return false;
   }

   public static String[] getTooltipLines(String var0) {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var2 < 10; var2++) {
         String var3 = var0 + ".tooltip." + (var2 + 1);
         String var4 = Lang.get(var3, (String)null);
         if (var4 == null) {
            break;
         }

         var1.add(var4);
      }

      return var1.size() <= 0 ? null : var1.toArray(new String[var1.size()]);
   }

   @Override
   public Rectangle getTooltipBounds(GuiScreen var1, int var2, int var3) {
      int var4 = var1.l / 2 - 150;
      int var5 = var1.m / 6 - 7;
      if (var3 <= var5 + 98) {
         var5 += 105;
      }

      int var6 = var4 + 150 + 150;
      int var7 = var5 + 84 + 10;
      return new Rectangle(var4, var5, var6 - var4, var7 - var5);
   }

   @Override
   public String[] getTooltipLines(GuiButton var1, int var2) {
      if (!(var1 instanceof IOptionControl)) {
         return null;
      } else {
         IOptionControl var3 = (IOptionControl)var1;
         GameSettings$Options var4 = var3.getOption();
         return getTooltipLines(var4.getEnumString());
      }
   }
}
