package recovered.unidentified;

import com.google.common.base.Function;
import io.netty.channel.DefaultMessageSizeEstimator$1;
import net.minecraft.block.BlockFlower$EnumFlowerColor;
import net.minecraft.block.BlockFlower$EnumFlowerType;
import net.minecraft.client.renderer.block.model.BlockPart$1;
import net.minecraft.item.ItemStack;

public class UnidentifiedClass1509 implements Function<ItemStack, String> {
   public DefaultMessageSizeEstimator$1 field_0001;
   public BlockPart$1 field_0003;
   public UnidentifiedClass3838 field_0000;
   public UnidentifiedClass0026 field_0002;

   public String method_10410(ItemStack var1) {
      return BlockFlower$EnumFlowerType.getType(BlockFlower$EnumFlowerColor.RED, var1.getMetadata()).getUnlocalizedName();
   }
}
