package net.minecraft.item;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchMappingsTask;
import net.minecraft.block.Block;

public class ItemCloth extends ItemBlock {
   public ConcurrentHashMapV8$SearchMappingsTask field_0000;

   @Override
   public int getMetadata(int var1) {
      return var1;
   }

   @Override
   public String getUnlocalizedName(ItemStack var1) {
      return super.getUnlocalizedName() + "." + EnumDyeColor.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }

   public ItemCloth(Block var1) {
      super(var1);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }
}
