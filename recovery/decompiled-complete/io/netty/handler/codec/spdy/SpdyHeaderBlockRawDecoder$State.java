package io.netty.handler.codec.spdy;

import net.minecraft.tileentity.TileEntityBeacon;

public enum SpdyHeaderBlockRawDecoder$State {
   READ_NAME_LENGTH,
   READ_VALUE,
   READ_VALUE_LENGTH,
   SKIP_VALUE,
   SKIP_NAME,
   ERROR,
   READ_NAME,
   END_HEADER_BLOCK,
   READ_NUM_HEADERS;
   public TileEntityBeacon __junk6147964029493480186;
   // $VF: synthetic field
   public static SpdyHeaderBlockRawDecoder$State[] $VALUES = new SpdyHeaderBlockRawDecoder$State[]{
      SpdyHeaderBlockRawDecoder$State.READ_NUM_HEADERS,
      READ_NAME_LENGTH,
      SpdyHeaderBlockRawDecoder$State.READ_NAME,
      SpdyHeaderBlockRawDecoder$State.SKIP_NAME,
      READ_VALUE_LENGTH,
      READ_VALUE,
      SpdyHeaderBlockRawDecoder$State.SKIP_VALUE,
      SpdyHeaderBlockRawDecoder$State.END_HEADER_BLOCK,
      SpdyHeaderBlockRawDecoder$State.ERROR
   };
}
