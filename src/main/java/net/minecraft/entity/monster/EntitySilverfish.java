package net.minecraft.entity.monster;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntitySilverfish extends EntityMob {
   public EntitySilverfish.AISummonSilverfish summonSilverfish;

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         if (var1 instanceof EntityDamageSource || var1 == DamageSource.magic) {
            this.summonSilverfish.func_179462_f();
         }

         return super.attackEntityFrom(var1, var2);
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
   }

   @Override
   public boolean A_() {
      return true;
   }

   @Override
   public float getEyeHeight() {
      return 0.1F;
   }

   public EntitySilverfish(World var1) {
      super(var1);
      this.setSize(0.4F, 0.3F);
      this.i.addTask(1, new EntityAISwimming(this));
      this.i.addTask(3, this.summonSilverfish = new EntitySilverfish.AISummonSilverfish(this));
      this.i.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0, false));
      this.i.addTask(5, new EntitySilverfish.AIHideInStone(this));
      this.bi.addTask(1, new EntityAIHurtByTarget(this, true));
      this.bi.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.silverfish.step", 0.15F, 1.0F);
   }

   @Override
   public double getYOffset() {
      return 0.2;
   }

   @Override
   public String getLivingSound() {
      return "mob.silverfish.say";
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.ARTHROPOD;
   }

   @Override
   public boolean l_() {
      return false;
   }

   @Override
   public float getBlockPathWeight(BlockPos var1) {
      return this.o.getBlockState(var1.down()).getBlock() == Blocks.stone ? 10.0F : super.getBlockPathWeight(var1);
   }

   @Override
   public void onUpdate() {
      this.aI = this.y;
      super.onUpdate();
   }

   @Override
   public String getDeathSound() {
      return "mob.silverfish.kill";
   }

   @Override
   public Item getDropItem() {
      return null;
   }

   @Override
   public boolean getCanSpawnHere() {
      if (super.getCanSpawnHere()) {
         EntityPlayer var1 = this.o.getClosestPlayerToEntity(this, 5.0);
         return var1 == null;
      } else {
         return false;
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.silverfish.hit";
   }

   public static class AIHideInStone extends EntityAIWander {
      public boolean field_179484_c;
      public EnumFacing facing;
      public EntitySilverfish silverfish;

      @Override
      public boolean continueExecuting() {
         return this.field_179484_c ? false : super.continueExecuting();
      }

      @Override
      public boolean shouldExecute() {
         if (this.silverfish.getAttackTarget() != null) {
            return false;
         } else if (!this.silverfish.s().noPath()) {
            return false;
         } else {
            Random var1 = this.silverfish.getRNG();
            if (var1.nextInt(10) == 0) {
               this.facing = EnumFacing.random(var1);
               BlockPos var2 = new BlockPos(this.silverfish.s, this.silverfish.t + 0.5, this.silverfish.u).a(this.facing);
               IBlockState var3 = this.silverfish.o.getBlockState(var2);
               if (BlockSilverfish.canContainSilverfish(var3)) {
                  this.field_179484_c = true;
                  return true;
               }
            }

            this.field_179484_c = false;
            return super.shouldExecute();
         }
      }

      @Override
      public void startExecuting() {
         if (!this.field_179484_c) {
            super.startExecuting();
         } else {
            World var1 = this.silverfish.o;
            BlockPos var2 = new BlockPos(this.silverfish.s, this.silverfish.t + 0.5, this.silverfish.u).a(this.facing);
            IBlockState var3 = var1.getBlockState(var2);
            if (BlockSilverfish.canContainSilverfish(var3)) {
               var1.a(var2, Blocks.monster_egg.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.forModelBlock(var3)), 3);
               this.silverfish.spawnExplosionParticle();
               this.silverfish.setDead();
            }
         }
      }

      public AIHideInStone(EntitySilverfish var1) {
         super(var1, 1.0, 10);
         this.silverfish = var1;
         this.setMutexBits(1);
      }
   }

   public static class AISummonSilverfish extends EntityAIBase {
      public int field_179463_b;
      public EntitySilverfish silverfish;

      @Override
      public void updateTask() {
         this.field_179463_b--;
         if (this.field_179463_b <= 0) {
            World var1 = this.silverfish.o;
            Random var2 = this.silverfish.getRNG();
            BlockPos var3 = new BlockPos(this.silverfish);

            for (int var4 = 0; var4 <= 5 && var4 >= -5; var4 = var4 <= 0 ? 1 - var4 : 0 - var4) {
               for (int var5 = 0; var5 <= 10 && var5 >= -10; var5 = var5 <= 0 ? 1 - var5 : 0 - var5) {
                  for (int var6 = 0; var6 <= 10 && var6 >= -10; var6 = var6 <= 0 ? 1 - var6 : 0 - var6) {
                     BlockPos var7 = var3.add(var5, var4, var6);
                     IBlockState var8 = var1.getBlockState(var7);
                     if (var8.getBlock() == Blocks.monster_egg) {
                        if (var1.Q().getBoolean("mobGriefing")) {
                           var1.destroyBlock(var7, true);
                        } else {
                           var1.a(var7, var8.getValue(BlockSilverfish.VARIANT).getModelBlock(), 3);
                        }

                        if (var2.nextBoolean()) {
                           return;
                        }
                     }
                  }
               }
            }
         }
      }

      @Override
      public boolean shouldExecute() {
         return this.field_179463_b > 0;
      }

      public void func_179462_f() {
         if (this.field_179463_b == 0) {
            this.field_179463_b = 20;
         }
      }

      public AISummonSilverfish(EntitySilverfish var1) {
         this.silverfish = var1;
      }
   }
}
