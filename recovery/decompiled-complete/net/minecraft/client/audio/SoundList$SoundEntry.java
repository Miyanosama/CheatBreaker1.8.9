package net.minecraft.client.audio;

import net.minecraft.world.biome.BiomeGenHills;

public class SoundList$SoundEntry {
   public float pitch;
   public int weight;
   public BiomeGenHills field_0002;
   public float volume = 1.0F;
   public SoundList$SoundEntry$Type type;
   public boolean streaming;
   public String name;

   public SoundList$SoundEntry() {
      this.pitch = 1.0F;
      this.weight = 1;
      this.type = SoundList$SoundEntry$Type.FILE;
      this.streaming = false;
   }

   public String getSoundEntryName() {
      return this.name;
   }

   public void setSoundEntryName(String var1) {
      this.name = var1;
   }

   public int getSoundEntryWeight() {
      return this.weight;
   }

   public boolean isStreaming() {
      return this.streaming;
   }

   public void setSoundEntryPitch(float var1) {
      this.pitch = var1;
   }

   public SoundList$SoundEntry$Type getSoundEntryType() {
      return this.type;
   }

   public float getSoundEntryPitch() {
      return this.pitch;
   }

   public void setSoundEntryType(SoundList$SoundEntry$Type var1) {
      this.type = var1;
   }

   public void setSoundEntryWeight(int var1) {
      this.weight = var1;
   }

   public void setStreaming(boolean var1) {
      this.streaming = var1;
   }

   public float getSoundEntryVolume() {
      return this.volume;
   }

   public void setSoundEntryVolume(float var1) {
      this.volume = var1;
   }
}
