package org.apache.log4j.pattern;

import javazoom.jl.converter.WaveFile;
import net.minecraft.client.resources.SkinManager$2;
import org.apache.log4j.spi.LoggingEvent;

public class LiteralPatternConverter extends LoggingEventPatternConverter {
   public SkinManager$2 field_0001;
   public String literal;
   public WaveFile field_0000;

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(this.literal);
   }

   public void format(Object var1, StringBuffer var2) {
      var2.append(this.literal);
   }

   public LiteralPatternConverter(String var1) {
      super("Literal", "literal");
      this.literal = var1;
   }
}
