package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.block.BlockHalfStoneSlab;
import net.minecraft.creativetab.CreativeTabs$12;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;

public class SoundRegistry extends RegistrySimple<ResourceLocation, SoundEventAccessorComposite> {
   public Map<ResourceLocation, SoundEventAccessorComposite> soundRegistry;
   public CreativeTabs$12 field_0002;
   public BlockHalfStoneSlab field_0000;

   public void registerSound(SoundEventAccessorComposite var1) {
      this.putObject(var1.getSoundEventLocation(), var1);
   }

   @Override
   public Map<ResourceLocation, SoundEventAccessorComposite> createUnderlyingMap() {
      this.soundRegistry = Maps.newHashMap();
      return this.soundRegistry;
   }

   public void clearMap() {
      this.soundRegistry.clear();
   }
}
