package net.optifine.util;

import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldNameable;
import net.optifine.reflect.Reflector;

public class TileEntityUtils {
   public static String getTileEntityName(IBlockAccess var0, BlockPos var1) {
      TileEntity var2 = var0.getTileEntity(var1);
      return getTileEntityName(var2);
   }

   public static String getTileEntityName(TileEntity var0) {
      if (!(var0 instanceof IWorldNameable)) {
         return null;
      } else {
         IWorldNameable var1 = (IWorldNameable)var0;
         updateTileEntityName(var0);
         return !var1.u_() ? null : var1.z_();
      }
   }

   public static String getServerTileEntityRawName(BlockPos var0) {
      TileEntity var1 = IntegratedServerUtils.getTileEntity(var0);
      return var1 == null ? null : getTileEntityRawName(var1);
   }

   public static void updateTileEntityName(TileEntity var0) {
      BlockPos var1 = var0.v();
      String var2 = getTileEntityRawName(var0);
      if (var2 == null) {
         String var3 = getServerTileEntityRawName(var1);
         var3 = Config.normalize(var3);
         setTileEntityRawName(var0, var3);
      }
   }

   public static String getTileEntityRawName(TileEntity var0) {
      if (var0 instanceof TileEntityBeacon) {
         return (String)Reflector.getFieldValue(var0, Reflector.TileEntityBeacon_customName);
      } else if (var0 instanceof TileEntityBrewingStand) {
         return (String)Reflector.getFieldValue(var0, Reflector.TileEntityBrewingStand_customName);
      } else if (var0 instanceof TileEntityEnchantmentTable) {
         return (String)Reflector.getFieldValue(var0, Reflector.TileEntityEnchantmentTable_customName);
      } else if (var0 instanceof TileEntityFurnace) {
         return (String)Reflector.getFieldValue(var0, Reflector.TileEntityFurnace_customName);
      } else {
         if (var0 instanceof IWorldNameable) {
            IWorldNameable var1 = (IWorldNameable)var0;
            if (var1.u_()) {
               return var1.z_();
            }
         }

         return null;
      }
   }

   public static boolean setTileEntityRawName(TileEntity var0, String var1) {
      if (var0 instanceof TileEntityBeacon) {
         return Reflector.setFieldValue(var0, Reflector.TileEntityBeacon_customName, var1);
      } else if (var0 instanceof TileEntityBrewingStand) {
         return Reflector.setFieldValue(var0, Reflector.TileEntityBrewingStand_customName, var1);
      } else if (var0 instanceof TileEntityEnchantmentTable) {
         return Reflector.setFieldValue(var0, Reflector.TileEntityEnchantmentTable_customName, var1);
      } else if (var0 instanceof TileEntityFurnace) {
         return Reflector.setFieldValue(var0, Reflector.TileEntityFurnace_customName, var1);
      } else if (var0 instanceof TileEntityChest) {
         ((TileEntityChest)var0).setCustomName(var1);
         return true;
      } else if (var0 instanceof TileEntityDispenser) {
         ((TileEntityDispenser)var0).setCustomName(var1);
         return true;
      } else if (var0 instanceof TileEntityHopper) {
         ((TileEntityHopper)var0).setCustomName(var1);
         return true;
      } else {
         return false;
      }
   }
}
