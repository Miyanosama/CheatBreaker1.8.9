package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public interface ISound {
   float h();

   ISound.AttenuationType getAttenuationType();

   float getVolume();

   float i();

   boolean canRepeat();

   int getRepeatDelay();

   float getPitch();

   ResourceLocation getSoundLocation();

   float g();

   public static enum AttenuationType {
      NONE(0),
      LINEAR(2);

      public int type;

      public int getTypeInt() {
         return this.type;
      }

      AttenuationType(int var3) {
         this.type = var3;
      }
   }
}
