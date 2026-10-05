package net.minecraft.enchantment;

import io.netty.handler.stream.ChunkedWriteHandler$4;
import net.minecraft.entity.EnumCreatureAttribute;
import org.apache.log4j.pattern.ThreadPatternConverter;

public class EnchantmentHelper$ModifierLiving implements EnchantmentHelper$IModifier {
   public float livingModifier;
   public ThreadPatternConverter field_0003;
   public ChunkedWriteHandler$4 field_0000;
   public EnumCreatureAttribute entityLiving;

   @Override
   public void calculateModifier(Enchantment var1, int var2) {
      this.livingModifier = this.livingModifier + var1.calcDamageByCreature(var2, this.entityLiving);
   }

   public EnchantmentHelper$ModifierLiving() {
   }
}
