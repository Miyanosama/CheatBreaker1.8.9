package io.netty.handler.codec;

import com.cheatbreaker.client.module.AbstractModule;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.DefaultProgressivePromise;
import java.util.List;
import net.minecraft.crash.CrashReportCategory$Entry;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget$1;

public class MessageToMessageCodec$2 extends MessageToMessageDecoder<Object> {
   public CrashReportCategory$Entry __junk6991541740680868739;
   public EntityAINearestAttackableTarget$1 __junk5917349843320377800;
   public AbstractModule __junk2460369814708627604;
   public DefaultProgressivePromise __junk8667655580943818415;

   public MessageToMessageCodec$2(MessageToMessageCodec var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void decode(ChannelHandlerContext var1, Object var2, List<Object> var3) {
      this.this$0.decode(var1, var2, var3);
   }

   @Override
   public boolean acceptInboundMessage(Object var1) {
      return this.this$0.acceptInboundMessage(var1);
   }
}
