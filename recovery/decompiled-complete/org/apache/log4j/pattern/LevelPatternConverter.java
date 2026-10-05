package org.apache.log4j.pattern;

import net.minecraft.client.renderer.BlockModelRenderer$EnumNeighborInfo;
import net.minecraft.client.renderer.entity.RenderItem$6;
import net.minecraft.item.ItemHoe;
import org.apache.log4j.lf5.viewer.LogFactor5InputDialog$1;
import org.apache.log4j.spi.LoggingEvent;

public class LevelPatternConverter extends LoggingEventPatternConverter {
   public BlockModelRenderer$EnumNeighborInfo field_0003;
   public RenderItem$6 field_0005;
   public LogFactor5InputDialog$1 field_0002;
   public static LevelPatternConverter INSTANCE = new LevelPatternConverter();
   public ItemHoe field_0000;
   public static int field_0001;

   public LevelPatternConverter() {
      super("Level", "level");
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(var1.getLevel().toString());
   }

   public String getStyleClass(Object var1) {
      if (var1 instanceof LoggingEvent) {
         int var2 = ((LoggingEvent)var1).getLevel().toInt();
         switch (var2) {
            case 5000:
               return "level trace";
            case 10000:
               return "level debug";
            case 20000:
               return "level info";
            case 30000:
               return "level warn";
            case 40000:
               return "level error";
            case 50000:
               return "level fatal";
            default:
               return "level " + ((LoggingEvent)var1).getLevel().toString();
         }
      } else {
         return "level";
      }
   }

   public static LevelPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }
}
