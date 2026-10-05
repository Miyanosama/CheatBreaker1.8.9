package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import io.netty.util.internal.EmptyArrays;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.init.Bootstrap$5;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShapelessRecipes implements IRecipe {
   public Bootstrap$5 field_0002;
   public RenderXPOrb field_0004;
   public List<ItemStack> recipeItems;
   public EmptyArrays field_0003;
   public ItemStack recipeOutput;

   @Override
   public int getRecipeSize() {
      return this.recipeItems.size();
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

   @Override
   public boolean matches(InventoryCrafting var1, World var2) {
      ArrayList var3 = Lists.newArrayList(this.recipeItems);

      for (int var4 = 0; var4 < var1.getHeight(); var4++) {
         for (int var5 = 0; var5 < var1.getWidth(); var5++) {
            ItemStack var6 = var1.getStackInRowAndColumn(var5, var4);
            if (var6 != null) {
               boolean var7 = false;

               for (ItemStack var9 : var3) {
                  if (var6.getItem() == var9.getItem() && (var9.getMetadata() == 32767 || var6.getMetadata() == var9.getMetadata())) {
                     var7 = true;
                     var3.remove(var9);
                     break;
                  }
               }

               if (!var7) {
                  return false;
               }
            }
         }
      }

      return var3.isEmpty();
   }

   @Override
   public ItemStack getCraftingResult(InventoryCrafting var1) {
      return this.recipeOutput.copy();
   }

   public ShapelessRecipes(ItemStack var1, List<ItemStack> var2) {
      this.recipeOutput = var1;
      this.recipeItems = var2;
   }

   @Override
   public ItemStack getRecipeOutput() {
      return this.recipeOutput;
   }
}
