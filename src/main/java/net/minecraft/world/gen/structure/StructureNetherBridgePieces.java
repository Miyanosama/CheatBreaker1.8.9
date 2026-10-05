package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class StructureNetherBridgePieces {
   public static StructureNetherBridgePieces.PieceWeight[] primaryComponents = new StructureNetherBridgePieces.PieceWeight[]{
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Straight.class, 30, 0, true),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing3.class, 10, 4),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing.class, 10, 4),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Stairs.class, 10, 3),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Throne.class, 5, 2),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Entrance.class, 5, 1)
   };
   public static StructureNetherBridgePieces.PieceWeight[] secondaryComponents = new StructureNetherBridgePieces.PieceWeight[]{
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor5.class, 25, 0, true),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing2.class, 15, 5),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor2.class, 5, 10),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor.class, 5, 10),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor3.class, 10, 3, true),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor4.class, 7, 2),
      new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.NetherStalkRoom.class, 5, 2)
   };

   public static void registerNetherFortressPieces() {
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Crossing3.class, "NeBCr");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.End.class, "NeBEF");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Straight.class, "NeBS");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor3.class, "NeCCS");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor4.class, "NeCTB");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Entrance.class, "NeCE");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Crossing2.class, "NeSCSC");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor.class, "NeSCLT");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor5.class, "NeSC");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Corridor2.class, "NeSCRT");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.NetherStalkRoom.class, "NeCSR");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Throne.class, "NeMT");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Crossing.class, "NeRC");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Stairs.class, "NeSR");
      MapGenStructureIO.registerStructureComponent(StructureNetherBridgePieces.Start.class, "NeStart");
   }

   public static StructureNetherBridgePieces.Piece func_175887_b(
      StructureNetherBridgePieces.PieceWeight var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      Class var8 = var0.weightClass;
      Object var9 = null;
      if (var8 == StructureNetherBridgePieces.Straight.class) {
         var9 = StructureNetherBridgePieces.Straight.func_175882_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.Crossing3.class) {
         var9 = StructureNetherBridgePieces.Crossing3.func_175885_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.Crossing.class) {
         var9 = StructureNetherBridgePieces.Crossing.func_175873_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.Stairs.class) {
         var9 = StructureNetherBridgePieces.Stairs.func_175872_a(var1, var2, var3, var4, var5, var7, var6);
      } else if (var8 == StructureNetherBridgePieces.Throne.class) {
         var9 = StructureNetherBridgePieces.Throne.func_175874_a(var1, var2, var3, var4, var5, var7, var6);
      } else if (var8 == StructureNetherBridgePieces.Entrance.class) {
         var9 = StructureNetherBridgePieces.Entrance.func_175881_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.Corridor5.class) {
         var9 = StructureNetherBridgePieces.Corridor5.func_175877_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.Corridor2.class) {
         var9 = StructureNetherBridgePieces.Corridor2.func_175876_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.Corridor.class) {
         var9 = StructureNetherBridgePieces.Corridor.func_175879_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.Corridor3.class) {
         var9 = StructureNetherBridgePieces.Corridor3.func_175883_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.Corridor4.class) {
         var9 = StructureNetherBridgePieces.Corridor4.func_175880_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.Crossing2.class) {
         var9 = StructureNetherBridgePieces.Crossing2.func_175878_a(var1, var2, var3, var4, var5, var6, var7);
      } else if (var8 == StructureNetherBridgePieces.NetherStalkRoom.class) {
         var9 = StructureNetherBridgePieces.NetherStalkRoom.func_175875_a(var1, var2, var3, var4, var5, var6, var7);
      }

      return (StructureNetherBridgePieces.Piece)var9;
   }

   public static class Corridor extends StructureNetherBridgePieces.Piece {
      public boolean field_111021_b;

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 4, 2, 0, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 4, 3, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 4, 3, 3, 4, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 0, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 4, 3, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 3, 4, 1, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 3, 3, 4, 3, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         if (this.field_111021_b && var3.isVecInside(new BlockPos(this.a(3, 3), this.d(2), this.b(3, 3)))) {
            this.field_111021_b = false;
            this.generateChestContents(var1, var3, var2, 3, 2, 3, a, 2 + var2.nextInt(4));
         }

         this.a(var1, var3, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var4 = 0; var4 <= 4; var4++) {
            for (int var5 = 0; var5 <= 4; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, var5, var3);
            }
         }

         return true;
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentX((StructureNetherBridgePieces.Start)var1, var2, var3, 0, 1, true);
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setBoolean("Chest", this.field_111021_b);
      }

      public static StructureNetherBridgePieces.Corridor func_175879_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Corridor(var6, var1, var7, var5)
            : null;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.field_111021_b = var1.getBoolean("Chest");
      }

      public Corridor() {
      }

      public Corridor(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
         this.field_111021_b = var2.nextInt(3) == 0;
      }
   }

   public static class Corridor2 extends StructureNetherBridgePieces.Piece {
      public boolean field_111020_b;

      public static StructureNetherBridgePieces.Corridor2 func_175876_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Corridor2(var6, var1, var7, var5)
            : null;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setBoolean("Chest", this.field_111020_b);
      }

      public Corridor2(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
         this.field_111020_b = var2.nextInt(3) == 0;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.field_111020_b = var1.getBoolean("Chest");
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 0, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 1, 0, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 3, 0, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 4, 2, 0, 4, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 4, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 3, 4, 1, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 3, 3, 4, 3, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         if (this.field_111020_b && var3.isVecInside(new BlockPos(this.a(1, 3), this.d(2), this.b(1, 3)))) {
            this.field_111020_b = false;
            this.generateChestContents(var1, var3, var2, 1, 2, 3, a, 2 + var2.nextInt(4));
         }

         this.a(var1, var3, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var4 = 0; var4 <= 4; var4++) {
            for (int var5 = 0; var5 <= 4; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, var5, var3);
            }
         }

         return true;
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentZ((StructureNetherBridgePieces.Start)var1, var2, var3, 0, 1, true);
      }

      public Corridor2() {
      }
   }

   public static class Corridor3 extends StructureNetherBridgePieces.Piece {
      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)var1, var2, var3, 1, 0, true);
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         int var4 = this.a(Blocks.nether_brick_stairs, 2);

         for (int var5 = 0; var5 <= 9; var5++) {
            int var6 = Math.max(1, 7 - var5);
            int var7 = Math.min(Math.max(var6 + 5, 14 - var5), 13);
            int var8 = var5;
            this.a(var1, var3, 0, 0, var5, 4, var6, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.a(var1, var3, 1, var6 + 1, var5, 3, var7 - 1, var5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            if (var5 <= 6) {
               this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var4), 1, var6 + 1, var5, var3);
               this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var4), 2, var6 + 1, var5, var3);
               this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var4), 3, var6 + 1, var5, var3);
            }

            this.a(var1, var3, 0, var7, var5, 4, var7, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.a(var1, var3, 0, var6 + 1, var5, 0, var7 - 1, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.a(var1, var3, 4, var6 + 1, var5, 4, var7 - 1, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            if ((var5 & 1) == 0) {
               this.a(
                  var1,
                  var3,
                  0,
                  var6 + 2,
                  var5,
                  0,
                  var6 + 3,
                  var5,
                  Blocks.nether_brick_fence.getDefaultState(),
                  Blocks.nether_brick_fence.getDefaultState(),
                  false
               );
               this.a(
                  var1,
                  var3,
                  4,
                  var6 + 2,
                  var5,
                  4,
                  var6 + 3,
                  var5,
                  Blocks.nether_brick_fence.getDefaultState(),
                  Blocks.nether_brick_fence.getDefaultState(),
                  false
               );
            }

            for (int var9 = 0; var9 <= 4; var9++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var9, -1, var8, var3);
            }
         }

         return true;
      }

      public Corridor3() {
      }

      public static StructureNetherBridgePieces.Corridor3 func_175883_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -7, 0, 5, 14, 10, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Corridor3(var6, var1, var7, var5)
            : null;
      }

      public Corridor3(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }
   }

   public static class Corridor4 extends StructureNetherBridgePieces.Piece {
      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         byte var4 = 1;
         if (this.m == EnumFacing.WEST || this.m == EnumFacing.NORTH) {
            var4 = 5;
         }

         this.getNextComponentX((StructureNetherBridgePieces.Start)var1, var2, var3, 0, var4, var3.nextInt(8) > 0);
         this.getNextComponentZ((StructureNetherBridgePieces.Start)var1, var2, var3, 0, var4, var3.nextInt(8) > 0);
      }

      public Corridor4() {
      }

      public static StructureNetherBridgePieces.Corridor4 func_175880_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -3, 0, 0, 9, 7, 9, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Corridor4(var6, var1, var7, var5)
            : null;
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 0, 0, 8, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 8, 5, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 6, 0, 8, 6, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 2, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 6, 2, 0, 8, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 3, 0, 1, 4, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 7, 3, 0, 7, 4, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 4, 8, 2, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 1, 4, 2, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 6, 1, 4, 7, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 8, 8, 3, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 6, 0, 3, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 8, 3, 6, 8, 3, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 4, 0, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 8, 3, 4, 8, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 3, 5, 2, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 6, 3, 5, 7, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 4, 5, 1, 5, 5, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 7, 4, 5, 7, 5, 5, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);

         for (int var4 = 0; var4 <= 5; var4++) {
            for (int var5 = 0; var5 <= 8; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var5, -1, var4, var3);
            }
         }

         return true;
      }

      public Corridor4(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }
   }

   public static class Corridor5 extends StructureNetherBridgePieces.Piece {
      public Corridor5(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)var1, var2, var3, 1, 0, true);
      }

      public static StructureNetherBridgePieces.Corridor5 func_175877_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Corridor5(var6, var1, var7, var5)
            : null;
      }

      public Corridor5() {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 0, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 4, 2, 0, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 1, 0, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 3, 0, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 4, 3, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 4, 3, 3, 4, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var4 = 0; var4 <= 4; var4++) {
            for (int var5 = 0; var5 <= 4; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, var5, var3);
            }
         }

         return true;
      }
   }

   public static class Crossing extends StructureNetherBridgePieces.Piece {
      public Crossing() {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 0, 0, 6, 1, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 6, 7, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 1, 6, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 6, 1, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 2, 0, 6, 6, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 2, 6, 6, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 0, 6, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 5, 0, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 6, 2, 0, 6, 6, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 6, 2, 5, 6, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 6, 0, 4, 6, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 0, 4, 5, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 2, 6, 6, 4, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 6, 4, 5, 6, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 6, 2, 0, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 2, 0, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 6, 6, 2, 6, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 6, 5, 2, 6, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);

         for (int var4 = 0; var4 <= 6; var4++) {
            for (int var5 = 0; var5 <= 6; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, var5, var3);
            }
         }

         return true;
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)var1, var2, var3, 2, 0, false);
         this.getNextComponentX((StructureNetherBridgePieces.Start)var1, var2, var3, 0, 2, false);
         this.getNextComponentZ((StructureNetherBridgePieces.Start)var1, var2, var3, 0, 2, false);
      }

      public Crossing(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }

      public static StructureNetherBridgePieces.Crossing func_175873_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -2, 0, 0, 7, 9, 7, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Crossing(var6, var1, var7, var5)
            : null;
      }
   }

   public static class Crossing2 extends StructureNetherBridgePieces.Piece {
      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)var1, var2, var3, 1, 0, true);
         this.getNextComponentX((StructureNetherBridgePieces.Start)var1, var2, var3, 0, 1, true);
         this.getNextComponentZ((StructureNetherBridgePieces.Start)var1, var2, var3, 0, 1, true);
      }

      public Crossing2(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 0, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 4, 2, 0, 4, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 4, 0, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 4, 2, 4, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var4 = 0; var4 <= 4; var4++) {
            for (int var5 = 0; var5 <= 4; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, var5, var3);
            }
         }

         return true;
      }

      public static StructureNetherBridgePieces.Crossing2 func_175878_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Crossing2(var6, var1, var7, var5)
            : null;
      }

      public Crossing2() {
      }
   }

   public static class Crossing3 extends StructureNetherBridgePieces.Piece {
      public Crossing3(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }

      public Crossing3() {
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)var1, var2, var3, 8, 3, false);
         this.getNextComponentX((StructureNetherBridgePieces.Start)var1, var2, var3, 3, 8, false);
         this.getNextComponentZ((StructureNetherBridgePieces.Start)var1, var2, var3, 3, 8, false);
      }

      public static StructureNetherBridgePieces.Crossing3 func_175885_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -8, -3, 0, 19, 10, 19, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Crossing3(var6, var1, var7, var5)
            : null;
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 7, 3, 0, 11, 4, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 7, 18, 4, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 8, 5, 0, 10, 7, 18, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 8, 18, 7, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 7, 5, 0, 7, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 7, 5, 11, 7, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 11, 5, 0, 11, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 11, 5, 11, 11, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 7, 7, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 11, 5, 7, 18, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 11, 7, 5, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 11, 5, 11, 18, 5, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 7, 2, 0, 11, 2, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 7, 2, 13, 11, 2, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 7, 0, 0, 11, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 7, 0, 15, 11, 1, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var4 = 7; var4 <= 11; var4++) {
            for (int var5 = 0; var5 <= 2; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, var5, var3);
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, 18 - var5, var3);
            }
         }

         this.a(var1, var3, 0, 2, 7, 5, 2, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 13, 2, 7, 18, 2, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 7, 3, 1, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 15, 0, 7, 18, 1, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var6 = 0; var6 <= 2; var6++) {
            for (int var7 = 7; var7 <= 11; var7++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var6, -1, var7, var3);
               this.b(var1, Blocks.nether_brick.getDefaultState(), 18 - var6, -1, var7, var3);
            }
         }

         return true;
      }

      public Crossing3(Random var1, int var2, int var3) {
         super(0);
         this.m = EnumFacing.Plane.HORIZONTAL.random(var1);
         switch (this.m) {
            case NORTH:
            case SOUTH:
               this.l = new StructureBoundingBox(var2, 64, var3, var2 + 19 - 1, 73, var3 + 19 - 1);
               break;
            default:
               this.l = new StructureBoundingBox(var2, 64, var3, var2 + 19 - 1, 73, var3 + 19 - 1);
         }
      }
   }

   public static class End extends StructureNetherBridgePieces.Piece {
      public int fillSeed;

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         Random var4 = new Random(this.fillSeed);

         for (int var5 = 0; var5 <= 4; var5++) {
            for (int var6 = 3; var6 <= 4; var6++) {
               int var7 = var4.nextInt(8);
               this.a(var1, var3, var5, var6, 0, var5, var6, var7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            }
         }

         int var8 = var4.nextInt(8);
         this.a(var1, var3, 0, 5, 0, 0, 5, var8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         var8 = var4.nextInt(8);
         this.a(var1, var3, 4, 5, 0, 4, 5, var8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var10 = 0; var10 <= 4; var10++) {
            int var12 = var4.nextInt(5);
            this.a(var1, var3, var10, 2, 0, var10, 2, var12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         }

         for (int var11 = 0; var11 <= 4; var11++) {
            for (int var13 = 0; var13 <= 1; var13++) {
               int var14 = var4.nextInt(3);
               this.a(var1, var3, var11, var13, 0, var11, var13, var14, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            }
         }

         return true;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setInteger("Seed", this.fillSeed);
      }

      public End() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.fillSeed = var1.getInteger("Seed");
      }

      public End(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
         this.fillSeed = var2.nextInt();
      }

      public static StructureNetherBridgePieces.End func_175884_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -3, 0, 5, 10, 8, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.End(var6, var1, var7, var5)
            : null;
      }
   }

   public static class Entrance extends StructureNetherBridgePieces.Piece {
      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)var1, var2, var3, 5, 3, true);
      }

      public Entrance() {
      }

      public static StructureNetherBridgePieces.Entrance func_175881_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -5, -3, 0, 13, 14, 13, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Entrance(var6, var1, var7, var5)
            : null;
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 3, 0, 12, 4, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 0, 12, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 0, 1, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 11, 5, 0, 12, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 11, 4, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 8, 5, 11, 10, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 9, 11, 7, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 0, 4, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 8, 5, 0, 10, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 9, 0, 7, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 11, 2, 10, 12, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 8, 0, 7, 8, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);

         for (int var4 = 1; var4 <= 11; var4 += 2) {
            this.a(var1, var3, var4, 10, 0, var4, 11, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, var3, var4, 10, 12, var4, 11, 12, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, var3, 0, 10, var4, 0, 11, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, var3, 12, 10, var4, 12, 11, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, Blocks.nether_brick.getDefaultState(), var4, 13, 0, var3);
            this.a(var1, Blocks.nether_brick.getDefaultState(), var4, 13, 12, var3);
            this.a(var1, Blocks.nether_brick.getDefaultState(), 0, 13, var4, var3);
            this.a(var1, Blocks.nether_brick.getDefaultState(), 12, 13, var4, var3);
            this.a(var1, Blocks.nether_brick_fence.getDefaultState(), var4 + 1, 13, 0, var3);
            this.a(var1, Blocks.nether_brick_fence.getDefaultState(), var4 + 1, 13, 12, var3);
            this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 0, 13, var4 + 1, var3);
            this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 12, 13, var4 + 1, var3);
         }

         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, var3);
         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 12, var3);
         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, var3);
         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 12, 13, 0, var3);

         for (int var6 = 3; var6 <= 9; var6 += 2) {
            this.a(var1, var3, 1, 7, var6, 1, 8, var6, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, var3, 11, 7, var6, 11, 8, var6, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         }

         this.a(var1, var3, 4, 2, 0, 8, 2, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 4, 12, 2, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 4, 0, 0, 8, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 4, 0, 9, 8, 1, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 4, 3, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 9, 0, 4, 12, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var7 = 4; var7 <= 8; var7++) {
            for (int var5 = 0; var5 <= 2; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var7, -1, var5, var3);
               this.b(var1, Blocks.nether_brick.getDefaultState(), var7, -1, 12 - var5, var3);
            }
         }

         for (int var8 = 0; var8 <= 2; var8++) {
            for (int var10 = 4; var10 <= 8; var10++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var8, -1, var10, var3);
               this.b(var1, Blocks.nether_brick.getDefaultState(), 12 - var8, -1, var10, var3);
            }
         }

         this.a(var1, var3, 5, 5, 5, 7, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 6, 1, 6, 6, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, Blocks.nether_brick.getDefaultState(), 6, 0, 6, var3);
         this.a(var1, Blocks.flowing_lava.getDefaultState(), 6, 5, 6, var3);
         BlockPos var9 = new BlockPos(this.a(6, 6), this.d(5), this.b(6, 6));
         if (var3.isVecInside(var9)) {
            var1.forceBlockUpdateTick(Blocks.flowing_lava, var9, var2);
         }

         return true;
      }

      public Entrance(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }
   }

   public static class NetherStalkRoom extends StructureNetherBridgePieces.Piece {
      public static StructureNetherBridgePieces.NetherStalkRoom func_175875_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -5, -3, 0, 13, 14, 13, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.NetherStalkRoom(var6, var1, var7, var5)
            : null;
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)var1, var2, var3, 5, 3, true);
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)var1, var2, var3, 5, 11, true);
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 3, 0, 12, 4, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 0, 12, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 0, 1, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 11, 5, 0, 12, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 11, 4, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 8, 5, 11, 10, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 9, 11, 7, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 0, 4, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 8, 5, 0, 10, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 9, 0, 7, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 11, 2, 10, 12, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var4 = 1; var4 <= 11; var4 += 2) {
            this.a(var1, var3, var4, 10, 0, var4, 11, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, var3, var4, 10, 12, var4, 11, 12, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, var3, 0, 10, var4, 0, 11, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, var3, 12, 10, var4, 12, 11, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, Blocks.nether_brick.getDefaultState(), var4, 13, 0, var3);
            this.a(var1, Blocks.nether_brick.getDefaultState(), var4, 13, 12, var3);
            this.a(var1, Blocks.nether_brick.getDefaultState(), 0, 13, var4, var3);
            this.a(var1, Blocks.nether_brick.getDefaultState(), 12, 13, var4, var3);
            this.a(var1, Blocks.nether_brick_fence.getDefaultState(), var4 + 1, 13, 0, var3);
            this.a(var1, Blocks.nether_brick_fence.getDefaultState(), var4 + 1, 13, 12, var3);
            this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 0, 13, var4 + 1, var3);
            this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 12, 13, var4 + 1, var3);
         }

         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, var3);
         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 12, var3);
         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, var3);
         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 12, 13, 0, var3);

         for (int var9 = 3; var9 <= 9; var9 += 2) {
            this.a(var1, var3, 1, 7, var9, 1, 8, var9, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
            this.a(var1, var3, 11, 7, var9, 11, 8, var9, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         }

         int var10 = this.a(Blocks.nether_brick_stairs, 3);

         for (int var5 = 0; var5 <= 6; var5++) {
            int var6 = var5 + 4;

            for (int var7 = 5; var7 <= 7; var7++) {
               this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var10), var7, 5 + var5, var6, var3);
            }

            if (var6 >= 5 && var6 <= 8) {
               this.a(var1, var3, 5, 5, var6, 7, var5 + 4, var6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            } else if (var6 >= 9 && var6 <= 10) {
               this.a(var1, var3, 5, 8, var6, 7, var5 + 4, var6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            }

            if (var5 >= 1) {
               this.a(var1, var3, 5, 6 + var5, var6, 7, 9 + var5, var6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
         }

         for (int var11 = 5; var11 <= 7; var11++) {
            this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var10), var11, 12, 11, var3);
         }

         this.a(var1, var3, 5, 6, 7, 5, 7, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 7, 6, 7, 7, 7, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 5, 13, 12, 7, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 2, 3, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 9, 3, 5, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 4, 2, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 9, 5, 2, 10, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 9, 5, 9, 10, 5, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 10, 5, 4, 10, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         int var12 = this.a(Blocks.nether_brick_stairs, 0);
         int var13 = this.a(Blocks.nether_brick_stairs, 1);
         this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var13), 4, 5, 2, var3);
         this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var13), 4, 5, 3, var3);
         this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var13), 4, 5, 9, var3);
         this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var13), 4, 5, 10, var3);
         this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var12), 8, 5, 2, var3);
         this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var12), 8, 5, 3, var3);
         this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var12), 8, 5, 9, var3);
         this.a(var1, Blocks.nether_brick_stairs.getStateFromMeta(var12), 8, 5, 10, var3);
         this.a(var1, var3, 3, 4, 4, 4, 4, 8, Blocks.soul_sand.getDefaultState(), Blocks.soul_sand.getDefaultState(), false);
         this.a(var1, var3, 8, 4, 4, 9, 4, 8, Blocks.soul_sand.getDefaultState(), Blocks.soul_sand.getDefaultState(), false);
         this.a(var1, var3, 3, 5, 4, 4, 5, 8, Blocks.nether_wart.getDefaultState(), Blocks.nether_wart.getDefaultState(), false);
         this.a(var1, var3, 8, 5, 4, 9, 5, 8, Blocks.nether_wart.getDefaultState(), Blocks.nether_wart.getDefaultState(), false);
         this.a(var1, var3, 4, 2, 0, 8, 2, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 4, 12, 2, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 4, 0, 0, 8, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 4, 0, 9, 8, 1, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 4, 3, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 9, 0, 4, 12, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var14 = 4; var14 <= 8; var14++) {
            for (int var8 = 0; var8 <= 2; var8++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var14, -1, var8, var3);
               this.b(var1, Blocks.nether_brick.getDefaultState(), var14, -1, 12 - var8, var3);
            }
         }

         for (int var15 = 0; var15 <= 2; var15++) {
            for (int var16 = 4; var16 <= 8; var16++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var15, -1, var16, var3);
               this.b(var1, Blocks.nether_brick.getDefaultState(), 12 - var15, -1, var16, var3);
            }
         }

         return true;
      }

      public NetherStalkRoom(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }

      public NetherStalkRoom() {
      }
   }

   public abstract static class Piece extends StructureComponent {
      public static List<WeightedRandomChestContent> a = Lists.newArrayList(
         new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 5),
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 5),
         new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 15),
         new WeightedRandomChestContent(Items.golden_sword, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.golden_chestplate, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.flint_and_steel, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.nether_wart, 0, 3, 7, 5),
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 8),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 3),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 2, 4, 2)
      );

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
      }

      public Piece() {
      }

      public Piece(int var1) {
         super(var1);
      }

      public static boolean isAboveGround(StructureBoundingBox var0) {
         return var0 != null && var0.minY > 10;
      }

      public StructureNetherBridgePieces.Piece func_175871_a(
         StructureNetherBridgePieces.Start var1,
         List<StructureNetherBridgePieces.PieceWeight> var2,
         List<StructureComponent> var3,
         Random var4,
         int var5,
         int var6,
         int var7,
         EnumFacing var8,
         int var9
      ) {
         int var10 = this.getTotalWeight(var2);
         boolean var11 = var10 > 0 && var9 <= 30;
         int var12 = 0;

         while (var12 < 5 && var11) {
            var12++;
            int var13 = var4.nextInt(var10);

            for (StructureNetherBridgePieces.PieceWeight var15 : var2) {
               var13 -= var15.field_78826_b;
               if (var13 < 0) {
                  if (!var15.func_78822_a(var9) || var15 == var1.theNetherBridgePieceWeight && !var15.field_78825_e) {
                     break;
                  }

                  StructureNetherBridgePieces.Piece var16 = StructureNetherBridgePieces.func_175887_b(var15, var3, var4, var5, var6, var7, var8, var9);
                  if (var16 != null) {
                     var15.field_78827_c++;
                     var1.theNetherBridgePieceWeight = var15;
                     if (!var15.func_78823_a()) {
                        var2.remove(var15);
                     }

                     return var16;
                  }
               }
            }
         }

         return StructureNetherBridgePieces.End.func_175884_a(var3, var4, var5, var6, var7, var8, var9);
      }

      public StructureComponent getNextComponentNormal(
         StructureNetherBridgePieces.Start var1, List<StructureComponent> var2, Random var3, int var4, int var5, boolean var6
      ) {
         if (this.m != null) {
            switch (this.m) {
               case NORTH:
                  return this.func_175870_a(var1, var2, var3, this.l.minX + var4, this.l.minY + var5, this.l.minZ - 1, this.m, this.getComponentType(), var6);
               case SOUTH:
                  return this.func_175870_a(var1, var2, var3, this.l.minX + var4, this.l.minY + var5, this.l.maxZ + 1, this.m, this.getComponentType(), var6);
               case WEST:
                  return this.func_175870_a(var1, var2, var3, this.l.minX - 1, this.l.minY + var5, this.l.minZ + var4, this.m, this.getComponentType(), var6);
               case EAST:
                  return this.func_175870_a(var1, var2, var3, this.l.maxX + 1, this.l.minY + var5, this.l.minZ + var4, this.m, this.getComponentType(), var6);
            }
         }

         return null;
      }

      public StructureComponent func_175870_a(
         StructureNetherBridgePieces.Start var1,
         List<StructureComponent> var2,
         Random var3,
         int var4,
         int var5,
         int var6,
         EnumFacing var7,
         int var8,
         boolean var9
      ) {
         if (Math.abs(var4 - var1.getBoundingBox().minX) <= 112 && Math.abs(var6 - var1.getBoundingBox().minZ) <= 112) {
            List var10 = var1.primaryWeights;
            if (var9) {
               var10 = var1.secondaryWeights;
            }

            StructureNetherBridgePieces.Piece var11 = this.func_175871_a(var1, var10, var2, var3, var4, var5, var6, var7, var8 + 1);
            if (var11 != null) {
               var2.add(var11);
               var1.field_74967_d.add(var11);
            }

            return var11;
         } else {
            return StructureNetherBridgePieces.End.func_175884_a(var2, var3, var4, var5, var6, var7, var8);
         }
      }

      public int getTotalWeight(List<StructureNetherBridgePieces.PieceWeight> var1) {
         boolean var2 = false;
         int var3 = 0;

         for (StructureNetherBridgePieces.PieceWeight var5 : var1) {
            if (var5.field_78824_d > 0 && var5.field_78827_c < var5.field_78824_d) {
               var2 = true;
            }

            var3 += var5.field_78826_b;
         }

         return var2 ? var3 : -1;
      }

      public StructureComponent getNextComponentX(
         StructureNetherBridgePieces.Start var1, List<StructureComponent> var2, Random var3, int var4, int var5, boolean var6
      ) {
         if (this.m != null) {
            switch (this.m) {
               case NORTH:
                  return this.func_175870_a(
                     var1, var2, var3, this.l.minX - 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.WEST, this.getComponentType(), var6
                  );
               case SOUTH:
                  return this.func_175870_a(
                     var1, var2, var3, this.l.minX - 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.WEST, this.getComponentType(), var6
                  );
               case WEST:
                  return this.func_175870_a(
                     var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType(), var6
                  );
               case EAST:
                  return this.func_175870_a(
                     var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType(), var6
                  );
            }
         }

         return null;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
      }

      public StructureComponent getNextComponentZ(
         StructureNetherBridgePieces.Start var1, List<StructureComponent> var2, Random var3, int var4, int var5, boolean var6
      ) {
         if (this.m != null) {
            switch (this.m) {
               case NORTH:
                  return this.func_175870_a(
                     var1, var2, var3, this.l.maxX + 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.EAST, this.getComponentType(), var6
                  );
               case SOUTH:
                  return this.func_175870_a(
                     var1, var2, var3, this.l.maxX + 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.EAST, this.getComponentType(), var6
                  );
               case WEST:
                  return this.func_175870_a(
                     var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType(), var6
                  );
               case EAST:
                  return this.func_175870_a(
                     var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType(), var6
                  );
            }
         }

         return null;
      }
   }

   public static class PieceWeight {
      public int field_78826_b;
      public boolean field_78825_e;
      public int field_78827_c;
      public int field_78824_d;
      public Class<? extends StructureNetherBridgePieces.Piece> weightClass;

      public boolean func_78822_a(int var1) {
         return this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d;
      }

      public boolean func_78823_a() {
         return this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d;
      }

      public PieceWeight(Class<? extends StructureNetherBridgePieces.Piece> var1, int var2, int var3) {
         this(var1, var2, var3, false);
      }

      public PieceWeight(Class<? extends StructureNetherBridgePieces.Piece> var1, int var2, int var3, boolean var4) {
         this.weightClass = var1;
         this.field_78826_b = var2;
         this.field_78824_d = var3;
         this.field_78825_e = var4;
      }
   }

   public static class Stairs extends StructureNetherBridgePieces.Piece {
      public Stairs(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentZ((StructureNetherBridgePieces.Start)var1, var2, var3, 6, 2, false);
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 0, 0, 6, 1, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 6, 10, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 1, 8, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 2, 0, 6, 8, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 1, 0, 8, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 6, 2, 1, 6, 8, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 6, 5, 8, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 2, 0, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 6, 3, 2, 6, 5, 2, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 6, 3, 4, 6, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, Blocks.nether_brick.getDefaultState(), 5, 2, 5, var3);
         this.a(var1, var3, 4, 2, 5, 4, 3, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 3, 2, 5, 3, 4, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 2, 5, 2, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 5, 1, 6, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 7, 1, 5, 7, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 6, 8, 2, 6, 8, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 2, 6, 0, 4, 8, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 2, 5, 0, 4, 5, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);

         for (int var4 = 0; var4 <= 6; var4++) {
            for (int var5 = 0; var5 <= 6; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, var5, var3);
            }
         }

         return true;
      }

      public Stairs() {
      }

      public static StructureNetherBridgePieces.Stairs func_175872_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, int var5, EnumFacing var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -2, 0, 0, 7, 11, 7, var6);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Stairs(var5, var1, var7, var6)
            : null;
      }
   }

   public static class Start extends StructureNetherBridgePieces.Crossing3 {
      public List<StructureComponent> field_74967_d = Lists.newArrayList();
      public List<StructureNetherBridgePieces.PieceWeight> secondaryWeights;
      public List<StructureNetherBridgePieces.PieceWeight> primaryWeights;
      public StructureNetherBridgePieces.PieceWeight theNetherBridgePieceWeight;

      public Start() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
      }

      public Start(Random var1, int var2, int var3) {
         super(var1, var2, var3);
         this.primaryWeights = Lists.newArrayList();

         for (StructureNetherBridgePieces.PieceWeight var7 : StructureNetherBridgePieces.primaryComponents) {
            var7.field_78827_c = 0;
            this.primaryWeights.add(var7);
         }

         this.secondaryWeights = Lists.newArrayList();

         for (StructureNetherBridgePieces.PieceWeight var11 : StructureNetherBridgePieces.secondaryComponents) {
            var11.field_78827_c = 0;
            this.secondaryWeights.add(var11);
         }
      }
   }

   public static class Straight extends StructureNetherBridgePieces.Piece {
      public Straight(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         this.getNextComponentNormal((StructureNetherBridgePieces.Start)var1, var2, var3, 1, 3, false);
      }

      public Straight() {
      }

      public static StructureNetherBridgePieces.Straight func_175882_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -3, 0, 5, 10, 19, var5);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Straight(var6, var1, var7, var5)
            : null;
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 3, 0, 4, 4, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 5, 0, 3, 7, 18, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 0, 0, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 4, 5, 0, 4, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 0, 4, 2, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 2, 13, 4, 2, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 0, 4, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 0, 15, 4, 1, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

         for (int var4 = 0; var4 <= 4; var4++) {
            for (int var5 = 0; var5 <= 2; var5++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, var5, var3);
               this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, 18 - var5, var3);
            }
         }

         this.a(var1, var3, 0, 1, 1, 0, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 4, 0, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 3, 14, 0, 4, 14, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 0, 1, 17, 0, 4, 17, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 4, 1, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 4, 3, 4, 4, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 4, 3, 14, 4, 4, 14, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 4, 1, 17, 4, 4, 17, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         return true;
      }
   }

   public static class Throne extends StructureNetherBridgePieces.Piece {
      public boolean hasSpawner;

      public static StructureNetherBridgePieces.Throne func_175874_a(
         List<StructureComponent> var0, Random var1, int var2, int var3, int var4, int var5, EnumFacing var6
      ) {
         StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -2, 0, 0, 7, 8, 9, var6);
         return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
            ? new StructureNetherBridgePieces.Throne(var5, var1, var7, var6)
            : null;
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 2, 0, 6, 7, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.a(var1, var3, 1, 0, 0, 5, 1, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 1, 5, 2, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 3, 2, 5, 3, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 4, 3, 5, 4, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 2, 0, 1, 4, 2, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 2, 0, 5, 4, 2, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 5, 2, 1, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 5, 5, 2, 5, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 0, 5, 3, 0, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 6, 5, 3, 6, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, var3, 1, 5, 8, 5, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 1, 6, 3, var3);
         this.a(var1, Blocks.nether_brick_fence.getDefaultState(), 5, 6, 3, var3);
         this.a(var1, var3, 0, 6, 3, 0, 6, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 6, 6, 3, 6, 6, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 1, 6, 8, 5, 7, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         this.a(var1, var3, 2, 8, 8, 4, 8, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
         if (!this.hasSpawner) {
            BlockPos var4 = new BlockPos(this.a(3, 5), this.d(5), this.b(3, 5));
            if (var3.isVecInside(var4)) {
               this.hasSpawner = true;
               var1.a(var4, Blocks.mob_spawner.getDefaultState(), 2);
               TileEntity var5 = var1.getTileEntity(var4);
               if (var5 instanceof TileEntityMobSpawner) {
                  ((TileEntityMobSpawner)var5).getSpawnerBaseLogic().setEntityName("Blaze");
               }
            }
         }

         for (int var6 = 0; var6 <= 6; var6++) {
            for (int var7 = 0; var7 <= 6; var7++) {
               this.b(var1, Blocks.nether_brick.getDefaultState(), var6, -1, var7, var3);
            }
         }

         return true;
      }

      public Throne() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         super.readStructureFromNBT(var1);
         this.hasSpawner = var1.getBoolean("Mob");
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         super.writeStructureToNBT(var1);
         var1.setBoolean("Mob", this.hasSpawner);
      }

      public Throne(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }
   }
}
