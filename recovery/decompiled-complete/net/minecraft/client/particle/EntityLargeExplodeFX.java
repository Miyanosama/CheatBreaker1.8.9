package net.minecraft.client.particle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityLargeExplodeFX extends EntityFX {
   public static ResourceLocation EXPLOSION_TEXTURE = new ResourceLocation("textures/entity/explosion.png");
   public TextureManager theRenderEngine;
   public int field_70581_a;
   public float field_70582_as;
   public static VertexFormat field_181549_az = new VertexFormat()
      .addElement(DefaultVertexFormats.POSITION_3F)
      .addElement(DefaultVertexFormats.TEX_2F)
      .addElement(DefaultVertexFormats.COLOR_4UB)
      .addElement(DefaultVertexFormats.TEX_2S)
      .addElement(DefaultVertexFormats.NORMAL_3B)
      .addElement(DefaultVertexFormats.PADDING_1B);
   public int field_70584_aq;

   @Override
   public int getFXLayer() {
      return 3;
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      int var9 = (int)((this.field_70581_a + var3) * 15.0F / this.field_70584_aq);
      if (var9 <= 15) {
         this.theRenderEngine.bindTexture(EXPLOSION_TEXTURE);
         float var10 = var9 % 4 / 4.0F;
         float var11 = var10 + 0.24975F;
         float var12 = var9 / 4 / 4.0F;
         float var13 = var12 + 0.24975F;
         float var14 = 2.0F * this.field_70582_as;
         float var15 = (float)(this.p + (this.s - this.p) * var3 - aw);
         float var16 = (float)(this.q + (this.t - this.q) * var3 - ax);
         float var17 = (float)(this.r + (this.u - this.r) * var3 - ay);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableLighting();
         RenderHelper.disableStandardItemLighting();
         var1.begin(7, field_181549_az);
         var1.pos(var15 - var4 * var14 - var7 * var14, var16 - var5 * var14, var17 - var6 * var14 - var8 * var14)
            .tex(var11, var13)
            .color(this.ar, this.as, this.at, 1.0F)
            .lightmap(0, 240)
            .normal(0.0F, 1.0F, 0.0F)
            .endVertex();
         var1.pos(var15 - var4 * var14 + var7 * var14, var16 + var5 * var14, var17 - var6 * var14 + var8 * var14)
            .tex(var11, var12)
            .color(this.ar, this.as, this.at, 1.0F)
            .lightmap(0, 240)
            .normal(0.0F, 1.0F, 0.0F)
            .endVertex();
         var1.pos(var15 + var4 * var14 + var7 * var14, var16 + var5 * var14, var17 + var6 * var14 + var8 * var14)
            .tex(var10, var12)
            .color(this.ar, this.as, this.at, 1.0F)
            .lightmap(0, 240)
            .normal(0.0F, 1.0F, 0.0F)
            .endVertex();
         var1.pos(var15 + var4 * var14 - var7 * var14, var16 - var5 * var14, var17 + var6 * var14 - var8 * var14)
            .tex(var10, var13)
            .color(this.ar, this.as, this.at, 1.0F)
            .lightmap(0, 240)
            .normal(0.0F, 1.0F, 0.0F)
            .endVertex();
         Tessellator.getInstance().draw();
         GlStateManager.enableLighting();
      }
   }

   @Override
   public int b_(float var1) {
      return 61680;
   }

   public EntityLargeExplodeFX(TextureManager var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13) {
      super(var2, var3, var5, var7, 0.0, 0.0, 0.0);
      this.theRenderEngine = var1;
      this.field_70584_aq = 6 + this.V.nextInt(4);
      this.ar = this.as = this.at = this.V.nextFloat() * 0.6F + 0.4F;
      this.field_70582_as = 1.0F - (float)var9 * 0.5F;
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.field_70581_a++;
      if (this.field_70581_a == this.field_70584_aq) {
         this.setDead();
      }
   }
}
