package org.apache.log4j.pattern;

import net.minecraft.block.BlockSkull$1;
import net.optifine.shaders.uniform.CustomUniforms;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$17;

public class FileDatePatternConverter {
   public CustomUniforms field_0001;
   public LogBrokerMonitor$17 field_0002;
   public BlockSkull$1 field_0000;

   public static PatternConverter newInstance(String[] var0) {
      return var0 != null && var0.length != 0 ? DatePatternConverter.newInstance(var0) : DatePatternConverter.newInstance(new String[]{"yyyy-MM-dd"});
   }
}
