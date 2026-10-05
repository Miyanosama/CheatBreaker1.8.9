package net.minecraft.entity.monster;

import io.netty.util.HashedWheelTimer$HashedWheelTimeout$1;
import java.util.Random;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockSilverfish$EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass3605;

public class EntitySilverfish$AIHideInStone extends EntityAIWander {
   public boolean field_179484_c;
   public EnumFacing facing;
   public HashedWheelTimer$HashedWheelTimeout$1 field_0003;
   public EntitySilverfish silverfish;
   public UnidentifiedClass3605 field_0000;

   @Override
   public boolean continueExecuting() {
      return this.field_179484_c ? false : super.continueExecuting();
   }

   @Override
   public boolean shouldExecute() {
      if (this.silverfish.getAttackTarget() != null) {
         return false;
      } else if (!this.silverfish.s().noPath()) {
         return false;
      } else {
         Random var1 = this.silverfish.getRNG();
         if (var1.nextInt(10) == 0) {
            this.facing = EnumFacing.random(var1);
            BlockPos var2 = new BlockPos(this.silverfish.s, this.silverfish.t + 0.5, this.silverfish.u).a(this.facing);
            IBlockState var3 = this.silverfish.o.getBlockState(var2);
            if (BlockSilverfish.canContainSilverfish(var3)) {
               this.field_179484_c = true;
               return true;
            }
         }

         this.field_179484_c = false;
         return super.shouldExecute();
      }
   }

   @Override
   public void startExecuting() {
      if (!this.field_179484_c) {
         super.startExecuting();
      } else {
         World var1 = this.silverfish.o;
         BlockPos var2 = new BlockPos(this.silverfish.s, this.silverfish.t + 0.5, this.silverfish.u).a(this.facing);
         IBlockState var3 = var1.getBlockState(var2);
         if (BlockSilverfish.canContainSilverfish(var3)) {
            var1.a(var2, Blocks.monster_egg.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish$EnumType.forModelBlock(var3)), 3);
            this.silverfish.spawnExplosionParticle();
            this.silverfish.setDead();
         }
      }
   }

   public EntitySilverfish$AIHideInStone(EntitySilverfish var1) {
      super(var1, 1.0, 10);
      this.silverfish = var1;
      this.setMutexBits(1);
   }
}
