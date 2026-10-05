package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.HttpConstants;
import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$1;

public class MemoryAttribute extends AbstractMemoryHttpData implements Attribute {

   @Override
   public Attribute retain(int var1) {
      super.retain(var1);
      return this;
   }

   @Override
   public void addContent(ByteBuf var1, boolean var2) throws java.io.IOException {
      int var3 = var1.readableBytes();
      if (this.definedSize > 0L && this.definedSize < this.size + var3) {
         this.definedSize = this.size + var3;
      }

      super.addContent(var1, var2);
   }

   @Override
   public String toString() {
      return this.getName() + '=' + this.getValue();
   }

   @Override
   public int hashCode() {
      return this.getName().hashCode();
   }

   public MemoryAttribute(String var1) {
      super(var1, HttpConstants.DEFAULT_CHARSET, 0L);
   }

   @Override
   public String getValue() {
      return this.getByteBuf().toString(this.charset);
   }

   @Override
   public Attribute retain() {
      super.retain();
      return this;
   }

   @Override
   public Attribute duplicate() {
      MemoryAttribute var1 = new MemoryAttribute(this.getName());
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

   public MemoryAttribute(String var1, String var2) throws java.io.IOException {
      super(var1, HttpConstants.DEFAULT_CHARSET, 0L);
      this.setValue(var2);
   }

   @Override
   public void setValue(String var1) throws java.io.IOException {
      if (var1 == null) {
         throw new NullPointerException("value");
      } else {
         byte[] var2 = var1.getBytes(this.charset.name());
         ByteBuf var3 = Unpooled.wrappedBuffer(var2);
         if (this.definedSize > 0L) {
            this.definedSize = var3.readableBytes();
         }

         this.setContent(var3);
      }
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
   public Attribute copy() {
      MemoryAttribute var1 = new MemoryAttribute(this.getName());
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

   public int compareTo(InterfaceHttpData var1) {
      if (!(var1 instanceof Attribute)) {
         throw new ClassCastException("Cannot compare " + this.getHttpDataType() + " with " + var1.getHttpDataType());
      } else {
         return this.compareTo((Attribute)var1);
      }
   }

   @Override
   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return InterfaceHttpData.HttpDataType.Attribute;
   }

   public int compareTo(Attribute var1) {
      return this.getName().compareToIgnoreCase(var1.getName());
   }
}
