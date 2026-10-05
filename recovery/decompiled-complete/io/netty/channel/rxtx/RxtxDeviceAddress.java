package io.netty.channel.rxtx;

import io.netty.util.concurrent.FailedFuture;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachValueTask;
import java.net.SocketAddress;

public class RxtxDeviceAddress extends SocketAddress {
   public String value;
   public ConcurrentHashMapV8$ForEachValueTask __junk960749648880113007;
   public FailedFuture __junk6031639244640691658;
   public static long serialVersionUID;

   public RxtxDeviceAddress(String var1) {
      this.value = var1;
   }

   public String value() {
      return this.value;
   }
}
