package net.minecraft.entity;

import com.cheatbreaker.client.CheatBreaker;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import io.netty.channel.local.LocalChannel$1;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.Block$SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityLivingBase extends Entity {
   public float aE;
   public float aB;
   public float aZ;
   public float prevRotationYawHead;
   public EntityLivingBase lastAttacker;
   public float aC;
   public float prevOnGroundSpeedFactor;
   public float prevSwingProgress;
   public float aF;
   public BaseAttributeMap attributeMap;
   public Map<Integer, PotionEffect> activePotionsMap;
   public int av;
   public int swingProgressInt;
   public float field_0048;
   public int scoreValue;
   public boolean isSwingInProgress;
   public int arrowHitTimer;
   public int aQ;
   public int ax;
   public int revengeTimer;
   public float field_0023;
   public double newPosY;
   public double newRotationPitch;
   public float onGroundSpeedFactor;
   public float aA;
   public float field_0055;
   public int field_0052;
   public int au;
   public static UUID sprintingSpeedBoostModifierUUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
   public ItemStack[] previousEquipment;
   public EntityLivingBase entityLivingToAttack;
   public EntityPlayer aN;
   public float swingProgress;
   public int lastAttackerTime;
   public float ba;
   public float aw;
   public CombatTracker field_0054 = new CombatTracker(this);
   public float aJ;
   public int aD;
   public float aM;
   public int aO;
   public boolean potionsNeedUpdate;
   public boolean aY;
   public LocalChannel$1 field_0001;
   public double newPosZ;
   public float field_0057;
   public float aX;
   public double newRotationYaw;
   public int newPosRotationIncrements;
   public float absorptionAmount;
   public float aK;
   public float field_0036;
   public boolean dead;
   public float movedDistance;
   public float field_0010;
   public static AttributeModifier sprintingSpeedBoostModifier = new AttributeModifier(sprintingSpeedBoostModifierUUID, "Sprinting speed boost", 0.3F, 2)
      .setSaved(false);
   public float aI;
   public double newPosX;

   @Override
   public boolean m_() {
      return !this.I;
   }

   public void applyEntityAttributes() {
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed);
   }

   public String getFallSoundString(int var1) {
      return var1 > 4 ? "game.neutral.hurt.fall.big" : "game.neutral.hurt.fall.small";
   }

   public Random getRNG() {
      return this.V;
   }

   public boolean canDropLoot() {
      return !this.o_();
   }

   public void onLivingUpdate() {
      if (this.field_0052 > 0) {
         this.field_0052--;
      }

      if (this.newPosRotationIncrements > 0) {
         double var1 = this.s + (this.newPosX - this.s) / this.newPosRotationIncrements;
         double var3 = this.t + (this.newPosY - this.t) / this.newPosRotationIncrements;
         double var5 = this.u + (this.newPosZ - this.u) / this.newPosRotationIncrements;
         double var7 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - this.y);
         this.y = (float)(this.y + var7 / this.newPosRotationIncrements);
         this.z = (float)(this.z + (this.newRotationPitch - this.z) / this.newPosRotationIncrements);
         this.newPosRotationIncrements--;
         this.b(var1, var3, var5);
         this.setRotation(this.y, this.z);
      } else if (!this.isServerWorld()) {
         this.v *= 0.98;
         this.w *= 0.98;
         this.x *= 0.98;
      }

      if (Math.abs(this.v) < 0.005) {
         this.v = 0.0;
      }

      if (Math.abs(this.w) < 0.005) {
         this.w = 0.0;
      }

      if (Math.abs(this.x) < 0.005) {
         this.x = 0.0;
      }

      this.o.B.startSection("ai");
      if (this.isMovementBlocked()) {
         this.aY = false;
         this.aZ = 0.0F;
         this.ba = 0.0F;
         this.field_0036 = 0.0F;
      } else if (this.isServerWorld()) {
         this.o.B.startSection("newAi");
         this.updateEntityActionState();
         this.o.B.endSection();
      }

      this.o.B.endSection();
      this.o.B.startSection("jump");
      if (this.aY) {
         if (this.V()) {
            this.method_20656();
         } else if (this.ab()) {
            this.handleJumpLava();
         } else if (this.C && this.field_0052 == 0) {
            this.jump();
            this.field_0052 = 10;
         }
      } else {
         this.field_0052 = 0;
      }

      this.o.B.endSection();
      this.o.B.startSection("travel");
      this.aZ *= 0.98F;
      this.ba *= 0.98F;
      this.field_0036 *= 0.9F;
      this.moveEntityWithHeading(this.aZ, this.ba);
      this.o.B.endSection();
      this.o.B.startSection("push");
      if (!this.o.D) {
         this.collideWithNearbyEntities();
      }

      this.o.B.endSection();
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEFINED;
   }

   @Override
   public void updateFallState(double var1, boolean var3, Block var4, BlockPos var5) {
      if (!this.V()) {
         this.handleWaterMovement();
      }

      if (!this.o.D && this.O > 3.0F && var3) {
         IBlockState var6 = this.o.getBlockState(var5);
         Block var7 = var6.getBlock();
         float var8 = MathHelper.ceiling_float_int(this.O - 3.0F);
         if (var7.getMaterial() != Material.air) {
            double var9 = Math.min(0.2F + var8 / 15.0F, 10.0F);
            if (var9 > 2.5) {
               var9 = 2.5;
            }

            int var11 = (int)(150.0 * var9);
            ((WorldServer)this.o).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.s, this.t, this.u, var11, 0.0, 0.0, 0.0, 0.15F, Block.getStateId(var6));
         }
      }

      super.updateFallState(var1, var3, var4, var5);
   }

   public int getRevengeTimer() {
      return this.revengeTimer;
   }

   public boolean isPotionActive(int var1) {
      return this.activePotionsMap.containsKey(var1);
   }

   @Override
   public boolean isEntityAlive() {
      return !this.I && this.getHealth() > 0.0F;
   }

   public void dropFewItems(boolean var1, int var2) {
   }

   public boolean n_() {
      int var1 = MathHelper.floor_double(this.s);
      int var2 = MathHelper.floor_double(this.getEntityBoundingBox().b);
      int var3 = MathHelper.floor_double(this.u);
      Block var4 = this.o.getBlockState(new BlockPos(var1, var2, var3)).getBlock();
      return (var4 == Blocks.ladder || var4 == Blocks.vine) && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).isSpectator());
   }

   public boolean t(Entity var1) {
      return this.o.rayTraceBlocks(new Vec3(this.s, this.t + this.getEyeHeight(), this.u), new Vec3(var1.s, var1.t + var1.getEyeHeight(), var1.u)) == null;
   }

   public void setLastAttacker(Entity var1) {
      if (var1 instanceof EntityLivingBase) {
         this.lastAttacker = (EntityLivingBase)var1;
      } else {
         this.lastAttacker = null;
      }

      this.lastAttackerTime = this.W;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setFloat("HealF", this.getHealth());
      var1.setShort("Health", (short)Math.ceil(this.getHealth()));
      var1.setShort("HurtTime", (short)this.au);
      var1.setInteger("HurtByTimestamp", this.revengeTimer);
      var1.setShort("DeathTime", (short)this.ax);
      var1.setFloat("AbsorptionAmount", this.getAbsorptionAmount());

      for (ItemStack var5 : this.getInventory()) {
         if (var5 != null) {
            this.attributeMap.removeAttributeModifiers(var5.getAttributeModifiers());
         }
      }

      var1.setTag("Attributes", SharedMonsterAttributes.writeBaseAttributeMapToNBT(this.getAttributeMap()));

      for (ItemStack var12 : this.getInventory()) {
         if (var12 != null) {
            this.attributeMap.applyAttributeModifiers(var12.getAttributeModifiers());
         }
      }

      if (!this.activePotionsMap.isEmpty()) {
         NBTTagList var7 = new NBTTagList();

         for (PotionEffect var11 : this.activePotionsMap.values()) {
            var7.appendTag(var11.writeCustomPotionEffectToNBT(new NBTTagCompound()));
         }

         var1.setTag("ActiveEffects", var7);
      }
   }

   public float applyArmorCalculations(DamageSource var1, float var2) {
      if (!var1.isUnblockable()) {
         int var3 = 25 - this.getTotalArmorValue();
         float var4 = var2 * var3;
         this.damageArmor(var2);
         var2 = var4 / 25.0F;
      }

      return var2;
   }

   public void updateArmSwingProgress() {
      int var1 = this.getArmSwingAnimationEnd();
      if (this.isSwingInProgress) {
         this.swingProgressInt++;
         if (this.swingProgressInt >= var1) {
            this.swingProgressInt = 0;
            this.isSwingInProgress = false;
         }
      } else {
         this.swingProgressInt = 0;
      }

      this.swingProgress = (float)this.swingProgressInt / var1;
   }

   public EntityLivingBase getAITarget() {
      return this.entityLivingToAttack;
   }

   public float updateDistance(float var1, float var2) {
      float var3 = MathHelper.wrapAngleTo180_float(var1 - this.aI);
      this.aI += var3 * 0.3F;
      float var4 = MathHelper.wrapAngleTo180_float(this.y - this.aI);
      boolean var5 = var4 < -90.0F || var4 >= 90.0F;
      if (var4 < -75.0F) {
         var4 = -75.0F;
      }

      if (var4 >= 75.0F) {
         var4 = 75.0F;
      }

      this.aI = this.y - var4;
      if (var4 * var4 > 2500.0F) {
         this.aI += var4 * 0.2F;
      }

      if (var5) {
         var2 *= -1.0F;
      }

      return var2;
   }

   public int getLastAttackerTime() {
      return this.lastAttackerTime;
   }

   @Override
   public void setRotationYawHead(float var1) {
      this.aK = var1;
   }

   @Override
   public void updateRidden() {
      super.updateRidden();
      this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor;
      this.onGroundSpeedFactor = 0.0F;
      this.O = 0.0F;
   }

   public Team getTeam() {
      return this.o.Z().getPlayersTeam(this.aK().toString());
   }

   public void sendEnterCombat() {
   }

   public void resetPotionEffectMetadata() {
      this.ac.updateObject(8, (byte)0);
      this.ac.updateObject(7, 0);
   }

   @Override
   public Vec3 getLook(float var1) {
      if (var1 == 1.0F) {
         return this.getVectorForRotation(this.z, this.aK);
      } else {
         float var2 = this.B + (this.z - this.B) * var1;
         float var3 = this.prevRotationYawHead + (this.aK - this.prevRotationYawHead) * var1;
         return this.getVectorForRotation(var2, var3);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else if (this.o.D) {
         return false;
      } else {
         this.aQ = 0;
         if (this.getHealth() <= 0.0F) {
            return false;
         } else if (var1.isFireDamage() && this.isPotionActive(Potion.fireResistance)) {
            return false;
         } else {
            if ((var1 == DamageSource.anvil || var1 == DamageSource.fallingBlock) && this.getEquipmentInSlot(4) != null) {
               this.getEquipmentInSlot(4).damageItem((int)(var2 * 4.0F + this.V.nextFloat() * var2 * 2.0F), this);
               var2 *= 0.75F;
            }

            this.aB = 1.5F;
            boolean var3 = true;
            if (this.Z > this.aD / 2.0F) {
               if (var2 <= this.aX) {
                  return false;
               }

               this.damageEntity(var1, var2 - this.aX);
               this.aX = var2;
               var3 = false;
            } else {
               this.aX = var2;
               this.Z = this.aD;
               this.damageEntity(var1, var2);
               this.au = this.av = 10;
            }

            this.aw = 0.0F;
            Entity var4 = var1.getEntity();
            if (var4 != null) {
               if (var4 instanceof EntityLivingBase) {
                  this.setRevengeTarget((EntityLivingBase)var4);
               }

               if (var4 instanceof EntityPlayer) {
                  this.aO = 100;
                  this.aN = (EntityPlayer)var4;
               } else if (var4 instanceof EntityWolf) {
                  EntityWolf var5 = (EntityWolf)var4;
                  if (var5.isTamed()) {
                     this.aO = 100;
                     this.aN = null;
                  }
               }
            }

            if (var3) {
               this.o.setEntityState(this, (byte)2);
               if (var1 != DamageSource.drown) {
                  this.setBeenAttacked();
               }

               if (var4 != null) {
                  double var9 = var4.s - this.s;

                  double var7;
                  for (var7 = var4.u - this.u; var9 * var9 + var7 * var7 < 1.0E-4; var7 = (Math.random() - Math.random()) * 0.01) {
                     var9 = (Math.random() - Math.random()) * 0.01;
                  }

                  this.aw = (float)(MathHelper.atan2(var7, var9) * 180.0 / Math.PI - this.y);
                  this.knockBack(var4, var2, var9, var7);
               } else {
                  this.aw = (int)(Math.random() * 2.0) * 180;
               }
            }

            if (this.getHealth() <= 0.0F) {
               String var10 = this.getDeathSound();
               if (var3 && var10 != null) {
                  this.playSound(var10, this.getSoundVolume(), this.bC());
               }

               this.onDeath(var1);
            } else {
               String var11 = this.getHurtSound();
               if (var3 && var11 != null) {
                  this.playSound(var11, this.getSoundVolume(), this.bC());
               }
            }

            return true;
         }
      }
   }

   public float getJumpUpwardsMotion() {
      return 0.42F;
   }

   public boolean isOnSameTeam(EntityLivingBase var1) {
      return this.isOnTeam(var1.getTeam());
   }

   public void dropEquipment(boolean var1, int var2) {
   }

   @Override
   public void setSprinting(boolean var1) {
      super.setSprinting(var1);
      IAttributeInstance var2 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      if (var2.getModifier(sprintingSpeedBoostModifierUUID) != null) {
         var2.removeModifier(sprintingSpeedBoostModifier);
      }

      if (var1) {
         var2.applyModifier(sprintingSpeedBoostModifier);
      }
   }

   public int getTotalArmorValue() {
      int var1 = 0;

      for (ItemStack var5 : this.getInventory()) {
         if (var5 != null && var5.getItem() instanceof ItemArmor) {
            int var6 = ((ItemArmor)var5.getItem()).damageReduceAmount;
            var1 += var6;
         }
      }

      return var1;
   }

   public void method_20646(Entity var1) {
      double var2 = var1.s;
      double var4 = var1.getEntityBoundingBox().b + var1.K;
      double var6 = var1.u;
      byte var8 = 1;

      for (int var9 = -var8; var9 <= var8; var9++) {
         for (int var10 = -var8; var10 < var8; var10++) {
            if (var9 != 0 || var10 != 0) {
               int var11 = (int)(this.s + var9);
               int var12 = (int)(this.u + var10);
               AxisAlignedBB var13 = this.getEntityBoundingBox().offset(var9, 1.0, var10);
               if (this.o.getCollisionBoxes(var13).isEmpty()) {
                  if (World.doesBlockHaveSolidTopSurface(this.o, new BlockPos(var11, (int)this.t, var12))) {
                     this.setPositionAndUpdate(this.s + var9, this.t + 1.0, this.u + var10);
                     return;
                  }

                  if (World.doesBlockHaveSolidTopSurface(this.o, new BlockPos(var11, (int)this.t - 1, var12))
                     || this.o.getBlockState(new BlockPos(var11, (int)this.t - 1, var12)).getBlock().getMaterial() == Material.water) {
                     var2 = this.s + var9;
                     var4 = this.t + 1.0;
                     var6 = this.u + var10;
                  }
               }
            }
         }
      }

      this.setPositionAndUpdate(var2, var4, var6);
   }

   @Override
   public void kill() {
      this.attackEntityFrom(DamageSource.outOfWorld, 4.0F);
   }

   public void setArrowCountInEntity(int var1) {
      this.ac.updateObject(9, (byte)var1);
   }

   public EntityLivingBase getAttackingEntity() {
      return (EntityLivingBase)(this.field_0054.func_94550_c() != null
         ? this.field_0054.func_94550_c()
         : (this.aN != null ? this.aN : (this.entityLivingToAttack != null ? this.entityLivingToAttack : null)));
   }

   public BaseAttributeMap getAttributeMap() {
      if (this.attributeMap == null) {
         this.attributeMap = new ServersideAttributeMap();
      }

      return this.attributeMap;
   }

   public float getAbsorptionAmount() {
      return this.absorptionAmount;
   }

   @Override
   public void setBeenAttacked() {
      this.G = this.V.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue();
   }

   public void method_20656() {
      this.w += 0.04F;
   }

   public String getHurtSound() {
      return "game.neutral.hurt";
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      this.setAbsorptionAmount(var1.getFloat("AbsorptionAmount"));
      if (var1.hasKey("Attributes", 9) && this.o != null && !this.o.D) {
         SharedMonsterAttributes.setAttributeModifiers(this.getAttributeMap(), var1.getTagList("Attributes", 10));
      }

      if (var1.hasKey("ActiveEffects", 9)) {
         NBTTagList var2 = var1.getTagList("ActiveEffects", 10);

         for (int var3 = 0; var3 < var2.tagCount(); var3++) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            PotionEffect var5 = PotionEffect.readCustomPotionEffectFromNBT(var4);
            if (var5 != null) {
               this.activePotionsMap.put(var5.getPotionID(), var5);
            }
         }
      }

      if (var1.hasKey("HealF", 99)) {
         this.setHealth(var1.getFloat("HealF"));
      } else {
         NBTBase var6 = var1.getTag("Health");
         if (var6 == null) {
            this.setHealth(this.getMaxHealth());
         } else if (var6.getId() == 5) {
            this.setHealth(((NBTTagFloat)var6).getFloat());
         } else if (var6.getId() == 2) {
            this.setHealth(((NBTTagShort)var6).getShort());
         }
      }

      this.au = var1.getShort("HurtTime");
      this.ax = var1.getShort("DeathTime");
      this.revengeTimer = var1.getInteger("HurtByTimestamp");
   }

   public int bh() {
      return this.aQ;
   }

   public abstract ItemStack getHeldItem();

   public boolean bJ() {
      return false;
   }

   public float getSoundVolume() {
      return 1.0F;
   }

   public int getExperiencePoints(EntityPlayer var1) {
      return 0;
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.I;
   }

   public void updateEntityActionState() {
   }

   public float bI() {
      return this.field_0048;
   }

   public void method_20635() {
      Iterator var1 = this.activePotionsMap.keySet().iterator();

      while (var1.hasNext()) {
         Integer var2 = (Integer)var1.next();
         PotionEffect var3 = this.activePotionsMap.get(var2);
         if (!var3.onUpdate(this)) {
            if (!this.o.D) {
               var1.remove();
               this.onFinishedPotionEffect(var3);
            }
         } else if (var3.getDuration() % 600 == 0) {
            this.a(var3, false);
         }
      }

      if (this.potionsNeedUpdate) {
         if (!this.o.D) {
            this.updatePotionMetadata();
         }

         this.potionsNeedUpdate = false;
      }

      int var12 = this.ac.getWatchableObjectInt(7);
      boolean var13 = this.ac.getWatchableObjectByte(8) > 0;
      if (var12 > 0) {
         boolean var4 = false;
         if (!this.isInvisible()) {
            var4 = this.V.nextBoolean();
         } else {
            var4 = this.V.nextInt(15) == 0;
         }

         if (var13) {
            var4 &= this.V.nextInt(5) == 0;
         }

         if (var4 && var12 > 0) {
            boolean var5 = CheatBreaker.getInstance().getModuleManager().field_0044.isEnabled()
               && CheatBreaker.getInstance().getModuleManager().field_0044.field_0012.method_08908();
            double var6 = (var12 >> 16 & 0xFF) / 255.0;
            double var8 = (var12 >> 8 & 0xFF) / 255.0;
            double var10 = (var12 >> 0 & 0xFF) / 255.0;
            if (var5) {
               this.o
                  .spawnParticle(
                     var13 ? EnumParticleTypes.SPELL_MOB_AMBIENT : EnumParticleTypes.SPELL_MOB,
                     this.s + (this.V.nextDouble() - 0.5) * this.J,
                     this.t + this.V.nextDouble() * this.K,
                     this.u + (this.V.nextDouble() - 0.5) * this.J,
                     var6,
                     var8,
                     var10
                  );
            } else if (!CheatBreaker.getInstance().getModuleManager().field_0044.isEnabled()) {
               this.o
                  .spawnParticle(
                     var13 ? EnumParticleTypes.SPELL_MOB_AMBIENT : EnumParticleTypes.SPELL_MOB,
                     this.s + (this.V.nextDouble() - 0.5) * this.J,
                     this.t + this.V.nextDouble() * this.K,
                     this.u + (this.V.nextDouble() - 0.5) * this.J,
                     var6,
                     var8,
                     var10
                  );
            }
         }
      }
   }

   public void knockBack(Entity var1, float var2, double var3, double var5) {
      if (this.V.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()) {
         this.ai = true;
         float var7 = MathHelper.sqrt_double(var3 * var3 + var5 * var5);
         float var8 = 0.4F;
         this.v /= 2.0;
         this.w /= 2.0;
         this.x /= 2.0;
         this.v -= var3 / var7 * var8;
         this.w += var8;
         this.x -= var5 / var7 * var8;
         if (this.w > 0.4F) {
            this.w = 0.4F;
         }
      }
   }

   public void clearActivePotions() {
      Iterator var1 = this.activePotionsMap.keySet().iterator();

      while (var1.hasNext()) {
         Integer var2 = (Integer)var1.next();
         PotionEffect var3 = this.activePotionsMap.get(var2);
         if (!this.o.D) {
            var1.remove();
            this.onFinishedPotionEffect(var3);
         }
      }
   }

   @Override
   public void onKillCommand() {
      this.attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE);
   }

   public void handleJumpLava() {
      this.w += 0.04F;
   }

   public boolean isMovementBlocked() {
      return this.getHealth() <= 0.0F;
   }

   public boolean isPlayer() {
      return false;
   }

   public void onNewPotionEffect(PotionEffect var1) {
      this.potionsNeedUpdate = true;
      if (!this.o.D) {
         Potion.potionTypes[var1.getPotionID()].applyAttributesModifiersToEntity(this, this.getAttributeMap(), var1.getAmplifier());
      }
   }

   public EntityLivingBase getLastAttacker() {
      return this.lastAttacker;
   }

   public void setRevengeTarget(EntityLivingBase var1) {
      this.entityLivingToAttack = var1;
      this.revengeTimer = this.W;
   }

   public void addRandomDrop() {
   }

   @Override
   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10) {
      this.newPosX = var1;
      this.newPosY = var3;
      this.newPosZ = var5;
      this.newRotationYaw = var7;
      this.newRotationPitch = var8;
      this.newPosRotationIncrements = var9;
   }

   public void renderBrokenItemStack(ItemStack var1) {
      this.playSound("random.break", 0.8F, 0.8F + this.o.s.nextFloat() * 0.4F);

      for (int var2 = 0; var2 < 5; var2++) {
         Vec3 var3 = new Vec3((this.V.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
         var3 = var3.rotatePitch(-this.z * (float) Math.PI / 180.0F);
         var3 = var3.rotateYaw(-this.y * (float) Math.PI / 180.0F);
         double var4 = -this.V.nextFloat() * 0.6 - 0.3;
         Vec3 var6 = new Vec3((this.V.nextFloat() - 0.5) * 0.3, var4, 0.6);
         var6 = var6.rotatePitch(-this.z * (float) Math.PI / 180.0F);
         var6 = var6.rotateYaw(-this.y * (float) Math.PI / 180.0F);
         var6 = var6.addVector(this.s, this.t + this.getEyeHeight(), this.u);
         this.o
            .spawnParticle(
               EnumParticleTypes.ITEM_CRACK,
               var6.xCoord,
               var6.yCoord,
               var6.zCoord,
               var3.xCoord,
               var3.yCoord + 0.05,
               var3.zCoord,
               Item.getIdFromItem(var1.getItem())
            );
      }
   }

   @Override
   public abstract ItemStack[] getInventory();

   @Override
   public abstract void setCurrentItemOrArmor(int var1, ItemStack var2);

   public boolean isPotionApplicable(PotionEffect var1) {
      if (this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
         int var2 = var1.getPotionID();
         if (var2 == Potion.regeneration.id || var2 == Potion.poison.id) {
            return false;
         }
      }

      return true;
   }

   public float getMaxHealth() {
      return (float)this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
   }

   public void onDeathUpdate() {
      this.ax++;
      if (this.ax == 20) {
         if (!this.o.D && (this.aO > 0 || this.isPlayer()) && this.canDropLoot() && this.o.Q().getBoolean("doMobLoot")) {
            int var1 = this.getExperiencePoints(this.aN);

            while (var1 > 0) {
               int var2 = EntityXPOrb.getXPSplit(var1);
               var1 -= var2;
               this.o.spawnEntityInWorld(new EntityXPOrb(this.o, this.s, this.t, this.u, var2));
            }
         }

         this.setDead();

         for (int var8 = 0; var8 < 20; var8++) {
            double var9 = this.V.nextGaussian() * 0.02;
            double var4 = this.V.nextGaussian() * 0.02;
            double var6 = this.V.nextGaussian() * 0.02;
            this.o
               .spawnParticle(
                  EnumParticleTypes.EXPLOSION_NORMAL,
                  this.s + this.V.nextFloat() * this.J * 2.0F - this.J,
                  this.t + this.V.nextFloat() * this.K,
                  this.u + this.V.nextFloat() * this.J * 2.0F - this.J,
                  var9,
                  var4,
                  var6
               );
         }
      }
   }

   @Override
   public void performHurtAnimation() {
      this.au = this.av = 10;
      this.aw = 0.0F;
   }

   @Override
   public void setRenderYawOffset(float var1) {
      this.aI = var1;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.o.D) {
         int var1 = this.getArrowCountInEntity();
         if (var1 > 0) {
            if (this.arrowHitTimer <= 0) {
               this.arrowHitTimer = 20 * (30 - var1);
            }

            this.arrowHitTimer--;
            if (this.arrowHitTimer <= 0) {
               this.setArrowCountInEntity(var1 - 1);
            }
         }

         for (int var2 = 0; var2 < 5; var2++) {
            ItemStack var3 = this.previousEquipment[var2];
            ItemStack var4 = this.getEquipmentInSlot(var2);
            if (!ItemStack.areItemStacksEqual(var4, var3)) {
               ((WorldServer)this.o).getEntityTracker().sendToAllTrackingEntity(this, new S04PacketEntityEquipment(this.F(), var2, var4));
               if (var3 != null) {
                  this.attributeMap.removeAttributeModifiers(var3.getAttributeModifiers());
               }

               if (var4 != null) {
                  this.attributeMap.applyAttributeModifiers(var4.getAttributeModifiers());
               }

               this.previousEquipment[var2] = var4 == null ? null : var4.copy();
            }
         }

         if (this.W % 20 == 0) {
            this.getCombatTracker().reset();
         }
      }

      this.onLivingUpdate();
      double var9 = this.s - this.p;
      double var10 = this.u - this.r;
      float var5 = (float)(var9 * var9 + var10 * var10);
      float var6 = this.aI;
      float var7 = 0.0F;
      this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor;
      float var8 = 0.0F;
      if (var5 > 0.0025000002F) {
         var8 = 1.0F;
         var7 = (float)Math.sqrt(var5) * 3.0F;
         var6 = (float)MathHelper.atan2(var10, var9) * 180.0F / (float) Math.PI - 90.0F;
      }

      if (this.swingProgress > 0.0F) {
         var6 = this.y;
      }

      if (!this.C) {
         var8 = 0.0F;
      }

      this.onGroundSpeedFactor = this.onGroundSpeedFactor + (var8 - this.onGroundSpeedFactor) * 0.3F;
      this.o.B.startSection("headTurn");
      var7 = this.updateDistance(var6, var7);
      this.o.B.endSection();
      this.o.B.startSection("rangeChecks");

      while (this.y - this.A < -180.0F) {
         this.A -= 360.0F;
      }

      while (this.y - this.A >= 180.0F) {
         this.A += 360.0F;
      }

      while (this.aI - this.aJ < -180.0F) {
         this.aJ -= 360.0F;
      }

      while (this.aI - this.aJ >= 180.0F) {
         this.aJ += 360.0F;
      }

      while (this.z - this.B < -180.0F) {
         this.B -= 360.0F;
      }

      while (this.z - this.B >= 180.0F) {
         this.B += 360.0F;
      }

      while (this.aK - this.prevRotationYawHead < -180.0F) {
         this.prevRotationYawHead -= 360.0F;
      }

      while (this.aK - this.prevRotationYawHead >= 180.0F) {
         this.prevRotationYawHead += 360.0F;
      }

      this.o.B.endSection();
      this.movedDistance += var7;
   }

   public void onDeath(DamageSource var1) {
      Entity var2 = var1.getEntity();
      EntityLivingBase var3 = this.getAttackingEntity();
      if (this.scoreValue >= 0 && var3 != null) {
         var3.addToPlayerScore(this, this.scoreValue);
      }

      if (var2 != null) {
         var2.onKillEntity(this);
      }

      this.dead = true;
      this.getCombatTracker().reset();
      if (!this.o.D) {
         int var4 = 0;
         if (var2 instanceof EntityPlayer) {
            var4 = EnchantmentHelper.getLootingModifier((EntityLivingBase)var2);
         }

         if (this.canDropLoot() && this.o.Q().getBoolean("doMobLoot")) {
            this.dropFewItems(this.aO > 0, var4);
            this.dropEquipment(this.aO > 0, var4);
            if (this.aO > 0 && this.V.nextFloat() < 0.025F + var4 * 0.01F) {
               this.addRandomDrop();
            }
         }
      }

      this.o.setEntityState(this, (byte)3);
   }

   public CombatTracker getCombatTracker() {
      return this.field_0054;
   }

   public PotionEffect getActivePotionEffect(Potion var1) {
      return this.activePotionsMap.get(var1.id);
   }

   public boolean isPotionActive(Potion var1) {
      return this.activePotionsMap.containsKey(var1.id);
   }

   @Override
   public void mountEntity(Entity var1) {
      if (this.m != null && var1 == null) {
         if (!this.o.D) {
            this.method_20646(this.m);
         }

         if (this.m != null) {
            this.m.l = null;
         }

         this.m = null;
      } else {
         super.mountEntity(var1);
      }
   }

   public boolean isServerWorld() {
      return !this.o.D;
   }

   public EntityLivingBase(World var1) {
      super(var1);
      this.activePotionsMap = Maps.newHashMap();
      this.previousEquipment = new ItemStack[5];
      this.aD = 20;
      this.aM = 0.02F;
      this.potionsNeedUpdate = true;
      this.applyEntityAttributes();
      this.setHealth(this.getMaxHealth());
      this.k = true;
      this.field_0055 = (float)((Math.random() + 1.0) * 0.01F);
      this.b(this.s, this.t, this.u);
      this.field_0023 = (float)Math.random() * 12398.0F;
      this.y = (float)(Math.random() * Math.PI * 2.0);
      this.aK = this.y;
      this.S = 0.6F;
   }

   public void onFinishedPotionEffect(PotionEffect var1) {
      this.potionsNeedUpdate = true;
      if (!this.o.D) {
         Potion.potionTypes[var1.getPotionID()].removeAttributesModifiersFromEntity(this, this.getAttributeMap(), var1.getAmplifier());
      }
   }

   public int getArmSwingAnimationEnd() {
      return this.isPotionActive(Potion.digSpeed)
         ? 6 - (1 + this.getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1
         : (this.isPotionActive(Potion.digSlowdown) ? 6 + (1 + this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2 : 6);
   }

   public void a(Entity var1, int var2) {
      if (!var1.I && !this.o.D) {
         EntityTracker var3 = ((WorldServer)this.o).getEntityTracker();
         if (var1 instanceof EntityItem) {
            var3.sendToAllTrackingEntity(var1, new S0DPacketCollectItem(var1.F(), this.F()));
         }

         if (var1 instanceof EntityArrow) {
            var3.sendToAllTrackingEntity(var1, new S0DPacketCollectItem(var1.F(), this.F()));
         }

         if (var1 instanceof EntityXPOrb) {
            var3.sendToAllTrackingEntity(var1, new S0DPacketCollectItem(var1.F(), this.F()));
         }
      }
   }

   @Override
   public boolean aO() {
      return false;
   }

   public boolean attackEntityAsMob(Entity var1) {
      this.setLastAttacker(var1);
      return false;
   }

   @Override
   public void fall(float var1, float var2) {
      super.fall(var1, var2);
      PotionEffect var3 = this.getActivePotionEffect(Potion.jump);
      float var4 = var3 != null ? var3.getAmplifier() + 1 : 0.0F;
      int var5 = MathHelper.ceiling_float_int((var1 - 3.0F - var4) * var2);
      if (var5 > 0) {
         this.playSound(this.getFallSoundString(var5), 1.0F, 1.0F);
         this.attackEntityFrom(DamageSource.fall, var5);
         int var6 = MathHelper.floor_double(this.s);
         int var7 = MathHelper.floor_double(this.t - 0.2F);
         int var8 = MathHelper.floor_double(this.u);
         Block var9 = this.o.getBlockState(new BlockPos(var6, var7, var8)).getBlock();
         if (var9.getMaterial() != Material.air) {
            Block$SoundType var10 = var9.stepSound;
            this.playSound(var10.getStepSound(), var10.getVolume() * 0.5F, var10.getFrequency() * 0.75F);
         }
      }
   }

   public abstract ItemStack getCurrentArmor(int var1);

   public float bC() {
      return this.o_() ? (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.5F : (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F;
   }

   public int decreaseAirSupply(int var1) {
      int var2 = EnchantmentHelper.getRespiration(this);
      return var2 > 0 && this.V.nextInt(var2 + 1) > 0 ? var1 : var1 - 1;
   }

   public void swingItem() {
      if (!this.isSwingInProgress || this.swingProgressInt >= this.getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
         this.swingProgressInt = -1;
         this.isSwingInProgress = true;
         if (this.o instanceof WorldServer) {
            ((WorldServer)this.o).getEntityTracker().sendToAllTrackingEntity(this, new S0BPacketAnimation(this, 0));
         }
      }
   }

   public void sendEndCombat() {
   }

   public float getHealth() {
      return this.ac.getWatchableObjectFloat(6);
   }

   public void removePotionEffectClient(int var1) {
      this.activePotionsMap.remove(var1);
   }

   public int getArrowCountInEntity() {
      return this.ac.getWatchableObjectByte(9);
   }

   @Override
   public void k_() {
      this.ac.addObject(7, 0);
      this.ac.addObject(8, (byte)0);
      this.ac.addObject(9, (byte)0);
      this.ac.addObject(6, 1.0F);
   }

   public void heal(float var1) {
      float var2 = this.getHealth();
      if (var2 > 0.0F) {
         this.setHealth(var2 + var1);
      }
   }

   public void collideWithNearbyEntities() {
      List var1 = this.o
         .a(this, this.getEntityBoundingBox().expand(0.2F, 0.0, 0.2F), Predicates.and(EntitySelectors.NOT_SPECTATING, new EntityLivingBase$1(this)));
      if (!var1.isEmpty()) {
         for (int var2 = 0; var2 < var1.size(); var2++) {
            Entity var3 = (Entity)var1.get(var2);
            this.collideWithEntity(var3);
         }
      }
   }

   public void moveEntityWithHeading(float var1, float var2) {
      if (this.isServerWorld()) {
         if (!this.V() || this instanceof EntityPlayer && ((EntityPlayer)this).bA.isFlying) {
            if (!this.ab() || this instanceof EntityPlayer && ((EntityPlayer)this).bA.isFlying) {
               float var9 = 0.91F;
               if (this.C) {
                  var9 = this.o
                        .getBlockState(
                           new BlockPos(
                              MathHelper.floor_double(this.s), MathHelper.floor_double(this.getEntityBoundingBox().b) - 1, MathHelper.floor_double(this.u)
                           )
                        )
                        .getBlock()
                        .L
                     * 0.91F;
               }

               float var4 = 0.16277136F / (var9 * var9 * var9);
               float var12;
               if (this.C) {
                  var12 = this.bI() * var4;
               } else {
                  var12 = this.aM;
               }

               this.a(var1, var2, var12);
               var9 = 0.91F;
               if (this.C) {
                  var9 = this.o
                        .getBlockState(
                           new BlockPos(
                              MathHelper.floor_double(this.s), MathHelper.floor_double(this.getEntityBoundingBox().b) - 1, MathHelper.floor_double(this.u)
                           )
                        )
                        .getBlock()
                        .L
                     * 0.91F;
               }

               if (this.n_()) {
                  float var14 = 0.15F;
                  this.v = MathHelper.clamp_double(this.v, -var14, var14);
                  this.x = MathHelper.clamp_double(this.x, -var14, var14);
                  this.O = 0.0F;
                  if (this.w < -0.15) {
                     this.w = -0.15;
                  }

                  boolean var15 = this.isSneaking() && this instanceof EntityPlayer;
                  if (var15 && this.w < 0.0) {
                     this.w = 0.0;
                  }
               }

               this.d(this.v, this.w, this.x);
               if (this.D && this.n_()) {
                  this.w = 0.2;
               }

               if (this.o.D
                  && (
                     !this.o.e(new BlockPos((int)this.s, 0, (int)this.u))
                        || !this.o.getChunkFromBlockCoords(new BlockPos((int)this.s, 0, (int)this.u)).isLoaded()
                  )) {
                  if (this.t > 0.0) {
                     this.w = -0.1;
                  } else {
                     this.w = 0.0;
                  }
               } else {
                  this.w -= 0.08;
               }

               this.w *= 0.98F;
               this.v *= var9;
               this.x *= var9;
            } else {
               double var8 = this.t;
               this.a(var1, var2, 0.02F);
               this.d(this.v, this.w, this.x);
               this.v *= 0.5;
               this.w *= 0.5;
               this.x *= 0.5;
               this.w -= 0.02;
               if (this.D && this.isOffsetPositionInLiquid(this.v, this.w + 0.6F - this.t + var8, this.x)) {
                  this.w = 0.3F;
               }
            }
         } else {
            double var3 = this.t;
            float var5 = 0.8F;
            float var6 = 0.02F;
            float var7 = EnchantmentHelper.method_08075(this);
            if (var7 > 3.0F) {
               var7 = 3.0F;
            }

            if (!this.C) {
               var7 *= 0.5F;
            }

            if (var7 > 0.0F) {
               var5 += (0.54600006F - var5) * var7 / 3.0F;
               var6 += (this.bI() * 1.0F - var6) * var7 / 3.0F;
            }

            this.a(var1, var2, var6);
            this.d(this.v, this.w, this.x);
            this.v *= var5;
            this.w *= 0.8F;
            this.x *= var5;
            this.w -= 0.02;
            if (this.D && this.isOffsetPositionInLiquid(this.v, this.w + 0.6F - this.t + var3, this.x)) {
               this.w = 0.3F;
            }
         }
      }

      this.aA = this.aB;
      double var11 = this.s - this.p;
      double var13 = this.u - this.r;
      float var16 = MathHelper.sqrt_double(var11 * var11 + var13 * var13) * 4.0F;
      if (var16 > 1.0F) {
         var16 = 1.0F;
      }

      this.aB = this.aB + (var16 - this.aB) * 0.4F;
      this.aC = this.aC + this.aB;
   }

   public float applyPotionDamageCalculations(DamageSource var1, float var2) {
      if (var1.isDamageAbsolute()) {
         return var2;
      } else {
         if (this.isPotionActive(Potion.resistance) && var1 != DamageSource.outOfWorld) {
            int var3 = (this.getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5;
            int var4 = 25 - var3;
            float var5 = var2 * var4;
            var2 = var5 / 25.0F;
         }

         if (var2 <= 0.0F) {
            return 0.0F;
         } else {
            int var6 = EnchantmentHelper.getEnchantmentModifierDamage(this.getInventory(), var1);
            if (var6 > 20) {
               var6 = 20;
            }

            if (var6 > 0 && var6 <= 20) {
               int var7 = 25 - var6;
               float var8 = var2 * var7;
               var2 = var8 / 25.0F;
            }

            return var2;
         }
      }
   }

   public boolean isEntityUndead() {
      return this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
   }

   public boolean isOnTeam(Team var1) {
      return this.getTeam() != null ? this.getTeam().isSameTeam(var1) : false;
   }

   public abstract ItemStack getEquipmentInSlot(int var1);

   public void jump() {
      this.w = this.getJumpUpwardsMotion();
      if (this.isPotionActive(Potion.jump)) {
         this.w = this.w + (this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F;
      }

      if (this.isSprinting()) {
         float var1 = this.y * (float) (Math.PI / 180.0);
         this.v = this.v - MathHelper.sin(var1) * 0.2F;
         this.x = this.x + MathHelper.cos(var1) * 0.2F;
      }

      this.ai = true;
   }

   public void removePotionEffect(int var1) {
      PotionEffect var2 = this.activePotionsMap.remove(var1);
      if (var2 != null) {
         this.onFinishedPotionEffect(var2);
      }
   }

   public void a(PotionEffect var1, boolean var2) {
      this.potionsNeedUpdate = true;
      if (var2 && !this.o.D) {
         Potion.potionTypes[var1.getPotionID()].removeAttributesModifiersFromEntity(this, this.getAttributeMap(), var1.getAmplifier());
         Potion.potionTypes[var1.getPotionID()].applyAttributesModifiersToEntity(this, this.getAttributeMap(), var1.getAmplifier());
      }
   }

   public boolean o_() {
      return false;
   }

   public void setAIMoveSpeed(float var1) {
      this.field_0048 = var1;
   }

   public void markPotionsDirty() {
      this.potionsNeedUpdate = true;
   }

   @Override
   public void onEntityUpdate() {
      this.prevSwingProgress = this.swingProgress;
      super.onEntityUpdate();
      this.o.B.startSection("livingEntityBaseTick");
      boolean var1 = this instanceof EntityPlayer;
      if (this.isEntityAlive()) {
         if (this.isEntityInsideOpaqueBlock()) {
            this.attackEntityFrom(DamageSource.inWall, 1.0F);
         } else if (var1 && !this.o.af().contains(this.getEntityBoundingBox())) {
            double var2 = this.o.af().getClosestDistance(this) + this.o.af().getDamageBuffer();
            if (var2 < 0.0) {
               this.attackEntityFrom(DamageSource.inWall, Math.max(1, MathHelper.floor_double(-var2 * this.o.af().getDamageAmount())));
            }
         }
      }

      if (this.isImmuneToFire() || this.o.D) {
         this.extinguish();
      }

      boolean var7 = var1 && ((EntityPlayer)this).bA.disableDamage;
      if (this.isEntityAlive()) {
         if (this.a(Material.water)) {
            if (!this.method_06476() && !this.isPotionActive(Potion.waterBreathing.id) && !var7) {
               this.setAir(this.decreaseAirSupply(this.getAir()));
               if (this.getAir() == -20) {
                  this.setAir(0);

                  for (int var3 = 0; var3 < 8; var3++) {
                     float var4 = this.V.nextFloat() - this.V.nextFloat();
                     float var5 = this.V.nextFloat() - this.V.nextFloat();
                     float var6 = this.V.nextFloat() - this.V.nextFloat();
                     this.o.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.s + var4, this.t + var5, this.u + var6, this.v, this.w, this.x);
                  }

                  this.attackEntityFrom(DamageSource.drown, 2.0F);
               }
            }

            if (!this.o.D && this.au() && this.m instanceof EntityLivingBase) {
               this.mountEntity((Entity)null);
            }
         } else {
            this.setAir(300);
         }
      }

      if (this.isEntityAlive() && this.U()) {
         this.extinguish();
      }

      this.aE = this.aF;
      if (this.au > 0) {
         this.au--;
      }

      if (this.Z > 0 && !(this instanceof EntityPlayerMP)) {
         this.Z--;
      }

      if (this.getHealth() <= 0.0F) {
         this.onDeathUpdate();
      }

      if (this.aO > 0) {
         this.aO--;
      } else {
         this.aN = null;
      }

      if (this.lastAttacker != null && !this.lastAttacker.isEntityAlive()) {
         this.lastAttacker = null;
      }

      if (this.entityLivingToAttack != null) {
         if (!this.entityLivingToAttack.isEntityAlive()) {
            this.setRevengeTarget((EntityLivingBase)null);
         } else if (this.W - this.revengeTimer > 100) {
            this.setRevengeTarget((EntityLivingBase)null);
         }
      }

      this.method_20635();
      this.field_0010 = this.movedDistance;
      this.aJ = this.aI;
      this.prevRotationYawHead = this.aK;
      this.A = this.y;
      this.B = this.z;
      this.o.B.endSection();
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 2) {
         this.aB = 1.5F;
         this.Z = this.aD;
         this.au = this.av = 10;
         this.aw = 0.0F;
         String var2 = this.getHurtSound();
         if (var2 != null) {
            this.playSound(this.getHurtSound(), this.getSoundVolume(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
         }

         this.attackEntityFrom(DamageSource.generic, 0.0F);
      } else if (var1 == 3) {
         String var3 = this.getDeathSound();
         if (var3 != null) {
            this.playSound(this.getDeathSound(), this.getSoundVolume(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
         }

         this.setHealth(0.0F);
         this.onDeath(DamageSource.generic);
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   public Collection<PotionEffect> getActivePotionEffects() {
      return this.activePotionsMap.values();
   }

   public String getDeathSound() {
      return "game.neutral.die";
   }

   public boolean method_06476() {
      return false;
   }

   public void setAbsorptionAmount(float var1) {
      if (var1 < 0.0F) {
         var1 = 0.0F;
      }

      this.absorptionAmount = var1;
   }

   @Override
   public float getRotationYawHead() {
      return this.aK;
   }

   public IAttributeInstance getEntityAttribute(IAttribute var1) {
      return this.getAttributeMap().getAttributeInstance(var1);
   }

   public void damageArmor(float var1) {
   }

   public void updatePotionMetadata() {
      if (this.activePotionsMap.isEmpty()) {
         this.resetPotionEffectMetadata();
         this.setInvisible(false);
      } else {
         int var1 = PotionHelper.calcPotionLiquidColor(this.activePotionsMap.values());
         this.ac.updateObject(8, (byte)(PotionHelper.getAreAmbient(this.activePotionsMap.values()) ? 1 : 0));
         this.ac.updateObject(7, var1);
         this.setInvisible(this.isPotionActive(Potion.invisibility.id));
      }
   }

   public float getSwingProgress(float var1) {
      float var2 = this.swingProgress - this.prevSwingProgress;
      if (var2 < 0.0F) {
         var2++;
      }

      return this.prevSwingProgress + var2 * var1;
   }

   public void collideWithEntity(Entity var1) {
      var1.applyEntityCollision(this);
   }

   public void damageEntity(DamageSource var1, float var2) {
      if (!this.isEntityInvulnerable(var1)) {
         var2 = this.applyArmorCalculations(var1, var2);
         var2 = this.applyPotionDamageCalculations(var1, var2);
         float var7 = Math.max(var2 - this.getAbsorptionAmount(), 0.0F);
         this.setAbsorptionAmount(this.getAbsorptionAmount() - (var2 - var7));
         if (var7 != 0.0F) {
            float var4 = this.getHealth();
            this.setHealth(var4 - var7);
            this.getCombatTracker().trackDamage(var1, var4, var7);
            this.setAbsorptionAmount(this.getAbsorptionAmount() - var7);
         }
      }
   }

   public void c(PotionEffect var1) {
      if (this.isPotionApplicable(var1)) {
         if (this.activePotionsMap.containsKey(var1.getPotionID())) {
            this.activePotionsMap.get(var1.getPotionID()).combine(var1);
            this.a(this.activePotionsMap.get(var1.getPotionID()), true);
         } else {
            this.activePotionsMap.put(var1.getPotionID(), var1);
            this.onNewPotionEffect(var1);
         }
      }
   }

   public void i(boolean var1) {
      this.aY = var1;
   }

   @Override
   public Vec3 getLookVec() {
      return this.getLook(1.0F);
   }

   public void setHealth(float var1) {
      this.ac.updateObject(6, MathHelper.clamp_float(var1, 0.0F, this.getMaxHealth()));
   }
}
