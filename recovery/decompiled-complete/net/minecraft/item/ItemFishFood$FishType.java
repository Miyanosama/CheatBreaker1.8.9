package net.minecraft.item;

import com.cheatbreaker.client.ui.mainmenu.CosmeticsMenu;
import com.google.common.collect.Maps;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$Node;
import java.util.Map;

public enum ItemFishFood$FishType {
   PUFFERFISH(3, "pufferfish", 1, 0.1F),
   SALMON(1, "salmon", 2, 0.1F, 6, 0.8F),
   COD(0, "cod", 2, 0.1F, 5, 0.6F),
   CLOWNFISH(2, "clownfish", 1, 0.1F);

   public float uncookedSaturationModifier;
   // $VF: synthetic field
   public static ItemFishFood$FishType[] $VALUES = new ItemFishFood$FishType[]{
      ItemFishFood$FishType.COD, ItemFishFood$FishType.SALMON, ItemFishFood$FishType.CLOWNFISH, PUFFERFISH
   };
   public ConcurrentHashMapV8$Node field_0001;
   public CosmeticsMenu field_0013;
   public static Map<Integer, ItemFishFood$FishType> META_LOOKUP = Maps.newHashMap();
   public int meta;
   public int uncookedHealAmount;
   public int cookedHealAmount;
   public boolean cookable = false;
   public float cookedSaturationModifier;
   public String unlocalizedName;

   public int getCookedHealAmount() {
      return this.cookedHealAmount;
   }

   public ItemFishFood$FishType(int var3, String var4, int var5, float var6) {
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
      for (ItemFishFood$FishType var3 : values()) {
         META_LOOKUP.put(var3.getMetadata(), var3);
      }
   }

   public ItemFishFood$FishType(int var3, String var4, int var5, float var6, int var7, float var8) {
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

   public static ItemFishFood$FishType byMetadata(int var0) {
      ItemFishFood$FishType var1 = META_LOOKUP.get(var0);
      return var1 == null ? COD : var1;
   }

   public float getUncookedSaturationModifier() {
      return this.uncookedSaturationModifier;
   }

   public boolean canCook() {
      return this.cookable;
   }

   public static ItemFishFood$FishType byItemStack(ItemStack var0) {
      return var0.getItem() instanceof ItemFishFood ? byMetadata(var0.getMetadata()) : COD;
   }

   public int getUncookedHealAmount() {
      return this.uncookedHealAmount;
   }

   public int getMetadata() {
      return this.meta;
   }
}
