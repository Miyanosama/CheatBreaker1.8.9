package net.minecraft.client.particle;

import io.netty.handler.codec.spdy.SpdySessionHandler$4;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S38PacketPlayerListItem$AddPlayerData;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCrit2FX extends EntityFX {
   public RenderEnderCrystal field_0002;
   public SpdySessionHandler$4 field_0004;
   public S04PacketEntityEquipment field_0001;
   public S38PacketPlayerListItem$AddPlayerData field_0003;
   public float field_174839_a;

   public EntityCrit2FX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      this(var1, var2, var4, var6, var8, var10, var12, 1.0F);
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = (this.f + var3) / this.g * 32.0F;
      var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
      this.h = this.field_174839_a * var9;
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public EntityCrit2FX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.v *= 0.1F;
      this.w *= 0.1F;
      this.x *= 0.1F;
      this.v += var8 * 0.4;
      this.w += var10 * 0.4;
      this.x += var12 * 0.4;
      this.ar = this.as = this.at = (float)(Math.random() * 0.3F + 0.6F);
      this.h *= 0.75F;
      this.h *= var14;
      this.field_174839_a = this.h;
      this.g = (int)(6.0 / (Math.random() * 0.8 + 0.6));
      this.g = (int)(this.g * var14);
      this.T = false;
      this.k(65);
      this.onUpdate();
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
      this.as = (float)(this.as * 0.96);
      this.at = (float)(this.at * 0.9);
      this.v *= 0.7F;
      this.w *= 0.7F;
      this.x *= 0.7F;
      this.w -= 0.02F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
      }
   }
}
