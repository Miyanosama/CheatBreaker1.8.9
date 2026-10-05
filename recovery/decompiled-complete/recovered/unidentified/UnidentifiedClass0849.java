package recovered.unidentified;

import com.google.common.base.Function;
import net.minecraft.block.BlockFlower$EnumFlowerColor;
import net.minecraft.block.BlockFlower$EnumFlowerType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HttpUtil$1;

public class UnidentifiedClass0849 implements Function<ItemStack, String> {
   public HttpUtil$1 field_0000;

   public String method_05744(ItemStack var1) {
      return BlockFlower$EnumFlowerType.getType(BlockFlower$EnumFlowerColor.YELLOW, var1.getMetadata()).getUnlocalizedName();
   }
}
