package recovered.unidentified;

import com.google.common.base.Function;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import net.minecraft.block.BlockPlanks$EnumType;
import net.minecraft.item.ItemStack;
import org.java_websocket.server.CustomSSLWebSocketServerFactory;

public class UnidentifiedClass4999 implements Function<ItemStack, String> {
   public DefaultEventExecutorGroup field_0000;
   public CustomSSLWebSocketServerFactory field_0001;

   public String method_29746(ItemStack var1) {
      return BlockPlanks$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
