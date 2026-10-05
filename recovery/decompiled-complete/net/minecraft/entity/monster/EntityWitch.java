package net.minecraft.entity.monster;

import java.util.List;
import java.util.UUID;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.optifine.DynamicLightsMap;

public class EntityWitch extends EntityMob implements IRangedAttackMob {
   public int witchAttackTimer;
   public DynamicLightsMap field_0005;
   public S00PacketKeepAlive field_0002;
   public static AttributeModifier MODIFIER = new AttributeModifier(EntityWitch.MODIFIER_UUID, "Drinking speed penalty", -0.25, 0).setSaved(false);
   public static UUID MODIFIER_UUID = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
   public static Item[] witchDrops = new Item[]{
      Items.glowstone_dust, Items.sugar, Items.redstone, Items.spider_eye, Items.glass_bottle, Items.gunpowder, Items.stick, Items.stick
   };

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 15) {
         for (int var2 = 0; var2 < this.V.nextInt(35) + 10; var2++) {
            this.o
               .spawnParticle(
                  EnumParticleTypes.SPELL_WITCH,
                  this.s + this.V.nextGaussian() * 0.13F,
                  this.getEntityBoundingBox().e + 0.5 + this.V.nextGaussian() * 0.13F,
                  this.u + this.V.nextGaussian() * 0.13F,
                  0.0,
                  0.0,
                  0.0
               );
         }
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public void k_() {
      super.k_();
      this.H().addObject(21, (byte)0);
   }

   @Override
   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      if (!this.getAggressive()) {
         EntityPotion var3 = new EntityPotion(this.o, this, 32732);
         double var4 = var1.t + var1.getEyeHeight() - 1.1F;
         var3.z -= -20.0F;
         double var6 = var1.s + var1.v - this.s;
         double var8 = var4 - this.t;
         double var10 = var1.u + var1.x - this.u;
         float var12 = MathHelper.sqrt_double(var6 * var6 + var10 * var10);
         if (var12 >= 8.0F && !var1.isPotionActive(Potion.moveSlowdown)) {
            var3.setPotionDamage(32698);
         } else if (var1.getHealth() >= 8.0F && !var1.isPotionActive(Potion.poison)) {
            var3.setPotionDamage(32660);
         } else if (var12 <= 3.0F && !var1.isPotionActive(Potion.weakness) && this.V.nextFloat() < 0.25F) {
            var3.setPotionDamage(32696);
         }

         var3.setThrowableHeading(var6, var8 + var12 * 0.2F, var10, 0.75F, 8.0F);
         this.o.spawnEntityInWorld(var3);
      }
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(3) + 1;

      for (int var4 = 0; var4 < var3; var4++) {
         int var5 = this.V.nextInt(3);
         Item var6 = witchDrops[this.V.nextInt(witchDrops.length)];
         if (var2 > 0) {
            var5 += this.V.nextInt(var2 + 1);
         }

         for (int var7 = 0; var7 < var5; var7++) {
            this.dropItem(var6, 1);
         }
      }
   }

   @Override
   public String getHurtSound() {
      return null;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(26.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
   }

   public void setAggressive(boolean var1) {
      this.H().updateObject(21, (byte)(var1 ? 1 : 0));
   }

   @Override
   public float getEyeHeight() {
      return 1.62F;
   }

   @Override
   public String getDeathSound() {
      return null;
   }

   public boolean getAggressive() {
      return this.H().getWatchableObjectByte(21) == 1;
   }

   @Override
   public String getLivingSound() {
      return null;
   }

   public EntityWitch(World var1) {
      super(var1);
      this.setSize(0.6F, 1.95F);
      this.i.addTask(1, new EntityAISwimming(this));
      this.i.addTask(2, new EntityAIArrowAttack(this, 1.0, 60, 10.0F));
      this.i.addTask(2, new EntityAIWander(this, 1.0));
      this.i.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(3, new EntityAILookIdle(this));
      this.bi.addTask(1, new EntityAIHurtByTarget(this, false));
      this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
   }

   @Override
   public void onLivingUpdate() {
      if (!this.o.D) {
         if (this.getAggressive()) {
            if (this.witchAttackTimer-- <= 0) {
               this.setAggressive(false);
               ItemStack var5 = this.getHeldItem();
               this.setCurrentItemOrArmor(0, (ItemStack)null);
               if (var5 != null && var5.getItem() == Items.potionitem) {
                  List var6 = Items.potionitem.getEffects(var5);
                  if (var6 != null) {
                     for (PotionEffect var4 : var6) {
                        this.c(new PotionEffect(var4));
                     }
                  }
               }

               this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(MODIFIER);
            }
         } else {
            short var1 = -1;
            if (this.V.nextFloat() < 0.15F && this.a(Material.water) && !this.isPotionActive(Potion.waterBreathing)) {
               var1 = 8237;
            } else if (this.V.nextFloat() < 0.15F && this.isBurning() && !this.isPotionActive(Potion.fireResistance)) {
               var1 = 16307;
            } else if (this.V.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
               var1 = 16341;
            } else if (this.V.nextFloat() < 0.25F
               && this.getAttackTarget() != null
               && !this.isPotionActive(Potion.moveSpeed)
               && this.getAttackTarget().h(this) > 121.0) {
               var1 = 16274;
            } else if (this.V.nextFloat() < 0.25F
               && this.getAttackTarget() != null
               && !this.isPotionActive(Potion.moveSpeed)
               && this.getAttackTarget().h(this) > 121.0) {
               var1 = 16274;
            }

            if (var1 > -1) {
               this.setCurrentItemOrArmor(0, new ItemStack(Items.potionitem, 1, var1));
               this.witchAttackTimer = this.getHeldItem().getMaxItemUseDuration();
               this.setAggressive(true);
               IAttributeInstance var2 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
               var2.removeModifier(MODIFIER);
               var2.applyModifier(MODIFIER);
            }
         }

         if (this.V.nextFloat() < 7.5E-4F) {
            this.o.setEntityState(this, (byte)15);
         }
      }

      super.onLivingUpdate();
   }

   @Override
   public float applyPotionDamageCalculations(DamageSource var1, float var2) {
      var2 = super.applyPotionDamageCalculations(var1, var2);
      if (var1.getEntity() == this) {
         var2 = 0.0F;
      }

      if (var1.isMagicDamage()) {
         var2 = (float)(var2 * 0.15);
      }

      return var2;
   }
}
