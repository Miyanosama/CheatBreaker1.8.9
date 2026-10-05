package net.minecraft.client.renderer;

import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.item.ItemBanner;
import net.minecraft.util.EnumFacing;

public enum BlockModelRenderer$Orientation {
   WEST(EnumFacing.WEST, false),
   FLIP_DOWN(EnumFacing.DOWN, true),
   FLIP_NORTH(EnumFacing.NORTH, true),
   FLIP_UP(EnumFacing.UP, true),
   EAST(EnumFacing.EAST, false),
   NORTH(EnumFacing.NORTH, false),
   FLIP_WEST(EnumFacing.WEST, true),
   DOWN(EnumFacing.DOWN, false),
   UP(EnumFacing.UP, false),
   FLIP_SOUTH(EnumFacing.SOUTH, true),
   SOUTH(EnumFacing.SOUTH, false),
   FLIP_EAST(EnumFacing.EAST, true);
   // $VF: synthetic field
   public static BlockModelRenderer$Orientation[] $VALUES = new BlockModelRenderer$Orientation[]{
      BlockModelRenderer$Orientation.DOWN,
      BlockModelRenderer$Orientation.UP,
      BlockModelRenderer$Orientation.NORTH,
      BlockModelRenderer$Orientation.SOUTH,
      WEST,
      BlockModelRenderer$Orientation.EAST,
      FLIP_DOWN,
      BlockModelRenderer$Orientation.FLIP_UP,
      BlockModelRenderer$Orientation.FLIP_NORTH,
      BlockModelRenderer$Orientation.FLIP_SOUTH,
      BlockModelRenderer$Orientation.FLIP_WEST,
      BlockModelRenderer$Orientation.FLIP_EAST
   };
   public ResourcePackRepository field_0009;
   public ItemBanner field_0003;
   public int field_178229_m;

   public BlockModelRenderer$Orientation(EnumFacing var3, boolean var4) {
      this.field_178229_m = var3.getIndex() + (var4 ? EnumFacing.values().length : 0);
   }
}
