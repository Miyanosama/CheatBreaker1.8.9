package net.minecraft.item.crafting;

import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.world.World;
import org.apache.log4j.pattern.LineLocationPatternConverter;

public class RecipesBanners$RecipeDuplicatePattern implements IRecipe {
   public ChunkCompileTaskGenerator field_0001;
   public LineLocationPatternConverter field_0002;
   public WebSocket08FrameDecoder$State field_0000;

   @Override
   public int getRecipeSize() {
      return 2;
   }

   @Override
   public ItemStack getCraftingResult(InventoryCrafting var1) {
      for (int var2 = 0; var2 < var1.getSizeInventory(); var2++) {
         ItemStack var3 = var1.getStackInSlot(var2);
         if (var3 != null && TileEntityBanner.getPatterns(var3) > 0) {
            ItemStack var4 = var3.copy();
            var4.stackSize = 1;
            return var4;
         }
      }

      return null;
   }

   @Override
   public ItemStack[] getRemainingItems(InventoryCrafting var1) {
      ItemStack[] var2 = new ItemStack[var1.getSizeInventory()];

      for (int var3 = 0; var3 < var2.length; var3++) {
         ItemStack var4 = var1.getStackInSlot(var3);
         if (var4 != null) {
            if (var4.getItem().hasContainerItem()) {
               var2[var3] = new ItemStack(var4.getItem().getContainerItem());
            } else if (var4.hasTagCompound() && TileEntityBanner.getPatterns(var4) > 0) {
               var2[var3] = var4.copy();
               var2[var3].stackSize = 1;
            }
         }
      }

      return var2;
   }

   @Override
   public ItemStack getRecipeOutput() {
      return null;
   }

   @Override
   public boolean matches(InventoryCrafting var1, World var2) {
      ItemStack var3 = null;
      ItemStack var4 = null;

      for (int var5 = 0; var5 < var1.getSizeInventory(); var5++) {
         ItemStack var6 = var1.getStackInSlot(var5);
         if (var6 != null) {
            if (var6.getItem() != Items.banner) {
               return false;
            }

            if (var3 != null && var4 != null) {
               return false;
            }

            int var7 = TileEntityBanner.method_24268(var6);
            boolean var8 = TileEntityBanner.getPatterns(var6) > 0;
            if (var3 != null) {
               if (var8) {
                  return false;
               }

               if (var7 != TileEntityBanner.method_24268(var3)) {
                  return false;
               }

               var4 = var6;
            } else if (var4 != null) {
               if (!var8) {
                  return false;
               }

               if (var7 != TileEntityBanner.method_24268(var4)) {
                  return false;
               }

               var3 = var6;
            } else if (var8) {
               var3 = var6;
            } else {
               var4 = var6;
            }
         }
      }

      return var3 != null && var4 != null;
   }

   public RecipesBanners$RecipeDuplicatePattern() {
   }
}
