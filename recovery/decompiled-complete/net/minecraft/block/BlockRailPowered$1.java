package net.minecraft.block;

import com.google.common.base.Predicate;
import net.optifine.shaders.gui.GuiButtonShaderOption;

public class BlockRailPowered$1 implements Predicate<BlockRailBase$EnumRailDirection> {
   public GuiButtonShaderOption field_0000;

   public boolean apply(BlockRailBase$EnumRailDirection var1) {
      return var1 != BlockRailBase$EnumRailDirection.NORTH_EAST
         && var1 != BlockRailBase$EnumRailDirection.NORTH_WEST
         && var1 != BlockRailBase$EnumRailDirection.SOUTH_EAST
         && var1 != BlockRailBase$EnumRailDirection.SOUTH_WEST;
   }
}
