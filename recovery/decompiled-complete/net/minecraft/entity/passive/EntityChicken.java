package net.minecraft.entity.passive;

import com.cheatbreaker.client.ui.mainmenu.ChangelogMenu;
import junit.swingui.TestSelector;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityChicken extends EntityAnimal {
   public float field_70884_g;
   public float wingRotDelta = 1.0F;
   public boolean chickenJockey;
   public float destPos;
   public ChangelogMenu field_0006;
   public int timeUntilNextEgg;
   public TestSelector field_0003;
   public float field_70888_h;
   public float wingRotation;

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
   }

   @Override
   public String getHurtSound() {
      return "mob.chicken.hurt";
   }

   @Override
   public boolean isBreedingItem(ItemStack var1) {
      return var1 != null && var1.getItem() == Items.wheat_seeds;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("IsChickenJockey", this.chickenJockey);
      var1.setInteger("EggLayTime", this.timeUntilNextEgg);
   }

   public EntityChicken createChild(EntityAgeable var1) {
      return new EntityChicken(this.o);
   }

   @Override
   public Item getDropItem() {
      return Items.feather;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.chickenJockey = var1.getBoolean("IsChickenJockey");
      if (var1.hasKey("EggLayTime")) {
         this.timeUntilNextEgg = var1.getInteger("EggLayTime");
      }
   }

   @Override
   public void updateRiderPosition() {
      super.updateRiderPosition();
      float var1 = MathHelper.sin(this.aI * (float) Math.PI / 180.0F);
      float var2 = MathHelper.cos(this.aI * (float) Math.PI / 180.0F);
      float var3 = 0.1F;
      float var4 = 0.0F;
      this.l.b(this.s + var3 * var1, this.t + this.K * 0.5F + this.l.getYOffset() + var4, this.u - var3 * var2);
      if (this.l instanceof EntityLivingBase) {
         ((EntityLivingBase)this.l).aI = this.aI;
      }
   }

   public boolean isChickenJockey() {
      return this.chickenJockey;
   }

   @Override
   public String getDeathSound() {
      return "mob.chicken.hurt";
   }

   public void setChickenJockey(boolean var1) {
      this.chickenJockey = var1;
   }

   @Override
   public float getEyeHeight() {
      return this.K;
   }

   @Override
   public int getExperiencePoints(EntityPlayer var1) {
      return this.isChickenJockey() ? 10 : super.getExperiencePoints(var1);
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(3) + this.V.nextInt(1 + var2);

      for (int var4 = 0; var4 < var3; var4++) {
         this.dropItem(Items.feather, 1);
      }

      if (this.isBurning()) {
         this.dropItem(Items.cooked_chicken, 1);
      } else {
         this.dropItem(Items.chicken, 1);
      }
   }

   public EntityChicken(World var1) {
      super(var1);
      this.setSize(0.4F, 0.7F);
      this.timeUntilNextEgg = this.V.nextInt(6000) + 6000;
      this.i.addTask(0, new EntityAISwimming(this));
      this.i.addTask(1, new EntityAIPanic(this, 1.4));
      this.i.addTask(2, new EntityAIMate(this, 1.0));
      this.i.addTask(3, new EntityAITempt(this, 1.0, Items.wheat_seeds, false));
      this.i.addTask(4, new EntityAIFollowParent(this, 1.1));
      this.i.addTask(5, new EntityAIWander(this, 1.0));
      this.i.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.i.addTask(7, new EntityAILookIdle(this));
   }

   @Override
   public String getLivingSound() {
      return "mob.chicken.say";
   }

   @Override
   public boolean canDespawn() {
      return this.isChickenJockey() && this.l == null;
   }

   @Override
   public void fall(float var1, float var2) {
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.field_70888_h = this.wingRotation;
      this.field_70884_g = this.destPos;
      this.destPos = (float)(this.destPos + (this.C ? -1 : 4) * 0.3);
      this.destPos = MathHelper.clamp_float(this.destPos, 0.0F, 1.0F);
      if (!this.C && this.wingRotDelta < 1.0F) {
         this.wingRotDelta = 1.0F;
      }

      this.wingRotDelta = (float)(this.wingRotDelta * 0.9);
      if (!this.C && this.w < 0.0) {
         this.w *= 0.6;
      }

      this.wingRotation = this.wingRotation + this.wingRotDelta * 2.0F;
      if (!this.o.D && !this.o_() && !this.isChickenJockey() && --this.timeUntilNextEgg <= 0) {
         this.playSound("mob.chicken.plop", 1.0F, (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
         this.dropItem(Items.egg, 1);
         this.timeUntilNextEgg = this.V.nextInt(6000) + 6000;
      }
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.chicken.step", 0.15F, 1.0F);
   }
}
