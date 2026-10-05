package io.netty.handler.codec.compression;

import io.netty.buffer.UnpooledUnsafeDirectByteBuf;
import net.minecraft.block.state.pattern.BlockPattern$PatternHelper;
import org.java_websocket.framing.CloseFrame;

public enum JdkZlibDecoder$GzipState {
   FLG_READ,
   PROCESS_FHCRC,
   HEADER_END,
   SKIP_COMMENT,
   SKIP_FNAME,
   FOOTER_START,
   XLEN_READ,
   HEADER_START;
   public CloseFrame __junk8887172935476292076;
   public UnpooledUnsafeDirectByteBuf __junk2560001954518608761;
   // $VF: synthetic field
   public static JdkZlibDecoder$GzipState[] $VALUES = new JdkZlibDecoder$GzipState[]{
      JdkZlibDecoder$GzipState.HEADER_START,
      HEADER_END,
      FLG_READ,
      JdkZlibDecoder$GzipState.XLEN_READ,
      JdkZlibDecoder$GzipState.SKIP_FNAME,
      JdkZlibDecoder$GzipState.SKIP_COMMENT,
      PROCESS_FHCRC,
      JdkZlibDecoder$GzipState.FOOTER_START
   };
   public BlockPattern$PatternHelper __junk1152818655132438604;
}
