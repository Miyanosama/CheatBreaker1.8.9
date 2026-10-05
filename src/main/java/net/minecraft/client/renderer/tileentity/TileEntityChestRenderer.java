package net.minecraft.client.renderer.tileentity;

import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

public class TileEntityChestRenderer extends TileEntitySpecialRenderer<TileEntityChest> {
   public ModelChest largeChest;
   public static ResourceLocation textureTrappedDouble = new ResourceLocation("textures/entity/chest/trapped_double.png");
   public boolean isChristmas;
   public static ResourceLocation textureChristmasDouble = new ResourceLocation("textures/entity/chest/christmas_double.png");
   public static ResourceLocation textureNormalDouble = new ResourceLocation("textures/entity/chest/normal_double.png");
   public static ResourceLocation textureTrapped = new ResourceLocation("textures/entity/chest/trapped.png");
   public static ResourceLocation textureChristmas = new ResourceLocation("textures/entity/chest/christmas.png");
   public ModelChest simpleChest = new ModelChest();
   public static ResourceLocation textureNormal = new ResourceLocation("textures/entity/chest/normal.png");

   public void renderTileEntityAt(TileEntityChest var1, double var2, double var4, double var6, float var8, int var9) {
      GlStateManager.enableDepth();
      GlStateManager.depthFunc(515);
      GlStateManager.depthMask(true);
      int var10;
      if (!var1.t()) {
         var10 = 0;
      } else {
         Block var11 = var1.w();
         var10 = var1.u();
         if (var11 instanceof BlockChest && var10 == 0) {
            ((BlockChest)var11).checkForSurroundingChests(var1.getWorld(), var1.v(), var1.getWorld().getBlockState(var1.v()));
            var10 = var1.u();
         }

         var1.checkForAdjacentChests();
      }

      if (var1.adjacentChestZNeg == null && var1.adjacentChestXNeg == null) {
         ModelChest var15;
         if (var1.adjacentChestXPos == null && var1.adjacentChestZPos == null) {
            var15 = this.simpleChest;
            if (var9 >= 0) {
               this.bindTexture(a[var9]);
               GlStateManager.matrixMode(5890);
               GlStateManager.pushMatrix();
               GlStateManager.scale(4.0F, 4.0F, 1.0F);
               GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.matrixMode(5888);
            } else if (this.isChristmas) {
               this.bindTexture(textureChristmas);
            } else if (var1.getChestType() == 1) {
               this.bindTexture(textureTrapped);
            } else {
               this.bindTexture(textureNormal);
            }
         } else {
            var15 = this.largeChest;
            if (var9 >= 0) {
               this.bindTexture(a[var9]);
               GlStateManager.matrixMode(5890);
               GlStateManager.pushMatrix();
               GlStateManager.scale(8.0F, 4.0F, 1.0F);
               GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.matrixMode(5888);
            } else if (this.isChristmas) {
               this.bindTexture(textureChristmasDouble);
            } else if (var1.getChestType() == 1) {
               this.bindTexture(textureTrappedDouble);
            } else {
               this.bindTexture(textureNormalDouble);
            }
         }

         GlStateManager.pushMatrix();
         GlStateManager.enableRescaleNormal();
         if (var9 < 0) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         }

         GlStateManager.translate((float)var2, (float)var4 + 1.0F, (float)var6 + 1.0F);
         GlStateManager.scale(1.0F, -1.0F, -1.0F);
         GlStateManager.translate(0.5F, 0.5F, 0.5F);
         short var12 = 0;
         if (var10 == 2) {
            var12 = 180;
         }

         if (var10 == 3) {
            var12 = 0;
         }

         if (var10 == 4) {
            var12 = 90;
         }

         if (var10 == 5) {
            var12 = -90;
         }

         if (var10 == 2 && var1.adjacentChestXPos != null) {
            GlStateManager.translate(1.0F, 0.0F, 0.0F);
         }

         if (var10 == 5 && var1.adjacentChestZPos != null) {
            GlStateManager.translate(0.0F, 0.0F, -1.0F);
         }

         GlStateManager.rotate(var12, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(-0.5F, -0.5F, -0.5F);
         float var13 = var1.recoveredField3296 + (var1.recoveredField3297 - var1.recoveredField3296) * var8;
         if (var1.adjacentChestZNeg != null) {
            float var14 = var1.adjacentChestZNeg.recoveredField3296
               + (var1.adjacentChestZNeg.recoveredField3297 - var1.adjacentChestZNeg.recoveredField3296) * var8;
            if (var14 > var13) {
               var13 = var14;
            }
         }

         if (var1.adjacentChestXNeg != null) {
            float var18 = var1.adjacentChestXNeg.recoveredField3296
               + (var1.adjacentChestXNeg.recoveredField3297 - var1.adjacentChestXNeg.recoveredField3296) * var8;
            if (var18 > var13) {
               var13 = var18;
            }
         }

         var13 = 1.0F - var13;
         var13 = 1.0F - var13 * var13 * var13;
         var15.a.rotateAngleX = -(var13 * (float) Math.PI / 2.0F);
         var15.renderAll();
         GlStateManager.disableRescaleNormal();
         GlStateManager.popMatrix();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (var9 >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
         }
      }
   }

   public TileEntityChestRenderer() {
      this.largeChest = new ModelLargeChest();
      Calendar var1 = Calendar.getInstance();
      if (var1.get(2) + 1 == 12 && var1.get(5) >= 24 && var1.get(5) <= 26) {
         this.isChristmas = true;
      }
   }
}
