package net.minecraft.client.renderer;

import com.google.common.collect.Maps;
import io.netty.handler.codec.compression.JZlibEncoder$3;
import java.util.LinkedHashMap;
import net.minecraft.block.BlockSlab$EnumBlockHalf;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlab$EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiPlayerTabOverlay$1;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.Session$Type;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold$Door;

public class BlockModelShapes$6 extends StateMapperBase {
   public ShaderGroup field_0003;
   public GuiPlayerTabOverlay$1 field_0002;
   public BlockSlab$EnumBlockHalf field_0004;
   public StructureStrongholdPieces$Stronghold$Door field_0000;
   public JZlibEncoder$3 field_0001;
   public Session$Type field_0006;

   public BlockModelShapes$6(BlockModelShapes var1) {
      this.field_178139_a = var1;
      super();
   }

   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState var1) {
      LinkedHashMap var2 = Maps.newLinkedHashMap(var1.getProperties());
      String var3 = BlockStoneSlab.VARIANT.getName((BlockStoneSlab$EnumType)var2.remove(BlockStoneSlab.VARIANT));
      var2.remove(BlockStoneSlab.SEAMLESS);
      String var4 = var1.getValue(BlockStoneSlab.SEAMLESS) ? "all" : "normal";
      return new ModelResourceLocation(var3 + "_double_slab", var4);
   }
}
