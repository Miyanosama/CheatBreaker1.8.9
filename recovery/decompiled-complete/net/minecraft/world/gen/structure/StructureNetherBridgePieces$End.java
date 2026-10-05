package net.minecraft.world.gen.structure;

import io.netty.util.concurrent.SingleThreadEventExecutor$4;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.optifine.shaders.gui.GuiButtonEnumShaderOption$1;

public class StructureNetherBridgePieces$End extends StructureNetherBridgePieces$Piece {
   public SingleThreadEventExecutor$4 field_0001;
   public int fillSeed;
   public GuiButtonEnumShaderOption$1 field_0000;

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

   public StructureNetherBridgePieces$End() {
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      super.readStructureFromNBT(var1);
      this.fillSeed = var1.getInteger("Seed");
   }

   public StructureNetherBridgePieces$End(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
      super(var1);
      this.m = var4;
      this.l = var3;
      this.fillSeed = var2.nextInt();
   }

   public static StructureNetherBridgePieces$End func_175884_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -3, 0, 5, 10, 8, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureNetherBridgePieces$End(var6, var1, var7, var5)
         : null;
   }
}
