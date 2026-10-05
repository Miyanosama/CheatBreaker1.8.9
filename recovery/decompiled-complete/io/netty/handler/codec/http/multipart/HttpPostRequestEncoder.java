package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.stream.ChunkedInput;
import io.netty.util.internal.ThreadLocalRandom;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import net.minecraft.client.network.NetHandlerPlayClient$3;

public class HttpPostRequestEncoder implements ChunkedInput<HttpContent> {
   public Charset charset;
   public HttpPostRequestEncoder$EncoderMode encoderMode;
   public static Map<Pattern, String> percentEncodings = new HashMap<>();
   public String multipartDataBoundary;
   public ListIterator<InterfaceHttpData> iterator;
   public ByteBuf currentBuffer;
   public boolean isKey = true;
   public InterfaceHttpData currentData;
   public List<InterfaceHttpData> bodyListDatas;
   public String multipartMixedBoundary;
   public HttpDataFactory factory;
   public boolean isMultipart;
   public List<InterfaceHttpData> multipartHttpDatas;
   public boolean duringMixedMode;
   public FileUpload currentFileUpload;
   public long globalBodySize;
   public boolean isChunked;
   public boolean isLastChunkSent;
   public NetHandlerPlayClient$3 __junk1357079981522329704;
   public boolean isLastChunk;
   public HttpRequest request;
   public boolean headerFinalized;

   public static String getNewMultipartDelimiter() {
      return Long.toHexString(ThreadLocalRandom.current().nextLong()).toLowerCase();
   }

   public HttpRequest finalizeRequest() {
      if (!this.headerFinalized) {
         if (this.isMultipart) {
            InternalAttribute var1 = new InternalAttribute(this.charset);
            if (this.duringMixedMode) {
               var1.addValue("\r\n--" + this.multipartMixedBoundary + "--");
            }

            var1.addValue("\r\n--" + this.multipartDataBoundary + "--\r\n");
            this.multipartHttpDatas.add(var1);
            this.multipartMixedBoundary = null;
            this.currentFileUpload = null;
            this.duringMixedMode = false;
            this.globalBodySize = this.globalBodySize + var1.size();
         }

         this.headerFinalized = true;
         HttpHeaders var9 = this.request.headers();
         List var2 = var9.getAll("Content-Type");
         List var3 = var9.getAll("Transfer-Encoding");
         if (var2 != null) {
            var9.remove("Content-Type");

            for (String var5 : var2) {
               String var6 = var5.toLowerCase();
               if (!var6.startsWith("multipart/form-data") && !var6.startsWith("application/x-www-form-urlencoded")) {
                  var9.add("Content-Type", var5);
               }
            }
         }

         if (this.isMultipart) {
            String var10 = "multipart/form-data; boundary=" + this.multipartDataBoundary;
            var9.add("Content-Type", var10);
         } else {
            var9.add("Content-Type", "application/x-www-form-urlencoded");
         }

         long var11 = this.globalBodySize;
         if (this.isMultipart) {
            this.iterator = this.multipartHttpDatas.listIterator();
         } else {
            var11 -= 170426409L & 16529L;
            this.iterator = this.multipartHttpDatas.listIterator();
         }

         var9.set("Content-Length", String.valueOf(var11));
         if (var11 <= (-3093227571437428821L & 68206564L) && !this.isMultipart) {
            HttpContent var13 = this.nextChunk();
            if (this.request instanceof FullHttpRequest) {
               FullHttpRequest var14 = (FullHttpRequest)this.request;
               ByteBuf var8 = var13.content();
               if (var14.content() != var8) {
                  var14.content().clear().writeBytes(var8);
                  var8.release();
               }

               return var14;
            } else {
               return new HttpPostRequestEncoder$WrappedFullHttpRequest(this.request, var13, null);
            }
         } else {
            this.isChunked = true;
            if (var3 != null) {
               var9.remove("Transfer-Encoding");

               for (String var7 : var3) {
                  if (!var7.equalsIgnoreCase("chunked")) {
                     var9.add("Transfer-Encoding", var7);
                  }
               }
            }

            HttpHeaders.setTransferEncodingChunked(this.request);
            return new HttpPostRequestEncoder$WrappedHttpRequest(this.request);
         }
      } else {
         throw new HttpPostRequestEncoder$ErrorDataEncoderException("Header already encoded");
      }
   }

   public void initDataMultipart() {
      this.multipartDataBoundary = getNewMultipartDelimiter();
   }

   public HttpPostRequestEncoder(HttpDataFactory var1, HttpRequest var2, boolean var3) {
      this(var1, var2, var3, HttpConstants.DEFAULT_CHARSET, HttpPostRequestEncoder$EncoderMode.RFC1738);
   }

   public List<InterfaceHttpData> getBodyListAttributes() {
      return this.bodyListDatas;
   }

   public void cleanFiles() {
      this.factory.cleanRequestHttpDatas(this.request);
   }

   public void addBodyAttribute(String var1, String var2) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         String var3 = var2;
         if (var2 == null) {
            var3 = "";
         }

         Attribute var4 = this.factory.createAttribute(this.request, var1, var3);
         this.addBodyHttpData(var4);
      }
   }

   public boolean isMultipart() {
      return this.isMultipart;
   }

   public void setBodyHttpDatas(List<InterfaceHttpData> var1) {
      if (var1 == null) {
         throw new NullPointerException("datas");
      } else {
         this.globalBodySize = 7961407061454110753L & 1614087186L;
         this.bodyListDatas.clear();
         this.currentFileUpload = null;
         this.duringMixedMode = false;
         this.multipartHttpDatas.clear();

         for (InterfaceHttpData var3 : var1) {
            this.addBodyHttpData(var3);
         }
      }
   }

   public HttpContent readChunk(ChannelHandlerContext var1) {
      return this.isLastChunkSent ? null : this.nextChunk();
   }

   public boolean isChunked() {
      return this.isChunked;
   }

   public ByteBuf fillByteBuf() {
      int var1 = this.currentBuffer.readableBytes();
      if (var1 > 8096) {
         ByteBuf var3 = this.currentBuffer.slice(this.currentBuffer.readerIndex(), 8096);
         this.currentBuffer.skipBytes(8096);
         return var3;
      } else {
         ByteBuf var2 = this.currentBuffer;
         this.currentBuffer = null;
         return var2;
      }
   }

   public void initMixedMultipart() {
      this.multipartMixedBoundary = getNewMultipartDelimiter();
   }

   public void addBodyHttpData(InterfaceHttpData var1) {
      if (this.headerFinalized) {
         throw new HttpPostRequestEncoder$ErrorDataEncoderException("Cannot add value once finalized");
      } else if (var1 == null) {
         throw new NullPointerException("data");
      } else {
         this.bodyListDatas.add(var1);
         if (!this.isMultipart) {
            if (var1 instanceof Attribute) {
               Attribute var10 = (Attribute)var1;

               try {
                  String var13 = this.encodeAttribute(var10.getName(), this.charset);
                  String var16 = this.encodeAttribute(var10.getValue(), this.charset);
                  Attribute var19 = this.factory.createAttribute(this.request, var13, var16);
                  this.multipartHttpDatas.add(var19);
                  this.globalBodySize = this.globalBodySize + var19.getName().length() + 1 + var19.length() + (-9091711240322932219L & 1091700737L);
               } catch (IOException var7) {
                  throw new HttpPostRequestEncoder$ErrorDataEncoderException(var7);
               }
            } else if (var1 instanceof FileUpload) {
               FileUpload var11 = (FileUpload)var1;
               String var14 = this.encodeAttribute(var11.getName(), this.charset);
               String var17 = this.encodeAttribute(var11.getFilename(), this.charset);
               Attribute var20 = this.factory.createAttribute(this.request, var14, var17);
               this.multipartHttpDatas.add(var20);
               this.globalBodySize = this.globalBodySize + var20.getName().length() + 1 + var20.length() + (4330411517907304649L & -4330411518554797513L);
            }
         } else {
            if (var1 instanceof Attribute) {
               if (this.duringMixedMode) {
                  InternalAttribute var2 = new InternalAttribute(this.charset);
                  var2.addValue("\r\n--" + this.multipartMixedBoundary + "--");
                  this.multipartHttpDatas.add(var2);
                  this.multipartMixedBoundary = null;
                  this.currentFileUpload = null;
                  this.duringMixedMode = false;
               }

               InternalAttribute var8 = new InternalAttribute(this.charset);
               if (!this.multipartHttpDatas.isEmpty()) {
                  var8.addValue("\r\n");
               }

               var8.addValue("--" + this.multipartDataBoundary + "\r\n");
               Attribute var3 = (Attribute)var1;
               var8.addValue("Content-Disposition: form-data; name=\"" + var3.getName() + "\"\r\n");
               Charset var4 = var3.getCharset();
               if (var4 != null) {
                  var8.addValue("Content-Type: text/plain; charset=" + var4 + "\r\n");
               }

               var8.addValue("\r\n");
               this.multipartHttpDatas.add(var8);
               this.multipartHttpDatas.add(var1);
               this.globalBodySize = this.globalBodySize + var3.length() + var8.size();
            } else if (var1 instanceof FileUpload) {
               FileUpload var9 = (FileUpload)var1;
               InternalAttribute var12 = new InternalAttribute(this.charset);
               if (!this.multipartHttpDatas.isEmpty()) {
                  var12.addValue("\r\n");
               }

               boolean var15;
               if (this.duringMixedMode) {
                  if (this.currentFileUpload != null && this.currentFileUpload.getName().equals(var9.getName())) {
                     var15 = true;
                  } else {
                     var12.addValue("--" + this.multipartMixedBoundary + "--");
                     this.multipartHttpDatas.add(var12);
                     this.multipartMixedBoundary = null;
                     var12 = new InternalAttribute(this.charset);
                     var12.addValue("\r\n");
                     var15 = false;
                     this.currentFileUpload = var9;
                     this.duringMixedMode = false;
                  }
               } else if (this.currentFileUpload != null && this.currentFileUpload.getName().equals(var9.getName())) {
                  this.initMixedMultipart();
                  InternalAttribute var5 = (InternalAttribute)this.multipartHttpDatas.get(this.multipartHttpDatas.size() - 2);
                  this.globalBodySize = this.globalBodySize - var5.size();
                  StringBuilder var6 = new StringBuilder(
                     139
                        + this.multipartDataBoundary.length()
                        + this.multipartMixedBoundary.length() * 2
                        + var9.getFilename().length()
                        + var9.getName().length()
                  );
                  var6.append("--");
                  var6.append(this.multipartDataBoundary);
                  var6.append("\r\n");
                  var6.append("Content-Disposition");
                  var6.append(": ");
                  var6.append("form-data");
                  var6.append("; ");
                  var6.append("name");
                  var6.append("=\"");
                  var6.append(var9.getName());
                  var6.append("\"\r\n");
                  var6.append("Content-Type");
                  var6.append(": ");
                  var6.append("multipart/mixed");
                  var6.append("; ");
                  var6.append("boundary");
                  var6.append('=');
                  var6.append(this.multipartMixedBoundary);
                  var6.append("\r\n\r\n");
                  var6.append("--");
                  var6.append(this.multipartMixedBoundary);
                  var6.append("\r\n");
                  var6.append("Content-Disposition");
                  var6.append(": ");
                  var6.append("attachment");
                  var6.append("; ");
                  var6.append("filename");
                  var6.append("=\"");
                  var6.append(var9.getFilename());
                  var6.append("\"\r\n");
                  var5.setValue(var6.toString(), 1);
                  var5.setValue("", 2);
                  this.globalBodySize = this.globalBodySize + var5.size();
                  var15 = true;
                  this.duringMixedMode = true;
               } else {
                  var15 = false;
                  this.currentFileUpload = var9;
                  this.duringMixedMode = false;
               }

               if (var15) {
                  var12.addValue("--" + this.multipartMixedBoundary + "\r\n");
                  var12.addValue("Content-Disposition: attachment; filename=\"" + var9.getFilename() + "\"\r\n");
               } else {
                  var12.addValue("--" + this.multipartDataBoundary + "\r\n");
                  var12.addValue("Content-Disposition: form-data; name=\"" + var9.getName() + "\"; " + "filename" + "=\"" + var9.getFilename() + "\"\r\n");
               }

               var12.addValue("Content-Type: " + var9.getContentType());
               String var18 = var9.getContentTransferEncoding();
               if (var18 != null && var18.equals(HttpPostBodyUtil$TransferEncodingMechanism.BINARY.value())) {
                  var12.addValue("\r\nContent-Transfer-Encoding: " + HttpPostBodyUtil$TransferEncodingMechanism.BINARY.value() + "\r\n\r\n");
               } else if (var9.getCharset() != null) {
                  var12.addValue("; charset=" + var9.getCharset() + "\r\n\r\n");
               } else {
                  var12.addValue("\r\n\r\n");
               }

               this.multipartHttpDatas.add(var12);
               this.multipartHttpDatas.add(var1);
               this.globalBodySize = this.globalBodySize + var9.length() + var12.size();
            }
         }
      }
   }

   public void addBodyFileUploads(String var1, File[] var2, String[] var3, boolean[] var4) {
      if (var2.length != var3.length && var2.length != var4.length) {
         throw new NullPointerException("Different array length");
      } else {
         for (int var5 = 0; var5 < var2.length; var5++) {
            this.addBodyFileUpload(var1, var2[var5], var3[var5], var4[var5]);
         }
      }
   }

   public HttpContent encodeNextChunkMultipart(int var1) {
      if (this.currentData == null) {
         return null;
      } else {
         ByteBuf var2;
         if (this.currentData instanceof InternalAttribute) {
            var2 = ((InternalAttribute)this.currentData).toByteBuf();
            this.currentData = null;
         } else {
            if (this.currentData instanceof Attribute) {
               try {
                  var2 = ((Attribute)this.currentData).getChunk(var1);
               } catch (IOException var5) {
                  throw new HttpPostRequestEncoder$ErrorDataEncoderException(var5);
               }
            } else {
               try {
                  var2 = ((HttpData)this.currentData).getChunk(var1);
               } catch (IOException var4) {
                  throw new HttpPostRequestEncoder$ErrorDataEncoderException(var4);
               }
            }

            if (var2.capacity() == 0) {
               this.currentData = null;
               return null;
            }
         }

         if (this.currentBuffer == null) {
            this.currentBuffer = var2;
         } else {
            this.currentBuffer = Unpooled.wrappedBuffer(this.currentBuffer, var2);
         }

         if (this.currentBuffer.readableBytes() < 8096) {
            this.currentData = null;
            return null;
         } else {
            var2 = this.fillByteBuf();
            return new DefaultHttpContent(var2);
         }
      }
   }

   @Override
   public void close() {
   }

   @Override
   public boolean isEndOfInput() {
      return this.isLastChunkSent;
   }

   static {
      percentEncodings.put(Pattern.compile("\\*"), "%2A");
      percentEncodings.put(Pattern.compile("\\+"), "%20");
      percentEncodings.put(Pattern.compile("%7E"), "~");
   }

   public HttpPostRequestEncoder(HttpRequest var1, boolean var2) {
      this(new DefaultHttpDataFactory(218253577L & 1886962246L), var1, var2, HttpConstants.DEFAULT_CHARSET, HttpPostRequestEncoder$EncoderMode.RFC1738);
   }

   public String encodeAttribute(String var1, Charset var2) {
      if (var1 == null) {
         return "";
      } else {
         try {
            String var3 = URLEncoder.encode(var1, var2.name());
            if (this.encoderMode == HttpPostRequestEncoder$EncoderMode.RFC3986) {
               for (Entry var5 : percentEncodings.entrySet()) {
                  String var6 = (String)var5.getValue();
                  var3 = ((Pattern)var5.getKey()).matcher(var3).replaceAll(var6);
               }
            }

            return var3;
         } catch (UnsupportedEncodingException var7) {
            throw new HttpPostRequestEncoder$ErrorDataEncoderException(var2.name(), var7);
         }
      }
   }

   public HttpContent encodeNextChunkUrlEncoded(int var1) {
      if (this.currentData == null) {
         return null;
      } else {
         int var2 = var1;
         if (this.isKey) {
            String var4 = this.currentData.getName();
            ByteBuf var3 = Unpooled.wrappedBuffer(var4.getBytes());
            this.isKey = false;
            if (this.currentBuffer == null) {
               this.currentBuffer = Unpooled.wrappedBuffer(var3, Unpooled.wrappedBuffer("=".getBytes()));
               var2 = var1 - (var3.readableBytes() + 1);
            } else {
               this.currentBuffer = Unpooled.wrappedBuffer(this.currentBuffer, var3, Unpooled.wrappedBuffer("=".getBytes()));
               var2 = var1 - (var3.readableBytes() + 1);
            }

            if (this.currentBuffer.readableBytes() >= 8096) {
               var3 = this.fillByteBuf();
               return new DefaultHttpContent(var3);
            }
         }

         ByteBuf var6;
         try {
            var6 = ((HttpData)this.currentData).getChunk(var2);
         } catch (IOException var5) {
            throw new HttpPostRequestEncoder$ErrorDataEncoderException(var5);
         }

         ByteBuf var10 = null;
         if (var6.readableBytes() < var2) {
            this.isKey = true;
            var10 = this.iterator.hasNext() ? Unpooled.wrappedBuffer("&".getBytes()) : null;
         }

         if (var6.capacity() == 0) {
            this.currentData = null;
            if (this.currentBuffer == null) {
               this.currentBuffer = var10;
            } else if (var10 != null) {
               this.currentBuffer = Unpooled.wrappedBuffer(this.currentBuffer, var10);
            }

            if (this.currentBuffer.readableBytes() >= 8096) {
               var6 = this.fillByteBuf();
               return new DefaultHttpContent(var6);
            } else {
               return null;
            }
         } else {
            if (this.currentBuffer == null) {
               if (var10 != null) {
                  this.currentBuffer = Unpooled.wrappedBuffer(var6, var10);
               } else {
                  this.currentBuffer = var6;
               }
            } else if (var10 != null) {
               this.currentBuffer = Unpooled.wrappedBuffer(this.currentBuffer, var6, var10);
            } else {
               this.currentBuffer = Unpooled.wrappedBuffer(this.currentBuffer, var6);
            }

            if (this.currentBuffer.readableBytes() < 8096) {
               this.currentData = null;
               this.isKey = true;
               return null;
            } else {
               var6 = this.fillByteBuf();
               return new DefaultHttpContent(var6);
            }
         }
      }
   }

   public HttpContent nextChunk() {
      if (this.isLastChunk) {
         this.isLastChunkSent = true;
         return LastHttpContent.EMPTY_LAST_CONTENT;
      } else {
         int var2 = 8096;
         if (this.currentBuffer != null) {
            var2 -= this.currentBuffer.readableBytes();
         }

         if (var2 <= 0) {
            ByteBuf var5 = this.fillByteBuf();
            return new DefaultHttpContent(var5);
         } else {
            if (this.currentData != null) {
               if (this.isMultipart) {
                  HttpContent var3 = this.encodeNextChunkMultipart(var2);
                  if (var3 != null) {
                     return var3;
                  }
               } else {
                  HttpContent var6 = this.encodeNextChunkUrlEncoded(var2);
                  if (var6 != null) {
                     return var6;
                  }
               }

               var2 = 8096 - this.currentBuffer.readableBytes();
            }

            if (!this.iterator.hasNext()) {
               this.isLastChunk = true;
               ByteBuf var4 = this.currentBuffer;
               this.currentBuffer = null;
               return new DefaultHttpContent(var4);
            } else {
               while (var2 > 0 && this.iterator.hasNext()) {
                  this.currentData = this.iterator.next();
                  HttpContent var7;
                  if (this.isMultipart) {
                     var7 = this.encodeNextChunkMultipart(var2);
                  } else {
                     var7 = this.encodeNextChunkUrlEncoded(var2);
                  }

                  if (var7 != null) {
                     return var7;
                  }

                  var2 = 8096 - this.currentBuffer.readableBytes();
               }

               this.isLastChunk = true;
               if (this.currentBuffer == null) {
                  this.isLastChunkSent = true;
                  return LastHttpContent.EMPTY_LAST_CONTENT;
               } else {
                  ByteBuf var1 = this.currentBuffer;
                  this.currentBuffer = null;
                  return new DefaultHttpContent(var1);
               }
            }
         }
      }
   }

   public HttpPostRequestEncoder(HttpDataFactory var1, HttpRequest var2, boolean var3, Charset var4, HttpPostRequestEncoder$EncoderMode var5) {
      if (var1 == null) {
         throw new NullPointerException("factory");
      } else if (var2 == null) {
         throw new NullPointerException("request");
      } else if (var4 == null) {
         throw new NullPointerException("charset");
      } else if (var2.getMethod() != HttpMethod.POST) {
         throw new HttpPostRequestEncoder$ErrorDataEncoderException("Cannot create a Encoder if not a POST");
      } else {
         this.request = var2;
         this.charset = var4;
         this.factory = var1;
         this.bodyListDatas = new ArrayList<>();
         this.isLastChunk = false;
         this.isLastChunkSent = false;
         this.isMultipart = var3;
         this.multipartHttpDatas = new ArrayList<>();
         this.encoderMode = var5;
         if (this.isMultipart) {
            this.initDataMultipart();
         }
      }
   }

   public void addBodyFileUpload(String var1, File var2, String var3, boolean var4) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else if (var2 == null) {
         throw new NullPointerException("file");
      } else {
         String var5 = var3;
         String var6 = null;
         if (var3 == null) {
            if (var4) {
               var5 = "text/plain";
            } else {
               var5 = "application/octet-stream";
            }
         }

         if (!var4) {
            var6 = HttpPostBodyUtil$TransferEncodingMechanism.BINARY.value();
         }

         FileUpload var7 = this.factory.createFileUpload(this.request, var1, var2.getName(), var5, var6, null, var2.length());

         try {
            var7.setContent(var2);
         } catch (IOException var9) {
            throw new HttpPostRequestEncoder$ErrorDataEncoderException(var9);
         }

         this.addBodyHttpData(var7);
      }
   }
}
