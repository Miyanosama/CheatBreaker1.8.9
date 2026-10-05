package net.optifine;

import net.minecraft.block.BlockLiquid;

public enum ConnectedTexturesCompact$Dir {
   UP,
   RIGHT,
   UP_RIGHT,
   DOWN,
   UP_LEFT,
   LEFT,
   DOWN_LEFT,
   DOWN_RIGHT;
   public static ConnectedTexturesCompact$Dir[] VALUES = values();
   // $VF: synthetic field
   public static ConnectedTexturesCompact$Dir[] $VALUES = new ConnectedTexturesCompact$Dir[]{
      UP, UP_RIGHT, RIGHT, ConnectedTexturesCompact$Dir.DOWN_RIGHT, DOWN, ConnectedTexturesCompact$Dir.DOWN_LEFT, ConnectedTexturesCompact$Dir.LEFT, UP_LEFT
   };
   public BlockLiquid field_0010;
}
