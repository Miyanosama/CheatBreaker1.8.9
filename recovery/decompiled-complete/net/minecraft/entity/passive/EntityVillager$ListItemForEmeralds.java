package net.minecraft.entity.passive;

import io.netty.handler.codec.rtsp.RtspResponseEncoder;
import java.util.Random;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class EntityVillager$ListItemForEmeralds implements EntityVillager$ITradeList {
   public EntityVillager$PriceInfo priceInfo;
   public ItemStack itemToBuy;
   public RtspResponseEncoder field_0000;

   @Override
   public void modifyMerchantRecipeList(MerchantRecipeList var1, Random var2) {
      int var3 = 1;
      if (this.priceInfo != null) {
         var3 = this.priceInfo.getPrice(var2);
      }

      ItemStack var4;
      ItemStack var5;
      if (var3 < 0) {
         var4 = new ItemStack(Items.emerald, 1, 0);
         var5 = new ItemStack(this.itemToBuy.getItem(), -var3, this.itemToBuy.getMetadata());
      } else {
         var4 = new ItemStack(Items.emerald, var3, 0);
         var5 = new ItemStack(this.itemToBuy.getItem(), 1, this.itemToBuy.getMetadata());
      }

      var1.add(new MerchantRecipe(var4, var5));
   }

   public EntityVillager$ListItemForEmeralds(Item var1, EntityVillager$PriceInfo var2) {
      this.itemToBuy = new ItemStack(var1);
      this.priceInfo = var2;
   }

   public EntityVillager$ListItemForEmeralds(ItemStack var1, EntityVillager$PriceInfo var2) {
      this.itemToBuy = var1;
      this.priceInfo = var2;
   }
}
