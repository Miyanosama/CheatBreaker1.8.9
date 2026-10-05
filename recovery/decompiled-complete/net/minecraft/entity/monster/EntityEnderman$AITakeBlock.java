package net.minecraft.entity.monster;

import com.cheatbreaker.client.module.type.NickHiderModule;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentArrowKnockback;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper$3;

public class EntityEnderman$AITakeBlock extends EntityAIBase {
   public EnchantmentArrowKnockback field_0001;
   public NickHiderModule field_0003;
   public BiomeColorHelper$3 field_0000;
   public EntityEnderman enderman;

   @Override
   public void updateTask() {
      Random var1 = this.enderman.getRNG();
      World var2 = this.enderman.o;
      int var3 = MathHelper.floor_double(this.enderman.s - 2.0 + var1.nextDouble() * 4.0);
      int var4 = MathHelper.floor_double(this.enderman.t + var1.nextDouble() * 3.0);
      int var5 = MathHelper.floor_double(this.enderman.u - 2.0 + var1.nextDouble() * 4.0);
      BlockPos var6 = new BlockPos(var3, var4, var5);
      IBlockState var7 = var2.getBlockState(var6);
      Block var8 = var7.getBlock();
      if (EntityEnderman.access$300().contains(var8)) {
         this.enderman.setHeldBlockState(var7);
         var2.setBlockState(var6, Blocks.air.getDefaultState());
      }
   }

   @Override
   public boolean shouldExecute() {
      return !this.enderman.o.Q().getBoolean("mobGriefing")
         ? false
         : (this.enderman.getHeldBlockState().getBlock().getMaterial() != Material.air ? false : this.enderman.getRNG().nextInt(20) == 0);
   }

   public EntityEnderman$AITakeBlock(EntityEnderman var1) {
      this.enderman = var1;
   }
}
