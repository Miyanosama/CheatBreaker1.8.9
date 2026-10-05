package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBrewingStand extends GuiContainer {
   public IInventory recoveredField1841;
   public InventoryPlayer recoveredField1842;
   public static ResourceLocation recoveredField1843 = new ResourceLocation("textures/gui/container/brewing_stand.png");

   public GuiBrewingStand(InventoryPlayer var1, IInventory var2) {
      super(new ContainerBrewingStand(var1, var2));
      this.recoveredField1842 = var1;
      this.recoveredField1841 = var2;
   }

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      String var3 = this.recoveredField1841.getDisplayName().getUnformattedText();
      this.q.drawString(var3, this.f / 2 - this.q.getStringWidth(var3) / 2, 6, 4210752);
      this.q.drawString(this.recoveredField1842.getDisplayName().getUnformattedText(), 8, this.g - 96 + 2, 4210752);
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(recoveredField1843);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
      int var6 = this.recoveredField1841.getField(0);
      if (var6 > 0) {
         int var7 = (int)(28.0F * (1.0F - var6 / 400.0F));
         if (var7 > 0) {
            this.drawTexturedModalRect(var4 + 97, var5 + 16, 176, 0, 9, var7);
         }

         int var8 = var6 / 2 % 7;
         switch (var8) {
            case 0:
               var7 = 29;
               break;
            case 1:
               var7 = 24;
               break;
            case 2:
               var7 = 20;
               break;
            case 3:
               var7 = 16;
               break;
            case 4:
               var7 = 11;
               break;
            case 5:
               var7 = 6;
               break;
            case 6:
               var7 = 0;
         }

         if (var7 > 0) {
            this.drawTexturedModalRect(var4 + 65, var5 + 14 + 29 - var7, 185, 29 - var7, 12, var7);
         }
      }
   }
}
