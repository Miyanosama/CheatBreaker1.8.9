package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.log4j.ProvisionNode;

public class EntitySmokeFX extends EntityFX {
   public ProvisionNode field_0001;
   public float smokeParticleScale;
   public RenderBlaze field_0000;

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      if (this.f++ >= this.g) {
         this.setDead();
      }

      this.k(7 - this.f * 8 / this.g);
      this.w += 0.004;
      this.d(this.v, this.w, this.x);
      if (this.t == this.q) {
         this.v *= 1.1;
         this.x *= 1.1;
      }

      this.v *= 0.96F;
      this.w *= 0.96F;
      this.x *= 0.96F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
      }
   }

   public EntitySmokeFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      this(var1, var2, var4, var6, var8, var10, var12, 1.0F);
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = (this.f + var3) / this.g * 32.0F;
      var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
      this.h = this.smokeParticleScale * var9;
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public EntitySmokeFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.v *= 0.1F;
      this.w *= 0.1F;
      this.x *= 0.1F;
      this.v += var8;
      this.w += var10;
      this.x += var12;
      this.ar = this.as = this.at = (float)(Math.random() * 0.3F);
      this.h *= 0.75F;
      this.h *= var14;
      this.smokeParticleScale = this.h;
      this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2));
      this.g = (int)(this.g * var14);
      this.T = false;
   }
}
