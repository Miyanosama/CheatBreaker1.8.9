package net.minecraft.block;

import com.cheatbreaker.client.module.type.ContainerBlurModule;
import com.cheatbreaker.client.ui.element.type.custom.KeybindElement;
import net.minecraft.world.gen.feature.WorldGenMelon;

// $VF: synthetic class
public class BlockHugeMushroom$1 {
   public KeybindElement field_0001;
   public ContainerBlurModule field_0000;
   public WorldGenMelon field_0002;

   static {
      try {
         field_181092_a[BlockHugeMushroom$EnumType.ALL_STEM.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_181092_a[BlockHugeMushroom$EnumType.ALL_INSIDE.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_181092_a[BlockHugeMushroom$EnumType.STEM.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
