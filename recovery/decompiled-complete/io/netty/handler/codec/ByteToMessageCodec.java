package io.netty.handler.codec;

import io.netty.bootstrap.AbstractBootstrap$2;
import io.netty.buffer.ByteBuf;
import io.netty.channel.AbstractChannelHandlerContext$WriteAndFlushTask;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.internal.TypeParameterMatcher;
import java.util.List;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.optifine.expr.FunctionFloat;
import recovered.unidentified.UnidentifiedClass1790;

public abstract class ByteToMessageCodec<I> extends ChannelDuplexHandler {
   public AbstractBootstrap$2 __junk3703934449306613123;
   public MessageToByteEncoder<I> encoder;
   public WorldGenSand __junk627770384914300876;
   public TypeParameterMatcher outboundMsgMatcher;
   public FunctionFloat __junk1578117866899377979;
   public AbstractChannelHandlerContext$WriteAndFlushTask __junk3627445104435712313;
   public NoiseGenerator __junk9183396093920965720;
   public UnidentifiedClass1790 __junk4316121203842931779;
   public ByteToMessageDecoder decoder = new ByteToMessageCodec$1(this);

   public void decodeLast(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      this.decode(var1, var2, var3);
   }

   public ByteToMessageCodec(boolean var1) {
      this.outboundMsgMatcher = TypeParameterMatcher.find(this, ByteToMessageCodec.class, "I");
      this.encoder = new ByteToMessageCodec$Encoder(this, var1);
   }

   public boolean acceptOutboundMessage(Object var1) {
      return this.outboundMsgMatcher.match(var1);
   }

   public ByteToMessageCodec() {
      this(true);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      this.decoder.channelRead(var1, var2);
   }

   public abstract void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3);

   public abstract void encode(ChannelHandlerContext var1, I var2, ByteBuf var3);

   public ByteToMessageCodec(Class<? extends I> var1) {
      this(var1, true);
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      this.encoder.write(var1, var2, var3);
   }

   public ByteToMessageCodec(Class<? extends I> var1, boolean var2) {
      this.checkForSharableAnnotation();
      this.outboundMsgMatcher = TypeParameterMatcher.get(var1);
      this.encoder = new ByteToMessageCodec$Encoder(this, var2);
   }

   public void checkForSharableAnnotation() {
      if (this.isSharable()) {
         throw new IllegalStateException("@Sharable annotation is not allowed");
      }
   }
}
