package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.block.BlockContainer;
import net.optifine.config.VillagerProfession;

public class ByteToMessageCodec$Encoder extends MessageToByteEncoder<I> {
   public VillagerProfession __junk1402638109261262983;
   public BlockContainer __junk7523129071809901563;

   @Override
   public boolean acceptOutboundMessage(Object var1) {
      return this.this$0.acceptOutboundMessage(var1);
   }

   @Override
   public void encode(ChannelHandlerContext var1, I var2, ByteBuf var3) {
      this.this$0.encode(var1, var2, var3);
   }

   public ByteToMessageCodec$Encoder(ByteToMessageCodec var1, boolean var2) {
      this.this$0 = var1;
      super(var2);
   }
}
