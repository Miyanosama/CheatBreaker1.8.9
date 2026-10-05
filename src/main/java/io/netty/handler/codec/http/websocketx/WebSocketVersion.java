package io.netty.handler.codec.http.websocketx;

import io.netty.handler.codec.socks.SocksCmdResponseDecoder;
import javazoom.jl.decoder.LayerIIIDecoder;
import net.minecraft.util.JsonSerializableSet;
import org.apache.log4j.Hierarchy;

public enum WebSocketVersion {
      UNKNOWN,
      V00,
      V07,
      V08,
      V13;
   public static WebSocketVersion[] $VALUES = new WebSocketVersion[]{UNKNOWN, WebSocketVersion.V00, V07, V08, V13};

   public String toHttpHeaderValue() {
      if (this == V00) {
         return "0";
      } else if (this == V07) {
         return "7";
      } else if (this == V08) {
         return "8";
      } else if (this == V13) {
         return "13";
      } else {
         throw new IllegalStateException("Unknown web socket version: " + this);
      }
   }
}
