package net.minecraft.command;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;

public class PlayerSelector$4 implements Comparator<Entity> {
   public WorldGenDoublePlant field_0000;

   public int compare(Entity var1, Entity var2) {
      return ComparisonChain.start().compare(var1.getDistanceSq(this.field_179612_a), var2.getDistanceSq(this.field_179612_a)).result();
   }

   public PlayerSelector$4(BlockPos var1) {
      this.field_179612_a = var1;
      super();
   }
}
