package recovered.unidentified;

import com.google.common.base.Function;
import io.netty.handler.codec.socks.SocksInitResponseDecoder;
import net.minecraft.block.BlockPlanks$EnumType;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MessageDeserializer;
import net.optifine.entity.model.ModelAdapterBat;

public class UnidentifiedClass0753 implements Function<ItemStack, String> {
   public UnidentifiedClass0546 field_0003;
   public SocksInitResponseDecoder field_0005;
   public UnidentifiedClass3246 field_0002;
   public UnidentifiedClass1092 field_0004;
   public MessageDeserializer field_0000;
   public ModelAdapterBat field_0001;
   public ItemDye field_0006;

   public String method_05199(ItemStack var1) {
      return BlockPlanks$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
