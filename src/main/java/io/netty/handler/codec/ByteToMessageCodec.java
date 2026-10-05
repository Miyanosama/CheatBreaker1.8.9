package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AbstractChannelHandlerContext;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.internal.TypeParameterMatcher;
import java.util.List;
import javax.vecmath.MismatchedSizeException;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.optifine.config.VillagerProfession;
import net.optifine.expr.FunctionFloat;
import org.apache.log4j.chainsaw.EventDetails;
import com.cheatbreaker.client.emote.type.FacepalmEmote;

public abstract class ByteToMessageCodec<I> extends ChannelDuplexHandler {
   public MessageToByteEncoder<I> encoder;
   public TypeParameterMatcher outboundMsgMatcher;
   public ByteToMessageDecoder decoder = new ByteToMessageDecoder() {

      @Override
      public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
         ByteToMessageCodec.this.decode(var1, var2, var3);
      }

      @Override
      public void decodeLast(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
         ByteToMessageCodec.this.decodeLast(var1, var2, var3);
      }
   };

   public void decodeLast(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      this.decode(var1, var2, var3);
   }

   public ByteToMessageCodec(boolean var1) {
      this.outboundMsgMatcher = TypeParameterMatcher.find(this, ByteToMessageCodec.class, "I");
      this.encoder = new ByteToMessageCodec.Encoder(var1);
   }

   public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
      return this.outboundMsgMatcher.match(var1);
   }

   public ByteToMessageCodec() {
      this(true);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      this.decoder.channelRead(var1, var2);
   }

   public abstract void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception ;

   public abstract void encode(ChannelHandlerContext var1, I var2, ByteBuf var3) throws java.lang.Exception ;

   public ByteToMessageCodec(Class<? extends I> var1) {
      this(var1, true);
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      this.encoder.write(var1, var2, var3);
   }

   public ByteToMessageCodec(Class<? extends I> var1, boolean var2) {
      this.checkForSharableAnnotation();
      this.outboundMsgMatcher = TypeParameterMatcher.get(var1);
      this.encoder = new ByteToMessageCodec.Encoder(var2);
   }

   public void checkForSharableAnnotation() {
      if (this.isSharable()) {
         throw new IllegalStateException("@Sharable annotation is not allowed");
      }
   }

   public final class Encoder extends MessageToByteEncoder<I> {

      @Override
      public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
         return ByteToMessageCodec.this.acceptOutboundMessage(var1);
      }

      @Override
      public void encode(ChannelHandlerContext var1, I var2, ByteBuf var3) throws java.lang.Exception {
         ByteToMessageCodec.this.encode(var1, (I)var2, var3);
      }

      public Encoder(boolean var2) {
         super(var2);
      }
   }
}
