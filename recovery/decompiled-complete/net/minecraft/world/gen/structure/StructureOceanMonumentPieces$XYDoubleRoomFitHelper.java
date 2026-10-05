package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.util.EnumFacing;
import recovered.unidentified.UnidentifiedClass3884;

public class StructureOceanMonumentPieces$XYDoubleRoomFitHelper implements StructureOceanMonumentPieces$MonumentRoomFitHelper {
   public UnidentifiedClass3884 field_0000;
   public EntityAIBeg field_0001;

   public StructureOceanMonumentPieces$XYDoubleRoomFitHelper() {
   }

   @Override
   public boolean func_175969_a(StructureOceanMonumentPieces$RoomDefinition var1) {
      if (var1.field_175966_c[EnumFacing.EAST.getIndex()]
         && !var1.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d
         && var1.field_175966_c[EnumFacing.UP.getIndex()]
         && !var1.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d) {
         StructureOceanMonumentPieces$RoomDefinition var2 = var1.field_175965_b[EnumFacing.EAST.getIndex()];
         return var2.field_175966_c[EnumFacing.UP.getIndex()] && !var2.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d;
      } else {
         return false;
      }
   }

   @Override
   public StructureOceanMonumentPieces$Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      var2.field_175963_d = true;
      var2.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d = true;
      var2.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
      var2.field_175965_b[EnumFacing.EAST.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
      return new StructureOceanMonumentPieces$DoubleXYRoom(var1, var2, var3);
   }
}
