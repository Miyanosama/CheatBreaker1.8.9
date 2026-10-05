package net.minecraft.client.particle;

import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import net.minecraft.world.World;
import org.apache.log4j.pattern.LineLocationPatternConverter;

public class EntityAuraFX extends EntityFX {
   public LineLocationPatternConverter field_0000;
   public ProtobufVarint32LengthFieldPrepender field_0001;

   public EntityAuraFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      float var14 = this.V.nextFloat() * 0.1F + 0.2F;
      this.ar = var14;
      this.as = var14;
      this.at = var14;
      this.k(0);
      this.setSize(0.02F, 0.02F);
      this.h = this.h * (this.V.nextFloat() * 0.6F + 0.5F);
      this.v *= 0.02F;
      this.w *= 0.02F;
      this.x *= 0.02F;
      this.g = (int)(20.0 / (Math.random() * 0.8 + 0.2));
      this.T = true;
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.d(this.v, this.w, this.x);
      this.v *= 0.99;
      this.w *= 0.99;
      this.x *= 0.99;
      if (this.g-- <= 0) {
         this.setDead();
      }
   }
}
