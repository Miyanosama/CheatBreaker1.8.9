package net.minecraft.item.crafting;

import io.netty.channel.local.LocalChannel$2;
import net.minecraft.command.server.CommandPardonPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBanner$EnumBannerPattern;
import net.minecraft.world.World;

public class RecipesBanners$RecipeAddPattern implements IRecipe {
   public LocalChannel$2 field_0000;
   public CommandPardonPlayer field_0001;

   @Override
   public int getRecipeSize() {
      return 10;
   }

   @Override
   public ItemStack getCraftingResult(InventoryCrafting var1) {
      ItemStack var2 = null;

      for (int var3 = 0; var3 < var1.getSizeInventory(); var3++) {
         ItemStack var4 = var1.getStackInSlot(var3);
         if (var4 != null && var4.getItem() == Items.banner) {
            var2 = var4.copy();
            var2.stackSize = 1;
            break;
         }
      }

      TileEntityBanner$EnumBannerPattern var8 = this.func_179533_c(var1);
      if (var8 != null) {
         int var9 = 0;

         for (int var5 = 0; var5 < var1.getSizeInventory(); var5++) {
            ItemStack var6 = var1.getStackInSlot(var5);
            if (var6 != null && var6.getItem() == Items.dye) {
               var9 = var6.getMetadata();
               break;
            }
         }

         NBTTagCompound var10 = var2.getSubCompound("BlockEntityTag", true);
         NBTTagList var11 = null;
         if (var10.hasKey("Patterns", 9)) {
            var11 = var10.getTagList("Patterns", 10);
         } else {
            var11 = new NBTTagList();
            var10.setTag("Patterns", var11);
         }

         NBTTagCompound var7 = new NBTTagCompound();
         var7.setString("Pattern", var8.getPatternID());
         var7.setInteger("Color", var9);
         var11.appendTag(var7);
      }

      return var2;
   }

   @Override
   public ItemStack getRecipeOutput() {
      return null;
   }

   @Override
   public boolean matches(InventoryCrafting var1, World var2) {
      boolean var3 = false;

      for (int var4 = 0; var4 < var1.getSizeInventory(); var4++) {
         ItemStack var5 = var1.getStackInSlot(var4);
         if (var5 != null && var5.getItem() == Items.banner) {
            if (var3) {
               return false;
            }

            if (TileEntityBanner.getPatterns(var5) >= 6) {
               return false;
            }

            var3 = true;
         }
      }

      return !var3 ? false : this.func_179533_c(var1) != null;
   }

   @Override
   public ItemStack[] getRemainingItems(InventoryCrafting var1) {
      ItemStack[] var2 = new ItemStack[var1.getSizeInventory()];

      for (int var3 = 0; var3 < var2.length; var3++) {
         ItemStack var4 = var1.getStackInSlot(var3);
         if (var4 != null && var4.getItem().hasContainerItem()) {
            var2[var3] = new ItemStack(var4.getItem().getContainerItem());
         }
      }

      return var2;
   }

   public RecipesBanners$RecipeAddPattern() {
   }

   public TileEntityBanner$EnumBannerPattern func_179533_c(InventoryCrafting var1) {
      for (TileEntityBanner$EnumBannerPattern var5 : TileEntityBanner$EnumBannerPattern.values()) {
         if (var5.hasValidCrafting()) {
            boolean var6 = true;
            if (var5.hasCraftingStack()) {
               boolean var7 = false;
               boolean var8 = false;

               for (int var9 = 0; var9 < var1.getSizeInventory() && var6; var9++) {
                  ItemStack var10 = var1.getStackInSlot(var9);
                  if (var10 != null && var10.getItem() != Items.banner) {
                     if (var10.getItem() == Items.dye) {
                        if (var8) {
                           var6 = false;
                           break;
                        }

                        var8 = true;
                     } else {
                        if (var7 || !var10.isItemEqual(var5.getCraftingStack())) {
                           var6 = false;
                           break;
                        }

                        var7 = true;
                     }
                  }
               }

               if (!var7) {
                  var6 = false;
               }
            } else if (var1.getSizeInventory() == var5.getCraftingLayers().length * var5.getCraftingLayers()[0].length()) {
               int var12 = -1;

               for (int var13 = 0; var13 < var1.getSizeInventory() && var6; var13++) {
                  int var14 = var13 / 3;
                  int var15 = var13 % 3;
                  ItemStack var11 = var1.getStackInSlot(var13);
                  if (var11 != null && var11.getItem() != Items.banner) {
                     if (var11.getItem() != Items.dye) {
                        var6 = false;
                        break;
                     }

                     if (var12 != -1 && var12 != var11.getMetadata()) {
                        var6 = false;
                        break;
                     }

                     if (var5.getCraftingLayers()[var14].charAt(var15) == ' ') {
                        var6 = false;
                        break;
                     }

                     var12 = var11.getMetadata();
                  } else if (var5.getCraftingLayers()[var14].charAt(var15) != ' ') {
                     var6 = false;
                     break;
                  }
               }
            } else {
               var6 = false;
            }

            if (var6) {
               return var5;
            }
         }
      }

      return null;
   }
}
