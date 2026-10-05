package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import io.netty.channel.ChannelFutureListener$1;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.init.Bootstrap$9;
import net.minecraft.inventory.InventoryBasic;

public class EntityIronGolem$AINearestAttackableTargetNonCreeper<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
   public Bootstrap$9 field_0001;
   public ChannelFutureListener$1 field_0000;
   public InventoryBasic field_0002;

   public EntityIronGolem$AINearestAttackableTargetNonCreeper(
      EntityCreature var1, Class<T> var2, int var3, boolean var4, boolean var5, Predicate<? super T> var6
   ) {
      super(var1, var2, var3, var4, var5, var6);
      this.c = new EntityIronGolem$AINearestAttackableTargetNonCreeper$1(this, var6, var1);
   }
}
