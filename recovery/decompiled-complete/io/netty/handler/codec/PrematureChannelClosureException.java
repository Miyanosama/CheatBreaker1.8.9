package io.netty.handler.codec;

import com.cheatbreaker.client.ui.util.HudUtil;
import io.netty.channel.socket.nio.NioSocketChannel$NioSocketChannelConfig;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import net.minecraft.block.BlockBanner$1;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;

public class PrematureChannelClosureException extends CodecException {
   public ObjectEncoder __junk4865181661979405917;
   public HudUtil __junk2030070576444945205;
   public LogLevel __junk6025656436127488298;
   public BlockBanner$1 __junk2814136262635825237;
   public NioSocketChannel$NioSocketChannelConfig __junk2412704081869273510;
   public static long serialVersionUID;
   public LayerSheepWool __junk3287967822303834621;

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
