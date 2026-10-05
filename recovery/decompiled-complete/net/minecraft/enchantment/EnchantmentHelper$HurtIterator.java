package net.minecraft.enchantment;

import net.minecraft.client.main.llIlllIIlllIIllIIlllIlIII;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class EnchantmentHelper$HurtIterator implements EnchantmentHelper$IModifier {
   public Entity attacker;
   public llIlllIIlllIIllIIlllIlIII field_0002;
   public EntityLivingBase user;

   public EnchantmentHelper$HurtIterator() {
   }

   @Override
   public void calculateModifier(Enchantment var1, int var2) {
      var1.onUserHurt(this.user, this.attacker, var2);
   }
}
