package net.minecraft.entity.monster;

import io.netty.buffer.PooledByteBufAllocator$PoolThreadLocalCache;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.EnumConnectionState$3;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class EntityZombie extends EntityMob {
   public EntityAIBreakDoor breakDoor = new EntityAIBreakDoor(this);
   public int conversionTime;
   public float zombieHeight;
   public EnumConnectionState$3 field_0010;
   public static UUID field_0001 = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
   public static AttributeModifier babySpeedBoostModifier = new AttributeModifier(field_0001, "Baby speed boost", 0.5, 1);
   public boolean isBreakDoorsTaskSet = false;
   public static IAttribute a = new RangedAttribute((IAttribute)null, "zombie.spawnReinforcements", 0.0, 0.0, 1.0)
      .setDescription("Spawn Reinforcements Chance");
   public float zombieWidth = -1.0F;
   public PooledByteBufAllocator$PoolThreadLocalCache field_0007;
   public WorldProvider field_0009;

   public void multiplySize(float var1) {
      super.setSize(this.zombieWidth * var1, this.zombieHeight * var1);
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   @Override
   public void setEquipmentBasedOnDifficulty(DifficultyInstance var1) {
      super.setEquipmentBasedOnDifficulty(var1);
      if (this.V.nextFloat() < (this.o.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
         int var2 = this.V.nextInt(3);
         if (var2 == 0) {
            this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
         } else {
            this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel));
         }
      }
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.zombie.step", 0.15F, 1.0F);
   }

   @Override
   public String getDeathSound() {
      return "mob.zombie.death";
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.getCurrentEquippedItem();
      if (var2 != null && var2.getItem() == Items.golden_apple && var2.getMetadata() == 0 && this.isVillager() && this.isPotionActive(Potion.weakness)) {
         if (!var1.bA.isCreativeMode) {
            var2.stackSize--;
         }

         if (var2.stackSize <= 0) {
            var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
         }

         if (!this.o.D) {
            this.startConversion(this.V.nextInt(2401) + 3600);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean isConverting() {
      return this.H().getWatchableObjectByte(14) == 1;
   }

   @Override
   public void addRandomDrop() {
      switch (this.V.nextInt(3)) {
         case 0:
            this.dropItem(Items.iron_ingot, 1);
            break;
         case 1:
            this.dropItem(Items.carrot, 1);
            break;
         case 2:
            this.dropItem(Items.potato, 1);
      }
   }

   @Override
   public boolean canDespawn() {
      return !this.isConverting();
   }

   public void setChildSize(boolean var1) {
      this.multiplySize(var1 ? 0.5F : 1.0F);
   }

   @Override
   public String getLivingSound() {
      return "mob.zombie.say";
   }

   @Override
   public void onKillEntity(EntityLivingBase var1) {
      super.onKillEntity(var1);
      if ((this.o.getDifficulty() == EnumDifficulty.NORMAL || this.o.getDifficulty() == EnumDifficulty.HARD) && var1 instanceof EntityVillager) {
         if (this.o.getDifficulty() != EnumDifficulty.HARD && this.V.nextBoolean()) {
            return;
         }

         EntityLiving var2 = (EntityLiving)var1;
         EntityZombie var3 = new EntityZombie(this.o);
         var3.copyLocationAndAnglesFrom(var1);
         this.o.removeEntity(var1);
         var3.onInitialSpawn(this.o.E(new BlockPos(var3)), (IEntityLivingData)null);
         var3.setVillager(true);
         if (var1.o_()) {
            var3.setChild(true);
         }

         var3.setNoAI(var2.isAIDisabled());
         if (var2.u_()) {
            var3.a(var2.aM());
            var3.setAlwaysRenderNameTag(var2.getAlwaysRenderNameTag());
         }

         this.o.spawnEntityInWorld(var3);
         this.o.playAuxSFXAtEntity((EntityPlayer)null, 1016, new BlockPos((int)this.s, (int)this.t, (int)this.u), 0);
      }
   }

   @Override
   public void onDeath(DamageSource var1) {
      super.onDeath(var1);
      if (var1.getEntity() instanceof EntityCreeper
         && !(this instanceof EntityPigZombie)
         && ((EntityCreeper)var1.getEntity()).getPowered()
         && ((EntityCreeper)var1.getEntity()).isAIEnabled()) {
         ((EntityCreeper)var1.getEntity()).func_175493_co();
         this.a(new ItemStack(Items.skull, 1, 2), 0.0F);
      }
   }

   public boolean isBreakDoorsTaskSet() {
      return this.isBreakDoorsTaskSet;
   }

   public void setChild(boolean var1) {
      this.H().updateObject(12, (byte)(var1 ? 1 : 0));
      if (this.o != null && !this.o.D) {
         IAttributeInstance var2 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         var2.removeModifier(babySpeedBoostModifier);
         if (var1) {
            var2.applyModifier(babySpeedBoostModifier);
         }
      }

      this.setChildSize(var1);
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23F);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
      this.getAttributeMap().registerAttribute(a).setBaseValue(this.V.nextDouble() * 0.1F);
   }

   public int getConversionTimeBoost() {
      int var1 = 1;
      if (this.V.nextFloat() < 0.01F) {
         int var2 = 0;
         BlockPos$MutableBlockPos var3 = new BlockPos$MutableBlockPos();

         for (int var4 = (int)this.s - 4; var4 < (int)this.s + 4 && var2 < 14; var4++) {
            for (int var5 = (int)this.t - 4; var5 < (int)this.t + 4 && var2 < 14; var5++) {
               for (int var6 = (int)this.u - 4; var6 < (int)this.u + 4 && var2 < 14; var6++) {
                  Block var7 = this.o.getBlockState(var3.set(var4, var5, var6)).getBlock();
                  if (var7 == Blocks.iron_bars || var7 == Blocks.bed) {
                     if (this.V.nextFloat() < 0.3F) {
                        var1++;
                     }

                     var2++;
                  }
               }
            }
         }
      }

      return var1;
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 16) {
         if (!this.R()) {
            this.o.playSound(this.s + 0.5, this.t + 0.5, this.u + 0.5, "mob.zombie.remedy", 1.0F + this.V.nextFloat(), this.V.nextFloat() * 0.7F + 0.3F, false);
         }
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public float getEyeHeight() {
      float var1 = 1.74F;
      if (this.o_()) {
         var1 = (float)(var1 - 0.81);
      }

      return var1;
   }

   @Override
   public double getYOffset() {
      return this.o_() ? 0.0 : -0.35;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if (var1.getBoolean("IsBaby")) {
         this.setChild(true);
      }

      if (var1.getBoolean("IsVillager")) {
         this.setVillager(true);
      }

      if (var1.hasKey("ConversionTime", 99) && var1.getInteger("ConversionTime") > -1) {
         this.startConversion(var1.getInteger("ConversionTime"));
      }

      this.setBreakDoorsAItask(var1.getBoolean("CanBreakDoors"));
   }

   public void convertToVillager() {
      EntityVillager var1 = new EntityVillager(this.o);
      var1.copyLocationAndAnglesFrom(this);
      var1.onInitialSpawn(this.o.E(new BlockPos(var1)), (IEntityLivingData)null);
      var1.setLookingForHome();
      if (this.o_()) {
         var1.setGrowingAge(-24000);
      }

      this.o.removeEntity(this);
      var1.setNoAI(this.isAIDisabled());
      if (this.u_()) {
         var1.a(this.aM());
         var1.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
      }

      this.o.spawnEntityInWorld(var1);
      var1.c(new PotionEffect(Potion.confusion.id, 200, 0));
      this.o.playAuxSFXAtEntity((EntityPlayer)null, 1017, new BlockPos((int)this.s, (int)this.t, (int)this.u), 0);
   }

   @Override
   public boolean o_() {
      return this.H().getWatchableObjectByte(12) == 1;
   }

   public void setVillager(boolean var1) {
      this.H().updateObject(13, (byte)(var1 ? 1 : 0));
   }

   @Override
   public int getExperiencePoints(EntityPlayer var1) {
      if (this.o_()) {
         this.b_ = (int)(this.b_ * 2.5F);
      }

      return super.getExperiencePoints(var1);
   }

   @Override
   public void k_() {
      super.k_();
      this.H().addObject(12, (byte)0);
      this.H().addObject(13, (byte)0);
      this.H().addObject(14, (byte)0);
   }

   @Override
   public Item getDropItem() {
      return Items.rotten_flesh;
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (super.attackEntityFrom(var1, var2)) {
         EntityLivingBase var3 = this.getAttackTarget();
         if (var3 == null && var1.getEntity() instanceof EntityLivingBase) {
            var3 = (EntityLivingBase)var1.getEntity();
         }

         if (var3 != null && this.o.getDifficulty() == EnumDifficulty.HARD && this.V.nextFloat() < this.getEntityAttribute(a).getAttributeValue()) {
            int var4 = MathHelper.floor_double(this.s);
            int var5 = MathHelper.floor_double(this.t);
            int var6 = MathHelper.floor_double(this.u);
            EntityZombie var7 = new EntityZombie(this.o);

            for (int var8 = 0; var8 < 50; var8++) {
               int var9 = var4 + MathHelper.getRandomIntegerInRange(this.V, 7, 40) * MathHelper.getRandomIntegerInRange(this.V, -1, 1);
               int var10 = var5 + MathHelper.getRandomIntegerInRange(this.V, 7, 40) * MathHelper.getRandomIntegerInRange(this.V, -1, 1);
               int var11 = var6 + MathHelper.getRandomIntegerInRange(this.V, 7, 40) * MathHelper.getRandomIntegerInRange(this.V, -1, 1);
               if (World.doesBlockHaveSolidTopSurface(this.o, new BlockPos(var9, var10 - 1, var11))
                  && this.o.getLightFromNeighbors(new BlockPos(var9, var10, var11)) < 10) {
                  var7.b(var9, var10, var11);
                  if (!this.o.isAnyPlayerWithinRangeAt(var9, var10, var11, 7.0)
                     && this.o.checkNoEntityCollision(var7.getEntityBoundingBox(), var7)
                     && this.o.a(var7, var7.getEntityBoundingBox()).isEmpty()
                     && !this.o.isAnyLiquid(var7.getEntityBoundingBox())) {
                     this.o.spawnEntityInWorld(var7);
                     var7.setAttackTarget(var3);
                     var7.onInitialSpawn(this.o.E(new BlockPos(var7)), (IEntityLivingData)null);
                     this.getEntityAttribute(a).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05F, 0));
                     var7.getEntityAttribute(a).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05F, 0));
                     break;
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void onUpdate() {
      if (!this.o.D && this.isConverting()) {
         int var1 = this.getConversionTimeBoost();
         this.conversionTime -= var1;
         if (this.conversionTime <= 0) {
            this.convertToVillager();
         }
      }

      super.onUpdate();
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance var1, IEntityLivingData var2) {
      var2 = super.onInitialSpawn(var1, var2);
      float var3 = var1.getClampedAdditionalDifficulty();
      this.setCanPickUpLoot(this.V.nextFloat() < 0.55F * var3);
      if (var2 == null) {
         var2 = new EntityZombie$GroupData(this, this.o.s.nextFloat() < 0.05F, this.o.s.nextFloat() < 0.05F, null);
      }

      if (var2 instanceof EntityZombie$GroupData) {
         EntityZombie$GroupData var4 = (EntityZombie$GroupData)var2;
         if (var4.isVillager) {
            this.setVillager(true);
         }

         if (var4.isChild) {
            this.setChild(true);
            if (this.o.s.nextFloat() < 0.05) {
               List var5 = this.o.getEntitiesWithinAABB(EntityChicken.class, this.getEntityBoundingBox().expand(5.0, 3.0, 5.0), EntitySelectors.IS_STANDALONE);
               if (!var5.isEmpty()) {
                  EntityChicken var6 = (EntityChicken)var5.get(0);
                  var6.setChickenJockey(true);
                  this.mountEntity(var6);
               }
            } else if (this.o.s.nextFloat() < 0.05) {
               EntityChicken var10 = new EntityChicken(this.o);
               var10.a_(this.s, this.t, this.u, this.y, 0.0F);
               var10.onInitialSpawn(var1, (IEntityLivingData)null);
               var10.setChickenJockey(true);
               this.o.spawnEntityInWorld(var10);
               this.mountEntity(var10);
            }
         }
      }

      this.setBreakDoorsAItask(this.V.nextFloat() < var3 * 0.1F);
      this.setEquipmentBasedOnDifficulty(var1);
      this.setEnchantmentBasedOnDifficulty(var1);
      if (this.getEquipmentInSlot(4) == null) {
         Calendar var8 = this.o.getCurrentDate();
         if (var8.get(2) + 1 == 10 && var8.get(5) == 31 && this.V.nextFloat() < 0.25F) {
            this.setCurrentItemOrArmor(4, new ItemStack(this.V.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
            this.bj[4] = 0.0F;
         }
      }

      this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
         .applyModifier(new AttributeModifier("Random spawn bonus", this.V.nextDouble() * 0.05F, 0));
      double var9 = this.V.nextDouble() * 1.5 * var3;
      if (var9 > 1.0) {
         this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", var9, 2));
      }

      if (this.V.nextFloat() < var3 * 0.05F) {
         this.getEntityAttribute(a).applyModifier(new AttributeModifier("Leader zombie bonus", this.V.nextDouble() * 0.25 + 0.5, 0));
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .applyModifier(new AttributeModifier("Leader zombie bonus", this.V.nextDouble() * 3.0 + 1.0, 2));
         this.setBreakDoorsAItask(true);
      }

      return var2;
   }

   public boolean isVillager() {
      return this.H().getWatchableObjectByte(13) == 1;
   }

   @Override
   public String getHurtSound() {
      return "mob.zombie.hurt";
   }

   @Override
   public boolean attackEntityAsMob(Entity var1) {
      boolean var2 = super.attackEntityAsMob(var1);
      if (var2) {
         int var3 = this.o.getDifficulty().getDifficultyId();
         if (this.getHeldItem() == null && this.isBurning() && this.V.nextFloat() < var3 * 0.3F) {
            var1.setFire(2 * var3);
         }
      }

      return var2;
   }

   public void startConversion(int var1) {
      this.conversionTime = var1;
      this.H().updateObject(14, (byte)1);
      this.removePotionEffect(Potion.weakness.id);
      this.c(new PotionEffect(Potion.damageBoost.id, var1, Math.min(this.o.getDifficulty().getDifficultyId() - 1, 0)));
      this.o.setEntityState(this, (byte)16);
   }

   public void setBreakDoorsAItask(boolean var1) {
      if (this.isBreakDoorsTaskSet != var1) {
         this.isBreakDoorsTaskSet = var1;
         if (var1) {
            this.i.addTask(1, this.breakDoor);
         } else {
            this.i.removeTask(this.breakDoor);
         }
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      if (this.o_()) {
         var1.setBoolean("IsBaby", true);
      }

      if (this.isVillager()) {
         var1.setBoolean("IsVillager", true);
      }

      var1.setInteger("ConversionTime", this.isConverting() ? this.conversionTime : -1);
      var1.setBoolean("CanBreakDoors", this.isBreakDoorsTaskSet());
   }

   public EntityZombie(World var1) {
      super(var1);
      ((PathNavigateGround)this.s()).setBreakDoors(true);
      this.i.addTask(0, new EntityAISwimming(this));
      this.i.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0, false));
      this.i.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0));
      this.i.addTask(7, new EntityAIWander(this, 1.0));
      this.i.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(8, new EntityAILookIdle(this));
      this.applyEntityAI();
      this.setSize(0.6F, 1.95F);
   }

   public void applyEntityAI() {
      this.i.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0, true));
      this.i.addTask(4, new EntityAIAttackOnCollide(this, EntityIronGolem.class, 1.0, true));
      this.i.addTask(6, new EntityAIMoveThroughVillage(this, 1.0, false));
      this.bi.addTask(1, new EntityAIHurtByTarget(this, true, EntityPigZombie.class));
      this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
      this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false));
      this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, true));
   }

   @Override
   public void setSize(float var1, float var2) {
      boolean var3 = this.zombieWidth > 0.0F && this.zombieHeight > 0.0F;
      this.zombieWidth = var1;
      this.zombieHeight = var2;
      if (!var3) {
         this.multiplySize(1.0F);
      }
   }

   @Override
   public int getTotalArmorValue() {
      int var1 = super.getTotalArmorValue() + 2;
      if (var1 > 20) {
         var1 = 20;
      }

      return var1;
   }

   @Override
   public boolean a_(ItemStack var1) {
      return var1.getItem() == Items.egg && this.o_() && this.au() ? false : super.a_(var1);
   }

   @Override
   public void onLivingUpdate() {
      if (this.o.isDaytime() && !this.o.D && !this.o_()) {
         float var1 = this.a_(1.0F);
         BlockPos var2 = new BlockPos(this.s, (double)Math.round(this.t), this.u);
         if (var1 > 0.5F && this.V.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.o.canSeeSky(var2)) {
            boolean var3 = true;
            ItemStack var4 = this.getEquipmentInSlot(4);
            if (var4 != null) {
               if (var4.isItemStackDamageable()) {
                  var4.setItemDamage(var4.getItemDamage() + this.V.nextInt(2));
                  if (var4.getItemDamage() >= var4.getMaxDamage()) {
                     this.renderBrokenItemStack(var4);
                     this.setCurrentItemOrArmor(4, (ItemStack)null);
                  }
               }

               var3 = false;
            }

            if (var3) {
               this.setFire(8);
            }
         }
      }

      if (this.au() && this.getAttackTarget() != null && this.m instanceof EntityChicken) {
         ((EntityLiving)this.m).s().setPath(this.s().getPath(), 1.5);
      }

      super.onLivingUpdate();
   }
}
