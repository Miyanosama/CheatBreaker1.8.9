package net.minecraft.stats;

import org.apache.log4j.helpers.DateTimeDateFormat;

public class StatBase$1 implements IStatType {
   public DateTimeDateFormat field_0000;

   @Override
   public String format(int var1) {
      return StatBase.access$000().format((long)var1);
   }
}
