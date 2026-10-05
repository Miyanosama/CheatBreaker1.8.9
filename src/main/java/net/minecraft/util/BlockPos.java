package net.minecraft.util;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import net.minecraft.entity.Entity;

public class BlockPos extends Vec3i {
   public static BlockPos ORIGIN = new BlockPos(0, 0, 0);
   public static int NUM_X_BITS = 1 + MathHelper.calculateLogBaseTwo(MathHelper.roundUpToPowerOfTwo(30000000));
   public static int NUM_Z_BITS = BlockPos.NUM_X_BITS;
   public static int NUM_Y_BITS = 64 - BlockPos.NUM_X_BITS - BlockPos.NUM_Z_BITS;
   public static int Y_SHIFT = 0 + NUM_Z_BITS;
   public static int X_SHIFT = BlockPos.Y_SHIFT + NUM_Y_BITS;
   public static long X_MASK = (1L << NUM_X_BITS) - 1L;
   public static long Y_MASK = (1L << BlockPos.NUM_Y_BITS) - 1L;
   public static long Z_MASK = (1L << NUM_Z_BITS) - 1L;

   public BlockPos north() {
      return this.north(1);
   }

   public BlockPos down() {
      return this.down(1);
   }

   public BlockPos south(int var1) {
      return this.a(EnumFacing.SOUTH, var1);
   }

   public BlockPos(Vec3i var1) {
      this(var1.getX(), var1.getY(), var1.getZ());
   }

   public static Iterable<BlockPos.MutableBlockPos> getAllInBoxMutable(BlockPos var0, BlockPos var1) {
      final BlockPos var2 = new BlockPos(Math.min(var0.getX(), var1.getX()), Math.min(var0.getY(), var1.getY()), Math.min(var0.getZ(), var1.getZ()));
      final BlockPos var3 = new BlockPos(Math.max(var0.getX(), var1.getX()), Math.max(var0.getY(), var1.getY()), Math.max(var0.getZ(), var1.getZ()));
      return new Iterable<BlockPos.MutableBlockPos>() {
         @Override
         public Iterator<BlockPos.MutableBlockPos> iterator() {
            return new AbstractIterator<BlockPos.MutableBlockPos>() {
               public BlockPos.MutableBlockPos theBlockPos = null;

               public BlockPos.MutableBlockPos computeNext() {
                  if (this.theBlockPos == null) {
                     this.theBlockPos = new BlockPos.MutableBlockPos(var2.getX(), var2.getY(), var2.getZ());
                     return this.theBlockPos;
                  } else if (this.theBlockPos.equals(var3)) {
                     return this.endOfData();
                  } else {
                     int var1x = this.theBlockPos.getX();
                     int var2x = this.theBlockPos.getY();
                     int var3x = this.theBlockPos.getZ();
                     if (var1x < var3.getX()) {
                        var1x++;
                     } else if (var2x < var3.getY()) {
                        var1x = var2.getX();
                        var2x++;
                     } else if (var3x < var3.getZ()) {
                        var1x = var2.getX();
                        var2x = var2.getY();
                        var3x++;
                     }

                     this.theBlockPos.x = var1x;
                     this.theBlockPos.y = var2x;
                     this.theBlockPos.z = var3x;
                     return this.theBlockPos;
                  }
               }
            };
         }
      };
   }

   public long toLong() {
      return (this.getX() & X_MASK) << X_SHIFT | (this.getY() & Y_MASK) << Y_SHIFT | (this.getZ() & Z_MASK) << 0;
   }

   public BlockPos up() {
      return this.up(1);
   }

   public BlockPos subtract(Vec3i var1) {
      return var1.getX() == 0 && var1.getY() == 0 && var1.getZ() == 0
         ? this
         : new BlockPos(this.getX() - var1.getX(), this.getY() - var1.getY(), this.getZ() - var1.getZ());
   }

   public BlockPos(Entity var1) {
      this(var1.s, var1.t, var1.u);
   }

   public static Iterable<BlockPos> getAllInBox(BlockPos var0, BlockPos var1) {
      final BlockPos var2 = new BlockPos(Math.min(var0.getX(), var1.getX()), Math.min(var0.getY(), var1.getY()), Math.min(var0.getZ(), var1.getZ()));
      final BlockPos var3 = new BlockPos(Math.max(var0.getX(), var1.getX()), Math.max(var0.getY(), var1.getY()), Math.max(var0.getZ(), var1.getZ()));
      return new Iterable<BlockPos>() {
         @Override
         public Iterator<BlockPos> iterator() {
            return new AbstractIterator<BlockPos>() {
               public BlockPos lastReturned = null;

               public BlockPos computeNext() {
                  if (this.lastReturned == null) {
                     this.lastReturned = var2;
                     return this.lastReturned;
                  } else if (this.lastReturned.equals(var3)) {
                     return this.endOfData();
                  } else {
                     int var1x = this.lastReturned.getX();
                     int var2x = this.lastReturned.getY();
                     int var3x = this.lastReturned.getZ();
                     if (var1x < var3.getX()) {
                        var1x++;
                     } else if (var2x < var3.getY()) {
                        var1x = var2.getX();
                        var2x++;
                     } else if (var3x < var3.getZ()) {
                        var1x = var2.getX();
                        var2x = var2.getY();
                        var3x++;
                     }

                     this.lastReturned = new BlockPos(var1x, var2x, var3x);
                     return this.lastReturned;
                  }
               }
            };
         }
      };
   }

   public BlockPos add(Vec3i var1) {
      return var1.getX() == 0 && var1.getY() == 0 && var1.getZ() == 0
         ? this
         : new BlockPos(this.getX() + var1.getX(), this.getY() + var1.getY(), this.getZ() + var1.getZ());
   }

   public BlockPos add(double var1, double var3, double var5) {
      return var1 == 0.0 && var3 == 0.0 && var5 == 0.0 ? this : new BlockPos(this.getX() + var1, this.getY() + var3, this.getZ() + var5);
   }

   public BlockPos(int var1, int var2, int var3) {
      super(var1, var2, var3);
   }

   public BlockPos up(int var1) {
      return this.a(EnumFacing.UP, var1);
   }

   public BlockPos north(int var1) {
      return this.a(EnumFacing.NORTH, var1);
   }

   public BlockPos east(int var1) {
      return this.a(EnumFacing.EAST, var1);
   }

   public BlockPos south() {
      return this.south(1);
   }

   public BlockPos down(int var1) {
      return this.a(EnumFacing.DOWN, var1);
   }

   public BlockPos a(EnumFacing var1) {
      return this.a(var1, 1);
   }

   public BlockPos west(int var1) {
      return this.a(EnumFacing.WEST, var1);
   }

   public BlockPos east() {
      return this.east(1);
   }

   public static BlockPos fromLong(long var0) {
      int var2 = (int)(var0 << 64 - X_SHIFT - NUM_X_BITS >> 64 - NUM_X_BITS);
      int var3 = (int)(var0 << 64 - Y_SHIFT - NUM_Y_BITS >> 64 - NUM_Y_BITS);
      int var4 = (int)(var0 << 64 - NUM_Z_BITS >> 64 - NUM_Z_BITS);
      return new BlockPos(var2, var3, var4);
   }

   public BlockPos a(EnumFacing var1, int var2) {
      return var2 == 0
         ? this
         : new BlockPos(this.getX() + var1.getFrontOffsetX() * var2, this.getY() + var1.getFrontOffsetY() * var2, this.getZ() + var1.getFrontOffsetZ() * var2);
   }

   public BlockPos west() {
      return this.west(1);
   }

   public BlockPos(double var1, double var3, double var5) {
      super(var1, var3, var5);
   }

   public BlockPos crossProduct(Vec3i var1) {
      return new BlockPos(
         this.getY() * var1.getZ() - this.getZ() * var1.getY(),
         this.getZ() * var1.getX() - this.getX() * var1.getZ(),
         this.getX() * var1.getY() - this.getY() * var1.getX()
      );
   }

   public BlockPos add(int var1, int var2, int var3) {
      return var1 == 0 && var2 == 0 && var3 == 0 ? this : new BlockPos(this.getX() + var1, this.getY() + var2, this.getZ() + var3);
   }

   public BlockPos(Vec3 var1) {
      this(var1.xCoord, var1.yCoord, var1.zCoord);
   }

   public static final class MutableBlockPos extends BlockPos {
      public int y;
      public int x;
      public int z;

      @Override
      public int getY() {
         return this.y;
      }

      public MutableBlockPos() {
         this(0, 0, 0);
      }

      public BlockPos.MutableBlockPos set(int var1, int var2, int var3) {
         this.x = var1;
         this.y = var2;
         this.z = var3;
         return this;
      }

      public MutableBlockPos(int var1, int var2, int var3) {
         super(0, 0, 0);
         this.x = var1;
         this.y = var2;
         this.z = var3;
      }

      @Override
      public int getX() {
         return this.x;
      }

      @Override
      public int getZ() {
         return this.z;
      }
   }
}
