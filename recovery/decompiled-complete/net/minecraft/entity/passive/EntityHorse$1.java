package net.minecraft.entity.passive;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.client.renderer.EnumFaceDirection$1;
import net.minecraft.entity.Entity;

public class EntityHorse$1 implements Predicate<Entity> {
   public EnumFaceDirection$1 field_0001;
   public BlockStoneSlabNew field_0003;
   public BlockCactus field_0000;
   public BlockTripWireHook field_0002;

   public boolean apply(Entity var1) {
      return var1 instanceof EntityHorse && ((EntityHorse)var1).isBreeding();
   }
}
