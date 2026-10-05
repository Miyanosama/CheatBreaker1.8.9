package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureVillagePieces$Church extends StructureVillagePieces$Village {
   public EntityAgeable field_0000;

   @Override
   public int func_180779_c(int var1, int var2) {
      return 2;
   }

   public StructureVillagePieces$Church(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
      super(var1, var2);
      this.m = var5;
      this.l = var4;
   }

   public StructureVillagePieces$Church() {
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

   public static StructureVillagePieces$Church func_175854_a(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 5, 12, 9, var6);
      return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
         ? new StructureVillagePieces$Church(var0, var7, var2, var8, var6)
         : null;
   }
}
