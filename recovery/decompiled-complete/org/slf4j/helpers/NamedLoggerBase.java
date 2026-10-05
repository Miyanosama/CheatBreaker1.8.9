package org.slf4j.helpers;

import java.io.Serializable;
import net.minecraft.client.renderer.entity.RenderTntMinecart;
import net.minecraft.inventory.SlotCrafting;
import org.apache.log4j.pattern.PatternParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NamedLoggerBase implements Serializable, Logger {
   public static long field_0004;
   public SlotCrafting field_0000;
   public String name;
   public RenderTntMinecart field_0003;
   public PatternParser field_0001;

   public Object readResolve() {
      return LoggerFactory.getLogger(this.getName());
   }

   @Override
   public String getName() {
      return this.name;
   }
}
