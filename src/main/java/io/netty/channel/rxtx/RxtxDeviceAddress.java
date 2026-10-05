package io.netty.channel.rxtx;

import io.netty.util.concurrent.FailedFuture;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import java.net.SocketAddress;

public class RxtxDeviceAddress extends SocketAddress {
   public String value;
   public static final long serialVersionUID = -2907820090993709523L;

   public RxtxDeviceAddress(String var1) {
      this.value = var1;
   }

   public String value() {
      return this.value;
   }
}
