package net.minecraft.util;

import net.minecraft.entity.EntityLivingBase;

public class CombatEntry {
   public float fallDistance;
   public float damage;
   public float health;
   public DamageSource damageSrc;
   public int field_94567_b;
   public String field_94566_e;

   public float getDamageAmount() {
      return this.damageSrc == DamageSource.outOfWorld ? Float.MAX_VALUE : this.fallDistance;
   }

   public String func_94562_g() {
      return this.field_94566_e;
   }

   public float func_94563_c() {
      return this.damage;
   }

   public DamageSource getDamageSrc() {
      return this.damageSrc;
   }

   public boolean isLivingDamageSrc() {
      return this.damageSrc.getEntity() instanceof EntityLivingBase;
   }

   public IChatComponent getDamageSrcDisplayName() {
      return this.getDamageSrc().getEntity() == null ? null : this.getDamageSrc().getEntity().getDisplayName();
   }

   public CombatEntry(DamageSource var1, int var2, float var3, float var4, String var5, float var6) {
      this.damageSrc = var1;
      this.field_94567_b = var2;
      this.damage = var4;
      this.health = var3;
      this.field_94566_e = var5;
      this.fallDistance = var6;
   }
}
