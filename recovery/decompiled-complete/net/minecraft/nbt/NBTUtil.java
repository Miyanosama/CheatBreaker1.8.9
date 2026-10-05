package net.minecraft.nbt;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import net.minecraft.util.StringUtils;
import net.optifine.gui.GuiAnimationSettingsOF;

public class NBTUtil {
   public GuiAnimationSettingsOF field_0000;

   public static GameProfile readGameProfileFromNBT(NBTTagCompound var0) {
      String var1 = null;
      String var2 = null;
      if (var0.hasKey("Name", 8)) {
         var1 = var0.getString("Name");
      }

      if (var0.hasKey("Id", 8)) {
         var2 = var0.getString("Id");
      }

      if (StringUtils.isNullOrEmpty(var1) && StringUtils.isNullOrEmpty(var2)) {
         return null;
      } else {
         UUID var3;
         try {
            var3 = UUID.fromString(var2);
         } catch (Throwable var12) {
            var3 = null;
         }

         GameProfile var4 = new GameProfile(var3, var1);
         if (var0.hasKey("Properties", 10)) {
            NBTTagCompound var5 = var0.getCompoundTag("Properties");

            for (String var7 : var5.getKeySet()) {
               NBTTagList var8 = var5.getTagList(var7, 10);

               for (int var9 = 0; var9 < var8.tagCount(); var9++) {
                  NBTTagCompound var10 = var8.getCompoundTagAt(var9);
                  String var11 = var10.getString("Value");
                  if (var10.hasKey("Signature", 8)) {
                     var4.getProperties().put(var7, new Property(var7, var11, var10.getString("Signature")));
                  } else {
                     var4.getProperties().put(var7, new Property(var7, var11));
                  }
               }
            }
         }

         return var4;
      }
   }

   public static NBTTagCompound writeGameProfile(NBTTagCompound var0, GameProfile var1) {
      if (!StringUtils.isNullOrEmpty(var1.getName())) {
         var0.setString("Name", var1.getName());
      }

      if (var1.getId() != null) {
         var0.setString("Id", var1.getId().toString());
      }

      if (!var1.getProperties().isEmpty()) {
         NBTTagCompound var2 = new NBTTagCompound();

         for (String var4 : var1.getProperties().keySet()) {
            NBTTagList var5 = new NBTTagList();

            for (Property var7 : var1.getProperties().get(var4)) {
               NBTTagCompound var8 = new NBTTagCompound();
               var8.setString("Value", var7.getValue());
               if (var7.hasSignature()) {
                  var8.setString("Signature", var7.getSignature());
               }

               var5.appendTag(var8);
            }

            var2.setTag(var4, var5);
         }

         var0.setTag("Properties", var2);
      }

      return var0;
   }

   public static boolean func_181123_a(NBTBase var0, NBTBase var1, boolean var2) {
      if (var0 == var1) {
         return true;
      } else if (var0 == null) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (!var0.getClass().equals(var1.getClass())) {
         return false;
      } else if (var0 instanceof NBTTagCompound) {
         NBTTagCompound var9 = (NBTTagCompound)var0;
         NBTTagCompound var10 = (NBTTagCompound)var1;

         for (String var12 : var9.getKeySet()) {
            NBTBase var13 = var9.getTag(var12);
            if (!func_181123_a(var13, var10.getTag(var12), var2)) {
               return false;
            }
         }

         return true;
      } else if (var0 instanceof NBTTagList && var2) {
         NBTTagList var3 = (NBTTagList)var0;
         NBTTagList var4 = (NBTTagList)var1;
         if (var3.tagCount() == 0) {
            return var4.tagCount() == 0;
         } else {
            for (int var5 = 0; var5 < var3.tagCount(); var5++) {
               NBTBase var6 = var3.get(var5);
               boolean var7 = false;

               for (int var8 = 0; var8 < var4.tagCount(); var8++) {
                  if (func_181123_a(var6, var4.get(var8), var2)) {
                     var7 = true;
                     break;
                  }
               }

               if (!var7) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return var0.equals(var1);
      }
   }
}
