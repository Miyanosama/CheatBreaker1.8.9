package recovered.unidentified;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer$1;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$EntryRoom;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$29;

public class UnidentifiedClass1285 extends Item {
   public LogBrokerMonitor$29 field_0000;
   public StructureOceanMonumentPieces$EntryRoom field_0001;
   public InventoryPlayer$1 field_0002;

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      boolean var9 = var3.getBlockState(var4).getBlock().isReplaceable(var3, var4);
      BlockPos var10 = var9 ? var4 : var4.a(var5);
      if (!var2.canPlayerEdit(var10, var5, var1)) {
         return false;
      } else {
         Block var11 = var3.getBlockState(var10).getBlock();
         if (!var3.canBlockBePlaced(var11, var10, false, var5, (Entity)null, var1)) {
            return false;
         } else if (Blocks.redstone_wire.canPlaceBlockAt(var3, var10)) {
            var1.stackSize--;
            var3.setBlockState(var10, Blocks.redstone_wire.getDefaultState());
            return true;
         } else {
            return false;
         }
      }
   }

   public UnidentifiedClass1285() {
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }
}
