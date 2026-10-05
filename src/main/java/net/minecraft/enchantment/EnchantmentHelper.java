package net.minecraft.enchantment;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.WeightedRandom;

public class EnchantmentHelper {
   public static Random enchantmentRand = new Random();
   public static EnchantmentHelper.ModifierDamage enchantmentModifierDamage = new EnchantmentHelper.ModifierDamage();
   public static EnchantmentHelper.ModifierLiving enchantmentModifierLiving = new EnchantmentHelper.ModifierLiving();
   public static EnchantmentHelper.HurtIterator ENCHANTMENT_ITERATOR_HURT = new EnchantmentHelper.HurtIterator();
   public static EnchantmentHelper.DamageIterator ENCHANTMENT_ITERATOR_DAMAGE = new EnchantmentHelper.DamageIterator();

   public static float getModifierForCreature(ItemStack var0, EnumCreatureAttribute var1) {
      enchantmentModifierLiving.livingModifier = 0.0F;
      enchantmentModifierLiving.entityLiving = var1;
      applyEnchantmentModifier(enchantmentModifierLiving, var0);
      return enchantmentModifierLiving.livingModifier;
   }

   public static void setEnchantments(Map<Integer, Integer> var0, ItemStack var1) {
      NBTTagList var2 = new NBTTagList();

      for (int var4 : var0.keySet()) {
         Enchantment var5 = Enchantment.getEnchantmentById(var4);
         if (var5 != null) {
            NBTTagCompound var6 = new NBTTagCompound();
            var6.setShort("id", (short)var4);
            var6.setShort("lvl", (short)((Integer)var0.get(var4)).intValue());
            var2.appendTag(var6);
            if (var1.getItem() == Items.enchanted_book) {
               Items.enchanted_book.addEnchantment(var1, new EnchantmentData(var5, (Integer)var0.get(var4)));
            }
         }
      }

      if (var2.tagCount() > 0) {
         if (var1.getItem() != Items.enchanted_book) {
            var1.setTagInfo("ench", var2);
         }
      } else if (var1.hasTagCompound()) {
         var1.getTagCompound().removeTag("ench");
      }
   }

   public static Map<Integer, EnchantmentData> mapEnchantmentData(int var0, ItemStack var1) {
      Item var2 = var1.getItem();
      HashMap var3 = null;
      boolean var4 = var1.getItem() == Items.book;

      for (Enchantment var8 : Enchantment.enchantmentsBookList) {
         if (var8 != null && (var8.type.canEnchantItem(var2) || var4)) {
            for (int var9 = var8.getMinLevel(); var9 <= var8.getMaxLevel(); var9++) {
               if (var0 >= var8.getMinEnchantability(var9) && var0 <= var8.getMaxEnchantability(var9)) {
                  if (var3 == null) {
                     var3 = Maps.newHashMap();
                  }

                  var3.put(var8.effectId, new EnchantmentData(var8, var9));
               }
            }
         }
      }

      return var3;
   }

   public static int getEnchantmentModifierDamage(ItemStack[] var0, DamageSource var1) {
      enchantmentModifierDamage.damageModifier = 0;
      enchantmentModifierDamage.source = var1;
      applyEnchantmentModifierArray(enchantmentModifierDamage, var0);
      if (enchantmentModifierDamage.damageModifier > 25) {
         enchantmentModifierDamage.damageModifier = 25;
      } else if (enchantmentModifierDamage.damageModifier < 0) {
         enchantmentModifierDamage.damageModifier = 0;
      }

      return (enchantmentModifierDamage.damageModifier + 1 >> 1) + enchantmentRand.nextInt((enchantmentModifierDamage.damageModifier >> 1) + 1);
   }

   public static void applyThornEnchantments(EntityLivingBase var0, Entity var1) {
      ENCHANTMENT_ITERATOR_HURT.attacker = var1;
      ENCHANTMENT_ITERATOR_HURT.user = var0;
      if (var0 != null) {
         applyEnchantmentModifierArray(ENCHANTMENT_ITERATOR_HURT, var0.getInventory());
      }

      if (var1 instanceof EntityPlayer) {
         applyEnchantmentModifier(ENCHANTMENT_ITERATOR_HURT, var0.getHeldItem());
      }
   }

   public static ItemStack getEnchantedItem(Enchantment var0, EntityLivingBase var1) {
      for (ItemStack var5 : var1.getInventory()) {
         if (var5 != null && getEnchantmentLevel(var0.effectId, var5) > 0) {
            return var5;
         }
      }

      return null;
   }

   public static int calcItemStackEnchantability(Random var0, int var1, int var2, ItemStack var3) {
      Item var4 = var3.getItem();
      int var5 = var4.getItemEnchantability();
      if (var5 <= 0) {
         return 0;
      } else {
         if (var2 > 15) {
            var2 = 15;
         }

         int var6 = var0.nextInt(8) + 1 + (var2 >> 1) + var0.nextInt(var2 + 1);
         return var1 == 0 ? Math.max(var6 / 3, 1) : (var1 == 1 ? var6 * 2 / 3 + 1 : Math.max(var6, var2 * 2));
      }
   }

   public static void applyArthropodEnchantments(EntityLivingBase var0, Entity var1) {
      ENCHANTMENT_ITERATOR_DAMAGE.user = var0;
      ENCHANTMENT_ITERATOR_DAMAGE.target = var1;
      if (var0 != null) {
         applyEnchantmentModifierArray(ENCHANTMENT_ITERATOR_DAMAGE, var0.getInventory());
      }

      if (var0 instanceof EntityPlayer) {
         applyEnchantmentModifier(ENCHANTMENT_ITERATOR_DAMAGE, var0.getHeldItem());
      }
   }

   public static int getFortuneModifier(EntityLivingBase var0) {
      return getEnchantmentLevel(Enchantment.u.effectId, var0.getHeldItem());
   }

   public static int getEfficiencyModifier(EntityLivingBase var0) {
      return getEnchantmentLevel(Enchantment.recoveredField627.effectId, var0.getHeldItem());
   }

   public static int getLootingModifier(EntityLivingBase var0) {
      return getEnchantmentLevel(Enchantment.recoveredField635.effectId, var0.getHeldItem());
   }

   public static List<EnchantmentData> buildEnchantmentList(Random var0, ItemStack var1, int var2) {
      Item var3 = var1.getItem();
      int var4 = var3.getItemEnchantability();
      if (var4 <= 0) {
         return null;
      } else {
         var4 /= 2;
         var4 = 1 + var0.nextInt((var4 >> 1) + 1) + var0.nextInt((var4 >> 1) + 1);
         int var5 = var4 + var2;
         float var6 = (var0.nextFloat() + var0.nextFloat() - 1.0F) * 0.15F;
         int var7 = (int)(var5 * (1.0F + var6) + 0.5F);
         if (var7 < 1) {
            var7 = 1;
         }

         ArrayList var8 = null;
         Map var9 = mapEnchantmentData(var7, var1);
         if (var9 != null && !var9.isEmpty()) {
            EnchantmentData var10 = (EnchantmentData)WeightedRandom.getRandomItem(var0, var9.values());
            if (var10 != null) {
               var8 = Lists.newArrayList();
               var8.add(var10);

               for (int var11 = var7; var0.nextInt(50) <= var11; var11 >>= 1) {
                  Iterator var12 = var9.keySet().iterator();

                  while (var12.hasNext()) {
                     Integer var13 = (Integer)var12.next();
                     boolean var14 = true;

                     for (EnchantmentData var16 : (Iterable<EnchantmentData>)(Iterable<?>)(var8)) {
                        if (!var16.enchantmentobj.canApplyTogether(Enchantment.getEnchantmentById(var13))) {
                           var14 = false;
                           break;
                        }
                     }

                     if (!var14) {
                        var12.remove();
                     }
                  }

                  if (!var9.isEmpty()) {
                     EnchantmentData var19 = (EnchantmentData)WeightedRandom.getRandomItem(var0, var9.values());
                     var8.add(var19);
                  }
               }
            }
         }

         return var8;
      }
   }

   public static int getKnockbackModifier(EntityLivingBase var0) {
      return getEnchantmentLevel(Enchantment.recoveredField636.effectId, var0.getHeldItem());
   }

   public static void applyEnchantmentModifier(EnchantmentHelper.IModifier var0, ItemStack var1) {
      if (var1 != null) {
         NBTTagList var2 = var1.getEnchantmentTagList();
         if (var2 != null) {
            for (int var3 = 0; var3 < var2.tagCount(); var3++) {
               short var4 = var2.getCompoundTagAt(var3).getShort("id");
               short var5 = var2.getCompoundTagAt(var3).getShort("lvl");
               if (Enchantment.getEnchantmentById(var4) != null) {
                  var0.calculateModifier(Enchantment.getEnchantmentById(var4), var5);
               }
            }
         }
      }
   }

   public static ItemStack addRandomEnchantment(Random var0, ItemStack var1, int var2) {
      List var3 = buildEnchantmentList(var0, var1, var2);
      boolean var4 = var1.getItem() == Items.book;
      if (var4) {
         var1.setItem(Items.enchanted_book);
      }

      if (var3 != null) {
         for (EnchantmentData var6 : (Iterable<EnchantmentData>)(Iterable<?>)(var3)) {
            if (var4) {
               Items.enchanted_book.addEnchantment(var1, var6);
            } else {
               var1.addEnchantment(var6.enchantmentobj, var6.enchantmentLevel);
            }
         }
      }

      return var1;
   }

   public static int method_08067(EntityLivingBase var0) {
      return getEnchantmentLevel(Enchantment.recoveredField632.effectId, var0.getHeldItem());
   }

   public static int getMaxEnchantmentLevel(int var0, ItemStack[] var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = 0;

         for (ItemStack var6 : var1) {
            int var7 = getEnchantmentLevel(var0, var6);
            if (var7 > var2) {
               var2 = var7;
            }
         }

         return var2;
      }
   }

   public static void applyEnchantmentModifierArray(EnchantmentHelper.IModifier var0, ItemStack[] var1) {
      for (ItemStack var5 : var1) {
         applyEnchantmentModifier(var0, var5);
      }
   }

   public static int method_08075(Entity var0) {
      return getMaxEnchantmentLevel(Enchantment.recoveredField626.effectId, var0.getInventory());
   }

   public static int method_08068(EntityLivingBase var0) {
      return getEnchantmentLevel(Enchantment.recoveredField633.effectId, var0.getHeldItem());
   }

   public static boolean getSilkTouchModifier(EntityLivingBase var0) {
      return getEnchantmentLevel(Enchantment.silkTouch.effectId, var0.getHeldItem()) > 0;
   }

   public static Map<Integer, Integer> getEnchantments(ItemStack var0) {
      LinkedHashMap var1 = Maps.newLinkedHashMap();
      NBTTagList var2 = var0.getItem() == Items.enchanted_book ? Items.enchanted_book.getEnchantments(var0) : var0.getEnchantmentTagList();
      if (var2 != null) {
         for (int var3 = 0; var3 < var2.tagCount(); var3++) {
            short var4 = var2.getCompoundTagAt(var3).getShort("id");
            short var5 = var2.getCompoundTagAt(var3).getShort("lvl");
            var1.put(Integer.valueOf(var4), Integer.valueOf(var5));
         }
      }

      return var1;
   }

   public static int getFireAspectModifier(EntityLivingBase var0) {
      return getEnchantmentLevel(Enchantment.recoveredField637.effectId, var0.getHeldItem());
   }

   public static int getEnchantmentLevel(int var0, ItemStack var1) {
      if (var1 == null) {
         return 0;
      } else {
         NBTTagList var2 = var1.getEnchantmentTagList();
         if (var2 == null) {
            return 0;
         } else {
            for (int var3 = 0; var3 < var2.tagCount(); var3++) {
               short var4 = var2.getCompoundTagAt(var3).getShort("id");
               short var5 = var2.getCompoundTagAt(var3).getShort("lvl");
               if (var4 == var0) {
                  return var5;
               }
            }

            return 0;
         }
      }
   }

   public static boolean getAquaAffinityModifier(EntityLivingBase var0) {
      return getMaxEnchantmentLevel(Enchantment.aquaAffinity.effectId, var0.getInventory()) > 0;
   }

   public static int getRespiration(Entity var0) {
      return getMaxEnchantmentLevel(Enchantment.recoveredField634.effectId, var0.getInventory());
   }

   public static final class DamageIterator implements EnchantmentHelper.IModifier {
      public Entity target;
      public EntityLivingBase user;

      @Override
      public void calculateModifier(Enchantment var1, int var2) {
         var1.onEntityDamaged(this.user, this.target, var2);
      }

      public DamageIterator() {
      }
   }

   public static final class HurtIterator implements EnchantmentHelper.IModifier {
      public Entity attacker;
      public EntityLivingBase user;

      public HurtIterator() {
      }

      @Override
      public void calculateModifier(Enchantment var1, int var2) {
         var1.onUserHurt(this.user, this.attacker, var2);
      }
   }

   public interface IModifier {
      void calculateModifier(Enchantment var1, int var2);
   }

   public static final class ModifierDamage implements EnchantmentHelper.IModifier {
      public int damageModifier;
      public DamageSource source;

      @Override
      public void calculateModifier(Enchantment var1, int var2) {
         this.damageModifier = this.damageModifier + var1.calcModifierDamage(var2, this.source);
      }

      public ModifierDamage() {
      }
   }

   public static final class ModifierLiving implements EnchantmentHelper.IModifier {
      public float livingModifier;
      public EnumCreatureAttribute entityLiving;

      @Override
      public void calculateModifier(Enchantment var1, int var2) {
         this.livingModifier = this.livingModifier + var1.calcDamageByCreature(var2, this.entityLiving);
      }

      public ModifierLiving() {
      }
   }
}
