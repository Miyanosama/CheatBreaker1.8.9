package net.optifine.shaders;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.minecraft.util.BlockPos;
import net.optifine.BlockPosM;

public class IteratorAxis implements Iterator<BlockPos> {
   public boolean hasNext;
   public double zNext;
   public double zStart;
   public double yNext;
   public double yDelta;
   public double yEnd;
   public double zDelta;
   public BlockPosM pos = new BlockPosM(0, 0, 0);
   public int xStart;
   public double zEnd;
   public int xNext;
   public double yStart;
   public int xEnd;

   public IteratorAxis(BlockPos var1, BlockPos var2, double var3, double var5) {
      this.hasNext = false;
      this.yDelta = var3;
      this.zDelta = var5;
      this.xStart = var1.getX();
      this.xEnd = var2.getX();
      this.yStart = var1.getY();
      this.yEnd = var2.getY() - 0.5;
      this.zStart = var1.getZ();
      this.zEnd = var2.getZ() - 0.5;
      this.xNext = this.xStart;
      this.yNext = this.yStart;
      this.zNext = this.zStart;
      this.hasNext = this.xNext < this.xEnd && this.yNext < this.yEnd && this.zNext < this.zEnd;
   }

   public void nextPos() {
      this.zNext++;
      if (this.zNext >= this.zEnd) {
         this.zNext = this.zStart;
         this.yNext++;
         if (this.yNext >= this.yEnd) {
            this.yNext = this.yStart;
            this.yStart = this.yStart + this.yDelta;
            this.yEnd = this.yEnd + this.yDelta;
            this.yNext = this.yStart;
            this.zStart = this.zStart + this.zDelta;
            this.zEnd = this.zEnd + this.zDelta;
            this.zNext = this.zStart;
            this.xNext++;
            if (this.xNext >= this.xEnd) {
            }
         }
      }
   }

   @Override
   public boolean hasNext() {
      return this.hasNext;
   }

   public static void main(String[] var0) throws java.lang.Exception {
      BlockPos var1 = new BlockPos(-2, 10, 20);
      BlockPos var2 = new BlockPos(2, 12, 22);
      double var3 = -0.5;
      double var5 = 0.5;
      IteratorAxis var7 = new IteratorAxis(var1, var2, var3, var5);
      System.out.println("Start: " + var1 + ", end: " + var2 + ", yDelta: " + var3 + ", zDelta: " + var5);

      while (var7.hasNext()) {
         BlockPos var8 = var7.next();
         System.out.println("" + var8);
      }
   }

   @Override
   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   public BlockPos next() {
      if (!this.hasNext) {
         throw new NoSuchElementException();
      } else {
         this.pos.setXyz((double)this.xNext, this.yNext, this.zNext);
         this.nextPos();
         this.hasNext = this.xNext < this.xEnd && this.yNext < this.yEnd && this.zNext < this.zEnd;
         return this.pos;
      }
   }
}
