package net.minecraft.client.particle;

import io.netty.channel.epoll.IovArray$1;
import io.netty.handler.codec.marshalling.ChannelBufferByteInput;
import io.netty.handler.traffic.GlobalTrafficShapingHandler$1;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.stream.MetadataCombat;
import net.minecraft.creativetab.CreativeTabs$4;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHeartFX extends EntityFX {
   public GlobalTrafficShapingHandler$1 field_0003;
   public IovArray$1 field_0005;
   public MetadataCombat field_0002;
   public float particleScaleOverTime;
   public ChannelBufferByteInput field_0000;
   public CreativeTabs$4 field_0001;

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

      this.v *= 0.86F;
      this.w *= 0.86F;
      this.x *= 0.86F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
      }
   }

   public EntityHeartFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      this(var1, var2, var4, var6, var8, var10, var12, 2.0F);
   }

   public EntityHeartFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
      this.v *= 0.01F;
      this.w *= 0.01F;
      this.x *= 0.01F;
      this.w += 0.1;
      this.h *= 0.75F;
      this.h *= var14;
      this.particleScaleOverTime = this.h;
      this.g = 16;
      this.T = false;
      this.k(80);
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = (this.f + var3) / this.g * 32.0F;
      var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
      this.h = this.particleScaleOverTime * var9;
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
