package net.minecraft.entity.monster;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySpider extends EntityMob {
   public void setBesideClimbableBlock(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      if (var1) {
         var2 = (byte)(var2 | 1);
      } else {
         var2 = (byte)(var2 & -2);
      }

      this.ac.updateObject(16, var2);
   }

   @Override
   public String getHurtSound() {
      return "mob.spider.say";
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
   }

   @Override
   public boolean n_() {
      return this.isBesideClimbableBlock();
   }

   @Override
   public String getDeathSound() {
      return "mob.spider.death";
   }

   @Override
   public void setInWeb() {
   }

   public boolean isBesideClimbableBlock() {
      return (this.ac.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public PathNavigate getNewNavigator(World var1) {
      return new PathNavigateClimber(this, var1);
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      super.dropFewItems(var1, var2);
      if (var1 && (this.V.nextInt(3) == 0 || this.V.nextInt(1 + var2) > 0)) {
         this.dropItem(Items.spider_eye, 1);
      }
   }

   @Override
   public String getLivingSound() {
      return "mob.spider.say";
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.ARTHROPOD;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.o.D) {
         this.setBesideClimbableBlock(this.D);
      }
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, new Byte((byte)0));
   }

   @Override
   public boolean isPotionApplicable(PotionEffect var1) {
      return var1.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(var1);
   }

   @Override
   public double getMountedYOffset() {
      return this.K * 0.5F;
   }

   public EntitySpider(World var1) {
      super(var1);
      this.setSize(1.4F, 0.9F);
      this.i.addTask(1, new EntityAISwimming(this));
      this.i.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
      this.i.addTask(4, new EntitySpider.AISpiderAttack(this, EntityPlayer.class));
      this.i.addTask(4, new EntitySpider.AISpiderAttack(this, EntityIronGolem.class));
      this.i.addTask(5, new EntityAIWander(this, 0.8));
      this.i.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(6, new EntityAILookIdle(this));
      this.bi.addTask(1, new EntityAIHurtByTarget(this, false));
      this.bi.addTask(2, new EntitySpider.AISpiderTarget<>(this, EntityPlayer.class));
      this.bi.addTask(3, new EntitySpider.AISpiderTarget<>(this, EntityIronGolem.class));
   }

   @Override
   public float getEyeHeight() {
      return 0.65F;
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.spider.step", 0.15F, 1.0F);
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance var1, IEntityLivingData var2) {
      var2 = super.onInitialSpawn(var1, var2);
      if (this.o.s.nextInt(100) == 0) {
         EntitySkeleton var3 = new EntitySkeleton(this.o);
         var3.a_(this.s, this.t, this.u, this.y, 0.0F);
         var3.onInitialSpawn(var1, (IEntityLivingData)null);
         this.o.spawnEntityInWorld(var3);
         var3.mountEntity(this);
      }

      if (var2 == null) {
         var2 = new EntitySpider.GroupData();
         if (this.o.getDifficulty() == EnumDifficulty.HARD && this.o.s.nextFloat() < 0.1F * var1.getClampedAdditionalDifficulty()) {
            ((EntitySpider.GroupData)var2).func_111104_a(this.o.s);
         }
      }

      if (var2 instanceof EntitySpider.GroupData) {
         int var5 = ((EntitySpider.GroupData)var2).potionEffectId;
         if (var5 > 0 && Potion.potionTypes[var5] != null) {
            this.c(new PotionEffect(var5, Integer.MAX_VALUE));
         }
      }

      return var2;
   }

   @Override
   public Item getDropItem() {
      return Items.string;
   }

   public static class AISpiderAttack extends EntityAIAttackOnCollide {
      @Override
      public double func_179512_a(EntityLivingBase var1) {
         return 4.0F + var1.J;
      }

      @Override
      public boolean continueExecuting() {
         float var1 = this.attacker.a_(1.0F);
         if (var1 >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
            this.attacker.setAttackTarget((EntityLivingBase)null);
            return false;
         } else {
            return super.continueExecuting();
         }
      }

      public AISpiderAttack(EntitySpider var1, Class<? extends Entity> var2) {
         super(var1, var2, 1.0, true);
      }
   }

   public static class AISpiderTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget {
      @Override
      public boolean shouldExecute() {
         float var1 = this.e.a_(1.0F);
         return var1 >= 0.5F ? false : super.shouldExecute();
      }

      public AISpiderTarget(EntitySpider var1, Class<T> var2) {
         super(var1, var2, true);
      }
   }

   public static class GroupData implements IEntityLivingData {
      public int potionEffectId;

      public void func_111104_a(Random var1) {
         int var2 = var1.nextInt(5);
         if (var2 <= 1) {
            this.potionEffectId = Potion.moveSpeed.id;
         } else if (var2 <= 2) {
            this.potionEffectId = Potion.damageBoost.id;
         } else if (var2 <= 3) {
            this.potionEffectId = Potion.regeneration.id;
         } else if (var2 <= 4) {
            this.potionEffectId = Potion.invisibility.id;
         }
      }
   }
}
