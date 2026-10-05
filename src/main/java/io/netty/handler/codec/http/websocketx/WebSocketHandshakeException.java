package io.netty.handler.codec.http.websocketx;

import com.cheatbreaker.client.websocket.client.WSPacketClientRequestsStatus;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.gen.structure.MapGenStructureData;

public class WebSocketHandshakeException extends RuntimeException {
   public static final long serialVersionUID = 1L;

   public WebSocketHandshakeException(String var1) {
      super(var1);
   }

   public WebSocketHandshakeException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
