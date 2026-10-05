package net.minecraft.entity.ai;

import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityMinecartMobSpawner$1 extends MobSpawnerBaseLogic {
   public EntityMinecartMobSpawner$1(EntityMinecartMobSpawner var1) {
      this.field_98296_a = var1;
      super();
   }

   @Override
   public BlockPos getSpawnerPosition() {
      return new BlockPos(this.field_98296_a);
   }

   @Override
   public World getSpawnerWorld() {
      return this.field_98296_a.o;
   }

   @Override
   public void func_98267_a(int var1) {
      this.field_98296_a.o.setEntityState(this.field_98296_a, (byte)var1);
   }
}
