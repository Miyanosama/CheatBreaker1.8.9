package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelShapes$8;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerWorkbench;
import net.optifine.entity.model.anim.RenderEntityParameterFloat$1;

public abstract class StructureComponent$BlockSelector {
   public RenderEntityParameterFloat$1 field_0004;
   public ContainerWorkbench field_0002;
   public RenderEntity field_0003;
   public BlockModelShapes$8 field_0000;
   public ComponentScatteredFeaturePieces$JunglePyramid field_0001;
   public IBlockState a = Blocks.air.getDefaultState();

   public IBlockState getBlockState() {
      return this.a;
   }

   public abstract void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5);
}
