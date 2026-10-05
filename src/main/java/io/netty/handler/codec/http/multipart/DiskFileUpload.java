package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javazoom.jl.player.advanced.AdvancedPlayer;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.optifine.shaders.config.ShaderMacros;

public class DiskFileUpload extends AbstractDiskHttpData implements FileUpload {
   public static final String prefix = "FUp_";
   public String filename;
   public static String baseDirectory;
   public static final String postfix = ".tmp";
   public static boolean deleteOnExitTemporaryFile = true;
   public String contentType;
   public String contentTransferEncoding;

   @Override
   public String getFilename() {
      return this.filename;
   }

   @Override
   public void setContentTransferEncoding(String var1) {
      this.contentTransferEncoding = var1;
   }

   @Override
   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return InterfaceHttpData.HttpDataType.FileUpload;
   }

   public int compareTo(FileUpload var1) {
      int var2 = this.getName().compareToIgnoreCase(var1.getName());
      return var2 != 0 ? var2 : var2;
   }

   @Override
   public String getBaseDirectory() {
      return baseDirectory;
   }

   @Override
   public String getContentType() {
      return this.contentType;
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
   public FileUpload retain() {
      super.retain();
      return this;
   }

   @Override
   public boolean deleteOnExit() {
      return deleteOnExitTemporaryFile;
   }

   public int compareTo(InterfaceHttpData var1) {
      if (!(var1 instanceof FileUpload)) {
         throw new ClassCastException("Cannot compare " + this.getHttpDataType() + " with " + var1.getHttpDataType());
      } else {
         return this.compareTo((FileUpload)var1);
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
   public int hashCode() {
      return this.getName().hashCode();
   }

   @Override
   public FileUpload duplicate() {
      DiskFileUpload var1 = new DiskFileUpload(
         this.getName(), this.getFilename(), this.getContentType(), this.getContentTransferEncoding(), this.getCharset(), this.size
      );
      ByteBuf var2 = this.content();
      if (var2 != null) {
         try {
            var1.setContent(var2.duplicate());
         } catch (IOException var4) {
            throw new ChannelException(var4);
         }
      }

      return var1;
   }

   @Override
   public FileUpload copy() {
      DiskFileUpload var1 = new DiskFileUpload(
         this.getName(), this.getFilename(), this.getContentType(), this.getContentTransferEncoding(), this.getCharset(), this.size
      );
      ByteBuf var2 = this.content();
      if (var2 != null) {
         try {
            var1.setContent(var2.copy());
         } catch (IOException var4) {
            throw new ChannelException(var4);
         }
      }

      return var1;
   }

   @Override
   public String getPostfix() {
      return ".tmp";
   }

   @Override
   public String getContentTransferEncoding() {
      return this.contentTransferEncoding;
   }

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
         + this.isInMemory()
         + "\r\nRealFile: "
         + (this.file != null ? this.file.getAbsolutePath() : "null")
         + " DefaultDeleteAfter: "
         + deleteOnExitTemporaryFile;
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
   public FileUpload retain(int var1) {
      super.retain(var1);
      return this;
   }

   @Override
   public String getPrefix() {
      return "FUp_";
   }

   public DiskFileUpload(String var1, String var2, String var3, String var4, Charset var5, long var6) {
      super(var1, var5, var6);
      this.setFilename(var2);
      this.setContentType(var3);
      this.setContentTransferEncoding(var4);
   }

   @Override
   public String getDiskFilename() {
      File var1 = new File(this.filename);
      return var1.getName();
   }
}
