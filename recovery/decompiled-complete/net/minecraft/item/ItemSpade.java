package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.monster.EntityGhast$AILookAround;
import net.minecraft.init.Blocks;

public class ItemSpade extends ItemTool {
   public BlockChest field_0000;
   public static Set<Block> EFFECTIVE_ON = Sets.newHashSet(
      new Block[]{
         Blocks.clay, Blocks.dirt, Blocks.farmland, Blocks.grass, Blocks.gravel, Blocks.mycelium, Blocks.sand, Blocks.snow, Blocks.snow_layer, Blocks.soul_sand
      }
   );
   public ItemAnvilBlock field_0002;
   public EntityGhast$AILookAround field_0003;

   public ItemSpade(Item$ToolMaterial var1) {
      super(1.0F, var1, EFFECTIVE_ON);
   }

   @Override
   public boolean canHarvestBlock(Block var1) {
      return var1 == Blocks.snow_layer ? true : var1 == Blocks.snow;
   }
}
