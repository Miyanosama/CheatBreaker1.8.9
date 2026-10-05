package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityFirework_SparkFX extends EntityFX {
   public EffectRenderer field_92047_az;
   public boolean twinkle;
   public boolean hasFadeColour;
   public float fadeColourRed;
   public float fadeColourGreen;
   public int baseTextureIndex = 160;
   public float fadeColourBlue;
   public boolean trail;

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      if (this.f++ >= this.g) {
         this.setDead();
      }

      if (this.f > this.g / 2) {
         this.i(1.0F - ((float)this.f - this.g / 2) / this.g);
         if (this.hasFadeColour) {
            this.ar = this.ar + (this.fadeColourRed - this.ar) * 0.2F;
            this.as = this.as + (this.fadeColourGreen - this.as) * 0.2F;
            this.at = this.at + (this.fadeColourBlue - this.at) * 0.2F;
         }
      }

      this.k(this.baseTextureIndex + (7 - this.f * 8 / this.g));
      this.w -= 0.004;
      this.d(this.v, this.w, this.x);
      this.v *= 0.91F;
      this.w *= 0.91F;
      this.x *= 0.91F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
      }

      if (this.trail && this.f < this.g / 2 && (this.f + this.g) % 2 == 0) {
         EntityFirework_SparkFX var1 = new EntityFirework_SparkFX(this.o, this.s, this.t, this.u, 0.0, 0.0, 0.0, this.field_92047_az);
         var1.i(0.99F);
         var1.b(this.ar, this.as, this.at);
         var1.f = var1.g / 2;
         if (this.hasFadeColour) {
            var1.hasFadeColour = true;
            var1.fadeColourRed = this.fadeColourRed;
            var1.fadeColourGreen = this.fadeColourGreen;
            var1.fadeColourBlue = this.fadeColourBlue;
         }

         var1.twinkle = this.twinkle;
         this.field_92047_az.addEffect(var1);
      }
   }

   @Override
   public float a_(float var1) {
      return 1.0F;
   }

   public void setFadeColour(int var1) {
      this.fadeColourRed = ((var1 & 0xFF0000) >> 16) / 255.0F;
      this.fadeColourGreen = ((var1 & 0xFF00) >> 8) / 255.0F;
      this.fadeColourBlue = ((var1 & 0xFF) >> 0) / 255.0F;
      this.hasFadeColour = true;
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (!this.twinkle || this.f < this.g / 3 || (this.f + this.g) / 3 % 2 == 0) {
         super.renderParticle(var1, var2, var3, var4, var5, var6, var7, var8);
      }
   }

   public void setTwinkle(boolean var1) {
      this.twinkle = var1;
   }

   public void setTrail(boolean var1) {
      this.trail = var1;
   }

   public EntityFirework_SparkFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, EffectRenderer var14) {
      super(var1, var2, var4, var6);
      this.v = var8;
      this.w = var10;
      this.x = var12;
      this.field_92047_az = var14;
      this.h *= 0.75F;
      this.g = 48 + this.V.nextInt(12);
      this.T = false;
   }

   public void setColour(int var1) {
      float var2 = ((var1 & 0xFF0000) >> 16) / 255.0F;
      float var3 = ((var1 & 0xFF00) >> 8) / 255.0F;
      float var4 = ((var1 & 0xFF) >> 0) / 255.0F;
      float var5 = 1.0F;
      this.b(var2 * var5, var3 * var5, var4 * var5);
   }

   @Override
   public boolean m_() {
      return false;
   }

   @Override
   public AxisAlignedBB t_() {
      return null;
   }

   @Override
   public int b_(float var1) {
      return 15728880;
   }
}
