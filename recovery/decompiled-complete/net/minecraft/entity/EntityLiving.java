package net.minecraft.entity;

import java.util.UUID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.scoreboard.Team;
import net.minecraft.src.Config;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.optifine.reflect.Reflector;

public abstract class EntityLiving extends EntityLivingBase {
   public boolean canPickUpLoot;
   public Entity leashedToEntity;
   public int a_;
   public EntityJumpHelper g;
   public String teamUuidString;
   public EntityLookHelper lookHelper;
   public boolean isLeashed;
   public ItemStack[] equipment = new ItemStack[5];
   public EntityLivingBase attackTarget;
   public EntityAITasks i;
   public UUID teamUuid;
   public NBTTagCompound leashNBTTag;
   public int b_;
   public EntitySenses senses;
   public EntityAITasks bi;
   public boolean persistenceRequired;
   public EntityBodyHelper bodyHelper;
   public EntityMoveHelper f;
   public PathNavigate h;
   public float[] bj = new float[5];

   public boolean isAIDisabled() {
      return this.ac.getWatchableObjectByte(15) != 0;
   }

   @Override
   public boolean replaceItemInInventory(int var1, ItemStack var2) {
      int var3;
      if (var1 == 99) {
         var3 = 0;
      } else {
         var3 = var1 - 100 + 1;
         if (var3 < 0 || var3 >= this.equipment.length) {
            return false;
         }
      }

      if (var2 != null && getArmorPosition(var2) != var3 && (var3 != 4 || !(var2.getItem() instanceof ItemBlock))) {
         return false;
      } else {
         this.setCurrentItemOrArmor(var3, var2);
         return true;
      }
   }

   public void setEnchantmentBasedOnDifficulty(DifficultyInstance var1) {
      float var2 = var1.getClampedAdditionalDifficulty();
      if (this.getHeldItem() != null && this.V.nextFloat() < 0.25F * var2) {
         EnchantmentHelper.addRandomEnchantment(this.V, this.getHeldItem(), (int)(5.0F + var2 * this.V.nextInt(18)));
      }

      for (int var3 = 0; var3 < 4; var3++) {
         ItemStack var4 = this.getCurrentArmor(var3);
         if (var4 != null && this.V.nextFloat() < 0.5F * var2) {
            EnchantmentHelper.addRandomEnchantment(this.V, var4, (int)(5.0F + var2 * this.V.nextInt(18)));
         }
      }
   }

   public static Item getArmorItemForSlot(int var0, int var1) {
      switch (var0) {
         case 4:
            if (var1 == 0) {
               return Items.leather_helmet;
            } else if (var1 == 1) {
               return Items.golden_helmet;
            } else if (var1 == 2) {
               return Items.chainmail_helmet;
            } else if (var1 == 3) {
               return Items.iron_helmet;
            } else if (var1 == 4) {
               return Items.diamond_helmet;
            }
         case 3:
            if (var1 == 0) {
               return Items.leather_chestplate;
            } else if (var1 == 1) {
               return Items.golden_chestplate;
            } else if (var1 == 2) {
               return Items.chainmail_chestplate;
            } else if (var1 == 3) {
               return Items.iron_chestplate;
            } else if (var1 == 4) {
               return Items.diamond_chestplate;
            }
         case 2:
            if (var1 == 0) {
               return Items.leather_leggings;
            } else if (var1 == 1) {
               return Items.golden_leggings;
            } else if (var1 == 2) {
               return Items.chainmail_leggings;
            } else if (var1 == 3) {
               return Items.iron_leggings;
            } else if (var1 == 4) {
               return Items.diamond_leggings;
            }
         case 1:
            if (var1 == 0) {
               return Items.leather_boots;
            } else if (var1 == 1) {
               return Items.golden_boots;
            } else if (var1 == 2) {
               return Items.chainmail_boots;
            } else if (var1 == 3) {
               return Items.iron_boots;
            } else if (var1 == 4) {
               return Items.diamond_boots;
            }
         default:
            return null;
      }
   }

   public void a(boolean var1, boolean var2) {
      if (this.isLeashed) {
         this.isLeashed = false;
         this.leashedToEntity = null;
         if (!this.o.D && var2) {
            this.dropItem(Items.lead, 1);
         }

         if (!this.o.D && var1 && this.o instanceof WorldServer) {
            ((WorldServer)this.o).getEntityTracker().sendToAllTrackingEntity(this, new S1BPacketEntityAttach(1, this, (Entity)null));
         }
      }
   }

   public EntitySenses getEntitySenses() {
      return this.senses;
   }

   public void setEquipmentDropChance(int var1, float var2) {
      this.bj[var1] = var2;
   }

   public void enablePersistence() {
      this.persistenceRequired = true;
   }

   @Override
   public void onEntityUpdate() {
      super.onEntityUpdate();
      this.o.B.startSection("mobBaseTick");
      if (this.isEntityAlive() && this.V.nextInt(1000) < this.a_++) {
         this.a_ = -this.getTalkInterval();
         this.playLivingSound();
      }

      this.o.B.endSection();
   }

   public boolean canBeSteered() {
      return false;
   }

   public void a(Entity var1, float var2, float var3) {
      double var4 = var1.s - this.s;
      double var6 = var1.u - this.u;
      double var8;
      if (var1 instanceof EntityLivingBase) {
         EntityLivingBase var10 = (EntityLivingBase)var1;
         var8 = var10.t + var10.getEyeHeight() - (this.t + this.getEyeHeight());
      } else {
         var8 = (var1.getEntityBoundingBox().b + var1.getEntityBoundingBox().e) / 2.0 - (this.t + this.getEyeHeight());
      }

      double var14 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
      float var12 = (float)(MathHelper.atan2(var6, var4) * 180.0 / Math.PI) - 90.0F;
      float var13 = (float)(-(MathHelper.atan2(var8, var14) * 180.0 / Math.PI));
      this.z = this.updateRotation(this.z, var13, var3);
      this.y = this.updateRotation(this.y, var12, var2);
   }

   @Override
   public ItemStack getEquipmentInSlot(int var1) {
      return this.equipment[var1];
   }

   @Override
   public int getMaxFallHeight() {
      if (this.getAttackTarget() == null) {
         return 3;
      } else {
         int var1 = (int)(this.getHealth() - this.getMaxHealth() * 0.33F);
         var1 -= (3 - this.o.getDifficulty().getDifficultyId()) * 4;
         if (var1 < 0) {
            var1 = 0;
         }

         return var1 + 3;
      }
   }

   @Override
   public boolean a_(EntityPlayer var1) {
      if (this.getLeashed() && this.getLeashedToEntity() == var1) {
         this.a(true, !var1.bA.isCreativeMode);
         return true;
      } else {
         ItemStack var2 = var1.bi.getCurrentItem();
         if (var2 != null && var2.getItem() == Items.lead && this.allowLeashing()) {
            if (!(this instanceof EntityTameable) || !((EntityTameable)this).isTamed()) {
               this.setLeashedToEntity(var1, true);
               var2.stackSize--;
               return true;
            }

            if (((EntityTameable)this).isOwner(var1)) {
               this.setLeashedToEntity(var1, true);
               var2.stackSize--;
               return true;
            }
         }

         return this.interact(var1) ? true : super.a_(var1);
      }
   }

   public void setLeashedToEntity(Entity var1, boolean var2) {
      this.isLeashed = true;
      this.leashedToEntity = var1;
      if (!this.o.D && var2 && this.o instanceof WorldServer) {
         ((WorldServer)this.o).getEntityTracker().sendToAllTrackingEntity(this, new S1BPacketEntityAttach(1, this, this.leashedToEntity));
      }
   }

   public EntityJumpHelper r() {
      return this.g;
   }

   public PathNavigate s() {
      return this.h;
   }

   @Override
   public void updateEntityActionState() {
      this.aQ++;
      this.o.B.startSection("checkDespawn");
      this.despawnEntity();
      this.o.B.endSection();
      this.o.B.startSection("sensing");
      this.senses.clearSensingCache();
      this.o.B.endSection();
      this.o.B.startSection("targetSelector");
      this.bi.onUpdateTasks();
      this.o.B.endSection();
      this.o.B.startSection("goalSelector");
      this.i.onUpdateTasks();
      this.o.B.endSection();
      this.o.B.startSection("navigation");
      this.h.onUpdateNavigation();
      this.o.B.endSection();
      this.o.B.startSection("mob tick");
      this.updateAITasks();
      this.o.B.endSection();
      this.o.B.startSection("controls");
      this.o.B.startSection("move");
      this.f.onUpdateMoveHelper();
      this.o.B.endStartSection("look");
      this.lookHelper.onUpdateLook();
      this.o.B.endStartSection("jump");
      this.g.doJump();
      this.o.B.endSection();
      this.o.B.endSection();
   }

   public EntityMoveHelper q() {
      return this.f;
   }

   public void setAttackTarget(EntityLivingBase var1) {
      this.attackTarget = var1;
      Reflector.callVoid(Reflector.ForgeHooks_onLivingSetAttackTarget, this, var1);
   }

   public boolean canDespawn() {
      return true;
   }

   public void eatGrassBonus() {
   }

   @Override
   public float updateDistance(float var1, float var2) {
      this.bodyHelper.updateRenderAngles();
      return var2;
   }

   @Override
   public ItemStack getCurrentArmor(int var1) {
      return this.equipment[var1 + 1];
   }

   public void spawnExplosionParticle() {
      if (this.o.D) {
         for (int var1 = 0; var1 < 20; var1++) {
            double var2 = this.V.nextGaussian() * 0.02;
            double var4 = this.V.nextGaussian() * 0.02;
            double var6 = this.V.nextGaussian() * 0.02;
            double var8 = 10.0;
            this.o
               .spawnParticle(
                  EnumParticleTypes.EXPLOSION_NORMAL,
                  this.s + this.V.nextFloat() * this.J * 2.0F - this.J - var2 * var8,
                  this.t + this.V.nextFloat() * this.K - var4 * var8,
                  this.u + this.V.nextFloat() * this.J * 2.0F - this.J - var6 * var8,
                  var2,
                  var4,
                  var6
               );
         }
      } else {
         this.o.setEntityState(this, (byte)20);
      }
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.o.B.startSection("looting");
      if (!this.o.D && this.canPickUpLoot() && !this.dead && this.o.Q().getBoolean("mobGriefing")) {
         for (EntityItem var2 : this.o.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().expand(1.0, 0.0, 1.0))) {
            if (!var2.I && var2.getEntityItem() != null && !var2.cannotPickup()) {
               this.updateEquipmentIfNeeded(var2);
            }
         }
      }

      this.o.B.endSection();
   }

   public boolean canAttackClass(Class<? extends EntityLivingBase> var1) {
      return var1 != EntityGhast.class;
   }

   public float updateRotation(float var1, float var2, float var3) {
      float var4 = MathHelper.wrapAngleTo180_float(var2 - var1);
      if (var4 > var3) {
         var4 = var3;
      }

      if (var4 < -var3) {
         var4 = -var3;
      }

      return var1 + var4;
   }

   public float getRenderSizeModifier() {
      return 1.0F;
   }

   public EntityLivingBase getAttackTarget() {
      return this.attackTarget;
   }

   @Override
   public void dropEquipment(boolean var1, int var2) {
      for (int var3 = 0; var3 < this.getInventory().length; var3++) {
         ItemStack var4 = this.getEquipmentInSlot(var3);
         boolean var5 = this.bj[var3] > 1.0F;
         if (var4 != null && (var1 || var5) && this.V.nextFloat() - var2 * 0.01F < this.bj[var3]) {
            if (!var5 && var4.isItemStackDamageable()) {
               int var6 = Math.max(var4.getMaxDamage() - 25, 1);
               int var7 = var4.getMaxDamage() - this.V.nextInt(this.V.nextInt(var6) + 1);
               if (var7 > var6) {
                  var7 = var6;
               }

               if (var7 < 1) {
                  var7 = 1;
               }

               var4.setItemDamage(var7);
            }

            this.a(var4, 0.0F);
         }
      }
   }

   public void setCanPickUpLoot(boolean var1) {
      this.canPickUpLoot = var1;
   }

   public void setEquipmentBasedOnDifficulty(DifficultyInstance var1) {
      if (this.V.nextFloat() < 0.15F * var1.getClampedAdditionalDifficulty()) {
         int var2 = this.V.nextInt(2);
         float var3 = this.o.getDifficulty() == EnumDifficulty.HARD ? 0.1F : 0.25F;
         if (this.V.nextFloat() < 0.095F) {
            var2++;
         }

         if (this.V.nextFloat() < 0.095F) {
            var2++;
         }

         if (this.V.nextFloat() < 0.095F) {
            var2++;
         }

         for (int var4 = 3; var4 >= 0; var4--) {
            ItemStack var5 = this.getCurrentArmor(var4);
            if (var4 < 3 && this.V.nextFloat() < var3) {
               break;
            }

            if (var5 == null) {
               Item var6 = getArmorItemForSlot(var4 + 1, var2);
               if (var6 != null) {
                  this.setCurrentItemOrArmor(var4 + 1, new ItemStack(var6));
               }
            }
         }
      }
   }

   public boolean method_26952() {
      if (this.o_()) {
         return false;
      } else if (this.au > 0) {
         return false;
      } else if (this.W < 20) {
         return false;
      } else {
         World var1 = this.s_();
         if (var1 == null) {
            return false;
         } else if (var1.j.size() != 1) {
            return false;
         } else {
            Entity var2 = var1.j.get(0);
            double var3 = Math.max(Math.abs(this.s - var2.s) - 16.0, 0.0);
            double var5 = Math.max(Math.abs(this.u - var2.u) - 16.0, 0.0);
            double var7 = var3 * var3 + var5 * var5;
            return !this.isInRangeToRenderDist(var7);
         }
      }
   }

   public boolean getCanSpawnHere() {
      return true;
   }

   public boolean getLeashed() {
      return this.isLeashed;
   }

   public EntityLookHelper getLookHelper() {
      return this.lookHelper;
   }

   public boolean allowLeashing() {
      return !this.getLeashed() && !(this instanceof IMob);
   }

   public String getLivingSound() {
      return null;
   }

   public void updateAITasks() {
   }

   @Override
   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
      this.equipment[var1] = var2;
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(15, (byte)0);
   }

   public boolean canPickUpLoot() {
      return this.canPickUpLoot;
   }

   public boolean isNoDespawnRequired() {
      return this.persistenceRequired;
   }

   @Override
   public Team getTeam() {
      UUID var1 = this.aK();
      if (this.teamUuid != var1) {
         this.teamUuid = var1;
         this.teamUuidString = var1.toString();
      }

      return this.o.Z().getPlayersTeam(this.teamUuidString);
   }

   @Override
   public boolean isServerWorld() {
      return super.isServerWorld() && !this.isAIDisabled();
   }

   @Override
   public ItemStack getHeldItem() {
      return this.equipment[0];
   }

   public Item getDropItem() {
      return null;
   }

   public void recreateLeash() {
      if (this.isLeashed && this.leashNBTTag != null) {
         if (this.leashNBTTag.hasKey("UUIDMost", 4) && this.leashNBTTag.hasKey("UUIDLeast", 4)) {
            UUID var4 = new UUID(this.leashNBTTag.getLong("UUIDMost"), this.leashNBTTag.getLong("UUIDLeast"));

            for (EntityLivingBase var3 : this.o.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(10.0, 10.0, 10.0))) {
               if (var3.aK().equals(var4)) {
                  this.leashedToEntity = var3;
                  break;
               }
            }
         } else if (this.leashNBTTag.hasKey("X", 99) && this.leashNBTTag.hasKey("Y", 99) && this.leashNBTTag.hasKey("Z", 99)) {
            BlockPos var1 = new BlockPos(this.leashNBTTag.getInteger("X"), this.leashNBTTag.getInteger("Y"), this.leashNBTTag.getInteger("Z"));
            EntityLeashKnot var2 = EntityLeashKnot.getKnotForPosition(this.o, var1);
            if (var2 == null) {
               var2 = EntityLeashKnot.createKnot(this.o, var1);
            }

            this.leashedToEntity = var2;
         } else {
            this.a(false, true);
         }
      }

      this.leashNBTTag = null;
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 20) {
         this.spawnExplosionParticle();
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("CanPickUpLoot", this.canPickUpLoot());
      var1.setBoolean("PersistenceRequired", this.persistenceRequired);
      NBTTagList var2 = new NBTTagList();

      for (int var3 = 0; var3 < this.equipment.length; var3++) {
         NBTTagCompound var4 = new NBTTagCompound();
         if (this.equipment[var3] != null) {
            this.equipment[var3].writeToNBT(var4);
         }

         var2.appendTag(var4);
      }

      var1.setTag("Equipment", var2);
      NBTTagList var6 = new NBTTagList();

      for (int var7 = 0; var7 < this.bj.length; var7++) {
         var6.appendTag(new NBTTagFloat(this.bj[var7]));
      }

      var1.setTag("DropChances", var6);
      var1.setBoolean("Leashed", this.isLeashed);
      if (this.leashedToEntity != null) {
         NBTTagCompound var8 = new NBTTagCompound();
         if (this.leashedToEntity instanceof EntityLivingBase) {
            var8.setLong("UUIDMost", this.leashedToEntity.aK().getMostSignificantBits());
            var8.setLong("UUIDLeast", this.leashedToEntity.aK().getLeastSignificantBits());
         } else if (this.leashedToEntity instanceof EntityHanging) {
            BlockPos var5 = ((EntityHanging)this.leashedToEntity).n();
            var8.setInteger("X", var5.getX());
            var8.setInteger("Y", var5.getY());
            var8.setInteger("Z", var5.getZ());
         }

         var1.setTag("Leash", var8);
      }

      if (this.isAIDisabled()) {
         var1.setBoolean("NoAI", this.isAIDisabled());
      }
   }

   public int getMaxSpawnedInChunk() {
      return 4;
   }

   public boolean interact(EntityPlayer var1) {
      return false;
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      Item var3 = this.getDropItem();
      if (var3 != null) {
         int var4 = this.V.nextInt(3);
         if (var2 > 0) {
            var4 += this.V.nextInt(var2 + 1);
         }

         for (int var5 = 0; var5 < var4; var5++) {
            this.dropItem(var3, 1);
         }
      }
   }

   public PathNavigate getNewNavigator(World var1) {
      return new PathNavigateGround(this, var1);
   }

   public void setMoveForward(float var1) {
      this.ba = var1;
   }

   @Override
   public void setAIMoveSpeed(float var1) {
      super.setAIMoveSpeed(var1);
      this.setMoveForward(var1);
   }

   public void setNoAI(boolean var1) {
      this.ac.updateObject(15, (byte)(var1 ? 1 : 0));
   }

   public void updateLeashedState() {
      if (this.leashNBTTag != null) {
         this.recreateLeash();
      }

      if (this.isLeashed) {
         if (!this.isEntityAlive()) {
            this.a(true, true);
         }

         if (this.leashedToEntity == null || this.leashedToEntity.I) {
            this.a(true, true);
         }
      }
   }

   public boolean a_(ItemStack var1) {
      return true;
   }

   public Entity getLeashedToEntity() {
      return this.leashedToEntity;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if (var1.hasKey("CanPickUpLoot", 1)) {
         this.setCanPickUpLoot(var1.getBoolean("CanPickUpLoot"));
      }

      this.persistenceRequired = var1.getBoolean("PersistenceRequired");
      if (var1.hasKey("Equipment", 9)) {
         NBTTagList var2 = var1.getTagList("Equipment", 10);

         for (int var3 = 0; var3 < this.equipment.length; var3++) {
            this.equipment[var3] = ItemStack.loadItemStackFromNBT(var2.getCompoundTagAt(var3));
         }
      }

      if (var1.hasKey("DropChances", 9)) {
         NBTTagList var4 = var1.getTagList("DropChances", 5);

         for (int var5 = 0; var5 < var4.tagCount(); var5++) {
            this.bj[var5] = var4.getFloatAt(var5);
         }
      }

      this.isLeashed = var1.getBoolean("Leashed");
      if (this.isLeashed && var1.hasKey("Leash", 10)) {
         this.leashNBTTag = var1.getCompoundTag("Leash");
      }

      this.setNoAI(var1.getBoolean("NoAI"));
   }

   public IEntityLivingData onInitialSpawn(DifficultyInstance var1, IEntityLivingData var2) {
      this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random spawn bonus", this.V.nextGaussian() * 0.05, 1));
      return var2;
   }

   public static int getArmorPosition(ItemStack var0) {
      if (var0.getItem() == Item.getItemFromBlock(Blocks.pumpkin) || var0.getItem() == Items.skull) {
         return 4;
      } else {
         if (var0.getItem() instanceof ItemArmor) {
            switch (((ItemArmor)var0.getItem()).armorType) {
               case 0:
                  return 4;
               case 1:
                  return 3;
               case 2:
                  return 2;
               case 3:
                  return 1;
            }
         }

         return 0;
      }
   }

   public void playLivingSound() {
      String var1 = this.getLivingSound();
      if (var1 != null) {
         this.playSound(var1, this.getSoundVolume(), this.bC());
      }
   }

   public void updateEquipmentIfNeeded(EntityItem var1) {
      ItemStack var2 = var1.getEntityItem();
      int var3 = getArmorPosition(var2);
      if (var3 > -1) {
         boolean var4 = true;
         ItemStack var5 = this.getEquipmentInSlot(var3);
         if (var5 != null) {
            if (var3 == 0) {
               if (var2.getItem() instanceof ItemSword && !(var5.getItem() instanceof ItemSword)) {
                  var4 = true;
               } else if (var2.getItem() instanceof ItemSword && var5.getItem() instanceof ItemSword) {
                  ItemSword var8 = (ItemSword)var2.getItem();
                  ItemSword var10 = (ItemSword)var5.getItem();
                  if (var8.getDamageVsEntity() != var10.getDamageVsEntity()) {
                     var4 = var8.getDamageVsEntity() > var10.getDamageVsEntity();
                  } else {
                     var4 = var2.getMetadata() > var5.getMetadata() || var2.hasTagCompound() && !var5.hasTagCompound();
                  }
               } else if (var2.getItem() instanceof ItemBow && var5.getItem() instanceof ItemBow) {
                  var4 = var2.hasTagCompound() && !var5.hasTagCompound();
               } else {
                  var4 = false;
               }
            } else if (var2.getItem() instanceof ItemArmor && !(var5.getItem() instanceof ItemArmor)) {
               var4 = true;
            } else if (var2.getItem() instanceof ItemArmor && var5.getItem() instanceof ItemArmor) {
               ItemArmor var6 = (ItemArmor)var2.getItem();
               ItemArmor var7 = (ItemArmor)var5.getItem();
               if (var6.damageReduceAmount != var7.damageReduceAmount) {
                  var4 = var6.damageReduceAmount > var7.damageReduceAmount;
               } else {
                  var4 = var2.getMetadata() > var5.getMetadata() || var2.hasTagCompound() && !var5.hasTagCompound();
               }
            } else {
               var4 = false;
            }
         }

         if (var4 && this.a_(var2)) {
            if (var5 != null && this.V.nextFloat() - 0.1F < this.bj[var3]) {
               this.a(var5, 0.0F);
            }

            if (var2.getItem() == Items.diamond && var1.getThrower() != null) {
               EntityPlayer var9 = this.o.getPlayerEntityByName(var1.getThrower());
               if (var9 != null) {
                  var9.triggerAchievement(AchievementList.diamondsToYou);
               }
            }

            this.setCurrentItemOrArmor(var3, var2);
            this.bj[var3] = 2.0F;
            this.persistenceRequired = true;
            this.a(var1, 1);
            var1.setDead();
         }
      }
   }

   public int getTalkInterval() {
      return 80;
   }

   public int getVerticalFaceSpeed() {
      return 40;
   }

   @Override
   public void onUpdate() {
      if (Config.isSmoothWorld() && this.method_26952()) {
         this.onUpdateMinimal();
      } else {
         super.onUpdate();
         if (!this.o.D) {
            this.updateLeashedState();
         }
      }
   }

   @Override
   public ItemStack[] getInventory() {
      return this.equipment;
   }

   public EntityLiving(World var1) {
      super(var1);
      this.teamUuid = null;
      this.teamUuidString = null;
      this.i = new EntityAITasks(var1 != null && var1.B != null ? var1.B : null);
      this.bi = new EntityAITasks(var1 != null && var1.B != null ? var1.B : null);
      this.lookHelper = new EntityLookHelper(this);
      this.f = new EntityMoveHelper(this);
      this.g = new EntityJumpHelper(this);
      this.bodyHelper = new EntityBodyHelper(this);
      this.h = this.getNewNavigator(var1);
      this.senses = new EntitySenses(this);

      for (int var2 = 0; var2 < this.bj.length; var2++) {
         this.bj[var2] = 0.085F;
      }
   }

   public void onUpdateMinimal() {
      this.aQ++;
      if (this instanceof EntityMob) {
         float var1 = this.a_(1.0F);
         if (var1 > 0.5F) {
            this.aQ += 2;
         }
      }

      this.despawnEntity();
   }

   public boolean isNotColliding() {
      return this.o.checkNoEntityCollision(this.getEntityBoundingBox(), this)
         && this.o.a(this, this.getEntityBoundingBox()).isEmpty()
         && !this.o.isAnyLiquid(this.getEntityBoundingBox());
   }

   @Override
   public int getExperiencePoints(EntityPlayer var1) {
      if (this.b_ > 0) {
         int var2 = this.b_;
         ItemStack[] var3 = this.getInventory();

         for (int var4 = 0; var4 < var3.length; var4++) {
            if (var3[var4] != null && this.bj[var4] <= 1.0F) {
               var2 += 1 + this.V.nextInt(3);
            }
         }

         return var2;
      } else {
         return this.b_;
      }
   }

   public void despawnEntity() {
      Object var1 = null;
      Object var2 = Reflector.getFieldValue(Reflector.Event_Result_DEFAULT);
      Object var3 = Reflector.getFieldValue(Reflector.Event_Result_DENY);
      if (this.persistenceRequired) {
         this.aQ = 0;
      } else if ((this.aQ & 31) != 31 || (var1 = Reflector.call(Reflector.ForgeEventFactory_canEntityDespawn, this)) == var2) {
         EntityPlayer var4 = this.o.getClosestPlayerToEntity(this, -1.0);
         if (var4 != null) {
            double var5 = var4.s - this.s;
            double var7 = var4.t - this.t;
            double var9 = var4.u - this.u;
            double var11 = var5 * var5 + var7 * var7 + var9 * var9;
            if (this.canDespawn() && var11 > 16384.0) {
               this.setDead();
            }

            if (this.aQ > 600 && this.V.nextInt(800) == 0 && var11 > 1024.0 && this.canDespawn()) {
               this.setDead();
            } else if (var11 < 1024.0) {
               this.aQ = 0;
            }
         }
      } else if (var1 == var3) {
         this.aQ = 0;
      } else {
         this.setDead();
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0);
   }
}
