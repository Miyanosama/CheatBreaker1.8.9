package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnderman extends EntityMob {
   public static UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
   public static AttributeModifier attackingSpeedBoostModifier = new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 0.15F, 0)
      .setSaved(false);
   public boolean isAggressive;
   public static Set<Block> carriableBlocks = Sets.newIdentityHashSet();

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      IBlockState var2 = this.getHeldBlockState();
      var1.setShort("carried", (short)Block.getIdFromBlock(var2.getBlock()));
      var1.setShort("carriedData", (short)var2.getBlock().getMetaFromState(var2));
   }

   @Override
   public void updateAITasks() {
      if (this.U()) {
         this.attackEntityFrom(DamageSource.drown, 1.0F);
      }

      if (this.isScreaming() && !this.isAggressive && this.V.nextInt(100) == 0) {
         this.setScreaming(false);
      }

      if (this.o.isDaytime()) {
         float var1 = this.a_(1.0F);
         if (var1 > 0.5F && this.o.canSeeSky(new BlockPos(this)) && this.V.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
            this.setAttackTarget((EntityLivingBase)null);
            this.setScreaming(false);
            this.isAggressive = false;
            this.teleportRandomly();
         }
      }

      super.updateAITasks();
   }

   public IBlockState getHeldBlockState() {
      return Block.getStateById(this.ac.getWatchableObjectShort(16) & '\uffff');
   }

   public boolean shouldAttackPlayer(EntityPlayer var1) {
      ItemStack var2 = var1.bi.armorInventory[3];
      if (var2 != null && var2.getItem() == Item.getItemFromBlock(Blocks.pumpkin)) {
         return false;
      } else {
         Vec3 var3 = var1.getLook(1.0F).normalize();
         Vec3 var4 = new Vec3(this.s - var1.s, this.getEntityBoundingBox().b + this.K / 2.0F - (var1.t + var1.getEyeHeight()), this.u - var1.u);
         double var5 = var4.lengthVector();
         var4 = var4.normalize();
         double var7 = var3.dotProduct(var4);
         return var7 > 1.0 - 0.025 / var5 ? var1.t(this) : false;
      }
   }

   public boolean teleportTo(double var1, double var3, double var5) {
      double var7 = this.s;
      double var9 = this.t;
      double var11 = this.u;
      this.s = var1;
      this.t = var3;
      this.u = var5;
      boolean var13 = false;
      BlockPos var14 = new BlockPos(this.s, this.t, this.u);
      if (this.o.e(var14)) {
         boolean var15 = false;

         while (!var15 && var14.getY() > 0) {
            BlockPos var16 = var14.down();
            Block var17 = this.o.getBlockState(var16).getBlock();
            if (var17.getMaterial().blocksMovement()) {
               var15 = true;
            } else {
               this.t--;
               var14 = var16;
            }
         }

         if (var15) {
            super.setPositionAndUpdate(this.s, this.t, this.u);
            if (this.o.a(this, this.getEntityBoundingBox()).isEmpty() && !this.o.isAnyLiquid(this.getEntityBoundingBox())) {
               var13 = true;
            }
         }
      }

      if (!var13) {
         this.b(var7, var9, var11);
         return false;
      } else {
         short var28 = 128;

         for (int var29 = 0; var29 < var28; var29++) {
            double var30 = var29 / (var28 - 1.0);
            float var19 = (this.V.nextFloat() - 0.5F) * 0.2F;
            float var20 = (this.V.nextFloat() - 0.5F) * 0.2F;
            float var21 = (this.V.nextFloat() - 0.5F) * 0.2F;
            double var22 = var7 + (this.s - var7) * var30 + (this.V.nextDouble() - 0.5) * this.J * 2.0;
            double var24 = var9 + (this.t - var9) * var30 + this.V.nextDouble() * this.K;
            double var26 = var11 + (this.u - var11) * var30 + (this.V.nextDouble() - 0.5) * this.J * 2.0;
            this.o.spawnParticle(EnumParticleTypes.PORTAL, var22, var24, var26, var19, var20, var21);
         }

         this.o.playSoundEffect(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
         this.playSound("mob.endermen.portal", 1.0F, 1.0F);
         return true;
      }
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, new Short((short)0));
      this.ac.addObject(17, new Byte((byte)0));
      this.ac.addObject(18, new Byte((byte)0));
   }

   @Override
   public String getLivingSound() {
      return this.isScreaming() ? "mob.endermen.scream" : "mob.endermen.idle";
   }

   @Override
   public float getEyeHeight() {
      return 2.55F;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      IBlockState var2;
      if (var1.hasKey("carried", 8)) {
         var2 = Block.getBlockFromName(var1.getString("carried")).getStateFromMeta(var1.getShort("carriedData") & '\uffff');
      } else {
         var2 = Block.getBlockById(var1.getShort("carried")).getStateFromMeta(var1.getShort("carriedData") & '\uffff');
      }

      this.setHeldBlockState(var2);
   }

   @Override
   public String getHurtSound() {
      return "mob.endermen.hit";
   }

   @Override
   public String getDeathSound() {
      return "mob.endermen.death";
   }

   public boolean teleportToEntity(Entity var1) {
      Vec3 var2 = new Vec3(this.s - var1.s, this.getEntityBoundingBox().b + this.K / 2.0F - var1.t + var1.getEyeHeight(), this.u - var1.u);
      var2 = var2.normalize();
      double var3 = 16.0;
      double var5 = this.s + (this.V.nextDouble() - 0.5) * 8.0 - var2.xCoord * var3;
      double var7 = this.t + (this.V.nextInt(16) - 8) - var2.yCoord * var3;
      double var9 = this.u + (this.V.nextDouble() - 0.5) * 8.0 - var2.zCoord * var3;
      return this.teleportTo(var5, var7, var9);
   }

   public EntityEnderman(World var1) {
      super(var1);
      this.setSize(0.6F, 2.9F);
      this.S = 1.0F;
      this.i.addTask(0, new EntityAISwimming(this));
      this.i.addTask(2, new EntityAIAttackOnCollide(this, 1.0, false));
      this.i.addTask(7, new EntityAIWander(this, 1.0));
      this.i.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.i.addTask(8, new EntityAILookIdle(this));
      this.i.addTask(10, new EntityEnderman.AIPlaceBlock(this));
      this.i.addTask(11, new EntityEnderman.AITakeBlock(this));
      this.bi.addTask(1, new EntityAIHurtByTarget(this, false));
      this.bi.addTask(2, new EntityEnderman.AIFindPlayer(this));
      this.bi.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityEndermite.class, 10, true, false, new Predicate<EntityEndermite>() {
         public boolean apply(EntityEndermite var1) {
            return var1.isSpawnedByPlayer();
         }
      }));
   }

   public void setScreaming(boolean var1) {
      this.ac.updateObject(18, (byte)(var1 ? 1 : 0));
   }

   static {
      carriableBlocks.add(Blocks.grass);
      carriableBlocks.add(Blocks.dirt);
      carriableBlocks.add(Blocks.sand);
      carriableBlocks.add(Blocks.gravel);
      carriableBlocks.add(Blocks.yellow_flower);
      carriableBlocks.add(Blocks.red_flower);
      carriableBlocks.add(Blocks.brown_mushroom);
      carriableBlocks.add(Blocks.red_mushroom);
      carriableBlocks.add(Blocks.tnt);
      carriableBlocks.add(Blocks.cactus);
      carriableBlocks.add(Blocks.clay);
      carriableBlocks.add(Blocks.pumpkin);
      carriableBlocks.add(Blocks.melon_block);
      carriableBlocks.add(Blocks.mycelium);
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      Item var3 = this.getDropItem();
      if (var3 != null) {
         int var4 = this.V.nextInt(2 + var2);

         for (int var5 = 0; var5 < var4; var5++) {
            this.dropItem(var3, 1);
         }
      }
   }

   @Override
   public Item getDropItem() {
      return Items.ender_pearl;
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         if (var1.getEntity() == null || !(var1.getEntity() instanceof EntityEndermite)) {
            if (!this.o.D) {
               this.setScreaming(true);
            }

            if (var1 instanceof EntityDamageSource && var1.getEntity() instanceof EntityPlayer) {
               if (var1.getEntity() instanceof EntityPlayerMP && ((EntityPlayerMP)var1.getEntity()).theItemInWorldManager.isCreative()) {
                  this.setScreaming(false);
               } else {
                  this.isAggressive = true;
               }
            }

            if (var1 instanceof EntityDamageSourceIndirect) {
               this.isAggressive = false;

               for (int var4 = 0; var4 < 64; var4++) {
                  if (this.teleportRandomly()) {
                     return true;
                  }
               }

               return false;
            }
         }

         boolean var3 = super.attackEntityFrom(var1, var2);
         if (var1.isUnblockable() && this.V.nextInt(10) != 0) {
            this.teleportRandomly();
         }

         return var3;
      }
   }

   public void setHeldBlockState(IBlockState var1) {
      this.ac.updateObject(16, (short)(Block.getStateId(var1) & 65535));
   }

   public boolean isScreaming() {
      return this.ac.getWatchableObjectByte(18) > 0;
   }

   @Override
   public void onLivingUpdate() {
      if (this.o.D) {
         for (int var1 = 0; var1 < 2; var1++) {
            this.o
               .spawnParticle(
                  EnumParticleTypes.PORTAL,
                  this.s + (this.V.nextDouble() - 0.5) * this.J,
                  this.t + this.V.nextDouble() * this.K - 0.25,
                  this.u + (this.V.nextDouble() - 0.5) * this.J,
                  (this.V.nextDouble() - 0.5) * 2.0,
                  -this.V.nextDouble(),
                  (this.V.nextDouble() - 0.5) * 2.0
               );
         }
      }

      this.aY = false;
      super.onLivingUpdate();
   }

   public boolean teleportRandomly() {
      double var1 = this.s + (this.V.nextDouble() - 0.5) * 64.0;
      double var3 = this.t + (this.V.nextInt(64) - 32);
      double var5 = this.u + (this.V.nextDouble() - 0.5) * 64.0;
      return this.teleportTo(var1, var3, var5);
   }

   public static class AIFindPlayer extends EntityAINearestAttackableTarget {
      public EntityEnderman enderman;
      public int field_179451_i;
      public int field_179450_h;
      public EntityPlayer player;

      public AIFindPlayer(EntityEnderman var1) {
         super(var1, EntityPlayer.class, true);
         this.enderman = var1;
      }

      @Override
      public void startExecuting() {
         this.field_179450_h = 5;
         this.field_179451_i = 0;
      }

      @Override
      public void updateTask() {
         if (this.player != null) {
            if (--this.field_179450_h <= 0) {
               this.d = this.player;
               this.player = null;
               super.startExecuting();
               this.enderman.playSound("mob.endermen.stare", 1.0F, 1.0F);
               this.enderman.setScreaming(true);
               IAttributeInstance var1 = this.enderman.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
               var1.applyModifier(EntityEnderman.attackingSpeedBoostModifier);
            }
         } else {
            if (this.d != null) {
               if (this.d instanceof EntityPlayer && this.enderman.shouldAttackPlayer((EntityPlayer)this.d)) {
                  if (this.d.h(this.enderman) < 16.0) {
                     this.enderman.teleportRandomly();
                  }

                  this.field_179451_i = 0;
               } else if (this.d.h(this.enderman) > 256.0 && this.field_179451_i++ >= 30 && this.enderman.teleportToEntity(this.d)) {
                  this.field_179451_i = 0;
               }
            }

            super.updateTask();
         }
      }

      @Override
      public void resetTask() {
         this.player = null;
         this.enderman.setScreaming(false);
         IAttributeInstance var1 = this.enderman.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         var1.removeModifier(EntityEnderman.attackingSpeedBoostModifier);
         super.resetTask();
      }

      @Override
      public boolean continueExecuting() {
         if (this.player != null) {
            if (!this.enderman.shouldAttackPlayer(this.player)) {
               return false;
            } else {
               this.enderman.isAggressive = true;
               this.enderman.a(this.player, 10.0F, 10.0F);
               return true;
            }
         } else {
            return super.continueExecuting();
         }
      }

      @Override
      public boolean shouldExecute() {
         double var1 = this.f();
         List var3 = this.e.o.getEntitiesWithinAABB(EntityPlayer.class, this.e.getEntityBoundingBox().expand(var1, 4.0, var1), this.c);
         Collections.sort(var3, this.b);
         if (var3.isEmpty()) {
            return false;
         } else {
            this.player = (EntityPlayer)var3.get(0);
            return true;
         }
      }
   }

   public static class AIPlaceBlock extends EntityAIBase {
      public EntityEnderman enderman;

      @Override
      public void updateTask() {
         Random var1 = this.enderman.getRNG();
         World var2 = this.enderman.o;
         int var3 = MathHelper.floor_double(this.enderman.s - 1.0 + var1.nextDouble() * 2.0);
         int var4 = MathHelper.floor_double(this.enderman.t + var1.nextDouble() * 2.0);
         int var5 = MathHelper.floor_double(this.enderman.u - 1.0 + var1.nextDouble() * 2.0);
         BlockPos var6 = new BlockPos(var3, var4, var5);
         Block var7 = var2.getBlockState(var6).getBlock();
         Block var8 = var2.getBlockState(var6.down()).getBlock();
         if (this.func_179474_a(var2, var6, this.enderman.getHeldBlockState().getBlock(), var7, var8)) {
            var2.a(var6, this.enderman.getHeldBlockState(), 3);
            this.enderman.setHeldBlockState(Blocks.air.getDefaultState());
         }
      }

      @Override
      public boolean shouldExecute() {
         return !this.enderman.o.Q().getBoolean("mobGriefing")
            ? false
            : (this.enderman.getHeldBlockState().getBlock().getMaterial() == Material.air ? false : this.enderman.getRNG().nextInt(2000) == 0);
      }

      public boolean func_179474_a(World var1, BlockPos var2, Block var3, Block var4, Block var5) {
         return !var3.canPlaceBlockAt(var1, var2)
            ? false
            : (var4.getMaterial() != Material.air ? false : (var5.getMaterial() == Material.air ? false : var5.isFullCube()));
      }

      public AIPlaceBlock(EntityEnderman var1) {
         this.enderman = var1;
      }
   }

   public static class AITakeBlock extends EntityAIBase {
      public EntityEnderman enderman;

      @Override
      public void updateTask() {
         Random var1 = this.enderman.getRNG();
         World var2 = this.enderman.o;
         int var3 = MathHelper.floor_double(this.enderman.s - 2.0 + var1.nextDouble() * 4.0);
         int var4 = MathHelper.floor_double(this.enderman.t + var1.nextDouble() * 3.0);
         int var5 = MathHelper.floor_double(this.enderman.u - 2.0 + var1.nextDouble() * 4.0);
         BlockPos var6 = new BlockPos(var3, var4, var5);
         IBlockState var7 = var2.getBlockState(var6);
         Block var8 = var7.getBlock();
         if (EntityEnderman.carriableBlocks.contains(var8)) {
            this.enderman.setHeldBlockState(var7);
            var2.setBlockState(var6, Blocks.air.getDefaultState());
         }
      }

      @Override
      public boolean shouldExecute() {
         return !this.enderman.o.Q().getBoolean("mobGriefing")
            ? false
            : (this.enderman.getHeldBlockState().getBlock().getMaterial() != Material.air ? false : this.enderman.getRNG().nextInt(20) == 0);
      }

      public AITakeBlock(EntityEnderman var1) {
         this.enderman = var1;
      }
   }
}
