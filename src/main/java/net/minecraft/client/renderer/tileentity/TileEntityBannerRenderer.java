package net.minecraft.client.renderer.tileentity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.LayeredColorMaskTexture;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TileEntityBannerRenderer extends TileEntitySpecialRenderer<TileEntityBanner> {
   public static Map<String, TileEntityBannerRenderer.TimedBannerTexture> DESIGNS = Maps.newHashMap();
   public ModelBanner bannerModel = new ModelBanner();
   public static ResourceLocation BANNERTEXTURES = new ResourceLocation("textures/entity/banner_base.png");

   public ResourceLocation func_178463_a(TileEntityBanner var1) {
      String var2 = var1.getPatternResourceLocation();
      if (var2.isEmpty()) {
         return null;
      } else {
         TileEntityBannerRenderer.TimedBannerTexture var3 = DESIGNS.get(var2);
         if (var3 == null) {
            if (DESIGNS.size() >= 256) {
               long var4 = System.currentTimeMillis();
               Iterator var6 = DESIGNS.keySet().iterator();

               while (var6.hasNext()) {
                  String var7 = (String)var6.next();
                  TileEntityBannerRenderer.TimedBannerTexture var8 = DESIGNS.get(var7);
                  if (var4 - var8.systemTime > 60000L) {
                     Minecraft.getMinecraft().getTextureManager().deleteTexture(var8.bannerTexture);
                     var6.remove();
                  }
               }

               if (DESIGNS.size() >= 256) {
                  return null;
               }
            }

            List var9 = var1.getPatternList();
            List var5 = var1.getColorList();
            ArrayList var10 = Lists.newArrayList();

            for (TileEntityBanner.EnumBannerPattern var12 : (Iterable<TileEntityBanner.EnumBannerPattern>)(Iterable<?>)(var9)) {
               var10.add("textures/entity/banner/" + var12.getPatternName() + ".png");
            }

            var3 = new TileEntityBannerRenderer.TimedBannerTexture();
            var3.bannerTexture = new ResourceLocation(var2);
            Minecraft.getMinecraft().getTextureManager().loadTexture(var3.bannerTexture, new LayeredColorMaskTexture(BANNERTEXTURES, var10, var5));
            DESIGNS.put(var2, var3);
         }

         var3.systemTime = System.currentTimeMillis();
         return var3.bannerTexture;
      }
   }

   public void renderTileEntityAt(TileEntityBanner var1, double var2, double var4, double var6, float var8, int var9) {
      boolean var10 = var1.getWorld() != null;
      boolean var11 = !var10 || var1.w() == Blocks.standing_banner;
      int var12 = var10 ? var1.u() : 0;
      long var13 = var10 ? var1.getWorld().K() : 0L;
      GlStateManager.pushMatrix();
      float var15 = 0.6666667F;
      if (var11) {
         GlStateManager.translate((float)var2 + 0.5F, (float)var4 + 0.75F * var15, (float)var6 + 0.5F);
         float var16 = var12 * 360 / 16.0F;
         GlStateManager.rotate(-var16, 0.0F, 1.0F, 0.0F);
         this.bannerModel.bannerStand.showModel = true;
      } else {
         float var19 = 0.0F;
         if (var12 == 2) {
            var19 = 180.0F;
         }

         if (var12 == 4) {
            var19 = 90.0F;
         }

         if (var12 == 5) {
            var19 = -90.0F;
         }

         GlStateManager.translate((float)var2 + 0.5F, (float)var4 - 0.25F * var15, (float)var6 + 0.5F);
         GlStateManager.rotate(-var19, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
         this.bannerModel.bannerStand.showModel = false;
      }

      BlockPos var20 = var1.v();
      float var17 = var20.getX() * 7 + var20.getY() * 9 + var20.getZ() * 13 + (float)var13 + var8;
      this.bannerModel.bannerSlate.rotateAngleX = (-0.0125F + 0.01F * MathHelper.cos(var17 * (float) Math.PI * 0.02F)) * (float) Math.PI;
      GlStateManager.enableRescaleNormal();
      ResourceLocation var18 = this.func_178463_a(var1);
      if (var18 != null) {
         this.bindTexture(var18);
         GlStateManager.pushMatrix();
         GlStateManager.scale(var15, -var15, -var15);
         this.bannerModel.renderBanner();
         GlStateManager.popMatrix();
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public static class TimedBannerTexture {
      public ResourceLocation bannerTexture;
      public long systemTime;

      public TimedBannerTexture() {
      }
   }
}
