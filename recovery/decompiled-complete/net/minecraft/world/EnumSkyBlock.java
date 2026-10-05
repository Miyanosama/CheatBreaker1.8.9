package net.minecraft.world;

import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.lf5.LogRecord;

public enum EnumSkyBlock {
   SKY(15),
   BLOCK(0);
   public FormattingInfo field_0003;
   public int defaultLightValue;
   // $VF: synthetic field
   public static EnumSkyBlock[] $VALUES = new EnumSkyBlock[]{SKY, EnumSkyBlock.BLOCK};
   public LogRecord field_0000;

   public EnumSkyBlock(int var3) {
      this.defaultLightValue = var3;
   }
}
