package net.minecraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityChest extends TileEntityLockable implements IInventory, ITickable {
   public TileEntityChest adjacentChestXNeg;
   public int numPlayersUsing;
   public TileEntityChest adjacentChestZNeg;
   public TileEntityChest adjacentChestZPos;
   public ItemStack[] chestContents = new ItemStack[27];
   public float recoveredField3296;
   public float recoveredField3297;
   public int ticksSinceSync;
   public String customName;
   public boolean adjacentChestChecked;
   public TileEntityChest adjacentChestXPos;
   public int cachedChestType;

   public void func_174910_a(TileEntityChest var1, EnumFacing var2) {
      if (var1.isInvalid()) {
         this.adjacentChestChecked = false;
      } else if (this.adjacentChestChecked) {
         switch (var2) {
            case NORTH:
               if (this.adjacentChestZNeg != var1) {
                  this.adjacentChestChecked = false;
               }
               break;
            case SOUTH:
               if (this.adjacentChestZPos != var1) {
                  this.adjacentChestChecked = false;
               }
               break;
            case EAST:
               if (this.adjacentChestXPos != var1) {
                  this.adjacentChestChecked = false;
               }
               break;
            case WEST:
               if (this.adjacentChestXNeg != var1) {
                  this.adjacentChestChecked = false;
               }
         }
      }
   }

   public TileEntityChest getAdjacentChest(EnumFacing var1) {
      BlockPos var2 = this.c.a(var1);
      if (this.isChestAt(var2)) {
         TileEntity var3 = this.b.getTileEntity(var2);
         if (var3 instanceof TileEntityChest) {
            TileEntityChest var4 = (TileEntityChest)var3;
            var4.func_174910_a(this, var1.getOpposite());
            return var4;
         }
      }

      return null;
   }

   @Override
   public void clear() {
      for (int var1 = 0; var1 < this.chestContents.length; var1++) {
         this.chestContents[var1] = null;
      }
   }

   public TileEntityChest() {
      this.cachedChestType = -1;
   }

   public int getChestType() {
      if (this.cachedChestType == -1) {
         if (this.b == null || !(this.w() instanceof BlockChest)) {
            return 0;
         }

         this.cachedChestType = ((BlockChest)this.w()).chestType;
      }

      return this.cachedChestType;
   }

   @Override
   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }

   @Override
   public String getGuiID() {
      return "minecraft:chest";
   }

   @Override
   public void update() {
      this.checkForAdjacentChests();
      int var1 = this.c.getX();
      int var2 = this.c.getY();
      int var3 = this.c.getZ();
      this.ticksSinceSync++;
      if (!this.b.D && this.numPlayersUsing != 0 && (this.ticksSinceSync + var1 + var2 + var3) % 200 == 0) {
         this.numPlayersUsing = 0;
         float var4 = 5.0F;

         for (EntityPlayer var6 : this.b
            .getEntitiesWithinAABB(
               EntityPlayer.class, new AxisAlignedBB(var1 - var4, var2 - var4, var3 - var4, var1 + 1 + var4, var2 + 1 + var4, var3 + 1 + var4)
            )) {
            if (var6.bk instanceof ContainerChest) {
               IInventory var7 = ((ContainerChest)var6.bk).getLowerChestInventory();
               if (var7 == this || var7 instanceof InventoryLargeChest && ((InventoryLargeChest)var7).isPartOfLargeChest(this)) {
                  this.numPlayersUsing++;
               }
            }
         }
      }

      this.recoveredField3296 = this.recoveredField3297;
      float var11 = 0.1F;
      if (this.numPlayersUsing > 0 && this.recoveredField3297 == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
         double var12 = var1 + 0.5;
         double var15 = var3 + 0.5;
         if (this.adjacentChestZPos != null) {
            var15 += 0.5;
         }

         if (this.adjacentChestXPos != null) {
            var12 += 0.5;
         }

         this.b.playSoundEffect(var12, var2 + 0.5, var15, "random.chestopen", 0.5F, this.b.s.nextFloat() * 0.1F + 0.9F);
      }

      if (this.numPlayersUsing == 0 && this.recoveredField3297 > 0.0F || this.numPlayersUsing > 0 && this.recoveredField3297 < 1.0F) {
         float var13 = this.recoveredField3297;
         if (this.numPlayersUsing > 0) {
            this.recoveredField3297 += var11;
         } else {
            this.recoveredField3297 -= var11;
         }

         if (this.recoveredField3297 > 1.0F) {
            this.recoveredField3297 = 1.0F;
         }

         float var14 = 0.5F;
         if (this.recoveredField3297 < var14 && var13 >= var14 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
            double var16 = var1 + 0.5;
            double var9 = var3 + 0.5;
            if (this.adjacentChestZPos != null) {
               var9 += 0.5;
            }

            if (this.adjacentChestXPos != null) {
               var16 += 0.5;
            }

            this.b.playSoundEffect(var16, var2 + 0.5, var9, "random.chestclosed", 0.5F, this.b.s.nextFloat() * 0.1F + 0.9F);
         }

         if (this.recoveredField3297 < 0.0F) {
            this.recoveredField3297 = 0.0F;
         }
      }
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      return new ContainerChest(var1, this, var2);
   }

   @Override
   public void invalidate() {
      super.invalidate();
      this.updateContainingBlockInfo();
      this.checkForAdjacentChests();
   }

   @Override
   public boolean receiveClientEvent(int var1, int var2) {
      if (var1 == 1) {
         this.numPlayersUsing = var2;
         return true;
      } else {
         return super.receiveClientEvent(var1, var2);
      }
   }

   @Override
   public void setField(int var1, int var2) {
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.chestContents[var1] = var2;
      if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }

      this.markDirty();
   }

   @Override
   public String z_() {
      return this.u_() ? this.customName : "container.chest";
   }

   @Override
   public int getSizeInventory() {
      return 27;
   }

   @Override
   public ItemStack decrStackSize(int var1, int var2) {
      if (this.chestContents[var1] != null) {
         if (this.chestContents[var1].stackSize <= var2) {
            ItemStack var4 = this.chestContents[var1];
            this.chestContents[var1] = null;
            this.markDirty();
            return var4;
         } else {
            ItemStack var3 = this.chestContents[var1].splitStack(var2);
            if (this.chestContents[var1].stackSize == 0) {
               this.chestContents[var1] = null;
            }

            this.markDirty();
            return var3;
         }
      } else {
         return null;
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items", 10);
      this.chestContents = new ItemStack[this.getSizeInventory()];
      if (var1.hasKey("CustomName", 8)) {
         this.customName = var1.getString("CustomName");
      }

      for (int var3 = 0; var3 < var2.tagCount(); var3++) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         int var5 = var4.getByte("Slot") & 255;
         if (var5 >= 0 && var5 < this.chestContents.length) {
            this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }
   }

   public void setCustomName(String var1) {
      this.customName = var1;
   }

   @Override
   public void closeInventory(EntityPlayer var1) {
      if (!var1.isSpectator() && this.w() instanceof BlockChest) {
         this.numPlayersUsing--;
         this.b.addBlockEvent(this.c, this.w(), 1, this.numPlayersUsing);
         this.b.notifyNeighborsOfStateChange(this.c, this.w());
         this.b.notifyNeighborsOfStateChange(this.c.down(), this.w());
      }
   }

   @Override
   public boolean u_() {
      return this.customName != null && this.customName.length() > 0;
   }

   @Override
   public ItemStack getStackInSlot(int var1) {
      return this.chestContents[var1];
   }

   public TileEntityChest(int var1) {
      this.cachedChestType = var1;
   }

   @Override
   public void updateContainingBlockInfo() {
      super.updateContainingBlockInfo();
      this.adjacentChestChecked = false;
   }

   public void checkForAdjacentChests() {
      if (!this.adjacentChestChecked) {
         this.adjacentChestChecked = true;
         this.adjacentChestXNeg = this.getAdjacentChest(EnumFacing.WEST);
         this.adjacentChestXPos = this.getAdjacentChest(EnumFacing.EAST);
         this.adjacentChestZNeg = this.getAdjacentChest(EnumFacing.NORTH);
         this.adjacentChestZPos = this.getAdjacentChest(EnumFacing.SOUTH);
      }
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      NBTTagList var2 = new NBTTagList();

      for (int var3 = 0; var3 < this.chestContents.length; var3++) {
         if (this.chestContents[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.chestContents[var3].writeToNBT(var4);
            var2.appendTag(var4);
         }
      }

      var1.setTag("Items", var2);
      if (this.u_()) {
         var1.setString("CustomName", this.customName);
      }
   }

   @Override
   public ItemStack removeStackFromSlot(int var1) {
      if (this.chestContents[var1] != null) {
         ItemStack var2 = this.chestContents[var1];
         this.chestContents[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public int getField(int var1) {
      return 0;
   }

   @Override
   public void openInventory(EntityPlayer var1) {
      if (!var1.isSpectator()) {
         if (this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
         }

         this.numPlayersUsing++;
         this.b.addBlockEvent(this.c, this.w(), 1, this.numPlayersUsing);
         this.b.notifyNeighborsOfStateChange(this.c, this.w());
         this.b.notifyNeighborsOfStateChange(this.c.down(), this.w());
      }
   }

   public boolean isChestAt(BlockPos var1) {
      if (this.b == null) {
         return false;
      } else {
         Block var2 = this.b.getBlockState(var1).getBlock();
         return var2 instanceof BlockChest && ((BlockChest)var2).chestType == this.getChestType();
      }
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.b.getTileEntity(this.c) != this ? false : var1.e(this.c.getX() + 0.5, this.c.getY() + 0.5, this.c.getZ() + 0.5) <= 64.0;
   }
}
