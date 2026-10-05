package org.apache.log4j.pattern;

import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.spdy.DefaultSpdySettingsFrame$Setting;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapEntry;
import java.util.Date;
import javax.vecmath.Vector4f;
import net.minecraft.entity.item.EntityItem;
import recovered.unidentified.UnidentifiedClass1381;

public class IntegerPatternConverter extends PatternConverter {
   public UnidentifiedClass1381 field_0003;
   public Vector4f field_0005;
   public ConcurrentHashMapV8$MapEntry field_0002;
   public CombinedChannelDuplexHandler field_0004;
   public static IntegerPatternConverter INSTANCE = new IntegerPatternConverter();
   public EntityItem field_0001;
   public DefaultSpdySettingsFrame$Setting field_0006;

   public static IntegerPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }

   public void format(Object var1, StringBuffer var2) {
      if (var1 instanceof Integer) {
         var2.append(var1.toString());
      }

      if (var1 instanceof Date) {
         var2.append(Long.toString(((Date)var1).getTime()));
      }
   }

   public IntegerPatternConverter() {
      super("Integer", "integer");
   }
}
