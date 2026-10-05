package net.minecraft.client.renderer;

import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockQuartz$EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item$ToolMaterial;
import net.minecraft.scoreboard.IScoreObjectiveCriteria$EnumRenderType;
import recovered.unidentified.UnidentifiedClass1294;

public class BlockModelShapes$1 extends StateMapperBase {
   public Item$ToolMaterial field_0002;
   public IScoreObjectiveCriteria$EnumRenderType field_0004;
   public UnidentifiedClass1294 field_0001;
   public ModelZombieVillager field_0000;

   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState var1) {
      BlockQuartz$EnumType var2 = var1.getValue(BlockQuartz.VARIANT);
      switch (BlockModelShapes$8.field_178257_a[var2.ordinal()]) {
         case 1:
         default:
            return new ModelResourceLocation("quartz_block", "normal");
         case 2:
            return new ModelResourceLocation("chiseled_quartz_block", "normal");
         case 3:
            return new ModelResourceLocation("quartz_column", "axis=y");
         case 4:
            return new ModelResourceLocation("quartz_column", "axis=x");
         case 5:
            return new ModelResourceLocation("quartz_column", "axis=z");
      }
   }

   public BlockModelShapes$1(BlockModelShapes var1) {
      this.field_178143_a = var1;
      super();
   }
}
