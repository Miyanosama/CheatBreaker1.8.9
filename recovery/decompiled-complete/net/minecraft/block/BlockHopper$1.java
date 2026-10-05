package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.inventory.ContainerRepair$2;
import net.minecraft.util.EnumFacing;

public class BlockHopper$1 implements Predicate<EnumFacing> {
   public ContainerRepair$2 field_0000;

   public boolean apply(EnumFacing var1) {
      return var1 != EnumFacing.UP;
   }
}
