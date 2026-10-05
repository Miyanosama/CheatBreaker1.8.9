package org.java_websocket.framing;

import io.netty.channel.rxtx.RxtxChannelConfig$Databits;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus;
import net.minecraft.client.gui.spectator.PlayerMenuObject;
import net.minecraft.client.particle.EntityDiggingFX;
import org.java_websocket.enums.Opcode;

public class BinaryFrame extends DataFrame {
   public PlayerMenuObject field_0001;
   public RxtxChannelConfig$Databits field_0003;
   public EntityDiggingFX field_0000;
   public HttpPostRequestDecoder$MultiPartStatus field_0002;

   public BinaryFrame() {
      super(Opcode.BINARY);
   }
}
