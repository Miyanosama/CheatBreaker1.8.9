package net.minecraft.enchantment;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.net.SocketHubAppender$ServerMonitor;
import recovered.unidentified.UnidentifiedClass0201;

public class EnchantmentLootBonus extends Enchantment {
   public ModelCreeper field_0000;
   public UnidentifiedClass0201 field_0003;
   public SocketHubAppender$ServerMonitor field_0001;
   public ItemSlab field_0002;

   @Override
   public int getMaxLevel() {
      return 3;
   }

   @Override
   public boolean canApplyTogether(Enchantment var1) {
      return super.canApplyTogether(var1) && var1.effectId != silkTouch.effectId;
   }

   public EnchantmentLootBonus(int var1, ResourceLocation var2, int var3, EnumEnchantmentType var4) {
      super(var1, var2, var3, var4);
      if (var4 == EnumEnchantmentType.DIGGER) {
         this.setName("lootBonusDigger");
      } else if (var4 == EnumEnchantmentType.FISHING_ROD) {
         this.setName("lootBonusFishing");
      } else {
         this.setName("lootBonus");
      }
   }

   @Override
   public int getMinEnchantability(int var1) {
      return 15 + (var1 - 1) * 9;
   }

   @Override
   public int getMaxEnchantability(int var1) {
      return super.getMinEnchantability(var1) + 50;
   }
}
