package org.java_websocket.enums;

import com.cheatbreaker.client.websocket.client.WSPacketClientRequestsStatus;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$1;
import net.minecraft.client.shader.ShaderDefault;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$6;

public enum Role {
   SERVER,
   CLIENT;
   public WebSocket08FrameDecoder$1 field_0003;
   public ShaderDefault field_0006;
   public LogBrokerMonitor$6 field_0002;
   public C08PacketPlayerBlockPlacement field_0000;
   // $VF: synthetic field
   public static Role[] $VALUES = new Role[]{Role.CLIENT, SERVER};
   public WSPacketClientRequestsStatus field_0007;
}
