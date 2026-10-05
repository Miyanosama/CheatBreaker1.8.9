package net.minecraft.world.gen.structure;

import io.netty.util.internal.ConcurrentSet;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item$1;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureVillagePieces$House4Garden extends StructureVillagePieces$Village {
   public Item$1 field_0001;
   public GuiCustomizeSkin field_0003;
   public boolean isRoofAccessible;
   public ConcurrentSet field_0002;

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      super.writeStructureToNBT(var1);
      var1.setBoolean("Terrace", this.isRoofAccessible);
   }

   public static StructureVillagePieces$House4Garden func_175858_a(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 5, 6, 5, var6);
      return StructureComponent.findIntersecting(var1, var8) != null ? null : new StructureVillagePieces$House4Garden(var0, var7, var2, var8, var6);
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      super.readStructureFromNBT(var1);
      this.isRoofAccessible = var1.getBoolean("Terrace");
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.h < 0) {
         this.h = this.b(var1, var3);
         if (this.h < 0) {
            return true;
         }

         this.l.offset(0, this.h - this.l.maxY + 6 - 1, 0);
      }

      this.a(var1, var3, 0, 0, 0, 4, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
      this.a(var1, var3, 0, 4, 0, 4, 4, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 1, 4, 1, 3, 4, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 1, 0, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 2, 0, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 3, 0, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 1, 0, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 2, 0, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 3, 0, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 1, 4, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 2, 4, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 0, 3, 4, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 1, 4, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 2, 4, var3);
      this.a(var1, Blocks.cobblestone.getDefaultState(), 4, 3, 4, var3);
      this.a(var1, var3, 0, 1, 1, 0, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
      this.a(var1, var3, 4, 1, 1, 4, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
      this.a(var1, var3, 1, 1, 4, 3, 3, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
      this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 2, var3);
      this.a(var1, Blocks.glass_pane.getDefaultState(), 2, 2, 4, var3);
      this.a(var1, Blocks.glass_pane.getDefaultState(), 4, 2, 2, var3);
      this.a(var1, Blocks.planks.getDefaultState(), 1, 1, 0, var3);
      this.a(var1, Blocks.planks.getDefaultState(), 1, 2, 0, var3);
      this.a(var1, Blocks.planks.getDefaultState(), 1, 3, 0, var3);
      this.a(var1, Blocks.planks.getDefaultState(), 2, 3, 0, var3);
      this.a(var1, Blocks.planks.getDefaultState(), 3, 3, 0, var3);
      this.a(var1, Blocks.planks.getDefaultState(), 3, 2, 0, var3);
      this.a(var1, Blocks.planks.getDefaultState(), 3, 1, 0, var3);
      if (this.a(var1, 2, 0, -1, var3).getBlock().getMaterial() == Material.air && this.a(var1, 2, -1, -1, var3).getBlock().getMaterial() != Material.air) {
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 2, 0, -1, var3);
      }

      this.a(var1, var3, 1, 1, 1, 3, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      if (this.isRoofAccessible) {
         this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 0, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 5, 0, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 2, 5, 0, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 3, 5, 0, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 0, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 4, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 5, 4, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 2, 5, 4, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 3, 5, 4, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 4, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 1, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 2, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 4, 5, 3, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 1, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 2, var3);
         this.a(var1, Blocks.oak_fence.getDefaultState(), 0, 5, 3, var3);
      }

      if (this.isRoofAccessible) {
         int var4 = this.a(Blocks.ladder, 3);
         this.a(var1, Blocks.ladder.getStateFromMeta(var4), 3, 1, 3, var3);
         this.a(var1, Blocks.ladder.getStateFromMeta(var4), 3, 2, 3, var3);
         this.a(var1, Blocks.ladder.getStateFromMeta(var4), 3, 3, 3, var3);
         this.a(var1, Blocks.ladder.getStateFromMeta(var4), 3, 4, 3, var3);
      }

      this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m), 2, 3, 1, var3);

      for (int var6 = 0; var6 < 5; var6++) {
         for (int var5 = 0; var5 < 5; var5++) {
            this.b(var1, var5, 6, var6, var3);
            this.b(var1, Blocks.cobblestone.getDefaultState(), var5, -1, var6, var3);
         }
      }

      this.a(var1, var3, 1, 1, 2, 1);
      return true;
   }

   public StructureVillagePieces$House4Garden() {
   }

   public StructureVillagePieces$House4Garden(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
      super(var1, var2);
      this.m = var5;
      this.l = var4;
      this.isRoofAccessible = var3.nextBoolean();
   }
}
