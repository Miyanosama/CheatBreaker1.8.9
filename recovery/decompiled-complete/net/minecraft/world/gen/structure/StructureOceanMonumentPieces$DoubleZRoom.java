package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureOceanMonumentPieces$DoubleZRoom extends StructureOceanMonumentPieces$Piece {
   public ComponentScatteredFeaturePieces$JunglePyramid$Stones field_0001;
   public RenderArrow field_0002;
   public InventoryEnderChest field_0000;

   public StructureOceanMonumentPieces$DoubleZRoom() {
   }

   public StructureOceanMonumentPieces$DoubleZRoom(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      super(1, var1, var2, 1, 1, 2);
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      StructureOceanMonumentPieces$RoomDefinition var4 = this.k.field_175965_b[EnumFacing.NORTH.getIndex()];
      StructureOceanMonumentPieces$RoomDefinition var5 = this.k;
      if (this.k.field_175967_a / 25 > 0) {
         this.a(var1, var3, 0, 8, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
         this.a(var1, var3, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
      }

      if (var5.field_175965_b[EnumFacing.UP.getIndex()] == null) {
         this.a(var1, var3, 1, 4, 1, 6, 4, 7, a);
      }

      if (var4.field_175965_b[EnumFacing.UP.getIndex()] == null) {
         this.a(var1, var3, 1, 4, 8, 6, 4, 14, a);
      }

      this.a(var1, var3, 0, 3, 0, 0, 3, 15, b, b, false);
      this.a(var1, var3, 7, 3, 0, 7, 3, 15, b, b, false);
      this.a(var1, var3, 1, 3, 0, 7, 3, 0, b, b, false);
      this.a(var1, var3, 1, 3, 15, 6, 3, 15, b, b, false);
      this.a(var1, var3, 0, 2, 0, 0, 2, 15, a, a, false);
      this.a(var1, var3, 7, 2, 0, 7, 2, 15, a, a, false);
      this.a(var1, var3, 1, 2, 0, 7, 2, 0, a, a, false);
      this.a(var1, var3, 1, 2, 15, 6, 2, 15, a, a, false);
      this.a(var1, var3, 0, 1, 0, 0, 1, 15, b, b, false);
      this.a(var1, var3, 7, 1, 0, 7, 1, 15, b, b, false);
      this.a(var1, var3, 1, 1, 0, 7, 1, 0, b, b, false);
      this.a(var1, var3, 1, 1, 15, 6, 1, 15, b, b, false);
      this.a(var1, var3, 1, 1, 1, 1, 1, 2, b, b, false);
      this.a(var1, var3, 6, 1, 1, 6, 1, 2, b, b, false);
      this.a(var1, var3, 1, 3, 1, 1, 3, 2, b, b, false);
      this.a(var1, var3, 6, 3, 1, 6, 3, 2, b, b, false);
      this.a(var1, var3, 1, 1, 13, 1, 1, 14, b, b, false);
      this.a(var1, var3, 6, 1, 13, 6, 1, 14, b, b, false);
      this.a(var1, var3, 1, 3, 13, 1, 3, 14, b, b, false);
      this.a(var1, var3, 6, 3, 13, 6, 3, 14, b, b, false);
      this.a(var1, var3, 2, 1, 6, 2, 3, 6, b, b, false);
      this.a(var1, var3, 5, 1, 6, 5, 3, 6, b, b, false);
      this.a(var1, var3, 2, 1, 9, 2, 3, 9, b, b, false);
      this.a(var1, var3, 5, 1, 9, 5, 3, 9, b, b, false);
      this.a(var1, var3, 3, 2, 6, 4, 2, 6, b, b, false);
      this.a(var1, var3, 3, 2, 9, 4, 2, 9, b, b, false);
      this.a(var1, var3, 2, 2, 7, 2, 2, 8, b, b, false);
      this.a(var1, var3, 5, 2, 7, 5, 2, 8, b, b, false);
      this.a(var1, e, 2, 2, 5, var3);
      this.a(var1, e, 5, 2, 5, var3);
      this.a(var1, e, 2, 2, 10, var3);
      this.a(var1, e, 5, 2, 10, var3);
      this.a(var1, b, 2, 3, 5, var3);
      this.a(var1, b, 5, 3, 5, var3);
      this.a(var1, b, 2, 3, 10, var3);
      this.a(var1, b, 5, 3, 10, var3);
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

      return true;
   }
}
