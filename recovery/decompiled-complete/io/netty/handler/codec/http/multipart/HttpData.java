package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;

public interface HttpData extends ByteBufHolder, InterfaceHttpData {
   byte[] get();

   void addContent(ByteBuf var1, boolean var2);

   HttpData retain(int var1);

   void delete();

   void setCharset(Charset var1);

   String getString(Charset var1);

   ByteBuf getByteBuf();

   void setContent(ByteBuf var1);

   boolean isInMemory();

   void setContent(InputStream var1);

   HttpData retain();

   boolean renameTo(File var1);

   String getString();

   long length();

   HttpData copy();

   HttpData duplicate();

   boolean isCompleted();

   void setContent(File var1);

   Charset getCharset();

   File getFile();

   ByteBuf getChunk(int var1);
}
