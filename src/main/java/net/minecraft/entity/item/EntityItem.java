package net.minecraft.entity.item;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityItem extends Entity {
   public String owner;
   public int age;
   public int health = 5;
   public static Logger logger = LogManager.getLogger();
   public String thrower;
   public int delayBeforeCanPickup;
   public float hoverStart = (float)(Math.random() * Math.PI * 2.0);

   public void setThrower(String var1) {
      this.thrower = var1;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("Health", (byte)this.health);
      var1.setShort("Age", (short)this.age);
      var1.setShort("PickupDelay", (short)this.delayBeforeCanPickup);
      if (this.getThrower() != null) {
         var1.setString("Thrower", this.thrower);
      }

      if (this.getOwner() != null) {
         var1.setString("Owner", this.owner);
      }

      if (this.getEntityItem() != null) {
         var1.setTag("Item", this.getEntityItem().writeToNBT(new NBTTagCompound()));
      }
   }

   @Override
   public boolean handleWaterMovement() {
      if (this.o.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.water, this)) {
         if (!this.Y && !this.aa) {
            this.X();
         }

         this.Y = true;
      } else {
         this.Y = false;
      }

      return this.Y;
   }

   public EntityItem(World var1, double var2, double var4, double var6) {
      super(var1);
      this.setSize(0.25F, 0.25F);
      this.b(var2, var4, var6);
      this.y = (float)(Math.random() * 360.0);
      this.v = (float)(Math.random() * 0.2F - 0.1F);
      this.w = 0.2F;
      this.x = (float)(Math.random() * 0.2F - 0.1F);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      this.health = var1.getShort("Health") & 255;
      this.age = var1.getShort("Age");
      if (var1.hasKey("PickupDelay")) {
         this.delayBeforeCanPickup = var1.getShort("PickupDelay");
      }

      if (var1.hasKey("Owner")) {
         this.owner = var1.getString("Owner");
      }

      if (var1.hasKey("Thrower")) {
         this.thrower = var1.getString("Thrower");
      }

      NBTTagCompound var2 = var1.getCompoundTag("Item");
      this.setEntityItemStack(ItemStack.loadItemStackFromNBT(var2));
      if (this.getEntityItem() == null) {
         this.setDead();
      }
   }

   public void setPickupDelay(int var1) {
      this.delayBeforeCanPickup = var1;
   }

   public String getThrower() {
      return this.thrower;
   }

   public void setEntityItemStack(ItemStack var1) {
      this.H().updateObject(10, var1);
      this.H().setObjectWatched(10);
   }

   public void setAgeToCreativeDespawnTime() {
      this.age = 4800;
   }

   public EntityItem(World var1) {
      super(var1);
      this.setSize(0.25F, 0.25F);
      this.setEntityItemStack(new ItemStack(Blocks.air, 0));
   }

   public void searchForOtherItemsNearby() {
      for (EntityItem var2 : this.o.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().expand(0.5, 0.0, 0.5))) {
         this.combineItems(var2);
      }
   }

   public EntityItem(World var1, double var2, double var4, double var6, ItemStack var8) {
      this(var1, var2, var4, var6);
      this.setEntityItemStack(var8);
   }

   public void setInfinitePickupDelay() {
      this.delayBeforeCanPickup = 32767;
   }

   @Override
   public void onUpdate() {
      if (this.getEntityItem() == null) {
         this.setDead();
      } else {
         super.onUpdate();
         if (this.delayBeforeCanPickup > 0 && this.delayBeforeCanPickup != 32767) {
            this.delayBeforeCanPickup--;
         }

         this.p = this.s;
         this.q = this.t;
         this.r = this.u;
         this.w -= 0.04F;
         this.T = this.j(this.s, (this.getEntityBoundingBox().b + this.getEntityBoundingBox().e) / 2.0, this.u);
         this.d(this.v, this.w, this.x);
         boolean var1 = (int)this.p != (int)this.s || (int)this.q != (int)this.t || (int)this.r != (int)this.u;
         if (var1 || this.W % 25 == 0) {
            if (this.o.getBlockState(new BlockPos(this)).getBlock().getMaterial() == Material.lava) {
               this.w = 0.2F;
               this.v = (this.V.nextFloat() - this.V.nextFloat()) * 0.2F;
               this.x = (this.V.nextFloat() - this.V.nextFloat()) * 0.2F;
               this.playSound("random.fizz", 0.4F, 2.0F + this.V.nextFloat() * 0.4F);
            }

            if (!this.o.D) {
               this.searchForOtherItemsNearby();
            }
         }

         float var2 = 0.98F;
         if (this.C) {
            var2 = this.o
                  .getBlockState(
                     new BlockPos(MathHelper.floor_double(this.s), MathHelper.floor_double(this.getEntityBoundingBox().b) - 1, MathHelper.floor_double(this.u))
                  )
                  .getBlock()
                  .L
               * 0.98F;
         }

         this.v *= var2;
         this.w *= 0.98F;
         this.x *= var2;
         if (this.C) {
            this.w *= -0.5;
         }

         if (this.age != -32768) {
            this.age++;
         }

         this.handleWaterMovement();
         if (!this.o.D && this.age >= 6000) {
            this.setDead();
         }
      }
   }

   public String getOwner() {
      return this.owner;
   }

   public ItemStack getEntityItem() {
      ItemStack var1 = this.H().getWatchableObjectItemStack(10);
      if (var1 == null) {
         if (this.o != null) {
            logger.error("Item entity " + this.F() + " has no item?!");
         }

         return new ItemStack(Blocks.stone);
      } else {
         return var1;
      }
   }

   public void func_174870_v() {
      this.setInfinitePickupDelay();
      this.age = 5999;
   }

   public void setOwner(String var1) {
      this.owner = var1;
   }

   @Override
   public void k_() {
      this.H().addObjectByDataType(10, 5);
   }

   @Override
   public String z_() {
      return this.u_() ? this.aM() : StatCollector.translateToLocal("item." + this.getEntityItem().getUnlocalizedName());
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else if (this.getEntityItem() != null && this.getEntityItem().getItem() == Items.nether_star && var1.isExplosion()) {
         return false;
      } else {
         this.setBeenAttacked();
         this.health = (int)(this.health - var2);
         if (this.health <= 0) {
            this.setDead();
         }

         return false;
      }
   }

   public int getAge() {
      return this.age;
   }

   @Override
   public void b_(EntityPlayer var1) {
      if (!this.o.D) {
         ItemStack var2 = this.getEntityItem();
         int var3 = var2.stackSize;
         if (this.delayBeforeCanPickup == 0
            && (this.owner == null || 6000 - this.age <= 200 || this.owner.equals(var1.z_()))
            && var1.bi.addItemStackToInventory(var2)) {
            if (var2.getItem() == Item.getItemFromBlock(Blocks.log)) {
               var1.triggerAchievement(AchievementList.recoveredField321);
            }

            if (var2.getItem() == Item.getItemFromBlock(Blocks.log2)) {
               var1.triggerAchievement(AchievementList.recoveredField321);
            }

            if (var2.getItem() == Items.leather) {
               var1.triggerAchievement(AchievementList.recoveredField322);
            }

            if (var2.getItem() == Items.diamond) {
               var1.triggerAchievement(AchievementList.recoveredField320);
            }

            if (var2.getItem() == Items.blaze_rod) {
               var1.triggerAchievement(AchievementList.recoveredField318);
            }

            if (var2.getItem() == Items.diamond && this.getThrower() != null) {
               EntityPlayer var4 = this.o.getPlayerEntityByName(this.getThrower());
               if (var4 != null && var4 != var1) {
                  var4.triggerAchievement(AchievementList.diamondsToYou);
               }
            }

            if (!this.R()) {
               this.o.a(var1, "random.pop", 0.2F, ((this.V.nextFloat() - this.V.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            }

            var1.a(this, var3);
            if (var2.stackSize <= 0) {
               this.setDead();
            }
         }
      }
   }

   @Override
   public void travelToDimension(int var1) {
      super.travelToDimension(var1);
      if (!this.o.D) {
         this.searchForOtherItemsNearby();
      }
   }

   public void setNoPickupDelay() {
      this.delayBeforeCanPickup = 0;
   }

   @Override
   public void dealFireDamage(int var1) {
      this.attackEntityFrom(DamageSource.inFire, var1);
   }

   public boolean cannotPickup() {
      return this.delayBeforeCanPickup > 0;
   }

   @Override
   public boolean l_() {
      return false;
   }

   public boolean combineItems(EntityItem var1) {
      if (var1 == this) {
         return false;
      } else if (var1.isEntityAlive() && this.isEntityAlive()) {
         ItemStack var2 = this.getEntityItem();
         ItemStack var3 = var1.getEntityItem();
         if (this.delayBeforeCanPickup == 32767 || var1.delayBeforeCanPickup == 32767) {
            return false;
         } else if (this.age != -32768 && var1.age != -32768) {
            if (var3.getItem() != var2.getItem()) {
               return false;
            } else if (var3.hasTagCompound() ^ var2.hasTagCompound()) {
               return false;
            } else if (var3.hasTagCompound() && !var3.getTagCompound().equals(var2.getTagCompound())) {
               return false;
            } else if (var3.getItem() == null) {
               return false;
            } else if (var3.getItem().getHasSubtypes() && var3.getMetadata() != var2.getMetadata()) {
               return false;
            } else if (var3.stackSize < var2.stackSize) {
               return var1.combineItems(this);
            } else if (var3.stackSize + var2.stackSize > var3.getMaxStackSize()) {
               return false;
            } else {
               var3.stackSize = var3.stackSize + var2.stackSize;
               var1.delayBeforeCanPickup = Math.max(var1.delayBeforeCanPickup, this.delayBeforeCanPickup);
               var1.age = Math.min(var1.age, this.age);
               var1.setEntityItemStack(var3);
               this.setDead();
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void setNoDespawn() {
      this.age = -6000;
   }

   @Override
   public boolean r_() {
      return false;
   }

   public void setDefaultPickupDelay() {
      this.delayBeforeCanPickup = 10;
   }
}
