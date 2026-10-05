package net.minecraft.client;

import net.minecraft.entity.item.EntityMinecart$EnumMinecartType;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;

// $VF: synthetic class
public class Minecraft$10 {
   static {
      try {
         field_183017_b[EntityMinecart$EnumMinecartType.FURNACE.ordinal()] = 1;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_183017_b[EntityMinecart$EnumMinecartType.CHEST.ordinal()] = 2;
      } catch (NoSuchFieldError var7) {
      }

      try {
         field_183017_b[EntityMinecart$EnumMinecartType.TNT.ordinal()] = 3;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_183017_b[EntityMinecart$EnumMinecartType.HOPPER.ordinal()] = 4;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_183017_b[EntityMinecart$EnumMinecartType.COMMAND_BLOCK.ordinal()] = 5;
      } catch (NoSuchFieldError var4) {
      }

      field_183016_a = new int[MovingObjectPosition$MovingObjectType.values().length];

      try {
         field_183016_a[MovingObjectPosition$MovingObjectType.ENTITY.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_183016_a[MovingObjectPosition$MovingObjectType.BLOCK.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_183016_a[MovingObjectPosition$MovingObjectType.MISS.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
