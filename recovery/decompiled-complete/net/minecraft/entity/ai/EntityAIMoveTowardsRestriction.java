package net.minecraft.entity.ai;

import com.cheatbreaker.client.ui.overlay.element.AliasesElement;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.state.BlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Corridor;

public class EntityAIMoveTowardsRestriction extends EntityAIBase {
   public double movePosZ;
   public AliasesElement field_0007;
   public BlockFenceGate field_0003;
   public double movePosX;
   public double movementSpeed;
   public StructureNetherBridgePieces$Corridor field_0001;
   public EntityCreature theEntity;
   public BlockState field_0005;
   public double movePosY;

   @Override
   public boolean continueExecuting() {
      return !this.theEntity.s().noPath();
   }

   @Override
   public boolean shouldExecute() {
      if (this.theEntity.isWithinHomeDistanceCurrentPosition()) {
         return false;
      } else {
         BlockPos var1 = this.theEntity.getHomePosition();
         Vec3 var2 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 16, 7, new Vec3(var1.getX(), var1.getY(), var1.getZ()));
         if (var2 == null) {
            return false;
         } else {
            this.movePosX = var2.xCoord;
            this.movePosY = var2.yCoord;
            this.movePosZ = var2.zCoord;
            return true;
         }
      }
   }

   @Override
   public void startExecuting() {
      this.theEntity.s().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.movementSpeed);
   }

   public EntityAIMoveTowardsRestriction(EntityCreature var1, double var2) {
      this.theEntity = var1;
      this.movementSpeed = var2;
      this.setMutexBits(1);
   }
}
