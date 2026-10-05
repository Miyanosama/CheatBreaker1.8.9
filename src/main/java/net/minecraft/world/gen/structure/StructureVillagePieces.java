package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

public class StructureVillagePieces {
   public static StructureComponent method_02732(
      StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      if (var7 > 3 + var0.terrainType) {
         return null;
      } else if (Math.abs(var3 - var0.getBoundingBox().minX) <= 112 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 112) {
         StructureBoundingBox var8 = StructureVillagePieces.Path.func_175848_a(var0, var1, var2, var3, var4, var5, var6);
         if (var8 != null && var8.minY > 10) {
            StructureVillagePieces.Path var9 = new StructureVillagePieces.Path(var0, var7, var2, var8, var6);
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

   public static StructureVillagePieces.Village func_176065_a(
      StructureVillagePieces.Start var0,
      StructureVillagePieces.PieceWeight var1,
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
      if (var9 == StructureVillagePieces.House4Garden.class) {
         var10 = StructureVillagePieces.House4Garden.func_175858_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces.Church.class) {
         var10 = StructureVillagePieces.Church.func_175854_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces.House1.class) {
         var10 = StructureVillagePieces.House1.func_175850_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces.WoodHut.class) {
         var10 = StructureVillagePieces.WoodHut.func_175853_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces.Hall.class) {
         var10 = StructureVillagePieces.Hall.func_175857_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces.Field1.class) {
         var10 = StructureVillagePieces.Field1.func_175851_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces.Field2.class) {
         var10 = StructureVillagePieces.Field2.func_175852_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces.House2.class) {
         var10 = StructureVillagePieces.House2.func_175855_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if (var9 == StructureVillagePieces.House3.class) {
         var10 = StructureVillagePieces.House3.func_175849_a(var0, var2, var3, var4, var5, var6, var7, var8);
      }

      return (StructureVillagePieces.Village)var10;
   }

   public static StructureVillagePieces.Village func_176067_c(
      StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      int var8 = func_75079_a(var0.structureVillageWeightedPieceList);
      if (var8 <= 0) {
         return null;
      } else {
         int var9 = 0;

         while (var9 < 5) {
            var9++;
            int var10 = var2.nextInt(var8);

            for (StructureVillagePieces.PieceWeight var12 : var0.structureVillageWeightedPieceList) {
               var10 -= var12.villagePieceWeight;
               if (var10 < 0) {
                  if (!var12.canSpawnMoreVillagePiecesOfType(var7)
                     || var12 == var0.structVillagePieceWeight && var0.structureVillageWeightedPieceList.size() > 1) {
                     break;
                  }

                  StructureVillagePieces.Village var13 = func_176065_a(var0, var12, var1, var2, var3, var4, var5, var6, var7);
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

         StructureBoundingBox var14 = StructureVillagePieces.Torch.func_175856_a(var0, var1, var2, var3, var4, var5, var6);
         return var14 != null ? new StructureVillagePieces.Torch(var0, var7, var2, var14, var6) : null;
      }
   }

   public static StructureComponent method_02736(
      StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      if (var7 > 50) {
         return null;
      } else if (Math.abs(var3 - var0.getBoundingBox().minX) <= 112 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 112) {
         StructureVillagePieces.Village var8 = func_176067_c(var0, var1, var2, var3, var4, var5, var6, var7 + 1);
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
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.House1.class, "ViBH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Field1.class, "ViDF");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Field2.class, "ViF");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Torch.class, "ViL");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Hall.class, "ViPH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.House4Garden.class, "ViSH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.WoodHut.class, "ViSmH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Church.class, "ViST");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.House2.class, "ViS");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Start.class, "ViStart");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Path.class, "ViSR");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.House3.class, "ViTRH");
      MapGenStructureIO.registerStructureComponent(StructureVillagePieces.Well.class, "ViW");
   }

   public static int func_75079_a(List<StructureVillagePieces.PieceWeight> var0) {
      boolean var1 = false;
      int var2 = 0;

      for (StructureVillagePieces.PieceWeight var4 : var0) {
         if (var4.villagePiecesLimit > 0 && var4.villagePiecesSpawned < var4.villagePiecesLimit) {
            var1 = true;
         }

         var2 += var4.villagePieceWeight;
      }

      return var1 ? var2 : -1;
   }

   public static List<StructureVillagePieces.PieceWeight> getStructureVillageWeightedPieceList(Random var0, int var1) {
      ArrayList var2 = Lists.newArrayList();
      var2.add(
         new StructureVillagePieces.PieceWeight(StructureVillagePieces.House4Garden.class, 4, MathHelper.getRandomIntegerInRange(var0, 2 + var1, 4 + var1 * 2))
      );
      var2.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.Church.class, 20, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 1 + var1)));
      var2.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.House1.class, 20, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 2 + var1)));
      var2.add(
         new StructureVillagePieces.PieceWeight(StructureVillagePieces.WoodHut.class, 3, MathHelper.getRandomIntegerInRange(var0, 2 + var1, 5 + var1 * 3))
      );
      var2.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.Hall.class, 15, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 2 + var1)));
      var2.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.Field1.class, 3, MathHelper.getRandomIntegerInRange(var0, 1 + var1, 4 + var1)));
      var2.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.Field2.class, 3, MathHelper.getRandomIntegerInRange(var0, 2 + var1, 4 + var1 * 2)));
      var2.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.House2.class, 15, MathHelper.getRandomIntegerInRange(var0, 0, 1 + var1)));
      var2.add(new StructureVillagePieces.PieceWeight(StructureVillagePieces.House3.class, 8, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 3 + var1 * 2)));
      Iterator var3 = var2.iterator();

      while (var3.hasNext()) {
         if (((StructureVillagePieces.PieceWeight)var3.next()).villagePiecesLimit == 0) {
            var3.remove();
         }
      }

      return var2;
   }

   public static class Church extends StructureVillagePieces.Village {
      @Override
      public int func_180779_c(int var1, int var2) {
         return 2;
      }

      public Church(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
      }

      public Church() {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 12 - 1, 0);
         }

         this.a(var1, var3, 1, 1, 1, 3, 3, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 1, 5, 1, 3, 9, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 0, 3, 0, 8, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 1, 0, 3, 10, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 1, 1, 0, 10, 3, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 4, 1, 1, 4, 10, 3, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 4, 0, 4, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 4, 0, 4, 4, 4, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 1, 8, 3, 4, 8, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 5, 4, 3, 10, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 5, 5, 3, 5, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 9, 0, 4, 9, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 4, 0, 4, 4, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 11, 2, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 11, 2, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 2, 11, 0, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 2, 11, 4, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 1, 1, 6, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 1, 1, 7, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 2, 1, 7, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 3, 1, 6, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 3, 1, 7, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 1, 1, 5, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 2, 1, 6, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 3, 1, 5, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 1)), 1, 2, 7, var3);
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 0)), 3, 2, 7, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 3, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 3, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 6, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 7, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 6, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 7, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 6, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 7, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 6, 4, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 7, 4, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 3, 6, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 3, 6, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 3, 8, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.getOpposite()), 2, 4, 7, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.rotateY()), 1, 4, 6, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.rotateYCCW()), 3, 4, 6, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m), 2, 4, 5, var3);
         int var4 = this.a(Blocks.ladder, 4);

         for (int var5 = 1; var5 <= 9; var5++) {
            this.a(var1, Blocks.ladder.getStateFromMeta(var4), 3, var5, 3, var3);
         }

         this.a(var1, Blocks.air.getDefaultState(), 2, 1, 0, var3);
         this.a(var1, Blocks.air.getDefaultState(), 2, 2, 0, var3);
         this.placeDoorCurrentPosition(var1, var3, var2, 2, 1, 0, EnumFacing.getHorizontal(this.a(Blocks.oak_door, 1)));
         if (this.a(var1, 2, 0, -1, var3).getBlock().getMaterial() == Material.air && this.a(var1, 2, -1, -1, var3).getBlock().getMaterial() != Material.air) {
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 2, 0, -1, var3);
         }

         for (int var7 = 0; var7 < 9; var7++) {
            for (int var6 = 0; var6 < 5; var6++) {
               this.b(var1, var6, 12, var7, var3);
               this.b(var1, Blocks.cobblestone.getDefaultState(), var6, -1, var7, var3);
            }
         }

         this.a(var1, var3, 2, 1, 2, 1);
         return true;
      }

      public static StructureVillagePieces.Church func_175854_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
      ) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 5, 12, 9, var6);
         return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
            ? new StructureVillagePieces.Church(var0, var7, var2, var8, var6)
            : null;
      }
   }

   public static class Field1 extends StructureVillagePieces.Village {
      public Block cropTypeB;
      public Block cropTypeD;
      public Block cropTypeA;
      public Block cropTypeC;

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 4 - 1, 0);
         }

         this.a(var1, var3, 0, 1, 0, 12, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.a(var1, var3, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.a(var1, var3, 7, 0, 1, 8, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.a(var1, var3, 10, 0, 1, 11, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 0, 0, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 6, 0, 0, 6, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 12, 0, 0, 12, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 0, 11, 0, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 8, 11, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
         this.a(var1, var3, 9, 0, 1, 9, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);

         for (int var4 = 1; var4 <= 7; var4++) {
            this.a(var1, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 1, 1, var4, var3);
            this.a(var1, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 2, 1, var4, var3);
            this.a(var1, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 4, 1, var4, var3);
            this.a(var1, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 5, 1, var4, var3);
            this.a(var1, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 7, 1, var4, var3);
            this.a(var1, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 8, 1, var4, var3);
            this.a(var1, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 10, 1, var4, var3);
            this.a(var1, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 11, 1, var4, var3);
         }

         for (int var6 = 0; var6 < 9; var6++) {
            for (int var5 = 0; var5 < 13; var5++) {
               this.b(var1, var5, 4, var6, var3);
               this.b(var1, Blocks.dirt.getDefaultState(), var5, -1, var6, var3);
            }
         }

         return true;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.cropTypeA = Block.getBlockById(var1.getInteger("CA"));
         this.cropTypeB = Block.getBlockById(var1.getInteger("CB"));
         this.cropTypeC = Block.getBlockById(var1.getInteger("CC"));
         this.cropTypeD = Block.getBlockById(var1.getInteger("CD"));
      }

      public Field1() {
      }

      public Block func_151559_a(Random var1) {
         switch (var1.nextInt(5)) {
            case 0:
               return Blocks.carrots;
            case 1:
               return Blocks.potatoes;
            default:
               return Blocks.wheat;
         }
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
         var1.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
         var1.setInteger("CC", Block.blockRegistry.getIDForObject(this.cropTypeC));
         var1.setInteger("CD", Block.blockRegistry.getIDForObject(this.cropTypeD));
      }

      public static StructureVillagePieces.Field1 func_175851_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
      ) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 13, 4, 9, var6);
         return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
            ? new StructureVillagePieces.Field1(var0, var7, var2, var8, var6)
            : null;
      }

      public Field1(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
         this.cropTypeA = this.func_151559_a(var3);
         this.cropTypeB = this.func_151559_a(var3);
         this.cropTypeC = this.func_151559_a(var3);
         this.cropTypeD = this.func_151559_a(var3);
      }
   }

   public static class Field2 extends StructureVillagePieces.Village {
      public Block cropTypeB;
      public Block cropTypeA;

      public static StructureVillagePieces.Field2 func_175852_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
      ) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 7, 4, 9, var6);
         return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
            ? new StructureVillagePieces.Field2(var0, var7, var2, var8, var6)
            : null;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.cropTypeA = Block.getBlockById(var1.getInteger("CA"));
         this.cropTypeB = Block.getBlockById(var1.getInteger("CB"));
      }

      public Field2() {
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
         var1.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
      }

      public Field2(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
         this.cropTypeA = this.func_151560_a(var3);
         this.cropTypeB = this.func_151560_a(var3);
      }

      public Block func_151560_a(Random var1) {
         switch (var1.nextInt(5)) {
            case 0:
               return Blocks.carrots;
            case 1:
               return Blocks.potatoes;
            default:
               return Blocks.wheat;
         }
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 4 - 1, 0);
         }

         this.a(var1, var3, 0, 1, 0, 6, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.a(var1, var3, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 0, 0, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 6, 0, 0, 6, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 0, 5, 0, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 8, 5, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);

         for (int var4 = 1; var4 <= 7; var4++) {
            this.a(var1, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 1, 1, var4, var3);
            this.a(var1, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 2, 1, var4, var3);
            this.a(var1, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 4, 1, var4, var3);
            this.a(var1, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 5, 1, var4, var3);
         }

         for (int var6 = 0; var6 < 9; var6++) {
            for (int var5 = 0; var5 < 7; var5++) {
               this.b(var1, var5, 4, var6, var3);
               this.b(var1, Blocks.dirt.getDefaultState(), var5, -1, var6, var3);
            }
         }

         return true;
      }
   }

   public static class Hall extends StructureVillagePieces.Village {
      public Hall() {
      }

      public Hall(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
      }

      @Override
      public int func_180779_c(int var1, int var2) {
         return var1 == 0 ? 4 : super.func_180779_c(var1, var2);
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 7 - 1, 0);
         }

         this.a(var1, var3, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 2, 0, 6, 8, 0, 10, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 6, 0, 6, var3);
         this.a(var1, var3, 2, 1, 6, 2, 1, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.a(var1, var3, 8, 1, 6, 8, 1, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.a(var1, var3, 3, 1, 10, 7, 1, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 1, 7, 0, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 0, 0, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 8, 0, 0, 8, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 0, 7, 1, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 5, 7, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 0, 7, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 5, 7, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 4, 1, 8, 4, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 4, 4, 8, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 2, 8, 5, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, Blocks.planks.getDefaultState(), 0, 4, 2, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 0, 4, 3, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 8, 4, 2, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 8, 4, 3, var3);
         int var4 = this.a(Blocks.oak_stairs, 3);
         int var5 = this.a(Blocks.oak_stairs, 2);

         for (int var6 = -1; var6 <= 2; var6++) {
            for (int var7 = 0; var7 <= 8; var7++) {
               this.a(var1, Blocks.oak_stairs.getStateFromMeta(var4), var7, 4 + var6, var6, var3);
               this.a(var1, Blocks.oak_stairs.getStateFromMeta(var5), var7, 4 + var6, 5 - var6, var3);
            }
         }

         this.a(var1, Blocks.log.getDefaultState(), 0, 2, 1, var3);
         this.a(var1, Blocks.log.getDefaultState(), 0, 2, 4, var3);
         this.a(var1, Blocks.log.getDefaultState(), 8, 2, 1, var3);
         this.a(var1, Blocks.log.getDefaultState(), 8, 2, 4, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 3, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 2, 3, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 2, 5, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 3, 2, 5, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 5, 2, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 6, 2, 5, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 2, 1, 3, var3);
         this.a(var1, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 3, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 1, 1, 4, var3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(this.a(Blocks.oak_stairs, 3)), 2, 1, 4, var3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(this.a(Blocks.oak_stairs, 1)), 1, 1, 3, var3);
         this.a(var1, var3, 5, 0, 1, 7, 0, 3, Blocks.double_stone_slab.getDefaultState(), Blocks.double_stone_slab.getDefaultState(), false);
         this.a(var1, Blocks.double_stone_slab.getDefaultState(), 6, 1, 1, var3);
         this.a(var1, Blocks.double_stone_slab.getDefaultState(), 6, 1, 2, var3);
         this.a(var1, Blocks.air.getDefaultState(), 2, 1, 0, var3);
         this.a(var1, Blocks.air.getDefaultState(), 2, 2, 0, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m), 2, 3, 1, var3);
         this.placeDoorCurrentPosition(var1, var3, var2, 2, 1, 0, EnumFacing.getHorizontal(this.a(Blocks.oak_door, 1)));
         if (this.a(var1, 2, 0, -1, var3).getBlock().getMaterial() == Material.air && this.a(var1, 2, -1, -1, var3).getBlock().getMaterial() != Material.air) {
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 2, 0, -1, var3);
         }

         this.a(var1, Blocks.air.getDefaultState(), 6, 1, 5, var3);
         this.a(var1, Blocks.air.getDefaultState(), 6, 2, 5, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.getOpposite()), 6, 3, 4, var3);
         this.placeDoorCurrentPosition(var1, var3, var2, 6, 1, 5, EnumFacing.getHorizontal(this.a(Blocks.oak_door, 1)));

         for (int var8 = 0; var8 < 5; var8++) {
            for (int var9 = 0; var9 < 9; var9++) {
               this.b(var1, var9, 7, var8, var3);
               this.b(var1, Blocks.cobblestone.getDefaultState(), var9, -1, var8, var3);
            }
         }

         this.a(var1, var3, 4, 1, 2, 2);
         return true;
      }

      public static StructureVillagePieces.Hall func_175857_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
      ) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 9, 7, 11, var6);
         return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
            ? new StructureVillagePieces.Hall(var0, var7, var2, var8, var6)
            : null;
      }
   }

   public static class House1 extends StructureVillagePieces.Village {
      public House1(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 9 - 1, 0);
         }

         this.a(var1, var3, 1, 1, 1, 7, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 0, 8, 0, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 0, 8, 5, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 6, 1, 8, 6, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 7, 2, 8, 7, 3, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         int var4 = this.a(Blocks.oak_stairs, 3);
         int var5 = this.a(Blocks.oak_stairs, 2);

         for (int var6 = -1; var6 <= 2; var6++) {
            for (int var7 = 0; var7 <= 8; var7++) {
               this.a(var1, Blocks.oak_stairs.getStateFromMeta(var4), var7, 6 + var6, var6, var3);
               this.a(var1, Blocks.oak_stairs.getStateFromMeta(var5), var7, 6 + var6, 5 - var6, var3);
            }
         }

         this.a(var1, var3, 0, 1, 0, 0, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 1, 5, 8, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 8, 1, 0, 8, 1, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 2, 1, 0, 7, 1, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 0, 4, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 5, 0, 4, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 8, 2, 5, 8, 4, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 8, 2, 0, 8, 4, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 1, 0, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 5, 7, 4, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 8, 2, 1, 8, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 0, 7, 4, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 2, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 5, 2, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 6, 2, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 3, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 5, 3, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 6, 3, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 3, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 3, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 3, 3, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 2, 3, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 3, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 3, 3, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 2, 5, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 3, 2, 5, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 5, 2, 5, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 6, 2, 5, var3);
         this.a(var1, var3, 1, 4, 1, 7, 4, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 4, 4, 7, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 3, 4, 7, 3, 4, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
         this.a(var1, Blocks.planks.getDefaultState(), 7, 1, 4, var3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(this.a(Blocks.oak_stairs, 0)), 7, 1, 3, var3);
         int var9 = this.a(Blocks.oak_stairs, 3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(var9), 6, 1, 4, var3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(var9), 5, 1, 4, var3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(var9), 4, 1, 4, var3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(var9), 3, 1, 4, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 6, 1, 3, var3);
         this.a(var1, Blocks.wooden_pressure_plate.getDefaultState(), 6, 2, 3, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 1, 3, var3);
         this.a(var1, Blocks.wooden_pressure_plate.getDefaultState(), 4, 2, 3, var3);
         this.a(var1, Blocks.crafting_table.getDefaultState(), 7, 1, 1, var3);
         this.a(var1, Blocks.air.getDefaultState(), 1, 1, 0, var3);
         this.a(var1, Blocks.air.getDefaultState(), 1, 2, 0, var3);
         this.placeDoorCurrentPosition(var1, var3, var2, 1, 1, 0, EnumFacing.getHorizontal(this.a(Blocks.oak_door, 1)));
         if (this.a(var1, 1, 0, -1, var3).getBlock().getMaterial() == Material.air && this.a(var1, 1, -1, -1, var3).getBlock().getMaterial() != Material.air) {
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 1, 0, -1, var3);
         }

         for (int var10 = 0; var10 < 6; var10++) {
            for (int var8 = 0; var8 < 9; var8++) {
               this.b(var1, var8, 9, var10, var3);
               this.b(var1, Blocks.cobblestone.getDefaultState(), var8, -1, var10, var3);
            }
         }

         this.a(var1, var3, 2, 1, 2, 1);
         return true;
      }

      public House1() {
      }

      public static StructureVillagePieces.House1 func_175850_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
      ) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 9, 9, 6, var6);
         return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
            ? new StructureVillagePieces.House1(var0, var7, var2, var8, var6)
            : null;
      }

      @Override
      public int func_180779_c(int var1, int var2) {
         return 1;
      }
   }

   public static class House2 extends StructureVillagePieces.Village {
      public static List<WeightedRandomChestContent> villageBlacksmithChestContents = Lists.newArrayList(
         new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
         new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
         new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15),
         new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15),
         new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 3, 7, 5),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.sapling), 0, 3, 7, 5),
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)
      );
      public boolean hasMadeChest;

      public House2() {
      }

      public static StructureVillagePieces.House2 func_175855_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
      ) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 10, 6, 7, var6);
         return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
            ? new StructureVillagePieces.House2(var0, var7, var2, var8, var6)
            : null;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.hasMadeChest = var1.getBoolean("Chest");
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 6 - 1, 0);
         }

         this.a(var1, var3, 0, 1, 0, 9, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 0, 9, 0, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 4, 0, 9, 4, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 0, 9, 5, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
         this.a(var1, var3, 1, 5, 1, 8, 5, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 1, 1, 0, 2, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 1, 0, 0, 4, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 3, 1, 0, 3, 4, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 0, 1, 6, 0, 4, 6, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, Blocks.planks.getDefaultState(), 3, 3, 1, var3);
         this.a(var1, var3, 3, 1, 2, 3, 3, 2, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 4, 1, 3, 5, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 1, 1, 0, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 1, 6, 5, 3, 6, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 5, 1, 0, 5, 3, 0, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.a(var1, var3, 9, 1, 0, 9, 3, 0, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
         this.a(var1, var3, 6, 1, 4, 9, 4, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, Blocks.flowing_lava.getDefaultState(), 7, 1, 5, var3);
         this.a(var1, Blocks.flowing_lava.getDefaultState(), 8, 1, 5, var3);
         this.a(var1, Blocks.iron_bars.getDefaultState(), 9, 2, 5, var3);
         this.a(var1, Blocks.iron_bars.getDefaultState(), 9, 2, 4, var3);
         this.a(var1, var3, 7, 2, 4, 8, 2, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 6, 1, 3, var3);
         this.a(var1, Blocks.furnace.getDefaultState(), 6, 2, 3, var3);
         this.a(var1, Blocks.furnace.getDefaultState(), 6, 3, 3, var3);
         this.a(var1, Blocks.double_stone_slab.getDefaultState(), 8, 1, 1, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 4, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 2, 6, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 2, 6, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 2, 1, 4, var3);
         this.a(var1, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 4, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 1, 1, 5, var3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(this.a(Blocks.oak_stairs, 3)), 2, 1, 5, var3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(this.a(Blocks.oak_stairs, 1)), 1, 1, 4, var3);
         if (!this.hasMadeChest && var3.isVecInside(new BlockPos(this.a(5, 5), this.d(1), this.b(5, 5)))) {
            this.hasMadeChest = true;
            this.generateChestContents(var1, var3, var2, 5, 1, 5, villageBlacksmithChestContents, 3 + var2.nextInt(6));
         }

         for (int var4 = 6; var4 <= 8; var4++) {
            if (this.a(var1, var4, 0, -1, var3).getBlock().getMaterial() == Material.air
               && this.a(var1, var4, -1, -1, var3).getBlock().getMaterial() != Material.air) {
               this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), var4, 0, -1, var3);
            }
         }

         for (int var6 = 0; var6 < 7; var6++) {
            for (int var5 = 0; var5 < 10; var5++) {
               this.b(var1, var5, 6, var6, var3);
               this.b(var1, Blocks.cobblestone.getDefaultState(), var5, -1, var6, var3);
            }
         }

         this.a(var1, var3, 7, 1, 1, 1);
         return true;
      }

      public House2(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
      }

      @Override
      public int func_180779_c(int var1, int var2) {
         return 3;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setBoolean("Chest", this.hasMadeChest);
      }
   }

   public static class House3 extends StructureVillagePieces.Village {
      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 7 - 1, 0);
         }

         this.a(var1, var3, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 2, 0, 5, 8, 0, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 1, 7, 0, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 0, 0, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 8, 0, 0, 8, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 0, 7, 2, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 5, 2, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 2, 0, 6, 2, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 3, 0, 10, 7, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 0, 7, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 5, 2, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 4, 1, 8, 4, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 4, 4, 3, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 2, 8, 5, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, Blocks.planks.getDefaultState(), 0, 4, 2, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 0, 4, 3, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 8, 4, 2, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 8, 4, 3, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 8, 4, 4, var3);
         int var4 = this.a(Blocks.oak_stairs, 3);
         int var5 = this.a(Blocks.oak_stairs, 2);

         for (int var6 = -1; var6 <= 2; var6++) {
            for (int var7 = 0; var7 <= 8; var7++) {
               this.a(var1, Blocks.oak_stairs.getStateFromMeta(var4), var7, 4 + var6, var6, var3);
               if ((var6 > -1 || var7 <= 1) && (var6 > 0 || var7 <= 3) && (var6 > 1 || var7 <= 4 || var7 >= 6)) {
                  this.a(var1, Blocks.oak_stairs.getStateFromMeta(var5), var7, 4 + var6, 5 - var6, var3);
               }
            }
         }

         this.a(var1, var3, 3, 4, 5, 3, 4, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 7, 4, 2, 7, 4, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 4, 5, 4, 4, 5, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 6, 5, 4, 6, 5, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 5, 6, 3, 5, 6, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         int var10 = this.a(Blocks.oak_stairs, 0);

         for (int var11 = 4; var11 >= 1; var11--) {
            this.a(var1, Blocks.planks.getDefaultState(), var11, 2 + var11, 7 - var11, var3);

            for (int var8 = 8 - var11; var8 <= 10; var8++) {
               this.a(var1, Blocks.oak_stairs.getStateFromMeta(var10), var11, 2 + var11, var8, var3);
            }
         }

         int var12 = this.a(Blocks.oak_stairs, 1);
         this.a(var1, Blocks.planks.getDefaultState(), 6, 6, 3, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 7, 5, 4, var3);
         this.a(var1, Blocks.oak_stairs.getStateFromMeta(var12), 6, 6, 4, var3);

         for (int var13 = 6; var13 <= 8; var13++) {
            for (int var9 = 5; var9 <= 10; var9++) {
               this.a(var1, Blocks.oak_stairs.getStateFromMeta(var12), var13, 12 - var13, var9, var3);
            }
         }

         this.a(var1, Blocks.log.getDefaultState(), 0, 2, 1, var3);
         this.a(var1, Blocks.log.getDefaultState(), 0, 2, 4, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 3, var3);
         this.a(var1, Blocks.log.getDefaultState(), 4, 2, 0, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 5, 2, 0, var3);
         this.a(var1, Blocks.log.getDefaultState(), 6, 2, 0, var3);
         this.a(var1, Blocks.log.getDefaultState(), 8, 2, 1, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 2, 3, var3);
         this.a(var1, Blocks.log.getDefaultState(), 8, 2, 4, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 8, 2, 5, var3);
         this.a(var1, Blocks.log.getDefaultState(), 8, 2, 6, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 2, 7, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 8, 2, 8, var3);
         this.a(var1, Blocks.log.getDefaultState(), 8, 2, 9, var3);
         this.a(var1, Blocks.log.getDefaultState(), 2, 2, 6, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 2, 7, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 2, 8, var3);
         this.a(var1, Blocks.log.getDefaultState(), 2, 2, 9, var3);
         this.a(var1, Blocks.log.getDefaultState(), 4, 4, 10, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 5, 4, 10, var3);
         this.a(var1, Blocks.log.getDefaultState(), 6, 4, 10, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 5, 5, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), 2, 1, 0, var3);
         this.a(var1, Blocks.air.getDefaultState(), 2, 2, 0, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m), 2, 3, 1, var3);
         this.placeDoorCurrentPosition(var1, var3, var2, 2, 1, 0, EnumFacing.getHorizontal(this.a(Blocks.oak_door, 1)));
         this.a(var1, var3, 1, 0, -1, 3, 2, -1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         if (this.a(var1, 2, 0, -1, var3).getBlock().getMaterial() == Material.air && this.a(var1, 2, -1, -1, var3).getBlock().getMaterial() != Material.air) {
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 2, 0, -1, var3);
         }

         for (int var14 = 0; var14 < 5; var14++) {
            for (int var16 = 0; var16 < 9; var16++) {
               this.b(var1, var16, 7, var14, var3);
               this.b(var1, Blocks.cobblestone.getDefaultState(), var16, -1, var14, var3);
            }
         }

         for (int var15 = 5; var15 < 11; var15++) {
            for (int var17 = 2; var17 < 9; var17++) {
               this.b(var1, var17, 7, var15, var3);
               this.b(var1, Blocks.cobblestone.getDefaultState(), var17, -1, var15, var3);
            }
         }

         this.a(var1, var3, 4, 1, 2, 2);
         return true;
      }

      public static StructureVillagePieces.House3 func_175849_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
      ) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 9, 7, 12, var6);
         return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
            ? new StructureVillagePieces.House3(var0, var7, var2, var8, var6)
            : null;
      }

      public House3(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
      }

      public House3() {
      }
   }

   public static class House4Garden extends StructureVillagePieces.Village {
      public boolean isRoofAccessible;

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setBoolean("Terrace", this.isRoofAccessible);
      }

      public static StructureVillagePieces.House4Garden func_175858_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
      ) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 5, 6, 5, var6);
         return StructureComponent.findIntersecting(var1, var8) != null ? null : new StructureVillagePieces.House4Garden(var0, var7, var2, var8, var6);
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.isRoofAccessible = var1.getBoolean("Terrace");
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 6 - 1, 0);
         }

         this.a(var1, var3, 0, 0, 0, 4, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 0, 4, 0, 4, 4, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 1, 4, 1, 3, 4, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 1, 0, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 2, 0, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 3, 0, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 1, 0, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 2, 0, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 3, 0, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 1, 4, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 2, 4, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 3, 4, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 1, 4, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 2, 4, var3);
         this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 3, 4, var3);
         this.a(var1, var3, 0, 1, 1, 0, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 4, 1, 1, 4, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 1, 4, 3, 3, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 2, 4, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 2, 2, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 1, 1, 0, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 1, 2, 0, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 1, 3, 0, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 2, 3, 0, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 3, 3, 0, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 3, 2, 0, var3);
         this.a(var1, Blocks.planks.getDefaultState(), 3, 1, 0, var3);
         if (this.a(var1, 2, 0, -1, var3).getBlock().getMaterial() == Material.air && this.a(var1, 2, -1, -1, var3).getBlock().getMaterial() != Material.air) {
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 2, 0, -1, var3);
         }

         this.a(var1, var3, 1, 1, 1, 3, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         if (this.isRoofAccessible) {
            this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 0, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 5, 0, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 2, 5, 0, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 3, 5, 0, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 0, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 4, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 5, 4, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 2, 5, 4, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 3, 5, 4, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 4, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 1, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 2, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 3, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 1, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 2, var3);
            this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 3, var3);
         }

         if (this.isRoofAccessible) {
            int var4 = this.a(Blocks.ladder, 3);
            this.a(var1, Blocks.ladder.getStateFromMeta(var4), 3, 1, 3, var3);
            this.a(var1, Blocks.ladder.getStateFromMeta(var4), 3, 2, 3, var3);
            this.a(var1, Blocks.ladder.getStateFromMeta(var4), 3, 3, 3, var3);
            this.a(var1, Blocks.ladder.getStateFromMeta(var4), 3, 4, 3, var3);
         }

         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m), 2, 3, 1, var3);

         for (int var6 = 0; var6 < 5; var6++) {
            for (int var5 = 0; var5 < 5; var5++) {
               this.b(var1, var5, 6, var6, var3);
               this.b(var1, Blocks.cobblestone.getDefaultState(), var5, -1, var6, var3);
            }
         }

         this.a(var1, var3, 1, 1, 2, 1);
         return true;
      }

      public House4Garden() {
      }

      public House4Garden(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
         this.isRoofAccessible = var3.nextBoolean();
      }
   }

   public static class Path extends StructureVillagePieces.Road {
      public int length;

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.length = var1.getInteger("Length");
      }

      public static StructureBoundingBox func_175848_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6
      ) {
         for (int var7 = 7 * MathHelper.getRandomIntegerInRange(var2, 3, 5); var7 >= 7; var7 -= 7) {
            StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 3, 3, var7, var6);
            if (StructureComponent.findIntersecting(var1, var8) == null) {
               return var8;
            }
         }

         return null;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setInteger("Length", this.length);
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         IBlockState var4 = this.func_175847_a(Blocks.gravel.getDefaultState());
         IBlockState var5 = this.func_175847_a(Blocks.cobblestone.getDefaultState());

         for (int var6 = this.l.minX; var6 <= this.l.maxX; var6++) {
            for (int var7 = this.l.minZ; var7 <= this.l.maxZ; var7++) {
               BlockPos var8 = new BlockPos(var6, 64, var7);
               if (var3.isVecInside(var8)) {
                  var8 = var1.getTopSolidOrLiquidBlock(var8).down();
                  var1.a(var8, var4, 2);
                  var1.a(var8.down(), var5, 2);
               }
            }
         }

         return true;
      }

      public Path(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
         this.length = Math.max(var4.getXSize(), var4.getZSize());
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         boolean var4 = false;

         for (int var5 = var3.nextInt(5); var5 < this.length - 8; var5 += 2 + var3.nextInt(5)) {
            StructureComponent var6 = this.getNextComponentNN((StructureVillagePieces.Start)var1, var2, var3, 0, var5);
            if (var6 != null) {
               var5 += Math.max(var6.l.getXSize(), var6.l.getZSize());
               var4 = true;
            }
         }

         for (int var7 = var3.nextInt(5); var7 < this.length - 8; var7 += 2 + var3.nextInt(5)) {
            StructureComponent var8 = this.getNextComponentPP((StructureVillagePieces.Start)var1, var2, var3, 0, var7);
            if (var8 != null) {
               var7 += Math.max(var8.l.getXSize(), var8.l.getZSize());
               var4 = true;
            }
         }

         if (var4 && var3.nextInt(3) > 0 && this.m != null) {
            switch (this.m) {
               case NORTH:
                  StructureVillagePieces.method_02732(
                     (StructureVillagePieces.Start)var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ, EnumFacing.WEST, this.getComponentType()
                  );
                  break;
               case SOUTH:
                  StructureVillagePieces.method_02732(
                     (StructureVillagePieces.Start)var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.maxZ - 2, EnumFacing.WEST, this.getComponentType()
                  );
                  break;
               case WEST:
                  StructureVillagePieces.method_02732(
                     (StructureVillagePieces.Start)var1, var2, var3, this.l.minX, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
                  );
                  break;
               case EAST:
                  StructureVillagePieces.method_02732(
                     (StructureVillagePieces.Start)var1, var2, var3, this.l.maxX - 2, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
                  );
            }
         }

         if (var4 && var3.nextInt(3) > 0 && this.m != null) {
            switch (this.m) {
               case NORTH:
                  StructureVillagePieces.method_02732(
                     (StructureVillagePieces.Start)var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ, EnumFacing.EAST, this.getComponentType()
                  );
                  break;
               case SOUTH:
                  StructureVillagePieces.method_02732(
                     (StructureVillagePieces.Start)var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.maxZ - 2, EnumFacing.EAST, this.getComponentType()
                  );
                  break;
               case WEST:
                  StructureVillagePieces.method_02732(
                     (StructureVillagePieces.Start)var1, var2, var3, this.l.minX, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
                  );
                  break;
               case EAST:
                  StructureVillagePieces.method_02732(
                     (StructureVillagePieces.Start)var1, var2, var3, this.l.maxX - 2, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
                  );
            }
         }
      }

      public Path() {
      }
   }

   public static class PieceWeight {
      public Class<? extends StructureVillagePieces.Village> villagePieceClass;
      public int villagePiecesLimit;
      public int villagePieceWeight;
      public int villagePiecesSpawned;

      public PieceWeight(Class<? extends StructureVillagePieces.Village> var1, int var2, int var3) {
         this.villagePieceClass = var1;
         this.villagePieceWeight = var2;
         this.villagePiecesLimit = var3;
      }

      public boolean canSpawnMoreVillagePieces() {
         return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
      }

      public boolean canSpawnMoreVillagePiecesOfType(int var1) {
         return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
      }
   }

   public abstract static class Road extends StructureVillagePieces.Village {
      public Road() {
      }

      public Road(StructureVillagePieces.Start var1, int var2) {
         super(var1, var2);
      }
   }

   public static class Start extends StructureVillagePieces.Well {
      public List<StructureComponent> field_74932_i = Lists.newArrayList();
      public StructureVillagePieces.PieceWeight structVillagePieceWeight;
      public int terrainType;
      public boolean inDesert;
      public List<StructureComponent> field_74930_j = Lists.newArrayList();
      public List<StructureVillagePieces.PieceWeight> structureVillageWeightedPieceList;
      public WorldChunkManager worldChunkMngr;

      public WorldChunkManager getWorldChunkManager() {
         return this.worldChunkMngr;
      }

      public Start() {
      }

      public Start(WorldChunkManager var1, int var2, Random var3, int var4, int var5, List<StructureVillagePieces.PieceWeight> var6, int var7) {
         super((StructureVillagePieces.Start)null, 0, var3, var4, var5);
         this.worldChunkMngr = var1;
         this.structureVillageWeightedPieceList = var6;
         this.terrainType = var7;
         BiomeGenBase var8 = var1.getBiomeGenerator(new BlockPos(var4, 0, var5), BiomeGenBase.field_180279_ad);
         this.inDesert = var8 == BiomeGenBase.desert || var8 == BiomeGenBase.desertHills;
         this.func_175846_a(this.inDesert);
      }
   }

   public static class Torch extends StructureVillagePieces.Village {
      public Torch(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 4 - 1, 0);
         }

         this.a(var1, var3, 0, 0, 0, 2, 3, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 0, 0, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 1, 0, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 2, 0, var3);
         this.a(var1, Blocks.wool.getStateFromMeta(EnumDyeColor.WHITE.getDyeDamage()), 1, 3, 0, var3);
         boolean var4 = this.m == EnumFacing.EAST || this.m == EnumFacing.NORTH;
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.rotateY()), var4 ? 2 : 0, 3, 0, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m), 1, 3, 1, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.rotateYCCW()), var4 ? 0 : 2, 3, 0, var3);
         this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.getOpposite()), 1, 3, -1, var3);
         return true;
      }

      public Torch() {
      }

      public static StructureBoundingBox func_175856_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 3, 4, 2, var6);
         return StructureComponent.findIntersecting(var1, var7) != null ? null : var7;
      }
   }

   public abstract static class Village extends StructureComponent {
      public int villagersSpawned;
      public boolean isDesertVillage;
      public int h = -1;

      public static boolean canVillageGoDeeper(StructureBoundingBox var0) {
         return var0 != null && var0.minY > 10;
      }

      @Override
      public void a(World var1, IBlockState var2, int var3, int var4, int var5, StructureBoundingBox var6) {
         IBlockState var7 = this.func_175847_a(var2);
         super.a(var1, var7, var3, var4, var5, var6);
      }

      public void a(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6) {
         if (this.villagersSpawned < var6) {
            for (int var7 = this.villagersSpawned; var7 < var6; var7++) {
               int var8 = this.a(var3 + var7, var5);
               int var9 = this.d(var4);
               int var10 = this.b(var3 + var7, var5);
               if (!var2.isVecInside(new BlockPos(var8, var9, var10))) {
                  break;
               }

               this.villagersSpawned++;
               EntityVillager var11 = new EntityVillager(var1);
               var11.a_(var8 + 0.5, var9, var10 + 0.5, 0.0F, 0.0F);
               var11.onInitialSpawn(var1.E(new BlockPos(var11)), (IEntityLivingData)null);
               var11.setProfession(this.func_180779_c(var7, var11.getProfession()));
               var1.spawnEntityInWorld(var11);
            }
         }
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         this.h = var1.getInteger("HPos");
         this.villagersSpawned = var1.getInteger("VCount");
         this.isDesertVillage = var1.getBoolean("Desert");
      }

      public StructureComponent getNextComponentNN(StructureVillagePieces.Start var1, List<StructureComponent> var2, Random var3, int var4, int var5) {
         if (this.m != null) {
            switch (this.m) {
               case NORTH:
                  return StructureVillagePieces.method_02736(
                     var1, var2, var3, this.l.minX - 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.WEST, this.getComponentType()
                  );
               case SOUTH:
                  return StructureVillagePieces.method_02736(
                     var1, var2, var3, this.l.minX - 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.WEST, this.getComponentType()
                  );
               case WEST:
                  return StructureVillagePieces.method_02736(
                     var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
                  );
               case EAST:
                  return StructureVillagePieces.method_02736(
                     var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
                  );
            }
         }

         return null;
      }

      public int func_180779_c(int var1, int var2) {
         return var2;
      }

      public Village(StructureVillagePieces.Start var1, int var2) {
         super(var2);
         if (var1 != null) {
            this.isDesertVillage = var1.inDesert;
         }
      }

      @Override
      public void a(
         World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, IBlockState var9, IBlockState var10, boolean var11
      ) {
         IBlockState var12 = this.func_175847_a(var9);
         IBlockState var13 = this.func_175847_a(var10);
         super.a(var1, var2, var3, var4, var5, var6, var7, var8, var12, var13, var11);
      }

      public Village() {
      }

      public void func_175846_a(boolean var1) {
         this.isDesertVillage = var1;
      }

      @Override
      public void b(World var1, IBlockState var2, int var3, int var4, int var5, StructureBoundingBox var6) {
         IBlockState var7 = this.func_175847_a(var2);
         super.b(var1, var7, var3, var4, var5, var6);
      }

      public StructureComponent getNextComponentPP(StructureVillagePieces.Start var1, List<StructureComponent> var2, Random var3, int var4, int var5) {
         if (this.m != null) {
            switch (this.m) {
               case NORTH:
                  return StructureVillagePieces.method_02736(
                     var1, var2, var3, this.l.maxX + 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.EAST, this.getComponentType()
                  );
               case SOUTH:
                  return StructureVillagePieces.method_02736(
                     var1, var2, var3, this.l.maxX + 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.EAST, this.getComponentType()
                  );
               case WEST:
                  return StructureVillagePieces.method_02736(
                     var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
                  );
               case EAST:
                  return StructureVillagePieces.method_02736(
                     var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
                  );
            }
         }

         return null;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         var1.setInteger("HPos", this.h);
         var1.setInteger("VCount", this.villagersSpawned);
         var1.setBoolean("Desert", this.isDesertVillage);
      }

      public IBlockState func_175847_a(IBlockState var1) {
         if (this.isDesertVillage) {
            if (var1.getBlock() == Blocks.log || var1.getBlock() == Blocks.log2) {
               return Blocks.sandstone.getDefaultState();
            }

            if (var1.getBlock() == Blocks.cobblestone) {
               return Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata());
            }

            if (var1.getBlock() == Blocks.planks) {
               return Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata());
            }

            if (var1.getBlock() == Blocks.oak_stairs) {
               return Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, var1.getValue(BlockStairs.FACING));
            }

            if (var1.getBlock() == Blocks.stone_stairs) {
               return Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, var1.getValue(BlockStairs.FACING));
            }

            if (var1.getBlock() == Blocks.gravel) {
               return Blocks.sandstone.getDefaultState();
            }
         }

         return var1;
      }

      public int b(World var1, StructureBoundingBox var2) {
         int var3 = 0;
         int var4 = 0;
         BlockPos.MutableBlockPos var5 = new BlockPos.MutableBlockPos();

         for (int var6 = this.l.minZ; var6 <= this.l.maxZ; var6++) {
            for (int var7 = this.l.minX; var7 <= this.l.maxX; var7++) {
               var5.set(var7, 64, var6);
               if (var2.isVecInside(var5)) {
                  var3 += Math.max(var1.getTopSolidOrLiquidBlock(var5).getY(), var1.t.getAverageGroundLevel());
                  var4++;
               }
            }
         }

         return var4 == 0 ? -1 : var3 / var4;
      }
   }

   public static class Well extends StructureVillagePieces.Village {
      public Well(StructureVillagePieces.Start var1, int var2, Random var3, int var4, int var5) {
         super(var1, var2);
         this.m = EnumFacing.Plane.HORIZONTAL.random(var3);
         switch (this.m) {
            case NORTH:
            case SOUTH:
               this.l = new StructureBoundingBox(var4, 64, var5, var4 + 6 - 1, 78, var5 + 6 - 1);
               break;
            default:
               this.l = new StructureBoundingBox(var4, 64, var5, var4 + 6 - 1, 78, var5 + 6 - 1);
         }
      }

      public Well() {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 3, 0);
         }

         this.a(var1, var3, 1, 0, 1, 4, 12, 4, Blocks.cobblestone.getDefaultState(), Blocks.flowing_water.getDefaultState(), false);
         this.a(var1, Blocks.air.getDefaultState(), 2, 12, 2, var3);
         this.a(var1, Blocks.air.getDefaultState(), 3, 12, 2, var3);
         this.a(var1, Blocks.air.getDefaultState(), 2, 12, 3, var3);
         this.a(var1, Blocks.air.getDefaultState(), 3, 12, 3, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 13, 1, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 14, 1, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 13, 1, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 14, 1, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 13, 4, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 14, 4, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 13, 4, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 14, 4, var3);
         this.a(var1, var3, 1, 15, 1, 4, 15, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);

         for (int var4 = 0; var4 <= 5; var4++) {
            for (int var5 = 0; var5 <= 5; var5++) {
               if (var5 == 0 || var5 == 5 || var4 == 0 || var4 == 5) {
                  this.a(var1, Blocks.gravel.getDefaultState(), var5, 11, var4, var3);
                  this.b(var1, var5, 12, var4, var3);
               }
            }
         }

         return true;
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         StructureVillagePieces.method_02732(
            (StructureVillagePieces.Start)var1, var2, var3, this.l.minX - 1, this.l.maxY - 4, this.l.minZ + 1, EnumFacing.WEST, this.getComponentType()
         );
         StructureVillagePieces.method_02732(
            (StructureVillagePieces.Start)var1, var2, var3, this.l.maxX + 1, this.l.maxY - 4, this.l.minZ + 1, EnumFacing.EAST, this.getComponentType()
         );
         StructureVillagePieces.method_02732(
            (StructureVillagePieces.Start)var1, var2, var3, this.l.minX + 1, this.l.maxY - 4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
         );
         StructureVillagePieces.method_02732(
            (StructureVillagePieces.Start)var1, var2, var3, this.l.minX + 1, this.l.maxY - 4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
         );
      }
   }

   public static class WoodHut extends StructureVillagePieces.Village {
      public int tablePosition;
      public boolean isTallHouse;

      public WoodHut() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.tablePosition = var1.getInteger("T");
         this.isTallHouse = var1.getBoolean("C");
      }

      public static StructureVillagePieces.WoodHut func_175853_a(
         StructureVillagePieces.Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
      ) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 4, 6, 5, var6);
         return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
            ? new StructureVillagePieces.WoodHut(var0, var7, var2, var8, var6)
            : null;
      }

      public WoodHut(StructureVillagePieces.Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
         super(var1, var2);
         this.m = var5;
         this.l = var4;
         this.isTallHouse = var3.nextBoolean();
         this.tablePosition = var3.nextInt(3);
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setInteger("T", this.tablePosition);
         var1.setBoolean("C", this.isTallHouse);
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.h < 0) {
            this.h = this.b(var1, var3);
            if (this.h < 0) {
               return true;
            }

            this.l.offset(0, this.h - this.l.maxY + 6 - 1, 0);
         }

         this.a(var1, var3, 1, 1, 1, 3, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 0, 3, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 1, 2, 0, 3, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
         if (this.isTallHouse) {
            this.a(var1, var3, 1, 4, 1, 2, 4, 3, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         } else {
            this.a(var1, var3, 1, 5, 1, 2, 5, 3, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         }

         this.a(var1, Blocks.log.getDefaultState(), 1, 4, 0, var3);
         this.a(var1, Blocks.log.getDefaultState(), 2, 4, 0, var3);
         this.a(var1, Blocks.log.getDefaultState(), 1, 4, 4, var3);
         this.a(var1, Blocks.log.getDefaultState(), 2, 4, 4, var3);
         this.a(var1, Blocks.log.getDefaultState(), 0, 4, 1, var3);
         this.a(var1, Blocks.log.getDefaultState(), 0, 4, 2, var3);
         this.a(var1, Blocks.log.getDefaultState(), 0, 4, 3, var3);
         this.a(var1, Blocks.log.getDefaultState(), 3, 4, 1, var3);
         this.a(var1, Blocks.log.getDefaultState(), 3, 4, 2, var3);
         this.a(var1, Blocks.log.getDefaultState(), 3, 4, 3, var3);
         this.a(var1, var3, 0, 1, 0, 0, 3, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 3, 1, 0, 3, 3, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 0, 1, 4, 0, 3, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 3, 1, 4, 3, 3, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
         this.a(var1, var3, 0, 1, 1, 0, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 3, 1, 1, 3, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 1, 0, 2, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, var3, 1, 1, 4, 2, 3, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 2, var3);
         this.a(var1, Blocks.glass_pane.getDefaultState(), 3, 2, 2, var3);
         if (this.tablePosition > 0) {
            this.a(var1, Blocks.oak_fence.getDefaultState(), this.tablePosition, 1, 3, var3);
            this.a(var1, Blocks.wooden_pressure_plate.getDefaultState(), this.tablePosition, 2, 3, var3);
         }

         this.a(var1, Blocks.air.getDefaultState(), 1, 1, 0, var3);
         this.a(var1, Blocks.air.getDefaultState(), 1, 2, 0, var3);
         this.placeDoorCurrentPosition(var1, var3, var2, 1, 1, 0, EnumFacing.getHorizontal(this.a(Blocks.oak_door, 1)));
         if (this.a(var1, 1, 0, -1, var3).getBlock().getMaterial() == Material.air && this.a(var1, 1, -1, -1, var3).getBlock().getMaterial() != Material.air) {
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 1, 0, -1, var3);
         }

         for (int var4 = 0; var4 < 5; var4++) {
            for (int var5 = 0; var5 < 4; var5++) {
               this.b(var1, var5, 6, var4, var3);
               this.b(var1, Blocks.cobblestone.getDefaultState(), var5, -1, var4, var3);
            }
         }

         this.a(var1, var3, 1, 1, 2, 1);
         return true;
      }
   }
}
