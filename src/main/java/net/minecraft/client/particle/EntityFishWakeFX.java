package net.minecraft.client.particle;

import net.minecraft.world.World;

public class EntityFishWakeFX extends EntityFX {
   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.w = this.w - this.i;
      this.d(this.v, this.w, this.x);
      this.v *= 0.98F;
      this.w *= 0.98F;
      this.x *= 0.98F;
      int var1 = 60 - this.g;
      float var2 = var1 * 0.001F;
      this.setSize(var2, var2);
      this.k(19 + var1 % 4);
      if (this.g-- <= 0) {
         this.setDead();
      }
   }

   public EntityFishWakeFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.v *= 0.3F;
      this.w = Math.random() * 0.2F + 0.1F;
      this.x *= 0.3F;
      this.ar = 1.0F;
      this.as = 1.0F;
      this.at = 1.0F;
      this.k(19);
      this.setSize(0.01F, 0.01F);
      this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2));
      this.i = 0.0F;
      this.v = var8;
      this.w = var10;
      this.x = var12;
   }

   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
         return new EntityFishWakeFX(var2, var3, var5, var7, var9, var11, var13);
      }
   }
}
