package net.minecraft.init;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.log4j.chainsaw.ControlPanel$7;

public class Bootstrap$6 extends BehaviorDefaultDispenseItem {
   public ControlPanel$7 field_0000;

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      World var3 = var1.getWorld();
      BlockPos var4 = var1.getBlockPos().a(BlockDispenser.getFacing(var1.getBlockMetadata()));
      EntityTNTPrimed var5 = new EntityTNTPrimed(var3, var4.getX() + 0.5, var4.getY(), var4.getZ() + 0.5, (EntityLivingBase)null);
      var3.spawnEntityInWorld(var5);
      var3.a(var5, "game.tnt.primed", 1.0F, 1.0F);
      var2.stackSize--;
      return var2;
   }
}
