package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFX extends Entity {
   public int g;
   public float h;
   public float d;
   public float i;
   public float au = 1.0F;
   public static double aw;
   public float as;
   public float e;
   public static double ay;
   public TextureAtlasSprite av;
   public static double ax;
   public int f;
   public float at;
   public int b;
   public int c;
   public float ar;

   public EntityFX(World var1, double var2, double var4, double var6) {
      super(var1);
      this.setSize(0.2F, 0.2F);
      this.b(var2, var4, var6);
      this.P = this.p = var2;
      this.Q = this.q = var4;
      this.R = this.r = var6;
      this.ar = this.as = this.at = 1.0F;
      this.d = this.V.nextFloat() * 3.0F;
      this.e = this.V.nextFloat() * 3.0F;
      this.h = (this.V.nextFloat() * 0.5F + 0.5F) * 2.0F;
      this.g = (int)(4.0F / (this.V.nextFloat() * 0.9F + 0.1F));
      this.f = 0;
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      if (this.f++ >= this.g) {
         this.setDead();
      }

      this.w = this.w - 0.04 * this.i;
      this.d(this.v, this.w, this.x);
      this.v *= 0.98F;
      this.w *= 0.98F;
      this.x *= 0.98F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
      }
   }

   public int getFXLayer() {
      return 0;
   }

   public void a(TextureAtlasSprite var1) {
      int var2 = this.getFXLayer();
      if (var2 == 1) {
         this.av = var1;
      } else {
         throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
      }
   }

   public void k(int var1) {
      if (this.getFXLayer() != 0) {
         throw new RuntimeException("Invalid call to Particle.setMiscTex");
      } else {
         this.b = var1 % 16;
         this.c = var1 / 16;
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
   }

   public void k() {
      this.b++;
   }

   public float getBlueColorF() {
      return this.at;
   }

   public EntityFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      this(var1, var2, var4, var6);
      this.v = var8 + (Math.random() * 2.0 - 1.0) * 0.4F;
      this.w = var10 + (Math.random() * 2.0 - 1.0) * 0.4F;
      this.x = var12 + (Math.random() * 2.0 - 1.0) * 0.4F;
      float var14 = (float)(Math.random() + Math.random() + 1.0) * 0.15F;
      float var15 = MathHelper.sqrt_double(this.v * this.v + this.w * this.w + this.x * this.x);
      this.v = this.v / var15 * var14 * 0.4F;
      this.w = this.w / var15 * var14 * 0.4F + 0.1F;
      this.x = this.x / var15 * var14 * 0.4F;
   }

   @Override
   public void k_() {
   }

   @Override
   public boolean l_() {
      return false;
   }

   public float getGreenColorF() {
      return this.as;
   }

   public EntityFX multipleParticleScaleBy(float var1) {
      this.setSize(0.2F * var1, 0.2F * var1);
      this.h *= var1;
      return this;
   }

   public float getRedColorF() {
      return this.ar;
   }

   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = this.b / 16.0F;
      float var10 = var9 + 0.0624375F;
      float var11 = this.c / 16.0F;
      float var12 = var11 + 0.0624375F;
      float var13 = 0.1F * this.h;
      if (this.av != null) {
         var9 = this.av.getMinU();
         var10 = this.av.getMaxU();
         var11 = this.av.getMinV();
         var12 = this.av.getMaxV();
      }

      float var14 = (float)(this.p + (this.s - this.p) * var3 - aw);
      float var15 = (float)(this.q + (this.t - this.q) * var3 - ax);
      float var16 = (float)(this.r + (this.u - this.r) * var3 - ay);
      int var17 = this.b_(var3);
      int var18 = var17 >> 16 & 65535;
      int var19 = var17 & 65535;
      var1.pos(var14 - var4 * var13 - var7 * var13, var15 - var5 * var13, var16 - var6 * var13 - var8 * var13)
         .tex(var10, var12)
         .color(this.ar, this.as, this.at, this.au)
         .lightmap(var18, var19)
         .endVertex();
      var1.pos(var14 - var4 * var13 + var7 * var13, var15 + var5 * var13, var16 - var6 * var13 + var8 * var13)
         .tex(var10, var11)
         .color(this.ar, this.as, this.at, this.au)
         .lightmap(var18, var19)
         .endVertex();
      var1.pos(var14 + var4 * var13 + var7 * var13, var15 + var5 * var13, var16 + var6 * var13 + var8 * var13)
         .tex(var9, var11)
         .color(this.ar, this.as, this.at, this.au)
         .lightmap(var18, var19)
         .endVertex();
      var1.pos(var14 + var4 * var13 - var7 * var13, var15 - var5 * var13, var16 + var6 * var13 - var8 * var13)
         .tex(var9, var12)
         .color(this.ar, this.as, this.at, this.au)
         .lightmap(var18, var19)
         .endVertex();
   }

   @Override
   public boolean r_() {
      return false;
   }

   public float method_10059() {
      return this.au;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
   }

   public EntityFX multiplyVelocity(float var1) {
      this.v *= var1;
      this.w = (this.w - 0.1F) * var1 + 0.1F;
      this.x *= var1;
      return this;
   }

   public void b(float var1, float var2, float var3) {
      this.ar = var1;
      this.as = var2;
      this.at = var3;
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName()
         + ", Pos ("
         + this.s
         + ","
         + this.t
         + ","
         + this.u
         + "), RGBA ("
         + this.ar
         + ","
         + this.as
         + ","
         + this.at
         + ","
         + this.au
         + "), Age "
         + this.f;
   }

   public void i(float var1) {
      if (this.au == 1.0F && var1 < 1.0F) {
         Minecraft.getMinecraft().effectRenderer.moveToAlphaLayer(this);
      } else if (this.au < 1.0F && var1 == 1.0F) {
         Minecraft.getMinecraft().effectRenderer.moveToNoAlphaLayer(this);
      }

      this.au = var1;
   }
}
