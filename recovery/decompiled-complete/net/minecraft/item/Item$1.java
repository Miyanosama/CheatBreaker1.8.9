package net.minecraft.item;

import com.google.common.base.Function;
import io.netty.util.internal.InternalThreadLocalMap;
import net.minecraft.block.BlockStone$EnumType;
import recovered.unidentified.UnidentifiedClass3909;

public class Item$1 implements Function<ItemStack, String> {
   public InternalThreadLocalMap field_0000;
   public UnidentifiedClass3909 field_0001;

   public String apply(ItemStack var1) {
      return BlockStone$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
