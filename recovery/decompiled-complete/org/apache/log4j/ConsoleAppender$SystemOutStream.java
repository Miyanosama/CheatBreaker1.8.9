package org.apache.log4j;

import io.netty.channel.AdaptiveRecvByteBufAllocator$HandleImpl;
import java.io.OutputStream;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.util.IntHashMap$Entry;
import net.optifine.shaders.IteratorAxis;
import recovered.unidentified.UnidentifiedClass1858;

public class ConsoleAppender$SystemOutStream extends OutputStream {
   public IteratorAxis field_0002;
   public AdaptiveRecvByteBufAllocator$HandleImpl field_0004;
   public UnidentifiedClass1858 field_0001;
   public IntHashMap$Entry field_0003;
   public BlockPressurePlate field_0000;

   public void close() {
   }

   public void flush() {
      System.out.flush();
   }

   public void write(byte[] var1) {
      System.out.write(var1);
   }

   public void write(int var1) {
      System.out.write(var1);
   }

   public void write(byte[] var1, int var2, int var3) {
      System.out.write(var1, var2, var3);
   }
}
