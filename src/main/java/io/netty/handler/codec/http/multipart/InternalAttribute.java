package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.util.AbstractReferenceCounted;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.BlockStainedGlassPane;

public class InternalAttribute extends AbstractReferenceCounted implements InterfaceHttpData {
   public List<ByteBuf> value = new ArrayList<>();
   public int size;
   public Charset charset;

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof Attribute)) {
         return false;
      } else {
         Attribute var2 = (Attribute)var1;
         return this.getName().equalsIgnoreCase(var2.getName());
      }
   }

   public int size() {
      return this.size;
   }

   public ByteBuf toByteBuf() {
      return Unpooled.compositeBuffer().addComponents(this.value).writerIndex(this.size()).readerIndex(0);
   }

   @Override
   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return InterfaceHttpData.HttpDataType.InternalAttribute;
   }

   @Override
   public int hashCode() {
      return this.getName().hashCode();
   }

   public InternalAttribute(Charset var1) {
      this.charset = var1;
   }

   @Override
   public String getName() {
      return "InternalAttribute";
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (ByteBuf var3 : this.value) {
         var1.append(var3.toString(this.charset));
      }

      return var1.toString();
   }

   public void addValue(String var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException("value");
      } else {
         ByteBuf var3 = Unpooled.copiedBuffer(var1, this.charset);
         this.value.add(var2, var3);
         this.size = this.size + var3.readableBytes();
      }
   }

   public int compareTo(InterfaceHttpData var1) {
      if (!(var1 instanceof InternalAttribute)) {
         throw new ClassCastException("Cannot compare " + this.getHttpDataType() + " with " + var1.getHttpDataType());
      } else {
         return this.compareTo((InternalAttribute)var1);
      }
   }

   public void setValue(String var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException("value");
      } else {
         ByteBuf var3 = Unpooled.copiedBuffer(var1, this.charset);
         ByteBuf var4 = this.value.set(var2, var3);
         if (var4 != null) {
            this.size = this.size - var4.readableBytes();
            var4.release();
         }

         this.size = this.size + var3.readableBytes();
      }
   }

   public int compareTo(InternalAttribute var1) {
      return this.getName().compareToIgnoreCase(var1.getName());
   }

   @Override
   public void deallocate() {
   }

   public void addValue(String var1) {
      if (var1 == null) {
         throw new NullPointerException("value");
      } else {
         ByteBuf var2 = Unpooled.copiedBuffer(var1, this.charset);
         this.value.add(var2);
         this.size = this.size + var2.readableBytes();
      }
   }
}
