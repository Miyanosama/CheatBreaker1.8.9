package recovered.unidentified;

import io.netty.handler.codec.UnsupportedMessageTypeException;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleYRoom;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$MonumentRoomFitHelper;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$Piece;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$RoomDefinition;
import net.optifine.shaders.uniform.ShaderParameterIndexed;

public class UnidentifiedClass3482 implements StructureOceanMonumentPieces$MonumentRoomFitHelper {
   public ShaderParameterIndexed field_0000;
   public UnsupportedMessageTypeException field_0001;

   @Override
   public boolean func_175969_a(StructureOceanMonumentPieces$RoomDefinition var1) {
      return var1.field_175966_c[EnumFacing.UP.getIndex()] && !var1.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d;
   }

   public UnidentifiedClass3482() {
   }

   @Override
   public StructureOceanMonumentPieces$Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      var2.field_175963_d = true;
      var2.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
      return new StructureOceanMonumentPieces$DoubleYRoom(var1, var2, var3);
   }
}
