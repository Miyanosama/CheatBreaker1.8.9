package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTallGrass$EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.command.CommandWeather;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$MonumentBuilding;

public class EntityAIEatGrass extends EntityAIBase {
   public EntityLiving grassEaterEntity;
   public World entityWorld;
   public StructureOceanMonumentPieces$MonumentBuilding field_0002;
   public int eatingGrassTimer;
   public CommandWeather field_0000;
   public static Predicate<IBlockState> field_179505_b = BlockStateHelper.forBlock(Blocks.tallgrass)
      .where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass$EnumType.GRASS));

   @Override
   public void resetTask() {
      this.eatingGrassTimer = 0;
   }

   @Override
   public boolean continueExecuting() {
      return this.eatingGrassTimer > 0;
   }

   @Override
   public void startExecuting() {
      this.eatingGrassTimer = 40;
      this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
      this.grassEaterEntity.s().clearPathEntity();
   }

   public int getEatingGrassTimer() {
      return this.eatingGrassTimer;
   }

   @Override
   public boolean shouldExecute() {
      if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.o_() ? 50 : 1000) != 0) {
         return false;
      } else {
         BlockPos var1 = new BlockPos(this.grassEaterEntity.s, this.grassEaterEntity.t, this.grassEaterEntity.u);
         return field_179505_b.apply(this.entityWorld.getBlockState(var1)) ? true : this.entityWorld.getBlockState(var1.down()).getBlock() == Blocks.grass;
      }
   }

   @Override
   public void updateTask() {
      this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
      if (this.eatingGrassTimer == 4) {
         BlockPos var1 = new BlockPos(this.grassEaterEntity.s, this.grassEaterEntity.t, this.grassEaterEntity.u);
         if (field_179505_b.apply(this.entityWorld.getBlockState(var1))) {
            if (this.entityWorld.Q().getBoolean("mobGriefing")) {
               this.entityWorld.destroyBlock(var1, false);
            }

            this.grassEaterEntity.eatGrassBonus();
         } else {
            BlockPos var2 = var1.down();
            if (this.entityWorld.getBlockState(var2).getBlock() == Blocks.grass) {
               if (this.entityWorld.Q().getBoolean("mobGriefing")) {
                  this.entityWorld.b(2001, var2, Block.getIdFromBlock(Blocks.grass));
                  this.entityWorld.a(var2, Blocks.dirt.getDefaultState(), 2);
               }

               this.grassEaterEntity.eatGrassBonus();
            }
         }
      }
   }

   public EntityAIEatGrass(EntityLiving var1) {
      this.grassEaterEntity = var1;
      this.entityWorld = var1.o;
      this.setMutexBits(7);
   }
}
