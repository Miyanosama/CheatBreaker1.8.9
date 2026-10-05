package io.netty.handler.codec.http.websocketx;

import io.netty.handler.codec.socks.SocksCmdResponseDecoder$State;
import javazoom.jl.decoder.LayerIIIDecoder$III_side_info_t;
import net.minecraft.util.JsonSerializableSet;
import org.apache.log4j.Hierarchy;

public enum WebSocketVersion {
   V08,
   V07,
   UNKNOWN,
   V13,
   V00;
   public Hierarchy __junk9107221892028295815;
   public JsonSerializableSet __junk5437743088955474701;
   public SocksCmdResponseDecoder$State __junk4022524959106649780;
   // $VF: synthetic field
   public static WebSocketVersion[] $VALUES = new WebSocketVersion[]{UNKNOWN, WebSocketVersion.V00, V07, V08, V13};
   public LayerIIIDecoder$III_side_info_t __junk1577560683734129596;

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
