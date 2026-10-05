package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMinecartEmpty extends EntityMinecart {
   @Override
   public boolean a_(EntityPlayer var1) {
      if (this.l != null && this.l instanceof EntityPlayer && this.l != var1) {
         return true;
      } else if (this.l != null && this.l != var1) {
         return false;
      } else {
         if (!this.o.D) {
            var1.mountEntity(this);
         }

         return true;
      }
   }

   public EntityMinecartEmpty(World var1) {
      super(var1);
   }

   @Override
   public EntityMinecart.EnumMinecartType getMinecartType() {
      return EntityMinecart.EnumMinecartType.RIDEABLE;
   }

   @Override
   public void onActivatorRailPass(int var1, int var2, int var3, boolean var4) {
      if (var4) {
         if (this.l != null) {
            this.l.mountEntity((Entity)null);
         }

         if (this.getRollingAmplitude() == 0) {
            this.setRollingDirection(-this.getRollingDirection());
            this.setRollingAmplitude(10);
            this.setDamage(50.0F);
            this.setBeenAttacked();
         }
      }
   }

   public EntityMinecartEmpty(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }
}
