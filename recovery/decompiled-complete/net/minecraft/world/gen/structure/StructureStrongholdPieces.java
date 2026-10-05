package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.GlStateManager$BooleanState;
import net.minecraft.client.resources.SimpleReloadableResourceManager$1;
import net.minecraft.util.EnumFacing;

public class StructureStrongholdPieces {
   public static StructureStrongholdPieces$PieceWeight[] pieceWeightArray = new StructureStrongholdPieces$PieceWeight[]{
      new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$Straight.class, 40, 0),
      new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$Prison.class, 5, 5),
      new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$LeftTurn.class, 20, 0),
      new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$RightTurn.class, 20, 0),
      new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$RoomCrossing.class, 10, 6),
      new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$StairsStraight.class, 5, 5),
      new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$Stairs.class, 5, 5),
      new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$Crossing.class, 5, 4),
      new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$ChestCorridor.class, 5, 4),
      new StructureStrongholdPieces$1(StructureStrongholdPieces$Library.class, 10, 2),
      new StructureStrongholdPieces$2(StructureStrongholdPieces$PortalRoom.class, 20, 1)
   };
   public static int totalWeight;
   public static StructureStrongholdPieces$Stones strongholdStones = new StructureStrongholdPieces$Stones(null);
   public static List<StructureStrongholdPieces$PieceWeight> structurePieceList;
   public SimpleReloadableResourceManager$1 field_0000;
   public static Class<? extends StructureStrongholdPieces$Stronghold> strongComponentType;
   public GlStateManager$BooleanState field_0006;

   public static void prepareStructurePieces() {
      structurePieceList = Lists.newArrayList();

      for (StructureStrongholdPieces$PieceWeight var3 : pieceWeightArray) {
         var3.instancesSpawned = 0;
         structurePieceList.add(var3);
      }

      strongComponentType = null;
   }

   public static StructureStrongholdPieces$Stronghold func_175955_b(
      StructureStrongholdPieces$Stairs2 var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      if (!canAddStructurePieces()) {
         return null;
      } else {
         if (strongComponentType != null) {
            StructureStrongholdPieces$Stronghold var8 = func_175954_a(strongComponentType, var1, var2, var3, var4, var5, var6, var7);
            strongComponentType = null;
            if (var8 != null) {
               return var8;
            }
         }

         int var13 = 0;

         while (var13 < 5) {
            var13++;
            int var9 = var2.nextInt(totalWeight);

            for (StructureStrongholdPieces$PieceWeight var11 : structurePieceList) {
               var9 -= var11.pieceWeight;
               if (var9 < 0) {
                  if (!var11.canSpawnMoreStructuresOfType(var7) || var11 == var0.strongholdPieceWeight) {
                     break;
                  }

                  StructureStrongholdPieces$Stronghold var12 = func_175954_a(var11.pieceClass, var1, var2, var3, var4, var5, var6, var7);
                  if (var12 != null) {
                     var11.instancesSpawned++;
                     var0.strongholdPieceWeight = var11;
                     if (!var11.canSpawnMoreStructures()) {
                        structurePieceList.remove(var11);
                     }

                     return var12;
                  }
               }
            }
         }

         StructureBoundingBox var14 = StructureStrongholdPieces$Corridor.func_175869_a(var1, var2, var3, var4, var5, var6);
         return var14 != null && var14.minY > 1 ? new StructureStrongholdPieces$Corridor(var7, var2, var14, var6) : null;
      }
   }

   public static boolean canAddStructurePieces() {
      boolean var0 = false;
      totalWeight = 0;

      for (StructureStrongholdPieces$PieceWeight var2 : structurePieceList) {
         if (var2.instancesLimit > 0 && var2.instancesSpawned < var2.instancesLimit) {
            var0 = true;
         }

         totalWeight = totalWeight + var2.pieceWeight;
      }

      return var0;
   }

   public static StructureStrongholdPieces$Stronghold func_175954_a(
      Class<? extends StructureStrongholdPieces$Stronghold> var0,
      List<StructureComponent> var1,
      Random var2,
      int var3,
      int var4,
      int var5,
      EnumFacing var6,
      int var7
   ) {
      Object var8 = null;
      if (var0 == StructureStrongholdPieces$Straight.class) {
         var8 = StructureStrongholdPieces$Straight.func_175862_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$Prison.class) {
         var8 = StructureStrongholdPieces$Prison.func_175860_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$LeftTurn.class) {
         var8 = StructureStrongholdPieces$LeftTurn.func_175867_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$RightTurn.class) {
         var8 = StructureStrongholdPieces$RightTurn.func_175867_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$RoomCrossing.class) {
         var8 = StructureStrongholdPieces$RoomCrossing.func_175859_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$StairsStraight.class) {
         var8 = StructureStrongholdPieces$StairsStraight.func_175861_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$Stairs.class) {
         var8 = StructureStrongholdPieces$Stairs.func_175863_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$Crossing.class) {
         var8 = StructureStrongholdPieces$Crossing.func_175866_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$ChestCorridor.class) {
         var8 = StructureStrongholdPieces$ChestCorridor.func_175868_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$Library.class) {
         var8 = StructureStrongholdPieces$Library.func_175864_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var0 == StructureStrongholdPieces$PortalRoom.class) {
         var8 = StructureStrongholdPieces$PortalRoom.func_175865_a(var1, var2, var3, var4, var5, var6, var7);
      }

      return (StructureStrongholdPieces$Stronghold)var8;
   }

   public static StructureComponent func_175953_c(
      StructureStrongholdPieces$Stairs2 var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      if (var7 > 50) {
         return null;
      } else if (Math.abs(var3 - var0.getBoundingBox().minX) <= 112 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 112) {
         StructureStrongholdPieces$Stronghold var8 = func_175955_b(var0, var1, var2, var3, var4, var5, var6, var7 + 1);
         if (var8 != null) {
            var1.add(var8);
            var0.field_75026_c.add(var8);
         }

         return var8;
      } else {
         return null;
      }
   }

   public static void registerStrongholdPieces() {
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$ChestCorridor.class, "SHCC");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$Corridor.class, "SHFC");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$Crossing.class, "SH5C");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$LeftTurn.class, "SHLT");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$Library.class, "SHLi");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$PortalRoom.class, "SHPR");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$Prison.class, "SHPH");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$RightTurn.class, "SHRT");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$RoomCrossing.class, "SHRC");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$Stairs.class, "SHSD");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$Stairs2.class, "SHStart");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$Straight.class, "SHS");
      MapGenStructureIO.registerStructureComponent(StructureStrongholdPieces$StairsStraight.class, "SHSSD");
   }
}
