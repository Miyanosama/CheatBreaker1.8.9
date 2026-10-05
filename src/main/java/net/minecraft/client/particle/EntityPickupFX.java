package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.src.Config;
import net.minecraft.world.World;
import net.optifine.shaders.Program;
import net.optifine.shaders.Shaders;

public class EntityPickupFX extends EntityFX {
   public float field_174841_aA;
   public int maxAge;
   public Entity field_174840_a;
   public Entity field_174843_ax;
   public RenderManager field_174842_aB = Minecraft.getMinecraft().getRenderManager();
   public int age;

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      Program var9 = null;
      if (Config.isShaders()) {
         var9 = Shaders.activeProgram;
         Shaders.nextEntity(this.field_174840_a);
      }

      float var10 = (this.age + var3) / this.maxAge;
      var10 *= var10;
      double var11 = this.field_174840_a.s;
      double var13 = this.field_174840_a.t;
      double var15 = this.field_174840_a.u;
      double var17 = this.field_174843_ax.P + (this.field_174843_ax.s - this.field_174843_ax.P) * var3;
      double var19 = this.field_174843_ax.Q + (this.field_174843_ax.t - this.field_174843_ax.Q) * var3 + this.field_174841_aA;
      double var21 = this.field_174843_ax.R + (this.field_174843_ax.u - this.field_174843_ax.R) * var3;
      double var23 = var11 + (var17 - var11) * var10;
      double var25 = var13 + (var19 - var13) * var10;
      double var27 = var15 + (var21 - var15) * var10;
      int var29 = this.b_(var3);
      int var30 = var29 % 65536;
      int var31 = var29 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var30 / 1.0F, var31 / 1.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      var23 -= aw;
      var25 -= ax;
      var27 -= ay;
      this.field_174842_aB.renderEntityWithPosYaw(this.field_174840_a, (float)var23, (float)var25, (float)var27, this.field_174840_a.y, var3);
      if (Config.isShaders()) {
         Shaders.setEntityId((Entity)null);
         Shaders.useProgram(var9);
      }
   }

   @Override
   public int getFXLayer() {
      return 3;
   }

   public EntityPickupFX(World var1, Entity var2, Entity var3, float var4) {
      super(var1, var2.s, var2.t, var2.u, var2.v, var2.w, var2.x);
      this.field_174840_a = var2;
      this.field_174843_ax = var3;
      this.maxAge = 3;
      this.field_174841_aA = var4;
   }

   @Override
   public void onUpdate() {
      this.age++;
      if (this.age == this.maxAge) {
         this.setDead();
      }
   }
}
