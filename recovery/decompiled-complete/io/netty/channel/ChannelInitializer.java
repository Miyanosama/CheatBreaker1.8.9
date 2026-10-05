package io.netty.channel;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$WingRoom;

public abstract class ChannelInitializer<C extends Channel> extends ChannelInboundHandlerAdapter {
   public StructureOceanMonumentPieces$WingRoom __junk1741514558538808230;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ChannelInitializer.class);

   public abstract void initChannel(C var1);

   @Override
   public void channelRegistered(ChannelHandlerContext var1) {
      ChannelPipeline var2 = var1.pipeline();
      boolean var3 = false;

      try {
         this.initChannel((C)var1.channel());
         var2.remove(this);
         var1.fireChannelRegistered();
         var3 = true;
      } catch (Throwable var8) {
         logger.warn("Failed to initialize a channel. Closing: " + var1.channel(), var8);
      } finally {
         if (var2.context(this) != null) {
            var2.remove(this);
         }

         if (!var3) {
            var1.close();
         }
      }
   }
}
