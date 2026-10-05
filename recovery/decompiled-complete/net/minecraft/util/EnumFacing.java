package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import net.minecraft.client.renderer.texture.Stitcher$Slot;

public enum EnumFacing implements IStringSerializable {
   NORTH(2, 3, 2, "north", EnumFacing$AxisDirection.NEGATIVE, EnumFacing$Axis.Z, new Vec3i(0, 0, -1)),
   UP(1, 0, -1, "up", EnumFacing$AxisDirection.POSITIVE, EnumFacing$Axis.Y, new Vec3i(0, 1, 0)),
   WEST(4, 5, 1, "west", EnumFacing$AxisDirection.NEGATIVE, EnumFacing$Axis.X, new Vec3i(-1, 0, 0)),
   EAST(5, 4, 3, "east", EnumFacing$AxisDirection.POSITIVE, EnumFacing$Axis.X, new Vec3i(1, 0, 0)),
   DOWN(0, 1, -1, "down", EnumFacing$AxisDirection.NEGATIVE, EnumFacing$Axis.Y, new Vec3i(0, -1, 0)),
   SOUTH(3, 2, 0, "south", EnumFacing$AxisDirection.POSITIVE, EnumFacing$Axis.Z, new Vec3i(0, 0, 1));

   public static EnumFacing[] VALUES = new EnumFacing[6];
   public int opposite;
   public Vec3i directionVec;
   public String name;
   public CryptManager field_0012;
   // $VF: synthetic field
   public static EnumFacing[] $VALUES = new EnumFacing[]{DOWN, UP, NORTH, EnumFacing.SOUTH, WEST, EAST};
   public static Map<String, EnumFacing> NAME_LOOKUP = Maps.newHashMap();
   public EnumFacing$AxisDirection axisDirection;
   public Stitcher$Slot field_0006;
   public int index;
   public EnumFacing$Axis axis;
   public static EnumFacing[] HORIZONTALS = new EnumFacing[4];
   public int horizontalIndex;

   public String getName2() {
      return this.name;
   }

   public static EnumFacing getFront(int var0) {
      return VALUES[MathHelper.abs_int(var0 % VALUES.length)];
   }

   public EnumFacing rotateY() {
      switch (EnumFacing$1.$SwitchMap$net$minecraft$util$EnumFacing[this.ordinal()]) {
         case 1:
            return EAST;
         case 2:
            return SOUTH;
         case 3:
            return WEST;
         case 4:
            return NORTH;
         default:
            throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
      }
   }

   public EnumFacing rotateX() {
      switch (EnumFacing$1.$SwitchMap$net$minecraft$util$EnumFacing[this.ordinal()]) {
         case 1:
            return DOWN;
         case 2:
         case 4:
         default:
            throw new IllegalStateException("Unable to get X-rotated facing of " + this);
         case 3:
            return UP;
         case 5:
            return NORTH;
         case 6:
            return SOUTH;
      }
   }

   public EnumFacing rotateZ() {
      switch (EnumFacing$1.$SwitchMap$net$minecraft$util$EnumFacing[this.ordinal()]) {
         case 2:
            return DOWN;
         case 3:
         default:
            throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
         case 4:
            return UP;
         case 5:
            return EAST;
         case 6:
            return WEST;
      }
   }

   public int getHorizontalIndex() {
      return this.horizontalIndex;
   }

   public EnumFacing getOpposite() {
      return VALUES[this.opposite];
   }

   public EnumFacing(int var3, int var4, int var5, String var6, EnumFacing$AxisDirection var7, EnumFacing$Axis var8, Vec3i var9) {
      this.index = var3;
      this.horizontalIndex = var5;
      this.opposite = var4;
      this.name = var6;
      this.axis = var8;
      this.axisDirection = var7;
      this.directionVec = var9;
   }

   public EnumFacing rotateYCCW() {
      switch (EnumFacing$1.$SwitchMap$net$minecraft$util$EnumFacing[this.ordinal()]) {
         case 1:
            return WEST;
         case 2:
            return NORTH;
         case 3:
            return EAST;
         case 4:
            return SOUTH;
         default:
            throw new IllegalStateException("Unable to get CCW facing of " + this);
      }
   }

   public static EnumFacing random(Random var0) {
      return values()[var0.nextInt(values().length)];
   }

   public static EnumFacing getFacingFromAxis(EnumFacing$AxisDirection var0, EnumFacing$Axis var1) {
      for (EnumFacing var5 : values()) {
         if (var5.getAxisDirection() == var0 && var5.getAxis() == var1) {
            return var5;
         }
      }

      throw new IllegalArgumentException("No such direction: " + var0 + " " + var1);
   }

   public int getIndex() {
      return this.index;
   }

   public int getFrontOffsetZ() {
      return this.axis == EnumFacing$Axis.Z ? this.axisDirection.getOffset() : 0;
   }

   public Vec3i getDirectionVec() {
      return this.directionVec;
   }

   public static EnumFacing fromAngle(double var0) {
      return getHorizontal(MathHelper.floor_double(var0 / 90.0 + 0.5) & 3);
   }

   static {
      for (EnumFacing var3 : values()) {
         VALUES[var3.index] = var3;
         if (var3.getAxis().isHorizontal()) {
            HORIZONTALS[var3.horizontalIndex] = var3;
         }

         NAME_LOOKUP.put(var3.getName2().toLowerCase(), var3);
      }
   }

   public EnumFacing rotateAround(EnumFacing$Axis var1) {
      switch (EnumFacing$1.$SwitchMap$net$minecraft$util$EnumFacing$Axis[var1.ordinal()]) {
         case 1:
            if (this != WEST && this != EAST) {
               return this.rotateX();
            }

            return this;
         case 2:
            if (this != UP && this != DOWN) {
               return this.rotateY();
            }

            return this;
         case 3:
            if (this != NORTH && this != SOUTH) {
               return this.rotateZ();
            }

            return this;
         default:
            throw new IllegalStateException("Unable to get CW facing for axis " + var1);
      }
   }

   @Override
   public String toString() {
      return this.name;
   }

   public EnumFacing$Axis getAxis() {
      return this.axis;
   }

   public EnumFacing$AxisDirection getAxisDirection() {
      return this.axisDirection;
   }

   public static EnumFacing byName(String var0) {
      return var0 == null ? null : NAME_LOOKUP.get(var0.toLowerCase());
   }

   public int getFrontOffsetY() {
      return this.axis == EnumFacing$Axis.Y ? this.axisDirection.getOffset() : 0;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public static EnumFacing getFacingFromVector(float var0, float var1, float var2) {
      EnumFacing var3 = NORTH;
      float var4 = Float.MIN_VALUE;

      for (EnumFacing var8 : values()) {
         float var9 = var0 * var8.directionVec.getX() + var1 * var8.directionVec.getY() + var2 * var8.directionVec.getZ();
         if (var9 > var4) {
            var4 = var9;
            var3 = var8;
         }
      }

      return var3;
   }

   public int getFrontOffsetX() {
      return this.axis == EnumFacing$Axis.X ? this.axisDirection.getOffset() : 0;
   }

   public static EnumFacing getHorizontal(int var0) {
      return HORIZONTALS[MathHelper.abs_int(var0 % HORIZONTALS.length)];
   }
}
