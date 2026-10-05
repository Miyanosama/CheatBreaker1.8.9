package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.world.World;

public class StructureVillagePieces$Well extends StructureVillagePieces$Village {
   public StructureVillagePieces$Well(StructureVillagePieces$Start var1, int var2, Random var3, int var4, int var5) {
      super(var1, var2);
      this.m = EnumFacing$Plane.HORIZONTAL.random(var3);
      switch (StructureVillagePieces$1.field_176064_a[this.m.ordinal()]) {
         case 1:
         case 2:
            this.l = new StructureBoundingBox(var4, 64, var5, var4 + 6 - 1, 78, var5 + 6 - 1);
            break;
         default:
            this.l = new StructureBoundingBox(var4, 64, var5, var4 + 6 - 1, 78, var5 + 6 - 1);
      }
   }

   public StructureVillagePieces$Well() {
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
      StructureVillagePieces.access$100(
         (StructureVillagePieces$Start)var1, var2, var3, this.l.minX - 1, this.l.maxY - 4, this.l.minZ + 1, EnumFacing.WEST, this.getComponentType()
      );
      StructureVillagePieces.access$100(
         (StructureVillagePieces$Start)var1, var2, var3, this.l.maxX + 1, this.l.maxY - 4, this.l.minZ + 1, EnumFacing.EAST, this.getComponentType()
      );
      StructureVillagePieces.access$100(
         (StructureVillagePieces$Start)var1, var2, var3, this.l.minX + 1, this.l.maxY - 4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
      );
      StructureVillagePieces.access$100(
         (StructureVillagePieces$Start)var1, var2, var3, this.l.minX + 1, this.l.maxY - 4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
      );
   }
}
