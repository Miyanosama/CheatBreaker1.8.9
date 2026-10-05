package net.minecraft.potion;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class Potion {
   public static Potion[] potionTypes = new Potion[32];
   public static Map<ResourceLocation, Potion> field_180150_I = Maps.newHashMap();
   public static Potion field_180151_b = null;
   public static Potion moveSpeed = new Potion(1, new ResourceLocation("speed"), false, 8171462)
      .setPotionName("potion.moveSpeed")
      .setIconIndex(0, 0)
      .registerPotionAttributeModifier(SharedMonsterAttributes.movementSpeed, "91AEAA56-376B-4498-935B-2F7F68070635", 0.2F, 2);
   public static Potion moveSlowdown = new Potion(2, new ResourceLocation("slowness"), true, 5926017)
      .setPotionName("potion.moveSlowdown")
      .setIconIndex(1, 0)
      .registerPotionAttributeModifier(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15F, 2);
   public String name;
   public static Potion digSpeed = new Potion(3, new ResourceLocation("haste"), false, 14270531)
      .setPotionName("potion.digSpeed")
      .setIconIndex(2, 0)
      .setEffectiveness(1.5);
   public Map<IAttribute, AttributeModifier> attributeModifierMap = Maps.newHashMap();
   public int id;
   public static Potion digSlowdown = new Potion(4, new ResourceLocation("mining_fatigue"), true, 4866583)
      .setPotionName("potion.digSlowDown")
      .setIconIndex(3, 0);
   public static Potion damageBoost = new PotionAttackDamage(5, new ResourceLocation("strength"), false, 9643043)
      .setPotionName("potion.damageBoost")
      .setIconIndex(4, 0)
      .registerPotionAttributeModifier(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.5, 2);
   public static Potion heal = new PotionHealth(6, new ResourceLocation("instant_health"), false, 16262179).setPotionName("potion.heal");
   public static Potion harm = new PotionHealth(7, new ResourceLocation("instant_damage"), true, 4393481).setPotionName("potion.harm");
   public static Potion jump = new Potion(8, new ResourceLocation("jump_boost"), false, 2293580).setPotionName("potion.jump").setIconIndex(2, 1);
   public boolean isBadEffect;
   public static Potion confusion = new Potion(9, new ResourceLocation("nausea"), true, 5578058)
      .setPotionName("potion.confusion")
      .setIconIndex(3, 1)
      .setEffectiveness(0.25);
   public static Potion regeneration = new Potion(10, new ResourceLocation("regeneration"), false, 13458603)
      .setPotionName("potion.regeneration")
      .setIconIndex(7, 0)
      .setEffectiveness(0.25);
   public static Potion resistance = new Potion(11, new ResourceLocation("resistance"), false, 10044730).setPotionName("potion.resistance").setIconIndex(6, 1);
   public static Potion fireResistance = new Potion(12, new ResourceLocation("fire_resistance"), false, 14981690)
      .setPotionName("potion.fireResistance")
      .setIconIndex(7, 1);
   public boolean usable;
   public static Potion waterBreathing = new Potion(13, new ResourceLocation("water_breathing"), false, 3035801)
      .setPotionName("potion.waterBreathing")
      .setIconIndex(0, 2);
   public static Potion invisibility = new Potion(14, new ResourceLocation("invisibility"), false, 8356754)
      .setPotionName("potion.invisibility")
      .setIconIndex(0, 1);
   public static Potion blindness = new Potion(15, new ResourceLocation("blindness"), true, 2039587)
      .setPotionName("potion.blindness")
      .setIconIndex(5, 1)
      .setEffectiveness(0.25);
   public static Potion nightVision = new Potion(16, new ResourceLocation("night_vision"), false, 2039713)
      .setPotionName("potion.nightVision")
      .setIconIndex(4, 1);
   public static Potion hunger = new Potion(17, new ResourceLocation("hunger"), true, 5797459).setPotionName("potion.hunger").setIconIndex(1, 1);
   public static Potion weakness = new PotionAttackDamage(18, new ResourceLocation("weakness"), true, 4738376)
      .setPotionName("potion.weakness")
      .setIconIndex(5, 0)
      .registerPotionAttributeModifier(SharedMonsterAttributes.attackDamage, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0, 0);
   public static Potion poison = new Potion(19, new ResourceLocation("poison"), true, 5149489)
      .setPotionName("potion.poison")
      .setIconIndex(6, 0)
      .setEffectiveness(0.25);
   public static Potion wither = new Potion(20, new ResourceLocation("wither"), true, 3484199)
      .setPotionName("potion.wither")
      .setIconIndex(1, 2)
      .setEffectiveness(0.25);
   public static Potion healthBoost = new PotionHealthBoost(21, new ResourceLocation("health_boost"), false, 16284963)
      .setPotionName("potion.healthBoost")
      .setIconIndex(2, 2)
      .registerPotionAttributeModifier(SharedMonsterAttributes.maxHealth, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0, 0);
   public int statusIconIndex;
   public static Potion absorption = new PotionAbsorption(22, new ResourceLocation("absorption"), false, 2445989)
      .setPotionName("potion.absorption")
      .setIconIndex(2, 2);
   public static Potion saturation = new PotionHealth(23, new ResourceLocation("saturation"), false, 16262179).setPotionName("potion.saturation");
   public int liquidColor;
   public static Potion field_180153_z = null;
   public static Potion field_180147_A = null;
   public static Potion field_180148_B = null;
   public static Potion field_180149_C = null;
   public static Potion field_180143_D = null;
   public static Potion field_180144_E = null;
   public static Potion field_180145_F = null;
   public double effectiveness;
   public static Potion field_180146_G = null;

   public int getId() {
      return this.id;
   }

   public int getLiquidColor() {
      return this.liquidColor;
   }

   public double getEffectiveness() {
      return this.effectiveness;
   }

   public Map<IAttribute, AttributeModifier> getAttributeModifierMap() {
      return this.attributeModifierMap;
   }

   public static Set<ResourceLocation> getPotionLocations() {
      return field_180150_I.keySet();
   }

   public boolean isInstant() {
      return false;
   }

   public String getName() {
      return this.name;
   }

   public void performEffect(EntityLivingBase var1, int var2) {
      if (this.id == regeneration.id) {
         if (var1.getHealth() < var1.getMaxHealth()) {
            var1.heal(1.0F);
         }
      } else if (this.id == poison.id) {
         if (var1.getHealth() > 1.0F) {
            var1.attackEntityFrom(DamageSource.magic, 1.0F);
         }
      } else if (this.id == wither.id) {
         var1.attackEntityFrom(DamageSource.wither, 1.0F);
      } else if (this.id == hunger.id && var1 instanceof EntityPlayer) {
         ((EntityPlayer)var1).addExhaustion(0.025F * (var2 + 1));
      } else if (this.id == saturation.id && var1 instanceof EntityPlayer) {
         if (!var1.o.D) {
            ((EntityPlayer)var1).getFoodStats().addStats(var2 + 1, 1.0F);
         }
      } else if ((this.id != heal.id || var1.isEntityUndead()) && (this.id != harm.id || !var1.isEntityUndead())) {
         if (this.id == harm.id && !var1.isEntityUndead() || this.id == heal.id && var1.isEntityUndead()) {
            var1.attackEntityFrom(DamageSource.magic, 6 << var2);
         }
      } else {
         var1.heal(Math.max(4 << var2, 0));
      }
   }

   public Potion setEffectiveness(double var1) {
      this.effectiveness = var1;
      return this;
   }

   public boolean isBadEffect() {
      return this.isBadEffect;
   }

   public Potion setIconIndex(int var1, int var2) {
      this.statusIconIndex = var1 + var2 * 8;
      return this;
   }

   public Potion registerPotionAttributeModifier(IAttribute var1, String var2, double var3, int var5) {
      AttributeModifier var6 = new AttributeModifier(UUID.fromString(var2), this.getName(), var3, var5);
      this.attributeModifierMap.put(var1, var6);
      return this;
   }

   public boolean hasStatusIcon() {
      return this.statusIconIndex >= 0;
   }

   public void removeAttributesModifiersFromEntity(EntityLivingBase var1, BaseAttributeMap var2, int var3) {
      for (Entry var5 : this.attributeModifierMap.entrySet()) {
         IAttributeInstance var6 = var2.getAttributeInstance((IAttribute)var5.getKey());
         if (var6 != null) {
            var6.removeModifier((AttributeModifier)var5.getValue());
         }
      }
   }

   public static Potion getPotionFromResourceLocation(String var0) {
      return field_180150_I.get(new ResourceLocation(var0));
   }

   public void affectEntity(Entity var1, Entity var2, EntityLivingBase var3, int var4, double var5) {
      if ((this.id != heal.id || var3.isEntityUndead()) && (this.id != harm.id || !var3.isEntityUndead())) {
         if (this.id == harm.id && !var3.isEntityUndead() || this.id == heal.id && var3.isEntityUndead()) {
            int var8 = (int)(var5 * (6 << var4) + 0.5);
            if (var1 == null) {
               var3.attackEntityFrom(DamageSource.magic, var8);
            } else {
               var3.attackEntityFrom(DamageSource.causeIndirectMagicDamage(var1, var2), var8);
            }
         }
      } else {
         int var7 = (int)(var5 * (4 << var4) + 0.5);
         var3.heal(var7);
      }
   }

   public double getAttributeModifierAmount(int var1, AttributeModifier var2) {
      return var2.getAmount() * (var1 + 1);
   }

   public boolean isReady(int var1, int var2) {
      if (this.id == regeneration.id) {
         int var5 = 50 >> var2;
         return var5 > 0 ? var1 % var5 == 0 : true;
      } else if (this.id == poison.id) {
         int var4 = 25 >> var2;
         return var4 > 0 ? var1 % var4 == 0 : true;
      } else if (this.id == wither.id) {
         int var3 = 40 >> var2;
         return var3 > 0 ? var1 % var3 == 0 : true;
      } else {
         return this.id == hunger.id;
      }
   }

   public static String getDurationString(PotionEffect var0) {
      if (var0.getIsPotionDurationMax()) {
         return "**:**";
      } else {
         int var1 = var0.getDuration();
         return StringUtils.ticksToElapsedTime(var1);
      }
   }

   public void applyAttributesModifiersToEntity(EntityLivingBase var1, BaseAttributeMap var2, int var3) {
      for (Entry var5 : this.attributeModifierMap.entrySet()) {
         IAttributeInstance var6 = var2.getAttributeInstance((IAttribute)var5.getKey());
         if (var6 != null) {
            AttributeModifier var7 = (AttributeModifier)var5.getValue();
            var6.removeModifier(var7);
            var6.applyModifier(
               new AttributeModifier(var7.getID(), this.getName() + " " + var3, this.getAttributeModifierAmount(var3, var7), var7.getOperation())
            );
         }
      }
   }

   public boolean isUsable() {
      return this.usable;
   }

   public Potion(int var1, ResourceLocation var2, boolean var3, int var4) {
      this.name = "";
      this.statusIconIndex = -1;
      this.id = var1;
      potionTypes[var1] = this;
      field_180150_I.put(var2, this);
      this.isBadEffect = var3;
      if (var3) {
         this.effectiveness = 0.5;
      } else {
         this.effectiveness = 1.0;
      }

      this.liquidColor = var4;
   }

   public Potion setPotionName(String var1) {
      this.name = var1;
      return this;
   }

   public int getStatusIconIndex() {
      return this.statusIconIndex;
   }
}
