package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
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
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityCow extends EntityAnimal {
   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.cow.step", 0.15F, 1.0F);
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (var2 != null && var2.getItem() == Items.bucket && !var1.bA.isCreativeMode && !this.o_()) {
         if (var2.stackSize-- == 1) {
            var1.bi.setInventorySlotContents(var1.bi.currentItem, new ItemStack(Items.milk_bucket));
         } else if (!var1.bi.addItemStackToInventory(new ItemStack(Items.milk_bucket))) {
            var1.dropPlayerItemWithRandomChoice(new ItemStack(Items.milk_bucket, 1, 0), false);
         }

         return true;
      } else {
         return super.interact(var1);
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.cow.hurt";
   }

   public EntityCow createChild(EntityAgeable var1) {
      return new EntityCow(this.o);
   }

   @Override
   public String getDeathSound() {
      return "mob.cow.hurt";
   }

   @Override
   public float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2F);
   }

   @Override
   public Item getDropItem() {
      return Items.leather;
   }

   @Override
   public String getLivingSound() {
      return "mob.cow.say";
   }

   @Override
   public float getEyeHeight() {
      return this.K;
   }

   public EntityCow(World var1) {
      super(var1);
      this.setSize(0.9F, 1.3F);
      ((PathNavigateGround)this.s()).setAvoidsWater(true);
      this.i.addTask(0, new EntityAISwimming(this));
      this.i.addTask(1, new EntityAIPanic(this, 2.0));
      this.i.addTask(2, new EntityAIMate(this, 1.0));
      this.i.addTask(3, new EntityAITempt(this, 1.25, Items.wheat, false));
      this.i.addTask(4, new EntityAIFollowParent(this, 1.25));
      this.i.addTask(5, new EntityAIWander(this, 1.0));
      this.i.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.i.addTask(7, new EntityAILookIdle(this));
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(3) + this.V.nextInt(1 + var2);

      for (int var4 = 0; var4 < var3; var4++) {
         this.dropItem(Items.leather, 1);
      }

      var3 = this.V.nextInt(3) + 1 + this.V.nextInt(1 + var2);

      for (int var6 = 0; var6 < var3; var6++) {
         if (this.isBurning()) {
            this.dropItem(Items.cooked_beef, 1);
         } else {
            this.dropItem(Items.beef, 1);
         }
      }
   }
}
