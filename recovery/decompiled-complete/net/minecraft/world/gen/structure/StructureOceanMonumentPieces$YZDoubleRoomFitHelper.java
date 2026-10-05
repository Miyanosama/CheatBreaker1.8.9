package net.minecraft.world.gen.structure;

import io.netty.channel.AbstractServerChannel;
import io.netty.handler.codec.spdy.SpdyVersion;
import java.util.Random;
import junit.swingui.TestSuitePanel$TestTreeCellRenderer;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.util.EnumFacing;

public class StructureOceanMonumentPieces$YZDoubleRoomFitHelper implements StructureOceanMonumentPieces$MonumentRoomFitHelper {
   public TestSuitePanel$TestTreeCellRenderer field_0002;
   public StructureNetherBridgePieces$NetherStalkRoom field_0004;
   public EntityAIMoveToBlock field_0001;
   public SpdyVersion field_0003;
   public AbstractServerChannel field_0000;

   @Override
   public StructureOceanMonumentPieces$Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      var2.field_175963_d = true;
      var2.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
      var2.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
      var2.field_175965_b[EnumFacing.NORTH.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
      return new StructureOceanMonumentPieces$DoubleYZRoom(var1, var2, var3);
   }

   public StructureOceanMonumentPieces$YZDoubleRoomFitHelper() {
   }

   @Override
   public boolean func_175969_a(StructureOceanMonumentPieces$RoomDefinition var1) {
      if (var1.field_175966_c[EnumFacing.NORTH.getIndex()]
         && !var1.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d
         && var1.field_175966_c[EnumFacing.UP.getIndex()]
         && !var1.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d) {
         StructureOceanMonumentPieces$RoomDefinition var2 = var1.field_175965_b[EnumFacing.NORTH.getIndex()];
         return var2.field_175966_c[EnumFacing.UP.getIndex()] && !var2.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d;
      } else {
         return false;
      }
   }
}
