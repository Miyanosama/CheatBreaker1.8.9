package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpHeaders$Values;
import java.io.IOException;
import net.minecraft.block.BlockSnowBlock;

public class DiskAttribute extends AbstractDiskHttpData implements Attribute {
   public HttpHeaders$Values __junk5262648894429730624;
   public BlockSnowBlock __junk3902060755507515911;
   public static String prefix;
   public static boolean deleteOnExitTemporaryFile = true;
   public static String postfix;
   public static String baseDirectory;

   @Override
   public Attribute duplicate() {
      DiskAttribute var1 = new DiskAttribute(this.getName());
      var1.setCharset(this.getCharset());
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
   public String getDiskFilename() {
      return this.getName() + ".att";
   }

   public DiskAttribute(String var1) {
      super(var1, HttpConstants.DEFAULT_CHARSET, 404753424L & 588775687L);
   }

   @Override
   public boolean deleteOnExit() {
      return deleteOnExitTemporaryFile;
   }

   @Override
   public InterfaceHttpData$HttpDataType getHttpDataType() {
      return InterfaceHttpData$HttpDataType.Attribute;
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

   public int compareTo(Attribute var1) {
      return this.getName().compareToIgnoreCase(var1.getName());
   }

   @Override
   public Attribute retain(int var1) {
      super.retain(var1);
      return this;
   }

   @Override
   public String getBaseDirectory() {
      return baseDirectory;
   }

   @Override
   public String getPrefix() {
      return "Attr_";
   }

   @Override
   public String toString() {
      try {
         return this.getName() + '=' + this.getValue();
      } catch (IOException var2) {
         return this.getName() + "=IoException";
      }
   }

   @Override
   public String getValue() {
      byte[] var1 = this.get();
      return new String(var1, this.charset.name());
   }

   @Override
   public void addContent(ByteBuf var1, boolean var2) {
      int var3 = var1.readableBytes();
      if (this.definedSize > (-7792983397820265984L & 75702288L) && this.definedSize < this.size + var3) {
         this.definedSize = this.size + var3;
      }

      super.addContent(var1, var2);
   }

   public DiskAttribute(String var1, String var2) {
      super(var1, HttpConstants.DEFAULT_CHARSET, -4300744041239918526L & 8454292L);
      this.setValue(var2);
   }

   @Override
   public void setValue(String var1) {
      if (var1 == null) {
         throw new NullPointerException("value");
      } else {
         byte[] var2 = var1.getBytes(this.charset.name());
         ByteBuf var3 = Unpooled.wrappedBuffer(var2);
         if (this.definedSize > (134758659L & -695834920785660320L)) {
            this.definedSize = var3.readableBytes();
         }

         this.setContent(var3);
      }
   }

   @Override
   public int hashCode() {
      return this.getName().hashCode();
   }

   @Override
   public Attribute copy() {
      DiskAttribute var1 = new DiskAttribute(this.getName());
      var1.setCharset(this.getCharset());
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
      return ".att";
   }

   @Override
   public Attribute retain() {
      super.retain();
      return this;
   }

   public int compareTo(InterfaceHttpData var1) {
      if (!(var1 instanceof Attribute)) {
         throw new ClassCastException("Cannot compare " + this.getHttpDataType() + " with " + var1.getHttpDataType());
      } else {
         return this.compareTo((Attribute)var1);
      }
   }
}
