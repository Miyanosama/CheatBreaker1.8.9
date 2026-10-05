package net.minecraft.world;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockLilyPad;
import recovered.unidentified.UnidentifiedClass3177;

public class ColorizerGrass {
   public static int[] grassBuffer = new int[65536];
   public BlockLilyPad field_0003;
   public UnidentifiedClass3177 field_0000;
   public BlockFurnace field_0002;

   public static int getGrassColor(double var0, double var2) {
      var2 *= var0;
      int var4 = (int)((1.0 - var0) * 255.0);
      int var5 = (int)((1.0 - var2) * 255.0);
      int var6 = var5 << 8 | var4;
      return var6 > grassBuffer.length ? -65281 : grassBuffer[var6];
   }

   public static void setGrassBiomeColorizer(int[] var0) {
      grassBuffer = var0;
   }
}
