package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.module.type.ServerAddressModule;
import io.netty.buffer.ByteBuf;

public class SpdyHeaderBlockRawDecoder extends SpdyHeaderBlockDecoder {
   public int maxHeaderSize;
   public SpdyHeaderBlockRawDecoder$State state;
   public static int LENGTH_FIELD_SIZE;
   public int headerSize;
   public ServerAddressModule __junk702309914136626409;
   public ByteBuf cumulation;
   public int length;
   public String name;
   public int numHeaders;

   @Override
   public void decode(ByteBuf var1, SpdyHeadersFrame var2) {
      if (var1 == null) {
         throw new NullPointerException("headerBlock");
      } else if (var2 == null) {
         throw new NullPointerException("frame");
      } else {
         if (this.cumulation == null) {
            this.decodeHeaderBlock(var1, var2);
            if (var1.isReadable()) {
               this.cumulation = var1.alloc().buffer(var1.readableBytes());
               this.cumulation.writeBytes(var1);
            }
         } else {
            this.cumulation.writeBytes(var1);
            this.decodeHeaderBlock(this.cumulation, var2);
            if (this.cumulation.isReadable()) {
               this.cumulation.discardReadBytes();
            } else {
               this.releaseBuffer();
            }
         }
      }
   }

   public void decodeHeaderBlock(ByteBuf var1, SpdyHeadersFrame var2) {
      while (var1.isReadable()) {
         switch (SpdyHeaderBlockRawDecoder$1.$SwitchMap$io$netty$handler$codec$spdy$SpdyHeaderBlockRawDecoder$State[this.state.ordinal()]) {
            case 1:
               if (var1.readableBytes() < 4) {
                  return;
               }

               this.numHeaders = readLengthField(var1);
               if (this.numHeaders < 0) {
                  this.state = SpdyHeaderBlockRawDecoder$State.ERROR;
                  var2.setInvalid();
               } else {
                  if (this.numHeaders == 0) {
                     this.state = SpdyHeaderBlockRawDecoder$State.END_HEADER_BLOCK;
                     break;
                  }

                  this.state = SpdyHeaderBlockRawDecoder$State.READ_NAME_LENGTH;
               }
               break;
            case 2:
               if (var1.readableBytes() < 4) {
                  return;
               }

               this.length = readLengthField(var1);
               if (this.length <= 0) {
                  this.state = SpdyHeaderBlockRawDecoder$State.ERROR;
                  var2.setInvalid();
               } else {
                  if (this.length <= this.maxHeaderSize && this.headerSize <= this.maxHeaderSize - this.length) {
                     this.headerSize = this.headerSize + this.length;
                     this.state = SpdyHeaderBlockRawDecoder$State.READ_NAME;
                     break;
                  }

                  this.headerSize = this.maxHeaderSize + 1;
                  this.state = SpdyHeaderBlockRawDecoder$State.SKIP_NAME;
                  var2.setTruncated();
               }
               break;
            case 3:
               if (var1.readableBytes() < this.length) {
                  return;
               }

               byte[] var4 = new byte[this.length];
               var1.readBytes(var4);
               this.name = new String(var4, "UTF-8");
               if (var2.headers().contains(this.name)) {
                  this.state = SpdyHeaderBlockRawDecoder$State.ERROR;
                  var2.setInvalid();
                  break;
               }

               this.state = SpdyHeaderBlockRawDecoder$State.READ_VALUE_LENGTH;
               break;
            case 4:
               int var11 = Math.min(var1.readableBytes(), this.length);
               var1.skipBytes(var11);
               this.length -= var11;
               if (this.length == 0) {
                  this.state = SpdyHeaderBlockRawDecoder$State.READ_VALUE_LENGTH;
               }
               break;
            case 5:
               if (var1.readableBytes() < 4) {
                  return;
               }

               this.length = readLengthField(var1);
               if (this.length < 0) {
                  this.state = SpdyHeaderBlockRawDecoder$State.ERROR;
                  var2.setInvalid();
               } else if (this.length == 0) {
                  if (!var2.isTruncated()) {
                     var2.headers().add(this.name, "");
                  }

                  this.name = null;
                  if (--this.numHeaders == 0) {
                     this.state = SpdyHeaderBlockRawDecoder$State.END_HEADER_BLOCK;
                     break;
                  }

                  this.state = SpdyHeaderBlockRawDecoder$State.READ_NAME_LENGTH;
               } else {
                  if (this.length <= this.maxHeaderSize && this.headerSize <= this.maxHeaderSize - this.length) {
                     this.headerSize = this.headerSize + this.length;
                     this.state = SpdyHeaderBlockRawDecoder$State.READ_VALUE;
                     break;
                  }

                  this.headerSize = this.maxHeaderSize + 1;
                  this.name = null;
                  this.state = SpdyHeaderBlockRawDecoder$State.SKIP_VALUE;
                  var2.setTruncated();
               }
               break;
            case 6:
               if (var1.readableBytes() < this.length) {
                  return;
               }

               byte[] var5 = new byte[this.length];
               var1.readBytes(var5);
               int var6 = 0;
               int var7 = 0;
               if (var5[0] == 0) {
                  this.state = SpdyHeaderBlockRawDecoder$State.ERROR;
                  var2.setInvalid();
               } else {
                  for (; var6 < this.length; var7 = ++var6) {
                     while (var6 < var5.length && var5[var6] != 0) {
                        var6++;
                     }

                     if (var6 < var5.length && (var6 + 1 == var5.length || var5[var6 + 1] == 0)) {
                        this.state = SpdyHeaderBlockRawDecoder$State.ERROR;
                        var2.setInvalid();
                        break;
                     }

                     String var8 = new String(var5, var7, var6 - var7, "UTF-8");

                     try {
                        var2.headers().add(this.name, var8);
                     } catch (IllegalArgumentException var10) {
                        this.state = SpdyHeaderBlockRawDecoder$State.ERROR;
                        var2.setInvalid();
                        break;
                     }
                  }

                  this.name = null;
                  if (this.state != SpdyHeaderBlockRawDecoder$State.ERROR) {
                     if (--this.numHeaders == 0) {
                        this.state = SpdyHeaderBlockRawDecoder$State.END_HEADER_BLOCK;
                     } else {
                        this.state = SpdyHeaderBlockRawDecoder$State.READ_NAME_LENGTH;
                     }
                  }
               }
               break;
            case 7:
               int var3 = Math.min(var1.readableBytes(), this.length);
               var1.skipBytes(var3);
               this.length -= var3;
               if (this.length != 0) {
                  break;
               }

               if (--this.numHeaders == 0) {
                  this.state = SpdyHeaderBlockRawDecoder$State.END_HEADER_BLOCK;
                  break;
               }

               this.state = SpdyHeaderBlockRawDecoder$State.READ_NAME_LENGTH;
               break;
            case 8:
               this.state = SpdyHeaderBlockRawDecoder$State.ERROR;
               var2.setInvalid();
               break;
            case 9:
               var1.skipBytes(var1.readableBytes());
               return;
            default:
               throw new Error("Shouldn't reach here.");
         }
      }
   }

   @Override
   public void endHeaderBlock(SpdyHeadersFrame var1) {
      if (this.state != SpdyHeaderBlockRawDecoder$State.END_HEADER_BLOCK) {
         var1.setInvalid();
      }

      this.releaseBuffer();
      this.headerSize = 0;
      this.name = null;
      this.state = SpdyHeaderBlockRawDecoder$State.READ_NUM_HEADERS;
   }

   public static int readLengthField(ByteBuf var0) {
      int var1 = SpdyCodecUtil.getSignedInt(var0, var0.readerIndex());
      var0.skipBytes(4);
      return var1;
   }

   @Override
   public void end() {
      this.releaseBuffer();
   }

   public void releaseBuffer() {
      if (this.cumulation != null) {
         this.cumulation.release();
         this.cumulation = null;
      }
   }

   public SpdyHeaderBlockRawDecoder(SpdyVersion var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException("spdyVersion");
      } else {
         this.maxHeaderSize = var2;
         this.state = SpdyHeaderBlockRawDecoder$State.READ_NUM_HEADERS;
      }
   }
}
