package io.netty.handler.codec.marshalling;

import io.netty.buffer.ByteBuf;
import javazoom.jl.decoder.LayerIDecoder;
import net.minecraft.util.Util;
import net.optifine.CustomPanorama;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryImmediateEditor;
import org.jboss.marshalling.ByteOutput;

public class ChannelBufferByteOutput implements ByteOutput {
   public ByteBuf buffer;

   @Override
   public void write(int var1) throws java.io.IOException {
      this.buffer.writeByte(var1);
   }

   @Override
   public void flush() throws java.io.IOException {
   }

   public ByteBuf getBuffer() {
      return this.buffer;
   }

   @Override
   public void close() throws java.io.IOException {
   }

   @Override
   public void write(byte[] var1) throws java.io.IOException {
      this.buffer.writeBytes(var1);
   }

   public ChannelBufferByteOutput(ByteBuf var1) {
      this.buffer = var1;
   }

   @Override
   public void write(byte[] var1, int var2, int var3) throws java.io.IOException {
      this.buffer.writeBytes(var1, var2, var3);
   }
}
