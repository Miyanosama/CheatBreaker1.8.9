package net.minecraft.client.renderer.block.model;

import net.minecraft.util.EnumFacing;

public class BlockPartFace {
   public BlockFaceUV blockFaceUV;
   public EnumFacing cullFace;
   public static EnumFacing FACING_DEFAULT = null;
   public int tintIndex;
   public String texture;

   public BlockPartFace(EnumFacing var1, int var2, String var3, BlockFaceUV var4) {
      this.cullFace = var1;
      this.tintIndex = var2;
      this.texture = var3;
      this.blockFaceUV = var4;
   }
}
