package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.network.ServerStatusResponse;

public class MixedAttribute implements Attribute {
   public Attribute attribute;
   public long limitSize;

   @Override
   public Attribute copy() {
      return this.attribute.copy();
   }

   @Override
   public boolean release(int var1) {
      return this.attribute.release(var1);
   }

   @Override
   public Charset getCharset() {
      return this.attribute.getCharset();
   }

   @Override
   public File getFile() throws java.io.IOException {
      return this.attribute.getFile();
   }

   @Override
   public ByteBuf getByteBuf() throws java.io.IOException {
      return this.attribute.getByteBuf();
   }

   @Override
   public boolean isCompleted() {
      return this.attribute.isCompleted();
   }

   @Override
   public Attribute retain() {
      this.attribute.retain();
      return this;
   }

   @Override
   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return this.attribute.getHttpDataType();
   }

   @Override
   public String getName() {
      return this.attribute.getName();
   }

   public MixedAttribute(String var1, String var2, long var3) {
      this.limitSize = var3;
      if (var2.length() > this.limitSize) {
         try {
            this.attribute = new DiskAttribute(var1, var2);
         } catch (IOException var9) {
            try {
               this.attribute = new MemoryAttribute(var1, var2);
            } catch (IOException var8) {
               throw new IllegalArgumentException(var9);
            }
         }
      } else {
         try {
            this.attribute = new MemoryAttribute(var1, var2);
         } catch (IOException var7) {
            throw new IllegalArgumentException(var7);
         }
      }
   }

   @Override
   public long length() {
      return this.attribute.length();
   }

   @Override
   public int refCnt() {
      return this.attribute.refCnt();
   }

   @Override
   public void setCharset(Charset var1) {
      this.attribute.setCharset(var1);
   }

   @Override
   public Attribute duplicate() {
      return this.attribute.duplicate();
   }

   @Override
   public Attribute retain(int var1) {
      this.attribute.retain(var1);
      return this;
   }

   @Override
   public String getValue() throws java.io.IOException {
      return this.attribute.getValue();
   }

   public MixedAttribute(String var1, long var2) {
      this.limitSize = var2;
      this.attribute = new MemoryAttribute(var1);
   }

   @Override
   public void delete() {
      this.attribute.delete();
   }

   public int compareTo(InterfaceHttpData var1) {
      return this.attribute.compareTo(var1);
   }

   @Override
   public ByteBuf content() {
      return this.attribute.content();
   }

   @Override
   public void setContent(File var1) throws java.io.IOException {
      if (var1.length() > this.limitSize && this.attribute instanceof MemoryAttribute) {
         this.attribute = new DiskAttribute(this.attribute.getName());
      }

      this.attribute.setContent(var1);
   }

   @Override
   public String getString(Charset var1) throws java.io.IOException {
      return this.attribute.getString(var1);
   }

   @Override
   public String getString() throws java.io.IOException {
      return this.attribute.getString();
   }

   @Override
   public String toString() {
      return "Mixed: " + this.attribute.toString();
   }

   @Override
   public void setValue(String var1) throws java.io.IOException {
      this.attribute.setValue(var1);
   }

   @Override
   public boolean renameTo(File var1) throws java.io.IOException {
      return this.attribute.renameTo(var1);
   }

   @Override
   public boolean release() {
      return this.attribute.release();
   }

   @Override
   public void addContent(ByteBuf var1, boolean var2) throws java.io.IOException {
      if (this.attribute instanceof MemoryAttribute && this.attribute.length() + var1.readableBytes() > this.limitSize) {
         DiskAttribute var3 = new DiskAttribute(this.attribute.getName());
         if (((MemoryAttribute)this.attribute).getByteBuf() != null) {
            var3.addContent(((MemoryAttribute)this.attribute).getByteBuf(), false);
         }

         this.attribute = var3;
      }

      this.attribute.addContent(var1, var2);
   }

   @Override
   public boolean isInMemory() {
      return this.attribute.isInMemory();
   }

   @Override
   public ByteBuf getChunk(int var1) throws java.io.IOException {
      return this.attribute.getChunk(var1);
   }

   @Override
   public byte[] get() throws java.io.IOException {
      return this.attribute.get();
   }

   @Override
   public void setContent(ByteBuf var1) throws java.io.IOException {
      if (var1.readableBytes() > this.limitSize && this.attribute instanceof MemoryAttribute) {
         this.attribute = new DiskAttribute(this.attribute.getName());
      }

      this.attribute.setContent(var1);
   }

   @Override
   public void setContent(InputStream var1) throws java.io.IOException {
      if (this.attribute instanceof MemoryAttribute) {
         this.attribute = new DiskAttribute(this.attribute.getName());
      }

      this.attribute.setContent(var1);
   }
}
