package net.minecraft.client.renderer;

import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;

public class BlockModelShapes$4 extends StateMapperBase {
   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState var1) {
      LinkedHashMap var2 = Maps.newLinkedHashMap(var1.getProperties());
      if (var1.getValue(BlockStem.FACING) != EnumFacing.UP) {
         var2.remove(BlockStem.AGE);
      }

      return new ModelResourceLocation(Block.blockRegistry.getNameForObject(var1.getBlock()), this.getPropertyString(var2));
   }

   public BlockModelShapes$4(BlockModelShapes var1) {
      this.field_178134_a = var1;
      super();
   }
}
