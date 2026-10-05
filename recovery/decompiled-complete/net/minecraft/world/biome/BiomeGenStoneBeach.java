package net.minecraft.world.biome;

import net.minecraft.block.state.BlockWorldState$1;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;

public class BiomeGenStoneBeach extends BiomeGenBase {
   public BlockWorldState$1 field_0000;
   public InventoryEnderChest field_0001;

   public BiomeGenStoneBeach(int var1) {
      super(var1);
      this.au.clear();
      this.ak = Blocks.stone.getDefaultState();
      this.al = Blocks.stone.getDefaultState();
      this.as.treesPerChunk = -999;
      this.as.deadBushPerChunk = 0;
      this.as.reedsPerChunk = 0;
      this.as.cactiPerChunk = 0;
   }
}
