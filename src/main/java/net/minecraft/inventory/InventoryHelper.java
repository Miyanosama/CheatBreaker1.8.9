package net.minecraft.inventory;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InventoryHelper {
   public static Random RANDOM = new Random();

   public static void dropInventoryItems(World var0, double var1, double var3, double var5, IInventory var7) {
      for (int var8 = 0; var8 < var7.getSizeInventory(); var8++) {
         ItemStack var9 = var7.getStackInSlot(var8);
         if (var9 != null) {
            spawnItemStack(var0, var1, var3, var5, var9);
         }
      }
   }

   public static void dropInventoryItems(World var0, Entity var1, IInventory var2) {
      dropInventoryItems(var0, var1.s, var1.t, var1.u, var2);
   }

   public static void dropInventoryItems(World var0, BlockPos var1, IInventory var2) {
      dropInventoryItems(var0, var1.getX(), var1.getY(), var1.getZ(), var2);
   }

   public static void spawnItemStack(World var0, double var1, double var3, double var5, ItemStack var7) {
      float var8 = RANDOM.nextFloat() * 0.8F + 0.1F;
      float var9 = RANDOM.nextFloat() * 0.8F + 0.1F;
      float var10 = RANDOM.nextFloat() * 0.8F + 0.1F;

      while (var7.stackSize > 0) {
         int var11 = RANDOM.nextInt(21) + 10;
         if (var11 > var7.stackSize) {
            var11 = var7.stackSize;
         }

         var7.stackSize -= var11;
         EntityItem var12 = new EntityItem(var0, var1 + var8, var3 + var9, var5 + var10, new ItemStack(var7.getItem(), var11, var7.getMetadata()));
         if (var7.hasTagCompound()) {
            var12.getEntityItem().setTagCompound((NBTTagCompound)var7.getTagCompound().copy());
         }

         float var13 = 0.05F;
         var12.v = RANDOM.nextGaussian() * var13;
         var12.w = RANDOM.nextGaussian() * var13 + 0.2F;
         var12.x = RANDOM.nextGaussian() * var13;
         var0.spawnEntityInWorld(var12);
      }
   }
}
