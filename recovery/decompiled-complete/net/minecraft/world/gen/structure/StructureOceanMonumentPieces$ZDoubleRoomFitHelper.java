package net.minecraft.world.gen.structure;

import java.util.Random;
import javax.vecmath.SingularMatrixException;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.client.renderer.BlockModelShapes$1;
import net.minecraft.util.EnumFacing;

public class StructureOceanMonumentPieces$ZDoubleRoomFitHelper implements StructureOceanMonumentPieces$MonumentRoomFitHelper {
   public BlockEnchantmentTable field_0001;
   public SingularMatrixException field_0002;
   public BlockModelShapes$1 field_0000;

   @Override
   public StructureOceanMonumentPieces$Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      StructureOceanMonumentPieces$RoomDefinition var4 = var2;
      if (!var2.field_175966_c[EnumFacing.NORTH.getIndex()] || var2.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d) {
         var4 = var2.field_175965_b[EnumFacing.SOUTH.getIndex()];
      }

      var4.field_175963_d = true;
      var4.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
      return new StructureOceanMonumentPieces$DoubleZRoom(var1, var4, var3);
   }

   public StructureOceanMonumentPieces$ZDoubleRoomFitHelper() {
   }

   @Override
   public boolean func_175969_a(StructureOceanMonumentPieces$RoomDefinition var1) {
      return var1.field_175966_c[EnumFacing.NORTH.getIndex()] && !var1.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d;
   }
}
