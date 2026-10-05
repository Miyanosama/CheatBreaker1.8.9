package net.minecraft.init;

import net.minecraft.block.BlockDispenser;
import net.minecraft.command.CommandBase$CoordinateArg;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.LockCode;

public class Bootstrap$2 extends BehaviorDefaultDispenseItem {
   public CommandBase$CoordinateArg field_0001;
   public LockCode field_0002;
   public BehaviorDefaultDispenseItem field_150841_b = new BehaviorDefaultDispenseItem();

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      ItemBucket var3 = (ItemBucket)var2.getItem();
      BlockPos var4 = var1.getBlockPos().a(BlockDispenser.getFacing(var1.getBlockMetadata()));
      if (var3.tryPlaceContainedLiquid(var1.getWorld(), var4)) {
         var2.setItem(Items.bucket);
         var2.stackSize = 1;
         return var2;
      } else {
         return this.field_150841_b.dispense(var1, var2);
      }
   }
}
