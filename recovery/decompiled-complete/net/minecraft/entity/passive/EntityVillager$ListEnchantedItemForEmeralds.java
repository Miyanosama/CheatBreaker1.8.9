package net.minecraft.entity.passive;

import com.cheatbreaker.client.ui.module.CBAnchorHelper;
import java.util.Random;
import net.minecraft.client.renderer.entity.RenderGiantZombie;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class EntityVillager$ListEnchantedItemForEmeralds implements EntityVillager$ITradeList {
   public EntityVillager$PriceInfo priceInfo;
   public RenderGiantZombie field_0003;
   public CBAnchorHelper field_0000;
   public ItemStack enchantedItemStack;

   @Override
   public void modifyMerchantRecipeList(MerchantRecipeList var1, Random var2) {
      int var3 = 1;
      if (this.priceInfo != null) {
         var3 = this.priceInfo.getPrice(var2);
      }

      ItemStack var4 = new ItemStack(Items.emerald, var3, 0);
      ItemStack var5 = new ItemStack(this.enchantedItemStack.getItem(), 1, this.enchantedItemStack.getMetadata());
      var5 = EnchantmentHelper.addRandomEnchantment(var2, var5, 5 + var2.nextInt(15));
      var1.add(new MerchantRecipe(var4, var5));
   }

   public EntityVillager$ListEnchantedItemForEmeralds(Item var1, EntityVillager$PriceInfo var2) {
      this.enchantedItemStack = new ItemStack(var1);
      this.priceInfo = var2;
   }
}
