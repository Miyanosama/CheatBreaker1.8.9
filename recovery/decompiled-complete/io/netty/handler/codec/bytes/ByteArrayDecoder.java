package io.netty.handler.codec.bytes;

import com.cheatbreaker.client.module.type.ParticlesModule;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import junit.swingui.TestSelector$TestCellRenderer;
import net.minecraft.inventory.Container;
import net.optifine.config.GlVersion;

public class ByteArrayDecoder extends MessageToMessageDecoder<ByteBuf> {
   public GlVersion __junk902087096948046358;
   public Container __junk638276246010641267;
   public ParticlesModule __junk9061268019035366515;
   public TestSelector$TestCellRenderer __junk1958376987209906602;

   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      byte[] var4 = new byte[var2.readableBytes()];
      var2.getBytes(0, var4);
      var3.add(var4);
   }
}
