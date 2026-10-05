package net.minecraft.world.gen.structure;

import io.netty.handler.ssl.JettyNpnSslEngine;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityBeacon$BeamSegment;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.scijava.nativelib.BaseJniExtractor;

public class StructureNetherBridgePieces$Corridor4 extends StructureNetherBridgePieces$Piece {
   public TileEntityBeacon$BeamSegment field_0001;
   public StructureOceanMonumentPieces$YZDoubleRoomFitHelper field_0003;
   public BaseJniExtractor field_0000;
   public JettyNpnSslEngine field_0002;

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      byte var4 = 1;
      if (this.m == EnumFacing.WEST || this.m == EnumFacing.NORTH) {
         var4 = 5;
      }

      this.getNextComponentX((StructureNetherBridgePieces$Start)var1, var2, var3, 0, var4, var3.nextInt(8) > 0);
      this.getNextComponentZ((StructureNetherBridgePieces$Start)var1, var2, var3, 0, var4, var3.nextInt(8) > 0);
   }

   public StructureNetherBridgePieces$Corridor4() {
   }

   public static StructureNetherBridgePieces$Corridor4 func_175880_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -3, 0, 0, 9, 7, 9, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureNetherBridgePieces$Corridor4(var6, var1, var7, var5)
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

   public StructureNetherBridgePieces$Corridor4(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
      super(var1);
      this.m = var4;
      this.l = var3;
   }
}
