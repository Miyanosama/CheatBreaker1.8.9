package org.apache.log4j.spi;

import net.minecraft.entity.monster.EntityCreeper;

public abstract class Filter implements OptionHandler {
   public static int field_0002;
   public static int field_0004;
   public Filter next;
   public EntityCreeper field_0003;
   public static int field_0000;

   public void activateOptions() {
   }

   public void setNext(Filter var1) {
      this.next = var1;
   }

   public abstract int decide(LoggingEvent var1);

   public Filter getNext() {
      return this.next;
   }
}
