package net.minecraft.client.renderer;

import com.google.common.collect.Maps;
import io.netty.buffer.ReadOnlyByteBuf;
import java.util.LinkedHashMap;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDirt$DirtType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import org.apache.log4j.config.PropertyPrinter;

public class BlockModelShapes$5 extends StateMapperBase {
   public PropertyPrinter field_0001;
   public ServersideAttributeMap field_0000;
   public ReadOnlyByteBuf field_0002;

   public BlockModelShapes$5(BlockModelShapes var1) {
      this.field_178135_a = var1;
      super();
   }

   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState var1) {
      LinkedHashMap var2 = Maps.newLinkedHashMap(var1.getProperties());
      String var3 = BlockDirt.VARIANT.getName((BlockDirt$DirtType)var2.remove(BlockDirt.VARIANT));
      if (BlockDirt$DirtType.PODZOL != var1.getValue(BlockDirt.VARIANT)) {
         var2.remove(BlockDirt.SNOWY);
      }

      return new ModelResourceLocation(var3, this.getPropertyString(var2));
   }
}
