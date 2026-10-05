package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledUnsafeDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.realms.RealmsSharedConstants;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.server.DefaultWebSocketServerFactory;

public class JdkZlibDecoder extends ZlibDecoder {
   public static final int FEXTRA = 4;
   public int flags;
   public static final int FCOMMENT = 16;
   public CRC32 crc;
   public static final int FHCRC = 2;
   public volatile boolean finished;
   public Inflater inflater;
   public static final int FRESERVED = 224;
   public int xlen;
   public boolean decideZlibOrNone;
   public static final int FNAME = 8;
   public JdkZlibDecoder.GzipState gzipState = JdkZlibDecoder.GzipState.HEADER_START;
   public byte[] dictionary;

   public boolean readGZIPHeader(ByteBuf var1) {
      switch (this.gzipState) {
         case HEADER_START:
            if (var1.readableBytes() < 10) {
               return false;
            }

            byte var2 = var1.readByte();
            byte var3 = var1.readByte();
            if (var2 != 31) {
               throw new DecompressionException("Input is not in the GZIP format");
            }

            this.crc.update(var2);
            this.crc.update(var3);
            short var4 = var1.readUnsignedByte();
            if (var4 != 8) {
               throw new DecompressionException("Unsupported compression method " + var4 + " in the GZIP header");
            }

            this.crc.update(var4);
            this.flags = var1.readUnsignedByte();
            this.crc.update(this.flags);
            if ((this.flags & 224) != 0) {
               throw new DecompressionException("Reserved flags are set in the GZIP header");
            }

            this.crc.update(var1.readByte());
            this.crc.update(var1.readByte());
            this.crc.update(var1.readByte());
            this.crc.update(var1.readByte());
            this.crc.update(var1.readUnsignedByte());
            this.crc.update(var1.readUnsignedByte());
            this.gzipState = JdkZlibDecoder.GzipState.FLG_READ;
         case FLG_READ:
            if ((this.flags & 4) != 0) {
               if (var1.readableBytes() < 2) {
                  return false;
               }

               short var5 = var1.readUnsignedByte();
               short var6 = var1.readUnsignedByte();
               this.crc.update(var5);
               this.crc.update(var6);
               this.xlen |= var5 << 8 | var6;
            }

            this.gzipState = JdkZlibDecoder.GzipState.XLEN_READ;
         case XLEN_READ:
            if (this.xlen != -1) {
               if (var1.readableBytes() < this.xlen) {
                  return false;
               }

               byte[] var7 = new byte[this.xlen];
               var1.readBytes(var7);
               this.crc.update(var7);
            }

            this.gzipState = JdkZlibDecoder.GzipState.SKIP_FNAME;
         case SKIP_FNAME:
            if ((this.flags & 8) != 0) {
               if (!var1.isReadable()) {
                  return false;
               }

               short var8;
               do {
                  var8 = var1.readUnsignedByte();
                  this.crc.update(var8);
               } while (var8 != 0 && var1.isReadable());
            }

            this.gzipState = JdkZlibDecoder.GzipState.SKIP_COMMENT;
         case SKIP_COMMENT:
            if ((this.flags & 16) != 0) {
               if (!var1.isReadable()) {
                  return false;
               }

               short var9;
               do {
                  var9 = var1.readUnsignedByte();
                  this.crc.update(var9);
               } while (var9 != 0 && var1.isReadable());
            }

            this.gzipState = JdkZlibDecoder.GzipState.PROCESS_FHCRC;
         case PROCESS_FHCRC:
            break;
         case HEADER_END:
            return true;
         default:
            throw new IllegalStateException();
      }

      if ((this.flags & 2) != 0) {
         if (var1.readableBytes() < 4) {
            return false;
         }

         this.verifyCrc(var1);
      }

      this.crc.reset();
      this.gzipState = JdkZlibDecoder.GzipState.HEADER_END;
      return true;
   }

   @Override
   public void handlerRemoved0(ChannelHandlerContext var1) throws java.lang.Exception {
      super.handlerRemoved0(var1);
      if (this.inflater != null) {
         this.inflater.end();
      }
   }

   public JdkZlibDecoder(ZlibWrapper var1) {
      this(var1, null);
   }

   public JdkZlibDecoder() {
      this(ZlibWrapper.ZLIB, null);
   }

   public boolean readGZIPFooter(ByteBuf var1) {
      if (var1.readableBytes() < 8) {
         return false;
      } else {
         this.verifyCrc(var1);
         int var2 = 0;

         for (int var3 = 0; var3 < 4; var3++) {
            var2 |= var1.readUnsignedByte() << var3 * 8;
         }

         int var4 = this.inflater.getTotalOut();
         if (var2 != var4) {
            throw new DecompressionException("Number of bytes mismatch. Expected: " + var2 + ", Got: " + var4);
         } else {
            return true;
         }
      }
   }

   @Override
   public boolean isClosed() {
      return this.finished;
   }

   public JdkZlibDecoder(byte[] var1) {
      this(ZlibWrapper.ZLIB, var1);
   }

   public void verifyCrc(ByteBuf var1) {
      long var2 = 0L;

      for (int var4 = 0; var4 < 4; var4++) {
         var2 |= (long)var1.readUnsignedByte() << var4 * 8;
      }

      long var6 = this.crc.getValue();
      if (var2 != var6) {
         throw new DecompressionException("CRC value missmatch. Expected: " + var2 + ", Got: " + var6);
      }
   }

   public static boolean looksLikeZlib(short var0) {
      return (var0 & 30720) == 30720 && var0 % 31 == 0;
   }

   public JdkZlibDecoder(ZlibWrapper var1, byte[] var2) {
      this.flags = -1;
      this.xlen = -1;
      if (var1 == null) {
         throw new NullPointerException("wrapper");
      } else {
         switch (var1) {
            case GZIP:
               this.inflater = new Inflater(true);
               this.crc = new CRC32();
               break;
            case NONE:
               this.inflater = new Inflater(true);
               this.crc = null;
               break;
            case ZLIB:
               this.inflater = new Inflater();
               this.crc = null;
               break;
            case ZLIB_OR_NONE:
               this.decideZlibOrNone = true;
               this.crc = null;
               break;
            default:
               throw new IllegalArgumentException("Only GZIP or ZLIB is supported, but you used " + var1);
         }

         this.dictionary = var2;
      }
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      if (this.finished) {
         var2.skipBytes(var2.readableBytes());
      } else if (var2.isReadable()) {
         if (this.decideZlibOrNone) {
            if (var2.readableBytes() < 2) {
               return;
            }

            boolean var4 = !looksLikeZlib(var2.getShort(0));
            this.inflater = new Inflater(var4);
            this.decideZlibOrNone = false;
         }

         if (this.crc != null) {
            switch (this.gzipState) {
               case FOOTER_START:
                  if (this.readGZIPFooter(var2)) {
                     this.finished = true;
                  }

                  return;
               default:
                  if (this.gzipState != JdkZlibDecoder.GzipState.HEADER_END && !this.readGZIPHeader(var2)) {
                     return;
                  }
            }
         }

         int var18 = var2.readableBytes();
         if (var2.hasArray()) {
            this.inflater.setInput(var2.array(), var2.arrayOffset() + var2.readerIndex(), var2.readableBytes());
         } else {
            byte[] var5 = new byte[var2.readableBytes()];
            var2.getBytes(var2.readerIndex(), var5);
            this.inflater.setInput(var5);
         }

         int var19 = this.inflater.getRemaining() << 1;
         ByteBuf var6 = var1.alloc().heapBuffer(var19);

         try {
            boolean var7 = false;
            byte[] var8 = var6.array();

            while (!this.inflater.needsInput()) {
               int var9 = var6.writerIndex();
               int var10 = var6.arrayOffset() + var9;
               int var11 = var6.writableBytes();
               if (var11 == 0) {
                  var3.add(var6);
                  var6 = var1.alloc().heapBuffer(var19);
                  var8 = var6.array();
               } else {
                  int var12 = this.inflater.inflate(var8, var10, var11);
                  if (var12 > 0) {
                     var6.writerIndex(var9 + var12);
                     if (this.crc != null) {
                        this.crc.update(var8, var10, var12);
                     }
                  } else if (this.inflater.needsDictionary()) {
                     if (this.dictionary == null) {
                        throw new DecompressionException("decompression failure, unable to set dictionary as non was specified");
                     }

                     this.inflater.setDictionary(this.dictionary);
                  }

                  if (this.inflater.finished()) {
                     if (this.crc == null) {
                        this.finished = true;
                     } else {
                        var7 = true;
                     }
                     break;
                  }
               }
            }

            var2.skipBytes(var18 - this.inflater.getRemaining());
            if (var7) {
               this.gzipState = JdkZlibDecoder.GzipState.FOOTER_START;
               if (this.readGZIPFooter(var2)) {
                  this.finished = true;
               }
            }
         } catch (DataFormatException var16) {
            throw new DecompressionException("decompression failure", var16);
         } finally {
            if (var6.isReadable()) {
               var3.add(var6);
            } else {
               var6.release();
            }
         }
      }
   }

   public static enum GzipState {
      HEADER_START,
      HEADER_END,
      FLG_READ,
      XLEN_READ,
      SKIP_FNAME,
      SKIP_COMMENT,
      PROCESS_FHCRC,
      FOOTER_START;
      // $VF: synthetic field
      public static JdkZlibDecoder.GzipState[] $VALUES = new JdkZlibDecoder.GzipState[]{
         JdkZlibDecoder.GzipState.HEADER_START,
         HEADER_END,
         FLG_READ,
         JdkZlibDecoder.GzipState.XLEN_READ,
         JdkZlibDecoder.GzipState.SKIP_FNAME,
         JdkZlibDecoder.GzipState.SKIP_COMMENT,
         PROCESS_FHCRC,
         JdkZlibDecoder.GzipState.FOOTER_START
      };
   }
}
