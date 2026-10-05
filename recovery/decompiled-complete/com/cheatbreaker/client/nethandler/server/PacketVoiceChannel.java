package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.gui.GuiPageButtonList$GuiListEntry;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.crafting.RecipesBanners$1;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryImmediateEditor;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNode;
import recovered.unidentified.UnidentifiedClass0173;
import recovered.unidentified.UnidentifiedClass3988;

public class PacketVoiceChannel extends Packet {
   public ItemPickaxe field_0002;
   public Map<UUID, String> players;
   public UnidentifiedClass3988 field_0011;
   public BlockContainer field_0008;
   public String name;
   public CategoryNode field_0012;
   public Map<UUID, String> listening;
   public UnidentifiedClass0173 field_0006;
   public RecipesBanners$1 field_0007;
   public CategoryImmediateEditor field_0005;
   public UUID uuid;
   public S22PacketMultiBlockChange field_0010;
   public GuiPageButtonList$GuiListEntry field_0000;

   public Map<UUID, String> getListening() {
      return this.listening;
   }

   public void writeMap(ByteBufWrapper var1, Map<UUID, String> var2) {
      var1.writeVarInt(var2.size());
      var2.forEach((var1x, var2x) -> {
         var1.writeUUID(var1x);
         var1.writeString(var2x);
      });
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.uuid);
      var1.writeString(this.name);
      this.writeMap(var1, this.players);
      this.writeMap(var1, this.listening);
   }

   public String getName() {
      return this.name;
   }

   public Map<UUID, String> readMap(ByteBufWrapper var1) {
      int var2 = var1.readVarInt();
      HashMap var3 = new HashMap();

      for (int var4 = 0; var4 < var2; var4++) {
         UUID var5 = var1.readUUID();
         String var6 = var1.readString();
         var3.put(var5, var6);
      }

      return var3;
   }

   public Map<UUID, String> getPlayers() {
      return this.players;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public PacketVoiceChannel(UUID var1, String var2, Map<UUID, String> var3, Map<UUID, String> var4) {
      this.uuid = var1;
      this.name = var2;
      this.players = var3;
      this.listening = var4;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.uuid = var1.readUUID();
      this.name = var1.readString();
      this.players = this.readMap(var1);
      this.listening = this.readMap(var1);
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleVoiceChannels(this);
   }

   public PacketVoiceChannel() {
   }
}
