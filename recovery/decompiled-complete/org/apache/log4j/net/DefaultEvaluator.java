package org.apache.log4j.net;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler$ServerHandshakeStateEvent;
import net.minecraft.client.Minecraft$4;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;
import org.apache.log4j.varia.FallbackErrorHandler;

public class DefaultEvaluator implements TriggeringEventEvaluator {
   public FallbackErrorHandler field_0001;
   public WebSocketServerProtocolHandler$ServerHandshakeStateEvent field_0002;
   public Minecraft$4 field_0000;

   public boolean isTriggeringEvent(LoggingEvent var1) {
      return var1.getLevel().isGreaterOrEqual(Level.ERROR);
   }
}
