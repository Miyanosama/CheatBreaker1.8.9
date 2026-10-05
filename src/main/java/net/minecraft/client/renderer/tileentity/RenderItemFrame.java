package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class RenderItemFrame extends Render<EntityItemFrame> {
   public Minecraft mc = Minecraft.getMinecraft();
   public ModelResourceLocation itemFrameModel = new ModelResourceLocation("item_frame", "normal");
   public ModelResourceLocation mapModel = new ModelResourceLocation("item_frame", "map");
   public static ResourceLocation mapBackgroundTextures = new ResourceLocation("textures/map/map_background.png");
   public RenderItem itemRenderer;
   public static double itemRenderDistanceSq = 4096.0;

   public boolean isRenderItem(EntityItemFrame var1) {
      if (Shaders.isShadowPass) {
         return false;
      } else {
         if (!Config.zoomMode) {
            Entity var2 = this.mc.getRenderViewEntity();
            double var3 = var1.e(var2.s, var2.t, var2.u);
            if (var3 > itemRenderDistanceSq) {
               return false;
            }
         }

         return true;
      }
   }

   public void renderItem(EntityItemFrame var1) {
      ItemStack var2 = var1.getDisplayedItem();
      if (var2 != null) {
         if (!this.isRenderItem(var1)) {
            return;
         }

         if (!Config.zoomMode) {
            EntityPlayerSP var3 = this.mc.thePlayer;
            double var4 = var1.e(var3.s, var3.t, var3.u);
            if (var4 > 4096.0) {
               return;
            }
         }

         EntityItem var12 = new EntityItem(var1.o, 0.0, 0.0, 0.0, var2);
         Item var13 = var12.getEntityItem().getItem();
         var12.getEntityItem().stackSize = 1;
         var12.hoverStart = 0.0F;
         GlStateManager.pushMatrix();
         GlStateManager.disableLighting();
         int var5 = var1.getRotation();
         if (var13 instanceof ItemMap) {
            var5 = var5 % 4 * 2;
         }

         GlStateManager.rotate(var5 * 360.0F / 8.0F, 0.0F, 0.0F, 1.0F);
         if (!Reflector.postForgeBusEvent(Reflector.RenderItemInFrameEvent_Constructor, var1, this)) {
            if (var13 instanceof ItemMap) {
               this.b.renderEngine.bindTexture(mapBackgroundTextures);
               GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
               float var14 = 0.0078125F;
               GlStateManager.scale(var14, var14, var14);
               GlStateManager.translate(-64.0F, -64.0F, 0.0F);
               MapData var15 = Items.filled_map.getMapData(var12.getEntityItem(), var1.o);
               GlStateManager.translate(0.0F, 0.0F, -1.0F);
               if (var15 != null) {
                  this.mc.entityRenderer.getMapItemRenderer().renderMap(var15, true);
               }
            } else {
               TextureAtlasSprite var6 = null;
               if (var13 == Items.compass) {
                  var6 = this.mc.getTextureMapBlocks().getAtlasSprite(TextureCompass.locationSprite);
                  this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
                  if (var6 instanceof TextureCompass) {
                     TextureCompass var7 = (TextureCompass)var6;
                     double var8 = var7.currentAngle;
                     double var10 = var7.angleDelta;
                     var7.currentAngle = 0.0;
                     var7.angleDelta = 0.0;
                     var7.updateCompass(var1.o, var1.s, var1.u, MathHelper.wrapAngleTo180_float(180 + var1.b.getHorizontalIndex() * 90), false, true);
                     var7.currentAngle = var8;
                     var7.angleDelta = var10;
                  } else {
                     var6 = null;
                  }
               }

               GlStateManager.scale(0.5F, 0.5F, 0.5F);
               if (!this.itemRenderer.shouldRenderItemIn3D(var12.getEntityItem()) || var13 instanceof ItemSkull) {
                  GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
               }

               GlStateManager.pushAttrib();
               RenderHelper.enableStandardItemLighting();
               this.itemRenderer.renderItem(var12.getEntityItem(), ItemCameraTransforms.TransformType.FIXED);
               RenderHelper.disableStandardItemLighting();
               GlStateManager.popAttrib();
               if (var6 != null && var6.getFrameCount() > 0) {
                  var6.updateAnimation();
               }
            }
         }

         GlStateManager.enableLighting();
         GlStateManager.popMatrix();
      }
   }

   public static void updateItemRenderDistance() {
      Minecraft var0 = Config.getMinecraft();
      double var1 = Config.limit(var0.gameSettings.gammaSetting, 1.0F, 120.0F);
      double var3 = Math.max(6.0 * var0.displayHeight / var1, 16.0);
      itemRenderDistanceSq = var3 * var3;
   }

   public RenderItemFrame(RenderManager var1, RenderItem var2) {
      super(var1);
      this.itemRenderer = var2;
   }

   public void doRender(EntityItemFrame var1, double var2, double var4, double var6, float var8, float var9) {
      GlStateManager.pushMatrix();
      BlockPos var10 = var1.n();
      double var11 = var10.getX() - var1.s + var2;
      double var13 = var10.getY() - var1.t + var4;
      double var15 = var10.getZ() - var1.u + var6;
      GlStateManager.translate(var11 + 0.5, var13 + 0.5, var15 + 0.5);
      GlStateManager.rotate(180.0F - var1.y, 0.0F, 1.0F, 0.0F);
      this.b.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      BlockRendererDispatcher var17 = this.mc.getBlockRendererDispatcher();
      ModelManager var18 = var17.getBlockModelShapes().getModelManager();
      IBakedModel var19;
      if (var1.getDisplayedItem() != null && var1.getDisplayedItem().getItem() == Items.filled_map) {
         var19 = var18.getModel(this.mapModel);
      } else {
         var19 = var18.getModel(this.itemFrameModel);
      }

      GlStateManager.pushMatrix();
      GlStateManager.translate(-0.5F, -0.5F, -0.5F);
      var17.getBlockModelRenderer().renderModelBrightnessColor(var19, 1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
      GlStateManager.translate(0.0F, 0.0F, 0.4375F);
      this.renderItem(var1);
      GlStateManager.popMatrix();
      this.renderName(var1, var2 + var1.b.getFrontOffsetX() * 0.3F, var4 - 0.25, var6 + var1.b.getFrontOffsetZ() * 0.3F);
   }

   public void renderName(EntityItemFrame var1, double var2, double var4, double var6) {
      if (Minecraft.isGuiEnabled() && var1.getDisplayedItem() != null && var1.getDisplayedItem().hasDisplayName() && this.b.pointedEntity == var1) {
         float var8 = 1.6F;
         float var9 = 0.016666668F * var8;
         double var10 = var1.h(this.b.livingPlayer);
         float var12 = var1.isSneaking() ? 32.0F : 64.0F;
         if (var10 < var12 * var12) {
            String var13 = var1.getDisplayedItem().getDisplayName();
            if (var1.isSneaking()) {
               FontRenderer var14 = this.c();
               GlStateManager.pushMatrix();
               GlStateManager.translate((float)var2 + 0.0F, (float)var4 + var1.K + 0.5F, (float)var6);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(-this.b.playerViewY, 0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(this.b.playerViewX, 1.0F, 0.0F, 0.0F);
               GlStateManager.scale(-var9, -var9, var9);
               GlStateManager.disableLighting();
               GlStateManager.translate(0.0F, 0.25F / var9, 0.0F);
               GlStateManager.depthMask(false);
               GlStateManager.enableBlend();
               GlStateManager.blendFunc(770, 771);
               Tessellator var15 = Tessellator.getInstance();
               WorldRenderer var16 = var15.getWorldRenderer();
               int var17 = var14.getStringWidth(var13) / 2;
               GlStateManager.disableTexture2D();
               var16.begin(7, DefaultVertexFormats.POSITION_COLOR);
               var16.pos(-var17 - 1, -1.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               var16.pos(-var17 - 1, 8.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               var16.pos(var17 + 1, 8.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               var16.pos(var17 + 1, -1.0, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               var15.draw();
               GlStateManager.enableTexture2D();
               GlStateManager.depthMask(true);
               var14.drawString(var13, -var14.getStringWidth(var13) / 2, 0, 553648127);
               GlStateManager.enableLighting();
               GlStateManager.disableBlend();
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.popMatrix();
            } else {
               this.renderLivingLabel(var1, var13, var2, var4, var6, 64);
            }
         }
      }
   }

   public ResourceLocation getEntityTexture(EntityItemFrame var1) {
      return null;
   }
}
