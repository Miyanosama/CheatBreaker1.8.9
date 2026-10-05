package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe$3;
import java.util.ArrayList;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class RecipeRepairItem implements IRecipe {
   public EnumParticleTypes field_0001;
   public EpollSocketChannel$EpollSocketUnsafe$3 field_0002;
   public BaseAttributeMap field_0000;

   @Override
   public ItemStack getCraftingResult(InventoryCrafting var1) {
      ArrayList var2 = Lists.newArrayList();

      for (int var3 = 0; var3 < var1.getSizeInventory(); var3++) {
         ItemStack var4 = var1.getStackInSlot(var3);
         if (var4 != null) {
            var2.add(var4);
            if (var2.size() > 1) {
               ItemStack var5 = (ItemStack)var2.get(0);
               if (var4.getItem() != var5.getItem() || var5.stackSize != 1 || var4.stackSize != 1 || !var5.getItem().isDamageable()) {
                  return null;
               }
            }
         }
      }

      if (var2.size() == 2) {
         ItemStack var10 = (ItemStack)var2.get(0);
         ItemStack var11 = (ItemStack)var2.get(1);
         if (var10.getItem() == var11.getItem() && var10.stackSize == 1 && var11.stackSize == 1 && var10.getItem().isDamageable()) {
            Item var12 = var10.getItem();
            int var6 = var12.getMaxDamage() - var10.getItemDamage();
            int var7 = var12.getMaxDamage() - var11.getItemDamage();
            int var8 = var6 + var7 + var12.getMaxDamage() * 5 / 100;
            int var9 = var12.getMaxDamage() - var8;
            if (var9 < 0) {
               var9 = 0;
            }

            return new ItemStack(var10.getItem(), 1, var9);
         }
      }

      return null;
   }

   @Override
   public boolean matches(InventoryCrafting var1, World var2) {
      ArrayList var3 = Lists.newArrayList();

      for (int var4 = 0; var4 < var1.getSizeInventory(); var4++) {
         ItemStack var5 = var1.getStackInSlot(var4);
         if (var5 != null) {
            var3.add(var5);
            if (var3.size() > 1) {
               ItemStack var6 = (ItemStack)var3.get(0);
               if (var5.getItem() != var6.getItem() || var6.stackSize != 1 || var5.stackSize != 1 || !var6.getItem().isDamageable()) {
                  return false;
               }
            }
         }
      }

      return var3.size() == 2;
   }

   @Override
   public ItemStack getRecipeOutput() {
      return null;
   }

   @Override
   public int getRecipeSize() {
      return 4;
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
}
