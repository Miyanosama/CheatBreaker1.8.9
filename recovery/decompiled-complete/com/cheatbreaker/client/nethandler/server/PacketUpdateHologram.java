package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.BlockFlower$EnumFlowerColor;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.world.biome.BiomeGenJungle;

public class PacketUpdateHologram extends Packet {
   public BiomeGenJungle field_0000;
   public EntityAgeable field_0001;
   public UUID uuid;
   public List<String> lines;
   public BlockFlower$EnumFlowerColor field_0002;
   public NBTBase field_0005;

   @Override
   public void read(ByteBufWrapper var1) {
      this.uuid = var1.readUUID();
      int var2 = var1.readVarInt();
      this.lines = new ArrayList<>();

      for (int var3 = 0; var3 < var2; var3++) {
         this.lines.add(var1.readString());
      }
   }

   public PacketUpdateHologram(UUID var1, List<String> var2) {
      this.uuid = var1;
      this.lines = var2;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.uuid);
      var1.writeVarInt(this.lines.size());

      for (String var3 : this.lines) {
         var1.writeString(var3);
      }
   }

   public List<String> getLines() {
      return this.lines;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public PacketUpdateHologram() {
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleUpdateHologram(this);
   }
}
