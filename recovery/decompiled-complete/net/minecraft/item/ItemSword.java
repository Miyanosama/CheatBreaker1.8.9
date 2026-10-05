package net.minecraft.item;

import com.cheatbreaker.client.ui.CompetitiveLeaveWarningGui;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.realms.RealmsServerPing;
import net.minecraft.server.network.NetHandlerLoginServer$2;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.optifine.shaders.gui.GuiButtonEnumShaderOption$1;

public class ItemSword extends Item {
   public FileResourcePack field_0003;
   public NetHandlerLoginServer$2 field_0005;
   public CompetitiveLeaveWarningGui field_0006;
   public Item$ToolMaterial material;
   public RealmsServerPing field_0002;
   public float attackDamage;
   public GuiButtonEnumShaderOption$1 field_0004;
   public WorldGenForest field_0000;

   @Override
   public boolean isFull3D() {
      return true;
   }

   @Override
   public boolean getIsRepairable(ItemStack var1, ItemStack var2) {
      return this.material.getRepairItem() == var2.getItem() ? true : super.getIsRepairable(var1, var2);
   }

   @Override
   public int getMaxItemUseDuration(ItemStack var1) {
      return 72000;
   }

   public float getDamageVsEntity() {
      return this.material.getDamageVsEntity();
   }

   @Override
   public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
      Multimap var1 = super.getItemAttributeModifiers();
      var1.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(f, "Weapon modifier", this.attackDamage, 0));
      return var1;
   }

   @Override
   public boolean onBlockDestroyed(ItemStack var1, World var2, Block var3, BlockPos var4, EntityLivingBase var5) {
      if (var3.getBlockHardness(var2, var4) != 0.0) {
         var1.damageItem(2, var5);
      }

      return true;
   }

   public ItemSword(Item$ToolMaterial var1) {
      this.material = var1;
      this.h = 1;
      this.setMaxDamage(var1.getMaxUses());
      this.setCreativeTab(CreativeTabs.tabCombat);
      this.attackDamage = 4.0F + var1.getDamageVsEntity();
   }

   @Override
   public EnumAction getItemUseAction(ItemStack var1) {
      return EnumAction.BLOCK;
   }

   @Override
   public float getStrVsBlock(ItemStack var1, Block var2) {
      if (var2 == Blocks.web) {
         return 15.0F;
      } else {
         Material var3 = var2.getMaterial();
         return var3 != Material.plants && var3 != Material.vine && var3 != Material.coral && var3 != Material.leaves && var3 != Material.gourd ? 1.0F : 1.5F;
      }
   }

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
      return var1;
   }

   @Override
   public boolean hitEntity(ItemStack var1, EntityLivingBase var2, EntityLivingBase var3) {
      var1.damageItem(1, var3);
      return true;
   }

   public String getToolMaterialName() {
      return this.material.toString();
   }

   @Override
   public int getItemEnchantability() {
      return this.material.getEnchantability();
   }

   @Override
   public boolean canHarvestBlock(Block var1) {
      return var1 == Blocks.web;
   }
}
