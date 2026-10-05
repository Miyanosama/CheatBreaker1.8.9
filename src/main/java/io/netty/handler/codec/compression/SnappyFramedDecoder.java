package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.websocketx.WebSocket07FrameDecoder;
import java.util.Arrays;
import java.util.List;
import javax.vecmath.Matrix3f;
import net.minecraft.client.particle.EntityBreakingFX;

public class SnappyFramedDecoder extends ByteToMessageDecoder {
   public static final int MAX_UNCOMPRESSED_DATA_SIZE = 65540;
   public boolean started;
   public Snappy snappy = new Snappy();
   public boolean validateChecksums;
   public static byte[] SNAPPY = new byte[]{115, 78, 97, 80, 112, 89};
   public boolean corrupted;

   public SnappyFramedDecoder(boolean var1) {
      this.validateChecksums = var1;
   }

   public static SnappyFramedDecoder.ChunkType mapChunkType(byte var0) {
      if (var0 == 0) {
         return SnappyFramedDecoder.ChunkType.COMPRESSED_DATA;
      } else if (var0 == 1) {
         return SnappyFramedDecoder.ChunkType.UNCOMPRESSED_DATA;
      } else if (var0 == -1) {
         return SnappyFramedDecoder.ChunkType.STREAM_IDENTIFIER;
      } else {
         return (var0 & 128) == 128 ? SnappyFramedDecoder.ChunkType.RESERVED_SKIPPABLE : SnappyFramedDecoder.ChunkType.RESERVED_UNSKIPPABLE;
      }
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      if (this.corrupted) {
         var2.skipBytes(var2.readableBytes());
      } else {
         try {
            int var4 = var2.readerIndex();
            int var5 = var2.readableBytes();
            if (var5 >= 4) {
               short var6 = var2.getUnsignedByte(var4);
               SnappyFramedDecoder.ChunkType var7 = mapChunkType((byte)var6);
               int var8 = ByteBufUtil.swapMedium(var2.getUnsignedMedium(var4 + 1));
               switch (var7) {
                  case STREAM_IDENTIFIER:
                     if (var8 != SNAPPY.length) {
                        throw new DecompressionException("Unexpected length of stream identifier: " + var8);
                     }

                     if (var5 >= 4 + SNAPPY.length) {
                        byte[] var9 = new byte[var8];
                        var2.skipBytes(4).readBytes(var9);
                        if (!Arrays.equals(var9, SNAPPY)) {
                           throw new DecompressionException("Unexpected stream identifier contents. Mismatched snappy protocol version?");
                        }

                        this.started = true;
                     }
                     break;
                  case RESERVED_SKIPPABLE:
                     if (!this.started) {
                        throw new DecompressionException("Received RESERVED_SKIPPABLE tag before STREAM_IDENTIFIER");
                     }

                     if (var5 < 4 + var8) {
                        return;
                     }

                     var2.skipBytes(4 + var8);
                     break;
                  case RESERVED_UNSKIPPABLE:
                     throw new DecompressionException("Found reserved unskippable chunk type: 0x" + Integer.toHexString(var6));
                  case UNCOMPRESSED_DATA:
                     if (!this.started) {
                        throw new DecompressionException("Received UNCOMPRESSED_DATA tag before STREAM_IDENTIFIER");
                     }

                     if (var8 > 65540) {
                        throw new DecompressionException("Received UNCOMPRESSED_DATA larger than 65540 bytes");
                     }

                     if (var5 < 4 + var8) {
                        return;
                     }

                     var2.skipBytes(4);
                     if (this.validateChecksums) {
                        int var18 = ByteBufUtil.swapInt(var2.readInt());
                        Snappy.validateChecksum(var18, var2, var2.readerIndex(), var8 - 4);
                     } else {
                        var2.skipBytes(4);
                     }

                     var3.add(var2.readSlice(var8 - 4).retain());
                     break;
                  case COMPRESSED_DATA:
                     if (!this.started) {
                        throw new DecompressionException("Received COMPRESSED_DATA tag before STREAM_IDENTIFIER");
                     }

                     if (var5 < 4 + var8) {
                        return;
                     }

                     var2.skipBytes(4);
                     int var10 = ByteBufUtil.swapInt(var2.readInt());
                     ByteBuf var11 = var1.alloc().buffer(0);
                     if (this.validateChecksums) {
                        int var12 = var2.writerIndex();

                        try {
                           var2.writerIndex(var2.readerIndex() + var8 - 4);
                           this.snappy.decode(var2, var11);
                        } finally {
                           var2.writerIndex(var12);
                        }

                        Snappy.validateChecksum(var10, var11, 0, var11.writerIndex());
                     } else {
                        this.snappy.decode(var2.readSlice(var8 - 4), var11);
                     }

                     var3.add(var11);
                     this.snappy.reset();
               }
            }
         } catch (Exception var17) {
            this.corrupted = true;
            throw var17;
         }
      }
   }

   public SnappyFramedDecoder() {
      this(false);
   }

   public static enum ChunkType {
      STREAM_IDENTIFIER,
      COMPRESSED_DATA,
      UNCOMPRESSED_DATA,
      RESERVED_UNSKIPPABLE,
      RESERVED_SKIPPABLE;
      // $VF: synthetic field
      public static SnappyFramedDecoder.ChunkType[] $VALUES = new SnappyFramedDecoder.ChunkType[]{
         STREAM_IDENTIFIER,
         COMPRESSED_DATA,
         SnappyFramedDecoder.ChunkType.UNCOMPRESSED_DATA,
         SnappyFramedDecoder.ChunkType.RESERVED_UNSKIPPABLE,
         RESERVED_SKIPPABLE
      };
   }
}
