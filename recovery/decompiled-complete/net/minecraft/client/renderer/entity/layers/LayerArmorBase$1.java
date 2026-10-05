package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.resources.SkinManager;
import net.minecraft.item.ItemArmor$ArmorMaterial;

// $VF: synthetic class
public class LayerArmorBase$1 {
   public SkinManager field_0001;

   static {
      try {
         field_0000[ItemArmor$ArmorMaterial.LEATHER.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_0000[ItemArmor$ArmorMaterial.CHAIN.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0000[ItemArmor$ArmorMaterial.IRON.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0000[ItemArmor$ArmorMaterial.GOLD.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0000[ItemArmor$ArmorMaterial.DIAMOND.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
