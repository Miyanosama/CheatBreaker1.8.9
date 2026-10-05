package net.minecraft.entity.monster;

import java.util.Random;
import javax.vecmath.Tuple3f;
import net.minecraft.client.renderer.GlStateManager$ColorMask;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.potion.Potion;

public class EntitySpider$GroupData implements IEntityLivingData {
   public Tuple3f field_0001;
   public EnchantmentData field_0003;
   public GlStateManager$ColorMask field_0000;
   public int potionEffectId;

   public void func_111104_a(Random var1) {
      int var2 = var1.nextInt(5);
      if (var2 <= 1) {
         this.potionEffectId = Potion.moveSpeed.id;
      } else if (var2 <= 2) {
         this.potionEffectId = Potion.damageBoost.id;
      } else if (var2 <= 3) {
         this.potionEffectId = Potion.regeneration.id;
      } else if (var2 <= 4) {
         this.potionEffectId = Potion.invisibility.id;
      }
   }
}
