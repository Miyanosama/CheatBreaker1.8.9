package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.network.play.server.S02PacketChat;

public class ChatPacketEvent extends EventBus$Event {
   public S02PacketChat recoveredField3307;

   public ChatPacketEvent(S02PacketChat var1) {
      this.recoveredField3307 = var1;
   }

   public S02PacketChat method_05975() {
      return this.recoveredField3307;
   }
}
