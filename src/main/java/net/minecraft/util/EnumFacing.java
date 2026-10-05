package net.minecraft.util;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public enum EnumFacing implements IStringSerializable {
      DOWN(0, 1, -1, "down", EnumFacing.AxisDirection.NEGATIVE, EnumFacing.Axis.Y, new Vec3i(0, -1, 0)),
      UP(1, 0, -1, "up", EnumFacing.AxisDirection.POSITIVE, EnumFacing.Axis.Y, new Vec3i(0, 1, 0)),
      NORTH(2, 3, 2, "north", EnumFacing.AxisDirection.NEGATIVE, EnumFacing.Axis.Z, new Vec3i(0, 0, -1)),
      SOUTH(3, 2, 0, "south", EnumFacing.AxisDirection.POSITIVE, EnumFacing.Axis.Z, new Vec3i(0, 0, 1)),
      WEST(4, 5, 1, "west", EnumFacing.AxisDirection.NEGATIVE, EnumFacing.Axis.X, new Vec3i(-1, 0, 0)),
      EAST(5, 4, 3, "east", EnumFacing.AxisDirection.POSITIVE, EnumFacing.Axis.X, new Vec3i(1, 0, 0));
   public static EnumFacing[] $VALUES = new EnumFacing[]{DOWN, UP, NORTH, EnumFacing.SOUTH, WEST, EAST};
   public int opposite;
   public Vec3i directionVec;
   public String name;

   public static EnumFacing[] VALUES = new EnumFacing[6];
   public static EnumFacing[] HORIZONTALS = new EnumFacing[4];
   public EnumFacing.AxisDirection axisDirection;
   public int index;
   public EnumFacing.Axis axis;
   public static Map<String, EnumFacing> NAME_LOOKUP = Maps.newHashMap();
   public int horizontalIndex;

   public String getName2() {
      return this.name;
   }

   public static EnumFacing getFront(int var0) {
      return VALUES[MathHelper.abs_int(var0 % VALUES.length)];
   }

   public EnumFacing rotateY() {
      switch (this) {
         case NORTH:
            return EAST;
         case EAST:
            return SOUTH;
         case SOUTH:
            return WEST;
         case WEST:
            return NORTH;
         default:
            throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
      }
   }

   public EnumFacing rotateX() {
      switch (this) {
         case NORTH:
            return DOWN;
         case EAST:
         case WEST:
         default:
            throw new IllegalStateException("Unable to get X-rotated facing of " + this);
         case SOUTH:
            return UP;
         case UP:
            return NORTH;
         case DOWN:
            return SOUTH;
      }
   }

   public EnumFacing rotateZ() {
      switch (this) {
         case EAST:
            return DOWN;
         case SOUTH:
         default:
            throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
         case WEST:
            return UP;
         case UP:
            return EAST;
         case DOWN:
            return WEST;
      }
   }

   public int getHorizontalIndex() {
      return this.horizontalIndex;
   }

   public EnumFacing getOpposite() {
      return VALUES[this.opposite];
   }

   EnumFacing(int var3, int var4, int var5, String var6, EnumFacing.AxisDirection var7, EnumFacing.Axis var8, Vec3i var9) {
      this.index = var3;
      this.horizontalIndex = var5;
      this.opposite = var4;
      this.name = var6;
      this.axis = var8;
      this.axisDirection = var7;
      this.directionVec = var9;
   }

   public EnumFacing rotateYCCW() {
      switch (this) {
         case NORTH:
            return WEST;
         case EAST:
            return NORTH;
         case SOUTH:
            return EAST;
         case WEST:
            return SOUTH;
         default:
            throw new IllegalStateException("Unable to get CCW facing of " + this);
      }
   }

   public static EnumFacing random(Random var0) {
      return values()[var0.nextInt(values().length)];
   }

   public static EnumFacing getFacingFromAxis(EnumFacing.AxisDirection var0, EnumFacing.Axis var1) {
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
      return this.axis == EnumFacing.Axis.Z ? this.axisDirection.getOffset() : 0;
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

   public EnumFacing rotateAround(EnumFacing.Axis var1) {
      switch (var1) {
         case X:
            if (this != WEST && this != EAST) {
               return this.rotateX();
            }

            return this;
         case Y:
            if (this != UP && this != DOWN) {
               return this.rotateY();
            }

            return this;
         case Z:
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

   public EnumFacing.Axis getAxis() {
      return this.axis;
   }

   public EnumFacing.AxisDirection getAxisDirection() {
      return this.axisDirection;
   }

   public static EnumFacing byName(String var0) {
      return var0 == null ? null : NAME_LOOKUP.get(var0.toLowerCase());
   }

   public int getFrontOffsetY() {
      return this.axis == EnumFacing.Axis.Y ? this.axisDirection.getOffset() : 0;
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
      return this.axis == EnumFacing.Axis.X ? this.axisDirection.getOffset() : 0;
   }

   public static EnumFacing getHorizontal(int var0) {
      return HORIZONTALS[MathHelper.abs_int(var0 % HORIZONTALS.length)];
   }

   public static enum Axis implements IStringSerializable, Predicate<EnumFacing> {
      X("x", EnumFacing.Plane.HORIZONTAL),
      Y("y", EnumFacing.Plane.VERTICAL),
      Z("z", EnumFacing.Plane.HORIZONTAL);
      public EnumFacing.Plane plane;
      public String name;
      // $VF: synthetic field
      public static EnumFacing.Axis[] $VALUES = new EnumFacing.Axis[]{EnumFacing.Axis.X, EnumFacing.Axis.Y, Z};
      public static Map<String, EnumFacing.Axis> NAME_LOOKUP = Maps.newHashMap();

      public boolean isVertical() {
         return this.plane == EnumFacing.Plane.VERTICAL;
      }

      @Override
      public String getName() {
         return this.name;
      }

      public boolean apply(EnumFacing var1) {
         return var1 != null && var1.getAxis() == this;
      }

      @Override
      public String toString() {
         return this.name;
      }

      public boolean isHorizontal() {
         return this.plane == EnumFacing.Plane.HORIZONTAL;
      }

      public static EnumFacing.Axis byName(String var0) {
         return var0 == null ? null : NAME_LOOKUP.get(var0.toLowerCase());
      }

      public String getName2() {
         return this.name;
      }

      static {
         for (EnumFacing.Axis var3 : values()) {
            NAME_LOOKUP.put(var3.getName2().toLowerCase(), var3);
         }
      }

      public EnumFacing.Plane getPlane() {
         return this.plane;
      }

      Axis(String var3, EnumFacing.Plane var4) {
         this.name = var3;
         this.plane = var4;
      }
   }

   public static enum AxisDirection {
      POSITIVE(1, "Towards positive"),
      NEGATIVE(-1, "Towards negative");

      public int offset;
      public String description;

      public int getOffset() {
         return this.offset;
      }

      @Override
      public String toString() {
         return this.description;
      }

      AxisDirection(int var3, String var4) {
         this.offset = var3;
         this.description = var4;
      }
   }

   public static enum Plane implements Predicate<EnumFacing>, Iterable<EnumFacing> {
      HORIZONTAL,
      VERTICAL;
      // $VF: synthetic field
      public static EnumFacing.Plane[] $VALUES = new EnumFacing.Plane[]{HORIZONTAL, EnumFacing.Plane.VERTICAL};

      public boolean apply(EnumFacing var1) {
         return var1 != null && var1.getAxis().getPlane() == this;
      }

      public EnumFacing[] facings() {
         switch (this) {
            case HORIZONTAL:
               return new EnumFacing[]{EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST};
            case VERTICAL:
               return new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN};
            default:
               throw new Error("Someone's been tampering with the universe!");
         }
      }

      public EnumFacing random(Random var1) {
         EnumFacing[] var2 = this.facings();
         return var2[var1.nextInt(var2.length)];
      }

      @Override
      public Iterator<EnumFacing> iterator() {
         return Iterators.forArray(this.facings());
      }
   }
}
