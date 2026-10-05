package io.netty.util.internal.logging;

import io.netty.channel.DefaultChannelHandlerContext;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.entity.RenderOcelot;
import org.apache.commons.logging.LogFactory;

public class CommonsLoggerFactory extends InternalLoggerFactory {
   public Map<String, InternalLogger> loggerMap = new HashMap<>();

   @Override
   public InternalLogger newInstance(String var1) {
      return new CommonsLogger(LogFactory.getLog(var1), var1);
   }
}
