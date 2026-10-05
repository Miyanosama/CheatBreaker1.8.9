package net.minecraft.entity.passive;

import junit.swingui.TestRunner$12;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher$3;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.optifine.texture.InternalFormat;
import org.slf4j.helpers.FormattingTuple;

public abstract class EntityAnimal extends EntityAgeable implements IAnimals {
   public Block bn = Blocks.grass;
   public InternalFormat field_0004;
   public EntityPlayer playerInLove;
   public ChunkRenderDispatcher$3 field_0000;
   public int inLove;
   public FormattingTuple field_0006;
   public TestRunner$12 field_0005;

   public EntityPlayer getPlayerInLove() {
      return this.playerInLove;
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 18) {
         for (int var2 = 0; var2 < 7; var2++) {
            double var3 = this.V.nextGaussian() * 0.02;
            double var5 = this.V.nextGaussian() * 0.02;
            double var7 = this.V.nextGaussian() * 0.02;
            this.o
               .spawnParticle(
                  EnumParticleTypes.HEART,
                  this.s + this.V.nextFloat() * this.J * 2.0F - this.J,
                  this.t + 0.5 + this.V.nextFloat() * this.K,
                  this.u + this.V.nextFloat() * this.J * 2.0F - this.J,
                  var3,
                  var5,
                  var7
               );
         }
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public int getExperiencePoints(EntityPlayer var1) {
      return 1 + this.o.s.nextInt(3);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("InLove", this.inLove);
   }

   public void setInLove(EntityPlayer var1) {
      this.inLove = 600;
      this.playerInLove = var1;
      this.o.setEntityState(this, (byte)18);
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         this.inLove = 0;
         return super.attackEntityFrom(var1, var2);
      }
   }

   public boolean isInLove() {
      return this.inLove > 0;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.inLove = var1.getInteger("InLove");
   }

   @Override
   public boolean getCanSpawnHere() {
      int var1 = MathHelper.floor_double(this.s);
      int var2 = MathHelper.floor_double(this.getEntityBoundingBox().b);
      int var3 = MathHelper.floor_double(this.u);
      BlockPos var4 = new BlockPos(var1, var2, var3);
      return this.o.getBlockState(var4.down()).getBlock() == this.bn && this.o.getLight(var4) > 8 && super.getCanSpawnHere();
   }

   public boolean isBreedingItem(ItemStack var1) {
      return var1 == null ? false : var1.getItem() == Items.wheat;
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (var2 != null) {
         if (this.isBreedingItem(var2) && this.l() == 0 && this.inLove <= 0) {
            this.consumeItemFromStack(var1, var2);
            this.setInLove(var1);
            return true;
         }

         if (this.o_() && this.isBreedingItem(var2)) {
            this.consumeItemFromStack(var1, var2);
            this.func_175501_a((int)(-this.l() / 20 * 0.1F), true);
            return true;
         }
      }

      return super.interact(var1);
   }

   @Override
   public int getTalkInterval() {
      return 120;
   }

   public void consumeItemFromStack(EntityPlayer var1, ItemStack var2) {
      if (!var1.bA.isCreativeMode) {
         var2.stackSize--;
         if (var2.stackSize <= 0) {
            var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
         }
      }
   }

   @Override
   public float getBlockPathWeight(BlockPos var1) {
      return this.o.getBlockState(var1.down()).getBlock() == Blocks.grass ? 10.0F : this.o.o(var1) - 0.5F;
   }

   @Override
   public void updateAITasks() {
      if (this.l() != 0) {
         this.inLove = 0;
      }

      super.updateAITasks();
   }

   @Override
   public boolean canDespawn() {
      return false;
   }

   public boolean canMateWith(EntityAnimal var1) {
      return var1 == this ? false : (var1.getClass() != this.getClass() ? false : this.isInLove() && var1.isInLove());
   }

   public void resetInLove() {
      this.inLove = 0;
   }

   public EntityAnimal(World var1) {
      super(var1);
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.l() != 0) {
         this.inLove = 0;
      }

      if (this.inLove > 0) {
         this.inLove--;
         if (this.inLove % 10 == 0) {
            double var1 = this.V.nextGaussian() * 0.02;
            double var3 = this.V.nextGaussian() * 0.02;
            double var5 = this.V.nextGaussian() * 0.02;
            this.o
               .spawnParticle(
                  EnumParticleTypes.HEART,
                  this.s + this.V.nextFloat() * this.J * 2.0F - this.J,
                  this.t + 0.5 + this.V.nextFloat() * this.K,
                  this.u + this.V.nextFloat() * this.J * 2.0F - this.J,
                  var1,
                  var3,
                  var5
               );
         }
      }
   }
}
