package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.internal.AppendableCharSequence;
import java.util.List;
import net.minecraft.command.server.CommandBanIp;
import net.minecraft.entity.monster.EntityPigZombie;
import net.optifine.util.MathUtilsTest$1;
import org.apache.log4j.pattern.FullLocationPatternConverter;

public abstract class HttpObjectDecoder extends ReplayingDecoder<HttpObjectDecoder$State> {
   public HttpMessage message;
   public boolean validateHeaders;
   public int headerSize;
   public long contentLength;
   public CommandBanIp __junk7501237117556319431;
   public long chunkSize;
   public EntityPigZombie __junk8054555106704441606;
   public AppendableCharSequence seq = new AppendableCharSequence(128);
   public FullLocationPatternConverter __junk6982383028362455182;
   public int maxChunkSize;
   public boolean chunkedSupported;
   public int maxHeaderSize;
   public MathUtilsTest$1 __junk2192296379426266992;
   public HttpObjectDecoder$HeaderParser headerParser = new HttpObjectDecoder$HeaderParser(this, this.seq);
   public HttpObjectDecoder$LineParser lineParser = new HttpObjectDecoder$LineParser(this, this.seq);
   public int maxInitialLineLength;

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      switch (HttpObjectDecoder$1.$SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[this.state().ordinal()]) {
         case 1:
            try {
               skipControlCharacters(var2);
               this.checkpoint(HttpObjectDecoder$State.READ_INITIAL);
            } finally {
               this.checkpoint();
            }
         case 2:
            try {
               String[] var23 = splitInitialLine(this.lineParser.parse(var2));
               if (var23.length < 3) {
                  this.checkpoint(HttpObjectDecoder$State.SKIP_CONTROL_CHARS);
                  return;
               }

               this.message = this.createMessage(var23);
               this.checkpoint(HttpObjectDecoder$State.READ_HEADER);
            } catch (Exception var14) {
               var3.add(this.invalidMessage(var14));
               return;
            }
         case 3:
            try {
               HttpObjectDecoder$State var24 = this.readHeaders(var2);
               this.checkpoint(var24);
               if (var24 == HttpObjectDecoder$State.READ_CHUNK_SIZE) {
                  if (!this.chunkedSupported) {
                     throw new IllegalArgumentException("Chunked messages not supported");
                  }

                  var3.add(this.message);
                  return;
               }

               if (var24 == HttpObjectDecoder$State.SKIP_CONTROL_CHARS) {
                  var3.add(this.message);
                  var3.add(LastHttpContent.EMPTY_LAST_CONTENT);
                  this.reset();
                  return;
               }

               long var28 = this.contentLength();
               if (var28 != (4477329395941462528L & -4477329397559065598L) && (var28 != (-1L & -1L) || !this.isDecodingRequest())) {
                  if (!$assertionsDisabled
                     && var24 != HttpObjectDecoder$State.READ_FIXED_LENGTH_CONTENT
                     && var24 != HttpObjectDecoder$State.READ_VARIABLE_LENGTH_CONTENT) {
                     throw new AssertionError();
                  }

                  var3.add(this.message);
                  if (var24 == HttpObjectDecoder$State.READ_FIXED_LENGTH_CONTENT) {
                     this.chunkSize = var28;
                  }

                  return;
               }

               var3.add(this.message);
               var3.add(LastHttpContent.EMPTY_LAST_CONTENT);
               this.reset();
               return;
            } catch (Exception var16) {
               var3.add(this.invalidMessage(var16));
               return;
            }
         case 4:
            int var22 = Math.min(this.actualReadableBytes(), this.maxChunkSize);
            if (var22 > 0) {
               ByteBuf var27 = ByteBufUtil.readBytes(var1.alloc(), var2, var22);
               if (var2.isReadable()) {
                  var3.add(new DefaultHttpContent(var27));
               } else {
                  var3.add(new DefaultLastHttpContent(var27, this.validateHeaders));
                  this.reset();
               }
            } else if (!var2.isReadable()) {
               var3.add(LastHttpContent.EMPTY_LAST_CONTENT);
               this.reset();
            }

            return;
         case 5:
            int var21 = this.actualReadableBytes();
            if (var21 == 0) {
               return;
            }

            int var26 = Math.min(var21, this.maxChunkSize);
            if (var26 > this.chunkSize) {
               var26 = (int)this.chunkSize;
            }

            ByteBuf var6 = ByteBufUtil.readBytes(var1.alloc(), var2, var26);
            this.chunkSize -= var26;
            if (this.chunkSize == (506136072L & -2866412936030576224L)) {
               var3.add(new DefaultLastHttpContent(var6, this.validateHeaders));
               this.reset();
            } else {
               var3.add(new DefaultHttpContent(var6));
            }

            return;
         case 6:
            try {
               AppendableCharSequence var18 = this.lineParser.parse(var2);
               int var5 = getChunkSize(var18.toString());
               this.chunkSize = var5;
               if (var5 == 0) {
                  this.checkpoint(HttpObjectDecoder$State.READ_CHUNK_FOOTER);
                  return;
               }

               this.checkpoint(HttpObjectDecoder$State.READ_CHUNKED_CONTENT);
            } catch (Exception var13) {
               var3.add(this.invalidChunk(var13));
               return;
            }
         case 7:
            if (!$assertionsDisabled && this.chunkSize > (2147483647L & 2147483647L)) {
               throw new AssertionError();
            }

            int var19 = Math.min((int)this.chunkSize, this.maxChunkSize);
            DefaultHttpContent var25 = new DefaultHttpContent(ByteBufUtil.readBytes(var1.alloc(), var2, var19));
            this.chunkSize -= var19;
            var3.add(var25);
            if (this.chunkSize != (936901747950526474L & -936901748934305663L)) {
               return;
            }

            this.checkpoint(HttpObjectDecoder$State.READ_CHUNK_DELIMITER);
         case 8:
            while (true) {
               byte var20 = var2.readByte();
               if (var20 == 13) {
                  if (var2.readByte() == 10) {
                     this.checkpoint(HttpObjectDecoder$State.READ_CHUNK_SIZE);
                     return;
                  }
               } else {
                  if (var20 == 10) {
                     this.checkpoint(HttpObjectDecoder$State.READ_CHUNK_SIZE);
                     return;
                  }

                  this.checkpoint();
               }
            }
         case 9:
            try {
               LastHttpContent var17 = this.readTrailingHeaders(var2);
               var3.add(var17);
               this.reset();
               return;
            } catch (Exception var12) {
               var3.add(this.invalidChunk(var12));
               return;
            }
         case 10:
            var2.skipBytes(this.actualReadableBytes());
            break;
         case 11:
            int var4 = this.actualReadableBytes();
            if (var4 > 0) {
               var3.add(var2.readBytes(this.actualReadableBytes()));
            }
      }
   }

   public abstract HttpMessage createInvalidMessage();

   public HttpObjectDecoder() {
      this(4096, 8192, 8192, true);
   }

   public LastHttpContent readTrailingHeaders(ByteBuf var1) {
      this.headerSize = 0;
      AppendableCharSequence var2 = this.headerParser.parse(var1);
      String var3 = null;
      if (var2.length() <= 0) {
         return LastHttpContent.EMPTY_LAST_CONTENT;
      } else {
         DefaultLastHttpContent var4 = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER, this.validateHeaders);

         do {
            char var5 = var2.charAt(0);
            if (var3 != null && (var5 == ' ' || var5 == '\t')) {
               List var9 = var4.trailingHeaders().getAll(var3);
               if (!var9.isEmpty()) {
                  int var10 = var9.size() - 1;
                  String var8 = (String)var9.get(var10) + var2.toString().trim();
                  var9.set(var10, var8);
               }
            } else {
               String[] var6 = splitHeader(var2);
               String var7 = var6[0];
               if (!HttpHeaders.equalsIgnoreCase(var7, "Content-Length")
                  && !HttpHeaders.equalsIgnoreCase(var7, "Transfer-Encoding")
                  && !HttpHeaders.equalsIgnoreCase(var7, "Trailer")) {
                  var4.trailingHeaders().add(var7, var6[1]);
               }

               var3 = var7;
            }

            var2 = this.headerParser.parse(var1);
         } while (var2.length() > 0);

         return var4;
      }
   }

   public HttpObjectDecoder(int var1, int var2, int var3, boolean var4) {
      this(var1, var2, var3, var4, true);
   }

   public abstract boolean isDecodingRequest();

   public HttpObjectDecoder(int var1, int var2, int var3, boolean var4, boolean var5) {
      super(HttpObjectDecoder$State.SKIP_CONTROL_CHARS);
      this.contentLength = -5339657686059054384L & -3883714351394570233L;
      if (var1 <= 0) {
         throw new IllegalArgumentException("maxInitialLineLength must be a positive integer: " + var1);
      } else if (var2 <= 0) {
         throw new IllegalArgumentException("maxHeaderSize must be a positive integer: " + var2);
      } else if (var3 <= 0) {
         throw new IllegalArgumentException("maxChunkSize must be a positive integer: " + var3);
      } else {
         this.maxInitialLineLength = var1;
         this.maxHeaderSize = var2;
         this.maxChunkSize = var3;
         this.chunkedSupported = var4;
         this.validateHeaders = var5;
      }
   }

   public HttpContent invalidChunk(Exception var1) {
      this.checkpoint(HttpObjectDecoder$State.BAD_MESSAGE);
      DefaultLastHttpContent var2 = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER);
      var2.setDecoderResult(DecoderResult.failure(var1));
      this.message = null;
      return var2;
   }

   public abstract HttpMessage createMessage(String[] var1);

   public HttpMessage invalidMessage(Exception var1) {
      this.checkpoint(HttpObjectDecoder$State.BAD_MESSAGE);
      if (this.message != null) {
         this.message.setDecoderResult(DecoderResult.failure(var1));
      } else {
         this.message = this.createInvalidMessage();
         this.message.setDecoderResult(DecoderResult.failure(var1));
      }

      HttpMessage var2 = this.message;
      this.message = null;
      return var2;
   }

   public static int findWhitespace(CharSequence var0, int var1) {
      int var2 = var1;

      while (var2 < var0.length() && !Character.isWhitespace(var0.charAt(var2))) {
         var2++;
      }

      return var2;
   }

   public boolean isContentAlwaysEmpty(HttpMessage var1) {
      if (var1 instanceof HttpResponse) {
         HttpResponse var2 = (HttpResponse)var1;
         int var3 = var2.getStatus().code();
         if (var3 >= 100 && var3 < 200) {
            return var3 != 101 || var2.headers().contains("Sec-WebSocket-Accept");
         }

         switch (var3) {
            case 204:
            case 205:
            case 304:
               return true;
         }
      }

      return false;
   }

   public static int getChunkSize(String var0) {
      var0 = var0.trim();

      for (int var1 = 0; var1 < var0.length(); var1++) {
         char var2 = var0.charAt(var1);
         if (var2 == ';' || Character.isWhitespace(var2) || Character.isISOControl(var2)) {
            var0 = var0.substring(0, var1);
            break;
         }
      }

      return Integer.parseInt(var0, 16);
   }

   public HttpObjectDecoder$State readHeaders(ByteBuf var1) {
      this.headerSize = 0;
      HttpMessage var2 = this.message;
      HttpHeaders var3 = var2.headers();
      AppendableCharSequence var4 = this.headerParser.parse(var1);
      String var5 = null;
      String var6 = null;
      if (var4.length() > 0) {
         var3.clear();

         do {
            char var7 = var4.charAt(0);
            if (var5 == null || var7 != ' ' && var7 != '\t') {
               if (var5 != null) {
                  var3.add(var5, var6);
               }

               String[] var8 = splitHeader(var4);
               var5 = var8[0];
               var6 = var8[1];
            } else {
               var6 = var6 + ' ' + var4.toString().trim();
            }

            var4 = this.headerParser.parse(var1);
         } while (var4.length() > 0);

         if (var5 != null) {
            var3.add(var5, var6);
         }
      }

      HttpObjectDecoder$State var9;
      if (this.isContentAlwaysEmpty(var2)) {
         HttpHeaders.removeTransferEncodingChunked(var2);
         var9 = HttpObjectDecoder$State.SKIP_CONTROL_CHARS;
      } else if (HttpHeaders.isTransferEncodingChunked(var2)) {
         var9 = HttpObjectDecoder$State.READ_CHUNK_SIZE;
      } else if (this.contentLength() >= (7864487435007821856L & -7864487436066115447L)) {
         var9 = HttpObjectDecoder$State.READ_FIXED_LENGTH_CONTENT;
      } else {
         var9 = HttpObjectDecoder$State.READ_VARIABLE_LENGTH_CONTENT;
      }

      return var9;
   }

   public static int findNonWhitespace(CharSequence var0, int var1) {
      int var2 = var1;

      while (var2 < var0.length() && Character.isWhitespace(var0.charAt(var2))) {
         var2++;
      }

      return var2;
   }

   @Override
   public void decodeLast(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      this.decode(var1, var2, var3);
      if (this.message != null) {
         boolean var4;
         if (this.isDecodingRequest()) {
            var4 = true;
         } else {
            var4 = this.contentLength() > (17272996L & 308293448L);
         }

         this.reset();
         if (!var4) {
            var3.add(LastHttpContent.EMPTY_LAST_CONTENT);
         }
      }
   }

   public static void skipControlCharacters(ByteBuf var0) {
      char var1;
      do {
         var1 = (char)var0.readUnsignedByte();
      } while (Character.isISOControl(var1) || Character.isWhitespace(var1));

      var0.readerIndex(var0.readerIndex() - 1);
   }

   public long contentLength() {
      if (this.contentLength == (-9223372035739471226L & -9223372036846351360L)) {
         this.contentLength = HttpHeaders.getContentLength(this.message, -1L & -1L);
      }

      return this.contentLength;
   }

   public void reset() {
      HttpMessage var1 = this.message;
      this.message = null;
      this.contentLength = -9223372036574801792L & -9223372036787369976L;
      if (!this.isDecodingRequest()) {
         HttpResponse var2 = (HttpResponse)var1;
         if (var2 != null && var2.getStatus().code() == 101) {
            this.checkpoint(HttpObjectDecoder$State.UPGRADED);
            return;
         }
      }

      this.checkpoint(HttpObjectDecoder$State.SKIP_CONTROL_CHARS);
   }

   public static String[] splitHeader(AppendableCharSequence var0) {
      int var1 = var0.length();
      int var2 = findNonWhitespace(var0, 0);

      int var3;
      for (var3 = var2; var3 < var1; var3++) {
         char var7 = var0.charAt(var3);
         if (var7 == ':' || Character.isWhitespace(var7)) {
            break;
         }
      }

      int var4;
      for (var4 = var3; var4 < var1; var4++) {
         if (var0.charAt(var4) == ':') {
            var4++;
            break;
         }
      }

      int var5 = findNonWhitespace(var0, var4);
      if (var5 == var1) {
         return new String[]{var0.substring(var2, var3), ""};
      } else {
         int var6 = findEndOfString(var0);
         return new String[]{var0.substring(var2, var3), var0.substring(var5, var6)};
      }
   }

   public static String[] splitInitialLine(AppendableCharSequence var0) {
      int var1 = findNonWhitespace(var0, 0);
      int var2 = findWhitespace(var0, var1);
      int var3 = findNonWhitespace(var0, var2);
      int var4 = findWhitespace(var0, var3);
      int var5 = findNonWhitespace(var0, var4);
      int var6 = findEndOfString(var0);
      return new String[]{var0.substring(var1, var2), var0.substring(var3, var4), var5 < var6 ? var0.substring(var5, var6) : ""};
   }

   public static int findEndOfString(CharSequence var0) {
      int var1 = var0.length();

      while (var1 > 0 && Character.isWhitespace(var0.charAt(var1 - 1))) {
         var1--;
      }

      return var1;
   }
}
