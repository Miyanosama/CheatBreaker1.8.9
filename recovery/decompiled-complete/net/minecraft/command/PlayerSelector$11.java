package net.minecraft.command;

import com.google.common.base.Predicate;
import junit.extensions.ActiveTestSuite;
import net.minecraft.block.BlockPistonExtension$EnumPistonType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityPainting$EnumArt;
import net.minecraft.util.BlockPos;

public class PlayerSelector$11 implements Predicate<Entity> {
   public ActiveTestSuite field_0003;
   public EntityPainting$EnumArt field_0005;
   public BlockPistonExtension$EnumPistonType field_0004;

   public boolean apply(Entity var1) {
      int var2 = (int)var1.getDistanceSqToCenter(this.field_179599_a);
      return (this.field_179597_b < 0 || var2 >= this.field_179598_c) && (this.field_179595_d < 0 || var2 <= this.field_179596_e);
   }

   public PlayerSelector$11(BlockPos var1, int var2, int var3, int var4, int var5) {
      this.field_179599_a = var1;
      this.field_179597_b = var2;
      this.field_179598_c = var3;
      this.field_179595_d = var4;
      this.field_179596_e = var5;
      super();
   }
}
