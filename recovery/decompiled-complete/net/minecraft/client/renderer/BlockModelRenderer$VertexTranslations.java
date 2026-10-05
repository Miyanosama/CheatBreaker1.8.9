package net.minecraft.client.renderer;

import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.spdy.SpdyHeaderBlockRawEncoder;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.util.EnumFacing;
import recovered.unidentified.UnidentifiedClass3697;

public enum BlockModelRenderer$VertexTranslations {
   WEST(3, 0, 1, 2),
   DOWN(0, 1, 2, 3),
   EAST(1, 2, 3, 0),
   NORTH(3, 0, 1, 2),
   SOUTH(0, 1, 2, 3),
   UP(2, 3, 0, 1);
   public SpdyHeaderBlockRawEncoder field_0013;
   // $VF: synthetic field
   public static BlockModelRenderer$VertexTranslations[] $VALUES = new BlockModelRenderer$VertexTranslations[]{
      BlockModelRenderer$VertexTranslations.DOWN,
      BlockModelRenderer$VertexTranslations.UP,
      BlockModelRenderer$VertexTranslations.NORTH,
      BlockModelRenderer$VertexTranslations.SOUTH,
      WEST,
      BlockModelRenderer$VertexTranslations.EAST
   };
   public int field_178198_j;
   public int field_178201_i;
   public BlockStoneSlab field_0003;
   public int field_178191_g;
   public NioDatagramChannel field_0000;
   public static BlockModelRenderer$VertexTranslations[] VALUES = new BlockModelRenderer$VertexTranslations[6];
   public int field_178200_h;
   public UnidentifiedClass3697 field_0010;

   static {
      VALUES[EnumFacing.DOWN.getIndex()] = DOWN;
      VALUES[EnumFacing.UP.getIndex()] = UP;
      VALUES[EnumFacing.NORTH.getIndex()] = NORTH;
      VALUES[EnumFacing.SOUTH.getIndex()] = SOUTH;
      VALUES[EnumFacing.WEST.getIndex()] = WEST;
      VALUES[EnumFacing.EAST.getIndex()] = EAST;
   }

   public static BlockModelRenderer$VertexTranslations getVertexTranslations(EnumFacing var0) {
      return VALUES[var0.getIndex()];
   }

   public BlockModelRenderer$VertexTranslations(int var3, int var4, int var5, int var6) {
      this.field_178191_g = var3;
      this.field_178200_h = var4;
      this.field_178201_i = var5;
      this.field_178198_j = var6;
   }
}
