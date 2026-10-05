package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class ItemAxe extends ItemTool {
   public static Set<Block> EFFECTIVE_ON = Sets.newHashSet(
      Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder
   );

   public ItemAxe(Item.ToolMaterial var1) {
      super(3.0F, var1, EFFECTIVE_ON);
   }

   @Override
   public float getStrVsBlock(ItemStack var1, Block var2) {
      return var2.getMaterial() != Material.wood && var2.getMaterial() != Material.plants && var2.getMaterial() != Material.vine
         ? super.getStrVsBlock(var1, var2)
         : this.efficiencyOnProperMaterial;
   }
}
