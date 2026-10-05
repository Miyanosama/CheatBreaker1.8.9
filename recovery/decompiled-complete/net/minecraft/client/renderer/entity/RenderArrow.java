package net.minecraft.client.renderer.entity;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.channel.sctp.oio.OioSctpChannel$2;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Bootstrap;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.model.ModelUtils;
import net.optifine.util.TimedEvent;
import org.lwjgl.opengl.GL11;

public class RenderArrow extends Render<EntityArrow> {
   public TimedEvent field_0001;
   public ModelUtils field_0004;
   public OioSctpChannel$2 field_0005;
   public static ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");
   public BlockDynamicLiquid field_0002;
   public Bootstrap field_0003;

   public void doRender(EntityArrow var1, double var2, double var4, double var6, float var8, float var9) {
      if (!this.method_01981(var1)) {
         this.bindEntityTexture(var1);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)var2, (float)var4, (float)var6);
         GlStateManager.rotate(var1.A + (var1.y - var1.A) * var9 - 90.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(var1.B + (var1.z - var1.B) * var9, 0.0F, 0.0F, 1.0F);
         Tessellator var10 = Tessellator.getInstance();
         WorldRenderer var11 = var10.getWorldRenderer();
         byte var12 = 0;
         float var13 = 0.0F;
         float var14 = 0.5F;
         float var15 = (0 + var12 * 10) / 32.0F;
         float var16 = (5 + var12 * 10) / 32.0F;
         float var17 = 0.0F;
         float var18 = 0.15625F;
         float var19 = (5 + var12 * 10) / 32.0F;
         float var20 = (10 + var12 * 10) / 32.0F;
         float var21 = 0.05625F;
         GlStateManager.enableRescaleNormal();
         float var22 = var1.arrowShake - var9;
         if (var22 > 0.0F) {
            float var23 = -MathHelper.sin(var22 * 3.0F) * var22;
            GlStateManager.rotate(var23, 0.0F, 0.0F, 1.0F);
         }

         GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.scale(var21, var21, var21);
         GlStateManager.translate(-4.0F, 0.0F, 0.0F);
         GL11.glNormal3f(var21, 0.0F, 0.0F);
         var11.begin(7, DefaultVertexFormats.POSITION_TEX);
         var11.pos(-7.0, -2.0, -2.0).tex(var17, var19).endVertex();
         var11.pos(-7.0, -2.0, 2.0).tex(var18, var19).endVertex();
         var11.pos(-7.0, 2.0, 2.0).tex(var18, var20).endVertex();
         var11.pos(-7.0, 2.0, -2.0).tex(var17, var20).endVertex();
         var10.draw();
         GL11.glNormal3f(-var21, 0.0F, 0.0F);
         var11.begin(7, DefaultVertexFormats.POSITION_TEX);
         var11.pos(-7.0, 2.0, -2.0).tex(var17, var19).endVertex();
         var11.pos(-7.0, 2.0, 2.0).tex(var18, var19).endVertex();
         var11.pos(-7.0, -2.0, 2.0).tex(var18, var20).endVertex();
         var11.pos(-7.0, -2.0, -2.0).tex(var17, var20).endVertex();
         var10.draw();

         for (int var24 = 0; var24 < 4; var24++) {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, var21);
            var11.begin(7, DefaultVertexFormats.POSITION_TEX);
            var11.pos(-8.0, -2.0, 0.0).tex(var13, var15).endVertex();
            var11.pos(8.0, -2.0, 0.0).tex(var14, var15).endVertex();
            var11.pos(8.0, 2.0, 0.0).tex(var14, var16).endVertex();
            var11.pos(-8.0, 2.0, 0.0).tex(var13, var16).endVertex();
            var10.draw();
         }

         GlStateManager.disableRescaleNormal();
         GlStateManager.popMatrix();
         super.doRender(var1, var2, var4, var6, var8, var9);
      }
   }

   public ResourceLocation getEntityTexture(EntityArrow var1) {
      return arrowTextures;
   }

   public boolean method_01981(EntityArrow var1) {
      boolean var2 = var1.method_24577();
      boolean var3 = var1.v != 0.0 || var1.w != 0.0 || var1.x != 0.0;
      return CheatBreaker.getInstance().getGlobalSettings().field_0057.method_08908() && var3 && !var2
         || CheatBreaker.getInstance().getGlobalSettings().field_0098.method_08908() && var2;
   }

   public RenderArrow(RenderManager var1) {
      super(var1);
   }
}
