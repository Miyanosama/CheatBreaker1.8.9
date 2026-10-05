package net.optifine.gui;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.statemap.StateMap$Builder;

public class TooltipManager {
   public long mouseStillTime;
   public TooltipProvider tooltipProvider;
   public StateMap$Builder field_0002;
   public GuiScreen guiScreen;
   public int field_0000;
   public int field_0001 = 0;

   public TooltipManager(GuiScreen var1, TooltipProvider var2) {
      this.field_0000 = 0;
      this.mouseStillTime = 50429984L & 2293620059512903250L;
      this.guiScreen = var1;
      this.tooltipProvider = var2;
   }

   public void drawTooltips(int var1, int var2, List var3) {
      if (Math.abs(var1 - this.field_0001) <= 5 && Math.abs(var2 - this.field_0000) <= 5) {
         short var4 = 700;
         if (System.currentTimeMillis() >= this.mouseStillTime + var4) {
            GuiButton var5 = GuiScreenOF.getSelectedButton(var1, var2, var3);
            if (var5 != null) {
               Rectangle var6 = this.tooltipProvider.getTooltipBounds(this.guiScreen, var1, var2);
               String[] var7 = this.tooltipProvider.getTooltipLines(var5, var6.width);
               if (var7 != null) {
                  if (var7.length > 8) {
                     var7 = Arrays.copyOf(var7, 8);
                     var7[var7.length - 1] = var7[var7.length - 1] + " ...";
                  }

                  if (this.tooltipProvider.isRenderBorder()) {
                     int var8 = -528449408;
                     this.drawRectBorder(var6.x, var6.y, var6.x + var6.width, var6.y + var6.height, var8);
                  }

                  Gui.a(var6.x, var6.y, var6.x + var6.width, var6.y + var6.height, -536870912);

                  for (int var12 = 0; var12 < var7.length; var12++) {
                     String var9 = var7[var12];
                     int var10 = 14540253;
                     if (var9.endsWith("!")) {
                        var10 = 16719904;
                     }

                     FontRenderer var11 = Minecraft.getMinecraft().fontRendererObj;
                     var11.drawStringWithShadow(var9, var6.x + 5, var6.y + 5 + var12 * 11, var10);
                  }
               }
            }
         }
      } else {
         this.field_0001 = var1;
         this.field_0000 = var2;
         this.mouseStillTime = System.currentTimeMillis();
      }
   }

   public void drawRectBorder(int var1, int var2, int var3, int var4, int var5) {
      Gui.a(var1, var2 - 1, var3, var2, var5);
      Gui.a(var1, var4, var3, var4 + 1, var5);
      Gui.a(var1 - 1, var2, var1, var4, var5);
      Gui.a(var3, var2, var3 + 1, var4, var5);
   }
}
