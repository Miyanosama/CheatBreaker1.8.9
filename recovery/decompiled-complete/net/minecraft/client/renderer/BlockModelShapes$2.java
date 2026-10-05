package net.minecraft.client.renderer;

import com.cheatbreaker.client.module.type.FPSModule;
import io.netty.handler.codec.ByteToMessageCodec$Encoder;
import junit.framework.TestCase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class BlockModelShapes$2 extends StateMapperBase {
   public FPSModule field_0001;
   public ByteToMessageCodec$Encoder field_0003;
   public TestCase field_0000;

   public BlockModelShapes$2(BlockModelShapes var1) {
      this.field_178136_a = var1;
      super();
   }

   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState var1) {
      return new ModelResourceLocation("dead_bush", "normal");
   }
}
