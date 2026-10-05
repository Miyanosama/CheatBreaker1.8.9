package net.minecraft.network.play.server;

import com.cheatbreaker.client.ui.fading.ExponentialFade;
import net.minecraft.block.BlockTrapDoor$DoorHalf;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;
import org.java_websocket.server.WebSocketServer$WebSocketWorker$1;

public class S40PacketDisconnect implements Packet<INetHandlerPlayClient> {
   public WebSocketServer$WebSocketWorker$1 field_0001;
   public IChatComponent reason;
   public BlockTrapDoor$DoorHalf field_0000;
   public ExponentialFade field_0002;

   public S40PacketDisconnect(IChatComponent var1) {
      this.reason = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleDisconnect(this);
   }

   public IChatComponent getReason() {
      return this.reason;
   }

   public S40PacketDisconnect() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.reason = var1.readChatComponent();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeChatComponent(this.reason);
   }
}
