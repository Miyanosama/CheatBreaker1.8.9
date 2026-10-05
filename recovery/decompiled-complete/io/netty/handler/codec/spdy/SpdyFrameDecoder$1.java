package io.netty.handler.codec.spdy;

import io.netty.buffer.UnpooledHeapByteBuf;
import net.minecraft.command.CommandClone$StaticCloneData;

// $VF: synthetic class
public class SpdyFrameDecoder$1 {
   public CommandClone$StaticCloneData __junk1397151863664268409;
   public UnpooledHeapByteBuf __junk474874419306658461;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_COMMON_HEADER.ordinal()] = 1;
      } catch (NoSuchFieldError var14) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_DATA_FRAME.ordinal()] = 2;
      } catch (NoSuchFieldError var13) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_SYN_STREAM_FRAME.ordinal()] = 3;
      } catch (NoSuchFieldError var12) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_SYN_REPLY_FRAME.ordinal()] = 4;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_RST_STREAM_FRAME.ordinal()] = 5;
      } catch (NoSuchFieldError var10) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_SETTINGS_FRAME.ordinal()] = 6;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_SETTING.ordinal()] = 7;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_PING_FRAME.ordinal()] = 8;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_GOAWAY_FRAME.ordinal()] = 9;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_HEADERS_FRAME.ordinal()] = 10;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_WINDOW_UPDATE_FRAME.ordinal()] = 11;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.READ_HEADER_BLOCK.ordinal()] = 12;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.DISCARD_FRAME.ordinal()] = 13;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyFrameDecoder$State[SpdyFrameDecoder$State.FRAME_ERROR.ordinal()] = 14;
      } catch (NoSuchFieldError var1) {
      }
   }
}
