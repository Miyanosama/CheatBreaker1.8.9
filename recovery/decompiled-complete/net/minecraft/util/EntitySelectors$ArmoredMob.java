package net.minecraft.util;

import com.google.common.base.Predicate;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class EntitySelectors$ArmoredMob implements Predicate<Entity> {
   public ModelSlime field_0002;
   public ItemStack armor;
   public ConcurrentSet field_0001;
   public MovingObjectPosition$MovingObjectType field_0003;
   public SharedMonsterAttributes field_0000;

   public EntitySelectors$ArmoredMob(ItemStack var1) {
      this.armor = var1;
   }

   public boolean apply(Entity var1) {
      if (!var1.isEntityAlive()) {
         return false;
      } else if (!(var1 instanceof EntityLivingBase)) {
         return false;
      } else {
         EntityLivingBase var2 = (EntityLivingBase)var1;
         return var2.getEquipmentInSlot(EntityLiving.getArmorPosition(this.armor)) != null
            ? false
            : (var2 instanceof EntityLiving ? ((EntityLiving)var2).canPickUpLoot() : (var2 instanceof EntityArmorStand ? true : var2 instanceof EntityPlayer));
      }
   }
}
