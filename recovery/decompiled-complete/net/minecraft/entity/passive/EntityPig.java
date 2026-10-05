package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiResourcePackList;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass1353;

public class EntityPig extends EntityAnimal {
   public EntityAIControlledByPlayer aiControlledByPlayer;
   public UnidentifiedClass1353 field_0000;
   public GuiResourcePackList field_0002;

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("Saddle", this.getSaddled());
   }

   @Override
   public void fall(float var1, float var2) {
      super.fall(var1, var2);
      if (var1 > 5.0F && this.l instanceof EntityPlayer) {
         ((EntityPlayer)this.l).triggerAchievement(AchievementList.flyPig);
      }
   }

   @Override
   public boolean canBeSteered() {
      ItemStack var1 = ((EntityPlayer)this.l).getHeldItem();
      return var1 != null && var1.getItem() == Items.carrot_on_a_stick;
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      if (super.interact(var1)) {
         return true;
      } else if (this.getSaddled() && !this.o.D && (this.l == null || this.l == var1)) {
         var1.mountEntity(this);
         return true;
      } else {
         return false;
      }
   }

   public void setSaddled(boolean var1) {
      if (var1) {
         this.ac.updateObject(16, (byte)1);
      } else {
         this.ac.updateObject(16, (byte)0);
      }
   }

   public boolean getSaddled() {
      return (this.ac.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public void onStruckByLightning(EntityLightningBolt var1) {
      if (!this.o.D && !this.I) {
         EntityPigZombie var2 = new EntityPigZombie(this.o);
         var2.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
         var2.a_(this.s, this.t, this.u, this.y, this.z);
         var2.setNoAI(this.isAIDisabled());
         if (this.u_()) {
            var2.a(this.aM());
            var2.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
         }

         this.o.spawnEntityInWorld(var2);
         this.setDead();
      }
   }

   @Override
   public String getDeathSound() {
      return "mob.pig.death";
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setSaddled(var1.getBoolean("Saddle"));
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, (byte)0);
   }

   public EntityPig createChild(EntityAgeable var1) {
      return new EntityPig(this.o);
   }

   @Override
   public Item getDropItem() {
      return this.isBurning() ? Items.cooked_porkchop : Items.porkchop;
   }

   public EntityPig(World var1) {
      super(var1);
      this.setSize(0.9F, 0.9F);
      ((PathNavigateGround)this.s()).setAvoidsWater(true);
      this.i.addTask(0, new EntityAISwimming(this));
      this.i.addTask(1, new EntityAIPanic(this, 1.25));
      this.i.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.3F));
      this.i.addTask(3, new EntityAIMate(this, 1.0));
      this.i.addTask(4, new EntityAITempt(this, 1.2, Items.carrot_on_a_stick, false));
      this.i.addTask(4, new EntityAITempt(this, 1.2, Items.carrot, false));
      this.i.addTask(5, new EntityAIFollowParent(this, 1.1));
      this.i.addTask(6, new EntityAIWander(this, 1.0));
      this.i.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.i.addTask(8, new EntityAILookIdle(this));
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.pig.step", 0.15F, 1.0F);
   }

   @Override
   public String getLivingSound() {
      return "mob.pig.say";
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(3) + 1 + this.V.nextInt(1 + var2);

      for (int var4 = 0; var4 < var3; var4++) {
         if (this.isBurning()) {
            this.dropItem(Items.cooked_porkchop, 1);
         } else {
            this.dropItem(Items.porkchop, 1);
         }
      }

      if (this.getSaddled()) {
         this.dropItem(Items.saddle, 1);
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.pig.say";
   }

   public EntityAIControlledByPlayer getAIControlledByPlayer() {
      return this.aiControlledByPlayer;
   }

   @Override
   public boolean isBreedingItem(ItemStack var1) {
      return var1 != null && var1.getItem() == Items.carrot;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
   }
}
