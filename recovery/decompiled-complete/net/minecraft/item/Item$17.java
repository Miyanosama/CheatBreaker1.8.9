package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.BlockSandStone$EnumType;
import net.optifine.gui.TooltipManager;
import org.newsclub.net.unix.AFUNIXServerSocket;

public class Item$17 implements Function<ItemStack, String> {
   public TooltipManager field_0000;
   public AFUNIXServerSocket field_0001;

   public String apply(ItemStack var1) {
      return BlockSandStone$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
