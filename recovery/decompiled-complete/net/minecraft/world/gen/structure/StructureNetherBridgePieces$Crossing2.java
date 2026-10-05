package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import javax.vecmath.Vector2f;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureNetherBridgePieces$Crossing2 extends StructureNetherBridgePieces$Piece {
   public StructureComponent field_0000;
   public Vector2f field_0001;

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      this.getNextComponentNormal((StructureNetherBridgePieces$Start)var1, var2, var3, 1, 0, true);
      this.getNextComponentX((StructureNetherBridgePieces$Start)var1, var2, var3, 0, 1, true);
      this.getNextComponentZ((StructureNetherBridgePieces$Start)var1, var2, var3, 0, 1, true);
   }

   public StructureNetherBridgePieces$Crossing2(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
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

   public static StructureNetherBridgePieces$Crossing2 func_175878_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureNetherBridgePieces$Crossing2(var6, var1, var7, var5)
         : null;
   }

   public StructureNetherBridgePieces$Crossing2() {
   }
}
