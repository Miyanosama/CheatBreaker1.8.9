package recovered.unidentified;

import com.google.common.base.Function;
import io.netty.handler.codec.http.cors.CorsConfig$Builder;
import net.minecraft.block.BlockPlanks$EnumType;
import net.minecraft.item.ItemStack;
import net.optifine.shaders.config.ShaderOptionScreen;

public class UnidentifiedClass4363 implements Function<ItemStack, String> {
   public ShaderOptionScreen field_0000;
   public CorsConfig$Builder field_0001;

   public String method_26368(ItemStack var1) {
      return BlockPlanks$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
