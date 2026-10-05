package net.minecraft.client.particle;

import io.netty.handler.codec.http.cors.CorsConfig$1;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.slf4j.MarkerFactory;

public class EntityNoteFX extends EntityFX {
   public CorsConfig$1 field_0001;
   public float noteParticleScale;
   public ClientBrandRetriever field_0000;
   public MarkerFactory field_0002;

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = (this.f + var3) / this.g * 32.0F;
      var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
      this.h = this.noteParticleScale * var9;
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public EntityNoteFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.v *= 0.01F;
      this.w *= 0.01F;
      this.x *= 0.01F;
      this.w += 0.2;
      this.ar = MathHelper.sin(((float)var8 + 0.0F) * (float) Math.PI * 2.0F) * 0.65F + 0.35F;
      this.as = MathHelper.sin(((float)var8 + 0.33333334F) * (float) Math.PI * 2.0F) * 0.65F + 0.35F;
      this.at = MathHelper.sin(((float)var8 + 0.6666667F) * (float) Math.PI * 2.0F) * 0.65F + 0.35F;
      this.h *= 0.75F;
      this.h *= var14;
      this.noteParticleScale = this.h;
      this.g = 6;
      this.T = false;
      this.k(64);
   }

   public EntityNoteFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      this(var1, var2, var4, var6, var8, var10, var12, 2.0F);
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      if (this.f++ >= this.g) {
         this.setDead();
      }

      this.d(this.v, this.w, this.x);
      if (this.t == this.q) {
         this.v *= 1.1;
         this.x *= 1.1;
      }

      this.v *= 0.66F;
      this.w *= 0.66F;
      this.x *= 0.66F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
      }
   }
}
