package net.minecraft.entity.monster;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public abstract class EntityMob extends EntityCreature implements IMob {
   @Override
   public boolean canDropLoot() {
      return true;
   }

   @Override
   public String getFallSoundString(int var1) {
      return var1 > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
   }

   public EntityMob(World var1) {
      super(var1);
      this.experienceValue = 5;
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.o.getDifficulty() != EnumDifficulty.PEACEFUL && this.A_() && super.getCanSpawnHere();
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.o.D && this.o.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }
   }

   @Override
   public void onLivingUpdate() {
      this.updateArmSwingProgress();
      float var1 = this.a_(1.0F);
      if (var1 > 0.5F) {
         this.aQ += 2;
      }

      super.onLivingUpdate();
   }

   @Override
   public float getBlockPathWeight(BlockPos var1) {
      return 0.5F - this.o.o(var1);
   }

   @Override
   public String getHurtSound() {
      return "game.hostile.hurt";
   }

   @Override
   public String getSplashSound() {
      return "game.hostile.swim.splash";
   }

   public boolean A_() {
      BlockPos var1 = new BlockPos(this.s, this.getEntityBoundingBox().b, this.u);
      if (this.o.getLightFor(EnumSkyBlock.SKY, var1) > this.V.nextInt(32)) {
         return false;
      } else {
         int var2 = this.o.getLightFromNeighbors(var1);
         if (this.o.isThundering()) {
            int var3 = this.o.getSkylightSubtracted();
            this.o.setSkylightSubtracted(10);
            var2 = this.o.getLightFromNeighbors(var1);
            this.o.setSkylightSubtracted(var3);
         }

         return var2 <= this.V.nextInt(8);
      }
   }

   @Override
   public String getSwimSound() {
      return "game.hostile.swim";
   }

   @Override
   public boolean attackEntityAsMob(Entity var1) {
      float var2 = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
      int var3 = 0;
      if (var1 instanceof EntityLivingBase) {
         var2 += EnchantmentHelper.getModifierForCreature(this.getHeldItem(), ((EntityLivingBase)var1).getCreatureAttribute());
         var3 += EnchantmentHelper.getKnockbackModifier(this);
      }

      boolean var4 = var1.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
      if (var4) {
         if (var3 > 0) {
            var1.addVelocity(
               -MathHelper.sin(this.y * (float) Math.PI / 180.0F) * var3 * 0.5F, 0.1, MathHelper.cos(this.y * (float) Math.PI / 180.0F) * var3 * 0.5F
            );
            this.v *= 0.6;
            this.x *= 0.6;
         }

         int var5 = EnchantmentHelper.getFireAspectModifier(this);
         if (var5 > 0) {
            var1.setFire(var5 * 4);
         }

         this.applyEnchantments(this, var1);
      }

      return var4;
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else if (!super.attackEntityFrom(var1, var2)) {
         return false;
      } else {
         Entity var3 = var1.getEntity();
         return this.l != var3 && this.m != var3 ? true : true;
      }
   }

   @Override
   public String getDeathSound() {
      return "game.hostile.die";
   }
}
