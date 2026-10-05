package io.netty.handler.codec;

import com.cheatbreaker.client.ui.util.HudUtil;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;

public class PrematureChannelClosureException extends CodecException {
   public static final long serialVersionUID = 4907642202594703094L;

   public PrematureChannelClosureException() {
   }

   public PrematureChannelClosureException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public PrematureChannelClosureException(Throwable var1) {
      super(var1);
   }

   public PrematureChannelClosureException(String var1) {
      super(var1);
   }
}
