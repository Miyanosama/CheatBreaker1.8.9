package net.minecraft.item;

import io.netty.channel.oio.AbstractOioByteChannel;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.World$4;
import org.slf4j.helpers.FormattingTuple;

public class ItemSeeds extends Item {
   public Block soilBlockID;
   public FormattingTuple field_0002;
   public Block crops;
   public AbstractOioByteChannel field_0004;
   public World$4 field_0000;

   public ItemSeeds(Block var1, Block var2) {
      this.crops = var1;
      this.soilBlockID = var2;
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var5 != EnumFacing.UP) {
         return false;
      } else if (!var2.canPlayerEdit(var4.a(var5), var5, var1)) {
         return false;
      } else if (var3.getBlockState(var4).getBlock() == this.soilBlockID && var3.isAirBlock(var4.up())) {
         var3.setBlockState(var4.up(), this.crops.getDefaultState());
         var1.stackSize--;
         return true;
      } else {
         return false;
      }
   }
}
