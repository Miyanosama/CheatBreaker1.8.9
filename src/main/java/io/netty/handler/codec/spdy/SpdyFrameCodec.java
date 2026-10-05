package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.util.Version;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import java.net.SocketAddress;
import java.util.List;
import net.minecraft.client.player.inventory.LocalBlockIntercommunication;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.potion.PotionAbsorption;
import net.optifine.shaders.uniform.ShaderUniforms;

public class SpdyFrameCodec extends ByteToMessageDecoder implements ChannelOutboundHandler, SpdyFrameDecoderDelegate {
   public static SpdyProtocolException INVALID_FRAME = new SpdyProtocolException("Received invalid frame");
   public SpdyFrameEncoder spdyFrameEncoder;
   public SpdyHeadersFrame spdyHeadersFrame;
   public SpdySettingsFrame spdySettingsFrame;
   public ChannelHandlerContext ctx;
   public SpdyHeaderBlockDecoder spdyHeaderBlockDecoder;
   public SpdyHeaderBlockEncoder spdyHeaderBlockEncoder;
   public SpdyFrameDecoder spdyFrameDecoder;

   @Override
   public void readSynReplyFrame(int var1, boolean var2) {
      DefaultSpdySynReplyFrame var3 = new DefaultSpdySynReplyFrame(var1);
      var3.setLast(var2);
      this.spdyHeadersFrame = var3;
   }

   @Override
   public void readHeadersFrame(int var1, boolean var2) {
      this.spdyHeadersFrame = new DefaultSpdyHeadersFrame(var1);
      this.spdyHeadersFrame.setLast(var2);
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      this.spdyFrameDecoder.decode(var2);
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
      super.handlerAdded(var1);
      this.ctx = var1;
      var1.channel().closeFuture().addListener(new ChannelFutureListener() {

         public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
            SpdyFrameCodec.this.spdyHeaderBlockDecoder.end();
            SpdyFrameCodec.this.spdyHeaderBlockEncoder.end();
         }
      });
   }

   @Override
   public void flush(ChannelHandlerContext var1) throws java.lang.Exception {
      var1.flush();
   }

   @Override
   public void readSynStreamFrame(int var1, int var2, byte var3, boolean var4, boolean var5) {
      DefaultSpdySynStreamFrame var6 = new DefaultSpdySynStreamFrame(var1, var2, var3);
      var6.setLast(var4);
      var6.setUnidirectional(var5);
      this.spdyHeadersFrame = var6;
   }

   @Override
   public void close(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
      var1.close(var2);
   }

   public SpdyFrameCodec(SpdyVersion var1, int var2, int var3, int var4, int var5, int var6) {
      this(var1, var2, SpdyHeaderBlockDecoder.newInstance(var1, var3), SpdyHeaderBlockEncoder.newInstance(var1, var4, var5, var6));
   }

   @Override
   public void connect(ChannelHandlerContext var1, SocketAddress var2, SocketAddress var3, ChannelPromise var4) throws java.lang.Exception {
      var1.connect(var2, var3, var4);
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      if (var2 instanceof SpdyDataFrame) {
         SpdyDataFrame var5 = (SpdyDataFrame)var2;
         ByteBuf var4 = this.spdyFrameEncoder.encodeDataFrame(var1.alloc(), var5.streamId(), var5.isLast(), var5.content());
         var5.release();
         var1.write(var4, var3);
      } else if (var2 instanceof SpdySynStreamFrame) {
         SpdySynStreamFrame var30 = (SpdySynStreamFrame)var2;
         ByteBuf var6 = this.spdyHeaderBlockEncoder.encode(var30);

         ByteBuf var22;
         try {
            var22 = this.spdyFrameEncoder
               .encodeSynStreamFrame(
                  var1.alloc(), var30.streamId(), var30.associatedStreamId(), var30.priority(), var30.isLast(), var30.isUnidirectional(), var6
               );
         } finally {
            var6.release();
         }

         var1.write(var22, var3);
      } else if (var2 instanceof SpdySynReplyFrame) {
         SpdySynReplyFrame var31 = (SpdySynReplyFrame)var2;
         ByteBuf var38 = this.spdyHeaderBlockEncoder.encode(var31);

         ByteBuf var23;
         try {
            var23 = this.spdyFrameEncoder.encodeSynReplyFrame(var1.alloc(), var31.streamId(), var31.isLast(), var38);
         } finally {
            var38.release();
         }

         var1.write(var23, var3);
      } else if (var2 instanceof SpdyRstStreamFrame) {
         SpdyRstStreamFrame var32 = (SpdyRstStreamFrame)var2;
         ByteBuf var24 = this.spdyFrameEncoder.encodeRstStreamFrame(var1.alloc(), var32.streamId(), var32.status().code());
         var1.write(var24, var3);
      } else if (var2 instanceof SpdySettingsFrame) {
         SpdySettingsFrame var33 = (SpdySettingsFrame)var2;
         ByteBuf var25 = this.spdyFrameEncoder.encodeSettingsFrame(var1.alloc(), var33);
         var1.write(var25, var3);
      } else if (var2 instanceof SpdyPingFrame) {
         SpdyPingFrame var34 = (SpdyPingFrame)var2;
         ByteBuf var26 = this.spdyFrameEncoder.encodePingFrame(var1.alloc(), var34.id());
         var1.write(var26, var3);
      } else if (var2 instanceof SpdyGoAwayFrame) {
         SpdyGoAwayFrame var35 = (SpdyGoAwayFrame)var2;
         ByteBuf var27 = this.spdyFrameEncoder.encodeGoAwayFrame(var1.alloc(), var35.lastGoodStreamId(), var35.status().code());
         var1.write(var27, var3);
      } else if (var2 instanceof SpdyHeadersFrame) {
         SpdyHeadersFrame var36 = (SpdyHeadersFrame)var2;
         ByteBuf var39 = this.spdyHeaderBlockEncoder.encode(var36);

         ByteBuf var28;
         try {
            var28 = this.spdyFrameEncoder.encodeHeadersFrame(var1.alloc(), var36.streamId(), var36.isLast(), var39);
         } finally {
            var39.release();
         }

         var1.write(var28, var3);
      } else {
         if (!(var2 instanceof SpdyWindowUpdateFrame)) {
            throw new UnsupportedMessageTypeException(var2);
         }

         SpdyWindowUpdateFrame var37 = (SpdyWindowUpdateFrame)var2;
         ByteBuf var29 = this.spdyFrameEncoder.encodeWindowUpdateFrame(var1.alloc(), var37.streamId(), var37.deltaWindowSize());
         var1.write(var29, var3);
      }
   }

   @Override
   public void readSettingsFrame(boolean var1) {
      this.spdySettingsFrame = new DefaultSpdySettingsFrame();
      this.spdySettingsFrame.setClearPreviouslyPersistedSettings(var1);
   }

   @Override
   public void readSettingsEnd() {
      SpdySettingsFrame var1 = this.spdySettingsFrame;
      this.spdySettingsFrame = null;
      this.ctx.fireChannelRead(var1);
   }

   @Override
   public void disconnect(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
      var1.disconnect(var2);
   }

   @Override
   public void readDataFrame(int var1, boolean var2, ByteBuf var3) {
      DefaultSpdyDataFrame var4 = new DefaultSpdyDataFrame(var1, var3);
      var4.setLast(var2);
      this.ctx.fireChannelRead(var4);
   }

   @Override
   public void readHeaderBlock(ByteBuf var1) {
      try {
         this.spdyHeaderBlockDecoder.decode(var1, this.spdyHeadersFrame);
      } catch (Exception var6) {
         this.ctx.fireExceptionCaught(var6);
      } finally {
         var1.release();
      }
   }

   @Override
   public void bind(ChannelHandlerContext var1, SocketAddress var2, ChannelPromise var3) throws java.lang.Exception {
      var1.bind(var2, var3);
   }

   @Override
   public void read(ChannelHandlerContext var1) throws java.lang.Exception {
      var1.read();
   }

   @Override
   public void deregister(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
      var1.deregister(var2);
   }

   @Override
   public void readGoAwayFrame(int var1, int var2) {
      DefaultSpdyGoAwayFrame var3 = new DefaultSpdyGoAwayFrame(var1, var2);
      this.ctx.fireChannelRead(var3);
   }

   @Override
   public void readPingFrame(int var1) {
      DefaultSpdyPingFrame var2 = new DefaultSpdyPingFrame(var1);
      this.ctx.fireChannelRead(var2);
   }

   @Override
   public void readHeaderBlockEnd() {
      SpdyHeadersFrame var1 = null;

      try {
         this.spdyHeaderBlockDecoder.endHeaderBlock(this.spdyHeadersFrame);
         var1 = this.spdyHeadersFrame;
         this.spdyHeadersFrame = null;
      } catch (Exception var3) {
         this.ctx.fireExceptionCaught(var3);
      }

      if (var1 != null) {
         this.ctx.fireChannelRead(var1);
      }
   }

   @Override
   public void readSetting(int var1, int var2, boolean var3, boolean var4) {
      this.spdySettingsFrame.setValue(var1, var2, var3, var4);
   }

   @Override
   public void readWindowUpdateFrame(int var1, int var2) {
      DefaultSpdyWindowUpdateFrame var3 = new DefaultSpdyWindowUpdateFrame(var1, var2);
      this.ctx.fireChannelRead(var3);
   }

   public SpdyFrameCodec(SpdyVersion var1, int var2, SpdyHeaderBlockDecoder var3, SpdyHeaderBlockEncoder var4) {
      this.spdyFrameDecoder = new SpdyFrameDecoder(var1, this, var2);
      this.spdyFrameEncoder = new SpdyFrameEncoder(var1);
      this.spdyHeaderBlockDecoder = var3;
      this.spdyHeaderBlockEncoder = var4;
   }

   public SpdyFrameCodec(SpdyVersion var1) {
      this(var1, 8192, 16384, 6, 15, 8);
   }

   @Override
   public void readFrameError(String var1) {
      this.ctx.fireExceptionCaught(INVALID_FRAME);
   }

   @Override
   public void readRstStreamFrame(int var1, int var2) {
      DefaultSpdyRstStreamFrame var3 = new DefaultSpdyRstStreamFrame(var1, var2);
      this.ctx.fireChannelRead(var3);
   }
}
