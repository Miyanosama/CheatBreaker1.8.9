package net.minecraft.entity.monster;

import io.netty.buffer.ByteBufUtil$ThreadLocalDirectByteBuf;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport$4;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapterWither;

public class EntityEnderman$AIPlaceBlock extends EntityAIBase {
   public CrashReport$4 field_0002;
   public ByteBufUtil$ThreadLocalDirectByteBuf field_0004;
   public EntityMoveHelper field_0001;
   public ModelAdapterWither field_0003;
   public EntityEnderman enderman;

   @Override
   public void updateTask() {
      Random var1 = this.enderman.getRNG();
      World var2 = this.enderman.o;
      int var3 = MathHelper.floor_double(this.enderman.s - 1.0 + var1.nextDouble() * 2.0);
      int var4 = MathHelper.floor_double(this.enderman.t + var1.nextDouble() * 2.0);
      int var5 = MathHelper.floor_double(this.enderman.u - 1.0 + var1.nextDouble() * 2.0);
      BlockPos var6 = new BlockPos(var3, var4, var5);
      Block var7 = var2.getBlockState(var6).getBlock();
      Block var8 = var2.getBlockState(var6.down()).getBlock();
      if (this.func_179474_a(var2, var6, this.enderman.getHeldBlockState().getBlock(), var7, var8)) {
         var2.a(var6, this.enderman.getHeldBlockState(), 3);
         this.enderman.setHeldBlockState(Blocks.air.getDefaultState());
      }
   }

   @Override
   public boolean shouldExecute() {
      return !this.enderman.o.Q().getBoolean("mobGriefing")
         ? false
         : (this.enderman.getHeldBlockState().getBlock().getMaterial() == Material.air ? false : this.enderman.getRNG().nextInt(2000) == 0);
   }

   public boolean func_179474_a(World var1, BlockPos var2, Block var3, Block var4, Block var5) {
      return !var3.canPlaceBlockAt(var1, var2)
         ? false
         : (var4.getMaterial() != Material.air ? false : (var5.getMaterial() == Material.air ? false : var5.isFullCube()));
   }

   public EntityEnderman$AIPlaceBlock(EntityEnderman var1) {
      this.enderman = var1;
   }
}
