package net.minecraft.block;

import io.netty.handler.codec.socks.SocksAuthStatus;
import io.netty.util.AttributeKey;
import net.minecraft.server.network.NetHandlerStatusServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum BlockLever$EnumOrientation implements IStringSerializable {
   UP_X(6, "up_x", EnumFacing.UP),
   SOUTH(3, "south", EnumFacing.SOUTH),
   UP_Z(5, "up_z", EnumFacing.UP),
   WEST(2, "west", EnumFacing.WEST),
   DOWN_Z(7, "down_z", EnumFacing.DOWN),
   DOWN_X(0, "down_x", EnumFacing.DOWN),
   EAST(1, "east", EnumFacing.EAST),
   NORTH(4, "north", EnumFacing.NORTH);
   public NetHandlerStatusServer field_0005;
   public static BlockLever$EnumOrientation[] META_LOOKUP = new BlockLever$EnumOrientation[values().length];
   public String name;
   public EnumFacing facing;
   public SocksAuthStatus field_0000;
   // $VF: synthetic field
   public static BlockLever$EnumOrientation[] $VALUES = new BlockLever$EnumOrientation[]{
      DOWN_X, BlockLever$EnumOrientation.EAST, WEST, SOUTH, BlockLever$EnumOrientation.NORTH, UP_Z, UP_X, DOWN_Z
   };
   public AttributeKey field_0004;
   public int meta;

   public BlockLever$EnumOrientation(int var3, String var4, EnumFacing var5) {
      this.meta = var3;
      this.name = var4;
      this.facing = var5;
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   static {
      for (BlockLever$EnumOrientation var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   @Override
   public String toString() {
      return this.name;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public static BlockLever$EnumOrientation forFacings(EnumFacing var0, EnumFacing var1) {
      switch (BlockLever$1.field_180165_a[var0.ordinal()]) {
         case 1:
            switch (BlockLever$1.field_180164_c[var1.getAxis().ordinal()]) {
               case 1:
                  return DOWN_X;
               case 2:
                  return DOWN_Z;
               default:
                  throw new IllegalArgumentException("Invalid entityFacing " + var1 + " for facing " + var0);
            }
         case 2:
            switch (BlockLever$1.field_180164_c[var1.getAxis().ordinal()]) {
               case 1:
                  return UP_X;
               case 2:
                  return UP_Z;
               default:
                  throw new IllegalArgumentException("Invalid entityFacing " + var1 + " for facing " + var0);
            }
         case 3:
            return NORTH;
         case 4:
            return SOUTH;
         case 5:
            return WEST;
         case 6:
            return EAST;
         default:
            throw new IllegalArgumentException("Invalid facing: " + var0);
      }
   }

   public static BlockLever$EnumOrientation byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   public int getMetadata() {
      return this.meta;
   }
}
