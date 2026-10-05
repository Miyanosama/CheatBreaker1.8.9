package net.minecraft.client.audio;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class MovingSoundMinecart extends MovingSound {
   public float distance = 0.0F;
   public EntityMinecart minecart;

   public MovingSoundMinecart(EntityMinecart var1) {
      super(new ResourceLocation("minecraft:minecart.base"));
      this.minecart = var1;
      this.g = true;
      this.h = 0;
   }

   @Override
   public void update() {
      if (this.minecart.I) {
         this.donePlaying = true;
      } else {
         this.d = (float)this.minecart.s;
         this.e = (float)this.minecart.t;
         this.f = (float)this.minecart.u;
         float var1 = MathHelper.sqrt_double(this.minecart.v * this.minecart.v + this.minecart.x * this.minecart.x);
         if (var1 >= 0.01) {
            this.distance = MathHelper.clamp_float(this.distance + 0.0025F, 0.0F, 1.0F);
            this.volume = 0.0F + MathHelper.clamp_float(var1, 0.0F, 0.5F) * 0.7F;
         } else {
            this.distance = 0.0F;
            this.volume = 0.0F;
         }
      }
   }
}
