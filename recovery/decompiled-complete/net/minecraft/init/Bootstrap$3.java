package net.minecraft.init;

import io.netty.util.internal.logging.AbstractInternalLogger$1;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class Bootstrap$3 extends BehaviorDefaultDispenseItem {
   public EntityPortalFX field_0001;
   public BehaviorDefaultDispenseItem field_150840_b = new BehaviorDefaultDispenseItem();
   public AbstractInternalLogger$1 field_0000;
   public MaterialLiquid field_0003;

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      World var3 = var1.getWorld();
      BlockPos var4 = var1.getBlockPos().a(BlockDispenser.getFacing(var1.getBlockMetadata()));
      IBlockState var5 = var3.getBlockState(var4);
      Block var6 = var5.getBlock();
      Material var7 = var6.getMaterial();
      Item var8;
      if (Material.water.equals(var7) && var6 instanceof BlockLiquid && var5.getValue(BlockLiquid.b) == 0) {
         var8 = Items.water_bucket;
      } else {
         if (!Material.lava.equals(var7) || !(var6 instanceof BlockLiquid) || var5.getValue(BlockLiquid.b) != 0) {
            return super.dispenseStack(var1, var2);
         }

         var8 = Items.lava_bucket;
      }

      var3.setBlockToAir(var4);
      if (--var2.stackSize == 0) {
         var2.setItem(var8);
         var2.stackSize = 1;
      } else if (var1.<TileEntityDispenser>getBlockTileEntity().addItemStack(new ItemStack(var8)) < 0) {
         this.field_150840_b.dispense(var1, new ItemStack(var8));
      }

      return var2;
   }
}
