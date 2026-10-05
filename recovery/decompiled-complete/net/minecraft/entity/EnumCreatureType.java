package net.minecraft.entity;

import io.netty.handler.codec.marshalling.ThreadLocalMarshallerProvider;
import javax.vecmath.Tuple2f;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.passive.IAnimals;

public enum EnumCreatureType {
   WATER_CREATURE(EntityWaterMob.class, 5, Material.water, true, false),
   AMBIENT(EntityAmbientCreature.class, 15, Material.air, true, false),
   MONSTER(IMob.class, 70, Material.air, false, false),
   CREATURE(EntityAnimal.class, 10, Material.air, true, true);

   public BlockStaticLiquid field_0005;
   public Class<? extends IAnimals> creatureClass;
   public Tuple2f field_0011;
   public ThreadLocalMarshallerProvider field_0008;
   public boolean isAnimal;
   public boolean isPeacefulCreature;
   public Material creatureMaterial;
   public int maxNumberOfCreature;

   public Class<? extends IAnimals> getCreatureClass() {
      return this.creatureClass;
   }

   public boolean getPeacefulCreature() {
      return this.isPeacefulCreature;
   }

   public boolean getAnimal() {
      return this.isAnimal;
   }

   public EnumCreatureType(Class<? extends IAnimals> var3, int var4, Material var5, boolean var6, boolean var7) {
      this.creatureClass = var3;
      this.maxNumberOfCreature = var4;
      this.creatureMaterial = var5;
      this.isPeacefulCreature = var6;
      this.isAnimal = var7;
   }

   public int getMaxNumberOfCreature() {
      return this.maxNumberOfCreature;
   }
}
