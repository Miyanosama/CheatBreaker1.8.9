package net.minecraft.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.StatCollector;

public class ItemFireworkCharge extends Item {
   public static void addExplosionInfo(NBTTagCompound var0, List<String> var1) {
      int var2 = var0.getByte("Type");
      if (var2 >= 0 && var2 <= 4) {
         var1.add(StatCollector.translateToLocal("item.fireworksCharge.type." + var2).trim());
      } else {
         var1.add(StatCollector.translateToLocal("item.fireworksCharge.type").trim());
      }

      int[] var3 = var0.getIntArray("Colors");
      if (var3.length > 0) {
         boolean var4 = true;
         String var5 = "";

         for (int var9 : var3) {
            if (!var4) {
               var5 = var5 + ", ";
            }

            var4 = false;
            boolean var10 = false;

            for (int var11 = 0; var11 < ItemDye.dyeColors.length; var11++) {
               if (var9 == ItemDye.dyeColors[var11]) {
                  var10 = true;
                  var5 = var5 + StatCollector.translateToLocal("item.fireworksCharge." + EnumDyeColor.byDyeDamage(var11).getUnlocalizedName());
                  break;
               }
            }

            if (!var10) {
               var5 = var5 + StatCollector.translateToLocal("item.fireworksCharge.customColor");
            }
         }

         var1.add(var5);
      }

      int[] var13 = var0.getIntArray("FadeColors");
      if (var13.length > 0) {
         boolean var14 = true;
         String var16 = StatCollector.translateToLocal("item.fireworksCharge.fadeTo") + " ";

         for (int var21 : var13) {
            if (!var14) {
               var16 = var16 + ", ";
            }

            var14 = false;
            boolean var22 = false;

            for (int var12 = 0; var12 < 16; var12++) {
               if (var21 == ItemDye.dyeColors[var12]) {
                  var22 = true;
                  var16 = var16 + StatCollector.translateToLocal("item.fireworksCharge." + EnumDyeColor.byDyeDamage(var12).getUnlocalizedName());
                  break;
               }
            }

            if (!var22) {
               var16 = var16 + StatCollector.translateToLocal("item.fireworksCharge.customColor");
            }
         }

         var1.add(var16);
      }

      boolean var15 = var0.getBoolean("Trail");
      if (var15) {
         var1.add(StatCollector.translateToLocal("item.fireworksCharge.trail"));
      }

      boolean var17 = var0.getBoolean("Flicker");
      if (var17) {
         var1.add(StatCollector.translateToLocal("item.fireworksCharge.flicker"));
      }
   }

   public static NBTBase getExplosionTag(ItemStack var0, String var1) {
      if (var0.hasTagCompound()) {
         NBTTagCompound var2 = var0.getTagCompound().getCompoundTag("Explosion");
         if (var2 != null) {
            return var2.getTag(var1);
         }
      }

      return null;
   }

   @Override
   public void addInformation(ItemStack var1, EntityPlayer var2, List<String> var3, boolean var4) {
      if (var1.hasTagCompound()) {
         NBTTagCompound var5 = var1.getTagCompound().getCompoundTag("Explosion");
         if (var5 != null) {
            addExplosionInfo(var5, var3);
         }
      }
   }

   @Override
   public int getColorFromItemStack(ItemStack var1, int var2) {
      if (var2 != 1) {
         return super.getColorFromItemStack(var1, var2);
      } else {
         NBTBase var3 = getExplosionTag(var1, "Colors");
         if (!(var3 instanceof NBTTagIntArray)) {
            return 9079434;
         } else {
            NBTTagIntArray var4 = (NBTTagIntArray)var3;
            int[] var5 = var4.getIntArray();
            if (var5.length == 1) {
               return var5[0];
            } else {
               int var6 = 0;
               int var7 = 0;
               int var8 = 0;

               for (int var12 : var5) {
                  var6 += (var12 & 0xFF0000) >> 16;
                  var7 += (var12 & 0xFF00) >> 8;
                  var8 += (var12 & 0xFF) >> 0;
               }

               var6 /= var5.length;
               var7 /= var5.length;
               var8 /= var5.length;
               return var6 << 16 | var7 << 8 | var8;
            }
         }
      }
   }
}
