package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import net.minecraft.command.CommandParticle;
import net.minecraft.command.server.CommandListPlayers;
import org.json.JSONPointer;
import junit.swingui.TestRunner$14;

public class MixedFileUpload implements FileUpload {
   public long limitSize;
   public FileUpload fileUpload;
   public long definedSize;

   @Override
   public boolean release() {
      return this.fileUpload.release();
   }

   @Override
   public void setContentType(String var1) {
      this.fileUpload.setContentType(var1);
   }

   @Override
   public String getContentTransferEncoding() {
      return this.fileUpload.getContentTransferEncoding();
   }

   @Override
   public void setContentTransferEncoding(String var1) {
      this.fileUpload.setContentTransferEncoding(var1);
   }

   @Override
   public FileUpload duplicate() {
      return this.fileUpload.duplicate();
   }

   @Override
   public String getString(Charset var1) throws java.io.IOException {
      return this.fileUpload.getString(var1);
   }

   @Override
   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return this.fileUpload.getHttpDataType();
   }

   @Override
   public void setFilename(String var1) {
      this.fileUpload.setFilename(var1);
   }

   @Override
   public byte[] get() throws java.io.IOException {
      return this.fileUpload.get();
   }

   public int compareTo(InterfaceHttpData var1) {
      return this.fileUpload.compareTo(var1);
   }

   @Override
   public FileUpload copy() {
      return this.fileUpload.copy();
   }

   @Override
   public ByteBuf content() {
      return this.fileUpload.content();
   }

   @Override
   public boolean isInMemory() {
      return this.fileUpload.isInMemory();
   }

   @Override
   public long length() {
      return this.fileUpload.length();
   }

   @Override
   public File getFile() throws java.io.IOException {
      return this.fileUpload.getFile();
   }

   @Override
   public void setContent(InputStream var1) throws java.io.IOException {
      if (this.fileUpload instanceof MemoryFileUpload) {
         FileUpload var2 = this.fileUpload;
         this.fileUpload = new DiskFileUpload(
            this.fileUpload.getName(),
            this.fileUpload.getFilename(),
            this.fileUpload.getContentType(),
            this.fileUpload.getContentTransferEncoding(),
            this.fileUpload.getCharset(),
            this.definedSize
         );
         var2.release();
      }

      this.fileUpload.setContent(var1);
   }

   @Override
   public String getString() throws java.io.IOException {
      return this.fileUpload.getString();
   }

   @Override
   public String getFilename() {
      return this.fileUpload.getFilename();
   }

   @Override
   public void setCharset(Charset var1) {
      this.fileUpload.setCharset(var1);
   }

   @Override
   public void delete() {
      this.fileUpload.delete();
   }

   @Override
   public void addContent(ByteBuf var1, boolean var2) throws java.io.IOException {
      if (this.fileUpload instanceof MemoryFileUpload && this.fileUpload.length() + var1.readableBytes() > this.limitSize) {
         DiskFileUpload var3 = new DiskFileUpload(
            this.fileUpload.getName(),
            this.fileUpload.getFilename(),
            this.fileUpload.getContentType(),
            this.fileUpload.getContentTransferEncoding(),
            this.fileUpload.getCharset(),
            this.definedSize
         );
         ByteBuf var4 = this.fileUpload.getByteBuf();
         if (var4 != null && var4.isReadable()) {
            var3.addContent(var4.retain(), false);
         }

         this.fileUpload.release();
         this.fileUpload = var3;
      }

      this.fileUpload.addContent(var1, var2);
   }

   @Override
   public boolean isCompleted() {
      return this.fileUpload.isCompleted();
   }

   @Override
   public boolean renameTo(File var1) throws java.io.IOException {
      return this.fileUpload.renameTo(var1);
   }

   @Override
   public Charset getCharset() {
      return this.fileUpload.getCharset();
   }

   @Override
   public boolean release(int var1) {
      return this.fileUpload.release(var1);
   }

   @Override
   public String getContentType() {
      return this.fileUpload.getContentType();
   }

   @Override
   public ByteBuf getByteBuf() throws java.io.IOException {
      return this.fileUpload.getByteBuf();
   }

   @Override
   public void setContent(File var1) throws java.io.IOException {
      if (var1.length() > this.limitSize && this.fileUpload instanceof MemoryFileUpload) {
         FileUpload var2 = this.fileUpload;
         this.fileUpload = new DiskFileUpload(
            var2.getName(), var2.getFilename(), var2.getContentType(), var2.getContentTransferEncoding(), var2.getCharset(), this.definedSize
         );
         var2.release();
      }

      this.fileUpload.setContent(var1);
   }

   public MixedFileUpload(String var1, String var2, String var3, String var4, Charset var5, long var6, long var8) {
      this.limitSize = var8;
      if (var6 > this.limitSize) {
         this.fileUpload = new DiskFileUpload(var1, var2, var3, var4, var5, var6);
      } else {
         this.fileUpload = new MemoryFileUpload(var1, var2, var3, var4, var5, var6);
      }

      this.definedSize = var6;
   }

   @Override
   public String getName() {
      return this.fileUpload.getName();
   }

   @Override
   public void setContent(ByteBuf var1) throws java.io.IOException {
      if (var1.readableBytes() > this.limitSize && this.fileUpload instanceof MemoryFileUpload) {
         FileUpload var2 = this.fileUpload;
         this.fileUpload = new DiskFileUpload(
            var2.getName(), var2.getFilename(), var2.getContentType(), var2.getContentTransferEncoding(), var2.getCharset(), this.definedSize
         );
         var2.release();
      }

      this.fileUpload.setContent(var1);
   }

   @Override
   public int refCnt() {
      return this.fileUpload.refCnt();
   }

   @Override
   public String toString() {
      return "Mixed: " + this.fileUpload.toString();
   }

   @Override
   public ByteBuf getChunk(int var1) throws java.io.IOException {
      return this.fileUpload.getChunk(var1);
   }

   @Override
   public FileUpload retain() {
      this.fileUpload.retain();
      return this;
   }

   @Override
   public FileUpload retain(int var1) {
      this.fileUpload.retain(var1);
      return this;
   }
}
