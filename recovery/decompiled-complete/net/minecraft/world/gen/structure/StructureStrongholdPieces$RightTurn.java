package net.minecraft.world.gen.structure;

import io.netty.channel.AbstractChannel$AbstractUnsafe;
import java.util.List;
import java.util.Random;
import javax.vecmath.Point3d;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureStrongholdPieces$RightTurn extends StructureStrongholdPieces$LeftTurn {
   public Point3d field_0000;
   public AbstractChannel$AbstractUnsafe field_0001;

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.a(var1, var3)) {
         return false;
      } else {
         this.a(var1, var3, 0, 0, 0, 4, 4, 4, true, var2, StructureStrongholdPieces.access$200());
         this.a(var1, var2, var3, this.d, 1, 1, 0);
         if (this.m != EnumFacing.NORTH && this.m != EnumFacing.EAST) {
            this.a(var1, var3, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         } else {
            this.a(var1, var3, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         return true;
      }
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      if (this.m != EnumFacing.NORTH && this.m != EnumFacing.EAST) {
         this.b((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
      } else {
         this.c((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
      }
   }
}
