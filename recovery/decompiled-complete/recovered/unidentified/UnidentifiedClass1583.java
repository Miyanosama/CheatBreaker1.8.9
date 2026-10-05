package recovered.unidentified;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceMappingsToLongTask;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelShapes$4;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import org.apache.log4j.net.JMSAppender;

public class UnidentifiedClass1583 extends BlockFalling {
   public ConcurrentHashMapV8$MapReduceMappingsToLongTask field_0002;
   public BlockModelShapes$4 field_0003;
   public UnidentifiedClass1858 field_0000;
   public JMSAppender field_0001;

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return MapColor.stoneColor;
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      if (var3 > 3) {
         var3 = 3;
      }

      return var2.nextInt(10 - var3 * 3) == 0 ? Items.flint : Item.getItemFromBlock(this);
   }
}
