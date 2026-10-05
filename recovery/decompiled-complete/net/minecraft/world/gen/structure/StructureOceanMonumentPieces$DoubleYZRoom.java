package net.minecraft.world.gen.structure;

import io.netty.handler.codec.compression.JZlibEncoder$3;
import io.netty.util.concurrent.DefaultPromise$CauseHolder;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderMagmaCube;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

public class StructureOceanMonumentPieces$DoubleYZRoom extends StructureOceanMonumentPieces$Piece {
   public DefaultPromise$CauseHolder field_0002;
   public JZlibEncoder$3 field_0004;
   public WorldGenDeadBush field_0001;
   public RegistryNamespaced field_0003;
   public RenderMagmaCube field_0000;

   public StructureOceanMonumentPieces$DoubleYZRoom(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      super(1, var1, var2, 1, 2, 2);
   }

   public StructureOceanMonumentPieces$DoubleYZRoom() {
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      StructureOceanMonumentPieces$RoomDefinition var4 = this.k.field_175965_b[EnumFacing.NORTH.getIndex()];
      StructureOceanMonumentPieces$RoomDefinition var5 = this.k;
      StructureOceanMonumentPieces$RoomDefinition var6 = var4.field_175965_b[EnumFacing.UP.getIndex()];
      StructureOceanMonumentPieces$RoomDefinition var7 = var5.field_175965_b[EnumFacing.UP.getIndex()];
      if (this.k.field_175967_a / 25 > 0) {
         this.a(var1, var3, 0, 8, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
         this.a(var1, var3, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
      }

      if (var7.field_175965_b[EnumFacing.UP.getIndex()] == null) {
         this.a(var1, var3, 1, 8, 1, 6, 8, 7, a);
      }

      if (var6.field_175965_b[EnumFacing.UP.getIndex()] == null) {
         this.a(var1, var3, 1, 8, 8, 6, 8, 14, a);
      }

      for (int var8 = 1; var8 <= 7; var8++) {
         IBlockState var9 = b;
         if (var8 == 2 || var8 == 6) {
            var9 = a;
         }

         this.a(var1, var3, 0, var8, 0, 0, var8, 15, var9, var9, false);
         this.a(var1, var3, 7, var8, 0, 7, var8, 15, var9, var9, false);
         this.a(var1, var3, 1, var8, 0, 6, var8, 0, var9, var9, false);
         this.a(var1, var3, 1, var8, 15, 6, var8, 15, var9, var9, false);
      }

      for (int var10 = 1; var10 <= 7; var10++) {
         IBlockState var11 = c;
         if (var10 == 2 || var10 == 6) {
            var11 = e;
         }

         this.a(var1, var3, 3, var10, 7, 4, var10, 8, var11, var11, false);
      }

      if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
         this.a(var1, var3, 3, 1, 0, 4, 2, 0, false);
      }

      if (var5.field_175966_c[EnumFacing.EAST.getIndex()]) {
         this.a(var1, var3, 7, 1, 3, 7, 2, 4, false);
      }

      if (var5.field_175966_c[EnumFacing.WEST.getIndex()]) {
         this.a(var1, var3, 0, 1, 3, 0, 2, 4, false);
      }

      if (var4.field_175966_c[EnumFacing.NORTH.getIndex()]) {
         this.a(var1, var3, 3, 1, 15, 4, 2, 15, false);
      }

      if (var4.field_175966_c[EnumFacing.WEST.getIndex()]) {
         this.a(var1, var3, 0, 1, 11, 0, 2, 12, false);
      }

      if (var4.field_175966_c[EnumFacing.EAST.getIndex()]) {
         this.a(var1, var3, 7, 1, 11, 7, 2, 12, false);
      }

      if (var7.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
         this.a(var1, var3, 3, 5, 0, 4, 6, 0, false);
      }

      if (var7.field_175966_c[EnumFacing.EAST.getIndex()]) {
         this.a(var1, var3, 7, 5, 3, 7, 6, 4, false);
         this.a(var1, var3, 5, 4, 2, 6, 4, 5, b, b, false);
         this.a(var1, var3, 6, 1, 2, 6, 3, 2, b, b, false);
         this.a(var1, var3, 6, 1, 5, 6, 3, 5, b, b, false);
      }

      if (var7.field_175966_c[EnumFacing.WEST.getIndex()]) {
         this.a(var1, var3, 0, 5, 3, 0, 6, 4, false);
         this.a(var1, var3, 1, 4, 2, 2, 4, 5, b, b, false);
         this.a(var1, var3, 1, 1, 2, 1, 3, 2, b, b, false);
         this.a(var1, var3, 1, 1, 5, 1, 3, 5, b, b, false);
      }

      if (var6.field_175966_c[EnumFacing.NORTH.getIndex()]) {
         this.a(var1, var3, 3, 5, 15, 4, 6, 15, false);
      }

      if (var6.field_175966_c[EnumFacing.WEST.getIndex()]) {
         this.a(var1, var3, 0, 5, 11, 0, 6, 12, false);
         this.a(var1, var3, 1, 4, 10, 2, 4, 13, b, b, false);
         this.a(var1, var3, 1, 1, 10, 1, 3, 10, b, b, false);
         this.a(var1, var3, 1, 1, 13, 1, 3, 13, b, b, false);
      }

      if (var6.field_175966_c[EnumFacing.EAST.getIndex()]) {
         this.a(var1, var3, 7, 5, 11, 7, 6, 12, false);
         this.a(var1, var3, 5, 4, 10, 6, 4, 13, b, b, false);
         this.a(var1, var3, 6, 1, 10, 6, 3, 10, b, b, false);
         this.a(var1, var3, 6, 1, 13, 6, 3, 13, b, b, false);
      }

      return true;
   }
}
