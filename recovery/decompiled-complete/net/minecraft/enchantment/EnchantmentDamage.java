package net.minecraft.enchantment;

import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.command.CommandDebug;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenForest;
import net.optifine.shaders.ProgramStage;
import net.optifine.shaders.config.ShaderPackParser;
import org.apache.log4j.jmx.LayoutDynamicMBean;

public class EnchantmentDamage extends Enchantment {
   public static int[] thresholdEnchantability = new int[]{20, 20, 20};
   public LayoutDynamicMBean field_0010;
   public RenderLeashKnot field_0007;
   public static int[] levelEnchantability = new int[]{11, 8, 8};
   public CommandDebug field_0006;
   public int damageType;
   public ProgramStage field_0005;
   public BiomeGenForest field_0000;
   public static String[] protectionName = new String[]{"all", "undead", "arthropods"};
   public ShaderPackParser field_0003;
   public static int[] baseEnchantability = new int[]{1, 5, 5};

   @Override
   public int getMaxEnchantability(int var1) {
      return this.getMinEnchantability(var1) + thresholdEnchantability[this.damageType];
   }

   @Override
   public boolean canApplyTogether(Enchantment var1) {
      return !(var1 instanceof EnchantmentDamage);
   }

   @Override
   public void onEntityDamaged(EntityLivingBase var1, Entity var2, int var3) {
      if (var2 instanceof EntityLivingBase) {
         EntityLivingBase var4 = (EntityLivingBase)var2;
         if (this.damageType == 2 && var4.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
            int var5 = 20 + var1.getRNG().nextInt(10 * var3);
            var4.c(new PotionEffect(Potion.moveSlowdown.id, var5, 3));
         }
      }
   }

   @Override
   public String getName() {
      return "enchantment.damage." + protectionName[this.damageType];
   }

   @Override
   public int getMaxLevel() {
      return 5;
   }

   @Override
   public float calcDamageByCreature(int var1, EnumCreatureAttribute var2) {
      return this.damageType == 0
         ? var1 * 1.25F
         : (
            this.damageType == 1 && var2 == EnumCreatureAttribute.UNDEAD
               ? var1 * 2.5F
               : (this.damageType == 2 && var2 == EnumCreatureAttribute.ARTHROPOD ? var1 * 2.5F : 0.0F)
         );
   }

   @Override
   public int getMinEnchantability(int var1) {
      return baseEnchantability[this.damageType] + (var1 - 1) * levelEnchantability[this.damageType];
   }

   public EnchantmentDamage(int var1, ResourceLocation var2, int var3, int var4) {
      super(var1, var2, var3, EnumEnchantmentType.WEAPON);
      this.damageType = var4;
   }

   @Override
   public boolean canApply(ItemStack var1) {
      return var1.getItem() instanceof ItemAxe ? true : super.canApply(var1);
   }
}
