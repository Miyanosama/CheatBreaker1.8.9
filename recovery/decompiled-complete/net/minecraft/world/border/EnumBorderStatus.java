package net.minecraft.world.border;

import net.minecraft.world.chunk.Chunk$EnumCreateEntityType;

public enum EnumBorderStatus {
   STATIONARY(2138367),
   GROWING(4259712),
   SHRINKING(16724016);

   // $VF: synthetic field
   public static EnumBorderStatus[] $VALUES = new EnumBorderStatus[]{EnumBorderStatus.GROWING, EnumBorderStatus.SHRINKING, STATIONARY};
   public int id;
   public Chunk$EnumCreateEntityType field_0001;

   public int getID() {
      return this.id;
   }

   public EnumBorderStatus(int var3) {
      this.id = var3;
   }
}
