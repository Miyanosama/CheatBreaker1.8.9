package net.minecraft.entity.monster;

import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;
import net.optifine.shaders.config.Property;

public abstract class EntityGolem extends EntityCreature implements IAnimals {
   public Property field_0000;
   public BlockRedstoneRepeater field_0001;

   @Override
   public int getTalkInterval() {
      return 120;
   }

   @Override
   public String getLivingSound() {
      return "none";
   }

   @Override
   public boolean canDespawn() {
      return false;
   }

   @Override
   public String getHurtSound() {
      return "none";
   }

   @Override
   public void fall(float var1, float var2) {
   }

   @Override
   public String getDeathSound() {
      return "none";
   }

   public EntityGolem(World var1) {
      super(var1);
   }
}
