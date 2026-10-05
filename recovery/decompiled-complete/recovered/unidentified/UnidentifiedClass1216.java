package recovered.unidentified;

import net.minecraft.block.BlockSilverfish$EnumType;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeCache$Block;

public class UnidentifiedClass1216 extends GuiContainer {
   public IInventory field_0002;
   public InventoryPlayer field_0004;
   public BlockSilverfish$EnumType field_0001;
   public static ResourceLocation field_0003 = new ResourceLocation("textures/gui/container/brewing_stand.png");
   public BiomeCache$Block field_0000;

   public UnidentifiedClass1216(InventoryPlayer var1, IInventory var2) {
      super(new ContainerBrewingStand(var1, var2));
      this.field_0004 = var1;
      this.field_0002 = var2;
   }

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      String var3 = this.field_0002.getDisplayName().getUnformattedText();
      this.q.drawString(var3, this.f / 2 - this.q.getStringWidth(var3) / 2, 6, 4210752);
      this.q.drawString(this.field_0004.getDisplayName().getUnformattedText(), 8, this.g - 96 + 2, 4210752);
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(field_0003);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
      int var6 = this.field_0002.getField(0);
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
