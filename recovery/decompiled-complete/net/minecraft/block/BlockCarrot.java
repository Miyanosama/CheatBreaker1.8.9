package net.minecraft.block;

import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockCarrot extends BlockCrops {
   public FallbackResourceManager field_0000;

   @Override
   public Item getSeed() {
      return Items.carrot;
   }

   @Override
   public Item getCrop() {
      return Items.carrot;
   }
}
