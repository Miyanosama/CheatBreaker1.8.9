package net.minecraft.world.gen.structure;

import io.netty.util.internal.UnsafeAtomicIntegerFieldUpdater;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockTorch$1;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.optifine.expr.FunctionFloatArray;

public class StructureNetherBridgePieces$Corridor2 extends StructureNetherBridgePieces$Piece {
   public S1CPacketEntityMetadata field_0002;
   public BlockTorch$1 field_0004;
   public UnsafeAtomicIntegerFieldUpdater field_0001;
   public FunctionFloatArray field_0003;
   public boolean field_111020_b;

   public static StructureNetherBridgePieces$Corridor2 func_175876_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureNetherBridgePieces$Corridor2(var6, var1, var7, var5)
         : null;
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      super.writeStructureToNBT(var1);
      var1.setBoolean("Chest", this.field_111020_b);
   }

   public StructureNetherBridgePieces$Corridor2(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
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
      this.getNextComponentZ((StructureNetherBridgePieces$Start)var1, var2, var3, 0, 1, true);
   }

   public StructureNetherBridgePieces$Corridor2() {
   }
}
