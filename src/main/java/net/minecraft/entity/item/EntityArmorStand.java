package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotations;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityArmorStand extends EntityLivingBase {
   public long punchCooldown;
   public ItemStack[] contents = new ItemStack[5];
   public Rotations bodyRotation;
   public Rotations rightLegRotation;
   public static Rotations DEFAULT_HEAD_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
   public static Rotations DEFAULT_BODY_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
   public Rotations leftLegRotation;
   public boolean canInteract;
   public static Rotations DEFAULT_LEFTARM_ROTATION = new Rotations(-10.0F, 0.0F, -10.0F);
   public static Rotations DEFAULT_RIGHTARM_ROTATION = new Rotations(-15.0F, 0.0F, 10.0F);
   public static Rotations DEFAULT_LEFTLEG_ROTATION = new Rotations(-1.0F, 0.0F, -1.0F);
   public int disabledSlots;
   public boolean field_181028_bj;
   public Rotations leftArmRotation;
   public static Rotations DEFAULT_RIGHTLEG_ROTATION = new Rotations(1.0F, 0.0F, 1.0F);
   public Rotations rightArmRotation;
   public Rotations headRotation = DEFAULT_HEAD_ROTATION;

   @Override
   public boolean canBeCollidedWith() {
      return super.canBeCollidedWith() && !this.hasMarker();
   }

   public void setRightLegRotation(Rotations var1) {
      this.rightLegRotation = var1;
      this.ac.updateObject(16, var1);
   }

   @Override
   public void setInvisible(boolean var1) {
      this.canInteract = var1;
      super.setInvisible(var1);
   }

   @Override
   public boolean interactAt(EntityPlayer var1, Vec3 var2) {
      if (this.hasMarker()) {
         return false;
      } else if (!this.o.D && !var1.isSpectator()) {
         byte var3 = 0;
         ItemStack var4 = var1.getCurrentEquippedItem();
         boolean var5 = var4 != null;
         if (var5 && var4.getItem() instanceof ItemArmor) {
            ItemArmor var6 = (ItemArmor)var4.getItem();
            if (var6.armorType == 3) {
               var3 = 1;
            } else if (var6.armorType == 2) {
               var3 = 2;
            } else if (var6.armorType == 1) {
               var3 = 3;
            } else if (var6.armorType == 0) {
               var3 = 4;
            }
         }

         if (var5 && (var4.getItem() == Items.skull || var4.getItem() == Item.getItemFromBlock(Blocks.pumpkin))) {
            var3 = 4;
         }

         double var19 = 0.1;
         double var8 = 0.9;
         double var10 = 0.4;
         double var12 = 1.6;
         byte var14 = 0;
         boolean var15 = this.isSmall();
         double var16 = var15 ? var2.yCoord * 2.0 : var2.yCoord;
         if (var16 >= 0.1 && var16 < 0.1 + (var15 ? 0.8 : 0.45) && this.contents[1] != null) {
            var14 = 1;
         } else if (var16 >= 0.9 + (var15 ? 0.3 : 0.0) && var16 < 0.9 + (var15 ? 1.0 : 0.7) && this.contents[3] != null) {
            var14 = 3;
         } else if (var16 >= 0.4 && var16 < 0.4 + (var15 ? 1.0 : 0.8) && this.contents[2] != null) {
            var14 = 2;
         } else if (var16 >= 1.6 && this.contents[4] != null) {
            var14 = 4;
         }

         boolean var18 = this.contents[var14] != null;
         if ((this.disabledSlots & 1 << var14) != 0 || (this.disabledSlots & 1 << var3) != 0) {
            var14 = var3;
            if ((this.disabledSlots & 1 << var3) != 0) {
               if ((this.disabledSlots & 1) != 0) {
                  return true;
               }

               var14 = 0;
            }
         }

         if (var5 && var3 == 0 && !this.getShowArms()) {
            return true;
         } else {
            if (var5) {
               this.func_175422_a(var1, var3);
            } else if (var18) {
               this.func_175422_a(var1, var14);
            }

            return true;
         }
      } else {
         return true;
      }
   }

   @Override
   public void collideWithNearbyEntities() {
      List var1 = this.o.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
      if (var1 != null && !var1.isEmpty()) {
         for (int var2 = 0; var2 < var1.size(); var2++) {
            Entity var3 = (Entity)var1.get(var2);
            if (var3 instanceof EntityMinecart && ((EntityMinecart)var3).getMinecartType() == EntityMinecart.EnumMinecartType.RIDEABLE && this.h(var3) <= 0.2) {
               var3.applyEntityCollision(this);
            }
         }
      }
   }

   public EntityArmorStand(World var1) {
      super(var1);
      this.bodyRotation = DEFAULT_BODY_ROTATION;
      this.leftArmRotation = DEFAULT_LEFTARM_ROTATION;
      this.rightArmRotation = DEFAULT_RIGHTARM_ROTATION;
      this.leftLegRotation = DEFAULT_LEFTLEG_ROTATION;
      this.rightLegRotation = DEFAULT_RIGHTLEG_ROTATION;
      this.setSilent(true);
      this.T = this.hasNoGravity();
      this.setSize(0.5F, 1.975F);
   }

   @Override
   public void collideWithEntity(Entity var1) {
   }

   public void writePoseToNBT(NBTTagCompound var1) {
      NBTTagList var2 = var1.getTagList("Head", 5);
      if (var2.tagCount() > 0) {
         this.setHeadRotation(new Rotations(var2));
      } else {
         this.setHeadRotation(DEFAULT_HEAD_ROTATION);
      }

      NBTTagList var3 = var1.getTagList("Body", 5);
      if (var3.tagCount() > 0) {
         this.setBodyRotation(new Rotations(var3));
      } else {
         this.setBodyRotation(DEFAULT_BODY_ROTATION);
      }

      NBTTagList var4 = var1.getTagList("LeftArm", 5);
      if (var4.tagCount() > 0) {
         this.setLeftArmRotation(new Rotations(var4));
      } else {
         this.setLeftArmRotation(DEFAULT_LEFTARM_ROTATION);
      }

      NBTTagList var5 = var1.getTagList("RightArm", 5);
      if (var5.tagCount() > 0) {
         this.setRightArmRotation(new Rotations(var5));
      } else {
         this.setRightArmRotation(DEFAULT_RIGHTARM_ROTATION);
      }

      NBTTagList var6 = var1.getTagList("LeftLeg", 5);
      if (var6.tagCount() > 0) {
         this.setLeftLegRotation(new Rotations(var6));
      } else {
         this.setLeftLegRotation(DEFAULT_LEFTLEG_ROTATION);
      }

      NBTTagList var7 = var1.getTagList("RightLeg", 5);
      if (var7.tagCount() > 0) {
         this.setRightLegRotation(new Rotations(var7));
      } else {
         this.setRightLegRotation(DEFAULT_RIGHTLEG_ROTATION);
      }
   }

   public Rotations getRightArmRotation() {
      return this.rightArmRotation;
   }

   @Override
   public boolean isInRangeToRenderDist(double var1) {
      double var3 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0;
      if (Double.isNaN(var3) || var3 == 0.0) {
         var3 = 4.0;
      }

      var3 *= 64.0;
      return var1 < var3 * var3;
   }

   @Override
   public ItemStack[] getInventory() {
      return this.contents;
   }

   public void setSmall(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(10);
      if (var1) {
         var2 = (byte)(var2 | 1);
      } else {
         var2 = (byte)(var2 & -2);
      }

      this.ac.updateObject(10, var2);
   }

   @Override
   public boolean o_() {
      return this.isSmall();
   }

   public boolean isSmall() {
      return (this.ac.getWatchableObjectByte(10) & 1) != 0;
   }

   public boolean hasNoBasePlate() {
      return (this.ac.getWatchableObjectByte(10) & 8) != 0;
   }

   @Override
   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
      this.contents[var1] = var2;
   }

   public boolean getShowArms() {
      return (this.ac.getWatchableObjectByte(10) & 4) != 0;
   }

   @Override
   public float getEyeHeight() {
      return this.o_() ? this.K * 0.5F : this.K * 0.9F;
   }

   public void setNoGravity(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(10);
      if (var1) {
         var2 = (byte)(var2 | 2);
      } else {
         var2 = (byte)(var2 & -3);
      }

      this.ac.updateObject(10, var2);
   }

   public void setMarker(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(10);
      if (var1) {
         var2 = (byte)(var2 | 16);
      } else {
         var2 = (byte)(var2 & -17);
      }

      this.ac.updateObject(10, var2);
   }

   @Override
   public boolean isServerWorld() {
      return super.isServerWorld() && !this.hasNoGravity();
   }

   public void setShowArms(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(10);
      if (var1) {
         var2 = (byte)(var2 | 4);
      } else {
         var2 = (byte)(var2 & -5);
      }

      this.ac.updateObject(10, var2);
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.o.D) {
         return false;
      } else if (DamageSource.outOfWorld.equals(var1)) {
         this.setDead();
         return false;
      } else if (this.isEntityInvulnerable(var1) || this.canInteract || this.hasMarker()) {
         return false;
      } else if (var1.isExplosion()) {
         this.dropContents();
         this.setDead();
         return false;
      } else if (DamageSource.inFire.equals(var1)) {
         if (!this.isBurning()) {
            this.setFire(5);
         } else {
            this.damageArmorStand(0.15F);
         }

         return false;
      } else if (DamageSource.onFire.equals(var1) && this.getHealth() > 0.5F) {
         this.damageArmorStand(4.0F);
         return false;
      } else {
         boolean var3 = "arrow".equals(var1.getDamageType());
         boolean var4 = "player".equals(var1.getDamageType());
         if (!var4 && !var3) {
            return false;
         } else {
            if (var1.getSourceOfDamage() instanceof EntityArrow) {
               var1.getSourceOfDamage().setDead();
            }

            if (var1.getEntity() instanceof EntityPlayer && !((EntityPlayer)var1.getEntity()).bA.allowEdit) {
               return false;
            } else if (var1.isCreativePlayer()) {
               this.playParticles();
               this.setDead();
               return false;
            } else {
               long var5 = this.o.K();
               if (var5 - this.punchCooldown > 5L && !var3) {
                  this.punchCooldown = var5;
               } else {
                  this.dropBlock();
                  this.playParticles();
                  this.setDead();
               }

               return false;
            }
         }
      }
   }

   public Rotations getLeftLegRotation() {
      return this.leftLegRotation;
   }

   @Override
   public ItemStack getEquipmentInSlot(int var1) {
      return this.contents[var1];
   }

   @Override
   public float updateDistance(float var1, float var2) {
      this.aJ = this.A;
      this.aI = this.y;
      return 0.0F;
   }

   public Rotations getBodyRotation() {
      return this.bodyRotation;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      Rotations var1 = this.ac.getWatchableObjectRotations(11);
      if (!this.headRotation.equals(var1)) {
         this.setHeadRotation(var1);
      }

      Rotations var2 = this.ac.getWatchableObjectRotations(12);
      if (!this.bodyRotation.equals(var2)) {
         this.setBodyRotation(var2);
      }

      Rotations var3 = this.ac.getWatchableObjectRotations(13);
      if (!this.leftArmRotation.equals(var3)) {
         this.setLeftArmRotation(var3);
      }

      Rotations var4 = this.ac.getWatchableObjectRotations(14);
      if (!this.rightArmRotation.equals(var4)) {
         this.setRightArmRotation(var4);
      }

      Rotations var5 = this.ac.getWatchableObjectRotations(15);
      if (!this.leftLegRotation.equals(var5)) {
         this.setLeftLegRotation(var5);
      }

      Rotations var6 = this.ac.getWatchableObjectRotations(16);
      if (!this.rightLegRotation.equals(var6)) {
         this.setRightLegRotation(var6);
      }

      boolean var7 = this.hasMarker();
      if (!this.field_181028_bj && var7) {
         this.func_181550_a(false);
      } else {
         if (!this.field_181028_bj || var7) {
            return;
         }

         this.func_181550_a(true);
      }

      this.field_181028_bj = var7;
   }

   public Rotations getHeadRotation() {
      return this.headRotation;
   }

   @Override
   public boolean m_() {
      return false;
   }

   public boolean hasMarker() {
      return (this.ac.getWatchableObjectByte(10) & 16) != 0;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      NBTTagList var2 = new NBTTagList();

      for (int var3 = 0; var3 < this.contents.length; var3++) {
         NBTTagCompound var4 = new NBTTagCompound();
         if (this.contents[var3] != null) {
            this.contents[var3].writeToNBT(var4);
         }

         var2.appendTag(var4);
      }

      var1.setTag("Equipment", var2);
      if (this.getAlwaysRenderNameTag() && (this.aM() == null || this.aM().length() == 0)) {
         var1.setBoolean("CustomNameVisible", this.getAlwaysRenderNameTag());
      }

      var1.setBoolean("Invisible", this.isInvisible());
      var1.setBoolean("Small", this.isSmall());
      var1.setBoolean("ShowArms", this.getShowArms());
      var1.setInteger("DisabledSlots", this.disabledSlots);
      var1.setBoolean("NoGravity", this.hasNoGravity());
      var1.setBoolean("NoBasePlate", this.hasNoBasePlate());
      if (this.hasMarker()) {
         var1.setBoolean("Marker", this.hasMarker());
      }

      var1.setTag("Pose", this.readPoseFromNBT());
   }

   public void setHeadRotation(Rotations var1) {
      this.headRotation = var1;
      this.ac.updateObject(11, var1);
   }

   @Override
   public boolean replaceItemInInventory(int var1, ItemStack var2) {
      int var3;
      if (var1 == 99) {
         var3 = 0;
      } else {
         var3 = var1 - 100 + 1;
         if (var3 < 0 || var3 >= this.contents.length) {
            return false;
         }
      }

      if (var2 == null || EntityLiving.getArmorPosition(var2) == var3 || var3 == 4 && var2.getItem() instanceof ItemBlock) {
         this.setCurrentItemOrArmor(var3, var2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public ItemStack getHeldItem() {
      return this.contents[0];
   }

   public Rotations getLeftArmRotation() {
      return this.leftArmRotation;
   }

   public void setBodyRotation(Rotations var1) {
      this.bodyRotation = var1;
      this.ac.updateObject(12, var1);
   }

   @Override
   public boolean method_10521() {
      return this.isInvisible();
   }

   public boolean hasNoGravity() {
      return (this.ac.getWatchableObjectByte(10) & 2) != 0;
   }

   public void damageArmorStand(float var1) {
      float var2 = this.getHealth();
      var2 -= var1;
      if (var2 <= 0.5F) {
         this.dropContents();
         this.setDead();
      } else {
         this.setHealth(var2);
      }
   }

   @Override
   public void updatePotionMetadata() {
      this.setInvisible(this.canInteract);
   }

   public void setLeftLegRotation(Rotations var1) {
      this.leftLegRotation = var1;
      this.ac.updateObject(15, var1);
   }

   public void setRightArmRotation(Rotations var1) {
      this.rightArmRotation = var1;
      this.ac.updateObject(14, var1);
   }

   public void setNoBasePlate(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(10);
      if (var1) {
         var2 = (byte)(var2 | 8);
      } else {
         var2 = (byte)(var2 & -9);
      }

      this.ac.updateObject(10, var2);
   }

   public void playParticles() {
      if (this.o instanceof WorldServer) {
         ((WorldServer)this.o)
            .spawnParticle(
               EnumParticleTypes.BLOCK_DUST,
               this.s,
               this.t + this.K / 1.5,
               this.u,
               10,
               this.J / 4.0F,
               this.K / 4.0F,
               this.J / 4.0F,
               0.05,
               Block.getStateId(Blocks.planks.getDefaultState())
            );
      }
   }

   public void func_175422_a(EntityPlayer var1, int var2) {
      ItemStack var3 = this.contents[var2];
      if ((var3 == null || (this.disabledSlots & 1 << var2 + 8) == 0) && (var3 != null || (this.disabledSlots & 1 << var2 + 16) == 0)) {
         int var4 = var1.bi.currentItem;
         ItemStack var5 = var1.bi.getStackInSlot(var4);
         if (var1.bA.isCreativeMode && (var3 == null || var3.getItem() == Item.getItemFromBlock(Blocks.air)) && var5 != null) {
            ItemStack var7 = var5.copy();
            var7.stackSize = 1;
            this.setCurrentItemOrArmor(var2, var7);
         } else if (var5 == null || var5.stackSize <= 1) {
            this.setCurrentItemOrArmor(var2, var5);
            var1.bi.setInventorySlotContents(var4, var3);
         } else if (var3 == null) {
            ItemStack var6 = var5.copy();
            var6.stackSize = 1;
            this.setCurrentItemOrArmor(var2, var6);
            var5.stackSize--;
         }
      }
   }

   @Override
   public void moveEntityWithHeading(float var1, float var2) {
      if (!this.hasNoGravity()) {
         super.moveEntityWithHeading(var1, var2);
      }
   }

   public void setLeftArmRotation(Rotations var1) {
      this.leftArmRotation = var1;
      this.ac.updateObject(13, var1);
   }

   public Rotations getRightLegRotation() {
      return this.rightLegRotation;
   }

   public void dropBlock() {
      Block.a(this.o, new BlockPos(this), new ItemStack(Items.armor_stand));
      this.dropContents();
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if (var1.hasKey("Equipment", 9)) {
         NBTTagList var2 = var1.getTagList("Equipment", 10);

         for (int var3 = 0; var3 < this.contents.length; var3++) {
            this.contents[var3] = ItemStack.loadItemStackFromNBT(var2.getCompoundTagAt(var3));
         }
      }

      this.setInvisible(var1.getBoolean("Invisible"));
      this.setSmall(var1.getBoolean("Small"));
      this.setShowArms(var1.getBoolean("ShowArms"));
      this.disabledSlots = var1.getInteger("DisabledSlots");
      this.setNoGravity(var1.getBoolean("NoGravity"));
      this.setNoBasePlate(var1.getBoolean("NoBasePlate"));
      this.setMarker(var1.getBoolean("Marker"));
      this.field_181028_bj = !this.hasMarker();
      this.T = this.hasNoGravity();
      NBTTagCompound var4 = var1.getCompoundTag("Pose");
      this.writePoseToNBT(var4);
   }

   public void func_181550_a(boolean var1) {
      double var2 = this.s;
      double var4 = this.t;
      double var6 = this.u;
      if (var1) {
         this.setSize(0.5F, 1.975F);
      } else {
         this.setSize(0.0F, 0.0F);
      }

      this.b(var2, var4, var6);
   }

   @Override
   public void onKillCommand() {
      this.setDead();
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(10, (byte)0);
      this.ac.addObject(11, DEFAULT_HEAD_ROTATION);
      this.ac.addObject(12, DEFAULT_BODY_ROTATION);
      this.ac.addObject(13, DEFAULT_LEFTARM_ROTATION);
      this.ac.addObject(14, DEFAULT_RIGHTARM_ROTATION);
      this.ac.addObject(15, DEFAULT_LEFTLEG_ROTATION);
      this.ac.addObject(16, DEFAULT_RIGHTLEG_ROTATION);
   }

   public void dropContents() {
      for (int var1 = 0; var1 < this.contents.length; var1++) {
         if (this.contents[var1] != null && this.contents[var1].stackSize > 0) {
            if (this.contents[var1] != null) {
               Block.a(this.o, new BlockPos(this).up(), this.contents[var1]);
            }

            this.contents[var1] = null;
         }
      }
   }

   public EntityArmorStand(World var1, double var2, double var4, double var6) {
      this(var1);
      this.b(var2, var4, var6);
   }

   @Override
   public ItemStack getCurrentArmor(int var1) {
      return this.contents[var1 + 1];
   }

   public NBTTagCompound readPoseFromNBT() {
      NBTTagCompound var1 = new NBTTagCompound();
      if (!DEFAULT_HEAD_ROTATION.equals(this.headRotation)) {
         var1.setTag("Head", this.headRotation.writeToNBT());
      }

      if (!DEFAULT_BODY_ROTATION.equals(this.bodyRotation)) {
         var1.setTag("Body", this.bodyRotation.writeToNBT());
      }

      if (!DEFAULT_LEFTARM_ROTATION.equals(this.leftArmRotation)) {
         var1.setTag("LeftArm", this.leftArmRotation.writeToNBT());
      }

      if (!DEFAULT_RIGHTARM_ROTATION.equals(this.rightArmRotation)) {
         var1.setTag("RightArm", this.rightArmRotation.writeToNBT());
      }

      if (!DEFAULT_LEFTLEG_ROTATION.equals(this.leftLegRotation)) {
         var1.setTag("LeftLeg", this.leftLegRotation.writeToNBT());
      }

      if (!DEFAULT_RIGHTLEG_ROTATION.equals(this.rightLegRotation)) {
         var1.setTag("RightLeg", this.rightLegRotation.writeToNBT());
      }

      return var1;
   }
}
