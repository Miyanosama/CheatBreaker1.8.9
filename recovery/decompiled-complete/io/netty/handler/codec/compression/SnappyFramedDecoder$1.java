package io.netty.handler.codec.compression;

import io.netty.handler.codec.base64.Base64;
import net.minecraft.network.play.client.C16PacketClientStatus;
import recovered.unidentified.UnidentifiedClass3897;
import recovered.unidentified.UnidentifiedClass4506;

// $VF: synthetic class
public class SnappyFramedDecoder$1 {
   public C16PacketClientStatus __junk2476648940318782425;
   public UnidentifiedClass4506 __junk3832364642498093237;
   public Base64 __junk1367297689049665320;
   public UnidentifiedClass3897 __junk7715185095454775998;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$compression$SnappyFramedDecoder$ChunkType[SnappyFramedDecoder$ChunkType.STREAM_IDENTIFIER.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$SnappyFramedDecoder$ChunkType[SnappyFramedDecoder$ChunkType.RESERVED_SKIPPABLE.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$SnappyFramedDecoder$ChunkType[SnappyFramedDecoder$ChunkType.RESERVED_UNSKIPPABLE.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$SnappyFramedDecoder$ChunkType[SnappyFramedDecoder$ChunkType.UNCOMPRESSED_DATA.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$SnappyFramedDecoder$ChunkType[SnappyFramedDecoder$ChunkType.COMPRESSED_DATA.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
