package net.minecraft.entity.item;

import io.netty.buffer.PoolArena$HeapArena;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.realms.Tezzelator;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;

public class EntityMinecartHopper extends EntityMinecartContainer implements IHopper {
   public int transferTicker;
   public PoolArena$HeapArena field_0000;
   public Tezzelator field_0001;
   public BlockPos field_174900_c;
   public boolean isBlocked = true;

   @Override
   public double getYPos() {
      return this.t + 0.5;
   }

   public boolean getBlocked() {
      return this.isBlocked;
   }

   @Override
   public double getXPos() {
      return this.s;
   }

   @Override
   public int getDefaultDisplayTileOffset() {
      return 1;
   }

   public boolean canTransfer() {
      return this.transferTicker > 0;
   }

   public EntityMinecartHopper(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
      this.transferTicker = -1;
      this.field_174900_c = BlockPos.ORIGIN;
   }

   @Override
   public int getSizeInventory() {
      return 5;
   }

   @Override
   public void onActivatorRailPass(int var1, int var2, int var3, boolean var4) {
      boolean var5 = !var4;
      if (var5 != this.getBlocked()) {
         this.setBlocked(var5);
      }
   }

   @Override
   public World getWorld() {
      return this.o;
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      return new ContainerHopper(var1, this, var2);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.transferTicker = var1.getInteger("TransferCooldown");
   }

   @Override
   public double getZPos() {
      return this.u;
   }

   public void setTransferTicker(int var1) {
      this.transferTicker = var1;
   }

   @Override
   public boolean a_(EntityPlayer var1) {
      if (!this.o.D) {
         var1.displayGUIChest(this);
      }

      return true;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.o.D && this.isEntityAlive() && this.getBlocked()) {
         BlockPos var1 = new BlockPos(this);
         if (var1.equals(this.field_174900_c)) {
            this.transferTicker--;
         } else {
            this.setTransferTicker(0);
         }

         if (!this.canTransfer()) {
            this.setTransferTicker(0);
            if (this.func_96112_aD()) {
               this.setTransferTicker(4);
               this.markDirty();
            }
         }
      }
   }

   @Override
   public EntityMinecart$EnumMinecartType getMinecartType() {
      return EntityMinecart$EnumMinecartType.HOPPER;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("TransferCooldown", this.transferTicker);
   }

   @Override
   public IBlockState getDefaultDisplayTile() {
      return Blocks.hopper.getDefaultState();
   }

   public EntityMinecartHopper(World var1) {
      super(var1);
      this.transferTicker = -1;
      this.field_174900_c = BlockPos.ORIGIN;
   }

   public boolean func_96112_aD() {
      if (TileEntityHopper.captureDroppedItems(this)) {
         return true;
      } else {
         List var1 = this.o.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().expand(0.25, 0.0, 0.25), EntitySelectors.selectAnything);
         if (var1.size() > 0) {
            TileEntityHopper.putDropInInventoryAllSlots(this, (EntityItem)var1.get(0));
         }

         return false;
      }
   }

   @Override
   public String getGuiID() {
      return "minecraft:hopper";
   }

   @Override
   public void killMinecart(DamageSource var1) {
      super.killMinecart(var1);
      if (this.o.Q().getBoolean("doEntityDrops")) {
         this.dropItemWithOffset(Item.getItemFromBlock(Blocks.hopper), 1, 0.0F);
      }
   }

   public void setBlocked(boolean var1) {
      this.isBlocked = var1;
   }
}
