package net.minecraft.item;

import net.minecraft.init.Items;

public enum ItemArmor$ArmorMaterial {
   CHAIN("chainmail", 15, new int[]{2, 5, 4, 1}, 12),
   DIAMOND("diamond", 33, new int[]{3, 8, 6, 3}, 10),
   GOLD("gold", 7, new int[]{2, 5, 3, 1}, 25),
   IRON("iron", 15, new int[]{2, 6, 5, 2}, 9),
   LEATHER("leather", 5, new int[]{1, 3, 2, 1}, 15);

   // $VF: synthetic field
   public static ItemArmor$ArmorMaterial[] $VALUES = new ItemArmor$ArmorMaterial[]{
      ItemArmor$ArmorMaterial.LEATHER,
      ItemArmor$ArmorMaterial.CHAIN,
      ItemArmor$ArmorMaterial.IRON,
      ItemArmor$ArmorMaterial.GOLD,
      ItemArmor$ArmorMaterial.DIAMOND
   };
   public int maxDamageFactor;
   public int enchantability;
   public String name;
   public int[] damageReductionAmountArray;

   public int getDurability(int var1) {
      return ItemArmor.access$000()[var1] * this.maxDamageFactor;
   }

   public Item getRepairItem() {
      return this == LEATHER
         ? Items.leather
         : (this == CHAIN ? Items.iron_ingot : (this == GOLD ? Items.gold_ingot : (this == IRON ? Items.iron_ingot : (this == DIAMOND ? Items.diamond : null))));
   }

   public ItemArmor$ArmorMaterial(String var3, int var4, int[] var5, int var6) {
      this.name = var3;
      this.maxDamageFactor = var4;
      this.damageReductionAmountArray = var5;
      this.enchantability = var6;
   }

   public int getEnchantability() {
      return this.enchantability;
   }

   public int getDamageReductionAmount(int var1) {
      return this.damageReductionAmountArray[var1];
   }

   public String getName() {
      return this.name;
   }
}
