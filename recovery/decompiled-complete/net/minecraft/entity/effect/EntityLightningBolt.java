package net.minecraft.entity.effect;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLightningBolt extends EntityWeatherEffect {
   public long boltVertex;
   public int lightningState;
   public int boltLivingTime;

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
   }

   public EntityLightningBolt(World var1, double var2, double var4, double var6) {
      super(var1);
      this.a_(var2, var4, var6, 0.0F, 0.0F);
      this.lightningState = 2;
      this.boltVertex = this.V.nextLong();
      this.boltLivingTime = this.V.nextInt(3) + 1;
      BlockPos var8 = new BlockPos(this);
      if (!var1.D
         && var1.Q().getBoolean("doFireTick")
         && (var1.getDifficulty() == EnumDifficulty.NORMAL || var1.getDifficulty() == EnumDifficulty.HARD)
         && var1.isAreaLoaded(var8, 10)) {
         if (var1.getBlockState(var8).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(var1, var8)) {
            var1.setBlockState(var8, Blocks.fire.getDefaultState());
         }

         for (int var9 = 0; var9 < 4; var9++) {
            BlockPos var10 = var8.add(this.V.nextInt(3) - 1, this.V.nextInt(3) - 1, this.V.nextInt(3) - 1);
            if (var1.getBlockState(var10).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(var1, var10)) {
               var1.setBlockState(var10, Blocks.fire.getDefaultState());
            }
         }
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.lightningState == 2) {
         this.o.playSoundEffect(this.s, this.t, this.u, "ambient.weather.thunder", 10000.0F, 0.8F + this.V.nextFloat() * 0.2F);
         this.o.playSoundEffect(this.s, this.t, this.u, "random.explode", 2.0F, 0.5F + this.V.nextFloat() * 0.2F);
      }

      this.lightningState--;
      if (this.lightningState < 0) {
         if (this.boltLivingTime == 0) {
            this.setDead();
         } else if (this.lightningState < -this.V.nextInt(10)) {
            this.boltLivingTime--;
            this.lightningState = 1;
            this.boltVertex = this.V.nextLong();
            BlockPos var1 = new BlockPos(this);
            if (!this.o.D
               && this.o.Q().getBoolean("doFireTick")
               && this.o.isAreaLoaded(var1, 10)
               && this.o.getBlockState(var1).getBlock().getMaterial() == Material.air
               && Blocks.fire.canPlaceBlockAt(this.o, var1)) {
               this.o.setBlockState(var1, Blocks.fire.getDefaultState());
            }
         }
      }

      if (this.lightningState >= 0) {
         if (this.o.D) {
            this.o.setLastLightningBolt(2);
         } else {
            double var6 = 3.0;
            List var3 = this.o
               .getEntitiesWithinAABBExcludingEntity(
                  this, new AxisAlignedBB(this.s - var6, this.t - var6, this.u - var6, this.s + var6, this.t + 6.0 + var6, this.u + var6)
               );

            for (int var4 = 0; var4 < var3.size(); var4++) {
               Entity var5 = (Entity)var3.get(var4);
               var5.onStruckByLightning(this);
            }
         }
      }
   }

   @Override
   public void k_() {
   }
}
