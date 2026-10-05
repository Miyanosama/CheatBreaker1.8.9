package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public class PacketUpdateNametags extends Packet {
   public Map<UUID, List<String>> playersMap;

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeVarInt(this.playersMap == null ? -1 : this.playersMap.size());
      if (this.playersMap != null) {
         for (Entry var3 : this.playersMap.entrySet()) {
            UUID var4 = (UUID)var3.getKey();
            List var5 = (List)var3.getValue();
            var1.writeUUID(var4);
            var1.writeVarInt(var5.size());
            ((List<String>)var5).forEach(var1::writeString);
         }
      }
   }

   @Override
   public void read(ByteBufWrapper var1) {
      int var2 = var1.readVarInt();
      if (var2 == -1) {
         this.playersMap = null;
      } else {
         this.playersMap = new HashMap<>();

         for (int var3 = 0; var3 < var2; var3++) {
            UUID var4 = var1.readUUID();
            int var5 = var1.readVarInt();
            ArrayList var6 = new ArrayList();

            for (int var7 = 0; var7 < var5; var7++) {
               var6.add(var1.readString());
            }

            this.playersMap.put(var4, var6);
         }
      }
   }

   public PacketUpdateNametags(Map<UUID, List<String>> var1) {
      this.playersMap = var1;
   }

   public Map<UUID, List<String>> getPlayersMap() {
      return this.playersMap;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleNametagsUpdate(this);
   }

   public PacketUpdateNametags() {
   }
}
