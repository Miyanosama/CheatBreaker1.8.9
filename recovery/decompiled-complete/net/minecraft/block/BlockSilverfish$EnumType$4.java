package net.minecraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.renderer.BlockModelRenderer$AmbientOcclusionFace;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.init.Blocks;

public enum BlockSilverfish$EnumType$4 {
   public BlockSand field_0002;
   public EnumFaceDirection field_0003;
   public BlockModelRenderer$AmbientOcclusionFace field_0000;
   public GuiAchievements field_0001;

   public BlockSilverfish$EnumType$4(int var3, String var4, String var5) {
   }

   @Override
   public IBlockState getModelBlock() {
      return Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick$EnumType.MOSSY);
   }
}
