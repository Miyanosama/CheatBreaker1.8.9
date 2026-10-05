package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import io.netty.bootstrap.ServerBootstrap$ServerBootstrapAcceptor$1;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockSandStone$EnumType;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStoneSlab$EnumType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass3516;

public class ComponentScatteredFeaturePieces$DesertPyramid extends ComponentScatteredFeaturePieces$Feature {
   public boolean[] hasPlacedChest = new boolean[4];
   public static List<WeightedRandomChestContent> itemsToGenerateInTemple = Lists.newArrayList(
      new WeightedRandomChestContent[]{
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
      }
   );
   public UnidentifiedClass3516 field_0001;
   public ServerBootstrap$ServerBootstrapAcceptor$1 field_0003;
   public BlockSnow field_0000;

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
            var1, var3, var4 + 1, var4, var4 + 1, this.a - 2 - var4, var4, this.c - 2 - var4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false
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
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 9, 1, 1, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 9, 2, 1, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 9, 3, 1, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 10, 3, 1, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 11, 3, 1, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 11, 2, 1, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 11, 1, 1, var3);
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
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
      this.a(var1, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab$EnumType.SAND.getMetadata()), 1, 2, 2, var3);
      this.a(var1, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab$EnumType.SAND.getMetadata()), this.a - 2, 2, 2, var3);
      this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var7), 2, 1, 2, var3);
      this.a(var1, Blocks.sandstone_stairs.getStateFromMeta(var17), this.a - 3, 1, 2, var3);
      this.a(var1, var3, 4, 3, 5, 4, 3, 18, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
      this.a(var1, var3, this.a - 5, 3, 5, this.a - 5, 3, 17, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
      this.a(var1, var3, 3, 1, 5, 4, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.a(var1, var3, this.a - 6, 1, 5, this.a - 5, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

      for (byte var10 = 5; var10 <= 17; var10 += 2) {
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 4, 1, var10, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), 4, 2, var10, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), this.a - 5, 1, var10, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), this.a - 5, 2, var10, var3);
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
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var18, 2, 1, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 2, 2, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var18, 2, 3, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var18, 3, 1, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 3, 2, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var18, 3, 3, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 4, 1, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), var18, 4, 2, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 4, 3, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var18, 5, 1, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 5, 2, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var18, 5, 3, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 6, 1, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), var18, 6, 2, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 6, 3, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 7, 1, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 7, 2, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var18, 7, 3, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var18, 8, 1, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var18, 8, 2, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var18, 8, 3, var3);
      }

      for (int var19 = 2; var19 <= this.a - 3; var19 += this.a - 3 - 2) {
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var19 - 1, 2, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19, 2, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var19 + 1, 2, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var19 - 1, 3, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19, 3, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var19 + 1, 3, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 - 1, 4, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), var19, 4, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 + 1, 4, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var19 - 1, 5, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19, 5, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var19 + 1, 5, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 - 1, 6, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), var19, 6, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 + 1, 6, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 - 1, 7, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19, 7, 0, var3);
         this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), var19 + 1, 7, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var19 - 1, 8, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var19, 8, 0, var3);
         this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), var19 + 1, 8, 0, var3);
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         false
      );
      this.a(var1, Blocks.air.getDefaultState(), 8, 6, 0, var3);
      this.a(var1, Blocks.air.getDefaultState(), 12, 6, 0, var3);
      this.a(var1, Blocks.stained_hardened_clay.getStateFromMeta(var8), 9, 5, 0, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), 10, 5, 0, var3);
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()),
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
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()),
         false
      );
      this.a(var1, var3, 8, -8, 8, 12, -1, 12, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
      this.a(var1, var3, 9, -11, 9, 11, -1, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.a(var1, Blocks.stone_pressure_plate.getDefaultState(), 10, -11, 10, var3);
      this.a(var1, var3, 9, -13, 9, 11, -13, 11, Blocks.tnt.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.a(var1, Blocks.air.getDefaultState(), 8, -11, 10, var3);
      this.a(var1, Blocks.air.getDefaultState(), 8, -10, 10, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), 7, -10, 10, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 7, -11, 10, var3);
      this.a(var1, Blocks.air.getDefaultState(), 12, -11, 10, var3);
      this.a(var1, Blocks.air.getDefaultState(), 12, -10, 10, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), 13, -10, 10, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 13, -11, 10, var3);
      this.a(var1, Blocks.air.getDefaultState(), 10, -11, 8, var3);
      this.a(var1, Blocks.air.getDefaultState(), 10, -10, 8, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), 10, -10, 7, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 10, -11, 7, var3);
      this.a(var1, Blocks.air.getDefaultState(), 10, -11, 12, var3);
      this.a(var1, Blocks.air.getDefaultState(), 10, -10, 12, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.CHISELED.getMetadata()), 10, -10, 13, var3);
      this.a(var1, Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata()), 10, -11, 13, var3);

      for (EnumFacing var11 : EnumFacing$Plane.HORIZONTAL) {
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

   public ComponentScatteredFeaturePieces$DesertPyramid(Random var1, int var2, int var3) {
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

   public ComponentScatteredFeaturePieces$DesertPyramid() {
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
