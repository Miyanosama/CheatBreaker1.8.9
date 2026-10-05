package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Session;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeRenderer;

public class StructureOceanMonumentPieces$FitSimpleRoomTopHelper implements StructureOceanMonumentPieces$MonumentRoomFitHelper {
   public CategoryNodeRenderer field_0000;
   public Session field_0001;

   @Override
   public StructureOceanMonumentPieces$Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      var2.field_175963_d = true;
      return new StructureOceanMonumentPieces$SimpleTopRoom(var1, var2, var3);
   }

   public StructureOceanMonumentPieces$FitSimpleRoomTopHelper() {
   }

   @Override
   public boolean func_175969_a(StructureOceanMonumentPieces$RoomDefinition var1) {
      return !var1.field_175966_c[EnumFacing.WEST.getIndex()]
         && !var1.field_175966_c[EnumFacing.EAST.getIndex()]
         && !var1.field_175966_c[EnumFacing.NORTH.getIndex()]
         && !var1.field_175966_c[EnumFacing.SOUTH.getIndex()]
         && !var1.field_175966_c[EnumFacing.UP.getIndex()];
   }
}
