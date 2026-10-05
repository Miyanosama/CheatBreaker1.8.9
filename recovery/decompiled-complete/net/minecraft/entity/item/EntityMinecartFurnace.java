package net.minecraft.entity.item;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.command.PlayerSelector$1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs;

public class EntityMinecartFurnace extends EntityMinecart {
   public int fuel;
   public double pushZ;
   public PlayerSelector$1 field_0002;
   public StructureStrongholdPieces$Stairs field_0004;
   public AnvilConverterException field_0000;
   public double pushX;

   public EntityMinecartFurnace(World var1) {
      super(var1);
   }

   public boolean isMinecartPowered() {
      return (this.ac.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public IBlockState getDefaultDisplayTile() {
      return (this.isMinecartPowered() ? Blocks.lit_furnace : Blocks.furnace).getDefaultState().withProperty(BlockFurnace.FACING, EnumFacing.NORTH);
   }

   @Override
   public boolean a_(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (var2 != null && var2.getItem() == Items.coal) {
         if (!var1.bA.isCreativeMode && --var2.stackSize == 0) {
            var1.bi.setInventorySlotContents(var1.bi.currentItem, (ItemStack)null);
         }

         this.fuel += 3600;
      }

      this.pushX = this.s - var1.s;
      this.pushZ = this.u - var1.u;
      return true;
   }

   @Override
   public void killMinecart(DamageSource var1) {
      super.killMinecart(var1);
      if (!var1.isExplosion() && this.o.Q().getBoolean("doEntityDrops")) {
         this.a(new ItemStack(Blocks.furnace, 1), 0.0F);
      }
   }

   @Override
   public double getMaximumSpeed() {
      return 0.2;
   }

   @Override
   public void func_180460_a(BlockPos var1, IBlockState var2) {
      super.func_180460_a(var1, var2);
      double var3 = this.pushX * this.pushX + this.pushZ * this.pushZ;
      if (var3 > 1.0E-4 && this.v * this.v + this.x * this.x > 0.001) {
         var3 = MathHelper.sqrt_double(var3);
         this.pushX /= var3;
         this.pushZ /= var3;
         if (this.pushX * this.v + this.pushZ * this.x < 0.0) {
            this.pushX = 0.0;
            this.pushZ = 0.0;
         } else {
            double var5 = var3 / this.getMaximumSpeed();
            this.pushX *= var5;
            this.pushZ *= var5;
         }
      }
   }

   public EntityMinecartFurnace(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.fuel > 0) {
         this.fuel--;
      }

      if (this.fuel <= 0) {
         this.pushX = this.pushZ = 0.0;
      }

      this.setMinecartPowered(this.fuel > 0);
      if (this.isMinecartPowered() && this.V.nextInt(4) == 0) {
         this.o.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.s, this.t + 0.8, this.u, 0.0, 0.0, 0.0);
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setDouble("PushX", this.pushX);
      var1.setDouble("PushZ", this.pushZ);
      var1.setShort("Fuel", (short)this.fuel);
   }

   @Override
   public void applyDrag() {
      double var1 = this.pushX * this.pushX + this.pushZ * this.pushZ;
      if (var1 > 1.0E-4) {
         var1 = MathHelper.sqrt_double(var1);
         this.pushX /= var1;
         this.pushZ /= var1;
         double var3 = 1.0;
         this.v *= 0.8F;
         this.w *= 0.0;
         this.x *= 0.8F;
         this.v = this.v + this.pushX * var3;
         this.x = this.x + this.pushZ * var3;
      } else {
         this.v *= 0.98F;
         this.w *= 0.0;
         this.x *= 0.98F;
      }

      super.applyDrag();
   }

   public void setMinecartPowered(boolean var1) {
      if (var1) {
         this.ac.updateObject(16, (byte)(this.ac.getWatchableObjectByte(16) | 1));
      } else {
         this.ac.updateObject(16, (byte)(this.ac.getWatchableObjectByte(16) & -2));
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.pushX = var1.getDouble("PushX");
      this.pushZ = var1.getDouble("PushZ");
      this.fuel = var1.getShort("Fuel");
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, new Byte((byte)0));
   }

   @Override
   public EntityMinecart$EnumMinecartType getMinecartType() {
      return EntityMinecart$EnumMinecartType.FURNACE;
   }
}
