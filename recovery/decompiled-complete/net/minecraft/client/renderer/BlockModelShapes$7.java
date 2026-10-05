package net.minecraft.client.renderer;

import com.google.common.collect.Maps;
import io.netty.handler.codec.socks.SocksProtocolVersion;
import io.netty.util.concurrent.BlockingOperationException;
import io.netty.util.concurrent.MultithreadEventExecutorGroup$GenericEventExecutorChooser;
import java.util.LinkedHashMap;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.BlockStoneSlabNew$EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.Explosion;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$End;

public class BlockModelShapes$7 extends StateMapperBase {
   public Explosion field_0003;
   public SocksProtocolVersion field_0005;
   public MultithreadEventExecutorGroup$GenericEventExecutorChooser field_0002;
   public StructureNetherBridgePieces$End field_0000;
   public BlockingOperationException field_0001;

   public BlockModelShapes$7(BlockModelShapes var1) {
      this.field_178138_a = var1;
      super();
   }

   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState var1) {
      LinkedHashMap var2 = Maps.newLinkedHashMap(var1.getProperties());
      String var3 = BlockStoneSlabNew.VARIANT.getName((BlockStoneSlabNew$EnumType)var2.remove(BlockStoneSlabNew.VARIANT));
      var2.remove(BlockStoneSlab.SEAMLESS);
      String var4 = var1.getValue(BlockStoneSlabNew.SEAMLESS) ? "all" : "normal";
      return new ModelResourceLocation(var3 + "_double_slab", var4);
   }
}
