package net.minecraft.entity.passive;

import java.util.Random;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class EntityVillager$ItemAndEmeraldToItem implements EntityVillager$ITradeList {
   public EntityAILookAtTradePlayer field_0003;
   public ItemStack buyingItemStack;
   public EntityVillager$PriceInfo buyingPriceInfo;
   public ServerConfigurationManager field_0004;
   public EntityVillager$PriceInfo field_179408_d;
   public ItemStack sellingItemstack;

   public EntityVillager$ItemAndEmeraldToItem(Item var1, EntityVillager$PriceInfo var2, Item var3, EntityVillager$PriceInfo var4) {
      this.buyingItemStack = new ItemStack(var1);
      this.buyingPriceInfo = var2;
      this.sellingItemstack = new ItemStack(var3);
      this.field_179408_d = var4;
   }

   @Override
   public void modifyMerchantRecipeList(MerchantRecipeList var1, Random var2) {
      int var3 = 1;
      if (this.buyingPriceInfo != null) {
         var3 = this.buyingPriceInfo.getPrice(var2);
      }

      int var4 = 1;
      if (this.field_179408_d != null) {
         var4 = this.field_179408_d.getPrice(var2);
      }

      var1.add(
         new MerchantRecipe(
            new ItemStack(this.buyingItemStack.getItem(), var3, this.buyingItemStack.getMetadata()),
            new ItemStack(Items.emerald),
            new ItemStack(this.sellingItemstack.getItem(), var4, this.sellingItemstack.getMetadata())
         )
      );
   }
}
