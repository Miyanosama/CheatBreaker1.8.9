package io.netty.handler.codec.socks;

import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import javax.vecmath.Tuple3f;
import net.minecraft.client.audio.MovingSoundMinecartRiding;
import net.minecraft.client.gui.GuiScreenCustomizePresets;

public enum SocksMessageType {
      REQUEST,
      RESPONSE,
      UNKNOWN;
   public static SocksMessageType[] $VALUES = new SocksMessageType[]{REQUEST, SocksMessageType.RESPONSE, SocksMessageType.UNKNOWN};
}
