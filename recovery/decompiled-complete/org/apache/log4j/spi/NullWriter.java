package org.apache.log4j.spi;

import io.netty.handler.codec.spdy.SpdyHttpHeaders$Names;
import java.io.Writer;
import net.minecraft.stats.ObjectiveStat;

public class NullWriter extends Writer {
   public ObjectiveStat field_0000;
   public SpdyHttpHeaders$Names field_0001;

   public void write(char[] var1, int var2, int var3) {
   }

   public void flush() {
   }

   public void close() {
   }
}
