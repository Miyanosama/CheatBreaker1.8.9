package net.minecraft.client.renderer.entity.layers;

import net.minecraft.block.BlockStone$EnumType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderMooshroom;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class LayerMooshroomMushroom implements LayerRenderer<EntityMooshroom> {
   public ItemGlassBottle field_0003;
   public ModelRenderer modelRendererMushroom;
   public static ResourceLocation LOCATION_MUSHROOM_RED = new ResourceLocation("textures/entity/cow/mushroom_red.png");
   public static boolean hasTextureMushroom = false;
   public RenderMooshroom mooshroomRenderer;
   public BlockStone$EnumType field_0001;

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   public LayerMooshroomMushroom(RenderMooshroom var1) {
      this.mooshroomRenderer = var1;
      this.modelRendererMushroom = new ModelRenderer(this.mooshroomRenderer.f);
      this.modelRendererMushroom.setTextureSize(16, 16);
      this.modelRendererMushroom.rotationPointX = -6.0F;
      this.modelRendererMushroom.rotationPointZ = -8.0F;
      this.modelRendererMushroom.rotateAngleY = MathHelper.PI / 4.0F;
      int[][] var2 = new int[][]{null, null, {16, 16, 0, 0}, {16, 16, 0, 0}, null, null};
      this.modelRendererMushroom.addBox(var2, 0.0F, 0.0F, 10.0F, 20.0F, 16.0F, 0.0F, 0.0F);
      int[][] var3 = new int[][]{null, null, null, null, {16, 16, 0, 0}, {16, 16, 0, 0}};
      this.modelRendererMushroom.addBox(var3, 10.0F, 0.0F, 0.0F, 0.0F, 16.0F, 20.0F, 0.0F);
   }

   public static void update() {
      hasTextureMushroom = Config.hasResource(LOCATION_MUSHROOM_RED);
   }

   public void doRenderLayer(EntityMooshroom var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (!var1.o_() && !var1.isInvisible()) {
         BlockRendererDispatcher var9 = Minecraft.getMinecraft().getBlockRendererDispatcher();
         if (hasTextureMushroom) {
            this.mooshroomRenderer.a(LOCATION_MUSHROOM_RED);
         } else {
            this.mooshroomRenderer.a(TextureMap.locationBlocksTexture);
         }

         GlStateManager.enableCull();
         GlStateManager.cullFace(1028);
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0F, -1.0F, 1.0F);
         GlStateManager.translate(0.2F, 0.35F, 0.5F);
         GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.pushMatrix();
         GlStateManager.translate(-0.5F, -0.5F, 0.5F);
         if (hasTextureMushroom) {
            this.modelRendererMushroom.render(0.0625F);
         } else {
            var9.renderBlockBrightness(Blocks.red_mushroom.getDefaultState(), 1.0F);
         }

         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.1F, 0.0F, -0.6F);
         GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(-0.5F, -0.5F, 0.5F);
         if (hasTextureMushroom) {
            this.modelRendererMushroom.render(0.0625F);
         } else {
            var9.renderBlockBrightness(Blocks.red_mushroom.getDefaultState(), 1.0F);
         }

         GlStateManager.popMatrix();
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         ((ModelQuadruped)this.mooshroomRenderer.getMainModel()).a.postRender(0.0625F);
         GlStateManager.scale(1.0F, -1.0F, 1.0F);
         GlStateManager.translate(0.0F, 0.7F, -0.2F);
         GlStateManager.rotate(12.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(-0.5F, -0.5F, 0.5F);
         if (hasTextureMushroom) {
            this.modelRendererMushroom.render(0.0625F);
         } else {
            var9.renderBlockBrightness(Blocks.red_mushroom.getDefaultState(), 1.0F);
         }

         GlStateManager.popMatrix();
         GlStateManager.cullFace(1029);
         GlStateManager.disableCull();
      }
   }
}
