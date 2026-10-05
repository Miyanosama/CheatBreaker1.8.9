package io.netty.channel.sctp.nio;

import com.sun.nio.sctp.SctpChannel;
import io.netty.channel.sctp.DefaultSctpChannelConfig;
import io.netty.util.concurrent.SingleThreadEventExecutor$5;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.optifine.entity.model.ModelAdapterBlaze;
import net.optifine.http.HttpPipelineSender;

public class NioSctpChannel$NioSctpChannelConfig extends DefaultSctpChannelConfig {
   public S19PacketEntityHeadLook __junk2088626069329402618;
   public SingleThreadEventExecutor$5 __junk3042577294298291400;
   public ModelAdapterBlaze __junk7904896360076561864;
   public HttpPipelineSender __junk3480788182401451239;

   public NioSctpChannel$NioSctpChannelConfig(NioSctpChannel var1, NioSctpChannel var2, SctpChannel var3) {
      this.this$0 = var1;
      super(var2, var3);
   }

   @Override
   public void autoReadCleared() {
      NioSctpChannel.access$100(this.this$0, false);
   }
}
