package net.minecraft.block;

import com.cheatbreaker.client.util.dash.DashUtil;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.enchantment.EnchantmentArrowDamage;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockCocoa$1 {
   public EnchantmentArrowDamage field_0003;
   public LayerSlimeGel field_0000;
   public DashUtil field_0002;

   static {
      try {
         field_180415_a[EnumFacing.SOUTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_180415_a[EnumFacing.NORTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_180415_a[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_180415_a[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
