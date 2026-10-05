package net.minecraft.dispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.NetHandlerHandshakeTCP$1;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.world.World;
import org.apache.log4j.nt.NTEventLogAppender;

public class BehaviorDefaultDispenseItem implements IBehaviorDispenseItem {
   public NetHandlerHandshakeTCP$1 field_0000;
   public NTEventLogAppender field_0001;

   public int func_82488_a(EnumFacing var1) {
      return var1.getFrontOffsetX() + 1 + (var1.getFrontOffsetZ() + 1) * 3;
   }

   @Override
   public ItemStack dispense(IBlockSource var1, ItemStack var2) {
      ItemStack var3 = this.dispenseStack(var1, var2);
      this.playDispenseSound(var1);
      this.spawnDispenseParticles(var1, BlockDispenser.getFacing(var1.getBlockMetadata()));
      return var3;
   }

   public static void doDispense(World var0, ItemStack var1, int var2, EnumFacing var3, IPosition var4) {
      double var5 = var4.getX();
      double var7 = var4.getY();
      double var9 = var4.getZ();
      if (var3.getAxis() == EnumFacing$Axis.Y) {
         var7 -= 0.125;
      } else {
         var7 -= 0.15625;
      }

      EntityItem var11 = new EntityItem(var0, var5, var7, var9, var1);
      double var12 = var0.s.nextDouble() * 0.1 + 0.2;
      var11.v = var3.getFrontOffsetX() * var12;
      var11.w = 0.2F;
      var11.x = var3.getFrontOffsetZ() * var12;
      var11.v = var11.v + var0.s.nextGaussian() * 0.0075F * var2;
      var11.w = var11.w + var0.s.nextGaussian() * 0.0075F * var2;
      var11.x = var11.x + var0.s.nextGaussian() * 0.0075F * var2;
      var0.spawnEntityInWorld(var11);
   }

   public void playDispenseSound(IBlockSource var1) {
      var1.getWorld().b(1000, var1.getBlockPos(), 0);
   }

   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      EnumFacing var3 = BlockDispenser.getFacing(var1.getBlockMetadata());
      IPosition var4 = BlockDispenser.getDispensePosition(var1);
      ItemStack var5 = var2.splitStack(1);
      doDispense(var1.getWorld(), var5, 6, var3, var4);
      return var2;
   }

   public void spawnDispenseParticles(IBlockSource var1, EnumFacing var2) {
      var1.getWorld().b(2000, var1.getBlockPos(), this.func_82488_a(var2));
   }
}
