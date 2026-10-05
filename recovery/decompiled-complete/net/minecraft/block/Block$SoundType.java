package net.minecraft.block;

import io.netty.buffer.ByteBufProcessor$5;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.BlockPos$MutableBlockPos;

public class Block$SoundType {
   public CraftingManager field_0000;
   public float volume;
   public String soundName;
   public ByteBufProcessor$5 field_0003;
   public float frequency;
   public BlockPos$MutableBlockPos field_0005;

   public float getFrequency() {
      return this.frequency;
   }

   public String getStepSound() {
      return "step." + this.soundName;
   }

   public String getBreakSound() {
      return "dig." + this.soundName;
   }

   public Block$SoundType(String var1, float var2, float var3) {
      this.soundName = var1;
      this.volume = var2;
      this.frequency = var3;
   }

   public String getPlaceSound() {
      return this.getBreakSound();
   }

   public float getVolume() {
      return this.volume;
   }
}
