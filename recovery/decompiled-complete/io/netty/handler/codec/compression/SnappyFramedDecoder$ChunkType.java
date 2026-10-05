package io.netty.handler.codec.compression;

import net.minecraft.world.gen.ChunkProviderSettings$1;

public enum SnappyFramedDecoder$ChunkType {
   RESERVED_SKIPPABLE,
   STREAM_IDENTIFIER,
   COMPRESSED_DATA,
   UNCOMPRESSED_DATA,
   RESERVED_UNSKIPPABLE;
   public ChunkProviderSettings$1 __junk3322640534592694552;
   // $VF: synthetic field
   public static SnappyFramedDecoder$ChunkType[] $VALUES = new SnappyFramedDecoder$ChunkType[]{
      STREAM_IDENTIFIER,
      COMPRESSED_DATA,
      SnappyFramedDecoder$ChunkType.UNCOMPRESSED_DATA,
      SnappyFramedDecoder$ChunkType.RESERVED_UNSKIPPABLE,
      RESERVED_SKIPPABLE
   };
}
