package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;

public class EntityEnderCrystal extends Entity {
   public int innerRotation;
   public int health;

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
   }

   public EntityEnderCrystal(World var1) {
      super(var1);
      this.k = true;
      this.setSize(2.0F, 2.0F);
      this.health = 5;
      this.innerRotation = this.V.nextInt(100000);
   }

   @Override
   public boolean l_() {
      return false;
   }

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if (this.isEntityInvulnerable(var1)) {
         return false;
      } else {
         if (!this.I && !this.o.D) {
            this.health = 0;
            if (this.health <= 0) {
               this.setDead();
               if (!this.o.D) {
                  this.o.createExplosion((Entity)null, this.s, this.t, this.u, 6.0F, true);
               }
            }
         }

         return true;
      }
   }

   @Override
   public void k_() {
      this.ac.addObject(8, this.health);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.innerRotation++;
      this.ac.updateObject(8, this.health);
      int var1 = MathHelper.floor_double(this.s);
      int var2 = MathHelper.floor_double(this.t);
      int var3 = MathHelper.floor_double(this.u);
      if (this.o.t instanceof WorldProviderEnd && this.o.getBlockState(new BlockPos(var1, var2, var3)).getBlock() != Blocks.fire) {
         this.o.setBlockState(new BlockPos(var1, var2, var3), Blocks.fire.getDefaultState());
      }
   }

   public EntityEnderCrystal(World var1, double var2, double var4, double var6) {
      this(var1);
      this.b(var2, var4, var6);
   }
}
