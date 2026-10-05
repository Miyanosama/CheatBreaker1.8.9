package net.minecraft.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailBase$EnumRailDirection;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemMinecart$1 extends BehaviorDefaultDispenseItem {
   public EntityDiggingFX field_0000;
   public BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();

   @Override
   public void playDispenseSound(IBlockSource var1) {
      var1.getWorld().b(1000, var1.getBlockPos(), 0);
   }

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      EnumFacing var3 = BlockDispenser.getFacing(var1.getBlockMetadata());
      World var4 = var1.getWorld();
      double var5 = var1.getX() + var3.getFrontOffsetX() * 1.125;
      double var7 = Math.floor(var1.getY()) + var3.getFrontOffsetY();
      double var9 = var1.getZ() + var3.getFrontOffsetZ() * 1.125;
      BlockPos var11 = var1.getBlockPos().a(var3);
      IBlockState var12 = var4.getBlockState(var11);
      BlockRailBase$EnumRailDirection var13 = var12.getBlock() instanceof BlockRailBase
         ? var12.getValue(((BlockRailBase)var12.getBlock()).getShapeProperty())
         : BlockRailBase$EnumRailDirection.NORTH_SOUTH;
      double var14;
      if (BlockRailBase.isRailBlock(var12)) {
         if (var13.isAscending()) {
            var14 = 0.6;
         } else {
            var14 = 0.1;
         }
      } else {
         if (var12.getBlock().getMaterial() != Material.air || !BlockRailBase.isRailBlock(var4.getBlockState(var11.down()))) {
            return this.behaviourDefaultDispenseItem.dispense(var1, var2);
         }

         IBlockState var16 = var4.getBlockState(var11.down());
         BlockRailBase$EnumRailDirection var17 = var16.getBlock() instanceof BlockRailBase
            ? var16.getValue(((BlockRailBase)var16.getBlock()).getShapeProperty())
            : BlockRailBase$EnumRailDirection.NORTH_SOUTH;
         if (var3 != EnumFacing.DOWN && var17.isAscending()) {
            var14 = -0.4;
         } else {
            var14 = -0.9;
         }
      }

      EntityMinecart var18 = EntityMinecart.getMinecart(var4, var5, var7 + var14, var9, ItemMinecart.access$000((ItemMinecart)var2.getItem()));
      if (var2.hasDisplayName()) {
         var18.a(var2.getDisplayName());
      }

      var4.spawnEntityInWorld(var18);
      var2.splitStack(1);
      return var2;
   }
}
