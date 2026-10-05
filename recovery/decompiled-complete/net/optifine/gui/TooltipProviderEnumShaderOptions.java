package net.optifine.gui;

import java.awt.Rectangle;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.realms.Realms;
import net.minecraft.tileentity.TileEntityCommandBlock$1;
import net.optifine.shaders.config.EnumShaderOption;
import net.optifine.shaders.gui.GuiButtonDownloadShaders;
import net.optifine.shaders.gui.GuiButtonEnumShaderOption;
import recovered.unidentified.UnidentifiedClass0682;

public class TooltipProviderEnumShaderOptions implements TooltipProvider {
   public TileEntityCommandBlock$1 field_0001;
   public Realms field_0002;
   public UnidentifiedClass0682 field_0000;

   public String[] getTooltipLines(EnumShaderOption var1) {
      return TooltipProviderOptions.getTooltipLines(var1.getResourceKey());
   }

   @Override
   public String[] getTooltipLines(GuiButton var1, int var2) {
      if (var1 instanceof GuiButtonDownloadShaders) {
         return TooltipProviderOptions.getTooltipLines("of.options.shaders.DOWNLOAD");
      } else if (!(var1 instanceof GuiButtonEnumShaderOption)) {
         return null;
      } else {
         GuiButtonEnumShaderOption var3 = (GuiButtonEnumShaderOption)var1;
         EnumShaderOption var4 = var3.getEnumShaderOption();
         return this.getTooltipLines(var4);
      }
   }

   @Override
   public boolean isRenderBorder() {
      return true;
   }

   @Override
   public Rectangle getTooltipBounds(GuiScreen var1, int var2, int var3) {
      int var4 = var1.l - 450;
      byte var5 = 35;
      if (var4 < 10) {
         var4 = 10;
      }

      if (var3 <= var5 + 94) {
         var5 += 100;
      }

      int var6 = var4 + 150 + 150;
      int var7 = var5 + 84 + 10;
      return new Rectangle(var4, var5, var6 - var4, var7 - var5);
   }
}
