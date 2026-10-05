package net.minecraft.entity.monster;

import io.netty.handler.codec.socks.SocksInitRequest;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class EntitySilverfish extends EntityMob {
   public EntitySilverfish$AISummonSilverfish summonSilverfish;
   public WorldGenBigTree field_0002;
   public SocksInitRequest field_0000;

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
      this.i.addTask(3, this.summonSilverfish = new EntitySilverfish$AISummonSilverfish(this));
      this.i.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0, false));
      this.i.addTask(5, new EntitySilverfish$AIHideInStone(this));
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
}
