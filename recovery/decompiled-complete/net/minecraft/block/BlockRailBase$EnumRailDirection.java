package net.minecraft.block;

import io.netty.channel.PendingWriteQueue$PendingWrite$1;
import net.minecraft.client.renderer.RenderGlobal$ContainerLocalRenderInformation;
import net.minecraft.util.IStringSerializable;

public enum BlockRailBase$EnumRailDirection implements IStringSerializable {
   NORTH_EAST(9, "north_east"),
   SOUTH_WEST(7, "south_west"),
   NORTH_SOUTH(0, "north_south"),
   EAST_WEST(1, "east_west"),
   SOUTH_EAST(6, "south_east"),
   ASCENDING_NORTH(4, "ascending_north"),
   ASCENDING_EAST(2, "ascending_east"),
   ASCENDING_WEST(3, "ascending_west"),
   ASCENDING_SOUTH(5, "ascending_south"),
   NORTH_WEST(8, "north_west");
   public PendingWriteQueue$PendingWrite$1 field_0001;
   public static BlockRailBase$EnumRailDirection[] META_LOOKUP = new BlockRailBase$EnumRailDirection[values().length];
   // $VF: synthetic field
   public static BlockRailBase$EnumRailDirection[] $VALUES = new BlockRailBase$EnumRailDirection[]{
      NORTH_SOUTH,
      EAST_WEST,
      BlockRailBase$EnumRailDirection.ASCENDING_EAST,
      BlockRailBase$EnumRailDirection.ASCENDING_WEST,
      BlockRailBase$EnumRailDirection.ASCENDING_NORTH,
      BlockRailBase$EnumRailDirection.ASCENDING_SOUTH,
      BlockRailBase$EnumRailDirection.SOUTH_EAST,
      SOUTH_WEST,
      BlockRailBase$EnumRailDirection.NORTH_WEST,
      NORTH_EAST
   };
   public String name;
   public RenderGlobal$ContainerLocalRenderInformation field_0003;
   public int meta;

   public BlockRailBase$EnumRailDirection(int var3, String var4) {
      this.meta = var3;
      this.name = var4;
   }

   public int getMetadata() {
      return this.meta;
   }

   public boolean isAscending() {
      return this == ASCENDING_NORTH || this == ASCENDING_EAST || this == ASCENDING_SOUTH || this == ASCENDING_WEST;
   }

   public static BlockRailBase$EnumRailDirection byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.name;
   }

   static {
      for (BlockRailBase$EnumRailDirection var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }
}
