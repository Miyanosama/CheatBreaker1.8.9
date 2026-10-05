package net.minecraft.entity.projectile;

import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import com.cheatbreaker.client.util.HardwareId;
import io.netty.handler.codec.http.DefaultHttpHeaders$1;
import java.util.List;
import net.minecraft.client.particle.EntityRainFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityChest$1;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPotion extends EntityThrowable {
   public GradientTextButton field_0003;
   public EntityRainFX field_0005;
   public ItemStack potionDamage;
   public TileEntityChest$1 field_0004;
   public EntityAIAttackOnCollide field_0000;
   public HardwareId field_0001;
   public DefaultHttpHeaders$1 field_0006;

   @Override
   public float getGravityVelocity() {
      return 0.05F;
   }

   public EntityPotion(World var1) {
      super(var1);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if (var1.hasKey("Potion", 10)) {
         this.potionDamage = ItemStack.loadItemStackFromNBT(var1.getCompoundTag("Potion"));
      } else {
         this.setPotionDamage(var1.getInteger("potionValue"));
      }

      if (this.potionDamage == null) {
         this.setDead();
      }
   }

   public EntityPotion(World var1, EntityLivingBase var2, int var3) {
      this(var1, var2, new ItemStack(Items.potionitem, 1, var3));
   }

   @Override
   public float getVelocity() {
      return 0.5F;
   }

   public int getPotionDamage() {
      if (this.potionDamage == null) {
         this.potionDamage = new ItemStack(Items.potionitem, 1, 0);
      }

      return this.potionDamage.getMetadata();
   }

   @Override
   public void onImpact(MovingObjectPosition var1) {
      if (!this.o.D) {
         List var2 = Items.potionitem.getEffects(this.potionDamage);
         if (var2 != null && !var2.isEmpty()) {
            AxisAlignedBB var3 = this.getEntityBoundingBox().expand(4.0, 2.0, 4.0);
            List var4 = this.o.getEntitiesWithinAABB(EntityLivingBase.class, var3);
            if (!var4.isEmpty()) {
               for (EntityLivingBase var6 : var4) {
                  double var7 = this.h(var6);
                  if (var7 < 16.0) {
                     double var9 = 1.0 - Math.sqrt(var7) / 4.0;
                     if (var6 == var1.entityHit) {
                        var9 = 1.0;
                     }

                     for (PotionEffect var12 : var2) {
                        int var13 = var12.getPotionID();
                        if (Potion.potionTypes[var13].isInstant()) {
                           Potion.potionTypes[var13].affectEntity(this, this.getThrower(), var6, var12.getAmplifier(), var9);
                        } else {
                           int var14 = (int)(var9 * var12.getDuration() + 0.5);
                           if (var14 > 20) {
                              var6.c(new PotionEffect(var13, var14, var12.getAmplifier()));
                           }
                        }
                     }
                  }
               }
            }
         }

         this.o.b(2002, new BlockPos(this), this.getPotionDamage());
         this.setDead();
      }
   }

   @Override
   public float getInaccuracy() {
      return -20.0F;
   }

   public EntityPotion(World var1, EntityLivingBase var2, ItemStack var3) {
      super(var1, var2);
      this.potionDamage = var3;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      if (this.potionDamage != null) {
         var1.setTag("Potion", this.potionDamage.writeToNBT(new NBTTagCompound()));
      }
   }

   public void setPotionDamage(int var1) {
      if (this.potionDamage == null) {
         this.potionDamage = new ItemStack(Items.potionitem, 1, 0);
      }

      this.potionDamage.setItemDamage(var1);
   }

   public EntityPotion(World var1, double var2, double var4, double var6, int var8) {
      this(var1, var2, var4, var6, new ItemStack(Items.potionitem, 1, var8));
   }

   public EntityPotion(World var1, double var2, double var4, double var6, ItemStack var8) {
      super(var1, var2, var4, var6);
      this.potionDamage = var8;
   }
}
