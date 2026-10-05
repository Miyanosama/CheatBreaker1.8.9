package javax.vecmath;

import java.io.Serializable;
import javax.vecmath.VecMathUtil;

public class GVector implements Serializable, Cloneable {
   public static final long recoveredField2531 = 1398850036893875112L;
   public int length;
   public double[] values;

   public void interpolate(GVector var1, float var2) {
      this.interpolate(var1, (double)var2);
   }

   public void setElement(int var1, double var2) {
      this.values[var1] = var2;
   }

   public void add(GVector var1) {
      if (this.length != var1.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector4"));
      } else {
         for (int var2 = 0; var2 < this.length; var2++) {
            this.values[var2] = this.values[var2] + var1.values[var2];
         }
      }
   }

   public void normalize() {
      double var1 = 0.0;

      for (int var3 = 0; var3 < this.length; var3++) {
         var1 += this.values[var3] * this.values[var3];
      }

      double var4 = 1.0 / Math.sqrt(var1);

      for (int var6 = 0; var6 < this.length; var6++) {
         this.values[var6] = this.values[var6] * var4;
      }
   }

   public GVector(double[] var1, int var2) {
      this.length = var2;
      this.values = new double[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         this.values[var3] = var1[var3];
      }
   }

   @Override
   public int hashCode() {
      long var1 = 1L;

      for (int var3 = 0; var3 < this.length; var3++) {
         var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.values[var3]);
      }

      return (int)(var1 ^ var1 >> 32);
   }

   public double normSquared() {
      double var1 = 0.0;

      for (int var3 = 0; var3 < this.length; var3++) {
         var1 += this.values[var3] * this.values[var3];
      }

      return var1;
   }

   public void set(GVector var1) {
      if (this.length < var1.length) {
         this.length = var1.length;
         this.values = new double[this.length];

         for (int var2 = 0; var2 < this.length; var2++) {
            this.values[var2] = var1.values[var2];
         }
      } else {
         for (int var3 = 0; var3 < var1.length; var3++) {
            this.values[var3] = var1.values[var3];
         }

         for (int var4 = var1.length; var4 < this.length; var4++) {
            this.values[var4] = 0.0;
         }
      }
   }

   public void scale(double var1, GVector var3) {
      if (this.length != var3.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector1"));
      } else {
         for (int var4 = 0; var4 < this.length; var4++) {
            this.values[var4] = var3.values[var4] * var1;
         }
      }
   }

   public GVector(GVector var1) {
      this.values = new double[var1.length];
      this.length = var1.length;

      for (int var2 = 0; var2 < this.length; var2++) {
         this.values[var2] = var1.values[var2];
      }
   }

   public void normalize(GVector var1) {
      double var2 = 0.0;
      if (this.length != var1.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector0"));
      } else {
         for (int var4 = 0; var4 < this.length; var4++) {
            var2 += var1.values[var4] * var1.values[var4];
         }

         double var5 = 1.0 / Math.sqrt(var2);

         for (int var7 = 0; var7 < this.length; var7++) {
            this.values[var7] = var1.values[var7] * var5;
         }
      }
   }

   public GVector(Tuple4f var1) {
      this.values = new double[4];
      this.values[0] = var1.x;
      this.values[1] = var1.y;
      this.values[2] = var1.z;
      this.values[3] = var1.w;
      this.length = 4;
   }

   public double getElement(int var1) {
      return this.values[var1];
   }

   public double angle(GVector var1) {
      return Math.acos(this.dot(var1) / (this.norm() * var1.norm()));
   }

   public GVector(Tuple3f var1) {
      this.values = new double[3];
      this.values[0] = var1.x;
      this.values[1] = var1.y;
      this.values[2] = var1.z;
      this.length = 3;
   }

   public void scaleAdd(double var1, GVector var3, GVector var4) {
      if (var4.length != var3.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector2"));
      } else if (this.length != var3.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector3"));
      } else {
         for (int var5 = 0; var5 < this.length; var5++) {
            this.values[var5] = var3.values[var5] * var1 + var4.values[var5];
         }
      }
   }

   @Override
   public Object clone() {
      GVector var1 = null;

      try {
         var1 = (GVector)super.clone();
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }

      var1.values = new double[this.length];

      for (int var2 = 0; var2 < this.length; var2++) {
         var1.values[var2] = this.values[var2];
      }

      return var1;
   }

   public void set(Tuple3d var1) {
      if (this.length < 3) {
         this.length = 3;
         this.values = new double[3];
      }

      this.values[0] = var1.x;
      this.values[1] = var1.y;
      this.values[2] = var1.z;

      for (int var2 = 3; var2 < this.length; var2++) {
         this.values[var2] = 0.0;
      }
   }

   public int getSize() {
      return this.values.length;
   }

   public void scale(double var1) {
      for (int var3 = 0; var3 < this.length; var3++) {
         this.values[var3] = this.values[var3] * var1;
      }
   }

   public void interpolate(GVector var1, double var2) {
      if (var1.length != this.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector22"));
      } else {
         for (int var4 = 0; var4 < this.length; var4++) {
            this.values[var4] = (1.0 - var2) * this.values[var4] + var2 * var1.values[var4];
         }
      }
   }

   public void LUDBackSolve(GMatrix var1, GVector var2, GVector var3) {
      int var4 = var1.nRow * var1.nCol;
      double[] var5 = new double[var4];
      double[] var6 = new double[var4];
      int[] var7 = new int[var2.getSize()];
      if (var1.nRow != var2.getSize()) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector16"));
      } else if (var1.nRow != var3.getSize()) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector24"));
      } else if (var1.nRow != var1.nCol) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector25"));
      } else {
         for (int var8 = 0; var8 < var1.nRow; var8++) {
            for (int var9 = 0; var9 < var1.nCol; var9++) {
               var5[var8 * var1.nCol + var9] = var1.values[var8][var9];
            }
         }

         for (int var10 = 0; var10 < var4; var10++) {
            var6[var10] = 0.0;
         }

         for (int var11 = 0; var11 < var1.nRow; var11++) {
            var6[var11 * var1.nCol] = var2.values[var11];
         }

         for (int var12 = 0; var12 < var1.nCol; var12++) {
            var7[var12] = (int)var3.values[var12];
         }

         GMatrix.luBacksubstitution(var1.nRow, var5, var7, var6);

         for (int var13 = 0; var13 < var1.nRow; var13++) {
            this.values[var13] = var6[var13 * var1.nCol];
         }
      }
   }

   public void SVDBackSolve(GMatrix var1, GMatrix var2, GMatrix var3, GVector var4) {
      if (var1.nRow != var4.getSize() || var1.nRow != var1.nCol || var1.nRow != var2.nRow) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector15"));
      } else if (var2.nCol == this.values.length && var2.nCol == var3.nCol && var2.nCol == var3.nRow) {
         GMatrix var5 = new GMatrix(var1.nRow, var2.nCol);
         var5.mul(var1, var3);
         var5.mulTransposeRight(var1, var2);
         var5.invert();
         this.mul(var5, var4);
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector23"));
      }
   }

   public void set(Tuple3f var1) {
      if (this.length < 3) {
         this.length = 3;
         this.values = new double[3];
      }

      this.values[0] = var1.x;
      this.values[1] = var1.y;
      this.values[2] = var1.z;

      for (int var2 = 3; var2 < this.length; var2++) {
         this.values[var2] = 0.0;
      }
   }

   public void mul(GVector var1, GMatrix var2) {
      if (var2.getNumRow() != var1.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector12"));
      } else if (this.length != var2.getNumCol()) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector13"));
      } else {
         double[] var3;
         if (var1 != this) {
            var3 = var1.values;
         } else {
            var3 = (double[])this.values.clone();
         }

         for (int var4 = this.length - 1; var4 >= 0; var4--) {
            this.values[var4] = 0.0;

            for (int var5 = var1.length - 1; var5 >= 0; var5--) {
               this.values[var4] = this.values[var4] + var2.values[var5][var4] * var3[var5];
            }
         }
      }
   }

   @Override
   public String toString() {
      StringBuffer var1 = new StringBuffer(this.length * 8);

      for (int var2 = 0; var2 < this.length; var2++) {
         var1.append(this.values[var2]).append(" ");
      }

      return var1.toString();
   }

   public boolean epsilonEquals(GVector var1, double var2) {
      if (this.length != var1.length) {
         return false;
      } else {
         for (int var6 = 0; var6 < this.length; var6++) {
            double var4 = this.values[var6] - var1.values[var6];
            if ((var4 < 0.0 ? -var4 : var4) > var2) {
               return false;
            }
         }

         return true;
      }
   }

   public void interpolate(GVector var1, GVector var2, double var3) {
      if (var2.length != var1.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector20"));
      } else if (this.length != var1.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector21"));
      } else {
         for (int var5 = 0; var5 < this.length; var5++) {
            this.values[var5] = (1.0 - var3) * var1.values[var5] + var3 * var2.values[var5];
         }
      }
   }

   public GVector(Tuple3d var1) {
      this.values = new double[3];
      this.values[0] = var1.x;
      this.values[1] = var1.y;
      this.values[2] = var1.z;
      this.length = 3;
   }

   public void negate() {
      for (int var1 = this.length - 1; var1 >= 0; var1--) {
         this.values[var1] = this.values[var1] * -1.0;
      }
   }

   public GVector(Tuple4d var1) {
      this.values = new double[4];
      this.values[0] = var1.x;
      this.values[1] = var1.y;
      this.values[2] = var1.z;
      this.values[3] = var1.w;
      this.length = 4;
   }

   public void mul(GMatrix var1, GVector var2) {
      if (var1.getNumCol() != var2.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector10"));
      } else if (this.length != var1.getNumRow()) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector11"));
      } else {
         double[] var3;
         if (var2 != this) {
            var3 = var2.values;
         } else {
            var3 = (double[])this.values.clone();
         }

         for (int var4 = this.length - 1; var4 >= 0; var4--) {
            this.values[var4] = 0.0;

            for (int var5 = var2.length - 1; var5 >= 0; var5--) {
               this.values[var4] = this.values[var4] + var1.values[var4][var5] * var3[var5];
            }
         }
      }
   }

   public GVector(double[] var1) {
      this.length = var1.length;
      this.values = new double[var1.length];

      for (int var2 = 0; var2 < this.length; var2++) {
         this.values[var2] = var1[var2];
      }
   }

   @Override
   public boolean equals(Object var1) {
      try {
         GVector var2 = (GVector)var1;
         if (this.length != var2.length) {
            return false;
         } else {
            for (int var3 = 0; var3 < this.length; var3++) {
               if (this.values[var3] != var2.values[var3]) {
                  return false;
               }
            }

            return true;
         }
      } catch (ClassCastException var4) {
         return false;
      } catch (NullPointerException var5) {
         return false;
      }
   }

   public void zero() {
      for (int var1 = 0; var1 < this.length; var1++) {
         this.values[var1] = 0.0;
      }
   }

   public void set(Tuple2f var1) {
      if (this.length < 2) {
         this.length = 2;
         this.values = new double[2];
      }

      this.values[0] = var1.x;
      this.values[1] = var1.y;

      for (int var2 = 2; var2 < this.length; var2++) {
         this.values[var2] = 0.0;
      }
   }

   public void sub(GVector var1, GVector var2) {
      if (var1.length != var2.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector8"));
      } else if (this.length != var1.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector9"));
      } else {
         for (int var3 = 0; var3 < this.length; var3++) {
            this.values[var3] = var1.values[var3] - var2.values[var3];
         }
      }
   }

   public void add(GVector var1, GVector var2) {
      if (var1.length != var2.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector5"));
      } else if (this.length != var1.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector6"));
      } else {
         for (int var3 = 0; var3 < this.length; var3++) {
            this.values[var3] = var1.values[var3] + var2.values[var3];
         }
      }
   }

   public void set(double[] var1) {
      for (int var2 = this.length - 1; var2 >= 0; var2--) {
         this.values[var2] = var1[var2];
      }
   }

   public GVector(int var1) {
      this.length = var1;
      this.values = new double[var1];

      for (int var2 = 0; var2 < var1; var2++) {
         this.values[var2] = 0.0;
      }
   }

   public void setSize(int var1) {
      double[] var2 = new double[var1];
      int var4;
      if (this.length < var1) {
         var4 = this.length;
      } else {
         var4 = var1;
      }

      for (int var3 = 0; var3 < var4; var3++) {
         var2[var3] = this.values[var3];
      }

      this.length = var1;
      this.values = var2;
   }

   public void set(Tuple4f var1) {
      if (this.length < 4) {
         this.length = 4;
         this.values = new double[4];
      }

      this.values[0] = var1.x;
      this.values[1] = var1.y;
      this.values[2] = var1.z;
      this.values[3] = var1.w;

      for (int var2 = 4; var2 < this.length; var2++) {
         this.values[var2] = 0.0;
      }
   }

   public void set(Tuple4d var1) {
      if (this.length < 4) {
         this.length = 4;
         this.values = new double[4];
      }

      this.values[0] = var1.x;
      this.values[1] = var1.y;
      this.values[2] = var1.z;
      this.values[3] = var1.w;

      for (int var2 = 4; var2 < this.length; var2++) {
         this.values[var2] = 0.0;
      }
   }

   public double dot(GVector var1) {
      if (this.length != var1.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector14"));
      } else {
         double var2 = 0.0;

         for (int var4 = 0; var4 < this.length; var4++) {
            var2 += this.values[var4] * var1.values[var4];
         }

         return var2;
      }
   }

   public void interpolate(GVector var1, GVector var2, float var3) {
      this.interpolate(var1, var2, (double)var3);
   }

   public boolean equals(GVector var1) {
      try {
         if (this.length != var1.length) {
            return false;
         } else {
            for (int var2 = 0; var2 < this.length; var2++) {
               if (this.values[var2] != var1.values[var2]) {
                  return false;
               }
            }

            return true;
         }
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public GVector(Tuple2f var1) {
      this.values = new double[2];
      this.values[0] = var1.x;
      this.values[1] = var1.y;
      this.length = 2;
   }

   public void sub(GVector var1) {
      if (this.length != var1.length) {
         throw new MismatchedSizeException(VecMathI18N.getString("GVector7"));
      } else {
         for (int var2 = 0; var2 < this.length; var2++) {
            this.values[var2] = this.values[var2] - var1.values[var2];
         }
      }
   }

   public double norm() {
      double var1 = 0.0;

      for (int var3 = 0; var3 < this.length; var3++) {
         var1 += this.values[var3] * this.values[var3];
      }

      return Math.sqrt(var1);
   }
}
