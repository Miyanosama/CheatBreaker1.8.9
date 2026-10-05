package net.minecraft.item;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFishFood extends ItemFood {
   public boolean cooked;

   @Override
   public void getSubItems(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (ItemFishFood.FishType var7 : ItemFishFood.FishType.values()) {
         if (!this.cooked || var7.canCook()) {
            var3.add(new ItemStack(this, 1, var7.getMetadata()));
         }
      }
   }

   @Override
   public float getSaturationModifier(ItemStack var1) {
      ItemFishFood.FishType var2 = ItemFishFood.FishType.byItemStack(var1);
      return this.cooked && var2.canCook() ? var2.getCookedSaturationModifier() : var2.getUncookedSaturationModifier();
   }

   @Override
   public int getHealAmount(ItemStack var1) {
      ItemFishFood.FishType var2 = ItemFishFood.FishType.byItemStack(var1);
      return this.cooked && var2.canCook() ? var2.getCookedHealAmount() : var2.getUncookedHealAmount();
   }

   @Override
   public String getUnlocalizedName(ItemStack var1) {
      ItemFishFood.FishType var2 = ItemFishFood.FishType.byItemStack(var1);
      return this.getUnlocalizedName() + "." + var2.getUnlocalizedName() + "." + (this.cooked && var2.canCook() ? "cooked" : "raw");
   }

   public ItemFishFood(boolean var1) {
      super(0, 0.0F, false);
      this.cooked = var1;
   }

   @Override
   public void onFoodEaten(ItemStack var1, World var2, EntityPlayer var3) {
      ItemFishFood.FishType var4 = ItemFishFood.FishType.byItemStack(var1);
      if (var4 == ItemFishFood.FishType.PUFFERFISH) {
         var3.c(new PotionEffect(Potion.poison.id, 1200, 3));
         var3.c(new PotionEffect(Potion.hunger.id, 300, 2));
         var3.c(new PotionEffect(Potion.confusion.id, 300, 1));
      }

      super.onFoodEaten(var1, var2, var3);
   }

   @Override
   public String getPotionEffect(ItemStack var1) {
      return ItemFishFood.FishType.byItemStack(var1) == ItemFishFood.FishType.PUFFERFISH ? "+0-1+2+3+13&4-4" : null;
   }

   public static enum FishType {
      COD(0, "cod", 2, 0.1F, 5, 0.6F),
      SALMON(1, "salmon", 2, 0.1F, 6, 0.8F),
      CLOWNFISH(2, "clownfish", 1, 0.1F),
      PUFFERFISH(3, "pufferfish", 1, 0.1F);

      public float uncookedSaturationModifier;
      // $VF: synthetic field
      public static ItemFishFood.FishType[] $VALUES = new ItemFishFood.FishType[]{
         ItemFishFood.FishType.COD, ItemFishFood.FishType.SALMON, ItemFishFood.FishType.CLOWNFISH, PUFFERFISH
      };
      public static Map<Integer, ItemFishFood.FishType> META_LOOKUP = Maps.newHashMap();
      public int meta;
      public int uncookedHealAmount;
      public int cookedHealAmount;
      public boolean cookable = false;
      public float cookedSaturationModifier;
      public String unlocalizedName;

      public int getCookedHealAmount() {
         return this.cookedHealAmount;
      }

      FishType(int var3, String var4, int var5, float var6) {
         this.meta = var3;
         this.unlocalizedName = var4;
         this.uncookedHealAmount = var5;
         this.uncookedSaturationModifier = var6;
         this.cookedHealAmount = 0;
         this.cookedSaturationModifier = 0.0F;
         this.cookable = false;
      }

      public float getCookedSaturationModifier() {
         return this.cookedSaturationModifier;
      }

      static {
         for (ItemFishFood.FishType var3 : values()) {
            META_LOOKUP.put(var3.getMetadata(), var3);
         }
      }

      FishType(int var3, String var4, int var5, float var6, int var7, float var8) {
         this.meta = var3;
         this.unlocalizedName = var4;
         this.uncookedHealAmount = var5;
         this.uncookedSaturationModifier = var6;
         this.cookedHealAmount = var7;
         this.cookedSaturationModifier = var8;
         this.cookable = true;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      public static ItemFishFood.FishType byMetadata(int var0) {
         ItemFishFood.FishType var1 = META_LOOKUP.get(var0);
         return var1 == null ? COD : var1;
      }

      public float getUncookedSaturationModifier() {
         return this.uncookedSaturationModifier;
      }

      public boolean canCook() {
         return this.cookable;
      }

      public static ItemFishFood.FishType byItemStack(ItemStack var0) {
         return var0.getItem() instanceof ItemFishFood ? byMetadata(var0.getMetadata()) : COD;
      }

      public int getUncookedHealAmount() {
         return this.uncookedHealAmount;
      }

      public int getMetadata() {
         return this.meta;
      }
   }
}
