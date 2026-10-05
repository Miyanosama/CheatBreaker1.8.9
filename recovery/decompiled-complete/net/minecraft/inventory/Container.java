package net.minecraft.inventory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBufUtil$ThreadLocalUnsafeDirectByteBuf$1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.TupleIntJsonSerializable;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public abstract class Container {
   public Set<Slot> dragSlots;
   public List<Slot> c;
   public Set<EntityPlayer> playerList;
   public int d;
   public short transactionID;
   public LogBrokerMonitor field_0003;
   public RenderSnowMan field_0012;
   public List<ICrafting> e;
   public int dragMode;
   public List<ItemStack> inventoryItemStacks = Lists.newArrayList();
   public ByteBufUtil$ThreadLocalUnsafeDirectByteBuf$1 field_0004;
   public TupleIntJsonSerializable field_0008;
   public int dragEvent;

   public void removeCraftingFromCrafters(ICrafting var1) {
      this.e.remove(var1);
   }

   public static boolean isValidDragMode(int var0, EntityPlayer var1) {
      return var0 == 0 ? true : (var0 == 1 ? true : var0 == 2 && var1.bA.isCreativeMode);
   }

   public static boolean canAddItemToSlot(Slot var0, ItemStack var1, boolean var2) {
      boolean var3 = var0 == null || !var0.getHasStack();
      if (var0 != null && var0.getHasStack() && var1 != null && var1.isItemEqual(var0.getStack()) && ItemStack.areItemStackTagsEqual(var0.getStack(), var1)) {
         int var10002 = var2 ? 0 : var1.stackSize;
         var3 |= var0.getStack().stackSize + var10002 <= var1.getMaxStackSize();
      }

      return var3;
   }

   public abstract boolean canInteractWith(EntityPlayer var1);

   public boolean canMergeSlot(ItemStack var1, Slot var2) {
      return true;
   }

   public void detectAndSendChanges() {
      for (int var1 = 0; var1 < this.c.size(); var1++) {
         ItemStack var2 = this.c.get(var1).getStack();
         ItemStack var3 = this.inventoryItemStacks.get(var1);
         if (!ItemStack.areItemStacksEqual(var3, var2)) {
            var3 = var2 == null ? null : var2.copy();
            this.inventoryItemStacks.set(var1, var3);

            for (int var4 = 0; var4 < this.e.size(); var4++) {
               this.e.get(var4).sendSlotContents(this, var1, var3);
            }
         }
      }
   }

   public Slot a(int var1) {
      return this.c.get(var1);
   }

   public void onCraftGuiOpened(ICrafting var1) {
      if (this.e.contains(var1)) {
         throw new IllegalArgumentException("Listener already listening");
      } else {
         this.e.add(var1);
         var1.updateCraftingInventory(this, this.getInventory());
         this.detectAndSendChanges();
      }
   }

   public void putStackInSlot(int var1, ItemStack var2) {
      this.a(var1).putStack(var2);
   }

   public ItemStack slotClick(int var1, int var2, int var3, EntityPlayer var4) {
      ItemStack var5 = null;
      InventoryPlayer var6 = var4.bi;
      if (var3 == 5) {
         int var7 = this.dragEvent;
         this.dragEvent = getDragEvent(var2);
         if ((var7 != 1 || this.dragEvent != 2) && var7 != this.dragEvent) {
            this.resetDrag();
         } else if (var6.getItemStack() == null) {
            this.resetDrag();
         } else if (this.dragEvent == 0) {
            this.dragMode = extractDragMode(var2);
            if (isValidDragMode(this.dragMode, var4)) {
               this.dragEvent = 1;
               this.dragSlots.clear();
            } else {
               this.resetDrag();
            }
         } else if (this.dragEvent == 1) {
            Slot var8 = this.c.get(var1);
            if (var8 != null
               && canAddItemToSlot(var8, var6.getItemStack(), true)
               && var8.isItemValid(var6.getItemStack())
               && var6.getItemStack().stackSize > this.dragSlots.size()
               && this.canDragIntoSlot(var8)) {
               this.dragSlots.add(var8);
            }
         } else if (this.dragEvent == 2) {
            if (!this.dragSlots.isEmpty()) {
               ItemStack var22 = var6.getItemStack().copy();
               int var9 = var6.getItemStack().stackSize;

               for (Slot var11 : this.dragSlots) {
                  if (var11 != null
                     && canAddItemToSlot(var11, var6.getItemStack(), true)
                     && var11.isItemValid(var6.getItemStack())
                     && var6.getItemStack().stackSize >= this.dragSlots.size()
                     && this.canDragIntoSlot(var11)) {
                     ItemStack var12 = var22.copy();
                     int var13 = var11.getHasStack() ? var11.getStack().stackSize : 0;
                     computeStackSize(this.dragSlots, this.dragMode, var12, var13);
                     if (var12.stackSize > var12.getMaxStackSize()) {
                        var12.stackSize = var12.getMaxStackSize();
                     }

                     if (var12.stackSize > var11.getItemStackLimit(var12)) {
                        var12.stackSize = var11.getItemStackLimit(var12);
                     }

                     var9 -= var12.stackSize - var13;
                     var11.putStack(var12);
                  }
               }

               var22.stackSize = var9;
               if (var22.stackSize <= 0) {
                  var22 = null;
               }

               var6.setItemStack(var22);
            }

            this.resetDrag();
         } else {
            this.resetDrag();
         }
      } else if (this.dragEvent != 0) {
         this.resetDrag();
      } else if ((var3 == 0 || var3 == 1) && (var2 == 0 || var2 == 1)) {
         if (var1 == -999) {
            if (var6.getItemStack() != null) {
               if (var2 == 0) {
                  var4.dropPlayerItemWithRandomChoice(var6.getItemStack(), true);
                  var6.setItemStack((ItemStack)null);
               }

               if (var2 == 1) {
                  var4.dropPlayerItemWithRandomChoice(var6.getItemStack().splitStack(1), true);
                  if (var6.getItemStack().stackSize == 0) {
                     var6.setItemStack((ItemStack)null);
                  }
               }
            }
         } else if (var3 == 1) {
            if (var1 < 0) {
               return null;
            }

            Slot var20 = this.c.get(var1);
            if (var20 != null && var20.canTakeStack(var4)) {
               ItemStack var27 = this.transferStackInSlot(var4, var1);
               if (var27 != null) {
                  Item var32 = var27.getItem();
                  var5 = var27.copy();
                  if (var20.getStack() != null && var20.getStack().getItem() == var32) {
                     this.retrySlotClick(var1, var2, true, var4);
                  }
               }
            }
         } else {
            if (var1 < 0) {
               return null;
            }

            Slot var21 = this.c.get(var1);
            if (var21 != null) {
               ItemStack var28 = var21.getStack();
               ItemStack var33 = var6.getItemStack();
               if (var28 != null) {
                  var5 = var28.copy();
               }

               if (var28 == null) {
                  if (var33 != null && var21.isItemValid(var33)) {
                     int var39 = var2 == 0 ? var33.stackSize : 1;
                     if (var39 > var21.getItemStackLimit(var33)) {
                        var39 = var21.getItemStackLimit(var33);
                     }

                     if (var33.stackSize >= var39) {
                        var21.putStack(var33.splitStack(var39));
                     }

                     if (var33.stackSize == 0) {
                        var6.setItemStack((ItemStack)null);
                     }
                  }
               } else if (var21.canTakeStack(var4)) {
                  if (var33 == null) {
                     int var38 = var2 == 0 ? var28.stackSize : (var28.stackSize + 1) / 2;
                     ItemStack var42 = var21.decrStackSize(var38);
                     var6.setItemStack(var42);
                     if (var28.stackSize == 0) {
                        var21.putStack((ItemStack)null);
                     }

                     var21.onPickupFromSlot(var4, var6.getItemStack());
                  } else if (var21.isItemValid(var33)) {
                     if (var28.getItem() == var33.getItem() && var28.getMetadata() == var33.getMetadata() && ItemStack.areItemStackTagsEqual(var28, var33)) {
                        int var37 = var2 == 0 ? var33.stackSize : 1;
                        if (var37 > var21.getItemStackLimit(var33) - var28.stackSize) {
                           var37 = var21.getItemStackLimit(var33) - var28.stackSize;
                        }

                        if (var37 > var33.getMaxStackSize() - var28.stackSize) {
                           var37 = var33.getMaxStackSize() - var28.stackSize;
                        }

                        var33.splitStack(var37);
                        if (var33.stackSize == 0) {
                           var6.setItemStack((ItemStack)null);
                        }

                        var28.stackSize += var37;
                     } else if (var33.stackSize <= var21.getItemStackLimit(var33)) {
                        var21.putStack(var33);
                        var6.setItemStack(var28);
                     }
                  } else if (var28.getItem() == var33.getItem()
                     && var33.getMaxStackSize() > 1
                     && (!var28.getHasSubtypes() || var28.getMetadata() == var33.getMetadata())
                     && ItemStack.areItemStackTagsEqual(var28, var33)) {
                     int var36 = var28.stackSize;
                     if (var36 > 0 && var36 + var33.stackSize <= var33.getMaxStackSize()) {
                        var33.stackSize += var36;
                        var28 = var21.decrStackSize(var36);
                        if (var28.stackSize == 0) {
                           var21.putStack((ItemStack)null);
                        }

                        var21.onPickupFromSlot(var4, var6.getItemStack());
                     }
                  }
               }

               var21.onSlotChanged();
            }
         }
      } else if (var3 == 2 && var2 >= 0 && var2 < 9) {
         Slot var19 = this.c.get(var1);
         if (var19.canTakeStack(var4)) {
            ItemStack var26 = var6.getStackInSlot(var2);
            boolean var31 = var26 == null || var19.inventory == var6 && var19.isItemValid(var26);
            int var35 = -1;
            if (!var31) {
               var35 = var6.getFirstEmptyStack();
               var31 |= var35 > -1;
            }

            if (var19.getHasStack() && var31) {
               ItemStack var41 = var19.getStack();
               var6.setInventorySlotContents(var2, var41.copy());
               if ((var19.inventory != var6 || !var19.isItemValid(var26)) && var26 != null) {
                  if (var35 > -1) {
                     var6.addItemStackToInventory(var26);
                     var19.decrStackSize(var41.stackSize);
                     var19.putStack((ItemStack)null);
                     var19.onPickupFromSlot(var4, var41);
                  }
               } else {
                  var19.decrStackSize(var41.stackSize);
                  var19.putStack(var26);
                  var19.onPickupFromSlot(var4, var41);
               }
            } else if (!var19.getHasStack() && var26 != null && var19.isItemValid(var26)) {
               var6.setInventorySlotContents(var2, (ItemStack)null);
               var19.putStack(var26);
            }
         }
      } else if (var3 == 3 && var4.bA.isCreativeMode && var6.getItemStack() == null && var1 >= 0) {
         Slot var18 = this.c.get(var1);
         if (var18 != null && var18.getHasStack()) {
            ItemStack var25 = var18.getStack().copy();
            var25.stackSize = var25.getMaxStackSize();
            var6.setItemStack(var25);
         }
      } else if (var3 == 4 && var6.getItemStack() == null && var1 >= 0) {
         Slot var17 = this.c.get(var1);
         if (var17 != null && var17.getHasStack() && var17.canTakeStack(var4)) {
            ItemStack var24 = var17.decrStackSize(var2 == 0 ? 1 : var17.getStack().stackSize);
            var17.onPickupFromSlot(var4, var24);
            var4.dropPlayerItemWithRandomChoice(var24, true);
         }
      } else if (var3 == 6 && var1 >= 0) {
         Slot var16 = this.c.get(var1);
         ItemStack var23 = var6.getItemStack();
         if (var23 != null && (var16 == null || !var16.getHasStack() || !var16.canTakeStack(var4))) {
            int var30 = var2 == 0 ? 0 : this.c.size() - 1;
            int var34 = var2 == 0 ? 1 : -1;

            for (int var40 = 0; var40 < 2; var40++) {
               for (int var43 = var30; var43 >= 0 && var43 < this.c.size() && var23.stackSize < var23.getMaxStackSize(); var43 += var34) {
                  Slot var44 = this.c.get(var43);
                  if (var44.getHasStack()
                     && canAddItemToSlot(var44, var23, true)
                     && var44.canTakeStack(var4)
                     && this.canMergeSlot(var23, var44)
                     && (var40 != 0 || var44.getStack().stackSize != var44.getStack().getMaxStackSize())) {
                     int var14 = Math.min(var23.getMaxStackSize() - var23.stackSize, var44.getStack().stackSize);
                     ItemStack var15 = var44.decrStackSize(var14);
                     var23.stackSize += var14;
                     if (var15.stackSize <= 0) {
                        var44.putStack((ItemStack)null);
                     }

                     var44.onPickupFromSlot(var4, var15);
                  }
               }
            }
         }

         this.detectAndSendChanges();
      }

      return var5;
   }

   public void resetDrag() {
      this.dragEvent = 0;
      this.dragSlots.clear();
   }

   public short getNextTransactionID(InventoryPlayer var1) {
      this.transactionID++;
      return this.transactionID;
   }

   public List<ItemStack> getInventory() {
      ArrayList var1 = Lists.newArrayList();

      for (int var2 = 0; var2 < this.c.size(); var2++) {
         var1.add(this.c.get(var2).getStack());
      }

      return var1;
   }

   public static int getDragEvent(int var0) {
      return var0 & 3;
   }

   public void updateProgressBar(int var1, int var2) {
   }

   public static int func_94534_d(int var0, int var1) {
      return var0 & 3 | (var1 & 3) << 2;
   }

   public boolean canDragIntoSlot(Slot var1) {
      return true;
   }

   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      Slot var3 = this.c.get(var2);
      return var3 != null ? var3.getStack() : null;
   }

   public void onCraftMatrixChanged(IInventory var1) {
      this.detectAndSendChanges();
   }

   public void onContainerClosed(EntityPlayer var1) {
      InventoryPlayer var2 = var1.bi;
      if (var2.getItemStack() != null) {
         var1.dropPlayerItemWithRandomChoice(var2.getItemStack(), false);
         var2.setItemStack((ItemStack)null);
      }
   }

   public boolean enchantItem(EntityPlayer var1, int var2) {
      return false;
   }

   public void setCanCraft(EntityPlayer var1, boolean var2) {
      if (var2) {
         this.playerList.remove(var1);
      } else {
         this.playerList.add(var1);
      }
   }

   public static int calcRedstoneFromInventory(IInventory var0) {
      if (var0 == null) {
         return 0;
      } else {
         int var1 = 0;
         float var2 = 0.0F;

         for (int var3 = 0; var3 < var0.getSizeInventory(); var3++) {
            ItemStack var4 = var0.getStackInSlot(var3);
            if (var4 != null) {
               var2 += (float)var4.stackSize / Math.min(var0.getInventoryStackLimit(), var4.getMaxStackSize());
               var1++;
            }
         }

         var2 /= var0.getSizeInventory();
         return MathHelper.floor_float(var2 * 14.0F) + (var1 > 0 ? 1 : 0);
      }
   }

   public Slot getSlotFromInventory(IInventory var1, int var2) {
      for (int var3 = 0; var3 < this.c.size(); var3++) {
         Slot var4 = this.c.get(var3);
         if (var4.isHere(var1, var2)) {
            return var4;
         }
      }

      return null;
   }

   public static int calcRedstone(TileEntity var0) {
      return var0 instanceof IInventory ? calcRedstoneFromInventory((IInventory)var0) : 0;
   }

   public void retrySlotClick(int var1, int var2, boolean var3, EntityPlayer var4) {
      this.slotClick(var1, var2, 1, var4);
   }

   public static int extractDragMode(int var0) {
      return var0 >> 2 & 3;
   }

   public static void computeStackSize(Set<Slot> var0, int var1, ItemStack var2, int var3) {
      switch (var1) {
         case 0:
            var2.stackSize = MathHelper.floor_float((float)var2.stackSize / var0.size());
            break;
         case 1:
            var2.stackSize = 1;
            break;
         case 2:
            var2.stackSize = var2.getItem().getItemStackLimit();
      }

      var2.stackSize += var3;
   }

   public Container() {
      this.c = Lists.newArrayList();
      this.dragMode = -1;
      this.dragSlots = Sets.newHashSet();
      this.e = Lists.newArrayList();
      this.playerList = Sets.newHashSet();
   }

   public boolean getCanCraft(EntityPlayer var1) {
      return !this.playerList.contains(var1);
   }

   public void putStacksInSlots(ItemStack[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         this.a(var2).putStack(var1[var2]);
      }
   }

   public boolean mergeItemStack(ItemStack var1, int var2, int var3, boolean var4) {
      boolean var5 = false;
      int var6 = var2;
      if (var4) {
         var6 = var3 - 1;
      }

      if (var1.isStackable()) {
         while (var1.stackSize > 0 && (!var4 && var6 < var3 || var4 && var6 >= var2)) {
            Slot var7 = this.c.get(var6);
            ItemStack var8 = var7.getStack();
            if (var8 != null
               && var8.getItem() == var1.getItem()
               && (!var1.getHasSubtypes() || var1.getMetadata() == var8.getMetadata())
               && ItemStack.areItemStackTagsEqual(var1, var8)) {
               int var9 = var8.stackSize + var1.stackSize;
               if (var9 <= var1.getMaxStackSize()) {
                  var1.stackSize = 0;
                  var8.stackSize = var9;
                  var7.onSlotChanged();
                  var5 = true;
               } else if (var8.stackSize < var1.getMaxStackSize()) {
                  var1.stackSize = var1.stackSize - (var1.getMaxStackSize() - var8.stackSize);
                  var8.stackSize = var1.getMaxStackSize();
                  var7.onSlotChanged();
                  var5 = true;
               }
            }

            if (var4) {
               var6--;
            } else {
               var6++;
            }
         }
      }

      if (var1.stackSize > 0) {
         if (var4) {
            var6 = var3 - 1;
         } else {
            var6 = var2;
         }

         while (!var4 && var6 < var3 || var4 && var6 >= var2) {
            Slot var11 = this.c.get(var6);
            ItemStack var12 = var11.getStack();
            if (var12 == null) {
               var11.putStack(var1.copy());
               var11.onSlotChanged();
               var1.stackSize = 0;
               var5 = true;
               break;
            }

            if (var4) {
               var6--;
            } else {
               var6++;
            }
         }
      }

      return var5;
   }

   public Slot a(Slot var1) {
      var1.slotNumber = this.c.size();
      this.c.add(var1);
      this.inventoryItemStacks.add((ItemStack)null);
      return var1;
   }
}
