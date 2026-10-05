package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.Block;

public class ItemMultiTexture extends ItemBlock {
   public Block theBlock;
   public Function<ItemStack, String> nameFunction;

   @Override
   public int getMetadata(int var1) {
      return var1;
   }

   public ItemMultiTexture(Block var1, Block var2, final String[] var3) {
      this(var1, var2, new Function<ItemStack, String>() {
         public String apply(ItemStack var1) {
            int var2x = var1.getMetadata();
            if (var2x < 0 || var2x >= var3.length) {
               var2x = 0;
            }

            return var3[var2x];
         }
      });
   }

   public ItemMultiTexture(Block var1, Block var2, Function<ItemStack, String> var3) {
      super(var1);
      this.theBlock = var2;
      this.nameFunction = var3;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   @Override
   public String getUnlocalizedName(ItemStack var1) {
      return super.getUnlocalizedName() + "." + this.nameFunction.apply(var1);
   }
}
