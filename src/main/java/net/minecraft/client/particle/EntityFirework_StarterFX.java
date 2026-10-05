package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFirework_StarterFX extends EntityFX {
   public boolean twinkle;
   public EffectRenderer theEffectRenderer;
   public NBTTagList fireworkExplosions;
   public int fireworkAge;

   public EntityFirework_StarterFX(
      World var1, double var2, double var4, double var6, double var8, double var10, double var12, EffectRenderer var14, NBTTagCompound var15
   ) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.v = var8;
      this.w = var10;
      this.x = var12;
      this.theEffectRenderer = var14;
      this.g = 8;
      if (var15 != null) {
         this.fireworkExplosions = var15.getTagList("Explosions", 10);
         if (this.fireworkExplosions.tagCount() == 0) {
            this.fireworkExplosions = null;
         } else {
            this.g = this.fireworkExplosions.tagCount() * 2 - 1;

            for (int var16 = 0; var16 < this.fireworkExplosions.tagCount(); var16++) {
               NBTTagCompound var17 = this.fireworkExplosions.getCompoundTagAt(var16);
               if (var17.getBoolean("Flicker")) {
                  this.twinkle = true;
                  this.g += 15;
                  break;
               }
            }
         }
      }
   }

   @Override
   public void onUpdate() {
      if (this.fireworkAge == 0 && this.fireworkExplosions != null) {
         boolean var1 = this.func_92037_i();
         boolean var2 = false;
         if (this.fireworkExplosions.tagCount() >= 3) {
            var2 = true;
         } else {
            for (int var3 = 0; var3 < this.fireworkExplosions.tagCount(); var3++) {
               NBTTagCompound var4 = this.fireworkExplosions.getCompoundTagAt(var3);
               if (var4.getByte("Type") == 1) {
                  var2 = true;
                  break;
               }
            }
         }

         String var17 = "fireworks." + (var2 ? "largeBlast" : "blast") + (var1 ? "_far" : "");
         this.o.playSound(this.s, this.t, this.u, var17, 20.0F, 0.95F + this.V.nextFloat() * 0.1F, true);
      }

      if (this.fireworkAge % 2 == 0 && this.fireworkExplosions != null && this.fireworkAge / 2 < this.fireworkExplosions.tagCount()) {
         int var13 = this.fireworkAge / 2;
         NBTTagCompound var15 = this.fireworkExplosions.getCompoundTagAt(var13);
         byte var18 = var15.getByte("Type");
         boolean var19 = var15.getBoolean("Trail");
         boolean var5 = var15.getBoolean("Flicker");
         int[] var6 = var15.getIntArray("Colors");
         int[] var7 = var15.getIntArray("FadeColors");
         if (var6.length == 0) {
            var6 = new int[]{ItemDye.dyeColors[0]};
         }

         if (var18 == 1) {
            this.createBall(0.5, 4, var6, var7, var19, var5);
         } else if (var18 == 2) {
            this.createShaped(
               0.5,
               new double[][]{
                  {0.0, 1.0},
                  {0.3455, 0.309},
                  {0.9511, 0.309},
                  {0.3795918367346939, -0.12653061224489795},
                  {0.6122448979591837, -0.8040816326530612},
                  {0.0, -0.35918367346938773}
               },
               var6,
               var7,
               var19,
               var5,
               false
            );
         } else if (var18 == 3) {
            this.createShaped(
               0.5,
               new double[][]{
                  {0.0, 0.2},
                  {0.2, 0.2},
                  {0.2, 0.6},
                  {0.6, 0.6},
                  {0.6, 0.2},
                  {0.2, 0.2},
                  {0.2, 0.0},
                  {0.4, 0.0},
                  {0.4, -0.6},
                  {0.2, -0.6},
                  {0.2, -0.4},
                  {0.0, -0.4}
               },
               var6,
               var7,
               var19,
               var5,
               true
            );
         } else if (var18 == 4) {
            this.createBurst(var6, var7, var19, var5);
         } else {
            this.createBall(0.25, 2, var6, var7, var19, var5);
         }

         int var8 = var6[0];
         float var9 = ((var8 & 0xFF0000) >> 16) / 255.0F;
         float var10 = ((var8 & 0xFF00) >> 8) / 255.0F;
         float var11 = ((var8 & 0xFF) >> 0) / 255.0F;
         EntityFirework_OverlayFX var12 = new EntityFirework_OverlayFX(this.o, this.s, this.t, this.u);
         var12.b(var9, var10, var11);
         this.theEffectRenderer.addEffect(var12);
      }

      this.fireworkAge++;
      if (this.fireworkAge > this.g) {
         if (this.twinkle) {
            boolean var14 = this.func_92037_i();
            String var16 = "fireworks." + (var14 ? "twinkle_far" : "twinkle");
            this.o.playSound(this.s, this.t, this.u, var16, 20.0F, 0.9F + this.V.nextFloat() * 0.15F, true);
         }

         this.setDead();
      }
   }

   public void createParticle(
      double var1, double var3, double var5, double var7, double var9, double var11, int[] var13, int[] var14, boolean var15, boolean var16
   ) {
      EntityFirework_SparkFX var17 = new EntityFirework_SparkFX(this.o, var1, var3, var5, var7, var9, var11, this.theEffectRenderer);
      var17.i(0.99F);
      var17.setTrail(var15);
      var17.setTwinkle(var16);
      int var18 = this.V.nextInt(var13.length);
      var17.setColour(var13[var18]);
      if (var14 != null && var14.length > 0) {
         var17.setFadeColour(var14[this.V.nextInt(var14.length)]);
      }

      this.theEffectRenderer.addEffect(var17);
   }

   public void createBurst(int[] var1, int[] var2, boolean var3, boolean var4) {
      double var5 = this.V.nextGaussian() * 0.05;
      double var7 = this.V.nextGaussian() * 0.05;

      for (int var9 = 0; var9 < 70; var9++) {
         double var10 = this.v * 0.5 + this.V.nextGaussian() * 0.15 + var5;
         double var12 = this.x * 0.5 + this.V.nextGaussian() * 0.15 + var7;
         double var14 = this.w * 0.5 + this.V.nextDouble() * 0.5;
         this.createParticle(this.s, this.t, this.u, var10, var14, var12, var1, var2, var3, var4);
      }
   }

   public void createShaped(double var1, double[][] var3, int[] var4, int[] var5, boolean var6, boolean var7, boolean var8) {
      double var9 = var3[0][0];
      double var11 = var3[0][1];
      this.createParticle(this.s, this.t, this.u, var9 * var1, var11 * var1, 0.0, var4, var5, var6, var7);
      float var13 = this.V.nextFloat() * (float) Math.PI;
      double var14 = var8 ? 0.034 : 0.34;

      for (int var16 = 0; var16 < 3; var16++) {
         double var17 = var13 + var16 * (float) Math.PI * var14;
         double var19 = var9;
         double var21 = var11;

         for (int var23 = 1; var23 < var3.length; var23++) {
            double var24 = var3[var23][0];
            double var26 = var3[var23][1];

            for (double var28 = 0.25; var28 <= 1.0; var28 += 0.25) {
               double var30 = (var19 + (var24 - var19) * var28) * var1;
               double var32 = (var21 + (var26 - var21) * var28) * var1;
               double var34 = var30 * Math.sin(var17);
               var30 *= Math.cos(var17);

               for (double var36 = -1.0; var36 <= 1.0; var36 += 2.0) {
                  this.createParticle(this.s, this.t, this.u, var30 * var36, var32, var34 * var36, var4, var5, var6, var7);
               }
            }

            var19 = var24;
            var21 = var26;
         }
      }
   }

   @Override
   public int getFXLayer() {
      return 0;
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
   }

   public boolean func_92037_i() {
      Minecraft var1 = Minecraft.getMinecraft();
      return var1 == null || var1.getRenderViewEntity() == null || var1.getRenderViewEntity().e(this.s, this.t, this.u) >= 256.0;
   }

   public void createBall(double var1, int var3, int[] var4, int[] var5, boolean var6, boolean var7) {
      double var8 = this.s;
      double var10 = this.t;
      double var12 = this.u;

      for (int var14 = -var3; var14 <= var3; var14++) {
         for (int var15 = -var3; var15 <= var3; var15++) {
            for (int var16 = -var3; var16 <= var3; var16++) {
               double var17 = var15 + (this.V.nextDouble() - this.V.nextDouble()) * 0.5;
               double var19 = var14 + (this.V.nextDouble() - this.V.nextDouble()) * 0.5;
               double var21 = var16 + (this.V.nextDouble() - this.V.nextDouble()) * 0.5;
               double var23 = MathHelper.sqrt_double(var17 * var17 + var19 * var19 + var21 * var21) / var1 + this.V.nextGaussian() * 0.05;
               this.createParticle(var8, var10, var12, var17 / var23, var19 / var23, var21 / var23, var4, var5, var6, var7);
               if (var14 != -var3 && var14 != var3 && var15 != -var3 && var15 != var3) {
                  var16 += var3 * 2 - 1;
               }
            }
         }
      }
   }
}
