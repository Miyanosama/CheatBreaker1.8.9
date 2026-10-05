package org.java_websocket.client;

import java.net.InetAddress;
import java.net.URI;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class WebSocketClient$1 implements DnsResolver {
   public S08PacketPlayerPosLook field_0001;

   @Override
   public InetAddress resolve(URI var1) {
      return InetAddress.getByName(var1.getHost());
   }

   public WebSocketClient$1(WebSocketClient var1) {
      this.this$0 = var1;
      super();
   }
}
