package io.netty.handler.codec.spdy;

import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;

public enum SpdyFrameDecoder$State {
   READ_HEADERS_FRAME,
   READ_DATA_FRAME,
   READ_HEADER_BLOCK,
   READ_PING_FRAME,
   FRAME_ERROR,
   READ_SYN_STREAM_FRAME,
   READ_COMMON_HEADER,
   READ_GOAWAY_FRAME,
   READ_SYN_REPLY_FRAME,
   DISCARD_FRAME,
   READ_SETTING,
   READ_SETTINGS_FRAME,
   READ_WINDOW_UPDATE_FRAME,
   READ_RST_STREAM_FRAME;
   // $VF: synthetic field
   public static SpdyFrameDecoder$State[] $VALUES = new SpdyFrameDecoder$State[]{
      SpdyFrameDecoder$State.READ_COMMON_HEADER,
      READ_DATA_FRAME,
      SpdyFrameDecoder$State.READ_SYN_STREAM_FRAME,
      SpdyFrameDecoder$State.READ_SYN_REPLY_FRAME,
      SpdyFrameDecoder$State.READ_RST_STREAM_FRAME,
      SpdyFrameDecoder$State.READ_SETTINGS_FRAME,
      SpdyFrameDecoder$State.READ_SETTING,
      SpdyFrameDecoder$State.READ_PING_FRAME,
      SpdyFrameDecoder$State.READ_GOAWAY_FRAME,
      READ_HEADERS_FRAME,
      SpdyFrameDecoder$State.READ_WINDOW_UPDATE_FRAME,
      SpdyFrameDecoder$State.READ_HEADER_BLOCK,
      SpdyFrameDecoder$State.DISCARD_FRAME,
      SpdyFrameDecoder$State.FRAME_ERROR
   };
   public GuiControls __junk4124194766174250614;
   public PackMetadataSectionSerializer __junk3043416267725337751;
}
