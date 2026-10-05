package net.minecraft.client.audio;

import junit.runner.TestCaseClassLoader;
import net.minecraft.block.BlockTorch$2;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.anim.ModelVariableFloat;

public class SoundPoolEntry {
   public double pitch;
   public ModelVariableFloat field_0005;
   public boolean streamingSound;
   public TestCaseClassLoader field_0004;
   public ResourceLocation location;
   public double volume;
   public BlockTorch$2 field_0006;

   public void setPitch(double var1) {
      this.pitch = var1;
   }

   public double getPitch() {
      return this.pitch;
   }

   public double getVolume() {
      return this.volume;
   }

   public void setVolume(double var1) {
      this.volume = var1;
   }

   public boolean isStreamingSound() {
      return this.streamingSound;
   }

   public SoundPoolEntry(ResourceLocation var1, double var2, double var4, boolean var6) {
      this.location = var1;
      this.pitch = var2;
      this.volume = var4;
      this.streamingSound = var6;
   }

   public ResourceLocation getSoundPoolEntryLocation() {
      return this.location;
   }

   public SoundPoolEntry(SoundPoolEntry var1) {
      this.location = var1.location;
      this.pitch = var1.pitch;
      this.volume = var1.volume;
      this.streamingSound = var1.streamingSound;
   }
}
