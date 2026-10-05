package net.minecraft.inventory;

import io.netty.handler.codec.MessageToMessageCodec$2;
import io.netty.handler.codec.marshalling.ChannelBufferByteInput;
import io.netty.util.internal.MpscLinkedQueuePad1;
import java.util.Map;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Session;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapterBiped;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContainerRepair extends Container {
   public ChannelBufferByteInput field_0008;
   public int maximumCost;
   public MessageToMessageCodec$2 field_0007;
   public EntityPlayer thePlayer;
   public BlockFenceGate field_0005;
   public ModelAdapterBiped field_0009;
   public static Logger logger = LogManager.getLogger();
   public Session field_0002;
   public World theWorld;
   public BlockPos selfPosition;
   public MpscLinkedQueuePad1 field_0000;
   public int materialCost;
   public IInventory inputSlots;
   public IInventory outputSlot = new InventoryCraftResult();
   public String repairedItemName;

   public void updateItemName(String var1) {
      this.repairedItemName = var1;
      if (this.a(2).getHasStack()) {
         ItemStack var2 = this.a(2).getStack();
         if (StringUtils.isBlank(var1)) {
            var2.clearCustomName();
         } else {
            var2.setStackDisplayName(this.repairedItemName);
         }
      }

      this.updateRepairOutput();
   }

   @Override
   public void onContainerClosed(EntityPlayer var1) {
      super.onContainerClosed(var1);
      if (!this.theWorld.D) {
         for (int var2 = 0; var2 < this.inputSlots.getSizeInventory(); var2++) {
            ItemStack var3 = this.inputSlots.removeStackFromSlot(var2);
            if (var3 != null) {
               var1.dropPlayerItemWithRandomChoice(var3, false);
            }
         }
      }
   }

   @Override
   public void onCraftGuiOpened(ICrafting var1) {
      super.onCraftGuiOpened(var1);
      var1.sendProgressBarUpdate(this, 0, this.maximumCost);
   }

   public ContainerRepair(InventoryPlayer var1, World var2, BlockPos var3, EntityPlayer var4) {
      this.inputSlots = new ContainerRepair$1(this, "Repair", true, 2);
      this.selfPosition = var3;
      this.theWorld = var2;
      this.thePlayer = var4;
      this.a(new Slot(this.inputSlots, 0, 27, 47));
      this.a(new Slot(this.inputSlots, 1, 76, 47));
      this.a(new ContainerRepair$2(this, this.outputSlot, 2, 134, 47, var2, var3));

      for (int var5 = 0; var5 < 3; var5++) {
         for (int var6 = 0; var6 < 9; var6++) {
            this.a(new Slot(var1, var6 + var5 * 9 + 9, 8 + var6 * 18, 84 + var5 * 18));
         }
      }

      for (int var7 = 0; var7 < 9; var7++) {
         this.a(new Slot(var1, var7, 8 + var7 * 18, 142));
      }
   }

   @Override
   public void onCraftMatrixChanged(IInventory var1) {
      super.onCraftMatrixChanged(var1);
      if (var1 == this.inputSlots) {
         this.updateRepairOutput();
      }
   }

   @Override
   public boolean canInteractWith(EntityPlayer var1) {
      return this.theWorld.getBlockState(this.selfPosition).getBlock() != Blocks.anvil
         ? false
         : var1.e(this.selfPosition.getX() + 0.5, this.selfPosition.getY() + 0.5, this.selfPosition.getZ() + 0.5) <= 64.0;
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = this.c.get(var2);
      if (var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if (var2 == 2) {
            if (!this.mergeItemStack(var5, 3, 39, true)) {
               return null;
            }

            var4.onSlotChange(var5, var3);
         } else if (var2 != 0 && var2 != 1) {
            if (var2 >= 3 && var2 < 39 && !this.mergeItemStack(var5, 0, 2, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(var5, 3, 39, false)) {
            return null;
         }

         if (var5.stackSize == 0) {
            var4.putStack((ItemStack)null);
         } else {
            var4.onSlotChanged();
         }

         if (var5.stackSize == var3.stackSize) {
            return null;
         }

         var4.onPickupFromSlot(var1, var5);
      }

      return var3;
   }

   @Override
   public void updateProgressBar(int var1, int var2) {
      if (var1 == 0) {
         this.maximumCost = var2;
      }
   }

   public void updateRepairOutput() {
      boolean var1 = false;
      boolean var2 = true;
      boolean var3 = true;
      boolean var4 = true;
      byte var5 = 2;
      boolean var6 = true;
      boolean var7 = true;
      ItemStack var8 = this.inputSlots.getStackInSlot(0);
      this.maximumCost = 1;
      int var9 = 0;
      int var10 = 0;
      byte var11 = 0;
      if (var8 == null) {
         this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
         this.maximumCost = 0;
      } else {
         ItemStack var12 = var8.copy();
         ItemStack var13 = this.inputSlots.getStackInSlot(1);
         Map var14 = EnchantmentHelper.getEnchantments(var12);
         boolean var15 = false;
         var10 = var10 + var8.method_27845() + (var13 == null ? 0 : var13.method_27845());
         this.materialCost = 0;
         if (var13 != null) {
            var15 = var13.getItem() == Items.enchanted_book && Items.enchanted_book.getEnchantments(var13).tagCount() > 0;
            if (var12.isItemStackDamageable() && var12.getItem().getIsRepairable(var8, var13)) {
               int var29 = Math.min(var12.getItemDamage(), var12.getMaxDamage() / 4);
               if (var29 <= 0) {
                  this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
                  this.maximumCost = 0;
                  return;
               }

               int var33;
               for (var33 = 0; var29 > 0 && var33 < var13.stackSize; var33++) {
                  int var35 = var12.getItemDamage() - var29;
                  var12.setItemDamage(var35);
                  var9++;
                  var29 = Math.min(var12.getItemDamage(), var12.getMaxDamage() / 4);
               }

               this.materialCost = var33;
            } else {
               if (!var15 && (var12.getItem() != var13.getItem() || !var12.isItemStackDamageable())) {
                  this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
                  this.maximumCost = 0;
                  return;
               }

               if (var12.isItemStackDamageable() && !var15) {
                  int var16 = var8.getMaxDamage() - var8.getItemDamage();
                  int var17 = var13.getMaxDamage() - var13.getItemDamage();
                  int var18 = var17 + var12.getMaxDamage() * 12 / 100;
                  int var19 = var16 + var18;
                  int var20 = var12.getMaxDamage() - var19;
                  if (var20 < 0) {
                     var20 = 0;
                  }

                  if (var20 < var12.getMetadata()) {
                     var12.setItemDamage(var20);
                     var9 += 2;
                  }
               }

               Map var28 = EnchantmentHelper.getEnchantments(var13);

               for (int var34 : var28.keySet()) {
                  Enchantment var36 = Enchantment.getEnchantmentById(var34);
                  if (var36 != null) {
                     int var37 = var14.containsKey(var34) ? (Integer)var14.get(var34) : 0;
                     int var21 = (Integer)var28.get(var34);
                     int var22;
                     if (var37 == var21) {
                        var22 = ++var21;
                     } else {
                        var22 = Math.max(var21, var37);
                     }

                     var21 = var22;
                     boolean var23 = var36.canApply(var8);
                     if (this.thePlayer.bA.isCreativeMode || var8.getItem() == Items.enchanted_book) {
                        var23 = true;
                     }

                     for (int var25 : var14.keySet()) {
                        if (var25 != var34 && !var36.canApplyTogether(Enchantment.getEnchantmentById(var25))) {
                           var23 = false;
                           var9++;
                        }
                     }

                     if (var23) {
                        if (var22 > var36.getMaxLevel()) {
                           var21 = var36.getMaxLevel();
                        }

                        var14.put(var34, var21);
                        int var40 = 0;
                        switch (var36.getWeight()) {
                           case 1:
                              var40 = 8;
                              break;
                           case 2:
                              var40 = 4;
                           case 3:
                           case 4:
                           case 6:
                           case 7:
                           case 8:
                           case 9:
                           default:
                              break;
                           case 5:
                              var40 = 2;
                              break;
                           case 10:
                              var40 = 1;
                        }

                        if (var15) {
                           var40 = Math.max(1, var40 / 2);
                        }

                        var9 += var40 * var21;
                     }
                  }
               }
            }
         }

         if (StringUtils.isBlank(this.repairedItemName)) {
            if (var8.hasDisplayName()) {
               var11 = 1;
               var9 += var11;
               var12.clearCustomName();
            }
         } else if (!this.repairedItemName.equals(var8.getDisplayName())) {
            var11 = 1;
            var9 += var11;
            var12.setStackDisplayName(this.repairedItemName);
         }

         this.maximumCost = var10 + var9;
         if (var9 <= 0) {
            var12 = null;
         }

         if (var11 == var9 && var11 > 0 && this.maximumCost >= 40) {
            this.maximumCost = 39;
         }

         if (this.maximumCost >= 40 && !this.thePlayer.bA.isCreativeMode) {
            var12 = null;
         }

         if (var12 != null) {
            int var30 = var12.method_27845();
            if (var13 != null && var30 < var13.method_27845()) {
               var30 = var13.method_27845();
            }

            var30 = var30 * 2 + 1;
            var12.setRepairCost(var30);
            EnchantmentHelper.setEnchantments(var14, var12);
         }

         this.outputSlot.setInventorySlotContents(0, var12);
         this.detectAndSendChanges();
      }
   }

   public ContainerRepair(InventoryPlayer var1, World var2, EntityPlayer var3) {
      this(var1, var2, BlockPos.ORIGIN, var3);
   }
}
