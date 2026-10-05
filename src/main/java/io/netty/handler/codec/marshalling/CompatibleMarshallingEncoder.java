package io.netty.handler.codec.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import net.minecraft.network.NettyEncryptingDecoder;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.jboss.marshalling.Marshaller;

public class CompatibleMarshallingEncoder extends MessageToByteEncoder<Object> {
   public MarshallerProvider provider;

   @Override
   public void encode(ChannelHandlerContext var1, Object var2, ByteBuf var3) throws java.lang.Exception {
      Marshaller var4 = this.provider.getMarshaller(var1);
      var4.start(new ChannelBufferByteOutput(var3));
      var4.writeObject(var2);
      var4.finish();
      var4.close();
   }

   public CompatibleMarshallingEncoder(MarshallerProvider var1) {
      this.provider = var1;
   }
}
