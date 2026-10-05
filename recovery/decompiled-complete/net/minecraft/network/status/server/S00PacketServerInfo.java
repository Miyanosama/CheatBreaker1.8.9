package net.minecraft.network.status.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.event.HoverEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.ServerStatusResponse$MinecraftProtocolVersionIdentifier;
import net.minecraft.network.ServerStatusResponse$MinecraftProtocolVersionIdentifier$Serializer;
import net.minecraft.network.ServerStatusResponse$PlayerCountData;
import net.minecraft.network.ServerStatusResponse$PlayerCountData$Serializer;
import net.minecraft.network.ServerStatusResponse$Serializer;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChatStyle$Serializer;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IChatComponent$Serializer;

public class S00PacketServerInfo implements Packet<INetHandlerStatusClient> {
   public HoverEvent field_0001;
   public ServerStatusResponse response;
   public static Gson GSON = new GsonBuilder()
      .registerTypeAdapter(
         ServerStatusResponse$MinecraftProtocolVersionIdentifier.class, new ServerStatusResponse$MinecraftProtocolVersionIdentifier$Serializer()
      )
      .registerTypeAdapter(ServerStatusResponse$PlayerCountData.class, new ServerStatusResponse$PlayerCountData$Serializer())
      .registerTypeAdapter(ServerStatusResponse.class, new ServerStatusResponse$Serializer())
      .registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent$Serializer())
      .registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle$Serializer())
      .registerTypeAdapterFactory(new EnumTypeAdapterFactory())
      .create();

   public ServerStatusResponse getResponse() {
      return this.response;
   }

   public void processPacket(INetHandlerStatusClient var1) {
      var1.handleServerInfo(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.response = (ServerStatusResponse)GSON.fromJson(var1.readStringFromBuffer(32767), ServerStatusResponse.class);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(GSON.toJson(this.response));
   }

   public S00PacketServerInfo() {
   }

   public S00PacketServerInfo(ServerStatusResponse var1) {
      this.response = var1;
   }
}
