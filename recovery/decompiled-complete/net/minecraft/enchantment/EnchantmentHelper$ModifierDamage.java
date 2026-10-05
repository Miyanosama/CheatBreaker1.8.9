package net.minecraft.enchantment;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.DamageSource;
import net.optifine.entity.model.ModelAdapterSheep;
import recovered.unidentified.UnidentifiedClass0599;

public class EnchantmentHelper$ModifierDamage implements EnchantmentHelper$IModifier {
   public UnidentifiedClass0599 field_0002;
   public ModelAdapterSheep field_0004;
   public ServerData field_0001;
   public int damageModifier;
   public DamageSource source;

   @Override
   public void calculateModifier(Enchantment var1, int var2) {
      this.damageModifier = this.damageModifier + var1.calcModifierDamage(var2, this.source);
   }

   public EnchantmentHelper$ModifierDamage() {
   }
}
