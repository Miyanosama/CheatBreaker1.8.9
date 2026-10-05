package net.minecraft.item;

import com.cheatbreaker.client.ui.overlay.friend.FriendsListElement;
import io.netty.channel.socket.nio.NioServerSocketChannel$NioServerSocketChannelConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.world.World;

public class ItemFood extends Item {
   public boolean alwaysEdible;
   public int itemUseDuration = 32;
   public float saturationModifier;
   public NioServerSocketChannel$NioServerSocketChannelConfig field_0009;
   public int potionDuration;
   public boolean isWolfsFavoriteMeat;
   public int healAmount;
   public int potionId;
   public int potionAmplifier;
   public FriendsListElement field_0005;
   public float potionEffectProbability;
   public TileEntityBeacon field_0002;

   public ItemFood(int var1, float var2, boolean var3) {
      this.healAmount = var1;
      this.isWolfsFavoriteMeat = var3;
      this.saturationModifier = var2;
      this.setCreativeTab(CreativeTabs.tabFood);
   }

   public void onFoodEaten(ItemStack var1, World var2, EntityPlayer var3) {
      if (!var2.D && this.potionId > 0 && var2.s.nextFloat() < this.potionEffectProbability) {
         var3.c(new PotionEffect(this.potionId, this.potionDuration * 20, this.potionAmplifier));
      }
   }

   @Override
   public int getMaxItemUseDuration(ItemStack var1) {
      return 32;
   }

   public boolean isWolfsFavoriteMeat() {
      return this.isWolfsFavoriteMeat;
   }

   public ItemFood(int var1, boolean var2) {
      this(var1, 0.6F, var2);
   }

   public boolean method_03551() {
      return this.alwaysEdible;
   }

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if (var3.canEat(this.alwaysEdible)) {
         var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
      }

      return var1;
   }

   public ItemFood setAlwaysEdible() {
      this.alwaysEdible = true;
      return this;
   }

   public float getSaturationModifier(ItemStack var1) {
      return this.saturationModifier;
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack var1, World var2, EntityPlayer var3) {
      var1.stackSize--;
      var3.getFoodStats().addStats(this, var1);
      var2.a(var3, "random.burp", 0.5F, var2.s.nextFloat() * 0.1F + 0.9F);
      this.onFoodEaten(var1, var2, var3);
      var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return var1;
   }

   public int getHealAmount(ItemStack var1) {
      return this.healAmount;
   }

   @Override
   public EnumAction getItemUseAction(ItemStack var1) {
      return EnumAction.EAT;
   }

   public ItemFood setPotionEffect(int var1, int var2, int var3, float var4) {
      this.potionId = var1;
      this.potionDuration = var2;
      this.potionAmplifier = var3;
      this.potionEffectProbability = var4;
      return this;
   }
}
