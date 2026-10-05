package net.minecraft.util;

import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class EntityDamageSource extends DamageSource {
   public Entity damageSourceEntity;
   public boolean isThornsDamage = false;
   public ModelSnowMan field_0002;

   public EntityDamageSource setIsThornsDamage() {
      this.isThornsDamage = true;
      return this;
   }

   public boolean getIsThornsDamage() {
      return this.isThornsDamage;
   }

   @Override
   public Entity getEntity() {
      return this.damageSourceEntity;
   }

   @Override
   public IChatComponent getDeathMessage(EntityLivingBase var1) {
      ItemStack var2 = this.damageSourceEntity instanceof EntityLivingBase ? ((EntityLivingBase)this.damageSourceEntity).getHeldItem() : null;
      String var3 = "death.attack." + this.damageType;
      String var4 = var3 + ".item";
      return var2 != null && var2.hasDisplayName() && StatCollector.canTranslate(var4)
         ? new ChatComponentTranslation(var4, var1.getDisplayName(), this.damageSourceEntity.getDisplayName(), var2.getChatComponent())
         : new ChatComponentTranslation(var3, var1.getDisplayName(), this.damageSourceEntity.getDisplayName());
   }

   public EntityDamageSource(String var1, Entity var2) {
      super(var1);
      this.damageSourceEntity = var2;
   }

   @Override
   public boolean isDifficultyScaled() {
      return this.damageSourceEntity != null && this.damageSourceEntity instanceof EntityLivingBase && !(this.damageSourceEntity instanceof EntityPlayer);
   }
}
