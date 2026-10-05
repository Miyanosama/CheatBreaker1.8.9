package net.minecraft.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;

public abstract class EntityGolem extends EntityCreature implements IAnimals {
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
