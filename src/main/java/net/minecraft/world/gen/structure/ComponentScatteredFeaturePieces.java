package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$SwampHut;

public class ComponentScatteredFeaturePieces {
   public static void registerScatteredFeaturePieces() {
      MapGenStructureIO.registerStructureComponent(ComponentScatteredFeaturePieces.DesertPyramid.class, "TeDP");
      MapGenStructureIO.registerStructureComponent(ComponentScatteredFeaturePieces.JunglePyramid.class, "TeJP");
      MapGenStructureIO.registerStructureComponent(ComponentScatteredFeaturePieces$SwampHut.class, "TeSH");
   }

   public static class DesertPyramid extends ComponentScatteredFeaturePieces.Feature {
      public boolean[] hasPlacedChest = new boolean[4];
      public static List<WeightedRandomChestContent> itemsToGenerateInTemple = Lists.newArrayList(
         new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
         new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15),
         new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2),
         new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20),
         new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16),
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)
      );

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, -4, 0, this.a - 1, 0, this.c - 1, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);

         for (int var4 = 1; var4 <= 9; var4++) {
            this.a(
               var1,
               var3,
               var4,
               var4,
               var4,
               this.a - 1 - var4,
               var4,
               this.c - 1 - var4,
               Blocks.sandstone.getDefaultState(),
               Blocks.sandstone.getDefaultState(),
               false
            );
            this.a(
               var1,
               var3,
               var4 + 1,
               var4,
               var4 + 1,
               this.a - 2 - var4,
               var4,
               this.c - 2 - var4,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
         }

         for (int var14 = 0; var14 < this.a; var14++) {
            for (int var5 = 0; var5 < this.c; var5++) {
               byte var6 = -5;
               this.b(var1, Blocks.sandstone.getDefaultState(), var14, var6, var5, var3);
            }
         }

         int var15 = this.a(Blocks.sandstone_stairs, 3);
         int var16 = this.a(Blocks.sandstone_stairs, 2);
         int var17 = this.a(Blocks.sandstone_stairs, 0);
         int var7 = this.a(Blocks.sandstone_stairs, 1);
         int var8 = ~EnumDyeColor.ORANGE.getDyeDamage() & 15;
         int var9 = ~EnumDyeColor.BLUE.getDyeDamage() & 15;
         this.a(var1, var3, 0, 0, 0, 4, 9, 4, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 1, 10, 1, 3, 10, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var15), 2, 10, 0, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var16), 2, 10, 4, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var17), 0, 10, 2, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var7), 4, 10, 2, var3);
         this.a(var1, var3, this.a - 5, 0, 0, this.a - 1, 9, 4, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, this.a - 4, 10, 1, this.a - 2, 10, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var15), this.a - 3, 10, 0, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var16), this.a - 3, 10, 4, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var17), this.a - 5, 10, 2, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var7), this.a - 1, 10, 2, var3);
         this.a(var1, var3, 8, 0, 0, 12, 4, 4, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 9, 1, 0, 11, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 9, 1, 1, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 9, 2, 1, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 9, 3, 1, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 10, 3, 1, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 11, 3, 1, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 11, 2, 1, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 11, 1, 1, var3);
         this.a(var1, var3, 4, 1, 1, 8, 3, 3, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 4, 1, 2, 8, 2, 2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 12, 1, 1, 16, 3, 3, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 12, 1, 2, 16, 2, 2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 5, 4, 5, this.a - 6, 4, this.c - 6, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, var3, 9, 4, 9, 11, 4, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(
            var1,
            var3,
            8,
            1,
            8,
            8,
            3,
            8,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            false
         );
         this.a(
            var1,
            var3,
            12,
            1,
            8,
            12,
            3,
            8,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            false
         );
         this.a(
            var1,
            var3,
            8,
            1,
            12,
            8,
            3,
            12,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            false
         );
         this.a(
            var1,
            var3,
            12,
            1,
            12,
            12,
            3,
            12,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            false
         );
         this.a(var1, var3, 1, 1, 5, 4, 4, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, var3, this.a - 5, 1, 5, this.a - 2, 4, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, var3, 6, 7, 9, 6, 7, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, var3, this.a - 7, 7, 9, this.a - 7, 7, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(
            var1,
            var3,
            5,
            5,
            9,
            5,
            7,
            11,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            false
         );
         this.a(
            var1,
            var3,
            this.a - 6,
            5,
            9,
            this.a - 6,
            7,
            11,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            false
         );
         this.a(var1, Blocks.air.getDefaultState(), 5, 5, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), 5, 6, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), 6, 6, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), this.a - 6, 5, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), this.a - 6, 6, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), this.a - 7, 6, 10, var3);
         this.a(var1, var3, 2, 4, 4, 2, 6, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, this.a - 3, 4, 4, this.a - 3, 6, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var15), 2, 4, 5, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var15), 2, 3, 4, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var15), this.a - 3, 4, 5, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var15), this.a - 3, 3, 4, var3);
         this.a(var1, var3, 1, 1, 3, 2, 2, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, var3, this.a - 3, 1, 3, this.a - 2, 2, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, Blocks.sandstone_stairs.getDefaultState(), 1, 1, 2, var3);
         this.a(var1, Blocks.sandstone_stairs.getDefaultState(), this.a - 2, 1, 2, var3);
         this.a(var1, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SAND.getMetadata()), 1, 2, 2, var3);
         this.a(var1, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SAND.getMetadata()), this.a - 2, 2, 2, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var7), 2, 1, 2, var3);
         this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var17), this.a - 3, 1, 2, var3);
         this.a(var1, var3, 4, 3, 5, 4, 3, 18, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, var3, this.a - 5, 3, 5, this.a - 5, 3, 17, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, var3, 3, 1, 5, 4, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, this.a - 6, 1, 5, this.a - 5, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

         for (int var10 = 5; var10 <= 17; var10 += 2) {
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 4, 1, var10, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 4, 2, var10, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), this.a - 5, 1, var10, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), this.a - 5, 2, var10, var3);
         }

         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 10, 0, 7, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 10, 0, 8, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 9, 0, 9, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 11, 0, 9, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 8, 0, 10, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 12, 0, 10, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 7, 0, 10, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 13, 0, 10, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 9, 0, 11, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 11, 0, 11, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 10, 0, 12, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 10, 0, 13, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var9), 10, 0, 10, var3);

         for (int var18 = 0; var18 <= this.a - 1; var18 += this.a - 1) {
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var18, 2, 1, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 2, 2, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var18, 2, 3, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var18, 3, 1, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 3, 2, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var18, 3, 3, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 4, 1, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), var18, 4, 2, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 4, 3, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var18, 5, 1, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 5, 2, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var18, 5, 3, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 6, 1, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), var18, 6, 2, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 6, 3, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 7, 1, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 7, 2, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 7, 3, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var18, 8, 1, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var18, 8, 2, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var18, 8, 3, var3);
         }

         for (int var19 = 2; var19 <= this.a - 3; var19 += this.a - 3 - 2) {
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var19 - 1, 2, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19, 2, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var19 + 1, 2, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var19 - 1, 3, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19, 3, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var19 + 1, 3, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 - 1, 4, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), var19, 4, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 + 1, 4, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var19 - 1, 5, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19, 5, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var19 + 1, 5, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 - 1, 6, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), var19, 6, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 + 1, 6, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 - 1, 7, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19, 7, 0, var3);
            this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 + 1, 7, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var19 - 1, 8, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var19, 8, 0, var3);
            this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), var19 + 1, 8, 0, var3);
         }

         this.a(
            var1,
            var3,
            8,
            4,
            0,
            12,
            6,
            0,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            false
         );
         this.a(var1, Blocks.air.getDefaultState(), 8, 6, 0, var3);
         this.a(var1, Blocks.air.getDefaultState(), 12, 6, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 9, 5, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 10, 5, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 11, 5, 0, var3);
         this.a(
            var1,
            var3,
            8,
            -14,
            8,
            12,
            -11,
            12,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            false
         );
         this.a(
            var1,
            var3,
            8,
            -10,
            8,
            12,
            -10,
            12,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()),
            false
         );
         this.a(
            var1,
            var3,
            8,
            -9,
            8,
            12,
            -9,
            12,
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()),
            false
         );
         this.a(var1, var3, 8, -8, 8, 12, -1, 12, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
         this.a(var1, var3, 9, -11, 9, 11, -1, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, Blocks.stone_pressure_plate.getDefaultState(), 10, -11, 10, var3);
         this.a(var1, var3, 9, -13, 9, 11, -13, 11, Blocks.tnt.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, Blocks.air.getDefaultState(), 8, -11, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), 8, -10, 10, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 7, -10, 10, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 7, -11, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), 12, -11, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), 12, -10, 10, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 13, -10, 10, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 13, -11, 10, var3);
         this.a(var1, Blocks.air.getDefaultState(), 10, -11, 8, var3);
         this.a(var1, Blocks.air.getDefaultState(), 10, -10, 8, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 10, -10, 7, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 10, -11, 7, var3);
         this.a(var1, Blocks.air.getDefaultState(), 10, -11, 12, var3);
         this.a(var1, Blocks.air.getDefaultState(), 10, -10, 12, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.getMetadata()), 10, -10, 13, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()), 10, -11, 13, var3);

         for (EnumFacing var11 : EnumFacing.Plane.HORIZONTAL) {
            if (!this.hasPlacedChest[var11.getHorizontalIndex()]) {
               int var12 = var11.getFrontOffsetX() * 2;
               int var13 = var11.getFrontOffsetZ() * 2;
               this.hasPlacedChest[var11.getHorizontalIndex()] = this.generateChestContents(
                  var1,
                  var3,
                  var2,
                  10 + var12,
                  -11,
                  10 + var13,
                  WeightedRandomChestContent.func_177629_a(itemsToGenerateInTemple, Items.enchanted_book.getRandom(var2)),
                  2 + var2.nextInt(5)
               );
            }
         }

         return true;
      }

      public DesertPyramid(Random var1, int var2, int var3) {
         super(var1, var2, 64, var3, 21, 15, 21);
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setBoolean("hasPlacedChest0", this.hasPlacedChest[0]);
         var1.setBoolean("hasPlacedChest1", this.hasPlacedChest[1]);
         var1.setBoolean("hasPlacedChest2", this.hasPlacedChest[2]);
         var1.setBoolean("hasPlacedChest3", this.hasPlacedChest[3]);
      }

      public DesertPyramid() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.hasPlacedChest[0] = var1.getBoolean("hasPlacedChest0");
         this.hasPlacedChest[1] = var1.getBoolean("hasPlacedChest1");
         this.hasPlacedChest[2] = var1.getBoolean("hasPlacedChest2");
         this.hasPlacedChest[3] = var1.getBoolean("hasPlacedChest3");
      }
   }

   public abstract static class Feature extends StructureComponent {
      public int c;
      public int scatteredFeatureSizeY;
      public int field_74936_d = -1;
      public int a;

      public boolean a(World var1, StructureBoundingBox var2, int var3) {
         if (this.field_74936_d >= 0) {
            return true;
         } else {
            int var4 = 0;
            int var5 = 0;
            BlockPos.MutableBlockPos var6 = new BlockPos.MutableBlockPos();

            for (int var7 = this.l.minZ; var7 <= this.l.maxZ; var7++) {
               for (int var8 = this.l.minX; var8 <= this.l.maxX; var8++) {
                  var6.set(var8, 64, var7);
                  if (var2.isVecInside(var6)) {
                     var4 += Math.max(var1.getTopSolidOrLiquidBlock(var6).getY(), var1.t.getAverageGroundLevel());
                     var5++;
                  }
               }
            }

            if (var5 == 0) {
               return false;
            } else {
               this.field_74936_d = var4 / var5;
               this.l.offset(0, this.field_74936_d - this.l.minY + var3, 0);
               return true;
            }
         }
      }

      public Feature() {
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         var1.setInteger("Width", this.a);
         var1.setInteger("Height", this.scatteredFeatureSizeY);
         var1.setInteger("Depth", this.c);
         var1.setInteger("HPos", this.field_74936_d);
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         this.a = var1.getInteger("Width");
         this.scatteredFeatureSizeY = var1.getInteger("Height");
         this.c = var1.getInteger("Depth");
         this.field_74936_d = var1.getInteger("HPos");
      }

      public Feature(Random var1, int var2, int var3, int var4, int var5, int var6, int var7) {
         super(0);
         this.a = var5;
         this.scatteredFeatureSizeY = var6;
         this.c = var7;
         this.m = EnumFacing.Plane.HORIZONTAL.random(var1);
         switch (this.m) {
            case NORTH:
            case SOUTH:
               this.l = new StructureBoundingBox(var2, var3, var4, var2 + var5 - 1, var3 + var6 - 1, var4 + var7 - 1);
               break;
            default:
               this.l = new StructureBoundingBox(var2, var3, var4, var2 + var7 - 1, var3 + var6 - 1, var4 + var5 - 1);
         }
      }
   }

   public static class JunglePyramid extends ComponentScatteredFeaturePieces.Feature {
      public boolean placedTrap1;
      public boolean placedMainChest;
      public static List<WeightedRandomChestContent> field_175816_i = Lists.newArrayList(
         new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
         new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15),
         new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2),
         new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20),
         new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16),
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)
      );
      public static List<WeightedRandomChestContent> field_175815_j = Lists.newArrayList(new WeightedRandomChestContent(Items.arrow, 0, 2, 7, 30));
      public boolean placedTrap2;
      public static ComponentScatteredFeaturePieces.JunglePyramid.Stones junglePyramidsRandomScatteredStones = new ComponentScatteredFeaturePieces.JunglePyramid.Stones(
         
      );
      public boolean placedHiddenChest;

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.placedMainChest = var1.getBoolean("placedMainChest");
         this.placedHiddenChest = var1.getBoolean("placedHiddenChest");
         this.placedTrap1 = var1.getBoolean("placedTrap1");
         this.placedTrap2 = var1.getBoolean("placedTrap2");
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (!this.a(var1, var3, 0)) {
            return false;
         } else {
            int var4 = this.a(Blocks.stone_stairs, 3);
            int var5 = this.a(Blocks.stone_stairs, 2);
            int var6 = this.a(Blocks.stone_stairs, 0);
            int var7 = this.a(Blocks.stone_stairs, 1);
            this.a(var1, var3, 0, -4, 0, this.a - 1, 0, this.c - 1, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 2, 1, 2, 9, 2, 2, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 2, 1, 12, 9, 2, 12, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 2, 1, 3, 2, 2, 11, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 9, 1, 3, 9, 2, 11, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 1, 3, 1, 10, 6, 1, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 1, 3, 13, 10, 6, 13, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 1, 3, 2, 1, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 10, 3, 2, 10, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 2, 3, 2, 9, 3, 12, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 2, 6, 2, 9, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 3, 7, 3, 8, 7, 11, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 4, 8, 4, 7, 8, 10, false, var2, junglePyramidsRandomScatteredStones);
            this.fillWithAir(var1, var3, 3, 1, 3, 8, 2, 11);
            this.fillWithAir(var1, var3, 4, 3, 6, 7, 3, 9);
            this.fillWithAir(var1, var3, 2, 4, 2, 9, 5, 12);
            this.fillWithAir(var1, var3, 4, 6, 5, 7, 6, 9);
            this.fillWithAir(var1, var3, 5, 7, 6, 6, 7, 8);
            this.fillWithAir(var1, var3, 5, 1, 2, 6, 2, 2);
            this.fillWithAir(var1, var3, 5, 2, 12, 6, 2, 12);
            this.fillWithAir(var1, var3, 5, 5, 1, 6, 5, 1);
            this.fillWithAir(var1, var3, 5, 5, 13, 6, 5, 13);
            this.a(var1, Blocks.air.getDefaultState(), 1, 5, 5, var3);
            this.a(var1, Blocks.air.getDefaultState(), 10, 5, 5, var3);
            this.a(var1, Blocks.air.getDefaultState(), 1, 5, 9, var3);
            this.a(var1, Blocks.air.getDefaultState(), 10, 5, 9, var3);

            for (int var8 = 0; var8 <= 14; var8 += 14) {
               this.a(var1, var3, 2, 4, var8, 2, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
               this.a(var1, var3, 4, 4, var8, 4, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
               this.a(var1, var3, 7, 4, var8, 7, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
               this.a(var1, var3, 9, 4, var8, 9, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
            }

            this.a(var1, var3, 5, 6, 0, 6, 6, 0, false, var2, junglePyramidsRandomScatteredStones);

            for (int var10 = 0; var10 <= 11; var10 += 11) {
               for (int var9 = 2; var9 <= 12; var9 += 2) {
                  this.a(var1, var3, var10, 4, var9, var10, 5, var9, false, var2, junglePyramidsRandomScatteredStones);
               }

               this.a(var1, var3, var10, 6, 5, var10, 6, 5, false, var2, junglePyramidsRandomScatteredStones);
               this.a(var1, var3, var10, 6, 9, var10, 6, 9, false, var2, junglePyramidsRandomScatteredStones);
            }

            this.a(var1, var3, 2, 7, 2, 2, 9, 2, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 9, 7, 2, 9, 9, 2, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 2, 7, 12, 2, 9, 12, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 9, 7, 12, 9, 9, 12, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 4, 9, 4, 4, 9, 4, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 7, 9, 4, 7, 9, 4, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 4, 9, 10, 4, 9, 10, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 7, 9, 10, 7, 9, 10, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 5, 9, 7, 6, 9, 7, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 5, 9, 6, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 6, 9, 6, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var5), 5, 9, 8, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var5), 6, 9, 8, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 4, 0, 0, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 5, 0, 0, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 6, 0, 0, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 7, 0, 0, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 4, 1, 8, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 4, 2, 9, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 4, 3, 10, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 7, 1, 8, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 7, 2, 9, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 7, 3, 10, var3);
            this.a(var1, var3, 4, 1, 9, 4, 1, 9, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 7, 1, 9, 7, 1, 9, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 4, 1, 10, 7, 2, 10, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 5, 4, 5, 6, 4, 5, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var6), 4, 4, 5, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var7), 7, 4, 5, var3);

            for (int var11 = 0; var11 < 4; var11++) {
               this.a(var1, Blocks.stone_stairs.getStateFromMeta(var5), 5, 0 - var11, 6 + var11, var3);
               this.a(var1, Blocks.stone_stairs.getStateFromMeta(var5), 6, 0 - var11, 6 + var11, var3);
               this.fillWithAir(var1, var3, 5, 0 - var11, 7 + var11, 6, 0 - var11, 9 + var11);
            }

            this.fillWithAir(var1, var3, 1, -3, 12, 10, -1, 13);
            this.fillWithAir(var1, var3, 1, -3, 1, 3, -1, 13);
            this.fillWithAir(var1, var3, 1, -3, 1, 9, -1, 5);

            for (int var12 = 1; var12 <= 13; var12 += 2) {
               this.a(var1, var3, 1, -3, var12, 1, -2, var12, false, var2, junglePyramidsRandomScatteredStones);
            }

            for (int var13 = 2; var13 <= 12; var13 += 2) {
               this.a(var1, var3, 1, -1, var13, 3, -1, var13, false, var2, junglePyramidsRandomScatteredStones);
            }

            this.a(var1, var3, 2, -2, 1, 5, -2, 1, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 7, -2, 1, 9, -2, 1, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 6, -3, 1, 6, -3, 1, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 6, -1, 1, 6, -1, 1, false, var2, junglePyramidsRandomScatteredStones);
            this.a(
               var1,
               Blocks.tripwire_hook
                  .getStateFromMeta(this.a(Blocks.tripwire_hook, EnumFacing.EAST.getHorizontalIndex()))
                  .withProperty(BlockTripWireHook.ATTACHED, true),
               1,
               -3,
               8,
               var3
            );
            this.a(
               var1,
               Blocks.tripwire_hook
                  .getStateFromMeta(this.a(Blocks.tripwire_hook, EnumFacing.WEST.getHorizontalIndex()))
                  .withProperty(BlockTripWireHook.ATTACHED, true),
               4,
               -3,
               8,
               var3
            );
            this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 2, -3, 8, var3);
            this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 3, -3, 8, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 7, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 6, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 5, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 4, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 3, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 2, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 5, -3, 1, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 4, -3, 1, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 3, -3, 1, var3);
            if (!this.placedTrap1) {
               this.placedTrap1 = this.generateDispenserContents(var1, var3, var2, 3, -2, 1, EnumFacing.NORTH.getIndex(), field_175815_j, 2);
            }

            this.a(var1, Blocks.vine.getStateFromMeta(15), 3, -2, 2, var3);
            this.a(
               var1,
               Blocks.tripwire_hook
                  .getStateFromMeta(this.a(Blocks.tripwire_hook, EnumFacing.NORTH.getHorizontalIndex()))
                  .withProperty(BlockTripWireHook.ATTACHED, true),
               7,
               -3,
               1,
               var3
            );
            this.a(
               var1,
               Blocks.tripwire_hook
                  .getStateFromMeta(this.a(Blocks.tripwire_hook, EnumFacing.SOUTH.getHorizontalIndex()))
                  .withProperty(BlockTripWireHook.ATTACHED, true),
               7,
               -3,
               5,
               var3
            );
            this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 7, -3, 2, var3);
            this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 7, -3, 3, var3);
            this.a(var1, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, true), 7, -3, 4, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 8, -3, 6, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 9, -3, 6, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 9, -3, 5, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 9, -3, 4, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 9, -2, 4, var3);
            if (!this.placedTrap2) {
               this.placedTrap2 = this.generateDispenserContents(var1, var3, var2, 9, -2, 3, EnumFacing.WEST.getIndex(), field_175815_j, 2);
            }

            this.a(var1, Blocks.vine.getStateFromMeta(15), 8, -1, 3, var3);
            this.a(var1, Blocks.vine.getStateFromMeta(15), 8, -2, 3, var3);
            if (!this.placedMainChest) {
               this.placedMainChest = this.generateChestContents(
                  var1,
                  var3,
                  var2,
                  8,
                  -3,
                  3,
                  WeightedRandomChestContent.func_177629_a(field_175816_i, Items.enchanted_book.getRandom(var2)),
                  2 + var2.nextInt(5)
               );
            }

            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 9, -3, 2, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 8, -3, 1, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 4, -3, 5, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 5, -2, 5, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 5, -1, 5, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 6, -3, 5, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 7, -2, 5, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 7, -1, 5, var3);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 8, -3, 5, var3);
            this.a(var1, var3, 9, -1, 1, 9, -1, 5, false, var2, junglePyramidsRandomScatteredStones);
            this.fillWithAir(var1, var3, 8, -3, 8, 10, -1, 10);
            this.a(var1, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 8, -2, 11, var3);
            this.a(var1, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 9, -2, 11, var3);
            this.a(var1, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 10, -2, 11, var3);
            this.a(
               var1,
               Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(this.a(Blocks.lever, EnumFacing.NORTH.getIndex())))),
               8,
               -2,
               12,
               var3
            );
            this.a(
               var1,
               Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(this.a(Blocks.lever, EnumFacing.NORTH.getIndex())))),
               9,
               -2,
               12,
               var3
            );
            this.a(
               var1,
               Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(this.a(Blocks.lever, EnumFacing.NORTH.getIndex())))),
               10,
               -2,
               12,
               var3
            );
            this.a(var1, var3, 8, -3, 8, 8, -3, 10, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, var3, 10, -3, 8, 10, -3, 10, false, var2, junglePyramidsRandomScatteredStones);
            this.a(var1, Blocks.mossy_cobblestone.getDefaultState(), 10, -2, 9, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 8, -2, 9, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 8, -2, 10, var3);
            this.a(var1, Blocks.redstone_wire.getDefaultState(), 10, -1, 9, var3);
            this.a(var1, Blocks.sticky_piston.getStateFromMeta(EnumFacing.UP.getIndex()), 9, -2, 8, var3);
            this.a(var1, Blocks.sticky_piston.getStateFromMeta(this.a(Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -2, 8, var3);
            this.a(var1, Blocks.sticky_piston.getStateFromMeta(this.a(Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -1, 8, var3);
            this.a(var1, Blocks.unpowered_repeater.getStateFromMeta(this.a(Blocks.unpowered_repeater, EnumFacing.NORTH.getHorizontalIndex())), 10, -2, 10, var3);
            if (!this.placedHiddenChest) {
               this.placedHiddenChest = this.generateChestContents(
                  var1,
                  var3,
                  var2,
                  9,
                  -3,
                  10,
                  WeightedRandomChestContent.func_177629_a(field_175816_i, Items.enchanted_book.getRandom(var2)),
                  2 + var2.nextInt(5)
               );
            }

            return true;
         }
      }

      public JunglePyramid() {
      }

      public JunglePyramid(Random var1, int var2, int var3) {
         super(var1, var2, 64, var3, 12, 10, 15);
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setBoolean("placedMainChest", this.placedMainChest);
         var1.setBoolean("placedHiddenChest", this.placedHiddenChest);
         var1.setBoolean("placedTrap1", this.placedTrap1);
         var1.setBoolean("placedTrap2", this.placedTrap2);
      }

      public static class Stones extends StructureComponent.BlockSelector {
         @Override
         public void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5) {
            if (var1.nextFloat() < 0.4F) {
               this.a = Blocks.cobblestone.getDefaultState();
            } else {
               this.a = Blocks.mossy_cobblestone.getDefaultState();
            }
         }

         public Stones() {
         }
      }
   }
}
