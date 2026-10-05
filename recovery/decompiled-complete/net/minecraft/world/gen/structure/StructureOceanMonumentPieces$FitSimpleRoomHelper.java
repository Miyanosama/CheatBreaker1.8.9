package net.minecraft.world.gen.structure;

import io.netty.handler.ssl.SslHandler$6;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class StructureOceanMonumentPieces$FitSimpleRoomHelper implements StructureOceanMonumentPieces$MonumentRoomFitHelper {
   public SslHandler$6 field_0000;

   @Override
   public boolean func_175969_a(StructureOceanMonumentPieces$RoomDefinition var1) {
      return true;
   }

   public StructureOceanMonumentPieces$FitSimpleRoomHelper() {
   }

   @Override
   public StructureOceanMonumentPieces$Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      var2.field_175963_d = true;
      return new StructureOceanMonumentPieces$SimpleRoom(var1, var2, var3);
   }
}
