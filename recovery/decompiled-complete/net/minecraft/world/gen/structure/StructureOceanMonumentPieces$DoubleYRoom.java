package net.minecraft.world.gen.structure;

import io.netty.channel.DefaultMessageSizeEstimator$1;
import java.util.Random;
import net.minecraft.client.gui.inventory.CreativeCrafting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureOceanMonumentPieces$DoubleYRoom extends StructureOceanMonumentPieces$Piece {
   public CreativeCrafting field_0000;
   public DefaultMessageSizeEstimator$1 field_0001;

   public StructureOceanMonumentPieces$DoubleYRoom(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      super(1, var1, var2, 1, 2, 1);
   }

   public StructureOceanMonumentPieces$DoubleYRoom() {
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.k.field_175967_a / 25 > 0) {
         this.a(var1, var3, 0, 0, this.k.field_175966_c[EnumFacing.DOWN.getIndex()]);
      }

      StructureOceanMonumentPieces$RoomDefinition var4 = this.k.field_175965_b[EnumFacing.UP.getIndex()];
      if (var4.field_175965_b[EnumFacing.UP.getIndex()] == null) {
         this.a(var1, var3, 1, 8, 1, 6, 8, 6, a);
      }

      this.a(var1, var3, 0, 4, 0, 0, 4, 7, b, b, false);
      this.a(var1, var3, 7, 4, 0, 7, 4, 7, b, b, false);
      this.a(var1, var3, 1, 4, 0, 6, 4, 0, b, b, false);
      this.a(var1, var3, 1, 4, 7, 6, 4, 7, b, b, false);
      this.a(var1, var3, 2, 4, 1, 2, 4, 2, b, b, false);
      this.a(var1, var3, 1, 4, 2, 1, 4, 2, b, b, false);
      this.a(var1, var3, 5, 4, 1, 5, 4, 2, b, b, false);
      this.a(var1, var3, 6, 4, 2, 6, 4, 2, b, b, false);
      this.a(var1, var3, 2, 4, 5, 2, 4, 6, b, b, false);
      this.a(var1, var3, 1, 4, 5, 1, 4, 5, b, b, false);
      this.a(var1, var3, 5, 4, 5, 5, 4, 6, b, b, false);
      this.a(var1, var3, 6, 4, 5, 6, 4, 5, b, b, false);
      StructureOceanMonumentPieces$RoomDefinition var5 = this.k;

      for (byte var6 = 1; var6 <= 5; var6 += 4) {
         byte var7 = 0;
         if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 2, var6, var7, 2, var6 + 2, var7, b, b, false);
            this.a(var1, var3, 5, var6, var7, 5, var6 + 2, var7, b, b, false);
            this.a(var1, var3, 3, var6 + 2, var7, 4, var6 + 2, var7, b, b, false);
         } else {
            this.a(var1, var3, 0, var6, var7, 7, var6 + 2, var7, b, b, false);
            this.a(var1, var3, 0, var6 + 1, var7, 7, var6 + 1, var7, a, a, false);
         }

         var7 = 7;
         if (var5.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 2, var6, var7, 2, var6 + 2, var7, b, b, false);
            this.a(var1, var3, 5, var6, var7, 5, var6 + 2, var7, b, b, false);
            this.a(var1, var3, 3, var6 + 2, var7, 4, var6 + 2, var7, b, b, false);
         } else {
            this.a(var1, var3, 0, var6, var7, 7, var6 + 2, var7, b, b, false);
            this.a(var1, var3, 0, var6 + 1, var7, 7, var6 + 1, var7, a, a, false);
         }

         byte var8 = 0;
         if (var5.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, var8, var6, 2, var8, var6 + 2, 2, b, b, false);
            this.a(var1, var3, var8, var6, 5, var8, var6 + 2, 5, b, b, false);
            this.a(var1, var3, var8, var6 + 2, 3, var8, var6 + 2, 4, b, b, false);
         } else {
            this.a(var1, var3, var8, var6, 0, var8, var6 + 2, 7, b, b, false);
            this.a(var1, var3, var8, var6 + 1, 0, var8, var6 + 1, 7, a, a, false);
         }

         var8 = 7;
         if (var5.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, var8, var6, 2, var8, var6 + 2, 2, b, b, false);
            this.a(var1, var3, var8, var6, 5, var8, var6 + 2, 5, b, b, false);
            this.a(var1, var3, var8, var6 + 2, 3, var8, var6 + 2, 4, b, b, false);
         } else {
            this.a(var1, var3, var8, var6, 0, var8, var6 + 2, 7, b, b, false);
            this.a(var1, var3, var8, var6 + 1, 0, var8, var6 + 1, 7, a, a, false);
         }

         var5 = var4;
      }

      return true;
   }
}
