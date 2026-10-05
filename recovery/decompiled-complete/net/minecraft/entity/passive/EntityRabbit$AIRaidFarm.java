package net.minecraft.entity.passive;

import io.netty.handler.timeout.WriteTimeoutHandler$1;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceEntriesToIntTask;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelShapes$5;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemEmptyMap;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.RegionFileCache;
import net.optifine.expr.FunctionType$1;

public class EntityRabbit$AIRaidFarm extends EntityAIMoveToBlock {
   public ItemEmptyMap field_0004;
   public WriteTimeoutHandler$1 field_0007;
   public boolean field_179498_d;
   public ConcurrentHashMapV8$MapReduceEntriesToIntTask field_0006;
   public FunctionType$1 field_0000;
   public boolean field_179499_e = false;
   public RegionFileCache field_0008;
   public EntityRabbit rabbit;
   public BlockModelShapes$5 field_0002;

   @Override
   public void resetTask() {
      super.resetTask();
   }

   @Override
   public void updateTask() {
      super.updateTask();
      this.rabbit
         .getLookHelper()
         .setLookPosition(
            this.destinationBlock.getX() + 0.5, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5, 10.0F, this.rabbit.getVerticalFaceSpeed()
         );
      if (this.getIsAboveDestination()) {
         World var1 = this.rabbit.o;
         BlockPos var2 = this.destinationBlock.up();
         IBlockState var3 = var1.getBlockState(var2);
         Block var4 = var3.getBlock();
         if (this.field_179499_e && var4 instanceof BlockCarrot && var3.getValue(BlockCarrot.AGE) == 7) {
            var1.a(var2, Blocks.air.getDefaultState(), 2);
            var1.destroyBlock(var2, true);
            this.rabbit.createEatingParticles();
         }

         this.field_179499_e = false;
         this.a = 10;
      }
   }

   @Override
   public void startExecuting() {
      super.startExecuting();
   }

   @Override
   public boolean shouldExecute() {
      if (this.a <= 0) {
         if (!this.rabbit.o.Q().getBoolean("mobGriefing")) {
            return false;
         }

         this.field_179499_e = false;
         this.field_179498_d = EntityRabbit.access$000(this.rabbit);
      }

      return super.shouldExecute();
   }

   public EntityRabbit$AIRaidFarm(EntityRabbit var1) {
      super(var1, 0.7F, 16);
      this.rabbit = var1;
   }

   @Override
   public boolean shouldMoveTo(World var1, BlockPos var2) {
      Block var3 = var1.getBlockState(var2).getBlock();
      if (var3 == Blocks.farmland) {
         var2 = var2.up();
         IBlockState var4 = var1.getBlockState(var2);
         var3 = var4.getBlock();
         if (var3 instanceof BlockCarrot && var4.getValue(BlockCarrot.AGE) == 7 && this.field_179498_d && !this.field_179499_e) {
            this.field_179499_e = true;
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean continueExecuting() {
      return this.field_179499_e && super.continueExecuting();
   }
}
