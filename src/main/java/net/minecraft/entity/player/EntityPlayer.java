package net.minecraft.entity.player;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.ClientResourceManager;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public abstract class EntityPlayer extends EntityLivingBase {
   public float bD;
   public float renderOffsetX;
   public PlayerCapabilities bA;
   public double chasingPosX;
   public float renderOffsetZ;
   public BlockPos playerLocation;
   public int itemInUseCount;
   public int xpCooldown;
   public double chasingPosY;
   public Container bj;
   public InventoryEnderChest theInventoryEnderChest;
   public int lastXPSound;
   public InventoryPlayer bi = new InventoryPlayer(this);
   public float recoveredField2487;
   public double prevChasingPosY;
   public boolean bw;
   public boolean hasReducedDebug;
   public int xpSeed;
   public float cameraYaw;
   public double prevChasingPosX;
   public ClientResourceManager recoveredField2488;
   public int sleepTimer;
   public int bB;
   public ClientResourceManager recoveredField2489;
   public double chasingPosZ;
   public int flyToggleTimer;
   public boolean spawnForced;
   public BlockPos startMinecartRidingCoordinate;
   public float recoveredField2490;
   public double prevChasingPosZ;
   public int bC;
   public EntityFishHook fishEntity;
   public float bZ;
   public float prevCameraYaw;
   public Container bk;
   public BlockPos spawnChunk;
   public ItemStack itemInUse;
   public GameProfile gameProfile;
   public FoodStats foodStats;

   public void attackTargetEntityWithCurrentItem(Entity var1) {
      if (var1.r_() && !var1.hitByEntity(this)) {
         float var2 = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
         int var3 = 0;
         float var4 = 0.0F;
         if (var1 instanceof EntityLivingBase) {
            var4 = EnchantmentHelper.getModifierForCreature(this.getHeldItem(), ((EntityLivingBase)var1).getCreatureAttribute());
         } else {
            var4 = EnchantmentHelper.getModifierForCreature(this.getHeldItem(), EnumCreatureAttribute.UNDEFINED);
         }

         var3 += EnchantmentHelper.getKnockbackModifier(this);
         if (this.isSprinting()) {
            var3++;
         }

         if (var2 > 0.0F || var4 > 0.0F) {
            boolean var5 = this.O > 0.0F
               && !this.C
               && !this.n_()
               && !this.V()
               && !this.isPotionActive(Potion.blindness)
               && this.m == null
               && var1 instanceof EntityLivingBase;
            if (var5 && var2 > 0.0F) {
               var2 *= 1.5F;
            }

            var2 += var4;
            boolean var6 = false;
            int var7 = EnchantmentHelper.getFireAspectModifier(this);
            if (var1 instanceof EntityLivingBase && var7 > 0 && !var1.isBurning()) {
               var6 = true;
               var1.setFire(1);
            }

            double var8 = var1.v;
            double var10 = var1.w;
            double var12 = var1.x;
            boolean var14 = var1.attackEntityFrom(DamageSource.causePlayerDamage(this), var2);
            if (var14) {
               if (var3 > 0) {
                  var1.addVelocity(
                     -MathHelper.sin(this.y * (float) Math.PI / 180.0F) * var3 * 0.5F, 0.1, MathHelper.cos(this.y * (float) Math.PI / 180.0F) * var3 * 0.5F
                  );
                  this.v *= 0.6;
                  this.x *= 0.6;
                  this.setSprinting(false);
               }

               if (var1 instanceof EntityPlayerMP && var1.G) {
                  ((EntityPlayerMP)var1).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(var1));
                  var1.G = false;
                  var1.v = var8;
                  var1.w = var10;
                  var1.x = var12;
               }

               boolean var15 = Minecraft.getMinecraft().isSingleplayer()
                  ? CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1624.getValue().equals("Override")
                  : CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1636.getValue().equals("Override");
               boolean var16 = Minecraft.getMinecraft().isSingleplayer()
                  ? CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1624.getValue().equals("Vanilla")
                  : CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1636.getValue().equals("Vanilla");
               if (var5
                  || CheatBreaker.getInstance().getModuleManager().recoveredField1730.isEnabled()
                     && (Boolean)CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1621.getValue().equals("Always")
                     && !var16) {
                  if (CheatBreaker.getInstance().getModuleManager().recoveredField1730.isEnabled() && !var16) {
                     if (!CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1621.getValue().equals("Never") && var15) {
                        float var17 = (Float)CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1628.getValue();
                        Minecraft.getMinecraft().effectRenderer.method_00737(var1, EnumParticleTypes.CRIT, var17);
                     }
                  } else {
                     this.onCriticalHit(var1);
                  }
               }

               if (var4 > 0.0F
                  || CheatBreaker.getInstance().getModuleManager().recoveredField1730.isEnabled()
                     && (Boolean)CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1629.getValue().equals("Always")
                     && !var16) {
                  if (CheatBreaker.getInstance().getModuleManager().recoveredField1730.isEnabled() && !var16) {
                     if (!CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1629.getValue().equals("Never") && var15) {
                        float var23 = (Float)CheatBreaker.getInstance().getModuleManager().recoveredField1730.recoveredField1623.getValue();
                        Minecraft.getMinecraft().effectRenderer.method_00737(var1, EnumParticleTypes.CRIT_MAGIC, var23);
                     }
                  } else {
                     this.onEnchantmentCritical(var1);
                  }
               }

               if (var2 >= 18.0F) {
                  this.triggerAchievement(AchievementList.recoveredField319);
               }

               this.setLastAttacker(var1);
               if (var1 instanceof EntityLivingBase) {
                  EnchantmentHelper.applyThornEnchantments((EntityLivingBase)var1, this);
               }

               EnchantmentHelper.applyArthropodEnchantments(this, var1);
               ItemStack var24 = this.getCurrentEquippedItem();
               Object var18 = var1;
               if (var1 instanceof EntityDragonPart) {
                  IEntityMultiPart var19 = ((EntityDragonPart)var1).entityDragonObj;
                  if (var19 instanceof EntityLivingBase) {
                     var18 = (EntityLivingBase)var19;
                  }
               }

               if (var24 != null && var18 instanceof EntityLivingBase) {
                  var24.hitEntity((EntityLivingBase)var18, this);
                  if (var24.stackSize <= 0) {
                     this.ca();
                  }
               }

               if (var1 instanceof EntityLivingBase) {
                  this.addStat(StatList.damageDealtStat, Math.round(var2 * 10.0F));
                  if (var7 > 0) {
                     var1.setFire(var7 * 4);
                  }
               }

               this.addExhaustion(0.3F);
            } else if (var6) {
               var1.extinguish();
            }
         }
      }
   }

   public boolean hasReducedDebug() {
      return this.hasReducedDebug;
   }

   public boolean isInBed() {
      return this.o.getBlockState(this.playerLocation).getBlock() == Blocks.bed;
   }

   public void addChatComponentMessage(IChatComponent var1) {
   }

   @Override
   public void addToPlayerScore(Entity var1, int var2) {
      this.addScore(var2);
      Collection var3 = this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.totalKillCount);
      if (var1 instanceof EntityPlayer) {
         this.triggerAchievement(StatList.playerKillsStat);
         var3.addAll(this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.playerKillCount));
         var3.addAll(this.func_175137_e(var1));
      } else {
         this.triggerAchievement(StatList.mobKillsStat);
      }

      for (ScoreObjective var5 : (Iterable<ScoreObjective>)(Iterable<?>)(var3)) {
         Score var6 = this.getWorldScoreboard().getValueFromObjective(this.z_(), var5);
         var6.func_96648_a();
      }
   }

   @Override
   public boolean replaceItemInInventory(int var1, ItemStack var2) {
      if (var1 >= 0 && var1 < this.bi.mainInventory.length) {
         this.bi.setInventorySlotContents(var1, var2);
         return true;
      } else {
         int var3 = var1 - 100;
         if (var3 >= 0 && var3 < this.bi.armorInventory.length) {
            int var5 = var3 + 1;
            if (var2 != null && var2.getItem() != null) {
               if (var2.getItem() instanceof ItemArmor) {
                  if (EntityLiving.getArmorPosition(var2) != var5) {
                     return false;
                  }
               } else if (var5 != 4 || var2.getItem() != Items.skull && !(var2.getItem() instanceof ItemBlock)) {
                  return false;
               }
            }

            this.bi.setInventorySlotContents(var3 + this.bi.mainInventory.length, var2);
            return true;
         } else {
            int var4 = var1 - 200;
            if (var4 >= 0 && var4 < this.theInventoryEnderChest.getSizeInventory()) {
               this.theInventoryEnderChest.setInventorySlotContents(var4, var2);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   @Override
   public void damageArmor(float var1) {
      this.bi.damageArmor(var1);
   }

   public void method_00396(ClientResourceManager var1) {
      this.recoveredField2489 = var1;
   }

   @Override
   public String getDeathSound() {
      return "game.player.die";
   }

   public void setScore(int var1) {
      this.ac.updateObject(18, var1);
   }

   @Override
   public float getEyeHeight() {
      float var1 = 1.62F;
      if (this.bJ()) {
         var1 = 0.2F;
      }

      if (this.isSneaking()) {
         var1 -= 0.08F;
      }

      return var1;
   }

   @Override
   public void setInWeb() {
      if (!this.bA.isFlying) {
         super.setInWeb();
      }
   }

   public int getXPSeed() {
      return this.xpSeed;
   }

   public EntityPlayer.EnumStatus trySleep(BlockPos var1) {
      if (!this.o.D) {
         if (this.bJ() || !this.isEntityAlive()) {
            return EntityPlayer.EnumStatus.OTHER_PROBLEM;
         }

         if (!this.o.t.isSurfaceWorld()) {
            return EntityPlayer.EnumStatus.NOT_POSSIBLE_HERE;
         }

         if (this.o.isDaytime()) {
            return EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW;
         }

         if (Math.abs(this.s - var1.getX()) > 3.0 || Math.abs(this.t - var1.getY()) > 2.0 || Math.abs(this.u - var1.getZ()) > 3.0) {
            return EntityPlayer.EnumStatus.TOO_FAR_AWAY;
         }

         double var2 = 8.0;
         double var4 = 5.0;
         List var6 = this.o
            .getEntitiesWithinAABB(
               EntityMob.class,
               new AxisAlignedBB(var1.getX() - var2, var1.getY() - var4, var1.getZ() - var2, var1.getX() + var2, var1.getY() + var4, var1.getZ() + var2)
            );
         if (!var6.isEmpty()) {
            return EntityPlayer.EnumStatus.NOT_SAFE;
         }
      }

      if (this.au()) {
         this.mountEntity((Entity)null);
      }

      this.setSize(0.2F, 0.2F);
      if (this.o.e(var1)) {
         EnumFacing var7 = this.o.getBlockState(var1).getValue(BlockDirectional.O);
         float var3 = 0.5F;
         float var8 = 0.5F;
         switch (var7) {
            case SOUTH:
               var8 = 0.9F;
               break;
            case NORTH:
               var8 = 0.1F;
               break;
            case WEST:
               var3 = 0.1F;
               break;
            case EAST:
               var3 = 0.9F;
         }

         this.func_175139_a(var7);
         this.b(var1.getX() + var3, var1.getY() + 0.6875F, var1.getZ() + var8);
      } else {
         this.b(var1.getX() + 0.5F, var1.getY() + 0.6875F, var1.getZ() + 0.5F);
      }

      this.bw = true;
      this.sleepTimer = 0;
      this.playerLocation = var1;
      this.v = this.x = this.w = 0.0;
      if (!this.o.D) {
         this.o.updateAllPlayersSleepingFlag();
      }

      return EntityPlayer.EnumStatus.OK;
   }

   public ClientResourceManager method_00358() {
      return this.recoveredField2488;
   }

   public void setItemInUse(ItemStack var1, int var2) {
      if (var1 != this.itemInUse) {
         this.itemInUse = var1;
         this.itemInUseCount = var2;
         if (!this.o.D) {
            this.setEating(true);
         }
      }
   }

   @Override
   public double getYOffset() {
      return -0.35;
   }

   public void setSpawnPoint(BlockPos var1, boolean var2) {
      if (var1 != null) {
         this.spawnChunk = var1;
         this.spawnForced = var2;
      } else {
         this.spawnChunk = null;
         this.spawnForced = false;
      }
   }

   @Override
   public ItemStack[] getInventory() {
      return this.bi.armorInventory;
   }

   public void joinEntityItemWithWorld(EntityItem var1) {
      this.o.spawnEntityInWorld(var1);
   }

   @Override
   public int getTotalArmorValue() {
      return this.bi.getTotalArmorValue();
   }

   @Override
   public boolean isMovementBlocked() {
      return this.getHealth() <= 0.0F || this.bJ();
   }

   public void addExhaustion(float var1) {
      if (!this.bA.disableDamage && !this.o.D) {
         this.foodStats.addExhaustion(var1);
      }
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 9) {
         this.onItemUseFinish();
      } else if (var1 == 23) {
         this.hasReducedDebug = false;
      } else if (var1 == 22) {
         this.hasReducedDebug = true;
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public void X() {
      if (!this.isSpectator()) {
         super.X();
      }
   }

   public Scoreboard getWorldScoreboard() {
      return this.o.Z();
   }

   public void collideWithPlayer(Entity var1) {
      var1.b_(this);
   }

   public boolean method_00445() {
      return this.recoveredField2488 != null;
   }

   public void addMountedMovementStat(double var1, double var3, double var5) {
      if (this.m != null) {
         int var7 = Math.round(MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
         if (var7 > 0) {
            if (this.m instanceof EntityMinecart) {
               this.addStat(StatList.distanceByMinecartStat, var7);
               if (this.startMinecartRidingCoordinate == null) {
                  this.startMinecartRidingCoordinate = new BlockPos(this);
               } else if (this.startMinecartRidingCoordinate
                     .distanceSq(MathHelper.floor_double(this.s), MathHelper.floor_double(this.t), MathHelper.floor_double(this.u))
                  >= 1000000.0) {
                  this.triggerAchievement(AchievementList.onARail);
               }
            } else if (this.m instanceof EntityBoat) {
               this.addStat(StatList.distanceByBoatStat, var7);
            } else if (this.m instanceof EntityPig) {
               this.addStat(StatList.distanceByPigStat, var7);
            } else if (this.m instanceof EntityHorse) {
               this.addStat(StatList.distanceByHorseStat, var7);
            }
         }
      }
   }

   public void addMovementStat(double var1, double var3, double var5) {
      if (this.m == null) {
         if (this.a(Material.water)) {
            int var7 = Math.round(MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
            if (var7 > 0) {
               this.addStat(StatList.distanceDoveStat, var7);
               this.addExhaustion(0.015F * var7 * 0.01F);
            }
         } else if (this.V()) {
            int var8 = Math.round(MathHelper.sqrt_double(var1 * var1 + var5 * var5) * 100.0F);
            if (var8 > 0) {
               this.addStat(StatList.distanceSwumStat, var8);
               this.addExhaustion(0.015F * var8 * 0.01F);
            }
         } else if (this.n_()) {
            if (var3 > 0.0) {
               this.addStat(StatList.distanceClimbedStat, (int)Math.round(var3 * 100.0));
            }
         } else if (this.C) {
            int var9 = Math.round(MathHelper.sqrt_double(var1 * var1 + var5 * var5) * 100.0F);
            if (var9 > 0) {
               this.addStat(StatList.distanceWalkedStat, var9);
               if (this.isSprinting()) {
                  this.addStat(StatList.distanceSprintedStat, var9);
                  this.addExhaustion(0.099999994F * var9 * 0.01F);
               } else {
                  if (this.isSneaking()) {
                     this.addStat(StatList.distanceCrouchedStat, var9);
                  }

                  this.addExhaustion(0.01F * var9 * 0.01F);
               }
            }
         } else {
            int var10 = Math.round(MathHelper.sqrt_double(var1 * var1 + var5 * var5) * 100.0F);
            if (var10 > 25) {
               this.addStat(StatList.distanceFlownStat, var10);
            }
         }
      }
   }

   @Override
   public String getSwimSound() {
      return "game.player.swim";
   }

   public abstract boolean isSpectator();

   public ItemStack getItemInUse() {
      return this.itemInUse;
   }

   public EntityItem dropOneItem(boolean var1) {
      return this.dropItem(
         this.bi.decrStackSize(this.bi.currentItem, var1 && this.bi.getCurrentItem() != null ? this.bi.getCurrentItem().stackSize : 1), false, true
      );
   }

   public ClientResourceManager method_00329() {
      return this.recoveredField2489;
   }

   public boolean interactWith(Entity var1) {
      if (this.isSpectator()) {
         if (var1 instanceof IInventory) {
            this.displayGUIChest((IInventory)var1);
         }

         return false;
      } else {
         ItemStack var2 = this.getCurrentEquippedItem();
         ItemStack var3 = var2 != null ? var2.copy() : null;
         if (!var1.a_(this)) {
            if (var2 != null && var1 instanceof EntityLivingBase) {
               if (this.bA.isCreativeMode) {
                  var2 = var3;
               }

               if (var2.interactWithEntity(this, (EntityLivingBase)var1)) {
                  if (var2.stackSize <= 0 && !this.bA.isCreativeMode) {
                     this.ca();
                  }

                  return true;
               }
            }

            return false;
         } else {
            if (var2 != null && var2 == this.getCurrentEquippedItem()) {
               if (var2.stackSize <= 0 && !this.bA.isCreativeMode) {
                  this.ca();
               } else if (var2.stackSize < var3.stackSize && this.bA.isCreativeMode) {
                  var2.stackSize = var3.stackSize;
               }
            }

            return true;
         }
      }
   }

   public float getArmorVisibility() {
      int var1 = 0;

      for (ItemStack var5 : this.bi.armorInventory) {
         if (var5 != null) {
            var1++;
         }
      }

      return (float)var1 / this.bi.armorInventory.length;
   }

   public int getSleepTimer() {
      return this.sleepTimer;
   }

   public void clonePlayer(EntityPlayer var1, boolean var2) {
      if (var2) {
         this.bi.copyInventory(var1.bi);
         this.setHealth(var1.getHealth());
         this.foodStats = var1.foodStats;
         this.bB = var1.bB;
         this.bC = var1.bC;
         this.bD = var1.bD;
         this.setScore(var1.getScore());
         this.an = var1.an;
         this.ao = var1.ao;
         this.ap = var1.ap;
      } else if (this.o.Q().getBoolean("keepInventory")) {
         this.bi.copyInventory(var1.bi);
         this.bB = var1.bB;
         this.bC = var1.bC;
         this.bD = var1.bD;
         this.setScore(var1.getScore());
      }

      this.xpSeed = var1.xpSeed;
      this.theInventoryEnderChest = var1.theInventoryEnderChest;
      this.H().updateObject(10, var1.H().getWatchableObjectByte(10));
   }

   @Override
   public int getPortalCooldown() {
      return 10;
   }

   public int getScore() {
      return this.ac.getWatchableObjectInt(18);
   }

   @Override
   public boolean isEntityInsideOpaqueBlock() {
      return !this.bw && super.isEntityInsideOpaqueBlock();
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else if (this.bA.disableDamage && !var1.canHarmInCreative()) {
         return false;
      } else {
         this.aQ = 0;
         if (this.getHealth() <= 0.0F) {
            return false;
         } else {
            if (this.bJ() && !this.o.D) {
               this.wakeUpPlayer(true, true, false);
            }

            if (var1.isDifficultyScaled()) {
               if (this.o.getDifficulty() == EnumDifficulty.PEACEFUL) {
                  var2 = 0.0F;
               }

               if (this.o.getDifficulty() == EnumDifficulty.EASY) {
                  var2 = var2 / 2.0F + 1.0F;
               }

               if (this.o.getDifficulty() == EnumDifficulty.HARD) {
                  var2 = var2 * 3.0F / 2.0F;
               }
            }

            if (var2 == 0.0F) {
               return false;
            } else {
               Entity var3 = var1.getEntity();
               if (var3 instanceof EntityArrow && ((EntityArrow)var3).shootingEntity != null) {
                  var3 = ((EntityArrow)var3).shootingEntity;
               }

               return super.attackEntityFrom(var1, var2);
            }
         }
      }
   }

   public boolean canPlayerEdit(BlockPos var1, EnumFacing var2, ItemStack var3) {
      if (this.bA.allowEdit) {
         return true;
      } else if (var3 == null) {
         return false;
      } else {
         BlockPos var4 = var1.a(var2.getOpposite());
         Block var5 = this.o.getBlockState(var4).getBlock();
         return var3.canPlaceOn(var5) || var3.canEditBlocks();
      }
   }

   @Override
   public int getExperiencePoints(EntityPlayer var1) {
      if (this.o.Q().getBoolean("keepInventory")) {
         return 0;
      } else {
         int var2 = this.bB * 7;
         return var2 > 100 ? 100 : var2;
      }
   }

   @Override
   public void fall(float var1, float var2) {
      if (!this.bA.allowFlying) {
         if (var1 >= 2.0F) {
            this.addStat(StatList.distanceFallenStat, (int)Math.round(var1 * 100.0));
         }

         super.fall(var1, var2);
      }
   }

   public boolean isPlayerFullyAsleep() {
      return this.bw && this.sleepTimer >= 100;
   }

   @Override
   public void updateRidden() {
      if (!this.o.D && this.isSneaking()) {
         this.mountEntity((Entity)null);
         this.setSneaking(false);
      } else {
         double var1 = this.s;
         double var3 = this.t;
         double var5 = this.u;
         float var7 = this.y;
         float var8 = this.z;
         super.updateRidden();
         this.prevCameraYaw = this.cameraYaw;
         this.cameraYaw = 0.0F;
         this.addMountedMovementStat(this.s - var1, this.t - var3, this.u - var5);
         if (this.m instanceof EntityPig) {
            this.z = var8;
            this.y = var7;
            this.aI = ((EntityPig)this.m).aI;
         }
      }
   }

   public EntityItem dropPlayerItemWithRandomChoice(ItemStack var1, boolean var2) {
      return this.dropItem(var1, false, false);
   }

   public void sendPlayerAbilities() {
   }

   @Override
   public IChatComponent getDisplayName() {
      ChatComponentText var1 = new ChatComponentText(ScorePlayerTeam.formatPlayerName(this.getTeam(), this.z_()));
      var1.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + this.z_() + " "));
      var1.getChatStyle().setChatHoverEvent(this.getHoverEvent());
      var1.getChatStyle().setInsertion(this.z_());
      return var1;
   }

   public float getToolDigEfficiency(Block var1) {
      float var2 = this.bi.getStrVsBlock(var1);
      if (var2 > 1.0F) {
         int var3 = EnchantmentHelper.getEfficiencyModifier(this);
         ItemStack var4 = this.bi.getCurrentItem();
         if (var3 > 0 && var4 != null) {
            var2 += var3 * var3 + 1;
         }
      }

      if (this.isPotionActive(Potion.digSpeed)) {
         var2 *= 1.0F + (this.getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
      }

      if (this.isPotionActive(Potion.digSlowdown)) {
         float var5 = 1.0F;
         switch (this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) {
            case 0:
               var5 = 0.3F;
               break;
            case 1:
               var5 = 0.09F;
               break;
            case 2:
               var5 = 0.0027F;
               break;
            case 3:
            default:
               var5 = 8.1E-4F;
         }

         var2 *= var5;
      }

      if (this.a(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(this)) {
         var2 /= 5.0F;
      }

      if (!this.C) {
         var2 /= 5.0F;
      }

      return var2;
   }

   public boolean isSpawnForced() {
      return this.spawnForced;
   }

   @Override
   public void onDeath(DamageSource var1) {
      super.onDeath(var1);
      this.setSize(0.2F, 0.2F);
      this.b(this.s, this.t, this.u);
      this.w = 0.1F;
      if (this.z_().equals("Notch")) {
         this.dropItem(new ItemStack(Items.apple, 1), true, false);
      }

      if (!this.o.Q().getBoolean("keepInventory")) {
         this.bi.dropAllItems();
      }

      if (var1 != null) {
         this.v = -MathHelper.cos((this.aw + this.y) * (float) Math.PI / 180.0F) * 0.1F;
         this.x = -MathHelper.sin((this.aw + this.y) * (float) Math.PI / 180.0F) * 0.1F;
      } else {
         this.v = this.x = 0.0;
      }

      this.triggerAchievement(StatList.deathsStat);
      this.func_175145_a(StatList.timeSinceDeathStat);
   }

   public boolean isUsingItem() {
      return this.itemInUse != null;
   }

   public EntityItem dropItem(ItemStack var1, boolean var2, boolean var3) {
      if (var1 == null) {
         return null;
      } else if (var1.stackSize == 0) {
         return null;
      } else {
         double var4 = this.t - 0.3F + this.getEyeHeight();
         EntityItem var6 = new EntityItem(this.o, this.s, var4, this.u, var1);
         var6.setPickupDelay(40);
         if (var3) {
            var6.setThrower(this.z_());
         }

         if (var2) {
            float var7 = this.V.nextFloat() * 0.5F;
            float var8 = this.V.nextFloat() * (float) Math.PI * 2.0F;
            var6.v = -MathHelper.sin(var8) * var7;
            var6.x = MathHelper.cos(var8) * var7;
            var6.w = 0.2F;
         } else {
            float var9 = 0.3F;
            var6.v = -MathHelper.sin(this.y / 180.0F * (float) Math.PI) * MathHelper.cos(this.z / 180.0F * (float) Math.PI) * var9;
            var6.x = MathHelper.cos(this.y / 180.0F * (float) Math.PI) * MathHelper.cos(this.z / 180.0F * (float) Math.PI) * var9;
            var6.w = -MathHelper.sin(this.z / 180.0F * (float) Math.PI) * var9 + 0.1F;
            float var11 = this.V.nextFloat() * (float) Math.PI * 2.0F;
            var9 = 0.02F * this.V.nextFloat();
            var6.v = var6.v + Math.cos(var11) * var9;
            var6.w = var6.w + (this.V.nextFloat() - this.V.nextFloat()) * 0.1F;
            var6.x = var6.x + Math.sin(var11) * var9;
         }

         this.joinEntityItemWithWorld(var6);
         if (var3) {
            this.triggerAchievement(StatList.dropStat);
         }

         return var6;
      }
   }

   public boolean shouldHeal() {
      return this.getHealth() > 0.0F && this.getHealth() < this.getMaxHealth();
   }

   @Override
   public String getSplashSound() {
      return "game.player.swim.splash";
   }

   public void addExperienceLevel(int var1) {
      this.bB += var1;
      if (this.bB < 0) {
         this.bB = 0;
         this.bD = 0.0F;
         this.bC = 0;
      }

      if (var1 > 0 && this.bB % 5 == 0 && this.lastXPSound < this.W - 100.0F) {
         float var2 = this.bB > 30 ? 1.0F : this.bB / 30.0F;
         this.o.a(this, "random.levelup", var2 * 0.75F, 1.0F);
         this.lastXPSound = this.W;
      }
   }

   public void ca() {
      this.bi.setInventorySlotContents(this.bi.currentItem, (ItemStack)null);
   }

   public void stopUsingItem() {
      if (this.itemInUse != null) {
         this.itemInUse.onPlayerStoppedUsing(this.o, this, this.itemInUseCount);
      }

      this.clearItemInUse();
   }

   @Override
   public int getMaxInPortalTime() {
      return this.bA.disableDamage ? 0 : 80;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.aq = getUUID(this.gameProfile);
      NBTTagList var2 = var1.getTagList("Inventory", 10);
      this.bi.readFromNBT(var2);
      this.bi.currentItem = var1.getInteger("SelectedItemSlot");
      this.bw = var1.getBoolean("Sleeping");
      this.sleepTimer = var1.getShort("SleepTimer");
      this.bD = var1.getFloat("XpP");
      this.bB = var1.getInteger("XpLevel");
      this.bC = var1.getInteger("XpTotal");
      this.xpSeed = var1.getInteger("XpSeed");
      if (this.xpSeed == 0) {
         this.xpSeed = this.V.nextInt();
      }

      this.setScore(var1.getInteger("Score"));
      if (this.bw) {
         this.playerLocation = new BlockPos(this);
         this.wakeUpPlayer(true, true, false);
      }

      if (var1.hasKey("SpawnX", 99) && var1.hasKey("SpawnY", 99) && var1.hasKey("SpawnZ", 99)) {
         this.spawnChunk = new BlockPos(var1.getInteger("SpawnX"), var1.getInteger("SpawnY"), var1.getInteger("SpawnZ"));
         this.spawnForced = var1.getBoolean("SpawnForced");
      }

      this.foodStats.readNBT(var1);
      this.bA.readCapabilitiesFromNBT(var1);
      if (var1.hasKey("EnderItems", 9)) {
         NBTTagList var3 = var1.getTagList("EnderItems", 10);
         this.theInventoryEnderChest.loadInventoryFromNBT(var3);
      }
   }

   public FoodStats getFoodStats() {
      return this.foodStats;
   }

   public void addStat(StatBase var1, int var2) {
   }

   @Override
   public void setAbsorptionAmount(float var1) {
      if (var1 < 0.0F) {
         var1 = 0.0F;
      }

      this.H().updateObject(17, var1);
   }

   @Override
   public void onKillEntity(EntityLivingBase var1) {
      if (var1 instanceof IMob) {
         this.triggerAchievement(AchievementList.killEnemy);
      }

      EntityList.EntityEggInfo var2 = EntityList.entityEggs.get(EntityList.getEntityID(var1));
      if (var2 != null) {
         this.triggerAchievement(var2.field_151512_d);
      }
   }

   public void setReducedDebug(boolean var1) {
      this.hasReducedDebug = var1;
   }

   public void respawnPlayer() {
   }

   public boolean canHarvestBlock(Block var1) {
      return this.bi.canHeldItemHarvest(var1);
   }

   @Override
   public void setDead() {
      super.setDead();
      this.bj.onContainerClosed(this);
      if (this.bk != null) {
         this.bk.onContainerClosed(this);
      }
   }

   @Override
   public void jump() {
      super.jump();
      this.triggerAchievement(StatList.jumpStat);
      if (this.isSprinting()) {
         this.addExhaustion(0.8F);
      } else {
         this.addExhaustion(0.2F);
      }
   }

   public void onItemUseFinish() {
      if (this.itemInUse != null) {
         this.updateItemUse(this.itemInUse, 16);
         int var1 = this.itemInUse.stackSize;
         ItemStack var2 = this.itemInUse.onItemUseFinish(this.o, this);
         if (var2 != this.itemInUse || var2 != null && var2.stackSize != var1) {
            this.bi.mainInventory[this.bi.currentItem] = var2;
            if (var2.stackSize == 0) {
               this.bi.mainInventory[this.bi.currentItem] = null;
            }
         }

         this.clearItemInUse();
      }
   }

   public void wakeUpPlayer(boolean var1, boolean var2, boolean var3) {
      this.setSize(0.6F, 1.8F);
      IBlockState var4 = this.o.getBlockState(this.playerLocation);
      if (this.playerLocation != null && var4.getBlock() == Blocks.bed) {
         this.o.a(this.playerLocation, var4.withProperty(BlockBed.OCCUPIED, false), 4);
         BlockPos var5 = BlockBed.getSafeExitLocation(this.o, this.playerLocation, 0);
         if (var5 == null) {
            var5 = this.playerLocation.up();
         }

         this.b(var5.getX() + 0.5F, var5.getY() + 0.1F, var5.getZ() + 0.5F);
      }

      this.bw = false;
      if (!this.o.D && var2) {
         this.o.updateAllPlayersSleepingFlag();
      }

      this.sleepTimer = var1 ? 0 : 100;
      if (var3) {
         this.setSpawnPoint(this.playerLocation, false);
      }
   }

   public int getItemInUseCount() {
      return this.itemInUseCount;
   }

   public boolean canAttackPlayer(EntityPlayer var1) {
      Team var2 = this.getTeam();
      Team var3 = var1.getTeam();
      return var2 == null ? true : (!var2.isSameTeam(var3) ? true : var2.getAllowFriendlyFire());
   }

   public int method_00420() {
      return this.isUsingItem() ? this.itemInUse.getMaxItemUseDuration() - this.itemInUseCount : 0;
   }

   public void clearItemInUse() {
      this.itemInUse = null;
      this.itemInUseCount = 0;
      if (!this.o.D) {
         this.setEating(false);
      }
   }

   public void func_175145_a(StatBase var1) {
   }

   public static BlockPos getBedSpawnLocation(World var0, BlockPos var1, boolean var2) {
      Block var3 = var0.getBlockState(var1).getBlock();
      if (var3 != Blocks.bed) {
         if (!var2) {
            return null;
         } else {
            boolean var4 = var3.canSpawnInBlock();
            boolean var5 = var0.getBlockState(var1.up()).getBlock().canSpawnInBlock();
            return var4 && var5 ? var1 : null;
         }
      } else {
         return BlockBed.getSafeExitLocation(var0, var1, 0);
      }
   }

   public void displayGui(IInteractionObject var1) {
   }

   public static UUID getOfflineUUID(String var0) {
      return UUID.nameUUIDFromBytes(("OfflinePlayer:" + var0).getBytes(Charsets.UTF_8));
   }

   @Override
   public String z_() {
      return this.gameProfile.getName();
   }

   public void addExperience(int var1) {
      this.addScore(var1);
      int var2 = Integer.MAX_VALUE - this.bC;
      if (var1 > var2) {
         var1 = var2;
      }

      this.bD = this.bD + (float)var1 / this.xpBarCap();

      for (this.bC += var1; this.bD >= 1.0F; this.bD = this.bD / this.xpBarCap()) {
         this.bD = (this.bD - 1.0F) * this.xpBarCap();
         this.addExperienceLevel(1);
      }
   }

   public ItemStack getCurrentEquippedItem() {
      return this.bi.getCurrentItem();
   }

   public Collection<ScoreObjective> func_175137_e(Entity var1) {
      ScorePlayerTeam var2 = this.getWorldScoreboard().getPlayersTeam(this.z_());
      if (var2 != null) {
         int var3 = var2.getChatFormat().getColorIndex();
         if (var3 >= 0 && var3 < IScoreObjectiveCriteria.field_178793_i.length) {
            for (ScoreObjective var5 : this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.field_178793_i[var3])) {
               Score var6 = this.getWorldScoreboard().getValueFromObjective(var1.z_(), var5);
               var6.func_96648_a();
            }
         }
      }

      ScorePlayerTeam var7 = this.getWorldScoreboard().getPlayersTeam(var1.z_());
      if (var7 != null) {
         int var8 = var7.getChatFormat().getColorIndex();
         if (var8 >= 0 && var8 < IScoreObjectiveCriteria.field_178792_h.length) {
            return this.getWorldScoreboard().getObjectivesFromCriteria(IScoreObjectiveCriteria.field_178792_h[var8]);
         }
      }

      return Lists.newArrayList();
   }

   @Override
   public boolean aO() {
      return true;
   }

   @Override
   public boolean f(EntityPlayer var1) {
      if (!this.isInvisible()) {
         return false;
      } else if (var1.isSpectator()) {
         return false;
      } else {
         Team var2 = this.getTeam();
         return var2 == null || var1 == null || var1.getTeam() != var2 || !var2.getSeeFriendlyInvisiblesEnabled();
      }
   }

   public void func_175139_a(EnumFacing var1) {
      this.renderOffsetX = 0.0F;
      this.renderOffsetZ = 0.0F;
      switch (var1) {
         case SOUTH:
            this.renderOffsetZ = -1.8F;
            break;
         case NORTH:
            this.renderOffsetZ = 1.8F;
            break;
         case WEST:
            this.renderOffsetX = 1.8F;
            break;
         case EAST:
            this.renderOffsetX = -1.8F;
      }
   }

   @Override
   public Team getTeam() {
      return this.getWorldScoreboard().getPlayersTeam(this.z_());
   }

   @Override
   public void updateEntityActionState() {
      super.updateEntityActionState();
      this.updateArmSwingProgress();
      this.aK = this.y;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1F);
   }

   @Override
   public boolean isPlayer() {
      return true;
   }

   public void onCriticalHit(Entity var1) {
   }

   @Override
   public void I() {
      this.setSize(0.6F, 1.8F);
      super.I();
      this.setHealth(this.getMaxHealth());
      this.ax = 0;
   }

   @Override
   public void onLivingUpdate() {
      if (this.flyToggleTimer > 0) {
         this.flyToggleTimer--;
      }

      if (this.o.getDifficulty() == EnumDifficulty.PEACEFUL && this.o.Q().getBoolean("naturalRegeneration")) {
         if (this.getHealth() < this.getMaxHealth() && this.W % 20 == 0) {
            this.heal(1.0F);
         }

         if (this.foodStats.needFood() && this.W % 10 == 0) {
            this.foodStats.setFoodLevel(this.foodStats.getFoodLevel() + 1);
         }
      }

      this.bi.decrementAnimations();
      this.prevCameraYaw = this.cameraYaw;
      super.onLivingUpdate();
      IAttributeInstance var1 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      if (!this.o.D) {
         var1.setBaseValue(this.bA.getWalkSpeed());
      }

      this.aM = this.recoveredField2490;
      if (this.isSprinting()) {
         this.aM = (float)(this.aM + this.recoveredField2490 * 0.3);
      }

      this.setAIMoveSpeed((float)var1.getAttributeValue());
      float var2 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
      float var3 = (float)(Math.atan(-this.w * 0.2F) * 15.0);
      if (var2 > 0.1F) {
         var2 = 0.1F;
      }

      if (!this.C || this.getHealth() <= 0.0F) {
         var2 = 0.0F;
      }

      if (this.C || this.getHealth() <= 0.0F) {
         var3 = 0.0F;
      }

      this.cameraYaw = this.cameraYaw + (var2 - this.cameraYaw) * 0.4F;
      this.aF = this.aF + (var3 - this.aF) * 0.8F;
      if (this.getHealth() > 0.0F && !this.isSpectator()) {
         Object var4 = null;
         if (this.m != null && !this.m.I) {
            var4 = this.getEntityBoundingBox().union(this.m.getEntityBoundingBox()).expand(1.0, 0.0, 1.0);
         } else {
            var4 = this.getEntityBoundingBox().expand(1.0, 0.5, 1.0);
         }

         List var5 = this.o.getEntitiesWithinAABBExcludingEntity(this, (AxisAlignedBB)var4);

         for (int var6 = 0; var6 < var5.size(); var6++) {
            Entity var7 = (Entity)var5.get(var6);
            if (!var7.I) {
               this.collideWithPlayer(var7);
            }
         }
      }
   }

   @Override
   public ItemStack getCurrentArmor(int var1) {
      return this.bi.armorItemInSlot(var1);
   }

   @Override
   public String getHurtSound() {
      return "game.player.hurt";
   }

   public int xpBarCap() {
      return this.bB >= 30 ? 112 + (this.bB - 30) * 9 : (this.bB >= 15 ? 37 + (this.bB - 15) * 5 : 7 + this.bB * 2);
   }

   public void displayGUIChest(IInventory var1) {
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setTag("Inventory", this.bi.writeToNBT(new NBTTagList()));
      var1.setInteger("SelectedItemSlot", this.bi.currentItem);
      var1.setBoolean("Sleeping", this.bw);
      var1.setShort("SleepTimer", (short)this.sleepTimer);
      var1.setFloat("XpP", this.bD);
      var1.setInteger("XpLevel", this.bB);
      var1.setInteger("XpTotal", this.bC);
      var1.setInteger("XpSeed", this.xpSeed);
      var1.setInteger("Score", this.getScore());
      if (this.spawnChunk != null) {
         var1.setInteger("SpawnX", this.spawnChunk.getX());
         var1.setInteger("SpawnY", this.spawnChunk.getY());
         var1.setInteger("SpawnZ", this.spawnChunk.getZ());
         var1.setBoolean("SpawnForced", this.spawnForced);
      }

      this.foodStats.writeNBT(var1);
      this.bA.writeCapabilitiesToNBT(var1);
      var1.setTag("EnderItems", this.theInventoryEnderChest.saveInventoryToNBT());
      ItemStack var2 = this.bi.getCurrentItem();
      if (var2 != null && var2.getItem() != null) {
         var1.setTag("SelectedItem", var2.writeToNBT(new NBTTagCompound()));
      }
   }

   @Override
   public void playSound(String var1, float var2, float var3) {
      this.o.playSoundToNearExcept(this, var1, var2, var3);
   }

   @Override
   public boolean isPushedByWater() {
      return !this.bA.isFlying;
   }

   public void method_00451(ClientResourceManager var1) {
      this.recoveredField2488 = var1;
   }

   public void setGameType(WorldSettings.GameType var1) {
   }

   @Override
   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
      this.bi.armorInventory[var1] = var2;
   }

   public void openEditSign(TileEntitySign var1) {
   }

   public void triggerAchievement(StatBase var1) {
      this.addStat(var1, 1);
   }

   @Override
   public ItemStack getHeldItem() {
      return this.bi.getCurrentItem();
   }

   public void addScore(int var1) {
      int var2 = this.getScore();
      this.ac.updateObject(18, var2 + var1);
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, (byte)0);
      this.ac.addObject(17, 0.0F);
      this.ac.addObject(18, 0);
      this.ac.addObject(10, (byte)0);
   }

   public EntityPlayer(World var1, GameProfile var2) {
      super(var1);
      this.theInventoryEnderChest = new InventoryEnderChest();
      this.foodStats = new FoodStats();
      this.bA = new PlayerCapabilities();
      this.recoveredField2487 = 0.1F;
      this.recoveredField2490 = 0.02F;
      this.hasReducedDebug = false;
      this.recoveredField2488 = null;
      this.recoveredField2489 = null;
      this.aq = getUUID(var2);
      this.gameProfile = var2;
      this.bj = new ContainerPlayer(this.bi, !var1.D, this);
      this.bk = this.bj;
      BlockPos var3 = var1.M();
      this.a_(var3.getX() + 0.5, var3.getY() + 1, var3.getZ() + 0.5, 0.0F, 0.0F);
      this.recoveredField180 = 180.0F;
      this.fireResistance = 20;
   }

   @Override
   public float bI() {
      return (float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
   }

   @Override
   public void onUpdate() {
      this.T = this.isSpectator();
      if (this.isSpectator()) {
         this.C = false;
      }

      if (this.itemInUse != null) {
         ItemStack var1 = this.bi.getCurrentItem();
         if (var1 == this.itemInUse) {
            if (this.itemInUseCount <= 25 && this.itemInUseCount % 4 == 0) {
               this.updateItemUse(var1, 5);
            }

            if (--this.itemInUseCount == 0 && !this.o.D) {
               this.onItemUseFinish();
            }
         } else {
            this.clearItemInUse();
         }
      }

      if (this.xpCooldown > 0) {
         this.xpCooldown--;
      }

      if (this.bJ()) {
         this.sleepTimer++;
         if (this.sleepTimer > 100) {
            this.sleepTimer = 100;
         }

         if (!this.o.D) {
            if (!this.isInBed()) {
               this.wakeUpPlayer(true, true, false);
            } else if (this.o.isDaytime()) {
               this.wakeUpPlayer(false, true, true);
            }
         }
      } else if (this.sleepTimer > 0) {
         this.sleepTimer++;
         if (this.sleepTimer >= 110) {
            this.sleepTimer = 0;
         }
      }

      super.onUpdate();
      if (!this.o.D && this.bk != null && !this.bk.canInteractWith(this)) {
         this.closeScreen();
         this.bk = this.bj;
      }

      if (this.isBurning() && this.bA.disableDamage) {
         this.extinguish();
      }

      this.prevChasingPosX = this.chasingPosX;
      this.prevChasingPosY = this.chasingPosY;
      this.prevChasingPosZ = this.chasingPosZ;
      double var14 = this.s - this.chasingPosX;
      double var3 = this.t - this.chasingPosY;
      double var5 = this.u - this.chasingPosZ;
      double var7 = 10.0;
      if (var14 > var7) {
         this.prevChasingPosX = this.chasingPosX = this.s;
      }

      if (var5 > var7) {
         this.prevChasingPosZ = this.chasingPosZ = this.u;
      }

      if (var3 > var7) {
         this.prevChasingPosY = this.chasingPosY = this.t;
      }

      if (var14 < -var7) {
         this.prevChasingPosX = this.chasingPosX = this.s;
      }

      if (var5 < -var7) {
         this.prevChasingPosZ = this.chasingPosZ = this.u;
      }

      if (var3 < -var7) {
         this.prevChasingPosY = this.chasingPosY = this.t;
      }

      this.chasingPosX += var14 * 0.25;
      this.chasingPosZ += var5 * 0.25;
      this.chasingPosY += var3 * 0.25;
      if (this.m == null) {
         this.startMinecartRidingCoordinate = null;
      }

      if (!this.o.D) {
         this.foodStats.onUpdate(this);
         this.triggerAchievement(StatList.minutesPlayedStat);
         if (this.isEntityAlive()) {
            this.triggerAchievement(StatList.timeSinceDeathStat);
         }
      }

      int var9 = 29999999;
      double var10 = MathHelper.clamp_double(this.s, -2.9999999E7, 2.9999999E7);
      double var12 = MathHelper.clamp_double(this.u, -2.9999999E7, 2.9999999E7);
      if (var10 != this.s || var12 != this.u) {
         this.b(var10, this.t, var12);
      }
   }

   @Override
   public boolean bJ() {
      return this.bw;
   }

   public void removeExperienceLevel(int var1) {
      this.bB -= var1;
      if (this.bB < 0) {
         this.bB = 0;
         this.bD = 0.0F;
         this.bC = 0;
      }

      this.xpSeed = this.V.nextInt();
   }

   public BlockPos getBedLocation() {
      return this.spawnChunk;
   }

   public float getBedOrientationInDegrees() {
      if (this.playerLocation != null) {
         EnumFacing var1 = this.o.getBlockState(this.playerLocation).getValue(BlockDirectional.O);
         switch (var1) {
            case SOUTH:
               return 90.0F;
            case NORTH:
               return 270.0F;
            case WEST:
               return 0.0F;
            case EAST:
               return 180.0F;
         }
      }

      return 0.0F;
   }

   @Override
   public void moveEntityWithHeading(float var1, float var2) {
      double var3 = this.s;
      double var5 = this.t;
      double var7 = this.u;
      if (this.bA.isFlying && this.m == null) {
         double var9 = this.w;
         float var11 = this.aM;
         this.aM = this.bA.getFlySpeed() * (this.isSprinting() ? 2 : 1);
         super.moveEntityWithHeading(var1, var2);
         this.w = var9 * 0.6;
         this.aM = var11;
      } else {
         super.moveEntityWithHeading(var1, var2);
      }

      this.addMovementStat(this.s - var3, this.t - var5, this.u - var7);
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public void updateItemUse(ItemStack var1, int var2) {
      if (var1.getItemUseAction() == EnumAction.DRINK) {
         this.playSound("random.drink", 0.5F, this.o.s.nextFloat() * 0.1F + 0.9F);
      }

      if (var1.getItemUseAction() == EnumAction.EAT) {
         for (int var3 = 0; var3 < var2; var3++) {
            Vec3 var4 = new Vec3((this.V.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
            var4 = var4.rotatePitch(-this.z * (float) Math.PI / 180.0F);
            var4 = var4.rotateYaw(-this.y * (float) Math.PI / 180.0F);
            double var5 = -this.V.nextFloat() * 0.6 - 0.3;
            Vec3 var7 = new Vec3((this.V.nextFloat() - 0.5) * 0.3, var5, 0.6);
            var7 = var7.rotatePitch(-this.z * (float) Math.PI / 180.0F);
            var7 = var7.rotateYaw(-this.y * (float) Math.PI / 180.0F);
            var7 = var7.addVector(this.s, this.t + this.getEyeHeight(), this.u);
            if (var1.getHasSubtypes()) {
               this.o
                  .spawnParticle(
                     EnumParticleTypes.ITEM_CRACK,
                     var7.xCoord,
                     var7.yCoord,
                     var7.zCoord,
                     var4.xCoord,
                     var4.yCoord + 0.05,
                     var4.zCoord,
                     Item.getIdFromItem(var1.getItem()),
                     var1.getMetadata()
                  );
            } else {
               this.o
                  .spawnParticle(
                     EnumParticleTypes.ITEM_CRACK,
                     var7.xCoord,
                     var7.yCoord,
                     var7.zCoord,
                     var4.xCoord,
                     var4.yCoord + 0.05,
                     var4.zCoord,
                     Item.getIdFromItem(var1.getItem())
                  );
            }
         }

         this.playSound("random.eat", 0.5F + 0.5F * this.V.nextInt(2), (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
      }
   }

   public boolean isUser() {
      return false;
   }

   public boolean canOpen(LockCode var1) {
      if (var1.isEmpty()) {
         return true;
      } else {
         ItemStack var2 = this.getCurrentEquippedItem();
         return var2 != null && var2.hasDisplayName() ? var2.getDisplayName().equals(var1.getLock()) : false;
      }
   }

   public boolean isBlocking() {
      return this.isUsingItem() && this.itemInUse.getItem().getItemUseAction(this.itemInUse) == EnumAction.BLOCK;
   }

   public boolean cn() {
      return this.bA.allowEdit;
   }

   @Override
   public ItemStack getEquipmentInSlot(int var1) {
      return var1 == 0 ? this.bi.getCurrentItem() : this.bi.armorInventory[var1 - 1];
   }

   public void displayVillagerTradeGui(IMerchant var1) {
   }

   @Override
   public boolean C_() {
      return MinecraftServer.getServer().worldServers[0].Q().getBoolean("sendCommandFeedback");
   }

   public static UUID getUUID(GameProfile var0) {
      UUID var1 = var0.getId();
      if (var1 == null) {
         var1 = getOfflineUUID(var0.getName());
      }

      return var1;
   }

   public void closeScreen() {
      this.bk = this.bj;
   }

   public void onEnchantmentCritical(Entity var1) {
   }

   public InventoryEnderChest getInventoryEnderChest() {
      return this.theInventoryEnderChest;
   }

   public void displayGUIHorse(EntityHorse var1, IInventory var2) {
   }

   public boolean isWearing(EnumPlayerModelParts var1) {
      return (this.H().getWatchableObjectByte(10) & var1.getPartMask()) == var1.getPartMask();
   }

   public boolean method_00367() {
      return this.recoveredField2489 != null;
   }

   public void displayGUIBook(ItemStack var1) {
   }

   public void openEditCommandBlock(CommandBlockLogic var1) {
   }

   @Override
   public void damageEntity(DamageSource var1, float var2) {
      if (!this.isEntityInvulnerable(var1)) {
         if (!var1.isUnblockable() && this.isBlocking() && var2 > 0.0F) {
            var2 = (1.0F + var2) * 0.5F;
         }

         var2 = this.applyArmorCalculations(var1, var2);
         var2 = this.applyPotionDamageCalculations(var1, var2);
         float var7 = Math.max(var2 - this.getAbsorptionAmount(), 0.0F);
         this.setAbsorptionAmount(this.getAbsorptionAmount() - (var2 - var7));
         if (var7 != 0.0F) {
            this.addExhaustion(var1.getHungerDamage());
            float var4 = this.getHealth();
            this.setHealth(this.getHealth() - var7);
            this.getCombatTracker().trackDamage(var1, var4, var7);
            if (var7 < 3.4028235E37F) {
               this.addStat(StatList.damageTakenStat, Math.round(var7 * 10.0F));
            }
         }
      }
   }

   @Override
   public float getAbsorptionAmount() {
      return this.H().getWatchableObjectFloat(17);
   }

   @Override
   public String getFallSoundString(int var1) {
      return var1 > 4 ? "game.player.hurt.fall.big" : "game.player.hurt.fall.small";
   }

   public boolean canEat(boolean var1) {
      return (var1 || this.foodStats.needFood()) && !this.bA.disableDamage;
   }

   @Override
   public boolean l_() {
      return !this.bA.isFlying;
   }

   public static enum EnumChatVisibility {
      FULL(0, "options.chat.visibility.full"),
      SYSTEM(1, "options.chat.visibility.system"),
      HIDDEN(2, "options.chat.visibility.hidden");
      // $VF: synthetic field
      public static EntityPlayer.EnumChatVisibility[] $VALUES = new EntityPlayer.EnumChatVisibility[]{FULL, SYSTEM, EntityPlayer.EnumChatVisibility.HIDDEN};

      public static EntityPlayer.EnumChatVisibility[] ID_LOOKUP = new EntityPlayer.EnumChatVisibility[values().length];
      public int chatVisibility;
      public String resourceKey;

      public int getChatVisibility() {
         return this.chatVisibility;
      }

      public String getResourceKey() {
         return this.resourceKey;
      }

      static {
         for (EntityPlayer.EnumChatVisibility var3 : values()) {
            ID_LOOKUP[var3.chatVisibility] = var3;
         }
      }

      public static EntityPlayer.EnumChatVisibility getEnumChatVisibility(int var0) {
         return ID_LOOKUP[var0 % ID_LOOKUP.length];
      }

      EnumChatVisibility(int var3, String var4) {
         this.chatVisibility = var3;
         this.resourceKey = var4;
      }
   }

   public static enum EnumStatus {
      OK,
      NOT_POSSIBLE_HERE,
      NOT_POSSIBLE_NOW,
      TOO_FAR_AWAY,
      OTHER_PROBLEM,
      NOT_SAFE;
   }
}
