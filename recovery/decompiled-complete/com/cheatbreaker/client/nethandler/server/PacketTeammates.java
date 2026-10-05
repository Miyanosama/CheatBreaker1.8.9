package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import io.netty.channel.group.ChannelMatchers;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.optifine.reflect.FieldLocatorTypes;
import net.optifine.shaders.ShaderPackDefault;

public class PacketTeammates extends Packet {
   public EntityAIOcelotSit field_0001;
   public ChannelMatchers field_0002;
   public UUID leader;
   public FieldLocatorTypes field_0005;
   public PacketOverrideNametags field_0003;
   public long lastMs;
   public Map<UUID, Map<String, Double>> players;
   public ShaderPackDefault field_0004;

   @Override
   public void write(ByteBufWrapper var1) {
      var1.buf().writeBoolean(this.leader != null);
      if (this.leader != null) {
         var1.writeUUID(this.leader);
      }

      var1.buf().writeLong(this.lastMs);
      var1.writeVarInt(this.players.values().size());

      for (Entry var3 : this.players.entrySet()) {
         var1.writeUUID((UUID)var3.getKey());
         var1.writeVarInt(((Map)var3.getValue()).values().size());

         for (Entry var5 : ((Map)var3.getValue()).entrySet()) {
            var1.writeString((String)var5.getKey());
            var1.buf().writeDouble((Double)var5.getValue());
         }
      }
   }

   @Override
   public void read(ByteBufWrapper var1) {
      boolean var2 = var1.buf().readBoolean();
      if (var2) {
         this.leader = var1.readUUID();
      }

      this.lastMs = var1.buf().readLong();
      int var3 = var1.readVarInt();
      this.players = new HashMap<>();

      for (int var4 = 0; var4 < var3; var4++) {
         UUID var5 = var1.readUUID();
         int var6 = var1.readVarInt();
         HashMap var7 = new HashMap();

         for (int var8 = 0; var8 < var6; var8++) {
            String var9 = var1.readString();
            double var10 = var1.buf().readDouble();
            var7.put(var9, var10);
         }

         this.players.put(var5, var7);
      }
   }

   public Map<UUID, Map<String, Double>> getPlayers() {
      return this.players;
   }

   public long getLastMs() {
      return this.lastMs;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleTeammates(this);
   }

   public UUID getLeader() {
      return this.leader;
   }

   public PacketTeammates(UUID var1, long var2, Map<UUID, Map<String, Double>> var4) {
      this.leader = var1;
      this.lastMs = var2;
      this.players = var4;
   }

   public PacketTeammates() {
   }
}
