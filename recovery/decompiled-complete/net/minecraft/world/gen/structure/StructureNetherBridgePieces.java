package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import org.apache.log4j.helpers.UtilLoggingLevel;

public class StructureNetherBridgePieces {
   public static StructureNetherBridgePieces$PieceWeight[] secondaryComponents = new StructureNetherBridgePieces$PieceWeight[]{
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Corridor5.class, 25, 0, true),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Crossing2.class, 15, 5),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Corridor2.class, 5, 10),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Corridor.class, 5, 10),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Corridor3.class, 10, 3, true),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Corridor4.class, 7, 2),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$NetherStalkRoom.class, 5, 2)
   };
   public UtilLoggingLevel field_0002;
   public static StructureNetherBridgePieces$PieceWeight[] primaryComponents = new StructureNetherBridgePieces$PieceWeight[]{
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Straight.class, 30, 0, true),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Crossing3.class, 10, 4),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Crossing.class, 10, 4),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Stairs.class, 10, 3),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Throne.class, 5, 2),
      new StructureNetherBridgePieces$PieceWeight(StructureNetherBridgePieces$Entrance.class, 5, 1)
   };

   public static void registerNetherFortressPieces() {
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Crossing3.class, "NeBCr");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$End.class, "NeBEF");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Straight.class, "NeBS");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Corridor3.class, "NeCCS");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Corridor4.class, "NeCTB");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Entrance.class, "NeCE");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Crossing2.class, "NeSCSC");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Corridor.class, "NeSCLT");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Corridor5.class, "NeSC");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Corridor2.class, "NeSCRT");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$NetherStalkRoom.class, "NeCSR");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Throne.class, "NeMT");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Crossing.class, "NeRC");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Stairs.class, "NeSR");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces$Start.class, "NeStart");
   }

   public static StructureNetherBridgePieces$Piece func_175887_b(
      StructureNetherBridgePieces$PieceWeight var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      Class var8 = var0.weightClass;
      Object var9 = null;
      if (var8 == StructureNetherBridgePieces$Straight.class) {
         var9 = StructureNetherBridgePieces$Straight.func_175882_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$Crossing3.class) {
         var9 = StructureNetherBridgePieces$Crossing3.func_175885_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$Crossing.class) {
         var9 = StructureNetherBridgePieces$Crossing.func_175873_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$Stairs.class) {
         var9 = StructureNetherBridgePieces$Stairs.func_175872_a(var1, var2, var3, var4, var5, var7, var6);
      } else if (var8 == StructureNetherBridgePieces$Throne.class) {
         var9 = StructureNetherBridgePieces$Throne.func_175874_a(var1, var2, var3, var4, var5, var7, var6);
      } else if (var8 == StructureNetherBridgePieces$Entrance.class) {
         var9 = StructureNetherBridgePieces$Entrance.func_175881_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$Corridor5.class) {
         var9 = StructureNetherBridgePieces$Corridor5.func_175877_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$Corridor2.class) {
         var9 = StructureNetherBridgePieces$Corridor2.func_175876_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$Corridor.class) {
         var9 = StructureNetherBridgePieces$Corridor.func_175879_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$Corridor3.class) {
         var9 = StructureNetherBridgePieces$Corridor3.func_175883_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$Corridor4.class) {
         var9 = StructureNetherBridgePieces$Corridor4.func_175880_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$Crossing2.class) {
         var9 = StructureNetherBridgePieces$Crossing2.func_175878_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces$NetherStalkRoom.class) {
         var9 = StructureNetherBridgePieces$NetherStalkRoom.func_175875_a(var1, var2, var3, var4, var5, var6, var7);
      }

      return (StructureNetherBridgePieces$Piece)var9;
   }
}
