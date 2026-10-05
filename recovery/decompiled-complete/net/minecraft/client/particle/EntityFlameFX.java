package net.minecraft.client.particle;

import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.command.CommandResultStats;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilSaveConverter$1;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stones;
import net.optifine.player.PlayerItemsLayer;

public class EntityFlameFX extends EntityFX {
   public AnvilSaveConverter$1 field_0003;
   public PlayerItemsLayer field_0005;
   public CommandResultStats field_0002;
   public Item field_0004;
   public float flameScale;
   public PositionedSound field_0001;
   public StructureStrongholdPieces$Stones field_0006;

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      if (this.f++ >= this.g) {
         this.setDead();
      }

      this.d(this.v, this.w, this.x);
      this.v *= 0.96F;
      this.w *= 0.96F;
      this.x *= 0.96F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
      }
   }

   @Override
   public int b_(float var1) {
      float var2 = (this.f + var1) / this.g;
      var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
      int var3 = super.b_(var1);
      int var4 = var3 & 0xFF;
      int var5 = var3 >> 16 & 0xFF;
      var4 += (int)(var2 * 15.0F * 16.0F);
      if (var4 > 240) {
         var4 = 240;
      }

      return var4 | var5 << 16;
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = (this.f + var3) / this.g;
      this.h = this.flameScale * (1.0F - var9 * var9 * 0.5F);
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   @Override
   public float a_(float var1) {
      float var2 = (this.f + var1) / this.g;
      var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
      float var3 = super.a_(var1);
      return var3 * var2 + (1.0F - var2);
   }

   public EntityFlameFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.v = this.v * 0.01F + var8;
      this.w = this.w * 0.01F + var10;
      this.x = this.x * 0.01F + var12;
      this.s = this.s + (this.V.nextFloat() - this.V.nextFloat()) * 0.05F;
      this.t = this.t + (this.V.nextFloat() - this.V.nextFloat()) * 0.05F;
      this.u = this.u + (this.V.nextFloat() - this.V.nextFloat()) * 0.05F;
      this.flameScale = this.h;
      this.ar = this.as = this.at = 1.0F;
      this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2)) + 4;
      this.T = true;
      this.k(48);
   }
}
