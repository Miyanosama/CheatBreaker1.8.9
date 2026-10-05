package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.channel.rxtx.RxtxChannelConfig$Stopbits;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.network.PacketBuffer;
import net.optifine.DynamicLight;
import net.optifine.shaders.uniform.ShaderParameterBool$1;

public class WSPacketClientCrashReport extends WSPacket {
   public RxtxChannelConfig$Stopbits field_0006;
   public String stackTrace;
   public DynamicLight field_0005;
   public String version;
   public String crashId;
   public GuiTwitchUserMode field_0007;
   public String osInfo;
   public ShaderParameterBool$1 field_0002;
   public String memoryInfo;

   public WSPacketClientCrashReport(String var1, String var2, String var3, String var4, String var5) {
      this.crashId = var1;
      this.version = var2;
      this.osInfo = var3;
      this.memoryInfo = var4;
      this.stackTrace = var5;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.crashId);
      var1.writeString(this.version);
      var1.writeString(this.osInfo);
      var1.writeString(this.memoryInfo);
      var1.writeString(this.stackTrace);
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   @Override
   public void read(PacketBuffer var1) {
      this.crashId = var1.readStringFromBuffer(100);
      this.version = var1.readStringFromBuffer(100);
      this.osInfo = var1.readStringFromBuffer(500);
      this.memoryInfo = var1.readStringFromBuffer(500);
      this.stackTrace = var1.readStringFromBuffer(10000);
   }
}
