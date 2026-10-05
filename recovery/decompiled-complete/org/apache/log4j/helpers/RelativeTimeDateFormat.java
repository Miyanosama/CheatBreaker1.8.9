package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import net.minecraft.inventory.ContainerRepair$2;
import net.optifine.util.MathUtilsTest$OPER;

public class RelativeTimeDateFormat extends DateFormat {
   public MathUtilsTest$OPER field_0001;
   public long startTime = System.currentTimeMillis();
   public static long field_0000;
   public ContainerRepair$2 field_0002;

   public Date parse(String var1, ParsePosition var2) {
      return null;
   }

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      return var2.append(var1.getTime() - this.startTime);
   }
}
