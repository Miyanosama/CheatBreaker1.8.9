package net.minecraft.client.audio;

import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.ResourceLocation;

public class GuardianSound extends MovingSound {
   public RenderHorse field_0000;
   public EntityGuardian guardian;

   public GuardianSound(EntityGuardian var1) {
      super(new ResourceLocation("minecraft:mob.guardian.attack"));
      this.guardian = var1;
      this.i = ISound$AttenuationType.NONE;
      this.g = true;
      this.h = 0;
   }

   @Override
   public void update() {
      if (!this.guardian.I && this.guardian.hasTargetedEntity()) {
         this.d = (float)this.guardian.s;
         this.e = (float)this.guardian.t;
         this.f = (float)this.guardian.u;
         float var1 = this.guardian.func_175477_p(0.0F);
         this.volume = 0.0F + 1.0F * var1 * var1;
         this.pitch = 0.7F + 0.5F * var1;
      } else {
         this.donePlaying = true;
      }
   }
}
