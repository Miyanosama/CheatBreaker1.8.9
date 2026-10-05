package io.netty.handler.codec.marshalling;

import com.cheatbreaker.client.module.type.CrosshairModule;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.ReplayingDecoderBuffer;
import io.netty.handler.codec.spdy.DefaultSpdyGoAwayFrame;
import junit.swingui.TestSelector$TestCellRenderer;
import net.minecraft.client.audio.SoundList;
import net.minecraft.world.chunk.storage.AnvilSaveConverter$1;
import org.jboss.marshalling.Unmarshaller;

public class MarshallingDecoder extends LengthFieldBasedFrameDecoder {
   public TestSelector$TestCellRenderer __junk2795599308061669209;
   public CrosshairModule __junk4374559376839039243;
   public SoundList __junk605404641889088030;
   public ReplayingDecoderBuffer __junk999261964524113810;
   public AnvilSaveConverter$1 __junk6100882003385401445;
   public DefaultSpdyGoAwayFrame __junk4137436945611161001;
   public UnmarshallerProvider provider;

   @Override
   public Object decode(ChannelHandlerContext var1, ByteBuf var2) {
      ByteBuf var3 = (ByteBuf)super.decode(var1, var2);
      if (var3 == null) {
         return null;
      } else {
         Unmarshaller var4 = this.provider.getUnmarshaller(var1);
         ChannelBufferByteInput var5 = new ChannelBufferByteInput(var3);

         Object var7;
         try {
            var4.start(var5);
            Object var6 = var4.readObject();
            var4.finish();
            var7 = var6;
         } finally {
            var4.close();
         }

         return var7;
      }
   }

   public MarshallingDecoder(UnmarshallerProvider var1) {
      this(var1, 1048576);
   }

   @Override
   public ByteBuf extractFrame(ChannelHandlerContext var1, ByteBuf var2, int var3, int var4) {
      return var2.slice(var3, var4);
   }

   public MarshallingDecoder(UnmarshallerProvider var1, int var2) {
      super(var2, 0, 4, 0, 4);
      this.provider = var1;
   }
}
