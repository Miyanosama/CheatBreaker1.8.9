package net.minecraft.item;

import net.minecraft.block.BlockDirt$DirtType;
import net.minecraft.client.gui.GuiFlatPresets$LayerItem;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.lf5.LF5Appender;
import recovered.unidentified.UnidentifiedClass4731;

// $VF: synthetic class
public class ItemHoe$1 {
   public LF5Appender field_0002;
   public DailyRollingFileAppender field_0004;
   public GuiFlatPresets$LayerItem field_0001;
   public UnidentifiedClass4731 field_0003;

   static {
      try {
         field_179590_a[BlockDirt$DirtType.DIRT.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_179590_a[BlockDirt$DirtType.COARSE_DIRT.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
