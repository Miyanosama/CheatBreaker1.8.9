package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.module.type.NumberHudModule;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.util.internal.EmptyArrays;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.command.CommandReplaceItem;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.WeightedRandomChestContent;
import org.apache.log4j.lf5.viewer.LF5SwingUtils;
import com.cheatbreaker.client.ui.resourcepack.ResourcePackText;

public class SpdySessionHandler extends ChannelDuplexHandler {
   public int initialSendWindowSize = 65536;
   public static final int DEFAULT_WINDOW_SIZE = 65536;
   public int remoteConcurrentStreams;
   public AtomicInteger pings;
   public boolean sentGoAwayFrame;
   public int localConcurrentStreams;
   public SpdySession spdySession;
   public boolean server;
   public static final int DEFAULT_MAX_CONCURRENT_STREAMS = 2147483647;
   public static SpdyProtocolException PROTOCOL_EXCEPTION = new SpdyProtocolException();
   public int initialReceiveWindowSize = 65536;
   public ChannelFutureListener closeSessionFutureListener;
   public int minorVersion;
   public int lastGoodStreamId;
   public static SpdyProtocolException STREAM_CLOSED = new SpdyProtocolException("Stream closed");
   public volatile int initialSessionReceiveWindowSize = 65536;
   public boolean receivedGoAwayFrame;

   public void handleOutboundMessage(final ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      if (var2 instanceof SpdyDataFrame) {
         SpdyDataFrame var4 = (SpdyDataFrame)var2;
         int var5 = var4.streamId();
         if (this.spdySession.isLocalSideClosed(var5)) {
            var4.release();
            var3.setFailure(PROTOCOL_EXCEPTION);
            return;
         }

         int var6 = var4.content().readableBytes();
         int var7 = this.spdySession.getSendWindowSize(var5);
         int var8 = this.spdySession.getSendWindowSize(0);
         var7 = Math.min(var7, var8);
         if (var7 <= 0) {
            this.spdySession.putPendingWrite(var5, new SpdySession.PendingWrite(var4, var3));
            return;
         }

         if (var7 < var6) {
            this.spdySession.updateSendWindowSize(var5, -1 * var7);
            this.spdySession.updateSendWindowSize(0, -1 * var7);
            DefaultSpdyDataFrame var9 = new DefaultSpdyDataFrame(var5, var4.content().readSlice(var7).retain());
            this.spdySession.putPendingWrite(var5, new SpdySession.PendingWrite(var4, var3));
            var1.write(var9).addListener(new ChannelFutureListener() {

               public void operationComplete(ChannelFuture var1x) throws java.lang.Exception {
                  if (!var1x.isSuccess()) {
                     SpdySessionHandler.this.issueSessionError(var1, SpdySessionStatus.INTERNAL_ERROR);
                  }
               }
            });
            return;
         }

         this.spdySession.updateSendWindowSize(var5, -1 * var6);
         this.spdySession.updateSendWindowSize(0, -1 * var6);
         var3.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture var1x) throws java.lang.Exception {
               if (!var1x.isSuccess()) {
                  SpdySessionHandler.this.issueSessionError(var1, SpdySessionStatus.INTERNAL_ERROR);
               }
            }
         });
         if (var4.isLast()) {
            this.halfCloseStream(var5, false, var3);
         }
      } else if (var2 instanceof SpdySynStreamFrame) {
         SpdySynStreamFrame var11 = (SpdySynStreamFrame)var2;
         int var17 = var11.streamId();
         if (this.isRemoteInitiatedId(var17)) {
            var3.setFailure(PROTOCOL_EXCEPTION);
            return;
         }

         byte var21 = var11.priority();
         boolean var24 = var11.isUnidirectional();
         boolean var26 = var11.isLast();
         if (!this.acceptStream(var17, var21, var24, var26)) {
            var3.setFailure(PROTOCOL_EXCEPTION);
            return;
         }
      } else if (var2 instanceof SpdySynReplyFrame) {
         SpdySynReplyFrame var12 = (SpdySynReplyFrame)var2;
         int var18 = var12.streamId();
         if (!this.isRemoteInitiatedId(var18) || this.spdySession.isLocalSideClosed(var18)) {
            var3.setFailure(PROTOCOL_EXCEPTION);
            return;
         }

         if (var12.isLast()) {
            this.halfCloseStream(var18, false, var3);
         }
      } else if (var2 instanceof SpdyRstStreamFrame) {
         SpdyRstStreamFrame var13 = (SpdyRstStreamFrame)var2;
         this.removeStream(var13.streamId(), var3);
      } else if (var2 instanceof SpdySettingsFrame) {
         SpdySettingsFrame var14 = (SpdySettingsFrame)var2;
         int var19 = var14.getValue(0);
         if (var19 >= 0 && var19 != this.minorVersion) {
            var3.setFailure(PROTOCOL_EXCEPTION);
            return;
         }

         int var22 = var14.getValue(4);
         if (var22 >= 0) {
            this.localConcurrentStreams = var22;
         }

         if (var14.isPersisted(7)) {
            var14.removeValue(7);
         }

         var14.setPersistValue(7, false);
         int var25 = var14.getValue(7);
         if (var25 >= 0) {
            this.updateInitialReceiveWindowSize(var25);
         }
      } else if (var2 instanceof SpdyPingFrame) {
         SpdyPingFrame var15 = (SpdyPingFrame)var2;
         if (this.isRemoteInitiatedId(var15.id())) {
            var1.fireExceptionCaught(new IllegalArgumentException("invalid PING ID: " + var15.id()));
            return;
         }

         this.pings.getAndIncrement();
      } else {
         if (var2 instanceof SpdyGoAwayFrame) {
            var3.setFailure(PROTOCOL_EXCEPTION);
            return;
         }

         if (var2 instanceof SpdyHeadersFrame) {
            SpdyHeadersFrame var16 = (SpdyHeadersFrame)var2;
            int var20 = var16.streamId();
            if (this.spdySession.isLocalSideClosed(var20)) {
               var3.setFailure(PROTOCOL_EXCEPTION);
               return;
            }

            if (var16.isLast()) {
               this.halfCloseStream(var20, false, var3);
            }
         } else if (var2 instanceof SpdyWindowUpdateFrame) {
            var3.setFailure(PROTOCOL_EXCEPTION);
            return;
         }
      }

      var1.write(var2, var3);
   }

   public void updateSendWindowSize(final ChannelHandlerContext var1, int var2, int var3) {
      this.spdySession.updateSendWindowSize(var2, var3);

      while (true) {
         SpdySession.PendingWrite var4 = this.spdySession.getPendingWrite(var2);
         if (var4 == null) {
            return;
         }

         SpdyDataFrame var5 = var4.spdyDataFrame;
         int var6 = var5.content().readableBytes();
         int var7 = var5.streamId();
         int var8 = this.spdySession.getSendWindowSize(var7);
         int var9 = this.spdySession.getSendWindowSize(0);
         var8 = Math.min(var8, var9);
         if (var8 <= 0) {
            return;
         }

         if (var8 < var6) {
            this.spdySession.updateSendWindowSize(var7, -1 * var8);
            this.spdySession.updateSendWindowSize(0, -1 * var8);
            DefaultSpdyDataFrame var10 = new DefaultSpdyDataFrame(var7, var5.content().readSlice(var8).retain());
            var1.writeAndFlush(var10).addListener(new ChannelFutureListener() {

               public void operationComplete(ChannelFuture var1x) throws java.lang.Exception {
                  if (!var1x.isSuccess()) {
                     SpdySessionHandler.this.issueSessionError(var1, SpdySessionStatus.INTERNAL_ERROR);
                  }
               }
            });
         } else {
            this.spdySession.removePendingWrite(var7);
            this.spdySession.updateSendWindowSize(var7, -1 * var6);
            this.spdySession.updateSendWindowSize(0, -1 * var6);
            if (var5.isLast()) {
               this.halfCloseStream(var7, false, var4.promise);
            }

            var1.writeAndFlush(var5, var4.promise).addListener(new ChannelFutureListener() {

               public void operationComplete(ChannelFuture var1x) throws java.lang.Exception {
                  if (!var1x.isSuccess()) {
                     SpdySessionHandler.this.issueSessionError(var1, SpdySessionStatus.INTERNAL_ERROR);
                  }
               }
            });
         }
      }
   }

   public void removeStream(int var1, ChannelFuture var2) {
      this.spdySession.removeStream(var1, STREAM_CLOSED, this.isRemoteInitiatedId(var1));
      if (this.closeSessionFutureListener != null && this.spdySession.noActiveStreams()) {
         var2.addListener(this.closeSessionFutureListener);
      }
   }

   public synchronized void updateInitialSendWindowSize(int var1) {
      int var2 = var1 - this.initialSendWindowSize;
      this.initialSendWindowSize = var1;
      this.spdySession.updateAllSendWindowSizes(var2);
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      if (!(var2 instanceof SpdyDataFrame)
         && !(var2 instanceof SpdySynStreamFrame)
         && !(var2 instanceof SpdySynReplyFrame)
         && !(var2 instanceof SpdyRstStreamFrame)
         && !(var2 instanceof SpdySettingsFrame)
         && !(var2 instanceof SpdyPingFrame)
         && !(var2 instanceof SpdyGoAwayFrame)
         && !(var2 instanceof SpdyHeadersFrame)
         && !(var2 instanceof SpdyWindowUpdateFrame)) {
         var1.write(var2, var3);
      } else {
         this.handleOutboundMessage(var1, var2, var3);
      }
   }

   public SpdySessionHandler(SpdyVersion var1, boolean var2) {
      this.spdySession = new SpdySession(this.initialSendWindowSize, this.initialReceiveWindowSize);
      this.remoteConcurrentStreams = Integer.MAX_VALUE;
      this.localConcurrentStreams = Integer.MAX_VALUE;
      this.pings = new AtomicInteger();
      if (var1 == null) {
         throw new NullPointerException("version");
      } else {
         this.server = var2;
         this.minorVersion = var1.getMinorVersion();
      }
   }

   public synchronized ChannelFuture sendGoAwayFrame(ChannelHandlerContext var1, SpdySessionStatus var2) {
      if (!this.sentGoAwayFrame) {
         this.sentGoAwayFrame = true;
         DefaultSpdyGoAwayFrame var3 = new DefaultSpdyGoAwayFrame(this.lastGoodStreamId, var2);
         return var1.writeAndFlush(var3);
      } else {
         return var1.newSucceededFuture();
      }
   }

   public synchronized void updateInitialReceiveWindowSize(int var1) {
      int var2 = var1 - this.initialReceiveWindowSize;
      this.initialReceiveWindowSize = var1;
      this.spdySession.updateAllReceiveWindowSizes(var2);
   }

   public boolean isRemoteInitiatedId(int var1) {
      boolean var2 = SpdyCodecUtil.isServerId(var1);
      return this.server && !var2 || !this.server && var2;
   }

   public void issueSessionError(ChannelHandlerContext var1, SpdySessionStatus var2) {
      this.sendGoAwayFrame(var1, var2).addListener(new SpdySessionHandler.ClosingChannelFutureListener(var1, var1.newPromise()));
   }

   public synchronized boolean acceptStream(int var1, byte var2, boolean var3, boolean var4) {
      if (!this.receivedGoAwayFrame && !this.sentGoAwayFrame) {
         boolean var5 = this.isRemoteInitiatedId(var1);
         int var6 = var5 ? this.localConcurrentStreams : this.remoteConcurrentStreams;
         if (this.spdySession.numActiveStreams(var5) >= var6) {
            return false;
         } else {
            this.spdySession.acceptStream(var1, var2, var3, var4, this.initialSendWindowSize, this.initialReceiveWindowSize, var5);
            if (var5) {
               this.lastGoodStreamId = var1;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception {
      if (var2 instanceof SpdyProtocolException) {
         this.issueSessionError(var1, SpdySessionStatus.PROTOCOL_ERROR);
      }

      var1.fireExceptionCaught(var2);
   }

   static {
      PROTOCOL_EXCEPTION.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
      STREAM_CLOSED.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
   }

   @Override
   public void close(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
      this.sendGoAwayFrame(var1, var2);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      if (var2 instanceof SpdyDataFrame) {
         SpdyDataFrame var3 = (SpdyDataFrame)var2;
         int var4 = var3.streamId();
         int var5 = -1 * var3.content().readableBytes();
         int var6 = this.spdySession.updateReceiveWindowSize(0, var5);
         if (var6 < 0) {
            this.issueSessionError(var1, SpdySessionStatus.PROTOCOL_ERROR);
            return;
         }

         if (var6 <= this.initialSessionReceiveWindowSize / 2) {
            int var7 = this.initialSessionReceiveWindowSize - var6;
            this.spdySession.updateReceiveWindowSize(0, var7);
            DefaultSpdyWindowUpdateFrame var8 = new DefaultSpdyWindowUpdateFrame(0, var7);
            var1.writeAndFlush(var8);
         }

         if (!this.spdySession.isActiveStream(var4)) {
            var3.release();
            if (var4 <= this.lastGoodStreamId) {
               this.issueStreamError(var1, var4, SpdyStreamStatus.PROTOCOL_ERROR);
            } else if (!this.sentGoAwayFrame) {
               this.issueStreamError(var1, var4, SpdyStreamStatus.INVALID_STREAM);
            }

            return;
         }

         if (this.spdySession.isRemoteSideClosed(var4)) {
            var3.release();
            this.issueStreamError(var1, var4, SpdyStreamStatus.STREAM_ALREADY_CLOSED);
            return;
         }

         if (!this.isRemoteInitiatedId(var4) && !this.spdySession.hasReceivedReply(var4)) {
            var3.release();
            this.issueStreamError(var1, var4, SpdyStreamStatus.PROTOCOL_ERROR);
            return;
         }

         int var27 = this.spdySession.updateReceiveWindowSize(var4, var5);
         if (var27 < this.spdySession.getReceiveWindowSizeLowerBound(var4)) {
            var3.release();
            this.issueStreamError(var1, var4, SpdyStreamStatus.FLOW_CONTROL_ERROR);
            return;
         }

         if (var27 < 0) {
            while (var3.content().readableBytes() > this.initialReceiveWindowSize) {
               DefaultSpdyDataFrame var29 = new DefaultSpdyDataFrame(var4, var3.content().readSlice(this.initialReceiveWindowSize).retain());
               var1.writeAndFlush(var29);
            }
         }

         if (var27 <= this.initialReceiveWindowSize / 2 && !var3.isLast()) {
            int var30 = this.initialReceiveWindowSize - var27;
            this.spdySession.updateReceiveWindowSize(var4, var30);
            DefaultSpdyWindowUpdateFrame var9 = new DefaultSpdyWindowUpdateFrame(var4, var30);
            var1.writeAndFlush(var9);
         }

         if (var3.isLast()) {
            this.halfCloseStream(var4, true, var1.newSucceededFuture());
         }
      } else if (var2 instanceof SpdySynStreamFrame) {
         SpdySynStreamFrame var10 = (SpdySynStreamFrame)var2;
         int var17 = var10.streamId();
         if (var10.isInvalid() || !this.isRemoteInitiatedId(var17) || this.spdySession.isActiveStream(var17)) {
            this.issueStreamError(var1, var17, SpdyStreamStatus.PROTOCOL_ERROR);
            return;
         }

         if (var17 <= this.lastGoodStreamId) {
            this.issueSessionError(var1, SpdySessionStatus.PROTOCOL_ERROR);
            return;
         }

         byte var22 = var10.priority();
         boolean var25 = var10.isLast();
         boolean var28 = var10.isUnidirectional();
         if (!this.acceptStream(var17, var22, var25, var28)) {
            this.issueStreamError(var1, var17, SpdyStreamStatus.REFUSED_STREAM);
            return;
         }
      } else if (var2 instanceof SpdySynReplyFrame) {
         SpdySynReplyFrame var11 = (SpdySynReplyFrame)var2;
         int var18 = var11.streamId();
         if (var11.isInvalid() || this.isRemoteInitiatedId(var18) || this.spdySession.isRemoteSideClosed(var18)) {
            this.issueStreamError(var1, var18, SpdyStreamStatus.INVALID_STREAM);
            return;
         }

         if (this.spdySession.hasReceivedReply(var18)) {
            this.issueStreamError(var1, var18, SpdyStreamStatus.STREAM_IN_USE);
            return;
         }

         this.spdySession.receivedReply(var18);
         if (var11.isLast()) {
            this.halfCloseStream(var18, true, var1.newSucceededFuture());
         }
      } else if (var2 instanceof SpdyRstStreamFrame) {
         SpdyRstStreamFrame var12 = (SpdyRstStreamFrame)var2;
         this.removeStream(var12.streamId(), var1.newSucceededFuture());
      } else if (var2 instanceof SpdySettingsFrame) {
         SpdySettingsFrame var13 = (SpdySettingsFrame)var2;
         int var19 = var13.getValue(0);
         if (var19 >= 0 && var19 != this.minorVersion) {
            this.issueSessionError(var1, SpdySessionStatus.PROTOCOL_ERROR);
            return;
         }

         int var23 = var13.getValue(4);
         if (var23 >= 0) {
            this.remoteConcurrentStreams = var23;
         }

         if (var13.isPersisted(7)) {
            var13.removeValue(7);
         }

         var13.setPersistValue(7, false);
         int var26 = var13.getValue(7);
         if (var26 >= 0) {
            this.updateInitialSendWindowSize(var26);
         }
      } else if (var2 instanceof SpdyPingFrame) {
         SpdyPingFrame var14 = (SpdyPingFrame)var2;
         if (this.isRemoteInitiatedId(var14.id())) {
            var1.writeAndFlush(var14);
            return;
         }

         if (this.pings.get() == 0) {
            return;
         }

         this.pings.getAndDecrement();
      } else if (var2 instanceof SpdyGoAwayFrame) {
         this.receivedGoAwayFrame = true;
      } else if (var2 instanceof SpdyHeadersFrame) {
         SpdyHeadersFrame var15 = (SpdyHeadersFrame)var2;
         int var20 = var15.streamId();
         if (var15.isInvalid()) {
            this.issueStreamError(var1, var20, SpdyStreamStatus.PROTOCOL_ERROR);
            return;
         }

         if (this.spdySession.isRemoteSideClosed(var20)) {
            this.issueStreamError(var1, var20, SpdyStreamStatus.INVALID_STREAM);
            return;
         }

         if (var15.isLast()) {
            this.halfCloseStream(var20, true, var1.newSucceededFuture());
         }
      } else if (var2 instanceof SpdyWindowUpdateFrame) {
         SpdyWindowUpdateFrame var16 = (SpdyWindowUpdateFrame)var2;
         int var21 = var16.streamId();
         int var24 = var16.deltaWindowSize();
         if (var21 != 0 && this.spdySession.isLocalSideClosed(var21)) {
            return;
         }

         if (this.spdySession.getSendWindowSize(var21) > Integer.MAX_VALUE - var24) {
            if (var21 == 0) {
               this.issueSessionError(var1, SpdySessionStatus.PROTOCOL_ERROR);
            } else {
               this.issueStreamError(var1, var21, SpdyStreamStatus.FLOW_CONTROL_ERROR);
            }

            return;
         }

         this.updateSendWindowSize(var1, var21, var24);
      }

      var1.fireChannelRead(var2);
   }

   public void halfCloseStream(int var1, boolean var2, ChannelFuture var3) {
      if (var2) {
         this.spdySession.closeRemoteSide(var1, this.isRemoteInitiatedId(var1));
      } else {
         this.spdySession.closeLocalSide(var1, this.isRemoteInitiatedId(var1));
      }

      if (this.closeSessionFutureListener != null && this.spdySession.noActiveStreams()) {
         var3.addListener(this.closeSessionFutureListener);
      }
   }

   public void setSessionReceiveWindowSize(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("sessionReceiveWindowSize");
      } else {
         this.initialSessionReceiveWindowSize = var1;
      }
   }

   public void sendGoAwayFrame(ChannelHandlerContext var1, ChannelPromise var2) {
      if (!var1.channel().isActive()) {
         var1.close(var2);
      } else {
         ChannelFuture var3 = this.sendGoAwayFrame(var1, SpdySessionStatus.OK);
         if (this.spdySession.noActiveStreams()) {
            var3.addListener(new SpdySessionHandler.ClosingChannelFutureListener(var1, var2));
         } else {
            this.closeSessionFutureListener = new SpdySessionHandler.ClosingChannelFutureListener(var1, var2);
         }
      }
   }

   public void issueStreamError(ChannelHandlerContext var1, int var2, SpdyStreamStatus var3) {
      boolean var4 = !this.spdySession.isRemoteSideClosed(var2);
      ChannelPromise var5 = var1.newPromise();
      this.removeStream(var2, var5);
      DefaultSpdyRstStreamFrame var6 = new DefaultSpdyRstStreamFrame(var2, var3);
      var1.writeAndFlush(var6, var5);
      if (var4) {
         var1.fireChannelRead(var6);
      }
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      for (Integer var3 : this.spdySession.activeStreams().keySet()) {
         this.removeStream(var3, var1.newSucceededFuture());
      }

      var1.fireChannelInactive();
   }

   public static final class ClosingChannelFutureListener implements ChannelFutureListener {
      public ChannelPromise promise;
      public ChannelHandlerContext ctx;

      public ClosingChannelFutureListener(ChannelHandlerContext var1, ChannelPromise var2) {
         this.ctx = var1;
         this.promise = var2;
      }

      public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
         this.ctx.close(this.promise);
      }
   }
}
