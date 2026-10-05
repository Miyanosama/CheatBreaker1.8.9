package net.optifine;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;

public class RandomEntity implements IRandomEntity {
   public Entity entity;

   @Override
   public int getMaxHealth() {
      if (!(this.entity instanceof EntityLiving)) {
         return 0;
      } else {
         EntityLiving var1 = (EntityLiving)this.entity;
         return (int)var1.getMaxHealth();
      }
   }

   @Override
   public BlockPos getSpawnPosition() {
      return this.entity.H().spawnPosition;
   }

   public void setEntity(Entity var1) {
      this.entity = var1;
   }

   @Override
   public int getId() {
      UUID var1 = this.entity.aK();
      long var2 = var1.getLeastSignificantBits();
      return (int)(var2 & 2147483647L);
   }

   @Override
   public int getHealth() {
      if (!(this.entity instanceof EntityLiving)) {
         return 0;
      } else {
         EntityLiving var1 = (EntityLiving)this.entity;
         return (int)var1.getHealth();
      }
   }

   public Entity getEntity() {
      return this.entity;
   }

   @Override
   public BiomeGenBase getSpawnBiome() {
      return this.entity.H().spawnBiome;
   }

   @Override
   public String getName() {
      return this.entity.u_() ? this.entity.aM() : null;
   }
}
