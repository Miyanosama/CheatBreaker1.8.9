package com.cheatbreaker.client.ui.util;

import io.netty.handler.ssl.OpenSslSessionStats;
import net.minecraft.block.BlockStoneBrick$EnumType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkSystem$6;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.WorldInfo$8;
import net.optifine.CustomColors;
import org.lwjgl.opengl.GL11;

public class HudUtil {
   public WorldInfo$8 field_0003;
   public BlockStoneBrick$EnumType field_0005;
   public ModelSkeleton field_0002;
   public NetworkSystem$6 field_0004;
   public static int[] colorCodes = new int[]{
      0,
      170,
      43520,
      43690,
      11141120,
      11141290,
      16755200,
      11184810,
      5592405,
      5592575,
      5635925,
      5636095,
      16733525,
      16733695,
      16777045,
      16777215,
      0,
      42,
      10752,
      10794,
      2752512,
      2752554,
      2763264,
      2763306,
      1381653,
      1381695,
      1392405,
      1392447,
      4134165,
      4134207,
      4144917,
      4144959
   };
   public OpenSslSessionStats field_0001;

   public static void renderItemOverlayIntoGUI(FontRenderer var0, ItemStack var1, int var2, int var3, boolean var4, boolean var5) {
      if (var1 != null && (var4 || var5)) {
         if (var1.isItemDamaged() && var4) {
            int var6 = (int)Math.round(13.0 - var1.getItemDamage() * 13.0 / var1.getMaxDamage());
            int var7 = (int)Math.round(255.0 - var1.getItemDamage() * 255.0 / var1.getMaxDamage());
            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
            GlStateManager.disableTexture2D();
            Tessellator var8 = Tessellator.getInstance();
            int var9 = 255 - var7 << 16 | var7 << 8;
            int var10 = (255 - var7) / 4 << 16 | 16128;
            method_03741(var8, var2 + 2, var3 + 13, 13, 2, 0, 0, 0, 255);
            method_03741(var8, var2 + 2, var3 + 13, 12, 1, (255 - var7) / 4, 64, 0, 255);
            int var11 = 255 - var7;
            int var12 = var7;
            int var13 = 0;
            if (Config.isCustomColors()) {
               int var14 = CustomColors.getDurabilityColor(var7);
               if (var14 >= 0) {
                  var11 = var14 >> 16 & 0xFF;
                  var12 = var14 >> 8 & 0xFF;
                  var13 = var14 >> 0 & 0xFF;
               }
            }

            method_03741(var8, var2 + 2, var3 + 13, var6, 1, var11, var12, var13, 255);
            GlStateManager.enableTexture2D();
            GlStateManager.enableLighting();
            GlStateManager.enableBlend();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         if (var5) {
            int var15 = 0;
            if (var1.getMaxStackSize() > 1) {
               var15 = countInInventory(Minecraft.getMinecraft().thePlayer, var1.getItem(), var1.getItemDamage());
            } else if (var1.getItem().equals(Items.bow)) {
               var15 = countInInventory(Minecraft.getMinecraft().thePlayer, Items.arrow);
            }

            if (var15 > 1) {
               String var16 = "" + var15;
               GlStateManager.disableLighting();
               GlStateManager.disableBlend();
               var0.drawStringWithShadow(var16, var2 + 19 - 2 - var0.getStringWidth(var16), var3 + 6 + 3, 16777215);
               GlStateManager.enableLighting();
               GlStateManager.disableBlend();
            }
         }
      }
   }

   public static int getColorCode(char var0, boolean var1) {
      return colorCodes[var1 ? "0123456789abcdef".indexOf(var0) : "0123456789abcdef".indexOf(var0) + 16];
   }

   public static String method_03744(String var0) {
      return var0.replaceAll("(?i)§[0-9a-fklmnor]", "");
   }

   public static void drawTexturedModalRect(int var0, int var1, int var2, int var3, int var4, int var5, float var6) {
      float var7 = 0.00390625F;
      float var8 = 0.00390625F;
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      var10.begin(7, DefaultVertexFormats.POSITION_TEX);
      var10.pos(var0 + 0, var1 + var5, var6).tex((var2 + 0) * var7, (var3 + var5) * var8).endVertex();
      var10.pos(var0 + var4, var1 + var5, var6).tex((var2 + var4) * var7, (var3 + var5) * var8).endVertex();
      var10.pos(var0 + var4, var1 + 0, var6).tex((var2 + var4) * var7, (var3 + 0) * var8).endVertex();
      var10.pos(var0 + 0, var1 + 0, var6).tex((var2 + 0) * var7, (var3 + 0) * var8).endVertex();
      var9.draw();
   }

   public static void renderItemOverlayIntoGUI(FontRenderer var0, ItemStack var1, int var2, int var3) {
      renderItemOverlayIntoGUI(var0, var1, var2, var3, true, true);
   }

   public static void drawContinuousTexturedBox(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, float var9) {
      drawContinuousTexturedBox(var0, var1, var2, var3, var4, var5, var6, var7, var8, var8, var8, var8, var9);
   }

   public static int countInInventory(EntityPlayer var0, Item var1) {
      return countInInventory(var0, var1, -1);
   }

   public static void drawContinuousTexturedBox(
      ResourceLocation var0,
      int var1,
      int var2,
      int var3,
      int var4,
      int var5,
      int var6,
      int var7,
      int var8,
      int var9,
      int var10,
      int var11,
      int var12,
      float var13
   ) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(var0);
      drawContinuousTexturedBox(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }

   public static int countInInventory(EntityPlayer var0, Item var1, int var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var0.bi.mainInventory.length; var4++) {
         if (var0.bi.mainInventory[var4] != null
            && var1.equals(var0.bi.mainInventory[var4].getItem())
            && (var2 == -1 || var0.bi.mainInventory[var4].getItemDamage() == var2)) {
            var3 += var0.bi.mainInventory[var4].stackSize;
         }
      }

      return var3;
   }

   public static void method_03741(Tessellator var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      WorldRenderer var9 = var0.getWorldRenderer();
      var9.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var9.pos(var1 + 0, var2 + 0, 0.0).color(var5, var6, var7, var8).endVertex();
      var9.pos(var1 + 0, var2 + var4, 0.0).color(var5, var6, var7, var8).endVertex();
      var9.pos(var1 + var3, var2 + var4, 0.0).color(var5, var6, var7, var8).endVertex();
      var9.pos(var1 + var3, var2 + 0, 0.0).color(var5, var6, var7, var8).endVertex();
      var0.draw();
   }

   public static void drawContinuousTexturedBox(
      ResourceLocation var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, float var10
   ) {
      drawContinuousTexturedBox(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var9, var9, var9, var10);
   }

   public static void drawContinuousTexturedBox(
      int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, float var12
   ) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3042);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glBlendFunc(770, 771);
      int var14 = var6 - var10 - var11;
      int var15 = var7 - var8 - var9;
      int var16 = var4 - var10 - var11;
      int var17 = var5 - var8 - var9;
      int var18 = var16 / var14;
      int var19 = var16 % var14;
      int var20 = var17 / var15;
      int var21 = var17 % var15;
      drawTexturedModalRect(var0, var1, var2, var3, var10, var8, var12);
      drawTexturedModalRect(var0 + var10 + var16, var1, var2 + var10 + var14, var3, var11, var8, var12);
      drawTexturedModalRect(var0, var1 + var8 + var17, var2, var3 + var8 + var15, var10, var9, var12);
      drawTexturedModalRect(var0 + var10 + var16, var1 + var8 + var17, var2 + var10 + var14, var3 + var8 + var15, var11, var9, var12);

      for (int var13 = 0; var13 < var18 + (var19 > 0 ? 1 : 0); var13++) {
         drawTexturedModalRect(var0 + var10 + var13 * var14, var1, var2 + var10, var3, var13 == var18 ? var19 : var14, var8, var12);
         drawTexturedModalRect(
            var0 + var10 + var13 * var14, var1 + var8 + var17, var2 + var10, var3 + var8 + var15, var13 == var18 ? var19 : var14, var9, var12
         );

         for (int var22 = 0; var22 < var20 + (var21 > 0 ? 1 : 0); var22++) {
            drawTexturedModalRect(
               var0 + var10 + var13 * var14,
               var1 + var8 + var22 * var15,
               var2 + var10,
               var3 + var8,
               var13 == var18 ? var19 : var14,
               var22 == var20 ? var21 : var15,
               var12
            );
         }
      }

      for (int var23 = 0; var23 < var20 + (var21 > 0 ? 1 : 0); var23++) {
         drawTexturedModalRect(var0, var1 + var8 + var23 * var15, var2, var3 + var8, var10, var23 == var20 ? var21 : var15, var12);
         drawTexturedModalRect(
            var0 + var10 + var16, var1 + var8 + var23 * var15, var2 + var10 + var14, var3 + var8, var11, var23 == var20 ? var21 : var15, var12
         );
      }
   }
}
