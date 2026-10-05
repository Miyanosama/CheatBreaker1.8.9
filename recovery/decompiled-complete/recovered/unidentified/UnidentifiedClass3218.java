package recovered.unidentified;

import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1;
import java.util.Random;
import junit.awtui.TestRunner;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleXRoom;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$MonumentRoomFitHelper;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$Piece;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$RoomDefinition;
import net.optifine.RandomTileEntity;

public class UnidentifiedClass3218 implements StructureOceanMonumentPieces$MonumentRoomFitHelper {
   public TestRunner field_0001;
   public RandomTileEntity field_0002;
   public AbstractNioChannel$AbstractNioUnsafe$1 field_0000;

   public UnidentifiedClass3218() {
   }

   @Override
   public boolean func_175969_a(StructureOceanMonumentPieces$RoomDefinition var1) {
      return var1.field_175966_c[EnumFacing.EAST.getIndex()] && !var1.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d;
   }

   @Override
   public StructureOceanMonumentPieces$Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      var2.field_175963_d = true;
      var2.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d = true;
      return new StructureOceanMonumentPieces$DoubleXRoom(var1, var2, var3);
   }
}
