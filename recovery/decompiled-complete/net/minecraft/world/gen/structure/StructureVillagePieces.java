package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import io.netty.buffer.PooledDirectByteBuf;
import io.netty.handler.ssl.SslHandler$4;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import recovered.unidentified.UnidentifiedClass3218;

public class StructureVillagePieces {
   public PooledDirectByteBuf field_0001;
   public UnidentifiedClass3218 field_0003;
   public RangedAttribute field_0000;
   public SslHandler$4 field_0002;

   public static StructureComponent method_02732(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      if (var7 > 3 + var0.terrainType) {
         return null;
      } else if (Math.abs(var3 - var0.getBoundingBox().minX) <= 112 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 112) {
         StructureBoundingBox var8 = StructureVillagePieces$Path.func_175848_a(var0, var1, var2, var3, var4, var5, var6);
         if (var8 != null && var8.minY > 10) {
            StructureVillagePieces$Path var9 = new StructureVillagePieces$Path(var0, var7, var2, var8, var6);
            int var10 = (var9.l.minX + var9.l.maxX) / 2;
            int var11 = (var9.l.minZ + var9.l.maxZ) / 2;
            int var12 = var9.l.maxX - var9.l.minX;
            int var13 = var9.l.maxZ - var9.l.minZ;
            int var14 = var12 > var13 ? var12 : var13;
            if (var0.getWorldChunkManager().areBiomesViable(var10, var11, var14 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
               var1.add(var9);
               var0.field_74930_j.add(var9);
               return var9;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static StructureVillagePieces$Village func_176065_a(
      StructureVillagePieces$Start var0,
      StructureVillagePieces$PieceWeight var1,
      List<StructureComponent> var2,
      Random var3,
      int var4,
      int var5,
      int var6,
      EnumFacing var7,
      int var8
   ) {
      Class var9 = var1.villagePieceClass;
      Object var10 = null;
      if (var9 == StructureVillagePieces$House4Garden.class) {
         var10 = StructureVillagePieces$House4Garden.func_175858_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces$Church.class) {
         var10 = StructureVillagePieces$Church.func_175854_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces$House1.class) {
         var10 = StructureVillagePieces$House1.func_175850_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces$WoodHut.class) {
         var10 = StructureVillagePieces$WoodHut.func_175853_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces$Hall.class) {
         var10 = StructureVillagePieces$Hall.func_175857_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces$Field1.class) {
         var10 = StructureVillagePieces$Field1.func_175851_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces$Field2.class) {
         var10 = StructureVillagePieces$Field2.func_175852_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces$House2.class) {
         var10 = StructureVillagePieces$House2.func_175855_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces$House3.class) {
         var10 = StructureVillagePieces$House3.func_175849_a(var0, var2, var3, var4, var5, var6, var7, var8);
      }

      return (StructureVillagePieces$Village)var10;
   }

   public static StructureVillagePieces$Village func_176067_c(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      int var8 = func_75079_a(var0.structureVillageWeightedPieceList);
      if (var8 <= 0) {
         return null;
      } else {
         int var9 = 0;

         while (var9 < 5) {
            var9++;
            int var10 = var2.nextInt(var8);

            for (StructureVillagePieces$PieceWeight var12 : var0.structureVillageWeightedPieceList) {
               var10 -= var12.villagePieceWeight;
               if (var10 < 0) {
                  if (!var12.canSpawnMoreVillagePiecesOfType(var7)
                     || var12 == var0.structVillagePieceWeight && var0.structureVillageWeightedPieceList.size() > 1) {
                     break;
                  }

                  StructureVillagePieces$Village var13 = func_176065_a(var0, var12, var1, var2, var3, var4, var5, var6, var7);
                  if (var13 != null) {
                     var12.villagePiecesSpawned++;
                     var0.structVillagePieceWeight = var12;
                     if (!var12.canSpawnMoreVillagePieces()) {
                        var0.structureVillageWeightedPieceList.remove(var12);
                     }

                     return var13;
                  }
               }
            }
         }

         StructureBoundingBox var14 = StructureVillagePieces$Torch.func_175856_a(var0, var1, var2, var3, var4, var5, var6);
         return var14 != null ? new StructureVillagePieces$Torch(var0, var7, var2, var14, var6) : null;
      }
   }

   public static StructureComponent method_02736(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      if (var7 > 50) {
         return null;
      } else if (Math.abs(var3 - var0.getBoundingBox().minX) <= 112 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 112) {
         StructureVillagePieces$Village var8 = func_176067_c(var0, var1, var2, var3, var4, var5, var6, var7 + 1);
         if (var8 != null) {
            int var9 = (var8.l.minX + var8.l.maxX) / 2;
            int var10 = (var8.l.minZ + var8.l.maxZ) / 2;
            int var11 = var8.l.maxX - var8.l.minX;
            int var12 = var8.l.maxZ - var8.l.minZ;
            int var13 = var11 > var12 ? var11 : var12;
            if (var0.getWorldChunkManager().areBiomesViable(var9, var10, var13 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
               var1.add(var8);
               var0.field_74932_i.add(var8);
               return var8;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static void registerVillagePieces() {
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$House1.class, "ViBH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$Field1.class, "ViDF");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$Field2.class, "ViF");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$Torch.class, "ViL");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$Hall.class, "ViPH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$House4Garden.class, "ViSH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$WoodHut.class, "ViSmH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$Church.class, "ViST");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$House2.class, "ViS");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$Start.class, "ViStart");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$Path.class, "ViSR");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$House3.class, "ViTRH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces$Well.class, "ViW");
   }

   public static int func_75079_a(List<StructureVillagePieces$PieceWeight> var0) {
      boolean var1 = false;
      int var2 = 0;

      for (StructureVillagePieces$PieceWeight var4 : var0) {
         if (var4.villagePiecesLimit > 0 && var4.villagePiecesSpawned < var4.villagePiecesLimit) {
            var1 = true;
         }

         var2 += var4.villagePieceWeight;
      }

      return var1 ? var2 : -1;
   }

   public static List<StructureVillagePieces$PieceWeight> getStructureVillageWeightedPieceList(Random var0, int var1) {
      ArrayList var2 = Lists.newArrayList();
      var2.add(
         new StructureVillagePieces$PieceWeight(StructureVillagePieces$House4Garden.class, 4, MathHelper.getRandomIntegerInRange(var0, 2 + var1, 4 + var1 * 2))
      );
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$Church.class, 20, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 1 + var1)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$House1.class, 20, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 2 + var1)));
      var2.add(
         new StructureVillagePieces$PieceWeight(StructureVillagePieces$WoodHut.class, 3, MathHelper.getRandomIntegerInRange(var0, 2 + var1, 5 + var1 * 3))
      );
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$Hall.class, 15, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 2 + var1)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$Field1.class, 3, MathHelper.getRandomIntegerInRange(var0, 1 + var1, 4 + var1)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$Field2.class, 3, MathHelper.getRandomIntegerInRange(var0, 2 + var1, 4 + var1 * 2)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$House2.class, 15, MathHelper.getRandomIntegerInRange(var0, 0, 1 + var1)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$House3.class, 8, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 3 + var1 * 2)));
      Iterator var3 = var2.iterator();

      while (var3.hasNext()) {
         if (((StructureVillagePieces$PieceWeight)var3.next()).villagePiecesLimit == 0) {
            var3.remove();
         }
      }

      return var2;
   }
}
