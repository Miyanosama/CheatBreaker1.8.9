package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockStoneSlabNew$EnumType;
import net.minecraft.item.ItemFireworkCharge;

public class EntityEnderman$1 implements Predicate<EntityEndermite> {
   public BlockStoneSlabNew$EnumType field_0002;
   public ItemFireworkCharge field_0000;

   public EntityEnderman$1(EntityEnderman var1) {
      this.field_179949_a = var1;
      super();
   }

   public boolean apply(EntityEndermite var1) {
      return var1.isSpawnedByPlayer();
   }
}
