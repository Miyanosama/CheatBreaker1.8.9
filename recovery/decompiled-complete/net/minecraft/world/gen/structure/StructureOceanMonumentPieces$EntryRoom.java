package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.apache.log4j.lf5.util.LogFileParser$1;
import recovered.unidentified.UnidentifiedClass4676;

public class StructureOceanMonumentPieces$EntryRoom extends StructureOceanMonumentPieces$Piece {
   public LogFileParser$1 field_0001;
   public PlayerControllerMP field_0002;
   public UnidentifiedClass4676 field_0000;

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      this.a(var1, var3, 0, 3, 0, 2, 3, 7, b, b, false);
      this.a(var1, var3, 5, 3, 0, 7, 3, 7, b, b, false);
      this.a(var1, var3, 0, 2, 0, 1, 2, 7, b, b, false);
      this.a(var1, var3, 6, 2, 0, 7, 2, 7, b, b, false);
      this.a(var1, var3, 0, 1, 0, 0, 1, 7, b, b, false);
      this.a(var1, var3, 7, 1, 0, 7, 1, 7, b, b, false);
      this.a(var1, var3, 0, 1, 7, 7, 3, 7, b, b, false);
      this.a(var1, var3, 1, 1, 0, 2, 3, 0, b, b, false);
      this.a(var1, var3, 5, 1, 0, 6, 3, 0, b, b, false);
      if (this.k.field_175966_c[EnumFacing.NORTH.getIndex()]) {
         this.a(var1, var3, 3, 1, 7, 4, 2, 7, false);
      }

      if (this.k.field_175966_c[EnumFacing.WEST.getIndex()]) {
         this.a(var1, var3, 0, 1, 3, 1, 2, 4, false);
      }

      if (this.k.field_175966_c[EnumFacing.EAST.getIndex()]) {
         this.a(var1, var3, 6, 1, 3, 7, 2, 4, false);
      }

      return true;
   }

   public StructureOceanMonumentPieces$EntryRoom() {
   }

   public StructureOceanMonumentPieces$EntryRoom(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2) {
      super(1, var1, var2, 1, 1, 1);
   }
}
