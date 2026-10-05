package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import javax.vecmath.MismatchedSizeException;
import net.minecraft.client.gui.GuiCustomizeWorldScreen$1;
import net.minecraft.client.gui.GuiDownloadTerrain;
import org.apache.log4j.chainsaw.EventDetails;

public class ByteToMessageCodec$1 extends ByteToMessageDecoder {
   public GuiCustomizeWorldScreen$1 __junk831416549328732505;
   public GuiDownloadTerrain __junk5145248596031091299;
   public MismatchedSizeException __junk7200213635037489041;
   public EventDetails __junk8099849622015324026;

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      this.this$0.decode(var1, var2, var3);
   }

   @Override
   public void decodeLast(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      this.this$0.decodeLast(var1, var2, var3);
   }

   public ByteToMessageCodec$1(ByteToMessageCodec var1) {
      this.this$0 = var1;
      super();
   }
}
