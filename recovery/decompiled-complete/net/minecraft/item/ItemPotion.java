package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.optifine.config.Weather;
import net.optifine.entity.model.ModelAdapterChest;

public class ItemPotion extends Item {
   public static Map<List<PotionEffect>, Integer> SUB_ITEMS_CACHE = Maps.newLinkedHashMap();
   public Map<Integer, List<PotionEffect>> effectCache = Maps.newHashMap();
   public Weather field_0005;
   public ModelAdapterChest field_0006;
   public TileEntityNote field_0001;
   public ColorizerGrass field_0000;
   public C0CPacketInput field_0003;

   public static boolean isSplash(int var0) {
      return (var0 & 16384) != 0;
   }

   @Override
   public boolean hasEffect(ItemStack var1) {
      List var2 = this.getEffects(var1);
      return var2 != null && !var2.isEmpty();
   }

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if (isSplash(var1.getMetadata())) {
         if (!var3.bA.isCreativeMode) {
            var1.stackSize--;
         }

         var2.a(var3, "random.bow", 0.5F, 0.4F / (g.nextFloat() * 0.4F + 0.8F));
         if (!var2.D) {
            var2.spawnEntityInWorld(new EntityPotion(var2, var3, var1));
         }

         var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
         return var1;
      } else {
         var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
         return var1;
      }
   }

   public boolean isEffectInstant(int var1) {
      List var2 = this.getEffects(var1);
      if (var2 != null && !var2.isEmpty()) {
         for (PotionEffect var4 : var2) {
            if (Potion.potionTypes[var4.getPotionID()].isInstant()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   @Override
   public void getSubItems(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      super.getSubItems(var1, var2, var3);
      if (SUB_ITEMS_CACHE.isEmpty()) {
         for (int var4 = 0; var4 <= 15; var4++) {
            for (int var5 = 0; var5 <= 1; var5++) {
               int var6;
               if (var5 == 0) {
                  var6 = var4 | 8192;
               } else {
                  var6 = var4 | 16384;
               }

               for (int var7 = 0; var7 <= 2; var7++) {
                  int var8 = var6;
                  if (var7 != 0) {
                     if (var7 == 1) {
                        var8 = var6 | 32;
                     } else if (var7 == 2) {
                        var8 = var6 | 64;
                     }
                  }

                  List var9 = PotionHelper.getPotionEffects(var8, false);
                  if (var9 != null && !var9.isEmpty()) {
                     SUB_ITEMS_CACHE.put(var9, var8);
                  }
               }
            }
         }
      }

      for (int var11 : SUB_ITEMS_CACHE.values()) {
         var3.add(new ItemStack(var1, 1, var11));
      }
   }

   @Override
   public int getColorFromItemStack(ItemStack var1, int var2) {
      return var2 > 0 ? 16777215 : this.getColorFromDamage(var1.getMetadata());
   }

   public List<PotionEffect> getEffects(int var1) {
      List var2 = this.effectCache.get(var1);
      if (var2 == null) {
         var2 = PotionHelper.getPotionEffects(var1, false);
         this.effectCache.put(var1, var2);
      }

      return var2;
   }

   public int getColorFromDamage(int var1) {
      return PotionHelper.getLiquidColor(var1, false);
   }

   @Override
   public String getItemStackDisplayName(ItemStack var1) {
      if (var1.getMetadata() == 0) {
         return StatCollector.translateToLocal("item.emptyPotion.name").trim();
      } else {
         String var2 = "";
         if (isSplash(var1.getMetadata())) {
            var2 = StatCollector.translateToLocal("potion.prefix.grenade").trim() + " ";
         }

         List var3 = Items.potionitem.getEffects(var1);
         if (var3 != null && !var3.isEmpty()) {
            String var5 = ((PotionEffect)var3.get(0)).getEffectName();
            var5 = var5 + ".postfix";
            return var2 + StatCollector.translateToLocal(var5).trim();
         } else {
            String var4 = PotionHelper.getPotionPrefix(var1.getMetadata());
            return StatCollector.translateToLocal(var4).trim() + " " + super.getItemStackDisplayName(var1);
         }
      }
   }

   @Override
   public int getMaxItemUseDuration(ItemStack var1) {
      return 32;
   }

   @Override
   public void addInformation(ItemStack var1, EntityPlayer var2, List<String> var3, boolean var4) {
      if (var1.getMetadata() != 0) {
         List var5 = Items.potionitem.getEffects(var1);
         HashMultimap var6 = HashMultimap.create();
         if (var5 != null && !var5.isEmpty()) {
            for (PotionEffect var8 : var5) {
               Object var9 = StatCollector.translateToLocal(var8.getEffectName()).trim();
               Potion var10 = Potion.potionTypes[var8.getPotionID()];
               Map var11 = var10.getAttributeModifierMap();
               if (var11 != null && var11.size() > 0) {
                  for (Entry var13 : var11.entrySet()) {
                     AttributeModifier var14 = (AttributeModifier)var13.getValue();
                     AttributeModifier var15 = new AttributeModifier(
                        var14.getName(), var10.getAttributeModifierAmount(var8.getAmplifier(), var14), var14.getOperation()
                     );
                     var6.put(((IAttribute)var13.getKey()).getAttributeUnlocalizedName(), var15);
                  }
               }

               if (var8.getAmplifier() > 0) {
                  var9 = var9 + " " + StatCollector.translateToLocal("potion.potency." + var8.getAmplifier()).trim();
               }

               if (var8.getDuration() > 20) {
                  var9 = var9 + " (" + Potion.getDurationString(var8) + ")";
               }

               if (var10.isBadEffect()) {
                  var3.add(EnumChatFormatting.RED + var9);
               } else {
                  var3.add(EnumChatFormatting.GRAY + var9);
               }
            }
         } else {
            Object var7 = StatCollector.translateToLocal("potion.empty").trim();
            var3.add(EnumChatFormatting.GRAY + var7);
         }

         if (!var6.isEmpty()) {
            var3.add("");
            var3.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("potion.effects.whenDrank"));

            for (Entry var18 : var6.entries()) {
               AttributeModifier var19 = (AttributeModifier)var18.getValue();
               double var20 = var19.getAmount();
               double var21;
               if (var19.getOperation() != 1 && var19.getOperation() != 2) {
                  var21 = var19.getAmount();
               } else {
                  var21 = var19.getAmount() * 100.0;
               }

               if (var20 > 0.0) {
                  var3.add(
                     EnumChatFormatting.BLUE
                        + StatCollector.translateToLocalFormatted(
                           "attribute.modifier.plus." + var19.getOperation(),
                           ItemStack.DECIMALFORMAT.format(var21),
                           StatCollector.translateToLocal("attribute.name." + (String)var18.getKey())
                        )
                  );
               } else if (var20 < 0.0) {
                  var21 *= -1.0;
                  var3.add(
                     EnumChatFormatting.RED
                        + StatCollector.translateToLocalFormatted(
                           "attribute.modifier.take." + var19.getOperation(),
                           ItemStack.DECIMALFORMAT.format(var21),
                           StatCollector.translateToLocal("attribute.name." + (String)var18.getKey())
                        )
                  );
               }
            }
         }
      }
   }

   @Override
   public EnumAction getItemUseAction(ItemStack var1) {
      return EnumAction.DRINK;
   }

   public List<PotionEffect> getEffects(ItemStack var1) {
      if (var1.hasTagCompound() && var1.getTagCompound().hasKey("CustomPotionEffects", 9)) {
         ArrayList var7 = Lists.newArrayList();
         NBTTagList var3 = var1.getTagCompound().getTagList("CustomPotionEffects", 10);

         for (int var4 = 0; var4 < var3.tagCount(); var4++) {
            NBTTagCompound var5 = var3.getCompoundTagAt(var4);
            PotionEffect var6 = PotionEffect.readCustomPotionEffectFromNBT(var5);
            if (var6 != null) {
               var7.add(var6);
            }
         }

         return var7;
      } else {
         List var2 = this.effectCache.get(var1.getMetadata());
         if (var2 == null) {
            var2 = PotionHelper.getPotionEffects(var1.getMetadata(), false);
            this.effectCache.put(var1.getMetadata(), var2);
         }

         return var2;
      }
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack var1, World var2, EntityPlayer var3) {
      if (!var3.bA.isCreativeMode) {
         var1.stackSize--;
      }

      if (!var2.D) {
         List var4 = this.getEffects(var1);
         if (var4 != null) {
            for (PotionEffect var6 : var4) {
               var3.c(new PotionEffect(var6));
            }
         }
      }

      var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      if (!var3.bA.isCreativeMode) {
         if (var1.stackSize <= 0) {
            return new ItemStack(Items.glass_bottle);
         }

         var3.bi.addItemStackToInventory(new ItemStack(Items.glass_bottle));
      }

      return var1;
   }

   public ItemPotion() {
      this.c(1);
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabBrewing);
   }
}
