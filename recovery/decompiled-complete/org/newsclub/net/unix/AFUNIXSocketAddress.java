package org.newsclub.net.unix;

import io.netty.channel.ChannelFutureListener$3;
import java.io.File;
import java.net.InetSocketAddress;
import net.minecraft.world.gen.ChunkProviderFlat;

public class AFUNIXSocketAddress extends InetSocketAddress {
   public ChannelFutureListener$3 field_0001;
   public ChunkProviderFlat field_0003;
   public static long field_0000;
   public String field_0002;

   @Override
   public String toString() {
      return this.getClass().getName() + "[host=" + this.getHostName() + ";port=" + this.getPort() + ";file=" + this.field_0002 + "]";
   }

   public String method_01607() {
      return this.field_0002;
   }

   public AFUNIXSocketAddress(File var1, int var2) {
      super(0);
      if (var2 != 0) {
         NativeUnixSocket.setPort1(this, var2);
      }

      this.field_0002 = var1.getCanonicalPath();
   }

   public AFUNIXSocketAddress(File var1) {
      this(var1, 0);
   }
}
