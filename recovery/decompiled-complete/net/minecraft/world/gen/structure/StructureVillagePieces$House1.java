package net.minecraft.world.gen.structure;

import io.netty.channel.sctp.oio.OioSctpServerChannel$2;
import io.netty.util.internal.UnsafeAtomicReferenceFieldUpdater;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.entity.EntityFlying;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureVillagePieces$House1 extends StructureVillagePieces$Village {
   public EntityFlying field_0001;
   public BlockFaceUV field_0003;
   public UnsafeAtomicReferenceFieldUpdater field_0000;
   public OioSctpServerChannel$2 field_0002;

   public StructureVillagePieces$House1(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
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

   public StructureVillagePieces$House1() {
   }

   public static StructureVillagePieces$House1 func_175850_a(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 9, 9, 6, var6);
      return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
         ? new StructureVillagePieces$House1(var0, var7, var2, var8, var6)
         : null;
   }

   @Override
   public int func_180779_c(int var1, int var2) {
      return 1;
   }
}
