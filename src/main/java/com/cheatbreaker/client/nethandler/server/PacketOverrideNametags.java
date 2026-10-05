package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacketOverrideNametags extends Packet {
   public UUID player;
   public List<String> tags;

   public UUID getPlayer() {
      return this.player;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleOverrideNametags(this);
   }

   public PacketOverrideNametags(UUID var1, List<String> var2) {
      this.player = var1;
      this.tags = var2;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.player = var1.readUUID();
      this.tags = var1.readOptional(() -> {
         int var1x = var1.readVarInt();
         ArrayList var2 = new ArrayList();

         for (int var3 = 0; var3 < var1x; var3++) {
            var2.add(var1.readString());
         }

         return var2;
      });
   }

   public List<String> getTags() {
      return this.tags;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.player);
      var1.writeOptional(this.tags, var1x -> {
         var1.writeVarInt(var1x.size());
         var1x.forEach(var1::writeString);
      });
   }

   public PacketOverrideNametags() {
   }
}
