package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.stream.IngestServerTester$1;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook$EnumFlags;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.render.AabbFrame;

public class RenderBoat extends Render<EntityBoat> {
   public AabbFrame field_0001;
   public static ResourceLocation boatTextures = new ResourceLocation("textures/entity/boat.png");
   public IngestServerTester$1 field_0004;
   public S08PacketPlayerPosLook$EnumFlags field_0000;
   public ModelBase modelBoat = new ModelBoat();

   public void doRender(EntityBoat var1, double var2, double var4, double var6, float var8, float var9) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)var2, (float)var4 + 0.25F, (float)var6);
      GlStateManager.rotate(180.0F - var8, 0.0F, 1.0F, 0.0F);
      float var10 = var1.getTimeSinceHit() - var9;
      float var11 = var1.getDamageTaken() - var9;
      if (var11 < 0.0F) {
         var11 = 0.0F;
      }

      if (var10 > 0.0F) {
         GlStateManager.rotate(MathHelper.sin(var10) * var10 * var11 / 10.0F * var1.getForwardDirection(), 1.0F, 0.0F, 0.0F);
      }

      float var12 = 0.75F;
      GlStateManager.scale(var12, var12, var12);
      GlStateManager.scale(1.0F / var12, 1.0F / var12, 1.0F / var12);
      this.bindEntityTexture(var1);
      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      this.modelBoat.render(var1, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GlStateManager.popMatrix();
      super.doRender(var1, var2, var4, var6, var8, var9);
   }

   public ResourceLocation getEntityTexture(EntityBoat var1) {
      return boatTextures;
   }

   public RenderBoat(RenderManager var1) {
      super(var1);
      this.c = 0.5F;
   }
}
