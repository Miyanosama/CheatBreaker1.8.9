package net.minecraft.entity.passive;

import java.util.Random;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.command.CommandWeather;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.optifine.gui.GuiOtherSettingsOF;

public class EntityVillager$EmeraldForItems implements EntityVillager$ITradeList {
   public EntityVillager$PriceInfo price;
   public CommandWeather field_0005;
   public EntityMinecartMobSpawner field_0002;
   public TextureAtlasSprite field_0004;
   public Item sellItem;
   public GuiOtherSettingsOF field_0001;

   @Override
   public void modifyMerchantRecipeList(MerchantRecipeList var1, Random var2) {
      int var3 = 1;
      if (this.price != null) {
         var3 = this.price.getPrice(var2);
      }

      var1.add(new MerchantRecipe(new ItemStack(this.sellItem, var3, 0), Items.emerald));
   }

   public EntityVillager$EmeraldForItems(Item var1, EntityVillager$PriceInfo var2) {
      this.sellItem = var1;
      this.price = var2;
   }
}
