package org.apache.log4j.helpers;

import net.minecraft.client.audio.SoundHandler$1;
import net.minecraft.client.renderer.BlockFluidRenderer;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.world.gen.FlatLayerInfo;
import net.minecraft.world.gen.structure.StructureComponent;
import org.apache.log4j.spi.LoggingEvent;

public abstract class PatternParser$NamedPatternConverter extends PatternConverter {
   public StructureComponent field_0002;
   public FlatLayerInfo field_0003;
   public RealmsBridge field_0001;
   public int precision;
   public BlockFluidRenderer field_0005;
   public SoundHandler$1 field_0000;

   public abstract String getFullyQualifiedName(LoggingEvent var1);

   public String convert(LoggingEvent var1) {
      String var2 = this.getFullyQualifiedName(var1);
      if (this.precision <= 0) {
         return var2;
      } else {
         int var3 = var2.length();
         int var4 = var3 - 1;

         for (int var5 = this.precision; var5 > 0; var5--) {
            var4 = var2.lastIndexOf(46, var4 - 1);
            if (var4 == -1) {
               return var2;
            }
         }

         return var2.substring(var4 + 1, var3);
      }
   }

   public PatternParser$NamedPatternConverter(FormattingInfo var1, int var2) {
      super(var1);
      this.precision = var2;
   }
}
