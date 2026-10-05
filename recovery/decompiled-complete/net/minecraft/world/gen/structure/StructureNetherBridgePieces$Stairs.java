package net.minecraft.world.gen.structure;

import io.netty.util.ReferenceCountUtil;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureNetherBridgePieces$Stairs extends StructureNetherBridgePieces$Piece {
   public StatFileWriter field_0000;
   public ReferenceCountUtil field_0001;

   public StructureNetherBridgePieces$Stairs(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
      super(var1);
      this.m = var4;
      this.l = var3;
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      this.getNextComponentZ((StructureNetherBridgePieces$Start)var1, var2, var3, 6, 2, false);
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

   public StructureNetherBridgePieces$Stairs() {
   }

   public static StructureNetherBridgePieces$Stairs func_175872_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, int var5, EnumFacing var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -2, 0, 0, 7, 11, 7, var6);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureNetherBridgePieces$Stairs(var5, var1, var7, var6)
         : null;
   }
}
