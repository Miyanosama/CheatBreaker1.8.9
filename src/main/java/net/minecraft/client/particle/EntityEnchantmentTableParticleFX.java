package net.minecraft.client.particle;

import net.minecraft.world.World;

public class EntityEnchantmentTableParticleFX extends EntityFX {
   public double coordX;
   public double coordZ;
   public double coordY;
   public float field_70565_a;

   @Override
   public float a_(float var1) {
      float var2 = super.a_(var1);
      float var3 = (float)this.f / this.g;
      var3 *= var3;
      var3 *= var3;
      return var2 * (1.0F - var3) + var3;
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      float var1 = (float)this.f / this.g;
      var1 = 1.0F - var1;
      float var2 = 1.0F - var1;
      var2 *= var2;
      var2 *= var2;
      this.s = this.coordX + this.v * var1;
      this.t = this.coordY + this.w * var1 - var2 * 1.2F;
      this.u = this.coordZ + this.x * var1;
      if (this.f++ >= this.g) {
         this.setDead();
      }
   }

   public EntityEnchantmentTableParticleFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.v = var8;
      this.w = var10;
      this.x = var12;
      this.coordX = var2;
      this.coordY = var4;
      this.coordZ = var6;
      this.s = this.p = var2 + var8;
      this.t = this.q = var4 + var10;
      this.u = this.r = var6 + var12;
      float var14 = this.V.nextFloat() * 0.6F + 0.4F;
      this.field_70565_a = this.h = this.V.nextFloat() * 0.5F + 0.2F;
      this.ar = this.as = this.at = 1.0F * var14;
      this.as *= 0.9F;
      this.ar *= 0.9F;
      this.g = (int)(Math.random() * 10.0) + 30;
      this.T = true;
      this.k((int)(Math.random() * 26.0 + 1.0 + 224.0));
   }

   @Override
   public int b_(float var1) {
      int var2 = super.b_(var1);
      float var3 = (float)this.f / this.g;
      var3 *= var3;
      var3 *= var3;
      int var4 = var2 & 0xFF;
      int var5 = var2 >> 16 & 0xFF;
      var5 += (int)(var3 * 15.0F * 16.0F);
      if (var5 > 240) {
         var5 = 240;
      }

      return var4 | var5 << 16;
   }

   public static class EnchantmentTable implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
         return new EntityEnchantmentTableParticleFX(var2, var3, var5, var7, var9, var11, var13);
      }
   }
}
