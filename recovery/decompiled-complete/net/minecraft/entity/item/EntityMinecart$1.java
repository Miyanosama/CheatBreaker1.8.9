package net.minecraft.entity.item;

import net.minecraft.block.BlockRailBase$EnumRailDirection;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import recovered.unidentified.UnidentifiedClass5128;

// $VF: synthetic class
public class EntityMinecart$1 {
   public S20PacketEntityProperties field_0001;
   public UnidentifiedClass5128 field_0002;

   static {
      try {
         field_180036_b[BlockRailBase$EnumRailDirection.ASCENDING_EAST.ordinal()] = 1;
      } catch (NoSuchFieldError var10) {
      }

      try {
         field_180036_b[BlockRailBase$EnumRailDirection.ASCENDING_WEST.ordinal()] = 2;
      } catch (NoSuchFieldError var9) {
      }

      try {
         field_180036_b[BlockRailBase$EnumRailDirection.ASCENDING_NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_180036_b[BlockRailBase$EnumRailDirection.ASCENDING_SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var7) {
      }

      field_180037_a = new int[EntityMinecart$EnumMinecartType.values().length];

      try {
         field_180037_a[EntityMinecart$EnumMinecartType.CHEST.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_180037_a[EntityMinecart$EnumMinecartType.FURNACE.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_180037_a[EntityMinecart$EnumMinecartType.TNT.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_180037_a[EntityMinecart$EnumMinecartType.SPAWNER.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_180037_a[EntityMinecart$EnumMinecartType.HOPPER.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_180037_a[EntityMinecart$EnumMinecartType.COMMAND_BLOCK.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
