package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.command.PlayerSelector$9;

public class BlockOldLeaf$1 implements Predicate<BlockPlanks$EnumType> {
   public PlayerSelector$9 field_0000;

   public boolean apply(BlockPlanks$EnumType var1) {
      return var1.getMetadata() < 4;
   }
}
