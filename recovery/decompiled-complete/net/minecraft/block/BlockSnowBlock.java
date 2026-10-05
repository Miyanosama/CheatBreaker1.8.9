package net.minecraft.block;

import io.netty.handler.codec.http.HttpContentEncoder;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandCompare;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockSnowBlock extends Block {
   public CommandCompare field_0000;
   public HttpContentEncoder field_0001;

   public BlockSnowBlock() {
      super(Material.craftedSnow);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int quantityDropped(Random var1) {
      return 4;
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.snowball;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (var1.getLightFor(EnumSkyBlock.BLOCK, var2) > 11) {
         this.dropBlockAsItem(var1, var2, var1.getBlockState(var2), 0);
         var1.setBlockToAir(var2);
      }
   }
}
