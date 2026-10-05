package io.netty.handler.codec.http.multipart;

import com.cheatbreaker.client.ui.element.type.ColorPickerColorElement;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelException;
import java.io.IOException;
import java.nio.charset.Charset;

public class MemoryFileUpload extends AbstractMemoryHttpData implements FileUpload {
   public String contentTransferEncoding;
   public String contentType;
   public String filename;

   @Override
   public String toString() {
      return "Content-Disposition: form-data; name=\""
         + this.getName()
         + "\"; "
         + "filename"
         + "=\""
         + this.filename
         + "\"\r\n"
         + "Content-Type"
         + ": "
         + this.contentType
         + (this.charset != null ? "; charset=" + this.charset + "\r\n" : "\r\n")
         + "Content-Length"
         + ": "
         + this.length()
         + "\r\n"
         + "Completed: "
         + this.isCompleted()
         + "\r\nIsInMemory: "
         + this.isInMemory();
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof Attribute)) {
         return false;
      } else {
         Attribute var2 = (Attribute)var1;
         return this.getName().equalsIgnoreCase(var2.getName());
      }
   }

   @Override
   public void setContentType(String var1) {
      if (var1 == null) {
         throw new NullPointerException("contentType");
      } else {
         this.contentType = var1;
      }
   }

   @Override
   public void setFilename(String var1) {
      if (var1 == null) {
         throw new NullPointerException("filename");
      } else {
         this.filename = var1;
      }
   }

   @Override
   public String getFilename() {
      return this.filename;
   }

   @Override
   public FileUpload copy() {
      MemoryFileUpload var1 = new MemoryFileUpload(
         this.getName(), this.getFilename(), this.getContentType(), this.getContentTransferEncoding(), this.getCharset(), this.size
      );
      ByteBuf var2 = this.content();
      if (var2 != null) {
         try {
            var1.setContent(var2.copy());
            return var1;
         } catch (IOException var4) {
            throw new ChannelException(var4);
         }
      } else {
         return var1;
      }
   }

   @Override
   public FileUpload retain(int var1) {
      super.retain(var1);
      return this;
   }

   public MemoryFileUpload(String var1, String var2, String var3, String var4, Charset var5, long var6) {
      super(var1, var5, var6);
      this.setFilename(var2);
      this.setContentType(var3);
      this.setContentTransferEncoding(var4);
   }

   @Override
   public String getContentTransferEncoding() {
      return this.contentTransferEncoding;
   }

   public int compareTo(InterfaceHttpData var1) {
      if (!(var1 instanceof FileUpload)) {
         throw new ClassCastException("Cannot compare " + this.getHttpDataType() + " with " + var1.getHttpDataType());
      } else {
         return this.compareTo((FileUpload)var1);
      }
   }

   @Override
   public String getContentType() {
      return this.contentType;
   }

   @Override
   public void setContentTransferEncoding(String var1) {
      this.contentTransferEncoding = var1;
   }

   @Override
   public int hashCode() {
      return this.getName().hashCode();
   }

   @Override
   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return InterfaceHttpData.HttpDataType.FileUpload;
   }

   @Override
   public FileUpload duplicate() {
      MemoryFileUpload var1 = new MemoryFileUpload(
         this.getName(), this.getFilename(), this.getContentType(), this.getContentTransferEncoding(), this.getCharset(), this.size
      );
      ByteBuf var2 = this.content();
      if (var2 != null) {
         try {
            var1.setContent(var2.duplicate());
            return var1;
         } catch (IOException var4) {
            throw new ChannelException(var4);
         }
      } else {
         return var1;
      }
   }

   public int compareTo(FileUpload var1) {
      int var2 = this.getName().compareToIgnoreCase(var1.getName());
      return var2 != 0 ? var2 : var2;
   }

   @Override
   public FileUpload retain() {
      super.retain();
      return this;
   }
}
