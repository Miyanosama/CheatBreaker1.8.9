package recovered.unidentified;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.EnumAction;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.gen.layer.IntCache;

// $VF: synthetic class
public class UnidentifiedClass4776 {
   public InventoryBasic field_0001;
   public ChatComponentTranslation field_0003;
   public IntCache field_0000;

   static {
      try {
         field_0002[EnumAction.EAT.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_0002[EnumAction.DRINK.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0002[EnumAction.BLOCK.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0002[EnumAction.BOW.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0002[EnumAction.NONE.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
