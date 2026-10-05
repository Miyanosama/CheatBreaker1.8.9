package io.netty.util.internal.logging;

import io.netty.channel.DefaultChannelHandlerContext;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.BlockVine$1;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.init.Bootstrap$12$1;
import org.apache.commons.logging.LogFactory;

public class CommonsLoggerFactory extends InternalLoggerFactory {
   public RenderOcelot __junk5665610825926411563;
   public Bootstrap$12$1 __junk7532895227829742403;
   public Map<String, InternalLogger> loggerMap = new HashMap<>();
   public BlockVine$1 __junk1021890348182596951;
   public DefaultChannelHandlerContext __junk8523935332768106605;

   @Override
   public InternalLogger newInstance(String var1) {
      return new CommonsLogger(LogFactory.getLog(var1), var1);
   }
}
