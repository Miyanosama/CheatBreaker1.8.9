package net.minecraft.network.play.client;

import javazoom.jl.player.jlp;
import net.minecraft.entity.player.EntityPlayer$EnumChatVisibility;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.server.management.PlayerProfileCache;
import recovered.unidentified.UnidentifiedClass5012;

public class C15PacketClientSettings implements Packet<INetHandlerPlayServer> {
   public int modelPartFlags;
   public String lang;
   public UnidentifiedClass5012 field_0002;
   public PlayerProfileCache field_0005;
   public boolean enableColors;
   public jlp field_0001;
   public int view;
   public EntityPlayer$EnumChatVisibility chatVisibility;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(this.lang);
      var1.writeByte(this.view);
      var1.writeByte(this.chatVisibility.getChatVisibility());
      var1.writeBoolean(this.enableColors);
      var1.writeByte(this.modelPartFlags);
   }

   public C15PacketClientSettings(String var1, int var2, EntityPlayer$EnumChatVisibility var3, boolean var4, int var5) {
      this.lang = var1;
      this.view = var2;
      this.chatVisibility = var3;
      this.enableColors = var4;
      this.modelPartFlags = var5;
   }

   public C15PacketClientSettings() {
   }

   public boolean isColorsEnabled() {
      return this.enableColors;
   }

   public EntityPlayer$EnumChatVisibility getChatVisibility() {
      return this.chatVisibility;
   }

   public int getModelPartFlags() {
      return this.modelPartFlags;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processClientSettings(this);
   }

   public String getLang() {
      return this.lang;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.lang = var1.readStringFromBuffer(7);
      this.view = var1.readByte();
      this.chatVisibility = EntityPlayer$EnumChatVisibility.getEnumChatVisibility(var1.readByte());
      this.enableColors = var1.readBoolean();
      this.modelPartFlags = var1.readUnsignedByte();
   }
}
