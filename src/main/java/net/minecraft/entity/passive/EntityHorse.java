package net.minecraft.entity.passive;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityHorse extends EntityAnimal implements IInvBasic {
   public static Predicate<Entity> recoveredField3639 = new Predicate<Entity>() {
      public boolean apply(Entity var1) {
         return var1 instanceof EntityHorse && ((EntityHorse)var1).isBreeding();
      }
   };
   public float jumpPower;
   public int eatingHaystackCounter;
   public int field_110278_bp;
   public static IAttribute horseJumpStrength = new RangedAttribute((IAttribute)null, "horse.jumpStrength", 0.7, 0.0, 2.0)
      .setDescription("Jump Strength")
      .setShouldWatch(true);
   public boolean recoveredField3637;
   public int temper;
   public static String[] recoveredField3641 = new String[]{
      null,
      "textures/entity/horse/armor/horse_armor_iron.png",
      "textures/entity/horse/armor/horse_armor_gold.png",
      "textures/entity/horse/armor/horse_armor_diamond.png"
   };
   public static String[] recoveredField3640 = new String[]{"", "meo", "goo", "dio"};
   public static int[] armorValues = new int[]{0, 5, 7, 11};
   public static String[] recoveredField3638 = new String[]{
      "textures/entity/horse/horse_white.png",
      "textures/entity/horse/horse_creamy.png",
      "textures/entity/horse/horse_chestnut.png",
      "textures/entity/horse/horse_brown.png",
      "textures/entity/horse/horse_black.png",
      "textures/entity/horse/horse_gray.png",
      "textures/entity/horse/horse_darkbrown.png"
   };
   public int gallopTime;
   public boolean field_175508_bO;
   public int openMouthCounter;
   public float prevRearingAmount;
   public static String[] recoveredField3636 = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
   public boolean recoveredField3642;
   public float prevMouthOpenness;
   public static String[] recoveredField3635 = new String[]{
      null,
      "textures/entity/horse/horse_markings_white.png",
      "textures/entity/horse/horse_markings_whitefield.png",
      "textures/entity/horse/horse_markings_whitedots.png",
      "textures/entity/horse/horse_markings_blackdots.png"
   };
   public static String[] recoveredField3643 = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
   public float mouthOpenness;
   public int field_110279_bq;
   public float headLean;
   public AnimalChest horseChest;
   public int jumpRearingCounter;
   public String[] horseTexturesArray = new String[3];
   public float rearingAmount;
   public float prevHeadLean;
   public boolean field_110294_bI;
   public String texturePrefix;

   public void setHasReproduced(boolean var1) {
      this.recoveredField3642 = var1;
   }

   public boolean canWearArmor() {
      return this.getHorseType() == 0;
   }

   public void setHorseWatchableBoolean(int var1, boolean var2) {
      int var3 = this.ac.getWatchableObjectInt(16);
      if (var2) {
         this.ac.updateObject(16, var3 | var1);
      } else {
         this.ac.updateObject(16, var3 & ~var1);
      }
   }

   public void resetTexturePrefix() {
      this.texturePrefix = null;
   }

   public String[] getVariantTexturePaths() {
      if (this.texturePrefix == null) {
         this.setHorseTexturePaths();
      }

      return this.horseTexturesArray;
   }

   public float getHorseSize() {
      return 0.5F;
   }

   public void method_23885(boolean var1) {
      this.recoveredField3637 = var1;
   }

   public int increaseTemper(int var1) {
      int var2 = MathHelper.clamp_int(this.getTemper() + var1, 0, this.getMaxTemper());
      this.setTemper(var2);
      return var2;
   }

   public EntityHorse getClosestHorse(Entity var1, double var2) {
      double var4 = Double.MAX_VALUE;
      Entity var6 = null;

      for (Entity var8 : this.o.a(var1, var1.getEntityBoundingBox().addCoord(var2, var2, var2), recoveredField3639)) {
         double var9 = var8.e(var1.s, var1.t, var1.u);
         if (var9 < var4) {
            var6 = var8;
            var4 = var9;
         }
      }

      return (EntityHorse)var6;
   }

   public void updateHorseSlots() {
      if (!this.o.D) {
         this.setHorseSaddled(this.horseChest.getStackInSlot(0) != null);
         if (this.canWearArmor()) {
            this.setHorseArmorStack(this.horseChest.getStackInSlot(1));
         }
      }
   }

   public boolean getHasReproduced() {
      return this.recoveredField3642;
   }

   @Override
   public float getEyeHeight() {
      return this.K;
   }

   public void mountTo(EntityPlayer var1) {
      var1.y = this.y;
      var1.z = this.z;
      this.setEatingHaystack(false);
      this.setRearing(false);
      if (!this.o.D) {
         var1.mountEntity(this);
      }
   }

   public void makeHorseRearWithSound() {
      this.makeHorseRear();
      String var1 = this.getAngrySoundName();
      if (var1 != null) {
         this.playSound(var1, this.getSoundVolume(), this.bC());
      }
   }

   public void setHorseTamed(boolean var1) {
      this.setHorseWatchableBoolean(2, var1);
   }

   public void setChested(boolean var1) {
      this.setHorseWatchableBoolean(8, var1);
   }

   @Override
   public boolean canMateWith(EntityAnimal var1) {
      if (var1 == this) {
         return false;
      } else if (var1.getClass() != this.getClass()) {
         return false;
      } else {
         EntityHorse var2 = (EntityHorse)var1;
         if (this.canMate() && var2.canMate()) {
            int var3 = this.getHorseType();
            int var4 = var2.getHorseType();
            return var3 == var4 || var3 == 0 && var4 == 1 || var3 == 1 && var4 == 0;
         } else {
            return false;
         }
      }
   }

   @Override
   public boolean allowLeashing() {
      return !this.isUndead() && super.allowLeashing();
   }

   public boolean func_175507_cI() {
      return this.field_175508_bO;
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (var2 != null && var2.getItem() == Items.spawn_egg) {
         return super.interact(var1);
      } else if (!this.isTame() && this.isUndead()) {
         return false;
      } else if (this.isTame() && this.isAdultHorse() && var1.isSneaking()) {
         this.openGUI(var1);
         return true;
      } else if (this.func_110253_bW() && this.l != null) {
         return super.interact(var1);
      } else {
         if (var2 != null) {
            boolean var3 = false;
            if (this.canWearArmor()) {
               int var4 = -1;
               if (var2.getItem() == Items.iron_horse_armor) {
                  var4 = 1;
               } else if (var2.getItem() == Items.golden_horse_armor) {
                  var4 = 2;
               } else if (var2.getItem() == Items.diamond_horse_armor) {
                  var4 = 3;
               }

               if (var4 >= 0) {
                  if (!this.isTame()) {
                     this.makeHorseRearWithSound();
                     return true;
                  }

                  this.openGUI(var1);
                  return true;
               }
            }

            if (!var3 && !this.isUndead()) {
               float var7 = 0.0F;
               short var5 = 0;
               byte var6 = 0;
               if (var2.getItem() == Items.wheat) {
                  var7 = 2.0F;
                  var5 = 20;
                  var6 = 3;
               } else if (var2.getItem() == Items.sugar) {
                  var7 = 1.0F;
                  var5 = 30;
                  var6 = 3;
               } else if (Block.getBlockFromItem(var2.getItem()) == Blocks.hay_block) {
                  var7 = 20.0F;
                  var5 = 180;
               } else if (var2.getItem() == Items.apple) {
                  var7 = 3.0F;
                  var5 = 60;
                  var6 = 3;
               } else if (var2.getItem() == Items.golden_carrot) {
                  var7 = 4.0F;
                  var5 = 60;
                  var6 = 5;
                  if (this.isTame() && this.l() == 0) {
                     var3 = true;
                     this.setInLove(var1);
                  }
               } else if (var2.getItem() == Items.golden_apple) {
                  var7 = 10.0F;
                  var5 = 240;
                  var6 = 10;
                  if (this.isTame() && this.l() == 0) {
                     var3 = true;
                     this.setInLove(var1);
                  }
               }

               if (this.getHealth() < this.getMaxHealth() && var7 > 0.0F) {
                  this.heal(var7);
                  var3 = true;
               }

               if (!this.isAdultHorse() && var5 > 0) {
                  this.addGrowth(var5);
                  var3 = true;
               }

               if (var6 > 0 && (var3 || !this.isTame()) && var6 < this.getMaxTemper()) {
                  var3 = true;
                  this.increaseTemper(var6);
               }

               if (var3) {
                  this.func_110266_cB();
               }
            }

            if (!this.isTame() && !var3) {
               if (var2 != null && var2.interactWithEntity(var1, this)) {
                  return true;
               }

               this.makeHorseRearWithSound();
               return true;
            }

            if (!var3 && this.canCarryChest() && !this.isChested() && var2.getItem() == Item.getItemFromBlock(Blocks.chest)) {
               this.setChested(true);
               this.playSound("mob.chickenplop", 1.0F, (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
               var3 = true;
               this.initHorseChest();
            }

            if (!var3 && this.func_110253_bW() && !this.isHorseSaddled() && var2.getItem() == Items.saddle) {
               this.openGUI(var1);
               return true;
            }

            if (var3) {
               if (!var1.bA.isCreativeMode && --var2.stackSize == 0) {
                  var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
               }

               return true;
            }
         }

         if (!this.func_110253_bW() || this.l != null) {
            return super.interact(var1);
         } else if (var2 != null && var2.interactWithEntity(var1, this)) {
            return true;
         } else {
            this.mountTo(var1);
            return true;
         }
      }
   }

   @Override
   public boolean m_() {
      return this.l == null;
   }

   public void setHorseVariant(int var1) {
      this.ac.updateObject(20, var1);
      this.resetTexturePrefix();
   }

   public void setHorseType(int var1) {
      this.ac.updateObject(19, (byte)var1);
      this.resetTexturePrefix();
   }

   public void setTemper(int var1) {
      this.temper = var1;
   }

   public boolean canMate() {
      return this.l == null
         && this.m == null
         && this.isTame()
         && this.isAdultHorse()
         && !this.isSterile()
         && this.getHealth() >= this.getMaxHealth()
         && this.isInLove();
   }

   public boolean isTame() {
      return this.getHorseWatchableBoolean(2);
   }

   public boolean prepareChunkForSpawn() {
      int var1 = MathHelper.floor_double(this.s);
      int var2 = MathHelper.floor_double(this.u);
      this.o.getBiomeGenForCoords(new BlockPos(var1, 0, var2));
      return true;
   }

   public boolean isAdultHorse() {
      return !this.o_();
   }

   @Override
   public void onInventoryChanged(InventoryBasic var1) {
      int var2 = this.getHorseArmorIndexSynced();
      boolean var3 = this.isHorseSaddled();
      this.updateHorseSlots();
      if (this.W > 20) {
         if (var2 == 0 && var2 != this.getHorseArmorIndexSynced()) {
            this.playSound("mob.horse.armor", 0.5F, 1.0F);
         } else if (var2 != this.getHorseArmorIndexSynced()) {
            this.playSound("mob.horse.armor", 0.5F, 1.0F);
         }

         if (!var3 && this.isHorseSaddled()) {
            this.playSound("mob.horse.leather", 0.5F, 1.0F);
         }
      }
   }

   public void func_110266_cB() {
      this.openHorseMouth();
      if (!this.R()) {
         this.o.a(this, "eating", 1.0F, 1.0F + (this.V.nextFloat() - this.V.nextFloat()) * 0.2F);
      }
   }

   public boolean isEatingHaystack() {
      return this.getHorseWatchableBoolean(32);
   }

   public int getHorseType() {
      return this.ac.getWatchableObjectByte(19);
   }

   @Override
   public void setEating(boolean var1) {
      this.setHorseWatchableBoolean(32, var1);
   }

   public boolean isUndead() {
      int var1 = this.getHorseType();
      return var1 == 3 || var1 == 4;
   }

   @Override
   public void func_142017_o(float var1) {
      if (var1 > 6.0F && this.isEatingHaystack()) {
         this.setEatingHaystack(false);
      }
   }

   public boolean isBreeding() {
      return this.getHorseWatchableBoolean(16);
   }

   public double getModifiedMovementSpeed() {
      return (0.45F + this.V.nextDouble() * 0.3 + this.V.nextDouble() * 0.3 + this.V.nextDouble() * 0.3) * 0.25;
   }

   @Override
   public String getHurtSound() {
      this.openHorseMouth();
      if (this.V.nextInt(3) == 0) {
         this.makeHorseRear();
      }

      int var1 = this.getHorseType();
      return var1 == 3 ? "mob.horse.zombie.hit" : (var1 == 4 ? "mob.horse.skeleton.hit" : (var1 != 1 && var1 != 2 ? "mob.horse.hit" : "mob.horse.donkey.hit"));
   }

   public void setHorseArmorStack(ItemStack var1) {
      this.ac.updateObject(22, this.getHorseArmorIndex(var1));
      this.resetTexturePrefix();
   }

   public float getGrassEatingAmount(float var1) {
      return this.prevHeadLean + (this.headLean - this.prevHeadLean) * var1;
   }

   public boolean func_110253_bW() {
      return this.isAdultHorse();
   }

   public void setHorseSaddled(boolean var1) {
      this.setHorseWatchableBoolean(4, var1);
   }

   @Override
   public boolean isBreedingItem(ItemStack var1) {
      return false;
   }

   public String getHorseTexture() {
      if (this.texturePrefix == null) {
         this.setHorseTexturePaths();
      }

      return this.texturePrefix;
   }

   @Override
   public boolean n_() {
      return false;
   }

   @Override
   public Item getDropItem() {
      boolean var1 = this.V.nextInt(4) == 0;
      int var2 = this.getHorseType();
      return var2 == 4 ? Items.bone : (var2 == 3 ? (var1 ? null : Items.rotten_flesh) : Items.leather);
   }

   public boolean method_23886() {
      return this.recoveredField3637;
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, 0);
      this.ac.addObject(19, (byte)0);
      this.ac.addObject(20, 0);
      this.ac.addObject(21, String.valueOf(""));
      this.ac.addObject(22, 0);
   }

   public int getTemper() {
      return this.temper;
   }

   public void setEatingHaystack(boolean var1) {
      this.setEating(var1);
   }

   public int getHorseArmorIndexSynced() {
      return this.ac.getWatchableObjectInt(22);
   }

   public boolean func_110239_cn() {
      return this.getHorseType() == 0 || this.getHorseArmorIndexSynced() > 0;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setEatingHaystack(var1.getBoolean("EatingHaystack"));
      this.setBreeding(var1.getBoolean("Bred"));
      this.setChested(var1.getBoolean("ChestedHorse"));
      this.setHasReproduced(var1.getBoolean("HasReproduced"));
      this.setHorseType(var1.getInteger("Type"));
      this.setHorseVariant(var1.getInteger("Variant"));
      this.setTemper(var1.getInteger("Temper"));
      this.setHorseTamed(var1.getBoolean("Tame"));
      String var2 = "";
      if (var1.hasKey("OwnerUUID", 8)) {
         var2 = var1.getString("OwnerUUID");
      } else {
         String var3 = var1.getString("Owner");
         var2 = PreYggdrasilConverter.getStringUUIDFromName(var3);
      }

      if (var2.length() > 0) {
         this.setOwnerId(var2);
      }

      IAttributeInstance var9 = this.getAttributeMap().getAttributeInstanceByName("Speed");
      if (var9 != null) {
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(var9.getBaseValue() * 0.25);
      }

      if (this.isChested()) {
         NBTTagList var4 = var1.getTagList("Items", 10);
         this.initHorseChest();

         for (int var5 = 0; var5 < var4.tagCount(); var5++) {
            NBTTagCompound var6 = var4.getCompoundTagAt(var5);
            int var7 = var6.getByte("Slot") & 255;
            if (var7 >= 2 && var7 < this.horseChest.getSizeInventory()) {
               this.horseChest.setInventorySlotContents(var7, ItemStack.loadItemStackFromNBT(var6));
            }
         }
      }

      if (var1.hasKey("ArmorItem", 10)) {
         ItemStack var10 = ItemStack.loadItemStackFromNBT(var1.getCompoundTag("ArmorItem"));
         if (var10 != null && isArmorItem(var10.getItem())) {
            this.horseChest.setInventorySlotContents(1, var10);
         }
      }

      if (var1.hasKey("SaddleItem", 10)) {
         ItemStack var11 = ItemStack.loadItemStackFromNBT(var1.getCompoundTag("SaddleItem"));
         if (var11 != null && var11.getItem() == Items.saddle) {
            this.horseChest.setInventorySlotContents(0, var11);
         }
      } else if (var1.getBoolean("Saddle")) {
         this.horseChest.setInventorySlotContents(0, new ItemStack(Items.saddle));
      }

      this.updateHorseSlots();
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      Block.SoundType var3 = var2.stepSound;
      if (this.o.getBlockState(var1.up()).getBlock() == Blocks.snow_layer) {
         var3 = Blocks.snow_layer.stepSound;
      }

      if (!var2.getMaterial().isLiquid()) {
         int var4 = this.getHorseType();
         if (this.l != null && var4 != 1 && var4 != 2) {
            this.gallopTime++;
            if (this.gallopTime > 5 && this.gallopTime % 3 == 0) {
               this.playSound("mob.horse.gallop", var3.getVolume() * 0.15F, var3.getFrequency());
               if (var4 == 0 && this.V.nextInt(10) == 0) {
                  this.playSound("mob.horse.breathe", var3.getVolume() * 0.6F, var3.getFrequency());
               }
            } else if (this.gallopTime <= 5) {
               this.playSound("mob.horse.wood", var3.getVolume() * 0.15F, var3.getFrequency());
            }
         } else if (var3 == Block.f) {
            this.playSound("mob.horse.wood", var3.getVolume() * 0.15F, var3.getFrequency());
         } else {
            this.playSound("mob.horse.soft", var3.getVolume() * 0.15F, var3.getFrequency());
         }
      }
   }

   public boolean getHorseWatchableBoolean(int var1) {
      return (this.ac.getWatchableObjectInt(16) & var1) != 0;
   }

   public boolean isSterile() {
      return this.isUndead() || this.getHorseType() == 2;
   }

   public void setJumpPower(int var1) {
      if (this.isHorseSaddled()) {
         if (var1 < 0) {
            var1 = 0;
         } else {
            this.field_110294_bI = true;
            this.makeHorseRear();
         }

         if (var1 >= 90) {
            this.jumpPower = 1.0F;
         } else {
            this.jumpPower = 0.4F + 0.4F * var1 / 90.0F;
         }
      }
   }

   public void func_110210_cH() {
      this.field_110278_bp = 1;
   }

   @Override
   public int getMaxSpawnedInChunk() {
      return 6;
   }

   public int getChestSize() {
      int var1 = this.getHorseType();
      return this.isChested() && (var1 == 1 || var1 == 2) ? 17 : 2;
   }

   public static boolean isArmorItem(Item var0) {
      return var0 == Items.iron_horse_armor || var0 == Items.golden_horse_armor || var0 == Items.diamond_horse_armor;
   }

   @Override
   public String getLivingSound() {
      this.openHorseMouth();
      if (this.V.nextInt(10) == 0 && !this.isMovementBlocked()) {
         this.makeHorseRear();
      }

      int var1 = this.getHorseType();
      return var1 == 3
         ? "mob.horse.zombie.idle"
         : (var1 == 4 ? "mob.horse.skeleton.idle" : (var1 != 1 && var1 != 2 ? "mob.horse.idle" : "mob.horse.donkey.idle"));
   }

   public String getAngrySoundName() {
      this.openHorseMouth();
      this.makeHorseRear();
      int var1 = this.getHorseType();
      return var1 != 3 && var1 != 4 ? (var1 != 1 && var1 != 2 ? "mob.horse.angry" : "mob.horse.donkey.angry") : null;
   }

   public float getMouthOpennessAngle(float var1) {
      return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * var1;
   }

   @Override
   public void setScaleForAge(boolean var1) {
      if (var1) {
         this.setScale(this.getHorseSize());
      } else {
         this.setScale(1.0F);
      }
   }

   @Override
   public int getTotalArmorValue() {
      return armorValues[this.getHorseArmorIndexSynced()];
   }

   public double getModifiedJumpStrength() {
      return 0.4F + this.V.nextDouble() * 0.2 + this.V.nextDouble() * 0.2 + this.V.nextDouble() * 0.2;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("EatingHaystack", this.isEatingHaystack());
      var1.setBoolean("ChestedHorse", this.isChested());
      var1.setBoolean("HasReproduced", this.getHasReproduced());
      var1.setBoolean("Bred", this.isBreeding());
      var1.setInteger("Type", this.getHorseType());
      var1.setInteger("Variant", this.getHorseVariant());
      var1.setInteger("Temper", this.getTemper());
      var1.setBoolean("Tame", this.isTame());
      var1.setString("OwnerUUID", this.getOwnerId());
      if (this.isChested()) {
         NBTTagList var2 = new NBTTagList();

         for (int var3 = 2; var3 < this.horseChest.getSizeInventory(); var3++) {
            ItemStack var4 = this.horseChest.getStackInSlot(var3);
            if (var4 != null) {
               NBTTagCompound var5 = new NBTTagCompound();
               var5.setByte("Slot", (byte)var3);
               var4.writeToNBT(var5);
               var2.appendTag(var5);
            }
         }

         var1.setTag("Items", var2);
      }

      if (this.horseChest.getStackInSlot(1) != null) {
         var1.setTag("ArmorItem", this.horseChest.getStackInSlot(1).writeToNBT(new NBTTagCompound()));
      }

      if (this.horseChest.getStackInSlot(0) != null) {
         var1.setTag("SaddleItem", this.horseChest.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
      }
   }

   public String getOwnerId() {
      return this.ac.getWatchableObjectString(21);
   }

   public float getModifiedMaxHealth() {
      return 15.0F + this.V.nextInt(8) + this.V.nextInt(9);
   }

   public void dropItemsInChest(Entity var1, AnimalChest var2) {
      if (var2 != null && !this.o.D) {
         for (int var3 = 0; var3 < var2.getSizeInventory(); var3++) {
            ItemStack var4 = var2.getStackInSlot(var3);
            if (var4 != null) {
               this.a(var4, 0.0F);
            }
         }
      }
   }

   @Override
   public boolean isMovementBlocked() {
      return this.l != null && this.isHorseSaddled() ? true : this.isEatingHaystack() || this.isRearing();
   }

   public boolean canCarryChest() {
      int var1 = this.getHorseType();
      return var1 == 2 || var1 == 1;
   }

   public void dropChestItems() {
      this.dropItemsInChest(this, this.horseChest);
      this.dropChests();
   }

   public void setRearing(boolean var1) {
      if (var1) {
         this.setEatingHaystack(false);
      }

      this.setHorseWatchableBoolean(64, var1);
   }

   @Override
   public String z_() {
      if (this.u_()) {
         return this.aM();
      } else {
         int var1 = this.getHorseType();
         switch (var1) {
            case 0:
            default:
               return StatCollector.translateToLocal("entity.horse.name");
            case 1:
               return StatCollector.translateToLocal("entity.donkey.name");
            case 2:
               return StatCollector.translateToLocal("entity.mule.name");
            case 3:
               return StatCollector.translateToLocal("entity.zombiehorse.name");
            case 4:
               return StatCollector.translateToLocal("entity.skeletonhorse.name");
         }
      }
   }

   public void openGUI(EntityPlayer var1) {
      if (!this.o.D && (this.l == null || this.l == var1) && this.isTame()) {
         this.horseChest.setCustomName(this.z_());
         var1.displayGUIHorse(this, this.horseChest);
      }
   }

   public int getHorseArmorIndex(ItemStack var1) {
      if (var1 == null) {
         return 0;
      } else {
         Item var2 = var1.getItem();
         return var2 == Items.iron_horse_armor ? 1 : (var2 == Items.golden_horse_armor ? 2 : (var2 == Items.diamond_horse_armor ? 3 : 0));
      }
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.o.D && this.ac.hasObjectChanged()) {
         this.ac.func_111144_e();
         this.resetTexturePrefix();
      }

      if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
         this.openMouthCounter = 0;
         this.setHorseWatchableBoolean(128, false);
      }

      if (!this.o.D && this.jumpRearingCounter > 0 && ++this.jumpRearingCounter > 20) {
         this.jumpRearingCounter = 0;
         this.setRearing(false);
      }

      if (this.field_110278_bp > 0 && ++this.field_110278_bp > 8) {
         this.field_110278_bp = 0;
      }

      if (this.field_110279_bq > 0) {
         this.field_110279_bq++;
         if (this.field_110279_bq > 300) {
            this.field_110279_bq = 0;
         }
      }

      this.prevHeadLean = this.headLean;
      if (this.isEatingHaystack()) {
         this.headLean = this.headLean + ((1.0F - this.headLean) * 0.4F + 0.05F);
         if (this.headLean > 1.0F) {
            this.headLean = 1.0F;
         }
      } else {
         this.headLean = this.headLean + ((0.0F - this.headLean) * 0.4F - 0.05F);
         if (this.headLean < 0.0F) {
            this.headLean = 0.0F;
         }
      }

      this.prevRearingAmount = this.rearingAmount;
      if (this.isRearing()) {
         this.prevHeadLean = this.headLean = 0.0F;
         this.rearingAmount = this.rearingAmount + ((1.0F - this.rearingAmount) * 0.4F + 0.05F);
         if (this.rearingAmount > 1.0F) {
            this.rearingAmount = 1.0F;
         }
      } else {
         this.field_110294_bI = false;
         this.rearingAmount = this.rearingAmount + ((0.8F * this.rearingAmount * this.rearingAmount * this.rearingAmount - this.rearingAmount) * 0.6F - 0.05F);
         if (this.rearingAmount < 0.0F) {
            this.rearingAmount = 0.0F;
         }
      }

      this.prevMouthOpenness = this.mouthOpenness;
      if (this.getHorseWatchableBoolean(128)) {
         this.mouthOpenness = this.mouthOpenness + ((1.0F - this.mouthOpenness) * 0.7F + 0.05F);
         if (this.mouthOpenness > 1.0F) {
            this.mouthOpenness = 1.0F;
         }
      } else {
         this.mouthOpenness = this.mouthOpenness + ((0.0F - this.mouthOpenness) * 0.7F - 0.05F);
         if (this.mouthOpenness < 0.0F) {
            this.mouthOpenness = 0.0F;
         }
      }
   }

   public EntityHorse(World var1) {
      super(var1);
      this.field_175508_bO = false;
      this.setSize(1.4F, 1.6F);
      this.ab = false;
      this.setChested(false);
      ((PathNavigateGround)this.s()).setAvoidsWater(true);
      this.i.addTask(0, new EntityAISwimming(this));
      this.i.addTask(1, new EntityAIPanic(this, 1.2));
      this.i.addTask(1, new EntityAIRunAroundLikeCrazy(this, 1.2));
      this.i.addTask(2, new EntityAIMate(this, 1.0));
      this.i.addTask(4, new EntityAIFollowParent(this, 1.0));
      this.i.addTask(6, new EntityAIWander(this, 0.7));
      this.i.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.i.addTask(8, new EntityAILookIdle(this));
      this.initHorseChest();
   }

   public boolean setTamedBy(EntityPlayer var1) {
      this.setOwnerId(var1.aK().toString());
      this.setHorseTamed(true);
      return true;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(horseJumpStrength);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(53.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.225F);
   }

   public double getHorseJumpStrength() {
      return this.getEntityAttribute(horseJumpStrength).getAttributeValue();
   }

   @Override
   public float getSoundVolume() {
      return 0.8F;
   }

   public boolean isChested() {
      return this.getHorseWatchableBoolean(8);
   }

   public void spawnHorseParticles(boolean var1) {
      EnumParticleTypes var2 = var1 ? EnumParticleTypes.HEART : EnumParticleTypes.SMOKE_NORMAL;

      for (int var3 = 0; var3 < 7; var3++) {
         double var4 = this.V.nextGaussian() * 0.02;
         double var6 = this.V.nextGaussian() * 0.02;
         double var8 = this.V.nextGaussian() * 0.02;
         this.o
            .spawnParticle(
               var2,
               this.s + this.V.nextFloat() * this.J * 2.0F - this.J,
               this.t + 0.5 + this.V.nextFloat() * this.K,
               this.u + this.V.nextFloat() * this.J * 2.0F - this.J,
               var4,
               var6,
               var8
            );
      }
   }

   public void dropChests() {
      if (!this.o.D && this.isChested()) {
         this.dropItem(Item.getItemFromBlock(Blocks.chest), 1);
         this.setChested(false);
      }
   }

   public void setBreeding(boolean var1) {
      this.setHorseWatchableBoolean(16, var1);
   }

   public boolean isRearing() {
      return this.getHorseWatchableBoolean(64);
   }

   @Override
   public boolean getCanSpawnHere() {
      this.prepareChunkForSpawn();
      return super.getCanSpawnHere();
   }

   public void openHorseMouth() {
      if (!this.o.D) {
         this.openMouthCounter = 1;
         this.setHorseWatchableBoolean(128, true);
      }
   }

   @Override
   public void updateRiderPosition() {
      super.updateRiderPosition();
      if (this.prevRearingAmount > 0.0F) {
         float var1 = MathHelper.sin(this.aI * (float) Math.PI / 180.0F);
         float var2 = MathHelper.cos(this.aI * (float) Math.PI / 180.0F);
         float var3 = 0.7F * this.prevRearingAmount;
         float var4 = 0.15F * this.prevRearingAmount;
         this.l.b(this.s + var3 * var1, this.t + this.getMountedYOffset() + this.l.getYOffset() + var4, this.u - var3 * var2);
         if (this.l instanceof EntityLivingBase) {
            ((EntityLivingBase)this.l).aI = this.aI;
         }
      }
   }

   public float getRearingAmount(float var1) {
      return this.prevRearingAmount + (this.rearingAmount - this.prevRearingAmount) * var1;
   }

   @Override
   public int getTalkInterval() {
      return 400;
   }

   public void setOwnerId(String var1) {
      this.ac.updateObject(21, var1);
   }

   public boolean isHorseSaddled() {
      return this.getHorseWatchableBoolean(4);
   }

   @Override
   public EntityAgeable createChild(EntityAgeable var1) {
      EntityHorse var2 = (EntityHorse)var1;
      EntityHorse var3 = new EntityHorse(this.o);
      int var4 = this.getHorseType();
      int var5 = var2.getHorseType();
      int var6 = 0;
      if (var4 == var5) {
         var6 = var4;
      } else if (var4 == 0 && var5 == 1 || var4 == 1 && var5 == 0) {
         var6 = 2;
      }

      if (var6 == 0) {
         int var7 = this.V.nextInt(9);
         int var8;
         if (var7 < 4) {
            var8 = this.getHorseVariant() & 0xFF;
         } else if (var7 < 8) {
            var8 = var2.getHorseVariant() & 0xFF;
         } else {
            var8 = this.V.nextInt(7);
         }

         int var9 = this.V.nextInt(5);
         if (var9 < 2) {
            var8 |= this.getHorseVariant() & 0xFF00;
         } else if (var9 < 4) {
            var8 |= var2.getHorseVariant() & 0xFF00;
         } else {
            var8 |= this.V.nextInt(5) << 8 & 0xFF00;
         }

         var3.setHorseVariant(var8);
      }

      var3.setHorseType(var6);
      double var13 = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue()
         + var1.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue()
         + this.getModifiedMaxHealth();
      var3.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(var13 / 3.0);
      double var15 = this.getEntityAttribute(horseJumpStrength).getBaseValue()
         + var1.getEntityAttribute(horseJumpStrength).getBaseValue()
         + this.getModifiedJumpStrength();
      var3.getEntityAttribute(horseJumpStrength).setBaseValue(var15 / 3.0);
      double var11 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()
         + var1.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()
         + this.getModifiedMovementSpeed();
      var3.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(var11 / 3.0);
      return var3;
   }

   @Override
   public void onLivingUpdate() {
      if (this.V.nextInt(200) == 0) {
         this.func_110210_cH();
      }

      super.onLivingUpdate();
      if (!this.o.D) {
         if (this.V.nextInt(900) == 0 && this.ax == 0) {
            this.heal(1.0F);
         }

         if (!this.isEatingHaystack()
            && this.l == null
            && this.V.nextInt(300) == 0
            && this.o
                  .getBlockState(new BlockPos(MathHelper.floor_double(this.s), MathHelper.floor_double(this.t) - 1, MathHelper.floor_double(this.u)))
                  .getBlock()
               == Blocks.grass) {
            this.setEatingHaystack(true);
         }

         if (this.isEatingHaystack() && ++this.eatingHaystackCounter > 50) {
            this.eatingHaystackCounter = 0;
            this.setEatingHaystack(false);
         }

         if (this.isBreeding() && !this.isAdultHorse() && !this.isEatingHaystack()) {
            EntityHorse var1 = this.getClosestHorse(this, 16.0);
            if (var1 != null && this.h(var1) > 4.0) {
               this.h.getPathToEntityLiving(var1);
            }
         }
      }
   }

   @Override
   public void onDeath(DamageSource var1) {
      super.onDeath(var1);
      if (!this.o.D) {
         this.dropChestItems();
      }
   }

   @Override
   public void fall(float var1, float var2) {
      if (var1 > 1.0F) {
         this.playSound("mob.horse.land", 0.4F, 1.0F);
      }

      int var3 = MathHelper.ceiling_float_int((var1 * 0.5F - 3.0F) * var2);
      if (var3 > 0) {
         this.attackEntityFrom(DamageSource.fall, var3);
         if (this.l != null) {
            this.l.attackEntityFrom(DamageSource.fall, var3);
         }

         Block var4 = this.o.getBlockState(new BlockPos(this.s, this.t - 0.2 - this.A, this.u)).getBlock();
         if (var4.getMaterial() != Material.air && !this.R()) {
            Block.SoundType var5 = var4.stepSound;
            this.o.a(this, var5.getStepSound(), var5.getVolume() * 0.5F, var5.getFrequency() * 0.75F);
         }
      }
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 7) {
         this.spawnHorseParticles(true);
      } else if (var1 == 6) {
         this.spawnHorseParticles(false);
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public boolean replaceItemInInventory(int var1, ItemStack var2) {
      if (var1 == 499 && this.canCarryChest()) {
         if (var2 == null && this.isChested()) {
            this.setChested(false);
            this.initHorseChest();
            return true;
         }

         if (var2 != null && var2.getItem() == Item.getItemFromBlock(Blocks.chest) && !this.isChested()) {
            this.setChested(true);
            this.initHorseChest();
            return true;
         }
      }

      int var3 = var1 - 400;
      if (var3 >= 0 && var3 < 2 && var3 < this.horseChest.getSizeInventory()) {
         if (var3 == 0 && var2 != null && var2.getItem() != Items.saddle) {
            return false;
         } else if (var3 == 1 && (var2 != null && !isArmorItem(var2.getItem()) || !this.canWearArmor())) {
            return false;
         } else {
            this.horseChest.setInventorySlotContents(var3, var2);
            this.updateHorseSlots();
            return true;
         }
      } else {
         int var4 = var1 - 500 + 2;
         if (var4 >= 2 && var4 < this.horseChest.getSizeInventory()) {
            this.horseChest.setInventorySlotContents(var4, var2);
            return true;
         } else {
            return false;
         }
      }
   }

   public int getHorseVariant() {
      return this.ac.getWatchableObjectInt(20);
   }

   public void initHorseChest() {
      AnimalChest var1 = this.horseChest;
      this.horseChest = new AnimalChest("HorseChest", this.getChestSize());
      this.horseChest.setCustomName(this.z_());
      if (var1 != null) {
         var1.removeInventoryChangeListener(this);
         int var2 = Math.min(var1.getSizeInventory(), this.horseChest.getSizeInventory());

         for (int var3 = 0; var3 < var2; var3++) {
            ItemStack var4 = var1.getStackInSlot(var3);
            if (var4 != null) {
               this.horseChest.setInventorySlotContents(var3, var4.copy());
            }
         }
      }

      this.horseChest.addInventoryChangeListener(this);
      this.updateHorseSlots();
   }

   @Override
   public void moveEntityWithHeading(float var1, float var2) {
      if (this.l != null && this.l instanceof EntityLivingBase && this.isHorseSaddled()) {
         this.A = this.y = this.l.y;
         this.z = this.l.z * 0.5F;
         this.setRotation(this.y, this.z);
         this.aK = this.aI = this.y;
         var1 = ((EntityLivingBase)this.l).aZ * 0.5F;
         var2 = ((EntityLivingBase)this.l).ba;
         if (var2 <= 0.0F) {
            var2 *= 0.25F;
            this.gallopTime = 0;
         }

         if (this.C && this.jumpPower == 0.0F && this.isRearing() && !this.field_110294_bI) {
            var1 = 0.0F;
            var2 = 0.0F;
         }

         if (this.jumpPower > 0.0F && !this.method_23886() && this.C) {
            this.w = this.getHorseJumpStrength() * this.jumpPower;
            if (this.isPotionActive(Potion.jump)) {
               this.w = this.w + (this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F;
            }

            this.method_23885(true);
            this.ai = true;
            if (var2 > 0.0F) {
               float var3 = MathHelper.sin(this.y * (float) Math.PI / 180.0F);
               float var4 = MathHelper.cos(this.y * (float) Math.PI / 180.0F);
               this.v = this.v + -0.4F * var3 * this.jumpPower;
               this.x = this.x + 0.4F * var4 * this.jumpPower;
               this.playSound("mob.horse.jump", 0.4F, 1.0F);
            }

            this.jumpPower = 0.0F;
         }

         this.S = 1.0F;
         this.aM = this.bI() * 0.1F;
         if (!this.o.D) {
            this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
            super.moveEntityWithHeading(var1, var2);
         }

         if (this.C) {
            this.jumpPower = 0.0F;
            this.method_23885(false);
         }

         this.aA = this.aB;
         double var10 = this.s - this.p;
         double var5 = this.u - this.r;
         float var7 = MathHelper.sqrt_double(var10 * var10 + var5 * var5) * 4.0F;
         if (var7 > 1.0F) {
            var7 = 1.0F;
         }

         this.aB = this.aB + (var7 - this.aB) * 0.4F;
         this.aC = this.aC + this.aB;
      } else {
         this.S = 0.5F;
         this.aM = 0.02F;
         super.moveEntityWithHeading(var1, var2);
      }
   }

   @Override
   public String getDeathSound() {
      this.openHorseMouth();
      int var1 = this.getHorseType();
      return var1 == 3
         ? "mob.horse.zombie.death"
         : (var1 == 4 ? "mob.horse.skeleton.death" : (var1 != 1 && var1 != 2 ? "mob.horse.death" : "mob.horse.donkey.death"));
   }

   public int getMaxTemper() {
      return 100;
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      Entity var3 = var1.getEntity();
      return this.l != null && this.l.equals(var3) ? false : super.attackEntityFrom(var1, var2);
   }

   public void makeHorseRear() {
      if (!this.o.D) {
         this.jumpRearingCounter = 1;
         this.setRearing(true);
      }
   }

   public void setHorseTexturePaths() {
      this.texturePrefix = "horse/";
      this.horseTexturesArray[0] = null;
      this.horseTexturesArray[1] = null;
      this.horseTexturesArray[2] = null;
      int var1 = this.getHorseType();
      int var2 = this.getHorseVariant();
      if (var1 == 0) {
         int var3 = var2 & 0xFF;
         int var4 = (var2 & 0xFF00) >> 8;
         if (var3 >= recoveredField3638.length) {
            this.field_175508_bO = false;
            return;
         }

         this.horseTexturesArray[0] = recoveredField3638[var3];
         this.texturePrefix = this.texturePrefix + recoveredField3636[var3];
         if (var4 >= recoveredField3635.length) {
            this.field_175508_bO = false;
            return;
         }

         this.horseTexturesArray[1] = recoveredField3635[var4];
         this.texturePrefix = this.texturePrefix + recoveredField3643[var4];
      } else {
         this.horseTexturesArray[0] = "";
         this.texturePrefix = this.texturePrefix + "_" + var1 + "_";
      }

      int var5 = this.getHorseArmorIndexSynced();
      if (var5 >= recoveredField3641.length) {
         this.field_175508_bO = false;
      } else {
         this.horseTexturesArray[2] = recoveredField3641[var5];
         this.texturePrefix = this.texturePrefix + recoveredField3640[var5];
         this.field_175508_bO = true;
      }
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance var1, IEntityLivingData var2) {
      var2 = super.onInitialSpawn(var1, var2);
      int var3 = 0;
      int var4 = 0;
      if (var2 instanceof EntityHorse.GroupData) {
         var3 = ((EntityHorse.GroupData)var2).recoveredField985;
         var4 = ((EntityHorse.GroupData)var2).recoveredField986 & 0xFF | this.V.nextInt(5) << 8;
      } else {
         if (this.V.nextInt(10) == 0) {
            var3 = 1;
         } else {
            int var5 = this.V.nextInt(7);
            int var6 = this.V.nextInt(5);
            var3 = 0;
            var4 = var5 | var6 << 8;
         }

         var2 = new EntityHorse.GroupData(var3, var4);
      }

      this.setHorseType(var3);
      this.setHorseVariant(var4);
      if (this.V.nextInt(5) == 0) {
         this.setGrowingAge(-24000);
      }

      if (var3 != 4 && var3 != 3) {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.getModifiedMaxHealth());
         if (var3 == 0) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.getModifiedMovementSpeed());
         } else {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.175F);
         }
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2F);
      }

      if (var3 != 2 && var3 != 1) {
         this.getEntityAttribute(horseJumpStrength).setBaseValue(this.getModifiedJumpStrength());
      } else {
         this.getEntityAttribute(horseJumpStrength).setBaseValue(0.5);
      }

      this.setHealth(this.getMaxHealth());
      return var2;
   }

   public static class GroupData implements IEntityLivingData {
      public int recoveredField985;
      public int recoveredField986;

      public GroupData(int var1, int var2) {
         this.recoveredField985 = var1;
         this.recoveredField986 = var2;
      }
   }
}
