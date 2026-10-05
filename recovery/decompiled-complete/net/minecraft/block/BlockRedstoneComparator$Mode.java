package net.minecraft.block;

import io.netty.handler.codec.compression.JdkZlibEncoder$4;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.util.IStringSerializable;

public enum BlockRedstoneComparator$Mode implements IStringSerializable {
   SUBTRACT("subtract"),
   COMPARE("compare");

   public PathFinder field_0003;
   // $VF: synthetic field
   public static BlockRedstoneComparator$Mode[] $VALUES = new BlockRedstoneComparator$Mode[]{
      BlockRedstoneComparator$Mode.COMPARE, BlockRedstoneComparator$Mode.SUBTRACT
   };
   public JdkZlibEncoder$4 field_0002;
   public String name;

   @Override
   public String toString() {
      return this.name;
   }

   public BlockRedstoneComparator$Mode(String var3) {
      this.name = var3;
   }

   @Override
   public String getName() {
      return this.name;
   }
}
