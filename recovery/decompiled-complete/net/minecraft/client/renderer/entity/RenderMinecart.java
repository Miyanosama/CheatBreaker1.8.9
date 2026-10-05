package net.minecraft.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.MapItemRenderer$Instance;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.Locale;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$12;

public class RenderMinecart<T extends EntityMinecart> extends Render<T> {
   public MapItemRenderer$Instance field_0001;
   public static ResourceLocation minecartTextures = new ResourceLocation("textures/entity/minecart.png");
   public ModelBase modelMinecart = new ModelMinecart();
   public LogBrokerMonitor$12 field_0000;
   public Locale field_0002;

   public RenderMinecart(RenderManager var1) {
      super(var1);
      this.c = 0.5F;
   }

   public void doRender(T var1, double var2, double var4, double var6, float var8, float var9) {
      GlStateManager.pushMatrix();
      this.bindEntityTexture((T)var1);
      long var10 = var1.F() * (4001983884046038327L & -4001983883596268097L);
      var10 = var10 * var10 * (4393250515L & -4472921770287221035L) + var10 * (-6857349890970383925L & 6857349889530103241L);
      float var12 = (((float)(var10 >> 16 & 2115521905308148583L & 134220807L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float var13 = (((float)(var10 >> 20 & 173017623L & 1879852039L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float var14 = (((float)(var10 >> 24 & 1075677447L & 675430439L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      GlStateManager.translate(var12, var13, var14);
      double var15 = var1.P + (var1.s - var1.P) * var9;
      double var17 = var1.Q + (var1.t - var1.Q) * var9;
      double var19 = var1.R + (var1.u - var1.R) * var9;
      double var21 = 0.3F;
      Vec3 var23 = var1.func_70489_a(var15, var17, var19);
      float var24 = var1.B + (var1.z - var1.B) * var9;
      if (var23 != null) {
         Vec3 var25 = var1.func_70495_a(var15, var17, var19, var21);
         Vec3 var26 = var1.func_70495_a(var15, var17, var19, -var21);
         if (var25 == null) {
            var25 = var23;
         }

         if (var26 == null) {
            var26 = var23;
         }

         var2 += var23.xCoord - var15;
         var4 += (var25.yCoord + var26.yCoord) / 2.0 - var17;
         var6 += var23.zCoord - var19;
         Vec3 var27 = var26.addVector(-var25.xCoord, -var25.yCoord, -var25.zCoord);
         if (var27.lengthVector() != 0.0) {
            var27 = var27.normalize();
            var8 = (float)(Math.atan2(var27.zCoord, var27.xCoord) * 180.0 / Math.PI);
            var24 = (float)(Math.atan(var27.yCoord) * 73.0);
         }
      }

      GlStateManager.translate((float)var2, (float)var4 + 0.375F, (float)var6);
      GlStateManager.rotate(180.0F - var8, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-var24, 0.0F, 0.0F, 1.0F);
      float var31 = var1.getRollingAmplitude() - var9;
      float var32 = var1.getDamage() - var9;
      if (var32 < 0.0F) {
         var32 = 0.0F;
      }

      if (var31 > 0.0F) {
         GlStateManager.rotate(MathHelper.sin(var31) * var31 * var32 / 10.0F * var1.getRollingDirection(), 1.0F, 0.0F, 0.0F);
      }

      int var34 = var1.getDisplayTileOffset();
      IBlockState var28 = var1.getDisplayTile();
      if (var28.getBlock().getRenderType() != -1) {
         GlStateManager.pushMatrix();
         this.a(TextureMap.locationBlocksTexture);
         float var29 = 0.75F;
         GlStateManager.scale(var29, var29, var29);
         GlStateManager.translate(-0.5F, (var34 - 8) / 16.0F, 0.5F);
         this.func_180560_a((T)var1, var9, var28);
         GlStateManager.popMatrix();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.bindEntityTexture((T)var1);
      }

      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      this.modelMinecart.render(var1, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GlStateManager.popMatrix();
      super.doRender((T)var1, var2, var4, var6, var8, var9);
   }

   public ResourceLocation getEntityTexture(T var1) {
      return minecartTextures;
   }

   public void func_180560_a(T var1, float var2, IBlockState var3) {
      GlStateManager.pushMatrix();
      Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(var3, var1.a_(var2));
      GlStateManager.popMatrix();
   }
}
