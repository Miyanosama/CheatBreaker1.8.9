package io.netty.handler.codec.http.websocketx;

import com.cheatbreaker.client.websocket.client.WSPacketClientRequestsStatus;
import io.netty.channel.AbstractChannelHandlerContext$6;
import net.minecraft.scoreboard.Team$EnumVisible;
import net.minecraft.world.gen.structure.MapGenStructureData;

public class WebSocketHandshakeException extends RuntimeException {
   public AbstractChannelHandlerContext$6 __junk7733612721731125926;
   public static long serialVersionUID;
   public WSPacketClientRequestsStatus __junk7791396151910703393;
   public MapGenStructureData __junk9172190006848330510;
   public Team$EnumVisible __junk3937015091723024146;

   public WebSocketHandshakeException(String var1) {
      super(var1);
   }

   public WebSocketHandshakeException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
