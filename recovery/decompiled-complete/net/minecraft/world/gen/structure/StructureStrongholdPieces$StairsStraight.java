package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureStrongholdPieces$StairsStraight extends StructureStrongholdPieces$Stronghold {
   public DefaultStateMapper field_0000;

   public StructureStrongholdPieces$StairsStraight() {
   }

   public static StructureStrongholdPieces$StairsStraight func_175861_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -7, 0, 5, 11, 8, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureStrongholdPieces$StairsStraight(var6, var1, var7, var5)
         : null;
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      this.a((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.a(var1, var3)) {
         return false;
      } else {
         this.a(var1, var3, 0, 0, 0, 4, 10, 7, true, var2, StructureStrongholdPieces.access$200());
         this.a(var1, var2, var3, this.d, 1, 7, 0);
         this.a(var1, var2, var3, StructureStrongholdPieces$Stronghold$Door.NORTH, 1, 1, 7);
         int var4 = this.a(Blocks.stone_stairs, 2);

         for (int var5 = 0; var5 < 6; var5++) {
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 1, 6 - var5, 1 + var5, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 2, 6 - var5, 1 + var5, var3);
            this.a(var1, Blocks.stone_stairs.getStateFromMeta(var4), 3, 6 - var5, 1 + var5, var3);
            if (var5 < 5) {
               this.a(var1, Blocks.stonebrick.getDefaultState(), 1, 5 - var5, 1 + var5, var3);
               this.a(var1, Blocks.stonebrick.getDefaultState(), 2, 5 - var5, 1 + var5, var3);
               this.a(var1, Blocks.stonebrick.getDefaultState(), 3, 5 - var5, 1 + var5, var3);
            }
         }

         return true;
      }
   }

   public StructureStrongholdPieces$StairsStraight(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
      super(var1);
      this.m = var4;
      this.d = this.a(var2);
      this.l = var3;
   }
}
