package net.minecraft.client.particle;

import net.minecraft.block.BlockNewLeaf$1;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLavaFX extends EntityFX {
   public float lavaParticleScale;
   public ResourcePackListEntryDefault field_0002;
   public BlockNewLeaf$1 field_0000;

   @Override
   public int b_(float var1) {
      float var2 = (this.f + var1) / this.g;
      var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
      int var3 = super.b_(var1);
      short var4 = 240;
      int var5 = var3 >> 16 & 0xFF;
      return var4 | var5 << 16;
   }

   @Override
   public float a_(float var1) {
      return 1.0F;
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      if (this.f++ >= this.g) {
         this.setDead();
      }

      float var1 = (float)this.f / this.g;
      if (this.V.nextFloat() > var1) {
         this.o.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.s, this.t, this.u, this.v, this.w, this.x);
      }

      this.w -= 0.03;
      this.d(this.v, this.w, this.x);
      this.v *= 0.999F;
      this.w *= 0.999F;
      this.x *= 0.999F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
      }
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = (this.f + var3) / this.g;
      this.h = this.lavaParticleScale * (1.0F - var9 * var9);
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public EntityLavaFX(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.v *= 0.8F;
      this.w *= 0.8F;
      this.x *= 0.8F;
      this.w = this.V.nextFloat() * 0.4F + 0.05F;
      this.ar = this.as = this.at = 1.0F;
      this.h = this.h * (this.V.nextFloat() * 2.0F + 0.2F);
      this.lavaParticleScale = this.h;
      this.g = (int)(16.0 / (Math.random() * 0.8 + 0.2));
      this.T = false;
      this.k(49);
   }
}
