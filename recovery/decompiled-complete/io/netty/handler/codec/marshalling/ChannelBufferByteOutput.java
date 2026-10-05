package io.netty.handler.codec.marshalling;

import io.netty.buffer.ByteBuf;
import javazoom.jl.decoder.LayerIDecoder$SubbandLayer1;
import net.minecraft.util.Util$EnumOS;
import net.optifine.CustomPanorama;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryImmediateEditor;
import org.jboss.marshalling.ByteOutput;

public class ChannelBufferByteOutput implements ByteOutput {
   public Util$EnumOS __junk4589931916022268182;
   public LayerIDecoder$SubbandLayer1 __junk219780256200578708;
   public ByteBuf buffer;
   public CustomPanorama __junk3049881410024759585;
   public CategoryImmediateEditor __junk3213030877791390663;

   public void write(int var1) {
      this.buffer.writeByte(var1);
   }

   public void flush() {
   }

   public ByteBuf getBuffer() {
      return this.buffer;
   }

   public void close() {
   }

   public void write(byte[] var1) {
      this.buffer.writeBytes(var1);
   }

   public ChannelBufferByteOutput(ByteBuf var1) {
      this.buffer = var1;
   }

   public void write(byte[] var1, int var2, int var3) {
      this.buffer.writeBytes(var1, var2, var3);
   }
}
