package net.minecraft.dispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BehaviorProjectileDispense extends BehaviorDefaultDispenseItem {
   public float func_82500_b() {
      return 1.1F;
   }

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      World var3 = var1.getWorld();
      IPosition var4 = BlockDispenser.getDispensePosition(var1);
      EnumFacing var5 = BlockDispenser.getFacing(var1.getBlockMetadata());
      IProjectile var6 = this.getProjectileEntity(var3, var4);
      var6.setThrowableHeading(var5.getFrontOffsetX(), var5.getFrontOffsetY() + 0.1F, var5.getFrontOffsetZ(), this.func_82500_b(), this.func_82498_a());
      var3.spawnEntityInWorld((Entity)var6);
      var2.splitStack(1);
      return var2;
   }

   public float func_82498_a() {
      return 6.0F;
   }

   @Override
   public void playDispenseSound(IBlockSource var1) {
      var1.getWorld().b(1002, var1.getBlockPos(), 0);
   }

   public abstract IProjectile getProjectileEntity(World var1, IPosition var2);
}
