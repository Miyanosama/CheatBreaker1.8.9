package net.minecraft.inventory;

import io.netty.util.internal.chmv8.ForkJoinTask;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockRailBase$EnumRailDirection;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.optifine.http.HttpResponse;

public class ContainerEnchantment extends Container {
   public Random rand;
   public int[] enchantLevels;
   public ForkJoinTask field_0005;
   public BlockPos position;
   public BlockRailBase$EnumRailDirection field_0004;
   public int[] enchantmentIds;
   public int xpSeed;
   public IInventory tableInventory = new ContainerEnchantment$1(this, "Enchant", true, 2);
   public World worldPointer;
   public HttpResponse field_0003;

   @Override
   public void onContainerClosed(EntityPlayer var1) {
      super.onContainerClosed(var1);
      if (!this.worldPointer.D) {
         for (int var2 = 0; var2 < this.tableInventory.getSizeInventory(); var2++) {
            ItemStack var3 = this.tableInventory.removeStackFromSlot(var2);
            if (var3 != null) {
               var1.dropPlayerItemWithRandomChoice(var3, false);
            }
         }
      }
   }

   @Override
   public void updateProgressBar(int var1, int var2) {
      if (var1 >= 0 && var1 <= 2) {
         this.enchantLevels[var1] = var2;
      } else if (var1 == 3) {
         this.xpSeed = var2;
      } else if (var1 >= 4 && var1 <= 6) {
         this.enchantmentIds[var1 - 4] = var2;
      } else {
         super.updateProgressBar(var1, var2);
      }
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = this.c.get(var2);
      if (var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if (var2 == 0) {
            if (!this.mergeItemStack(var5, 2, 38, true)) {
               return null;
            }
         } else if (var2 == 1) {
            if (!this.mergeItemStack(var5, 2, 38, true)) {
               return null;
            }
         } else if (var5.getItem() == Items.dye && EnumDyeColor.byDyeDamage(var5.getMetadata()) == EnumDyeColor.BLUE) {
            if (!this.mergeItemStack(var5, 1, 2, true)) {
               return null;
            }
         } else {
            if (this.c.get(0).getHasStack() || !this.c.get(0).isItemValid(var5)) {
               return null;
            }

            if (var5.hasTagCompound() && var5.stackSize == 1) {
               this.c.get(0).putStack(var5.copy());
               var5.stackSize = 0;
            } else if (var5.stackSize >= 1) {
               this.c.get(0).putStack(new ItemStack(var5.getItem(), 1, var5.getMetadata()));
               var5.stackSize--;
            }
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
   public void onCraftGuiOpened(ICrafting var1) {
      super.onCraftGuiOpened(var1);
      var1.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
      var1.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
      var1.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
      var1.sendProgressBarUpdate(this, 3, this.xpSeed & -16);
      var1.sendProgressBarUpdate(this, 4, this.enchantmentIds[0]);
      var1.sendProgressBarUpdate(this, 5, this.enchantmentIds[1]);
      var1.sendProgressBarUpdate(this, 6, this.enchantmentIds[2]);
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for (int var1 = 0; var1 < this.e.size(); var1++) {
         ICrafting var2 = this.e.get(var1);
         var2.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
         var2.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
         var2.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
         var2.sendProgressBarUpdate(this, 3, this.xpSeed & -16);
         var2.sendProgressBarUpdate(this, 4, this.enchantmentIds[0]);
         var2.sendProgressBarUpdate(this, 5, this.enchantmentIds[1]);
         var2.sendProgressBarUpdate(this, 6, this.enchantmentIds[2]);
      }
   }

   public int getLapisAmount() {
      ItemStack var1 = this.tableInventory.getStackInSlot(1);
      return var1 == null ? 0 : var1.stackSize;
   }

   public ContainerEnchantment(InventoryPlayer var1, World var2) {
      this(var1, var2, BlockPos.ORIGIN);
   }

   @Override
   public void onCraftMatrixChanged(IInventory var1) {
      if (var1 == this.tableInventory) {
         ItemStack var2 = var1.getStackInSlot(0);
         if (var2 != null && var2.method_27884()) {
            if (!this.worldPointer.D) {
               int var7 = 0;

               for (int var4 = -1; var4 <= 1; var4++) {
                  for (int var5 = -1; var5 <= 1; var5++) {
                     if ((var4 != 0 || var5 != 0)
                        && this.worldPointer.isAirBlock(this.position.add(var5, 0, var4))
                        && this.worldPointer.isAirBlock(this.position.add(var5, 1, var4))) {
                        if (this.worldPointer.getBlockState(this.position.add(var5 * 2, 0, var4 * 2)).getBlock() == Blocks.bookshelf) {
                           var7++;
                        }

                        if (this.worldPointer.getBlockState(this.position.add(var5 * 2, 1, var4 * 2)).getBlock() == Blocks.bookshelf) {
                           var7++;
                        }

                        if (var5 != 0 && var4 != 0) {
                           if (this.worldPointer.getBlockState(this.position.add(var5 * 2, 0, var4)).getBlock() == Blocks.bookshelf) {
                              var7++;
                           }

                           if (this.worldPointer.getBlockState(this.position.add(var5 * 2, 1, var4)).getBlock() == Blocks.bookshelf) {
                              var7++;
                           }

                           if (this.worldPointer.getBlockState(this.position.add(var5, 0, var4 * 2)).getBlock() == Blocks.bookshelf) {
                              var7++;
                           }

                           if (this.worldPointer.getBlockState(this.position.add(var5, 1, var4 * 2)).getBlock() == Blocks.bookshelf) {
                              var7++;
                           }
                        }
                     }
                  }
               }

               this.rand.setSeed(this.xpSeed);

               for (int var8 = 0; var8 < 3; var8++) {
                  this.enchantLevels[var8] = EnchantmentHelper.calcItemStackEnchantability(this.rand, var8, var7, var2);
                  this.enchantmentIds[var8] = -1;
                  if (this.enchantLevels[var8] < var8 + 1) {
                     this.enchantLevels[var8] = 0;
                  }
               }

               for (int var9 = 0; var9 < 3; var9++) {
                  if (this.enchantLevels[var9] > 0) {
                     List var10 = this.func_178148_a(var2, var9, this.enchantLevels[var9]);
                     if (var10 != null && !var10.isEmpty()) {
                        EnchantmentData var6 = (EnchantmentData)var10.get(this.rand.nextInt(var10.size()));
                        this.enchantmentIds[var9] = var6.enchantmentobj.effectId | var6.enchantmentLevel << 8;
                     }
                  }
               }

               this.detectAndSendChanges();
            }
         } else {
            for (int var3 = 0; var3 < 3; var3++) {
               this.enchantLevels[var3] = 0;
               this.enchantmentIds[var3] = -1;
            }
         }
      }
   }

   public ContainerEnchantment(InventoryPlayer var1, World var2, BlockPos var3) {
      this.rand = new Random();
      this.enchantLevels = new int[3];
      this.enchantmentIds = new int[]{-1, -1, -1};
      this.worldPointer = var2;
      this.position = var3;
      this.xpSeed = var1.player.getXPSeed();
      this.a(new ContainerEnchantment$2(this, this.tableInventory, 0, 15, 47));
      this.a(new ContainerEnchantment$3(this, this.tableInventory, 1, 35, 47));

      for (int var4 = 0; var4 < 3; var4++) {
         for (int var5 = 0; var5 < 9; var5++) {
            this.a(new Slot(var1, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18));
         }
      }

      for (int var6 = 0; var6 < 9; var6++) {
         this.a(new Slot(var1, var6, 8 + var6 * 18, 142));
      }
   }

   public List<EnchantmentData> func_178148_a(ItemStack var1, int var2, int var3) {
      this.rand.setSeed(this.xpSeed + var2);
      List var4 = EnchantmentHelper.buildEnchantmentList(this.rand, var1, var3);
      if (var1.getItem() == Items.book && var4 != null && var4.size() > 1) {
         var4.remove(this.rand.nextInt(var4.size()));
      }

      return var4;
   }

   @Override
   public boolean enchantItem(EntityPlayer var1, int var2) {
      ItemStack var3 = this.tableInventory.getStackInSlot(0);
      ItemStack var4 = this.tableInventory.getStackInSlot(1);
      int var5 = var2 + 1;
      if ((var4 == null || var4.stackSize < var5) && !var1.bA.isCreativeMode) {
         return false;
      } else if (this.enchantLevels[var2] > 0 && var3 != null && (var1.bB >= var5 && var1.bB >= this.enchantLevels[var2] || var1.bA.isCreativeMode)) {
         if (!this.worldPointer.D) {
            List var6 = this.func_178148_a(var3, var2, this.enchantLevels[var2]);
            boolean var7 = var3.getItem() == Items.book;
            if (var6 != null) {
               var1.removeExperienceLevel(var5);
               if (var7) {
                  var3.setItem(Items.enchanted_book);
               }

               for (int var8 = 0; var8 < var6.size(); var8++) {
                  EnchantmentData var9 = (EnchantmentData)var6.get(var8);
                  if (var7) {
                     Items.enchanted_book.addEnchantment(var3, var9);
                  } else {
                     var3.addEnchantment(var9.enchantmentobj, var9.enchantmentLevel);
                  }
               }

               if (!var1.bA.isCreativeMode) {
                  var4.stackSize -= var5;
                  if (var4.stackSize <= 0) {
                     this.tableInventory.setInventorySlotContents(1, (ItemStack)null);
                  }
               }

               var1.triggerAchievement(StatList.field_181739_W);
               this.tableInventory.markDirty();
               this.xpSeed = var1.getXPSeed();
               this.onCraftMatrixChanged(this.tableInventory);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean canInteractWith(EntityPlayer var1) {
      return this.worldPointer.getBlockState(this.position).getBlock() != Blocks.enchanting_table
         ? false
         : var1.e(this.position.getX() + 0.5, this.position.getY() + 0.5, this.position.getZ() + 0.5) <= 64.0;
   }
}
