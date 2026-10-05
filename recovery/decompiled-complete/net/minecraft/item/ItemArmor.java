package net.minecraft.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S44PacketWorldBorder$1;
import net.minecraft.world.World;
import net.optifine.entity.model.anim.RenderEntityParameterBool;
import net.optifine.shaders.config.EnumShaderOption;

public class ItemArmor extends Item {
   public RenderEntityParameterBool field_0003;
   public static IBehaviorDispenseItem dispenserBehavior = new ItemArmor$1();
   public int armorType;
   public EnumShaderOption field_0009;
   public int damageReduceAmount;
   public static String[] EMPTY_SLOT_NAMES = new String[]{
      "minecraft:items/empty_armor_slot_helmet",
      "minecraft:items/empty_armor_slot_chestplate",
      "minecraft:items/empty_armor_slot_leggings",
      "minecraft:items/empty_armor_slot_boots"
   };
   public S44PacketWorldBorder$1 field_0004;
   public ItemArmor$ArmorMaterial material;
   public int renderIndex;
   public static int[] maxDamageArray = new int[]{11, 16, 15, 13};

   public ItemArmor$ArmorMaterial getArmorMaterial() {
      return this.material;
   }

   @Override
   public int getColorFromItemStack(ItemStack var1, int var2) {
      if (var2 > 0) {
         return 16777215;
      } else {
         int var3 = this.getColor(var1);
         if (var3 < 0) {
            var3 = 16777215;
         }

         return var3;
      }
   }

   public int getColor(ItemStack var1) {
      if (this.material != ItemArmor$ArmorMaterial.LEATHER) {
         return -1;
      } else {
         NBTTagCompound var2 = var1.getTagCompound();
         if (var2 != null) {
            NBTTagCompound var3 = var2.getCompoundTag("display");
            if (var3 != null && var3.hasKey("color", 3)) {
               return var3.getInteger("color");
            }
         }

         return 10511680;
      }
   }

   public void setColor(ItemStack var1, int var2) {
      if (this.material != ItemArmor$ArmorMaterial.LEATHER) {
         throw new UnsupportedOperationException("Can't dye non-leather!");
      } else {
         NBTTagCompound var3 = var1.getTagCompound();
         if (var3 == null) {
            var3 = new NBTTagCompound();
            var1.setTagCompound(var3);
         }

         NBTTagCompound var4 = var3.getCompoundTag("display");
         if (!var3.hasKey("display", 10)) {
            var3.setTag("display", var4);
         }

         var4.setInteger("color", var2);
      }
   }

   public boolean hasColor(ItemStack var1) {
      return this.material != ItemArmor$ArmorMaterial.LEATHER
         ? false
         : (
            !var1.hasTagCompound()
               ? false
               : (!var1.getTagCompound().hasKey("display", 10) ? false : var1.getTagCompound().getCompoundTag("display").hasKey("color", 3))
         );
   }

   public ItemArmor(ItemArmor$ArmorMaterial var1, int var2, int var3) {
      this.material = var1;
      this.armorType = var3;
      this.renderIndex = var2;
      this.damageReduceAmount = var1.getDamageReductionAmount(var3);
      this.setMaxDamage(var1.getDurability(var3));
      this.h = 1;
      this.setCreativeTab(CreativeTabs.tabCombat);
      BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      int var4 = EntityLiving.getArmorPosition(var1) - 1;
      ItemStack var5 = var3.getCurrentArmor(var4);
      if (var5 == null) {
         var3.setCurrentItemOrArmor(var4, var1.copy());
         var1.stackSize = 0;
      }

      return var1;
   }

   @Override
   public int getItemEnchantability() {
      return this.material.getEnchantability();
   }

   public void removeColor(ItemStack var1) {
      if (this.material == ItemArmor$ArmorMaterial.LEATHER) {
         NBTTagCompound var2 = var1.getTagCompound();
         if (var2 != null) {
            NBTTagCompound var3 = var2.getCompoundTag("display");
            if (var3.hasKey("color")) {
               var3.removeTag("color");
            }
         }
      }
   }

   @Override
   public boolean getIsRepairable(ItemStack var1, ItemStack var2) {
      return this.material.getRepairItem() == var2.getItem() ? true : super.getIsRepairable(var1, var2);
   }
}
