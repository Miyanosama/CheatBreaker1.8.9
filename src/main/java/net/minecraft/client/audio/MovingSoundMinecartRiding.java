package net.minecraft.client.audio;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class MovingSoundMinecartRiding extends MovingSound {
   public EntityPlayer player;
   public EntityMinecart minecart;

   public MovingSoundMinecartRiding(EntityPlayer var1, EntityMinecart var2) {
      super(new ResourceLocation("minecraft:minecart.inside"));
      this.player = var1;
      this.minecart = var2;
      this.i = ISound.AttenuationType.NONE;
      this.g = true;
      this.h = 0;
   }

   @Override
   public void update() {
      if (!this.minecart.I && this.player.au() && this.player.m == this.minecart) {
         float var1 = MathHelper.sqrt_double(this.minecart.v * this.minecart.v + this.minecart.x * this.minecart.x);
         if (var1 >= 0.01) {
            this.volume = 0.0F + MathHelper.clamp_float(var1, 0.0F, 1.0F) * 0.75F;
         } else {
            this.volume = 0.0F;
         }
      } else {
         this.donePlaying = true;
      }
   }
}
