package net.minecraft.world.border;

public enum EnumBorderStatus {
      GROWING(4259712),
      SHRINKING(16724016),
      STATIONARY(2138367);
   public static EnumBorderStatus[] $VALUES = new EnumBorderStatus[]{EnumBorderStatus.GROWING, EnumBorderStatus.SHRINKING, STATIONARY};
   public int id;

   public int getID() {
      return this.id;
   }

   EnumBorderStatus(int var3) {
      this.id = var3;
   }
}
