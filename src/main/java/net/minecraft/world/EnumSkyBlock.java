package net.minecraft.world;

public enum EnumSkyBlock {
   SKY(15),
   BLOCK(0);
   public int defaultLightValue;
   public static EnumSkyBlock[] $VALUES = new EnumSkyBlock[]{SKY, EnumSkyBlock.BLOCK};

   EnumSkyBlock(int var3) {
      this.defaultLightValue = var3;
   }
}
