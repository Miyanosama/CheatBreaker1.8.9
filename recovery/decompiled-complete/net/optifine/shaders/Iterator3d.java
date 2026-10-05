package net.optifine.shaders;

import java.util.Iterator;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.optifine.BlockPosM;
import net.optifine.shaders.config.ShaderParser;
import recovered.unidentified.UnidentifiedClass4343;

public class Iterator3d implements Iterator<BlockPos> {
   public int kZ;
   public int kY;
   public static int field_0004;
   public static int field_0007;
   public BlockPosM blockPos = new BlockPosM(0, 0, 0);
   public IteratorAxis iteratorAxis;
   public UnidentifiedClass4343 field_0009;
   public int axis = 0;
   public int kX;
   public ShaderParser field_0010;
   public static int field_0000;

   public Iterator3d(BlockPos var1, BlockPos var2, int var3, int var4) {
      boolean var5 = var1.getX() > var2.getX();
      boolean var6 = var1.getY() > var2.getY();
      boolean var7 = var1.getZ() > var2.getZ();
      var1 = this.reverseCoord(var1, var5, var6, var7);
      var2 = this.reverseCoord(var2, var5, var6, var7);
      this.kX = var5 ? -1 : 1;
      this.kY = var6 ? -1 : 1;
      this.kZ = var7 ? -1 : 1;
      Vec3 var8 = new Vec3(var2.getX() - var1.getX(), var2.getY() - var1.getY(), var2.getZ() - var1.getZ());
      Vec3 var9 = var8.normalize();
      Vec3 var10 = new Vec3(1.0, 0.0, 0.0);
      double var11 = var9.dotProduct(var10);
      double var13 = Math.abs(var11);
      Vec3 var15 = new Vec3(0.0, 1.0, 0.0);
      double var16 = var9.dotProduct(var15);
      double var18 = Math.abs(var16);
      Vec3 var20 = new Vec3(0.0, 0.0, 1.0);
      double var21 = var9.dotProduct(var20);
      double var23 = Math.abs(var21);
      if (var23 >= var18 && var23 >= var13) {
         this.axis = 2;
         BlockPos var35 = new BlockPos(var1.getZ(), var1.getY() - var3, var1.getX() - var4);
         BlockPos var37 = new BlockPos(var2.getZ(), var1.getY() + var3 + 1, var1.getX() + var4 + 1);
         int var39 = var2.getZ() - var1.getZ();
         double var41 = (var2.getY() - var1.getY()) / (1.0 * var39);
         double var43 = (var2.getX() - var1.getX()) / (1.0 * var39);
         this.iteratorAxis = new IteratorAxis(var35, var37, var41, var43);
      } else if (var18 >= var13 && var18 >= var23) {
         this.axis = 1;
         BlockPos var34 = new BlockPos(var1.getY(), var1.getX() - var3, var1.getZ() - var4);
         BlockPos var36 = new BlockPos(var2.getY(), var1.getX() + var3 + 1, var1.getZ() + var4 + 1);
         int var38 = var2.getY() - var1.getY();
         double var40 = (var2.getX() - var1.getX()) / (1.0 * var38);
         double var42 = (var2.getZ() - var1.getZ()) / (1.0 * var38);
         this.iteratorAxis = new IteratorAxis(var34, var36, var40, var42);
      } else {
         this.axis = 0;
         BlockPos var25 = new BlockPos(var1.getX(), var1.getY() - var3, var1.getZ() - var4);
         BlockPos var26 = new BlockPos(var2.getX(), var1.getY() + var3 + 1, var1.getZ() + var4 + 1);
         int var27 = var2.getX() - var1.getX();
         double var28 = (var2.getY() - var1.getY()) / (1.0 * var27);
         double var30 = (var2.getZ() - var1.getZ()) / (1.0 * var27);
         this.iteratorAxis = new IteratorAxis(var25, var26, var28, var30);
      }
   }

   public BlockPos reverseCoord(BlockPos var1, boolean var2, boolean var3, boolean var4) {
      if (var2) {
         var1 = new BlockPos(-var1.getX(), var1.getY(), var1.getZ());
      }

      if (var3) {
         var1 = new BlockPos(var1.getX(), -var1.getY(), var1.getZ());
      }

      if (var4) {
         var1 = new BlockPos(var1.getX(), var1.getY(), -var1.getZ());
      }

      return var1;
   }

   @Override
   public void remove() {
      throw new RuntimeException("Not supported");
   }

   @Override
   public boolean hasNext() {
      return this.iteratorAxis.hasNext();
   }

   public static void main(String[] var0) {
      BlockPos var1 = new BlockPos(10, 20, 30);
      BlockPos var2 = new BlockPos(30, 40, 20);
      Iterator3d var3 = new Iterator3d(var1, var2, 1, 1);

      while (var3.hasNext()) {
         BlockPos var4 = var3.next();
         System.out.println("" + var4);
      }
   }

   public BlockPos next() {
      BlockPos var1 = this.iteratorAxis.next();
      switch (this.axis) {
         case 0:
            this.blockPos.setXyz(var1.getX() * this.kX, var1.getY() * this.kY, var1.getZ() * this.kZ);
            return this.blockPos;
         case 1:
            this.blockPos.setXyz(var1.getY() * this.kX, var1.getX() * this.kY, var1.getZ() * this.kZ);
            return this.blockPos;
         case 2:
            this.blockPos.setXyz(var1.getZ() * this.kX, var1.getY() * this.kY, var1.getX() * this.kZ);
            return this.blockPos;
         default:
            this.blockPos.setXyz(var1.getX() * this.kX, var1.getY() * this.kY, var1.getZ() * this.kZ);
            return this.blockPos;
      }
   }
}
