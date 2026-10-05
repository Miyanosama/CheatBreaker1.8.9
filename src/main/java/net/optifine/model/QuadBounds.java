package net.optifine.model;

import net.minecraft.util.EnumFacing;

public class QuadBounds {
   public float minY;
   public float maxX;
   public float minX = Float.MAX_VALUE;
   public float maxY;
   public float maxZ;
   public float minZ;

   public QuadBounds(int[] var1) {
      this.minY = Float.MAX_VALUE;
      this.minZ = Float.MAX_VALUE;
      this.maxX = -Float.MAX_VALUE;
      this.maxY = -Float.MAX_VALUE;
      this.maxZ = -Float.MAX_VALUE;
      int var2 = var1.length / 4;

      for (int var3 = 0; var3 < 4; var3++) {
         int var4 = var3 * var2;
         float var5 = Float.intBitsToFloat(var1[var4 + 0]);
         float var6 = Float.intBitsToFloat(var1[var4 + 1]);
         float var7 = Float.intBitsToFloat(var1[var4 + 2]);
         if (this.minX > var5) {
            this.minX = var5;
         }

         if (this.minY > var6) {
            this.minY = var6;
         }

         if (this.minZ > var7) {
            this.minZ = var7;
         }

         if (this.maxX < var5) {
            this.maxX = var5;
         }

         if (this.maxY < var6) {
            this.maxY = var6;
         }

         if (this.maxZ < var7) {
            this.maxZ = var7;
         }
      }
   }

   public float getMaxZ() {
      return this.maxZ;
   }

   public float getMinX() {
      return this.minX;
   }

   public float getMinY() {
      return this.minY;
   }

   public float getMinZ() {
      return this.minZ;
   }

   public boolean isFaceQuad(EnumFacing var1) {
      float var2;
      float var3;
      float var4;
      switch (var1) {
         case DOWN:
            var2 = this.getMinY();
            var3 = this.getMaxY();
            var4 = 0.0F;
            break;
         case UP:
            var2 = this.getMinY();
            var3 = this.getMaxY();
            var4 = 1.0F;
            break;
         case NORTH:
            var2 = this.getMinZ();
            var3 = this.getMaxZ();
            var4 = 0.0F;
            break;
         case SOUTH:
            var2 = this.getMinZ();
            var3 = this.getMaxZ();
            var4 = 1.0F;
            break;
         case WEST:
            var2 = this.getMinX();
            var3 = this.getMaxX();
            var4 = 0.0F;
            break;
         case EAST:
            var2 = this.getMinX();
            var3 = this.getMaxX();
            var4 = 1.0F;
            break;
         default:
            return false;
      }

      return var2 == var4 && var3 == var4;
   }

   public float getMaxX() {
      return this.maxX;
   }

   public boolean isFullQuad(EnumFacing var1) {
      float var2;
      float var3;
      float var4;
      float var5;
      switch (var1) {
         case DOWN:
         case UP:
            var2 = this.getMinX();
            var3 = this.getMaxX();
            var4 = this.getMinZ();
            var5 = this.getMaxZ();
            break;
         case NORTH:
         case SOUTH:
            var2 = this.getMinX();
            var3 = this.getMaxX();
            var4 = this.getMinY();
            var5 = this.getMaxY();
            break;
         case WEST:
         case EAST:
            var2 = this.getMinY();
            var3 = this.getMaxY();
            var4 = this.getMinZ();
            var5 = this.getMaxZ();
            break;
         default:
            return false;
      }

      return var2 == 0.0F && var3 == 1.0F && var4 == 0.0F && var5 == 1.0F;
   }

   public float getMaxY() {
      return this.maxY;
   }
}
