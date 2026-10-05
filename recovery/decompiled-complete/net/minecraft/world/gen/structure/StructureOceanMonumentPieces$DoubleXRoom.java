package net.minecraft.world.gen.structure;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$EntrySpliterator;
import java.util.Random;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.network.OldServerPinger$2;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureOceanMonumentPieces$DoubleXRoom extends StructureOceanMonumentPieces$Piece {
   public ModelDragon field_0001;
   public OldServerPinger$2 field_0003;
   public EntityAIMoveToBlock field_0000;
   public ConcurrentHashMapV8$EntrySpliterator field_0002;

   public StructureOceanMonumentPieces$DoubleXRoom(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      super(1, var1, var2, 2, 1, 1);
   }

   public StructureOceanMonumentPieces$DoubleXRoom() {
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      StructureOceanMonumentPieces$RoomDefinition var4 = this.k.field_175965_b[EnumFacing.EAST.getIndex()];
      StructureOceanMonumentPieces$RoomDefinition var5 = this.k;
      if (this.k.field_175967_a / 25 > 0) {
         this.a(var1, var3, 8, 0, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
         this.a(var1, var3, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
      }

      if (var5.field_175965_b[EnumFacing.UP.getIndex()] == null) {
         this.a(var1, var3, 1, 4, 1, 7, 4, 6, a);
      }

      if (var4.field_175965_b[EnumFacing.UP.getIndex()] == null) {
         this.a(var1, var3, 8, 4, 1, 14, 4, 6, a);
      }

      this.a(var1, var3, 0, 3, 0, 0, 3, 7, b, b, false);
      this.a(var1, var3, 15, 3, 0, 15, 3, 7, b, b, false);
      this.a(var1, var3, 1, 3, 0, 15, 3, 0, b, b, false);
      this.a(var1, var3, 1, 3, 7, 14, 3, 7, b, b, false);
      this.a(var1, var3, 0, 2, 0, 0, 2, 7, a, a, false);
      this.a(var1, var3, 15, 2, 0, 15, 2, 7, a, a, false);
      this.a(var1, var3, 1, 2, 0, 15, 2, 0, a, a, false);
      this.a(var1, var3, 1, 2, 7, 14, 2, 7, a, a, false);
      this.a(var1, var3, 0, 1, 0, 0, 1, 7, b, b, false);
      this.a(var1, var3, 15, 1, 0, 15, 1, 7, b, b, false);
      this.a(var1, var3, 1, 1, 0, 15, 1, 0, b, b, false);
      this.a(var1, var3, 1, 1, 7, 14, 1, 7, b, b, false);
      this.a(var1, var3, 5, 1, 0, 10, 1, 4, b, b, false);
      this.a(var1, var3, 6, 2, 0, 9, 2, 3, a, a, false);
      this.a(var1, var3, 5, 3, 0, 10, 3, 4, b, b, false);
      this.a(var1, e, 6, 2, 3, var3);
      this.a(var1, e, 9, 2, 3, var3);
      if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
         this.a(var1, var3, 3, 1, 0, 4, 2, 0, false);
      }

      if (var5.field_175966_c[EnumFacing.NORTH.getIndex()]) {
         this.a(var1, var3, 3, 1, 7, 4, 2, 7, false);
      }

      if (var5.field_175966_c[EnumFacing.WEST.getIndex()]) {
         this.a(var1, var3, 0, 1, 3, 0, 2, 4, false);
      }

      if (var4.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
         this.a(var1, var3, 11, 1, 0, 12, 2, 0, false);
      }

      if (var4.field_175966_c[EnumFacing.NORTH.getIndex()]) {
         this.a(var1, var3, 11, 1, 7, 12, 2, 7, false);
      }

      if (var4.field_175966_c[EnumFacing.EAST.getIndex()]) {
         this.a(var1, var3, 15, 1, 3, 15, 2, 4, false);
      }

      return true;
   }
}
