package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public interface ISound {
   float h();

   ISound$AttenuationType getAttenuationType();

   float getVolume();

   float i();

   boolean canRepeat();

   int getRepeatDelay();

   float getPitch();

   ResourceLocation getSoundLocation();

   float g();
}
