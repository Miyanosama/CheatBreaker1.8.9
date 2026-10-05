package net.minecraft.enchantment;

import com.cheatbreaker.client.module.type.EnchantmentGlintModule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import recovered.unidentified.UnidentifiedClass4657;

public abstract class Enchantment {
   public static Enchantment field_0019 = new EnchantmentWaterWalker(8, new ResourceLocation("depth_strider"), 2);
   public static Enchantment u = new EnchantmentLootBonus(35, new ResourceLocation("fortune"), 2, EnumEnchantmentType.DIGGER);
   public static Enchantment field_0018 = new EnchantmentDigging(32, new ResourceLocation("efficiency"), 10);
   public static Enchantment field_0029 = new EnchantmentProtection(4, new ResourceLocation("projectile_protection"), 5, 4);
   public RenderBlaze field_0006;
   public static Enchantment field_0008 = new EnchantmentProtection(2, new ResourceLocation("feather_falling"), 5, 2);
   public static Enchantment field_0033 = new EnchantmentDamage(17, new ResourceLocation("smite"), 5, 1);
   public static Enchantment field_0025 = new EnchantmentProtection(0, new ResourceLocation("protection"), 10, 0);
   public static Enchantment field_0009 = new EnchantmentLootBonus(61, new ResourceLocation("luck_of_the_sea"), 2, EnumEnchantmentType.FISHING_ROD);
   public static Enchantment aquaAffinity = new EnchantmentWaterWorker(6, new ResourceLocation("aqua_affinity"), 2);
   public static Enchantment blastProtection = new EnchantmentProtection(3, new ResourceLocation("blast_protection"), 2, 3);
   public static Enchantment infinity = new EnchantmentArrowInfinite(51, new ResourceLocation("infinity"), 1);
   public UnidentifiedClass4657 field_0023;
   public EnumEnchantmentType type;
   public ShaderLinkHelper field_0027;
   public static Enchantment power = new EnchantmentArrowDamage(48, new ResourceLocation("power"), 10);
   public static Map<ResourceLocation, Enchantment> locationEnchantments = Maps.newHashMap();
   public static Enchantment silkTouch = new EnchantmentUntouching(33, new ResourceLocation("silk_touch"), 1);
   public static Enchantment field_0021 = new EnchantmentFishingSpeed(62, new ResourceLocation("lure"), 2, EnumEnchantmentType.FISHING_ROD);
   public EnchantmentGlintModule field_0030;
   public static Enchantment field_0002 = new EnchantmentOxygen(5, new ResourceLocation("respiration"), 2);
   public static Enchantment unbreaking = new EnchantmentDurability(34, new ResourceLocation("unbreaking"), 5);
   public static Enchantment[] enchantmentsBookList;
   public static Enchantment field_0000 = new EnchantmentLootBonus(21, new ResourceLocation("looting"), 2, EnumEnchantmentType.WEAPON);
   public int effectId;
   public static Enchantment flame = new EnchantmentArrowFire(50, new ResourceLocation("flame"), 2);
   public static Enchantment field_0026 = new EnchantmentKnockback(19, new ResourceLocation("knockback"), 5);
   public static Enchantment[] enchantmentsList = new Enchantment[256];
   public int weight;
   public static Enchantment punch = new EnchantmentArrowKnockback(49, new ResourceLocation("punch"), 2);
   public String name;
   public static Enchantment field_0007 = new EnchantmentFireAspect(20, new ResourceLocation("fire_aspect"), 2);
   public static Enchantment field_0016 = new EnchantmentProtection(1, new ResourceLocation("fire_protection"), 5, 1);
   public static Enchantment field_0012 = new EnchantmentDamage(18, new ResourceLocation("bane_of_arthropods"), 5, 2);
   public static Enchantment field_0011 = new EnchantmentDamage(16, new ResourceLocation("sharpness"), 10, 0);
   public static Enchantment thorns = new EnchantmentThorns(7, new ResourceLocation("thorns"), 1);

   public void onEntityDamaged(EntityLivingBase var1, Entity var2, int var3) {
   }

   public static Set<ResourceLocation> func_181077_c() {
      return locationEnchantments.keySet();
   }

   static {
      ArrayList var0 = Lists.newArrayList();

      for (Enchantment var4 : enchantmentsList) {
         if (var4 != null) {
            var0.add(var4);
         }
      }

      enchantmentsBookList = var0.toArray(new Enchantment[var0.size()]);
   }

   public boolean canApplyTogether(Enchantment var1) {
      return this != var1;
   }

   public int calcModifierDamage(int var1, DamageSource var2) {
      return 0;
   }

   public int getMaxLevel() {
      return 1;
   }

   public String getTranslatedName(int var1) {
      String var2 = StatCollector.translateToLocal(this.getName());
      return var2 + " " + StatCollector.translateToLocal("enchantment.level." + var1);
   }

   public static Enchantment getEnchantmentById(int var0) {
      return var0 >= 0 && var0 < enchantmentsList.length ? enchantmentsList[var0] : null;
   }

   public int getMinLevel() {
      return 1;
   }

   public int getMaxEnchantability(int var1) {
      return this.getMinEnchantability(var1) + 5;
   }

   public int getMinEnchantability(int var1) {
      return 1 + var1 * 10;
   }

   public float calcDamageByCreature(int var1, EnumCreatureAttribute var2) {
      return 0.0F;
   }

   public Enchantment setName(String var1) {
      this.name = var1;
      return this;
   }

   public Enchantment(int var1, ResourceLocation var2, int var3, EnumEnchantmentType var4) {
      this.effectId = var1;
      this.weight = var3;
      this.type = var4;
      if (enchantmentsList[var1] != null) {
         throw new IllegalArgumentException("Duplicate enchantment id!");
      } else {
         enchantmentsList[var1] = this;
         locationEnchantments.put(var2, this);
      }
   }

   public int getWeight() {
      return this.weight;
   }

   public boolean canApply(ItemStack var1) {
      return this.type.canEnchantItem(var1.getItem());
   }

   public static Enchantment getEnchantmentByLocation(String var0) {
      return locationEnchantments.get(new ResourceLocation(var0));
   }

   public String getName() {
      return "enchantment." + this.name;
   }

   public void onUserHurt(EntityLivingBase var1, Entity var2, int var3) {
   }
}
