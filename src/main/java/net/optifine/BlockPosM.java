package net.optifine;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;

public class BlockPosM extends BlockPos {
   public boolean needsUpdate;
   public int mx;
   public int mz;
   public int my;
   public int level;
   public BlockPosM[] facings;

   public static Iterable getAllInBoxMutable(BlockPos var0, BlockPos var1) {
      final BlockPos var2 = new BlockPos(Math.min(var0.getX(), var1.getX()), Math.min(var0.getY(), var1.getY()), Math.min(var0.getZ(), var1.getZ()));
      final BlockPos var3 = new BlockPos(Math.max(var0.getX(), var1.getX()), Math.max(var0.getY(), var1.getY()), Math.max(var0.getZ(), var1.getZ()));
      return new Iterable() {
         @Override
         public Iterator iterator() {
            return new AbstractIterator() {
               public BlockPosM theBlockPosM = null;

               public BlockPosM computeNext0() {
                  if (this.theBlockPosM == null) {
                     this.theBlockPosM = new BlockPosM(var2.getX(), var2.getY(), var2.getZ(), 3);
                     return this.theBlockPosM;
                  } else if (this.theBlockPosM.equals(var3)) {
                     return (BlockPosM)this.endOfData();
                  } else {
                     int var1x = this.theBlockPosM.getX();
                     int var2x = this.theBlockPosM.getY();
                     int var3x = this.theBlockPosM.getZ();
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

                     this.theBlockPosM.setXyz(var1x, var2x, var3x);
                     return this.theBlockPosM;
                  }
               }

               @Override
               public Object computeNext() {
                  return this.computeNext0();
               }
            };
         }
      };
   }

   public BlockPosM set(int var1, int var2, int var3) {
      this.setXyz(var1, var2, var3);
      return this;
   }

   public BlockPos toImmutable() {
      return new BlockPos(this.mx, this.my, this.mz);
   }

   public BlockPosM(int var1, int var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public BlockPosM set(Vec3i var1) {
      this.setXyz(var1.getX(), var1.getY(), var1.getZ());
      return this;
   }

   public BlockPos offsetMutable(EnumFacing var1) {
      return this.a(var1);
   }

   @Override
   public int getX() {
      return this.mx;
   }

   @Override
   public int getY() {
      return this.my;
   }

   public void update() {
      for (int var1 = 0; var1 < 6; var1++) {
         BlockPosM var2 = this.facings[var1];
         if (var2 != null) {
            EnumFacing var3 = EnumFacing.VALUES[var1];
            int var4 = this.mx + var3.getFrontOffsetX();
            int var5 = this.my + var3.getFrontOffsetY();
            int var6 = this.mz + var3.getFrontOffsetZ();
            var2.setXyz(var4, var5, var6);
         }
      }

      this.needsUpdate = false;
   }

   public void setXyz(int var1, int var2, int var3) {
      this.mx = var1;
      this.my = var2;
      this.mz = var3;
      this.needsUpdate = true;
   }

   public void setXyz(double var1, double var3, double var5) {
      this.setXyz(MathHelper.floor_double(var1), MathHelper.floor_double(var3), MathHelper.floor_double(var5));
   }

   public BlockPosM(int var1, int var2, int var3, int var4) {
      super(0, 0, 0);
      this.mx = var1;
      this.my = var2;
      this.mz = var3;
      this.level = var4;
   }

   @Override
   public BlockPos a(EnumFacing var1) {
      if (this.level <= 0) {
         return super.a(var1, 1);
      } else {
         if (this.facings == null) {
            this.facings = new BlockPosM[EnumFacing.VALUES.length];
         }

         if (this.needsUpdate) {
            this.update();
         }

         int var2 = var1.getIndex();
         BlockPosM var3 = this.facings[var2];
         if (var3 == null) {
            int var4 = this.mx + var1.getFrontOffsetX();
            int var5 = this.my + var1.getFrontOffsetY();
            int var6 = this.mz + var1.getFrontOffsetZ();
            var3 = new BlockPosM(var4, var5, var6, this.level - 1);
            this.facings[var2] = var3;
         }

         return var3;
      }
   }

   public BlockPosM(double var1, double var3, double var5) {
      this(MathHelper.floor_double(var1), MathHelper.floor_double(var3), MathHelper.floor_double(var5));
   }

   @Override
   public int getZ() {
      return this.mz;
   }

   @Override
   public BlockPos a(EnumFacing var1, int var2) {
      return var2 == 1 ? this.a(var1) : super.a(var1, var2);
   }
}
