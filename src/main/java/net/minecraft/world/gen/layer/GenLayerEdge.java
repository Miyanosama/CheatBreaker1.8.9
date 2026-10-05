package net.minecraft.world.gen.layer;

public class GenLayerEdge extends GenLayer {
   public GenLayerEdge.Mode field_151627_c;

   public GenLayerEdge(long var1, GenLayer var3, GenLayerEdge.Mode var4) {
      super(var1);
      this.a = var3;
      this.field_151627_c = var4;
   }

   @Override
   public int[] getInts(int var1, int var2, int var3, int var4) {
      switch (this.field_151627_c) {
         case COOL_WARM:
         default:
            return this.getIntsCoolWarm(var1, var2, var3, var4);
         case HEAT_ICE:
            return this.getIntsHeatIce(var1, var2, var3, var4);
         case SPECIAL:
            return this.getIntsSpecial(var1, var2, var3, var4);
      }
   }

   public int[] getIntsHeatIce(int var1, int var2, int var3, int var4) {
      int var5 = var1 - 1;
      int var6 = var2 - 1;
      int var7 = 1 + var3 + 1;
      int var8 = 1 + var4 + 1;
      int[] var9 = this.a.getInts(var5, var6, var7, var8);
      int[] var10 = IntCache.getIntCache(var3 * var4);

      for (int var11 = 0; var11 < var4; var11++) {
         for (int var12 = 0; var12 < var3; var12++) {
            int var13 = var9[var12 + 1 + (var11 + 1) * var7];
            if (var13 == 4) {
               int var14 = var9[var12 + 1 + (var11 + 1 - 1) * var7];
               int var15 = var9[var12 + 1 + 1 + (var11 + 1) * var7];
               int var16 = var9[var12 + 1 - 1 + (var11 + 1) * var7];
               int var17 = var9[var12 + 1 + (var11 + 1 + 1) * var7];
               boolean var18 = var14 == 2 || var15 == 2 || var16 == 2 || var17 == 2;
               boolean var19 = var14 == 1 || var15 == 1 || var16 == 1 || var17 == 1;
               if (var19 || var18) {
                  var13 = 3;
               }
            }

            var10[var12 + var11 * var3] = var13;
         }
      }

      return var10;
   }

   public int[] getIntsSpecial(int var1, int var2, int var3, int var4) {
      int[] var5 = this.a.getInts(var1, var2, var3, var4);
      int[] var6 = IntCache.getIntCache(var3 * var4);

      for (int var7 = 0; var7 < var4; var7++) {
         for (int var8 = 0; var8 < var3; var8++) {
            this.a(var8 + var1, var7 + var2);
            int var9 = var5[var8 + var7 * var3];
            if (var9 != 0 && this.a(13) == 0) {
               var9 |= 1 + this.a(15) << 8 & 3840;
            }

            var6[var8 + var7 * var3] = var9;
         }
      }

      return var6;
   }

   public int[] getIntsCoolWarm(int var1, int var2, int var3, int var4) {
      int var5 = var1 - 1;
      int var6 = var2 - 1;
      int var7 = 1 + var3 + 1;
      int var8 = 1 + var4 + 1;
      int[] var9 = this.a.getInts(var5, var6, var7, var8);
      int[] var10 = IntCache.getIntCache(var3 * var4);

      for (int var11 = 0; var11 < var4; var11++) {
         for (int var12 = 0; var12 < var3; var12++) {
            this.a(var12 + var1, var11 + var2);
            int var13 = var9[var12 + 1 + (var11 + 1) * var7];
            if (var13 == 1) {
               int var14 = var9[var12 + 1 + (var11 + 1 - 1) * var7];
               int var15 = var9[var12 + 1 + 1 + (var11 + 1) * var7];
               int var16 = var9[var12 + 1 - 1 + (var11 + 1) * var7];
               int var17 = var9[var12 + 1 + (var11 + 1 + 1) * var7];
               boolean var18 = var14 == 3 || var15 == 3 || var16 == 3 || var17 == 3;
               boolean var19 = var14 == 4 || var15 == 4 || var16 == 4 || var17 == 4;
               if (var18 || var19) {
                  var13 = 2;
               }
            }

            var10[var12 + var11 * var3] = var13;
         }
      }

      return var10;
   }

   public static enum Mode {
      COOL_WARM,
      HEAT_ICE,
      SPECIAL;
      // $VF: synthetic field
      public static GenLayerEdge.Mode[] $VALUES = new GenLayerEdge.Mode[]{COOL_WARM, GenLayerEdge.Mode.HEAT_ICE, GenLayerEdge.Mode.SPECIAL};
   }
}
