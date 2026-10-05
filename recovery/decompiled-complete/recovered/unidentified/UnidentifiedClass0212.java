package recovered.unidentified;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import org.slf4j.helpers.SubstituteLoggerFactory;

public class UnidentifiedClass0212 {
   public SubstituteLoggerFactory field_0000;

   public static boolean method_01544(ItemStack var0) {
      return var0 != null && var0.getItem() instanceof ItemFood;
   }

   public static UnidentifiedClass0002 method_01545(ItemStack var0, EntityPlayer var1) {
      return method_01546(var0);
   }

   public static UnidentifiedClass0002 method_01546(ItemStack var0) {
      ItemFood var1 = (ItemFood)var0.getItem();
      int var2 = var1.getHealAmount(var0);
      float var3 = var1.getSaturationModifier(var0);
      return new UnidentifiedClass0002(var2, var3);
   }
}
