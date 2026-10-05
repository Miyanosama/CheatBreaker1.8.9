package net.minecraft.enchantment;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.JsonToNBT$Any;
import net.minecraft.util.LongHashMap$Entry;

public class EnchantmentHelper$DamageIterator implements EnchantmentHelper$IModifier {
   public Entity target;
   public EntityLivingBase user;
   public LongHashMap$Entry field_0000;
   public JsonToNBT$Any field_0002;

   @Override
   public void calculateModifier(Enchantment var1, int var2) {
      var1.onEntityDamaged(this.user, this.target, var2);
   }

   public EnchantmentHelper$DamageIterator() {
   }
}
