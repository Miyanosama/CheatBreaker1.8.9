package io.netty.handler.codec.marshalling;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityHanging;
import org.jboss.marshalling.ByteInput;

public class ChannelBufferByteInput implements ByteInput {
   public ByteBuf buffer;

   @Override
   public long skip(long var1) throws java.io.IOException {
      int var3 = this.buffer.readableBytes();
      if (var3 < var1) {
         var1 = var3;
      }

      this.buffer.readerIndex((int)(this.buffer.readerIndex() + var1));
      return var1;
   }

   @Override
   public int available() throws java.io.IOException {
      return this.buffer.readableBytes();
   }

   @Override
   public int read(byte[] var1) throws java.io.IOException {
      return this.read(var1, 0, var1.length);
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws java.io.IOException {
      int var4 = this.available();
      if (var4 == 0) {
         return -1;
      } else {
         var3 = Math.min(var4, var3);
         this.buffer.readBytes(var1, var2, var3);
         return var3;
      }
   }

   @Override
   public void close() throws java.io.IOException {
   }

   public ChannelBufferByteInput(ByteBuf var1) {
      this.buffer = var1;
   }

   @Override
   public int read() throws java.io.IOException {
      return this.buffer.isReadable() ? this.buffer.readByte() & 0xFF : -1;
   }
}
