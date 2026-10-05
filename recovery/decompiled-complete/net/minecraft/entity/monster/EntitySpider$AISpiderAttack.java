package net.minecraft.entity.monster;

import net.minecraft.client.util.JsonBlendingMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.optifine.reflect.ReflectorConstructor;

public class EntitySpider$AISpiderAttack extends EntityAIAttackOnCollide {
   public ReflectorConstructor field_0000;
   public JsonBlendingMode field_0001;

   @Override
   public double func_179512_a(EntityLivingBase var1) {
      return 4.0F + var1.J;
   }

   @Override
   public boolean continueExecuting() {
      float var1 = this.attacker.a_(1.0F);
      if (var1 >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
         this.attacker.setAttackTarget((EntityLivingBase)null);
         return false;
      } else {
         return super.continueExecuting();
      }
   }

   public EntitySpider$AISpiderAttack(EntitySpider var1, Class<? extends Entity> var2) {
      super(var1, var2, 1.0, true);
   }
}
