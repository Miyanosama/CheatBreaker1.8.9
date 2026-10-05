package net.minecraft.entity.monster;

import com.cheatbreaker.client.util.ClientCrashReporter;
import io.netty.util.concurrent.DefaultPromise$5;
import net.minecraft.block.BlockVine$1;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.apache.log4j.lf5.Log4JLogRecord;
import org.apache.log4j.spi.NOPLogger;

public class EntityCreeper extends EntityMob {
   public DefaultPromise$5 field_0005;
   public int field_175494_bm;
   public int explosionRadius;
   public BlockVine$1 field_0007;
   public NOPLogger field_0000;
   public int fuseTime = 30;
   public ClientCrashReporter field_0009;
   public int lastActiveTime;
   public int timeSinceIgnited;
   public Log4JLogRecord field_0003;

   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (var2 != null && var2.getItem() == Items.flint_and_steel) {
         this.o.playSoundEffect(this.s + 0.5, this.t + 0.5, this.u + 0.5, "fire.ignite", 1.0F, this.V.nextFloat() * 0.4F + 0.8F);
         var1.swingItem();
         if (!this.o.D) {
            this.ignite();
            var2.damageItem(1, var1);
            return true;
         }
      }

      return super.interact(var1);
   }

   public void ignite() {
      this.ac.updateObject(18, (byte)1);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.ac.updateObject(17, (byte)(var1.getBoolean("powered") ? 1 : 0));
      if (var1.hasKey("Fuse", 99)) {
         this.fuseTime = var1.getShort("Fuse");
      }

      if (var1.hasKey("ExplosionRadius", 99)) {
         this.explosionRadius = var1.getByte("ExplosionRadius");
      }

      if (var1.getBoolean("ignited")) {
         this.ignite();
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
   }

   public boolean getPowered() {
      return this.ac.getWatchableObjectByte(17) == 1;
   }

   public void setCreeperState(int var1) {
      this.ac.updateObject(16, (byte)var1);
   }

   @Override
   public void fall(float var1, float var2) {
      super.fall(var1, var2);
      this.timeSinceIgnited = (int)(this.timeSinceIgnited + var1 * 1.5F);
      if (this.timeSinceIgnited > this.fuseTime - 5) {
         this.timeSinceIgnited = this.fuseTime - 5;
      }
   }

   @Override
   public String getDeathSound() {
      return "mob.creeper.death";
   }

   public float getCreeperFlashIntensity(float var1) {
      return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * var1) / (this.fuseTime - 2);
   }

   public boolean isAIEnabled() {
      return this.field_175494_bm < 1 && this.o.Q().getBoolean("doMobLoot");
   }

   public void func_175493_co() {
      this.field_175494_bm++;
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, (byte)-1);
      this.ac.addObject(17, (byte)0);
      this.ac.addObject(18, (byte)0);
   }

   @Override
   public void onDeath(DamageSource var1) {
      super.onDeath(var1);
      if (var1.getEntity() instanceof EntitySkeleton) {
         int var2 = Item.getIdFromItem(Items.record_13);
         int var3 = Item.getIdFromItem(Items.record_wait);
         int var4 = var2 + this.V.nextInt(var3 - var2 + 1);
         this.dropItem(Item.getItemById(var4), 1);
      } else if (var1.getEntity() instanceof EntityCreeper
         && var1.getEntity() != this
         && ((EntityCreeper)var1.getEntity()).getPowered()
         && ((EntityCreeper)var1.getEntity()).isAIEnabled()) {
         ((EntityCreeper)var1.getEntity()).func_175493_co();
         this.a(new ItemStack(Items.skull, 1, 4), 0.0F);
      }
   }

   @Override
   public void onStruckByLightning(EntityLightningBolt var1) {
      super.onStruckByLightning(var1);
      this.ac.updateObject(17, (byte)1);
   }

   public int getCreeperState() {
      return this.ac.getWatchableObjectByte(16);
   }

   public EntityCreeper(World var1) {
      super(var1);
      this.explosionRadius = 3;
      this.field_175494_bm = 0;
      this.i.addTask(1, new EntityAISwimming(this));
      this.i.addTask(2, new EntityAICreeperSwell(this));
      this.i.addTask(3, new EntityAIAvoidEntity<>(this, EntityOcelot.class, 6.0F, 1.0, 1.2));
      this.i.addTask(4, new EntityAIAttackOnCollide(this, 1.0, false));
      this.i.addTask(5, new EntityAIWander(this, 0.8));
      this.i.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(6, new EntityAILookIdle(this));
      this.bi.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
      this.bi.addTask(2, new EntityAIHurtByTarget(this, false));
   }

   @Override
   public Item getDropItem() {
      return Items.gunpowder;
   }

   @Override
   public int getMaxFallHeight() {
      return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      if (this.ac.getWatchableObjectByte(17) == 1) {
         var1.setBoolean("powered", true);
      }

      var1.setShort("Fuse", (short)this.fuseTime);
      var1.setByte("ExplosionRadius", (byte)this.explosionRadius);
      var1.setBoolean("ignited", this.hasIgnited());
   }

   @Override
   public String getHurtSound() {
      return "mob.creeper.say";
   }

   public void explode() {
      if (!this.o.D) {
         boolean var1 = this.o.Q().getBoolean("mobGriefing");
         float var2 = this.getPowered() ? 2.0F : 1.0F;
         this.o.createExplosion(this, this.s, this.t, this.u, this.explosionRadius * var2, var1);
         this.setDead();
      }
   }

   public boolean hasIgnited() {
      return this.ac.getWatchableObjectByte(18) != 0;
   }

   @Override
   public void onUpdate() {
      if (this.isEntityAlive()) {
         this.lastActiveTime = this.timeSinceIgnited;
         if (this.hasIgnited()) {
            this.setCreeperState(1);
         }

         int var1 = this.getCreeperState();
         if (var1 > 0 && this.timeSinceIgnited == 0) {
            this.playSound("creeper.primed", 1.0F, 0.5F);
         }

         this.timeSinceIgnited += var1;
         if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
         }

         if (this.timeSinceIgnited >= this.fuseTime) {
            this.timeSinceIgnited = this.fuseTime;
            this.explode();
         }
      }

      super.onUpdate();
   }

   @Override
   public boolean attackEntityAsMob(Entity var1) {
      return true;
   }
}
