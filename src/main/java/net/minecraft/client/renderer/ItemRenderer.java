package net.minecraft.client.renderer;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.AnimationsModule;
import com.cheatbreaker.client.module.type.TextureOptionsModule;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.optifine.DynamicLights;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.ItemRenderer$EnumSwitch;
import com.cheatbreaker.client.util.render.FreelookController;
import com.cheatbreaker.client.util.render.LegacyItemAnimations;

public class ItemRenderer {
   public RenderItem itemRenderer;
   public static ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
   public float recoveredField1204;
   public Minecraft mc;
   public RenderManager renderManager;
   public float recoveredField1205;
   public static ResourceLocation RES_UNDERWATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
   public int equippedItemSlot = -1;
   public ItemStack itemToRender;

   public void method_25647() {
      this.recoveredField1205 = 0.0F;
   }

   public void renderBlockInHand(float var1, TextureAtlasSprite var2) {
      this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
      Tessellator var3 = Tessellator.getInstance();
      WorldRenderer var4 = var3.getWorldRenderer();
      float var5 = 0.1F;
      GlStateManager.color(0.1F, 0.1F, 0.1F, 0.5F);
      GlStateManager.pushMatrix();
      float var6 = var2.getMinU();
      float var7 = var2.getMaxU();
      float var8 = var2.getMinV();
      float var9 = var2.getMaxV();
      var4.begin(7, DefaultVertexFormats.POSITION_TEX);
      var4.pos(-1.0, -1.0, -0.5).tex(var7, var9).endVertex();
      var4.pos(1.0, -1.0, -0.5).tex(var6, var9).endVertex();
      var4.pos(1.0, 1.0, -0.5).tex(var6, var8).endVertex();
      var4.pos(-1.0, 1.0, -0.5).tex(var7, var8).endVertex();
      var3.draw();
      GlStateManager.popMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void doItemUsedTransformations(float var1) {
      float var2 = -0.4F * MathHelper.sin(MathHelper.sqrt_float(var1) * (float) Math.PI);
      float var3 = 0.2F * MathHelper.sin(MathHelper.sqrt_float(var1) * (float) Math.PI * 2.0F);
      float var4 = -0.2F * MathHelper.sin(var1 * (float) Math.PI);
      GlStateManager.translate(var2, var3, var4);
   }

   public void rotateArroundXAndY(float var1, float var2) {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(var1, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(var2, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.popMatrix();
   }

   public void renderItemInFirstPerson(float var1) {
      AnimationsModule var2 = CheatBreaker.getInstance().getModuleManager().recoveredField1717;
      FreelookController var3 = CheatBreaker.getInstance().getModuleManager().recoveredField1728;
      float var4 = var3.recoveredField3503 ? this.mc.thePlayer.y - var3.recoveredField3508 : 0.0F;
      if (this.itemToRender != null) {
         float var6 = this.recoveredField1204 + (this.recoveredField1205 - this.recoveredField1204) * var1;
         if (LegacyItemAnimations.recoveredField55.method_28640(this, this.itemToRender, var6, var1) && var2.isEnabled()) {
            return;
         }
      }

      if (!Config.isShaders() || !Shaders.isSkipRenderHand()) {
         float var5 = 1.0F - (this.recoveredField1204 + (this.recoveredField1205 - this.recoveredField1204) * var1);
         EntityPlayerSP var11 = this.mc.thePlayer;
         float var7 = var11.getSwingProgress(var1);
         float var8 = var11.B + (var11.z - var11.B) * var1;
         float var9 = var11.A + (var11.y - var11.A) * var1 + var4 * 10.0F;
         this.rotateArroundXAndY(var8, var9);
         this.setLightMapFromPlayer(var11);
         this.rotateWithPlayerRotations(var11, var1);
         GlStateManager.enableRescaleNormal();
         GlStateManager.pushMatrix();
         if (this.itemToRender != null) {
            if (this.itemToRender.getItem() instanceof ItemMap) {
               this.renderItemMap(var11, var8, var5, var7);
            } else if (var11.getItemInUseCount() > 0) {
               EnumAction var10 = this.itemToRender.getItemUseAction();
               switch (ItemRenderer$EnumSwitch.recoveredField1779[var10.ordinal()]) {
                  case 1:
                     this.transformFirstPersonItem(var5, 0.0F);
                     break;
                  case 2:
                  case 3:
                     this.performDrinking(var11, var1);
                     this.transformFirstPersonItem(var5, 0.0F);
                     break;
                  case 4:
                     this.transformFirstPersonItem(var5, 0.0F);
                     this.doBlockTransformations();
                     break;
                  case 5:
                     this.transformFirstPersonItem(var5, 0.0F);
                     this.doBowTransformations(var1, var11);
               }
            } else {
               this.doItemUsedTransformations(var7);
               this.transformFirstPersonItem(var5, var7);
            }

            this.renderItem(var11, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
         } else if (!var11.isInvisible()) {
            this.renderPlayerArm(var11, var5, var7);
         }

         GlStateManager.popMatrix();
         GlStateManager.disableRescaleNormal();
         RenderHelper.disableStandardItemLighting();
      }
   }

   public void renderItemMap(AbstractClientPlayer var1, float var2, float var3, float var4) {
      float var5 = -0.4F * MathHelper.sin(MathHelper.sqrt_float(var4) * (float) Math.PI);
      float var6 = 0.2F * MathHelper.sin(MathHelper.sqrt_float(var4) * (float) Math.PI * 2.0F);
      float var7 = -0.2F * MathHelper.sin(var4 * (float) Math.PI);
      GlStateManager.translate(var5, var6, var7);
      float var8 = this.getMapAngleFromPitch(var2);
      GlStateManager.translate(0.0F, 0.04F, -0.72F);
      GlStateManager.translate(0.0F, var3 * -1.2F, 0.0F);
      GlStateManager.translate(0.0F, var8 * -0.5F, 0.0F);
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(var8 * -85.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
      this.renderPlayerArms(var1);
      float var9 = MathHelper.sin(var4 * var4 * (float) Math.PI);
      float var10 = MathHelper.sin(MathHelper.sqrt_float(var4) * (float) Math.PI);
      GlStateManager.rotate(var9 * -20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(var10 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(var10 * -80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(0.38F, 0.38F, 0.38F);
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(-1.0F, -1.0F, 0.0F);
      GlStateManager.scale(0.015625F, 0.015625F, 0.015625F);
      this.mc.getTextureManager().bindTexture(RES_MAP_BACKGROUND);
      Tessellator var11 = Tessellator.getInstance();
      WorldRenderer var12 = var11.getWorldRenderer();
      GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      var12.begin(7, DefaultVertexFormats.POSITION_TEX);
      var12.pos(-7.0, 135.0, 0.0).tex(0.0, 1.0).endVertex();
      var12.pos(135.0, 135.0, 0.0).tex(1.0, 1.0).endVertex();
      var12.pos(135.0, -7.0, 0.0).tex(1.0, 0.0).endVertex();
      var12.pos(-7.0, -7.0, 0.0).tex(0.0, 0.0).endVertex();
      var11.draw();
      MapData var13 = Items.filled_map.getMapData(this.itemToRender, this.mc.theWorld);
      if (var13 != null) {
         this.mc.entityRenderer.getMapItemRenderer().renderMap(var13, false);
      }
   }

   public boolean isBlockTranslucent(Block var1) {
      return var1 != null && var1.getBlockLayer() == EnumWorldBlockLayer.TRANSLUCENT;
   }

   public void method_25662() {
      this.recoveredField1205 = 0.0F;
   }

   public void renderPlayerArm(AbstractClientPlayer var1, float var2, float var3) {
      float var4 = -0.3F * MathHelper.sin(MathHelper.sqrt_float(var3) * (float) Math.PI);
      float var5 = 0.4F * MathHelper.sin(MathHelper.sqrt_float(var3) * (float) Math.PI * 2.0F);
      float var6 = -0.4F * MathHelper.sin(var3 * (float) Math.PI);
      GlStateManager.translate(var4, var5, var6);
      GlStateManager.translate(0.64000005F, -0.6F, -0.71999997F);
      GlStateManager.translate(0.0F, var2 * -0.6F, 0.0F);
      GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
      float var7 = MathHelper.sin(var3 * var3 * (float) Math.PI);
      float var8 = MathHelper.sin(MathHelper.sqrt_float(var3) * (float) Math.PI);
      GlStateManager.rotate(var8 * 70.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(var7 * -20.0F, 0.0F, 0.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(var1.getLocationSkin());
      GlStateManager.translate(-1.0F, 3.6F, 3.5F);
      GlStateManager.rotate(120.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.scale(1.0F, 1.0F, 1.0F);
      GlStateManager.translate(5.6F, 0.0F, 0.0F);
      Render var9 = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
      GlStateManager.disableCull();
      RenderPlayer var10 = (RenderPlayer)var9;
      var10.renderRightArm(this.mc.thePlayer);
      GlStateManager.enableCull();
   }

   public void doBlockTransformations() {
      GlStateManager.translate(-0.5F, 0.2F, 0.0F);
      GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
   }

   public ItemRenderer(Minecraft var1) {
      this.mc = var1;
      this.renderManager = var1.getRenderManager();
      this.itemRenderer = var1.getRenderItem();
   }

   public void renderItem(EntityLivingBase var1, ItemStack var2, ItemCameraTransforms.TransformType var3) {
      if (var2 != null) {
         Item var4 = var2.getItem();
         Block var5 = Block.getBlockFromItem(var4);
         GlStateManager.pushMatrix();
         if (this.itemRenderer.shouldRenderItemIn3D(var2)) {
            GlStateManager.scale(2.0F, 2.0F, 2.0F);
            if (this.isBlockTranslucent(var5) && (!Config.isShaders() || !Shaders.renderItemKeepDepthMask)) {
               GlStateManager.depthMask(false);
            }
         }

         this.itemRenderer.renderItemModelForEntity(var2, var1, var3);
         if (this.isBlockTranslucent(var5)) {
            GlStateManager.depthMask(true);
         }

         GlStateManager.popMatrix();
      }
   }

   public void rotateWithPlayerRotations(EntityPlayerSP var1, float var2) {
      float var3 = var1.prevRenderArmPitch + (var1.renderArmPitch - var1.prevRenderArmPitch) * var2;
      float var4 = var1.prevRenderArmYaw + (var1.renderArmYaw - var1.prevRenderArmYaw) * var2;
      GlStateManager.rotate((var1.z - var3) * 0.1F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate((var1.y - var4) * 0.1F, 0.0F, 1.0F, 0.0F);
   }

   public void setLightMapFromPlayer(AbstractClientPlayer var1) {
      int var2 = this.mc.theWorld.getCombinedLight(new BlockPos(var1.s, var1.t + var1.getEyeHeight(), var1.u), 0);
      if (Config.isDynamicLights()) {
         var2 = DynamicLights.getCombinedLight(this.mc.getRenderViewEntity(), var2);
      }

      float var3 = var2 & 65535;
      float var4 = var2 >> 16;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var3, var4);
   }

   public void method_25646(float var1) {
      GlStateManager.disableAlpha();
      if (this.mc.thePlayer.isEntityInsideOpaqueBlock()) {
         IBlockState var2 = this.mc.theWorld.getBlockState(new BlockPos(this.mc.thePlayer));
         BlockPos var3 = new BlockPos(this.mc.thePlayer);
         EntityPlayerSP var4 = this.mc.thePlayer;

         for (int var5 = 0; var5 < 8; var5++) {
            double var6 = var4.s + ((var5 >> 0) % 2 - 0.5F) * var4.J * 0.8F;
            double var8 = var4.t + ((var5 >> 1) % 2 - 0.5F) * 0.1F;
            double var10 = var4.u + ((var5 >> 2) % 2 - 0.5F) * var4.J * 0.8F;
            BlockPos var12 = new BlockPos(var6, var8 + var4.getEyeHeight(), var10);
            IBlockState var13 = this.mc.theWorld.getBlockState(var12);
            if (var13.getBlock().isVisuallyOpaque()) {
               var2 = var13;
               var3 = var12;
            }
         }

         if (var2.getBlock().getRenderType() != -1) {
            Object var17 = Reflector.getFieldValue(Reflector.RenderBlockOverlayEvent_OverlayType_BLOCK);
            if (!Reflector.callBoolean(Reflector.ForgeEventFactory_renderBlockOverlay, this.mc.thePlayer, var1, var17, var2, var3)) {
               this.renderBlockInHand(var1, this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(var2));
            }
         }
      }

      if (!this.mc.thePlayer.isSpectator()) {
         if (this.mc.thePlayer.a(Material.water) && !Reflector.callBoolean(Reflector.ForgeEventFactory_renderWaterOverlay, this.mc.thePlayer, var1)) {
            this.method_25643(var1);
         }

         TextureOptionsModule var14 = CheatBreaker.getInstance().getModuleManager().recoveredField1727;
         boolean var15 = var14.isEnabled();
         boolean var16 = var15 ? (Boolean)var14.recoveredField1519.getValue() : true;
         if (this.mc.thePlayer.isBurning() && var16 && !Reflector.callBoolean(Reflector.ForgeEventFactory_renderFireOverlay, this.mc.thePlayer, var1)) {
            this.method_25661(var1);
         }
      }

      GlStateManager.enableAlpha();
   }

   public void performDrinking(AbstractClientPlayer var1, float var2) {
      float var3 = var1.getItemInUseCount() - var2 + 1.0F;
      float var4 = var3 / this.itemToRender.getMaxItemUseDuration();
      float var5 = MathHelper.abs(MathHelper.cos(var3 / 4.0F * (float) Math.PI) * 0.1F);
      if (var4 >= 0.8F) {
         var5 = 0.0F;
      }

      GlStateManager.translate(0.0F, var5, 0.0F);
      float var6 = 1.0F - (float)Math.pow(var4, 27.0);
      GlStateManager.translate(var6 * 0.6F, var6 * -0.5F, var6 * 0.0F);
      GlStateManager.rotate(var6 * 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(var6 * 10.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(var6 * 30.0F, 0.0F, 0.0F, 1.0F);
   }

   public void renderLeftArm(RenderPlayer var1) {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(92.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(41.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(-0.3F, -1.1F, 0.45F);
      var1.renderLeftArm(this.mc.thePlayer);
      GlStateManager.popMatrix();
   }

   public void method_25661(float var1) {
      Tessellator var2 = Tessellator.getInstance();
      WorldRenderer var3 = var2.getWorldRenderer();
      if (CheatBreaker.getInstance().getModuleManager().recoveredField1727.isEnabled()) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, CheatBreaker.getInstance().getModuleManager().recoveredField1727.recoveredField1521.method_08905() / 100.0F);
      } else {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
      }

      GlStateManager.depthFunc(519);
      GlStateManager.depthMask(false);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      float var4 = 1.0F;

      for (int var5 = 0; var5 < 2; var5++) {
         GlStateManager.pushMatrix();
         TextureAtlasSprite var6 = this.mc.getTextureMapBlocks().getAtlasSprite("minecraft:blocks/fire_layer_1");
         this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         float var7 = var6.getMinU();
         float var8 = var6.getMaxU();
         float var9 = var6.getMinV();
         float var10 = var6.getMaxV();
         float var11 = (0.0F - var4) / 2.0F;
         float var12 = var11 + var4;
         float var13 = 0.0F - var4 / 2.0F;
         float var14 = var13 + var4;
         float var15 = -0.5F;
         if (CheatBreaker.getInstance().getModuleManager().recoveredField1727.isEnabled()) {
            GlStateManager.translate(
               -(var5 * 2 - 1) * 0.24F, -1.3F + CheatBreaker.getInstance().getModuleManager().recoveredField1727.recoveredField1515.method_08905(), 0.0F
            );
         } else {
            GlStateManager.translate(-(var5 * 2 - 1) * 0.24F, -0.3F, 0.0F);
         }

         GlStateManager.rotate((var5 * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
         var3.begin(7, DefaultVertexFormats.POSITION_TEX);
         var3.setSprite(var6);
         var3.pos(var11, var13, var15).tex(var8, var10).endVertex();
         var3.pos(var12, var13, var15).tex(var7, var10).endVertex();
         var3.pos(var12, var14, var15).tex(var7, var9).endVertex();
         var3.pos(var11, var14, var15).tex(var8, var9).endVertex();
         var2.draw();
         GlStateManager.popMatrix();
      }

      if (CheatBreaker.getInstance().getModuleManager().recoveredField1727.isEnabled()) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, CheatBreaker.getInstance().getModuleManager().recoveredField1727.recoveredField1521.method_08905() / 100.0F);
      } else {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
      }

      GlStateManager.disableBlend();
      GlStateManager.depthMask(true);
      GlStateManager.depthFunc(515);
   }

   public void method_25643(float var1) {
      if (!Config.isShaders() || Shaders.method_02367()) {
         this.mc.getTextureManager().bindTexture(RES_UNDERWATER_OVERLAY);
         Tessellator var2 = Tessellator.getInstance();
         WorldRenderer var3 = var2.getWorldRenderer();
         float var4 = this.mc.thePlayer.a_(var1);
         GlStateManager.color(var4, var4, var4, 0.5F);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.pushMatrix();
         float var5 = -this.mc.thePlayer.y / 64.0F;
         float var6 = this.mc.thePlayer.z / 64.0F;
         var3.begin(7, DefaultVertexFormats.POSITION_TEX);
         var3.pos(-1.0, -1.0, -0.5).tex(4.0F + var5, 4.0F + var6).endVertex();
         var3.pos(1.0, -1.0, -0.5).tex(0.0F + var5, 4.0F + var6).endVertex();
         var3.pos(1.0, 1.0, -0.5).tex(0.0F + var5, 0.0F + var6).endVertex();
         var3.pos(-1.0, 1.0, -0.5).tex(4.0F + var5, 0.0F + var6).endVertex();
         var2.draw();
         GlStateManager.popMatrix();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
      }
   }

   public void transformFirstPersonItem(float var1, float var2) {
      GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
      GlStateManager.translate(0.0F, var1 * -0.6F, 0.0F);
      GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
      float var3 = MathHelper.sin(var2 * var2 * (float) Math.PI);
      float var4 = MathHelper.sin(MathHelper.sqrt_float(var2) * (float) Math.PI);
      GlStateManager.rotate(var3 * -20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(var4 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(var4 * -80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
   }

   public float getMapAngleFromPitch(float var1) {
      float var2 = 1.0F - var1 / 45.0F + 0.1F;
      var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
      return -MathHelper.cos(var2 * (float) Math.PI) * 0.5F + 0.5F;
   }

   public float method_25667(float var1) {
      EntityPlayerSP var2 = Minecraft.getMinecraft().thePlayer;
      AnimationsModule var3 = CheatBreaker.getInstance().getModuleManager().recoveredField1717;
      ItemStack var4 = var2.bi.getCurrentItem();
      return var3.recoveredField3728.method_08908() && this.equippedItemSlot == var2.bi.currentItem && ItemStack.areItemsEqual(this.itemToRender, var4)
         ? 1.0F - this.recoveredField1204
         : var1;
   }

   public void updateEquippedItem() {
      this.recoveredField1204 = this.recoveredField1205;
      EntityPlayerSP var1 = this.mc.thePlayer;
      ItemStack var2 = var1.bi.getCurrentItem();
      boolean var3 = false;
      if (this.itemToRender != null && var2 != null) {
         if (!this.itemToRender.getIsItemStackEqual(var2)) {
            if (Reflector.ForgeItem_shouldCauseReequipAnimation.exists()) {
               boolean var4 = Reflector.callBoolean(
                  this.itemToRender.getItem(),
                  Reflector.ForgeItem_shouldCauseReequipAnimation,
                  this.itemToRender,
                  var2,
                  this.equippedItemSlot != var1.bi.currentItem
               );
               if (!var4) {
                  this.itemToRender = var2;
                  this.equippedItemSlot = var1.bi.currentItem;
                  return;
               }
            }

            var3 = true;
         }
      } else if (this.itemToRender == null && var2 == null) {
         var3 = false;
      } else {
         var3 = true;
      }

      float var7 = 0.4F;
      float var5 = var3 ? 0.0F : 1.0F;
      float var6 = MathHelper.clamp_float(this.method_25667(var5 - this.recoveredField1205), -var7, var7);
      this.recoveredField1205 += var6;
      if (this.recoveredField1205 < 0.1F) {
         this.itemToRender = var2;
         this.equippedItemSlot = var1.bi.currentItem;
         if (Config.isShaders()) {
            Shaders.method_02352(var2);
         }
      }
   }

   public void renderRightArm(RenderPlayer var1) {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(54.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(64.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-62.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(0.25F, -0.85F, 0.75F);
      var1.renderRightArm(this.mc.thePlayer);
      GlStateManager.popMatrix();
   }

   public void doBowTransformations(float var1, AbstractClientPlayer var2) {
      GlStateManager.rotate(-18.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-12.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-8.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(-0.9F, 0.2F, 0.0F);
      float var3 = this.itemToRender.getMaxItemUseDuration() - (var2.getItemInUseCount() - var1 + 1.0F);
      float var4 = var3 / 20.0F;
      var4 = (var4 * var4 + var4 * 2.0F) / 3.0F;
      if (var4 > 1.0F) {
         var4 = 1.0F;
      }

      if (var4 > 0.1F) {
         float var5 = MathHelper.sin((var3 - 0.1F) * 1.3F);
         float var6 = var4 - 0.1F;
         float var7 = var5 * var6;
         GlStateManager.translate(var7 * 0.0F, var7 * 0.01F, var7 * 0.0F);
      }

      GlStateManager.translate(var4 * 0.0F, var4 * 0.0F, var4 * 0.1F);
      GlStateManager.scale(1.0F, 1.0F, 1.0F + var4 * 0.2F);
   }

   public void renderPlayerArms(AbstractClientPlayer var1) {
      this.mc.getTextureManager().bindTexture(var1.getLocationSkin());
      Render var2 = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
      RenderPlayer var3 = (RenderPlayer)var2;
      if (!var1.isInvisible()) {
         GlStateManager.disableCull();
         this.renderRightArm(var3);
         this.renderLeftArm(var3);
         GlStateManager.enableCull();
      }
   }
}
