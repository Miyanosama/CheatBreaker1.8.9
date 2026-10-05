package net.minecraft.block;

import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemHoe$1;
import net.minecraft.world.biome.BiomeGenMesa;

public class Block$4 extends Block$SoundType {
   public SlotFurnaceFuel field_0001;
   public ItemHoe$1 field_0003;
   public SoundEventAccessorComposite field_0000;
   public BiomeGenMesa field_0002;

   public Block$4(String var1, float var2, float var3) {
      super(var1, var2, var3);
   }

   @Override
   public String getBreakSound() {
      return "mob.slime.big";
   }

   @Override
   public String getStepSound() {
      return "mob.slime.small";
   }

   @Override
   public String getPlaceSound() {
      return "mob.slime.big";
   }
}
