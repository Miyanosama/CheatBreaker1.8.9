package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockPressurePlate extends BlockBasePressurePlate {
   public static PropertyBool POWERED = PropertyBool.create("powered");
   public BlockPressurePlate.Sensitivity sensitivity;

   public BlockPressurePlate(Material var1, BlockPressurePlate.Sensitivity var2) {
      super(var1);
      this.setDefaultState(this.M.getBaseState().withProperty(POWERED, false));
      this.sensitivity = var2;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, POWERED);
   }

   @Override
   public int computeRedstoneStrength(World var1, BlockPos var2) {
      AxisAlignedBB var3 = this.getSensitiveAABB(var2);
      List var4;
      switch (this.sensitivity) {
         case EVERYTHING:
            var4 = var1.getEntitiesWithinAABBExcludingEntity((Entity)null, var3);
            break;
         case MOBS:
            var4 = var1.getEntitiesWithinAABB(EntityLivingBase.class, var3);
            break;
         default:
            return 0;
      }

      if (!var4.isEmpty()) {
         for (Entity var6 : (Iterable<Entity>)(Iterable<?>)(var4)) {
            if (!var6.doesEntityNotTriggerPressurePlate()) {
               return 15;
            }
         }
      }

      return 0;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(POWERED, var1 == 1);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(POWERED) ? 1 : 0;
   }

   @Override
   public int getRedstoneStrength(IBlockState var1) {
      return var1.getValue(POWERED) ? 15 : 0;
   }

   @Override
   public IBlockState setRedstoneStrength(IBlockState var1, int var2) {
      return var1.withProperty(POWERED, var2 > 0);
   }

   public static enum Sensitivity {
      EVERYTHING,
      MOBS;
      // $VF: synthetic field
      public static BlockPressurePlate.Sensitivity[] $VALUES = new BlockPressurePlate.Sensitivity[]{
         BlockPressurePlate.Sensitivity.EVERYTHING, BlockPressurePlate.Sensitivity.MOBS
      };
   }
}
