package net.minecraft.block;

import net.minecraft.util.IStringSerializable;

public enum BlockDoor$EnumDoorHalf implements IStringSerializable {
   LOWER,
   UPPER;

   @Override
   public String toString() {
      return this.getName();
   }

   @Override
   public String getName() {
      return this == UPPER ? "upper" : "lower";
   }
}
