package io.netty.handler.codec.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceValuesToIntTask;
import net.minecraft.client.audio.SoundManager$2$1;
import net.minecraft.network.NettyEncryptingDecoder;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.jboss.marshalling.Marshaller;

public class CompatibleMarshallingEncoder extends MessageToByteEncoder<Object> {
   public MarshallerProvider provider;
   public NettyEncryptingDecoder __junk2136755715767647949;
   public SoundManager$2$1 __junk4514175314444947803;
   public ConcurrentHashMapV8$MapReduceValuesToIntTask __junk2831239897797758200;
   public ExtendedBlockStorage __junk2447136011792560740;

   @Override
   public void encode(ChannelHandlerContext var1, Object var2, ByteBuf var3) {
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
