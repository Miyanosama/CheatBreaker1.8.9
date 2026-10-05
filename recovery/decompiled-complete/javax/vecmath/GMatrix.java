package javax.vecmath;

import java.io.Serializable;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.realms.RealmsScrolledSelectionList;
import recovered.unidentified.UnidentifiedClass1276;

public class GMatrix implements Serializable, Cloneable {
   public static boolean field_0003;
   public double[][] values;
   public static double field_0002;
   public EntityAIRestrictSun field_0005;
   public int nCol;
   public static long field_0001;
   public RealmsScrolledSelectionList field_0007;
   public int nRow;

   public static void print_svd(double[] var0, double[] var1, GMatrix var2, GMatrix var3) {
      GMatrix var5 = new GMatrix(var2.nCol, var3.nRow);
      System.out.println(" \ns = ");

      for (int var4 = 0; var4 < var0.length; var4++) {
         System.out.println(" " + var0[var4]);
      }

      System.out.println(" \ne = ");

      for (int var6 = 0; var6 < var1.length; var6++) {
         System.out.println(" " + var1[var6]);
      }

      System.out.println(" \nu  = \n" + var2.toString());
      System.out.println(" \nv  = \n" + var3.toString());
      var5.setIdentity();

      for (int var7 = 0; var7 < var0.length; var7++) {
         var5.values[var7][var7] = var0[var7];
      }

      for (int var8 = 0; var8 < var1.length; var8++) {
         var5.values[var8][var8 + 1] = var1[var8];
      }

      System.out.println(" \nm  = \n" + var5.toString());
      var5.mulTransposeLeft(var2, var5);
      var5.mulTransposeRight(var5, var3);
      System.out.println(" \n u.transpose*m*v.transpose  = \n" + var5.toString());
   }

   public void set(double[] var1) {
      for (int var2 = 0; var2 < this.nRow; var2++) {
         for (int var3 = 0; var3 < this.nCol; var3++) {
            this.values[var2][var3] = var1[this.nCol * var2 + var3];
         }
      }
   }

   public static void update_v_split(int var0, int var1, GMatrix var2, double[] var3, double[] var4, GMatrix var5, GMatrix var6) {
      for (int var7 = 0; var7 < var2.nRow; var7++) {
         double var8 = var2.values[var7][var0];
         var2.values[var7][var0] = var3[0] * var8 - var4[0] * var2.values[var7][var1];
         var2.values[var7][var1] = var4[0] * var8 + var3[0] * var2.values[var7][var1];
      }

      System.out.println("topr    =" + var0);
      System.out.println("bottomr =" + var1);
      System.out.println("cosr =" + var3[0]);
      System.out.println("sinr =" + var4[0]);
      System.out.println("\nm =");
      checkMatrix(var6);
      System.out.println("\nv =");
      checkMatrix(var5);
      var6.mul(var6, var5);
      System.out.println("\nt*m =");
      checkMatrix(var6);
   }

   public static int computeSVD(GMatrix var0, GMatrix var1, GMatrix var2, GMatrix var3) {
      GMatrix var27 = new GMatrix(var0.nRow, var0.nCol);
      GMatrix var28 = new GMatrix(var0.nRow, var0.nCol);
      GMatrix var29 = new GMatrix(var0.nRow, var0.nCol);
      GMatrix var30 = new GMatrix(var0);
      int var24;
      int var25;
      if (var30.nRow >= var30.nCol) {
         var25 = var30.nCol;
         var24 = var30.nCol - 1;
      } else {
         var25 = var30.nRow;
         var24 = var30.nRow;
      }

      int var26;
      if (var30.nRow > var30.nCol) {
         var26 = var30.nRow;
      } else {
         var26 = var30.nCol;
      }

      double[] var31 = new double[var26];
      double[] var32 = new double[var25];
      double[] var33 = new double[var24];
      boolean var11 = false;
      var1.setIdentity();
      var3.setIdentity();
      int var7 = var30.nRow;
      int var8 = var30.nCol;

      for (int var9 = 0; var9 < var25; var9++) {
         if (var7 > 1) {
            double var18 = 0.0;

            for (int var4 = 0; var4 < var7; var4++) {
               var18 += var30.values[var4 + var9][var9] * var30.values[var4 + var9][var9];
            }

            var18 = Math.sqrt(var18);
            if (var30.values[var9][var9] == 0.0) {
               var31[0] = var18;
            } else {
               var31[0] = var30.values[var9][var9] + d_sign(var18, var30.values[var9][var9]);
            }

            for (int var38 = 1; var38 < var7; var38++) {
               var31[var38] = var30.values[var9 + var38][var9];
            }

            double var20 = 0.0;

            for (int var39 = 0; var39 < var7; var39++) {
               var20 += var31[var39] * var31[var39];
            }

            var20 = 2.0 / var20;

            for (int var5 = var9; var5 < var30.nRow; var5++) {
               for (int var6 = var9; var6 < var30.nRow; var6++) {
                  var28.values[var5][var6] = -var20 * var31[var5 - var9] * var31[var6 - var9];
               }
            }

            for (int var40 = var9; var40 < var30.nRow; var40++) {
               var28.values[var40][var40]++;
            }

            double var22 = 0.0;

            for (int var41 = var9; var41 < var30.nRow; var41++) {
               var22 += var28.values[var9][var41] * var30.values[var41][var9];
            }

            var30.values[var9][var9] = var22;

            for (int var53 = var9; var53 < var30.nRow; var53++) {
               for (int var62 = var9 + 1; var62 < var30.nCol; var62++) {
                  var27.values[var53][var62] = 0.0;

                  for (int var42 = var9; var42 < var30.nCol; var42++) {
                     var27.values[var53][var62] = var27.values[var53][var62] + var28.values[var53][var42] * var30.values[var42][var62];
                  }
               }
            }

            for (int var54 = var9; var54 < var30.nRow; var54++) {
               for (int var63 = var9 + 1; var63 < var30.nCol; var63++) {
                  var30.values[var54][var63] = var27.values[var54][var63];
               }
            }

            for (int var55 = var9; var55 < var30.nRow; var55++) {
               for (int var64 = 0; var64 < var30.nCol; var64++) {
                  var27.values[var55][var64] = 0.0;

                  for (int var43 = var9; var43 < var30.nCol; var43++) {
                     var27.values[var55][var64] = var27.values[var55][var64] + var28.values[var55][var43] * var1.values[var43][var64];
                  }
               }
            }

            for (int var56 = var9; var56 < var30.nRow; var56++) {
               for (int var65 = 0; var65 < var30.nCol; var65++) {
                  var1.values[var56][var65] = var27.values[var56][var65];
               }
            }

            var7--;
         }

         if (var8 > 2) {
            double var72 = 0.0;

            for (int var44 = 1; var44 < var8; var44++) {
               var72 += var30.values[var9][var9 + var44] * var30.values[var9][var9 + var44];
            }

            var72 = Math.sqrt(var72);
            if (var30.values[var9][var9 + 1] == 0.0) {
               var31[0] = var72;
            } else {
               var31[0] = var30.values[var9][var9 + 1] + d_sign(var72, var30.values[var9][var9 + 1]);
            }

            for (int var45 = 1; var45 < var8 - 1; var45++) {
               var31[var45] = var30.values[var9][var9 + var45 + 1];
            }

            double var75 = 0.0;

            for (int var46 = 0; var46 < var8 - 1; var46++) {
               var75 += var31[var46] * var31[var46];
            }

            var75 = 2.0 / var75;

            for (int var57 = var9 + 1; var57 < var8; var57++) {
               for (int var66 = var9 + 1; var66 < var30.nCol; var66++) {
                  var29.values[var57][var66] = -var75 * var31[var57 - var9 - 1] * var31[var66 - var9 - 1];
               }
            }

            for (int var47 = var9 + 1; var47 < var30.nCol; var47++) {
               var29.values[var47][var47]++;
            }

            double var77 = 0.0;

            for (int var48 = var9; var48 < var30.nCol; var48++) {
               var77 += var29.values[var48][var9 + 1] * var30.values[var9][var48];
            }

            var30.values[var9][var9 + 1] = var77;

            for (int var58 = var9 + 1; var58 < var30.nRow; var58++) {
               for (int var67 = var9 + 1; var67 < var30.nCol; var67++) {
                  var27.values[var58][var67] = 0.0;

                  for (int var49 = var9 + 1; var49 < var30.nCol; var49++) {
                     var27.values[var58][var67] = var27.values[var58][var67] + var29.values[var49][var67] * var30.values[var58][var49];
                  }
               }
            }

            for (int var59 = var9 + 1; var59 < var30.nRow; var59++) {
               for (int var68 = var9 + 1; var68 < var30.nCol; var68++) {
                  var30.values[var59][var68] = var27.values[var59][var68];
               }
            }

            for (int var60 = 0; var60 < var30.nRow; var60++) {
               for (int var69 = var9 + 1; var69 < var30.nCol; var69++) {
                  var27.values[var60][var69] = 0.0;

                  for (int var50 = var9 + 1; var50 < var30.nCol; var50++) {
                     var27.values[var60][var69] = var27.values[var60][var69] + var29.values[var50][var69] * var3.values[var60][var50];
                  }
               }
            }

            for (int var61 = 0; var61 < var30.nRow; var61++) {
               for (int var70 = var9 + 1; var70 < var30.nCol; var70++) {
                  var3.values[var61][var70] = var27.values[var61][var70];
               }
            }

            var8--;
         }
      }

      for (int var51 = 0; var51 < var25; var51++) {
         var32[var51] = var30.values[var51][var51];
      }

      for (int var52 = 0; var52 < var24; var52++) {
         var33[var52] = var30.values[var52][var52 + 1];
      }

      if (var30.nRow == 2 && var30.nCol == 2) {
         double[] var34 = new double[1];
         double[] var35 = new double[1];
         double[] var36 = new double[1];
         double[] var37 = new double[1];
         compute_2X2(var32[0], var33[0], var32[1], var32, var36, var34, var37, var35, 0);
         update_u(0, var1, var34, var36);
         update_v(0, var3, var35, var37);
         return 2;
      } else {
         compute_qr(0, var33.length - 1, var32, var33, var1, var3);
         return var32.length;
      }
   }

   public GMatrix(int var1, int var2, double[] var3) {
      this.values = new double[var1][var2];
      this.nRow = var1;
      this.nCol = var2;

      for (int var4 = 0; var4 < var1; var4++) {
         for (int var5 = 0; var5 < var2; var5++) {
            this.values[var4][var5] = var3[var4 * var2 + var5];
         }
      }
   }

   public void setSize(int var1, int var2) {
      double[][] var3 = new double[var1][var2];
      int var6;
      if (this.nRow < var1) {
         var6 = this.nRow;
      } else {
         var6 = var1;
      }

      int var7;
      if (this.nCol < var2) {
         var7 = this.nCol;
      } else {
         var7 = var2;
      }

      for (int var4 = 0; var4 < var6; var4++) {
         for (int var5 = 0; var5 < var7; var5++) {
            var3[var4][var5] = this.values[var4][var5];
         }
      }

      this.nRow = var1;
      this.nCol = var2;
      this.values = var3;
   }

   public void get(Matrix4f var1) {
      if (this.nRow >= 4 && this.nCol >= 4) {
         var1.m00 = (float)this.values[0][0];
         var1.m01 = (float)this.values[0][1];
         var1.m02 = (float)this.values[0][2];
         var1.m03 = (float)this.values[0][3];
         var1.m10 = (float)this.values[1][0];
         var1.m11 = (float)this.values[1][1];
         var1.m12 = (float)this.values[1][2];
         var1.m13 = (float)this.values[1][3];
         var1.m20 = (float)this.values[2][0];
         var1.m21 = (float)this.values[2][1];
         var1.m22 = (float)this.values[2][2];
         var1.m23 = (float)this.values[2][3];
         var1.m30 = (float)this.values[3][0];
         var1.m31 = (float)this.values[3][1];
         var1.m32 = (float)this.values[3][2];
         var1.m33 = (float)this.values[3][3];
      } else {
         var1.setZero();
         if (this.nCol > 0) {
            if (this.nRow > 0) {
               var1.m00 = (float)this.values[0][0];
               if (this.nRow > 1) {
                  var1.m10 = (float)this.values[1][0];
                  if (this.nRow > 2) {
                     var1.m20 = (float)this.values[2][0];
                     if (this.nRow > 3) {
                        var1.m30 = (float)this.values[3][0];
                     }
                  }
               }
            }

            if (this.nCol > 1) {
               if (this.nRow > 0) {
                  var1.m01 = (float)this.values[0][1];
                  if (this.nRow > 1) {
                     var1.m11 = (float)this.values[1][1];
                     if (this.nRow > 2) {
                        var1.m21 = (float)this.values[2][1];
                        if (this.nRow > 3) {
                           var1.m31 = (float)this.values[3][1];
                        }
                     }
                  }
               }

               if (this.nCol > 2) {
                  if (this.nRow > 0) {
                     var1.m02 = (float)this.values[0][2];
                     if (this.nRow > 1) {
                        var1.m12 = (float)this.values[1][2];
                        if (this.nRow > 2) {
                           var1.m22 = (float)this.values[2][2];
                           if (this.nRow > 3) {
                              var1.m32 = (float)this.values[3][2];
                           }
                        }
                     }
                  }

                  if (this.nCol > 3 && this.nRow > 0) {
                     var1.m03 = (float)this.values[0][3];
                     if (this.nRow > 1) {
                        var1.m13 = (float)this.values[1][3];
                        if (this.nRow > 2) {
                           var1.m23 = (float)this.values[2][3];
                           if (this.nRow > 3) {
                              var1.m33 = (float)this.values[3][3];
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void mul(GMatrix var1, GMatrix var2) {
      if (var1.nCol == var2.nRow && this.nRow == var1.nRow && this.nCol == var2.nCol) {
         double[][] var6 = new double[this.nRow][this.nCol];

         for (int var3 = 0; var3 < var1.nRow; var3++) {
            for (int var4 = 0; var4 < var2.nCol; var4++) {
               var6[var3][var4] = 0.0;

               for (int var5 = 0; var5 < var1.nCol; var5++) {
                  var6[var3][var4] = var6[var3][var4] + var1.values[var3][var5] * var2.values[var5][var4];
               }
            }
         }

         this.values = var6;
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix1"));
      }
   }

   public void mul(GMatrix var1) {
      if (this.nCol == var1.nRow && this.nCol == var1.nCol) {
         double[][] var5 = new double[this.nRow][this.nCol];

         for (int var2 = 0; var2 < this.nRow; var2++) {
            for (int var3 = 0; var3 < this.nCol; var3++) {
               var5[var2][var3] = 0.0;

               for (int var4 = 0; var4 < this.nCol; var4++) {
                  var5[var2][var3] = var5[var2][var3] + this.values[var2][var4] * var1.values[var4][var3];
               }
            }
         }

         this.values = var5;
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix0"));
      }
   }

   public static double compute_shift(double var0, double var2, double var4) {
      double var16 = Math.abs(var0);
      double var18 = Math.abs(var2);
      double var20 = Math.abs(var4);
      double var10 = min(var16, var20);
      double var12 = max(var16, var20);
      double var28;
      if (var10 == 0.0) {
         var28 = 0.0;
         if (var12 != 0.0) {
            double var6 = min(var12, var18) / max(var12, var18);
         }
      } else if (var18 < var12) {
         double var22 = var10 / var12 + 1.0;
         double var24 = (var12 - var10) / var12;
         double var30 = var18 / var12;
         double var26 = var30 * var30;
         double var14 = 2.0 / (Math.sqrt(var22 * var22 + var26) + Math.sqrt(var24 * var24 + var26));
         var28 = var10 * var14;
      } else {
         double var35 = var12 / var18;
         if (var35 == 0.0) {
            var28 = var10 * var12 / var18;
         } else {
            double var33 = var10 / var12 + 1.0;
            double var34 = (var12 - var10) / var12;
            double var31 = var33 * var35;
            double var8 = var34 * var35;
            double var32 = 1.0 / (Math.sqrt(var31 * var31 + 1.0) + Math.sqrt(var8 * var8 + 1.0));
            var28 = var10 * var32 * var35;
            var28 += var28;
         }
      }

      return var28;
   }

   public boolean epsilonEquals(GMatrix var1, double var2) {
      if (this.nRow == var1.nRow && this.nCol == var1.nCol) {
         for (int var4 = 0; var4 < this.nRow; var4++) {
            for (int var5 = 0; var5 < this.nCol; var5++) {
               double var6 = this.values[var4][var5] - var1.values[var4][var5];
               if ((var6 < 0.0 ? -var6 : var6) > var2) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void negate(GMatrix var1) {
      if (this.nRow == var1.nRow && this.nCol == var1.nCol) {
         for (int var2 = 0; var2 < this.nRow; var2++) {
            for (int var3 = 0; var3 < this.nCol; var3++) {
               this.values[var2][var3] = -var1.values[var2][var3];
            }
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix13"));
      }
   }

   public static double compute_rot(double var0, double var2, double[] var4, double[] var5) {
      double var25 = 2.002083095183101E-146;
      double var27 = 4.994797680505588E145;
      double var11;
      double var13;
      double var23;
      if (var2 == 0.0) {
         var11 = 1.0;
         var13 = 0.0;
         var23 = var0;
      } else if (var0 == 0.0) {
         var11 = 0.0;
         var13 = 1.0;
         var23 = var2;
      } else {
         double var19 = var0;
         double var21 = var2;
         double var16 = max(Math.abs(var0), Math.abs(var2));
         if (var16 >= 4.994797680505588E145) {
            int var30;
            for (var30 = 0; var16 >= 4.994797680505588E145; var16 = max(Math.abs(var19), Math.abs(var21))) {
               var30++;
               var19 *= 2.002083095183101E-146;
               var21 *= 2.002083095183101E-146;
            }

            var23 = Math.sqrt(var19 * var19 + var21 * var21);
            var11 = var19 / var23;
            var13 = var21 / var23;

            for (int var29 = 1; var29 <= var30; var29++) {
               var23 *= 4.994797680505588E145;
            }
         } else if (!(var16 <= 2.002083095183101E-146)) {
            var23 = Math.sqrt(var0 * var0 + var2 * var2);
            var11 = var0 / var23;
            var13 = var2 / var23;
         } else {
            int var18;
            for (var18 = 0; var16 <= 2.002083095183101E-146; var16 = max(Math.abs(var19), Math.abs(var21))) {
               var18++;
               var19 *= 4.994797680505588E145;
               var21 *= 4.994797680505588E145;
            }

            var23 = Math.sqrt(var19 * var19 + var21 * var21);
            var11 = var19 / var23;
            var13 = var21 / var23;

            for (int var15 = 1; var15 <= var18; var15++) {
               var23 *= 2.002083095183101E-146;
            }
         }

         if (Math.abs(var0) > Math.abs(var2) && var11 < 0.0) {
            var11 = -var11;
            var13 = -var13;
            var23 = -var23;
         }
      }

      var4[0] = var13;
      var5[0] = var11;
      return var23;
   }

   public static int compute_2X2(double var0, double var2, double var4, double[] var6, double[] var7, double[] var8, double[] var9, double[] var10, int var11) {
      double var12 = 2.0;
      double var14 = 1.0;
      double var65 = var6[0];
      double var63 = var6[1];
      double var55 = 0.0;
      double var57 = 0.0;
      double var59 = 0.0;
      double var61 = 0.0;
      double var36 = 0.0;
      double var44 = var0;
      double var38 = Math.abs(var0);
      double var48 = var4;
      double var42 = Math.abs(var4);
      byte var18 = 1;
      boolean var21;
      if (var42 > var38) {
         var21 = true;
      } else {
         var21 = false;
      }

      if (var21) {
         var18 = 3;
         var44 = var4;
         var48 = var0;
         double var19 = var38;
         var38 = var42;
         var42 = var19;
      }

      double var40 = Math.abs(var2);
      if (var40 == 0.0) {
         var6[1] = var42;
         var6[0] = var38;
         var55 = 1.0;
         var57 = 1.0;
         var59 = 0.0;
         var61 = 0.0;
      } else {
         boolean var52 = true;
         if (var40 > var38) {
            var18 = 2;
            if (var38 / var40 < 1.0E-10) {
               var52 = false;
               var65 = var40;
               if (var42 > 1.0) {
                  var63 = var38 / (var40 / var42);
               } else {
                  var63 = var38 / var40 * var42;
               }

               var55 = 1.0;
               var59 = var48 / var2;
               var61 = 1.0;
               var57 = var44 / var2;
            }
         }

         if (var52) {
            double var24 = var38 - var42;
            double var26;
            if (var24 == var38) {
               var26 = 1.0;
            } else {
               var26 = var24 / var38;
            }

            double var28 = var2 / var44;
            double var34 = 2.0 - var26;
            double var50 = var28 * var28;
            double var53 = var34 * var34;
            double var32 = Math.sqrt(var53 + var50);
            double var30;
            if (var26 == 0.0) {
               var30 = Math.abs(var28);
            } else {
               var30 = Math.sqrt(var26 * var26 + var50);
            }

            double var22 = (var32 + var30) * 0.5;
            if (var40 > var38) {
               var18 = 2;
               if (var38 / var40 < 1.0E-10) {
                  var52 = false;
                  var65 = var40;
                  if (var42 > 1.0) {
                     var63 = var38 / (var40 / var42);
                  } else {
                     var63 = var38 / var40 * var42;
                  }

                  var55 = 1.0;
                  var59 = var48 / var2;
                  var61 = 1.0;
                  var57 = var44 / var2;
               }
            }

            if (var52) {
               var24 = var38 - var42;
               if (var24 == var38) {
                  var26 = 1.0;
               } else {
                  var26 = var24 / var38;
               }

               var28 = var2 / var44;
               var34 = 2.0 - var26;
               var50 = var28 * var28;
               var53 = var34 * var34;
               var32 = Math.sqrt(var53 + var50);
               if (var26 == 0.0) {
                  var30 = Math.abs(var28);
               } else {
                  var30 = Math.sqrt(var26 * var26 + var50);
               }

               var22 = (var32 + var30) * 0.5;
               var63 = var42 / var22;
               var65 = var38 * var22;
               if (var50 == 0.0) {
                  if (var26 == 0.0) {
                     var34 = d_sign(var12, var44) * d_sign(var14, var2);
                  } else {
                     var34 = var2 / d_sign(var24, var44) + var28 / var34;
                  }
               } else {
                  var34 = (var28 / (var32 + var34) + var28 / (var30 + var26)) * (var22 + 1.0);
               }

               var26 = Math.sqrt(var34 * var34 + 4.0);
               var57 = 2.0 / var26;
               var61 = var34 / var26;
               var55 = (var57 + var61 * var28) / var22;
               var59 = var48 / var44 * var61 / var22;
            }
         }

         if (var21) {
            var8[0] = var61;
            var7[0] = var57;
            var10[0] = var59;
            var9[0] = var55;
         } else {
            var8[0] = var55;
            var7[0] = var59;
            var10[0] = var57;
            var9[0] = var61;
         }

         if (var18 == 1) {
            var36 = d_sign(var14, var10[0]) * d_sign(var14, var8[0]) * d_sign(var14, var0);
         }

         if (var18 == 2) {
            var36 = d_sign(var14, var9[0]) * d_sign(var14, var8[0]) * d_sign(var14, var2);
         }

         if (var18 == 3) {
            var36 = d_sign(var14, var9[0]) * d_sign(var14, var7[0]) * d_sign(var14, var4);
         }

         var6[var11] = d_sign(var65, var36);
         double var16 = var36 * d_sign(var14, var0) * d_sign(var14, var4);
         var6[var11 + 1] = d_sign(var63, var16);
      }

      return 0;
   }

   @Override
   public boolean equals(Object var1) {
      try {
         GMatrix var2 = (GMatrix)var1;
         if (this.nRow == var2.nRow && this.nCol == var2.nCol) {
            for (int var3 = 0; var3 < this.nRow; var3++) {
               for (int var4 = 0; var4 < this.nCol; var4++) {
                  if (this.values[var3][var4] != var2.values[var3][var4]) {
                     return false;
                  }
               }
            }

            return true;
         } else {
            return false;
         }
      } catch (ClassCastException var5) {
         return false;
      } catch (NullPointerException var6) {
         return false;
      }
   }

   public void invert(GMatrix var1) {
      this.invertGeneral(var1);
   }

   public void add(GMatrix var1) {
      if (this.nRow != var1.nRow) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix4"));
      } else if (this.nCol != var1.nCol) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix5"));
      } else {
         for (int var2 = 0; var2 < this.nRow; var2++) {
            for (int var3 = 0; var3 < this.nCol; var3++) {
               this.values[var2][var3] = this.values[var2][var3] + var1.values[var2][var3];
            }
         }
      }
   }

   public int SVD(GMatrix var1, GMatrix var2, GMatrix var3) {
      if (this.nCol != var3.nCol || this.nCol != var3.nRow) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix18"));
      } else if (this.nRow != var1.nRow || this.nRow != var1.nCol) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix25"));
      } else if (this.nRow == var2.nRow && this.nCol == var2.nCol) {
         if (this.nRow == 2 && this.nCol == 2 && this.values[1][0] == 0.0) {
            var1.setIdentity();
            var3.setIdentity();
            if (this.values[0][1] == 0.0) {
               return 2;
            } else {
               double[] var4 = new double[1];
               double[] var5 = new double[1];
               double[] var6 = new double[1];
               double[] var7 = new double[1];
               double[] var8 = new double[]{this.values[0][0], this.values[1][1]};
               compute_2X2(this.values[0][0], this.values[0][1], this.values[1][1], var8, var4, var6, var5, var7, 0);
               update_u(0, var1, var6, var4);
               update_v(0, var3, var7, var5);
               return 2;
            }
         } else {
            return computeSVD(this, var1, var2, var3);
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix26"));
      }
   }

   public void setRow(int var1, double[] var2) {
      for (int var3 = 0; var3 < this.nCol; var3++) {
         this.values[var1][var3] = var2[var3];
      }
   }

   public void get(GMatrix var1) {
      int var4;
      if (this.nCol < var1.nCol) {
         var4 = this.nCol;
      } else {
         var4 = var1.nCol;
      }

      int var5;
      if (this.nRow < var1.nRow) {
         var5 = this.nRow;
      } else {
         var5 = var1.nRow;
      }

      for (int var2 = 0; var2 < var5; var2++) {
         for (int var3 = 0; var3 < var4; var3++) {
            var1.values[var2][var3] = this.values[var2][var3];
         }
      }

      for (int var6 = var5; var6 < var1.nRow; var6++) {
         for (int var8 = 0; var8 < var1.nCol; var8++) {
            var1.values[var6][var8] = 0.0;
         }
      }

      for (int var9 = var4; var9 < var1.nCol; var9++) {
         for (int var7 = 0; var7 < var5; var7++) {
            var1.values[var7][var9] = 0.0;
         }
      }
   }

   public void set(GMatrix var1) {
      if (this.nRow < var1.nRow || this.nCol < var1.nCol) {
         this.nRow = var1.nRow;
         this.nCol = var1.nCol;
         this.values = new double[this.nRow][this.nCol];
      }

      for (int var2 = 0; var2 < Math.min(this.nRow, var1.nRow); var2++) {
         for (int var3 = 0; var3 < Math.min(this.nCol, var1.nCol); var3++) {
            this.values[var2][var3] = var1.values[var2][var3];
         }
      }

      for (int var4 = var1.nRow; var4 < this.nRow; var4++) {
         for (int var5 = var1.nCol; var5 < this.nCol; var5++) {
            this.values[var4][var5] = 0.0;
         }
      }
   }

   public void setScale(double var1) {
      int var5;
      if (this.nRow < this.nCol) {
         var5 = this.nRow;
      } else {
         var5 = this.nCol;
      }

      for (int var3 = 0; var3 < this.nRow; var3++) {
         for (int var4 = 0; var4 < this.nCol; var4++) {
            this.values[var3][var4] = 0.0;
         }
      }

      for (int var6 = 0; var6 < var5; var6++) {
         this.values[var6][var6] = var1;
      }
   }

   public int LUD(GMatrix var1, GVector var2) {
      int var3 = var1.nRow * var1.nCol;
      double[] var4 = new double[var3];
      int[] var5 = new int[1];
      int[] var6 = new int[var1.nRow];
      if (this.nRow != this.nCol) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix19"));
      } else if (this.nRow != var1.nRow) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix27"));
      } else if (this.nCol != var1.nCol) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix27"));
      } else if (var1.nRow != var2.getSize()) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix20"));
      } else {
         for (int var7 = 0; var7 < this.nRow; var7++) {
            for (int var8 = 0; var8 < this.nCol; var8++) {
               var4[var7 * this.nCol + var8] = this.values[var7][var8];
            }
         }

         if (!luDecomposition(var1.nRow, var4, var6, var5)) {
            throw new SingularMatrixException(VecMathI18N.getString("GMatrix21"));
         } else {
            for (int var9 = 0; var9 < this.nRow; var9++) {
               for (int var11 = 0; var11 < this.nCol; var11++) {
                  var1.values[var9][var11] = var4[var9 * this.nCol + var11];
               }
            }

            for (int var10 = 0; var10 < var1.nRow; var10++) {
               var2.values[var10] = var6[var10];
            }

            return var5[0];
         }
      }
   }

   public void sub(GMatrix var1) {
      if (this.nRow != var1.nRow) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix9"));
      } else if (this.nCol != var1.nCol) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix28"));
      } else {
         for (int var2 = 0; var2 < this.nRow; var2++) {
            for (int var3 = 0; var3 < this.nCol; var3++) {
               this.values[var2][var3] = this.values[var2][var3] - var1.values[var2][var3];
            }
         }
      }
   }

   public void mulTransposeRight(GMatrix var1, GMatrix var2) {
      if (var1.nCol == var2.nCol && this.nCol == var2.nRow && this.nRow == var1.nRow) {
         if (var1 != this && var2 != this) {
            for (int var7 = 0; var7 < this.nRow; var7++) {
               for (int var8 = 0; var8 < this.nCol; var8++) {
                  this.values[var7][var8] = 0.0;

                  for (int var9 = 0; var9 < var1.nCol; var9++) {
                     this.values[var7][var8] = this.values[var7][var8] + var1.values[var7][var9] * var2.values[var8][var9];
                  }
               }
            }
         } else {
            double[][] var6 = new double[this.nRow][this.nCol];

            for (int var3 = 0; var3 < this.nRow; var3++) {
               for (int var4 = 0; var4 < this.nCol; var4++) {
                  var6[var3][var4] = 0.0;

                  for (int var5 = 0; var5 < var1.nCol; var5++) {
                     var6[var3][var4] = var6[var3][var4] + var1.values[var3][var5] * var2.values[var4][var5];
                  }
               }
            }

            this.values = var6;
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix15"));
      }
   }

   @Override
   public int hashCode() {
      long var1 = -4259223511625819597L & 71311681L;
      var1 = (1608379720544559263L & -1608379722363103937L) * var1 + this.nRow;
      var1 = (1610760287L & 22287039L) * var1 + this.nCol;

      for (int var3 = 0; var3 < this.nRow; var3++) {
         for (int var4 = 0; var4 < this.nCol; var4++) {
            var1 = (1275136831L & 286277727L) * var1 + UnidentifiedClass1276.method_08549(this.values[var3][var4]);
         }
      }

      return (int)(var1 ^ var1 >> 32);
   }

   public int getNumRow() {
      return this.nRow;
   }

   @Override
   public Object clone() {
      GMatrix var1 = null;

      try {
         var1 = (GMatrix)super.clone();
      } catch (CloneNotSupportedException var4) {
         throw new InternalError();
      }

      var1.values = new double[this.nRow][this.nCol];

      for (int var2 = 0; var2 < this.nRow; var2++) {
         for (int var3 = 0; var3 < this.nCol; var3++) {
            var1.values[var2][var3] = this.values[var2][var3];
         }
      }

      return var1;
   }

   public void setIdentity() {
      for (int var1 = 0; var1 < this.nRow; var1++) {
         for (int var2 = 0; var2 < this.nCol; var2++) {
            this.values[var1][var2] = 0.0;
         }
      }

      int var3;
      if (this.nRow < this.nCol) {
         var3 = this.nRow;
      } else {
         var3 = this.nCol;
      }

      for (int var4 = 0; var4 < var3; var4++) {
         this.values[var4][var4] = 1.0;
      }
   }

   public void sub(GMatrix var1, GMatrix var2) {
      if (var2.nRow != var1.nRow) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix10"));
      } else if (var2.nCol != var1.nCol) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix11"));
      } else if (this.nRow == var1.nRow && this.nCol == var1.nCol) {
         for (int var3 = 0; var3 < this.nRow; var3++) {
            for (int var4 = 0; var4 < this.nCol; var4++) {
               this.values[var3][var4] = var1.values[var3][var4] - var2.values[var3][var4];
            }
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix12"));
      }
   }

   public void invertGeneral(GMatrix var1) {
      int var2 = var1.nRow * var1.nCol;
      double[] var3 = new double[var2];
      double[] var4 = new double[var2];
      int[] var5 = new int[var1.nRow];
      int[] var6 = new int[1];
      if (var1.nRow != var1.nCol) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix22"));
      } else {
         for (int var7 = 0; var7 < this.nRow; var7++) {
            for (int var8 = 0; var8 < this.nCol; var8++) {
               var3[var7 * this.nCol + var8] = var1.values[var7][var8];
            }
         }

         if (!luDecomposition(var1.nRow, var3, var5, var6)) {
            throw new SingularMatrixException(VecMathI18N.getString("GMatrix21"));
         } else {
            for (int var9 = 0; var9 < var2; var9++) {
               var4[var9] = 0.0;
            }

            for (int var10 = 0; var10 < this.nCol; var10++) {
               var4[var10 + var10 * this.nCol] = 1.0;
            }

            luBacksubstitution(var1.nRow, var3, var5, var4);

            for (int var11 = 0; var11 < this.nRow; var11++) {
               for (int var12 = 0; var12 < this.nCol; var12++) {
                  this.values[var11][var12] = var4[var11 * this.nCol + var12];
               }
            }
         }
      }
   }

   public void getRow(int var1, GVector var2) {
      if (var2.getSize() < this.nCol) {
         var2.setSize(this.nCol);
      }

      for (int var3 = 0; var3 < this.nCol; var3++) {
         var2.values[var3] = this.values[var1][var3];
      }
   }

   public double getElement(int var1, int var2) {
      return this.values[var1][var2];
   }

   public void set(Matrix3d var1) {
      if (this.nRow < 3 || this.nCol < 3) {
         this.values = new double[3][3];
         this.nRow = 3;
         this.nCol = 3;
      }

      this.values[0][0] = var1.m00;
      this.values[0][1] = var1.m01;
      this.values[0][2] = var1.m02;
      this.values[1][0] = var1.m10;
      this.values[1][1] = var1.m11;
      this.values[1][2] = var1.m12;
      this.values[2][0] = var1.m20;
      this.values[2][1] = var1.m21;
      this.values[2][2] = var1.m22;

      for (int var2 = 3; var2 < this.nRow; var2++) {
         for (int var3 = 3; var3 < this.nCol; var3++) {
            this.values[var2][var3] = 0.0;
         }
      }
   }

   public void setElement(int var1, int var2, double var3) {
      this.values[var1][var2] = var3;
   }

   public void transpose(GMatrix var1) {
      if (this.nRow == var1.nCol && this.nCol == var1.nRow) {
         if (var1 != this) {
            for (int var2 = 0; var2 < this.nRow; var2++) {
               for (int var3 = 0; var3 < this.nCol; var3++) {
                  this.values[var2][var3] = var1.values[var3][var2];
               }
            }
         } else {
            this.transpose();
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix17"));
      }
   }

   public void negate() {
      for (int var1 = 0; var1 < this.nRow; var1++) {
         for (int var2 = 0; var2 < this.nCol; var2++) {
            this.values[var1][var2] = -this.values[var1][var2];
         }
      }
   }

   public void getColumn(int var1, double[] var2) {
      for (int var3 = 0; var3 < this.nRow; var3++) {
         var2[var3] = this.values[var3][var1];
      }
   }

   public static double max(double var0, double var2) {
      return var0 > var2 ? var0 : var2;
   }

   public void mul(GVector var1, GVector var2) {
      if (this.nRow < var1.getSize()) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix2"));
      } else if (this.nCol < var2.getSize()) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix3"));
      } else {
         for (int var3 = 0; var3 < var1.getSize(); var3++) {
            for (int var4 = 0; var4 < var2.getSize(); var4++) {
               this.values[var3][var4] = var1.values[var3] * var2.values[var4];
            }
         }
      }
   }

   public int getNumCol() {
      return this.nCol;
   }

   public void getRow(int var1, double[] var2) {
      for (int var3 = 0; var3 < this.nCol; var3++) {
         var2[var3] = this.values[var1][var3];
      }
   }

   public void mulTransposeBoth(GMatrix var1, GMatrix var2) {
      if (var1.nRow == var2.nCol && this.nRow == var1.nCol && this.nCol == var2.nRow) {
         if (var1 != this && var2 != this) {
            for (int var7 = 0; var7 < this.nRow; var7++) {
               for (int var8 = 0; var8 < this.nCol; var8++) {
                  this.values[var7][var8] = 0.0;

                  for (int var9 = 0; var9 < var1.nRow; var9++) {
                     this.values[var7][var8] = this.values[var7][var8] + var1.values[var9][var7] * var2.values[var8][var9];
                  }
               }
            }
         } else {
            double[][] var6 = new double[this.nRow][this.nCol];

            for (int var3 = 0; var3 < this.nRow; var3++) {
               for (int var4 = 0; var4 < this.nCol; var4++) {
                  var6[var3][var4] = 0.0;

                  for (int var5 = 0; var5 < var1.nRow; var5++) {
                     var6[var3][var4] = var6[var3][var4] + var1.values[var5][var3] * var2.values[var4][var5];
                  }
               }
            }

            this.values = var6;
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix14"));
      }
   }

   public static boolean luDecomposition(int var0, double[] var1, int[] var2, int[] var3) {
      double[] var4 = new double[var0];
      int var7 = 0;
      int var8 = 0;
      var3[0] = 1;
      int var5 = var0;

      while (var5-- != 0) {
         double var10 = 0.0;
         int var6 = var0;

         while (var6-- != 0) {
            double var12 = var1[var7++];
            var12 = Math.abs(var12);
            if (var12 > var10) {
               var10 = var12;
            }
         }

         if (var10 == 0.0) {
            return false;
         }

         var4[var8++] = 1.0 / var10;
      }

      byte var9 = 0;

      for (int var24 = 0; var24 < var0; var24++) {
         for (int var21 = 0; var21 < var24; var21++) {
            int var16 = var9 + var0 * var21 + var24;
            double var19 = var1[var16];
            int var15 = var21;
            int var17 = var9 + var0 * var21;

            for (int var18 = var9 + var24; var15-- != 0; var18 += var0) {
               var19 -= var1[var17] * var1[var18];
               var17++;
            }

            var1[var16] = var19;
         }

         double var25 = 0.0;
         int var14 = -1;

         for (int var22 = var24; var22 < var0; var22++) {
            int var32 = var9 + var0 * var22 + var24;
            double var38 = var1[var32];
            int var30 = var24;
            int var34 = var9 + var0 * var22;

            for (int var36 = var9 + var24; var30-- != 0; var36 += var0) {
               var38 -= var1[var34] * var1[var36];
               var34++;
            }

            var1[var32] = var38;
            double var27;
            if ((var27 = var4[var22] * Math.abs(var38)) >= var25) {
               var25 = var27;
               var14 = var22;
            }
         }

         if (var14 < 0) {
            throw new RuntimeException(VecMathI18N.getString("GMatrix24"));
         }

         if (var24 != var14) {
            int var31 = var0;
            int var35 = var9 + var0 * var14;
            int var37 = var9 + var0 * var24;

            while (var31-- != 0) {
               double var28 = var1[var35];
               var1[var35++] = var1[var37];
               var1[var37++] = var28;
            }

            var4[var14] = var4[var24];
            var3[0] = -var3[0];
         }

         var2[var24] = var14;
         if (var1[var9 + var0 * var24 + var24] == 0.0) {
            return false;
         }

         if (var24 != var0 - 1) {
            double var29 = 1.0 / var1[var9 + var0 * var24 + var24];
            int var33 = var9 + var0 * (var24 + 1) + var24;

            for (int var23 = var0 - 1 - var24; var23-- != 0; var33 += var0) {
               var1[var33] *= var29;
            }
         }
      }

      return true;
   }

   public GMatrix(int var1, int var2) {
      this.values = new double[var1][var2];
      this.nRow = var1;
      this.nCol = var2;

      for (int var3 = 0; var3 < var1; var3++) {
         for (int var4 = 0; var4 < var2; var4++) {
            this.values[var3][var4] = 0.0;
         }
      }

      int var5;
      if (var1 < var2) {
         var5 = var1;
      } else {
         var5 = var2;
      }

      for (int var6 = 0; var6 < var5; var6++) {
         this.values[var6][var6] = 1.0;
      }
   }

   public void invert() {
      this.invertGeneral(this);
   }

   public static void print_m(GMatrix var0, GMatrix var1, GMatrix var2) {
      GMatrix var3 = new GMatrix(var0.nCol, var0.nRow);
      var3.mul(var1, var3);
      var3.mul(var3, var2);
      System.out.println("\n m = \n" + toString(var3));
   }

   public boolean epsilonEquals(GMatrix var1, float var2) {
      return this.epsilonEquals(var1, (double)var2);
   }

   public void getColumn(int var1, GVector var2) {
      if (var2.getSize() < this.nRow) {
         var2.setSize(this.nRow);
      }

      for (int var3 = 0; var3 < this.nRow; var3++) {
         var2.values[var3] = this.values[var3][var1];
      }
   }

   public void set(Matrix4f var1) {
      if (this.nRow < 4 || this.nCol < 4) {
         this.values = new double[4][4];
         this.nRow = 4;
         this.nCol = 4;
      }

      this.values[0][0] = var1.m00;
      this.values[0][1] = var1.m01;
      this.values[0][2] = var1.m02;
      this.values[0][3] = var1.m03;
      this.values[1][0] = var1.m10;
      this.values[1][1] = var1.m11;
      this.values[1][2] = var1.m12;
      this.values[1][3] = var1.m13;
      this.values[2][0] = var1.m20;
      this.values[2][1] = var1.m21;
      this.values[2][2] = var1.m22;
      this.values[2][3] = var1.m23;
      this.values[3][0] = var1.m30;
      this.values[3][1] = var1.m31;
      this.values[3][2] = var1.m32;
      this.values[3][3] = var1.m33;

      for (int var2 = 4; var2 < this.nRow; var2++) {
         for (int var3 = 4; var3 < this.nCol; var3++) {
            this.values[var2][var3] = 0.0;
         }
      }
   }

   public static void checkMatrix(GMatrix var0) {
      for (int var1 = 0; var1 < var0.nRow; var1++) {
         for (int var2 = 0; var2 < var0.nCol; var2++) {
            if (Math.abs(var0.values[var1][var2]) < 1.0E-10) {
               System.out.print(" 0.0     ");
            } else {
               System.out.print(" " + var0.values[var1][var2]);
            }
         }

         System.out.print("\n");
      }
   }

   public void get(Matrix4d var1) {
      if (this.nRow >= 4 && this.nCol >= 4) {
         var1.m00 = this.values[0][0];
         var1.m01 = this.values[0][1];
         var1.m02 = this.values[0][2];
         var1.m03 = this.values[0][3];
         var1.m10 = this.values[1][0];
         var1.m11 = this.values[1][1];
         var1.m12 = this.values[1][2];
         var1.m13 = this.values[1][3];
         var1.m20 = this.values[2][0];
         var1.m21 = this.values[2][1];
         var1.m22 = this.values[2][2];
         var1.m23 = this.values[2][3];
         var1.m30 = this.values[3][0];
         var1.m31 = this.values[3][1];
         var1.m32 = this.values[3][2];
         var1.m33 = this.values[3][3];
      } else {
         var1.setZero();
         if (this.nCol > 0) {
            if (this.nRow > 0) {
               var1.m00 = this.values[0][0];
               if (this.nRow > 1) {
                  var1.m10 = this.values[1][0];
                  if (this.nRow > 2) {
                     var1.m20 = this.values[2][0];
                     if (this.nRow > 3) {
                        var1.m30 = this.values[3][0];
                     }
                  }
               }
            }

            if (this.nCol > 1) {
               if (this.nRow > 0) {
                  var1.m01 = this.values[0][1];
                  if (this.nRow > 1) {
                     var1.m11 = this.values[1][1];
                     if (this.nRow > 2) {
                        var1.m21 = this.values[2][1];
                        if (this.nRow > 3) {
                           var1.m31 = this.values[3][1];
                        }
                     }
                  }
               }

               if (this.nCol > 2) {
                  if (this.nRow > 0) {
                     var1.m02 = this.values[0][2];
                     if (this.nRow > 1) {
                        var1.m12 = this.values[1][2];
                        if (this.nRow > 2) {
                           var1.m22 = this.values[2][2];
                           if (this.nRow > 3) {
                              var1.m32 = this.values[3][2];
                           }
                        }
                     }
                  }

                  if (this.nCol > 3 && this.nRow > 0) {
                     var1.m03 = this.values[0][3];
                     if (this.nRow > 1) {
                        var1.m13 = this.values[1][3];
                        if (this.nRow > 2) {
                           var1.m23 = this.values[2][3];
                           if (this.nRow > 3) {
                              var1.m33 = this.values[3][3];
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void identityMinus() {
      for (int var1 = 0; var1 < this.nRow; var1++) {
         for (int var2 = 0; var2 < this.nCol; var2++) {
            this.values[var1][var2] = -this.values[var1][var2];
         }
      }

      int var3;
      if (this.nRow < this.nCol) {
         var3 = this.nRow;
      } else {
         var3 = this.nCol;
      }

      for (int var4 = 0; var4 < var3; var4++) {
         this.values[var4][var4]++;
      }
   }

   public static void chase_across(double[] var0, double[] var1, int var2, GMatrix var3) {
      double[] var10 = new double[1];
      double[] var11 = new double[1];
      GMatrix var13 = new GMatrix(var3.nRow, var3.nCol);
      GMatrix var14 = new GMatrix(var3.nRow, var3.nCol);
      double var6 = var1[var2];
      double var4 = var0[var2 + 1];

      int var12;
      for (var12 = var2; var12 < var3.nCol - 2; var12++) {
         double var8 = compute_rot(var4, var6, var11, var10);
         var6 = -var1[var12 + 1] * var11[0];
         var4 = var0[var12 + 2];
         var0[var12 + 1] = var8;
         var1[var12 + 1] = var1[var12 + 1] * var10[0];
         update_u_split(var2, var12 + 1, var3, var10, var11, var13, var14);
      }

      var0[var12 + 1] = compute_rot(var4, var6, var11, var10);
      update_u_split(var2, var12 + 1, var3, var10, var11, var13, var14);
   }

   public static void luBacksubstitution(int var0, double[] var1, int[] var2, double[] var3) {
      byte var9 = 0;

      for (int var8 = 0; var8 < var0; var8++) {
         int var10 = var8;
         int var5 = -1;

         for (int var4 = 0; var4 < var0; var4++) {
            int var6 = var2[var9 + var4];
            double var15 = var3[var10 + var0 * var6];
            var3[var10 + var0 * var6] = var3[var10 + var0 * var4];
            if (var5 >= 0) {
               int var11 = var4 * var0;

               for (int var7 = var5; var7 <= var4 - 1; var7++) {
                  var15 -= var1[var11 + var7] * var3[var10 + var0 * var7];
               }
            } else if (var15 != 0.0) {
               var5 = var4;
            }

            var3[var10 + var0 * var4] = var15;
         }

         for (int var17 = 0; var17 < var0; var17++) {
            int var12 = var0 - 1 - var17;
            int var19 = var0 * var12;
            double var13 = 0.0;

            for (int var18 = 1; var18 <= var17; var18++) {
               var13 += var1[var19 + var0 - var18] * var3[var10 + var0 * (var0 - var18)];
            }

            var3[var10 + var0 * var12] = (var3[var10 + var0 * var12] - var13) / var1[var19 + var12];
         }
      }
   }

   public void set(Matrix3f var1) {
      if (this.nCol < 3 || this.nRow < 3) {
         this.nCol = 3;
         this.nRow = 3;
         this.values = new double[this.nRow][this.nCol];
      }

      this.values[0][0] = var1.m00;
      this.values[0][1] = var1.m01;
      this.values[0][2] = var1.m02;
      this.values[1][0] = var1.m10;
      this.values[1][1] = var1.m11;
      this.values[1][2] = var1.m12;
      this.values[2][0] = var1.m20;
      this.values[2][1] = var1.m21;
      this.values[2][2] = var1.m22;

      for (int var2 = 3; var2 < this.nRow; var2++) {
         for (int var3 = 3; var3 < this.nCol; var3++) {
            this.values[var2][var3] = 0.0;
         }
      }
   }

   public static void print_se(double[] var0, double[] var1) {
      System.out.println("\ns =" + var0[0] + " " + var0[1] + " " + var0[2]);
      System.out.println("e =" + var1[0] + " " + var1[1]);
   }

   public static String toString(GMatrix var0) {
      StringBuffer var1 = new StringBuffer(var0.nRow * var0.nCol * 8);

      for (int var2 = 0; var2 < var0.nRow; var2++) {
         for (int var3 = 0; var3 < var0.nCol; var3++) {
            if (Math.abs(var0.values[var2][var3]) < 1.0E-9) {
               var1.append("0.0000 ");
            } else {
               var1.append(var0.values[var2][var3]).append(" ");
            }
         }

         var1.append("\n");
      }

      return var1.toString();
   }

   public static void update_u_split(int var0, int var1, GMatrix var2, double[] var3, double[] var4, GMatrix var5, GMatrix var6) {
      for (int var7 = 0; var7 < var2.nCol; var7++) {
         double var8 = var2.values[var0][var7];
         var2.values[var0][var7] = var3[0] * var8 - var4[0] * var2.values[var1][var7];
         var2.values[var1][var7] = var4[0] * var8 + var3[0] * var2.values[var1][var7];
      }

      System.out.println("\nm=");
      checkMatrix(var6);
      System.out.println("\nu=");
      checkMatrix(var5);
      var6.mul(var5, var6);
      System.out.println("\nt*m=");
      checkMatrix(var6);
   }

   public static void update_v(int var0, GMatrix var1, double[] var2, double[] var3) {
      for (int var4 = 0; var4 < var1.nRow; var4++) {
         double var5 = var1.values[var4][var0];
         var1.values[var4][var0] = var2[0] * var5 + var3[0] * var1.values[var4][var0 + 1];
         var1.values[var4][var0 + 1] = -var3[0] * var5 + var2[0] * var1.values[var4][var0 + 1];
      }
   }

   public void setRow(int var1, GVector var2) {
      for (int var3 = 0; var3 < this.nCol; var3++) {
         this.values[var1][var3] = var2.values[var3];
      }
   }

   public void set(Matrix4d var1) {
      if (this.nRow < 4 || this.nCol < 4) {
         this.values = new double[4][4];
         this.nRow = 4;
         this.nCol = 4;
      }

      this.values[0][0] = var1.m00;
      this.values[0][1] = var1.m01;
      this.values[0][2] = var1.m02;
      this.values[0][3] = var1.m03;
      this.values[1][0] = var1.m10;
      this.values[1][1] = var1.m11;
      this.values[1][2] = var1.m12;
      this.values[1][3] = var1.m13;
      this.values[2][0] = var1.m20;
      this.values[2][1] = var1.m21;
      this.values[2][2] = var1.m22;
      this.values[2][3] = var1.m23;
      this.values[3][0] = var1.m30;
      this.values[3][1] = var1.m31;
      this.values[3][2] = var1.m32;
      this.values[3][3] = var1.m33;

      for (int var2 = 4; var2 < this.nRow; var2++) {
         for (int var3 = 4; var3 < this.nCol; var3++) {
            this.values[var2][var3] = 0.0;
         }
      }
   }

   public void mulTransposeLeft(GMatrix var1, GMatrix var2) {
      if (var1.nRow == var2.nRow && this.nCol == var2.nCol && this.nRow == var1.nCol) {
         if (var1 != this && var2 != this) {
            for (int var7 = 0; var7 < this.nRow; var7++) {
               for (int var8 = 0; var8 < this.nCol; var8++) {
                  this.values[var7][var8] = 0.0;

                  for (int var9 = 0; var9 < var1.nRow; var9++) {
                     this.values[var7][var8] = this.values[var7][var8] + var1.values[var9][var7] * var2.values[var9][var8];
                  }
               }
            }
         } else {
            double[][] var6 = new double[this.nRow][this.nCol];

            for (int var3 = 0; var3 < this.nRow; var3++) {
               for (int var4 = 0; var4 < this.nCol; var4++) {
                  var6[var3][var4] = 0.0;

                  for (int var5 = 0; var5 < var1.nRow; var5++) {
                     var6[var3][var4] = var6[var3][var4] + var1.values[var5][var3] * var2.values[var5][var4];
                  }
               }
            }

            this.values = var6;
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix16"));
      }
   }

   public void setColumn(int var1, double[] var2) {
      for (int var3 = 0; var3 < this.nRow; var3++) {
         this.values[var3][var1] = var2[var3];
      }
   }

   public void transpose() {
      if (this.nRow != this.nCol) {
         int var1 = this.nRow;
         this.nRow = this.nCol;
         this.nCol = var1;
         double[][] var3 = new double[this.nRow][this.nCol];

         for (int var5 = 0; var5 < this.nRow; var5++) {
            for (int var2 = 0; var2 < this.nCol; var2++) {
               var3[var5][var2] = this.values[var2][var5];
            }
         }

         this.values = var3;
      } else {
         for (int var6 = 0; var6 < this.nRow; var6++) {
            for (int var7 = 0; var7 < var6; var7++) {
               double var8 = this.values[var6][var7];
               this.values[var6][var7] = this.values[var7][var6];
               this.values[var7][var6] = var8;
            }
         }
      }
   }

   public void add(GMatrix var1, GMatrix var2) {
      if (var2.nRow != var1.nRow) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix6"));
      } else if (var2.nCol != var1.nCol) {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix7"));
      } else if (this.nCol == var1.nCol && this.nRow == var1.nRow) {
         for (int var3 = 0; var3 < this.nRow; var3++) {
            for (int var4 = 0; var4 < this.nCol; var4++) {
               this.values[var3][var4] = var1.values[var3][var4] + var2.values[var3][var4];
            }
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.getString("GMatrix8"));
      }
   }

   public void get(Matrix3d var1) {
      if (this.nRow >= 3 && this.nCol >= 3) {
         var1.m00 = this.values[0][0];
         var1.m01 = this.values[0][1];
         var1.m02 = this.values[0][2];
         var1.m10 = this.values[1][0];
         var1.m11 = this.values[1][1];
         var1.m12 = this.values[1][2];
         var1.m20 = this.values[2][0];
         var1.m21 = this.values[2][1];
         var1.m22 = this.values[2][2];
      } else {
         var1.setZero();
         if (this.nCol > 0) {
            if (this.nRow > 0) {
               var1.m00 = this.values[0][0];
               if (this.nRow > 1) {
                  var1.m10 = this.values[1][0];
                  if (this.nRow > 2) {
                     var1.m20 = this.values[2][0];
                  }
               }
            }

            if (this.nCol > 1) {
               if (this.nRow > 0) {
                  var1.m01 = this.values[0][1];
                  if (this.nRow > 1) {
                     var1.m11 = this.values[1][1];
                     if (this.nRow > 2) {
                        var1.m21 = this.values[2][1];
                     }
                  }
               }

               if (this.nCol > 2 && this.nRow > 0) {
                  var1.m02 = this.values[0][2];
                  if (this.nRow > 1) {
                     var1.m12 = this.values[1][2];
                     if (this.nRow > 2) {
                        var1.m22 = this.values[2][2];
                     }
                  }
               }
            }
         }
      }
   }

   public static void compute_qr(int var0, int var1, double[] var2, double[] var3, GMatrix var4, GMatrix var5) {
      double[] var24 = new double[1];
      double[] var25 = new double[1];
      double[] var26 = new double[1];
      double[] var27 = new double[1];
      new GMatrix(var4.nCol, var5.nRow);
      byte var29 = 2;
      double var30 = 4.89E-15;
      double var32 = 1.0;
      double var34 = -1.0;
      boolean var11 = false;
      double var20 = 0.0;
      double var22 = 0.0;

      for (int var8 = 0; var8 < 2 && !var11; var8++) {
         int var6;
         for (var6 = var0; var6 <= var1; var6++) {
            if (var6 == var0) {
               int var10;
               if (var3.length == var2.length) {
                  var10 = var1;
               } else {
                  var10 = var1 + 1;
               }

               double var12 = compute_shift(var2[var10 - 1], var3[var1], var2[var10]);
               var20 = (Math.abs(var2[var6]) - var12) * (d_sign(var32, var2[var6]) + var12 / var2[var6]);
               var22 = var3[var6];
            }

            double var14 = compute_rot(var20, var22, var27, var25);
            if (var6 != var0) {
               var3[var6 - 1] = var14;
            }

            var20 = var25[0] * var2[var6] + var27[0] * var3[var6];
            var3[var6] = var25[0] * var3[var6] - var27[0] * var2[var6];
            var22 = var27[0] * var2[var6 + 1];
            var2[var6 + 1] = var25[0] * var2[var6 + 1];
            update_v(var6, var5, var25, var27);
            var14 = compute_rot(var20, var22, var26, var24);
            var2[var6] = var14;
            var20 = var24[0] * var3[var6] + var26[0] * var2[var6 + 1];
            var2[var6 + 1] = var24[0] * var2[var6 + 1] - var26[0] * var3[var6];
            if (var6 < var1) {
               var22 = var26[0] * var3[var6 + 1];
               var3[var6 + 1] = var24[0] * var3[var6 + 1];
            }

            update_u(var6, var4, var24, var26);
         }

         if (var2.length == var3.length) {
            double var37 = compute_rot(var20, var22, var27, var25);
            var20 = var25[0] * var2[var6] + var27[0] * var3[var6];
            var3[var6] = var25[0] * var3[var6] - var27[0] * var2[var6];
            var2[var6 + 1] = var25[0] * var2[var6 + 1];
            update_v(var6, var5, var25, var27);
         }

         while (var1 - var0 > 1 && Math.abs(var3[var1]) < 4.89E-15) {
            var1--;
         }

         for (int var9 = var1 - 2; var9 > var0; var9--) {
            if (Math.abs(var3[var9]) < 4.89E-15) {
               compute_qr(var9 + 1, var1, var2, var3, var4, var5);
               var1 = var9 - 1;

               while (var1 - var0 > 1 && Math.abs(var3[var1]) < 4.89E-15) {
                  var1--;
               }
            }
         }

         if (var1 - var0 <= 1 && Math.abs(var3[var0 + 1]) < 4.89E-15) {
            var11 = true;
         }
      }

      if (Math.abs(var3[1]) < 4.89E-15) {
         compute_2X2(var2[var0], var3[var0], var2[var0 + 1], var2, var26, var24, var27, var25, 0);
         var3[var0] = 0.0;
         var3[var0 + 1] = 0.0;
      }

      update_u(var0, var4, var24, var26);
      update_v(var0, var5, var25, var27);
   }

   @Override
   public String toString() {
      StringBuffer var1 = new StringBuffer(this.nRow * this.nCol * 8);

      for (int var2 = 0; var2 < this.nRow; var2++) {
         for (int var3 = 0; var3 < this.nCol; var3++) {
            var1.append(this.values[var2][var3]).append(" ");
         }

         var1.append("\n");
      }

      return var1.toString();
   }

   public static void chase_up(double[] var0, double[] var1, int var2, GMatrix var3) {
      double[] var10 = new double[1];
      double[] var11 = new double[1];
      GMatrix var13 = new GMatrix(var3.nRow, var3.nCol);
      GMatrix var14 = new GMatrix(var3.nRow, var3.nCol);
      double var4 = var1[var2];
      double var6 = var0[var2];

      int var12;
      for (var12 = var2; var12 > 0; var12--) {
         double var8 = compute_rot(var4, var6, var11, var10);
         var4 = -var1[var12 - 1] * var11[0];
         var6 = var0[var12 - 1];
         var0[var12] = var8;
         var1[var12 - 1] = var1[var12 - 1] * var10[0];
         update_v_split(var12, var2 + 1, var3, var10, var11, var13, var14);
      }

      var0[var12 + 1] = compute_rot(var4, var6, var11, var10);
      update_v_split(var12, var2 + 1, var3, var10, var11, var13, var14);
   }

   public void setColumn(int var1, GVector var2) {
      for (int var3 = 0; var3 < this.nRow; var3++) {
         this.values[var3][var1] = var2.values[var3];
      }
   }

   public static double min(double var0, double var2) {
      return var0 < var2 ? var0 : var2;
   }

   public GMatrix(GMatrix var1) {
      this.nRow = var1.nRow;
      this.nCol = var1.nCol;
      this.values = new double[this.nRow][this.nCol];

      for (int var2 = 0; var2 < this.nRow; var2++) {
         for (int var3 = 0; var3 < this.nCol; var3++) {
            this.values[var2][var3] = var1.values[var2][var3];
         }
      }
   }

   public double trace() {
      int var2;
      if (this.nRow < this.nCol) {
         var2 = this.nRow;
      } else {
         var2 = this.nCol;
      }

      double var3 = 0.0;

      for (int var1 = 0; var1 < var2; var1++) {
         var3 += this.values[var1][var1];
      }

      return var3;
   }

   public void copySubMatrix(int var1, int var2, int var3, int var4, int var5, int var6, GMatrix var7) {
      if (this != var7) {
         for (int var8 = 0; var8 < var3; var8++) {
            for (int var9 = 0; var9 < var4; var9++) {
               var7.values[var5 + var8][var6 + var9] = this.values[var1 + var8][var2 + var9];
            }
         }
      } else {
         double[][] var10 = new double[var3][var4];

         for (int var11 = 0; var11 < var3; var11++) {
            for (int var13 = 0; var13 < var4; var13++) {
               var10[var11][var13] = this.values[var1 + var11][var2 + var13];
            }
         }

         for (int var12 = 0; var12 < var3; var12++) {
            for (int var14 = 0; var14 < var4; var14++) {
               var7.values[var5 + var12][var6 + var14] = var10[var12][var14];
            }
         }
      }
   }

   public boolean equals(GMatrix var1) {
      try {
         if (this.nRow == var1.nRow && this.nCol == var1.nCol) {
            for (int var2 = 0; var2 < this.nRow; var2++) {
               for (int var3 = 0; var3 < this.nCol; var3++) {
                  if (this.values[var2][var3] != var1.values[var2][var3]) {
                     return false;
                  }
               }
            }

            return true;
         } else {
            return false;
         }
      } catch (NullPointerException var4) {
         return false;
      }
   }

   public static double d_sign(double var0, double var2) {
      double var4 = var0 >= 0.0 ? var0 : -var0;
      return var2 >= 0.0 ? var4 : -var4;
   }

   public void get(Matrix3f var1) {
      if (this.nRow >= 3 && this.nCol >= 3) {
         var1.m00 = (float)this.values[0][0];
         var1.m01 = (float)this.values[0][1];
         var1.m02 = (float)this.values[0][2];
         var1.m10 = (float)this.values[1][0];
         var1.m11 = (float)this.values[1][1];
         var1.m12 = (float)this.values[1][2];
         var1.m20 = (float)this.values[2][0];
         var1.m21 = (float)this.values[2][1];
         var1.m22 = (float)this.values[2][2];
      } else {
         var1.setZero();
         if (this.nCol > 0) {
            if (this.nRow > 0) {
               var1.m00 = (float)this.values[0][0];
               if (this.nRow > 1) {
                  var1.m10 = (float)this.values[1][0];
                  if (this.nRow > 2) {
                     var1.m20 = (float)this.values[2][0];
                  }
               }
            }

            if (this.nCol > 1) {
               if (this.nRow > 0) {
                  var1.m01 = (float)this.values[0][1];
                  if (this.nRow > 1) {
                     var1.m11 = (float)this.values[1][1];
                     if (this.nRow > 2) {
                        var1.m21 = (float)this.values[2][1];
                     }
                  }
               }

               if (this.nCol > 2 && this.nRow > 0) {
                  var1.m02 = (float)this.values[0][2];
                  if (this.nRow > 1) {
                     var1.m12 = (float)this.values[1][2];
                     if (this.nRow > 2) {
                        var1.m22 = (float)this.values[2][2];
                     }
                  }
               }
            }
         }
      }
   }

   public void setZero() {
      for (int var1 = 0; var1 < this.nRow; var1++) {
         for (int var2 = 0; var2 < this.nCol; var2++) {
            this.values[var1][var2] = 0.0;
         }
      }
   }

   public static void update_u(int var0, GMatrix var1, double[] var2, double[] var3) {
      for (int var4 = 0; var4 < var1.nCol; var4++) {
         double var5 = var1.values[var0][var4];
         var1.values[var0][var4] = var2[0] * var5 + var3[0] * var1.values[var0 + 1][var4];
         var1.values[var0 + 1][var4] = -var3[0] * var5 + var2[0] * var1.values[var0 + 1][var4];
      }
   }
}
