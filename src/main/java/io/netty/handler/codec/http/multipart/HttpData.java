package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;

public interface HttpData extends ByteBufHolder, InterfaceHttpData {
   byte[] get() throws java.io.IOException ;

   void addContent(ByteBuf var1, boolean var2) throws java.io.IOException ;

   HttpData retain(int var1);

   void delete();

   void setCharset(Charset var1);

   String getString(Charset var1) throws java.io.IOException ;

   ByteBuf getByteBuf() throws java.io.IOException ;

   void setContent(ByteBuf var1) throws java.io.IOException ;

   boolean isInMemory();

   void setContent(InputStream var1) throws java.io.IOException ;

   HttpData retain();

   boolean renameTo(File var1) throws java.io.IOException ;

   String getString() throws java.io.IOException ;

   long length();

   HttpData copy();

   HttpData duplicate();

   boolean isCompleted();

   void setContent(File var1) throws java.io.IOException ;

   Charset getCharset();

   File getFile() throws java.io.IOException ;

   ByteBuf getChunk(int var1) throws java.io.IOException ;
}
