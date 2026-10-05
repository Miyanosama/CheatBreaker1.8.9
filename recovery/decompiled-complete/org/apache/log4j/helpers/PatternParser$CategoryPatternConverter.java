package org.apache.log4j.helpers;

import net.minecraft.client.renderer.tileentity.TileEntityEnderChestRenderer;
import net.minecraft.entity.monster.EntityGhast$AIFireballAttack;
import org.apache.log4j.spi.LoggingEvent;

public class PatternParser$CategoryPatternConverter extends PatternParser$NamedPatternConverter {
   public PatternParser this$0;
   public TileEntityEnderChestRenderer field_0002;
   public EntityGhast$AIFireballAttack field_0000;

   public PatternParser$CategoryPatternConverter(PatternParser var1, FormattingInfo var2, int var3) {
      this.this$0 = var1;
      super(var2, var3);
   }

   public String getFullyQualifiedName(LoggingEvent var1) {
      return var1.getLoggerName();
   }
}
