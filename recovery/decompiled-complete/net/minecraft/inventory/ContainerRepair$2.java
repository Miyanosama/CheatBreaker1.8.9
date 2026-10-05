package net.minecraft.inventory;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.optifine.DynamicLights;

public class ContainerRepair$2 extends Slot {
   public DynamicLights field_0002;

   @Override
   public boolean isItemValid(ItemStack var1) {
      return false;
   }

   public ContainerRepair$2(ContainerRepair var1, IInventory var2, int var3, int var4, int var5, World var6, BlockPos var7) {
      this.field_135068_e = var1;
      this.field_135071_a = var6;
      this.field_178174_b = var7;
      super(var2, var3, var4, var5);
   }

   @Override
   public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
      if (!var1.bA.isCreativeMode) {
         var1.addExperienceLevel(-this.field_135068_e.maximumCost);
      }

      ContainerRepair.access$000(this.field_135068_e).setInventorySlotContents(0, (ItemStack)null);
      if (ContainerRepair.access$100(this.field_135068_e) > 0) {
         ItemStack var3 = ContainerRepair.access$000(this.field_135068_e).getStackInSlot(1);
         if (var3 != null && var3.stackSize > ContainerRepair.access$100(this.field_135068_e)) {
            var3.stackSize = var3.stackSize - ContainerRepair.access$100(this.field_135068_e);
            ContainerRepair.access$000(this.field_135068_e).setInventorySlotContents(1, var3);
         } else {
            ContainerRepair.access$000(this.field_135068_e).setInventorySlotContents(1, (ItemStack)null);
         }
      } else {
         ContainerRepair.access$000(this.field_135068_e).setInventorySlotContents(1, (ItemStack)null);
      }

      this.field_135068_e.maximumCost = 0;
      IBlockState var5 = this.field_135071_a.getBlockState(this.field_178174_b);
      if (!var1.bA.isCreativeMode && !this.field_135071_a.D && var5.getBlock() == Blocks.anvil && var1.getRNG().nextFloat() < 0.12F) {
         int var4 = var5.getValue(BlockAnvil.DAMAGE);
         if (++var4 > 2) {
            this.field_135071_a.setBlockToAir(this.field_178174_b);
            this.field_135071_a.b(1020, this.field_178174_b, 0);
         } else {
            this.field_135071_a.a(this.field_178174_b, var5.withProperty(BlockAnvil.DAMAGE, var4), 2);
            this.field_135071_a.b(1021, this.field_178174_b, 0);
         }
      } else if (!this.field_135071_a.D) {
         this.field_135071_a.b(1021, this.field_178174_b, 0);
      }
   }

   @Override
   public boolean canTakeStack(EntityPlayer var1) {
      return (var1.bA.isCreativeMode || var1.bB >= this.field_135068_e.maximumCost) && this.field_135068_e.maximumCost > 0 && this.getHasStack();
   }
}
