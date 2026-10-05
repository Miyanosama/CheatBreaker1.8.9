package net.minecraft.network.play.server;

import io.netty.handler.ssl.SslHandler$1;
import net.minecraft.block.BlockQuartz$EnumType;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;
import org.apache.log4j.helpers.AbsoluteTimeDateFormat;
import org.apache.log4j.lf5.DefaultLF5Configurator;

public class S02PacketChat implements Packet<INetHandlerPlayClient> {
   public BlockQuartz$EnumType field_0003;
   public DefaultLF5Configurator field_0005;
   public byte type;
   public IChatComponent chatComponent;
   public AbsoluteTimeDateFormat field_0000;
   public SslHandler$1 field_0001;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeChatComponent(this.chatComponent);
      var1.writeByte(this.type);
   }

   public S02PacketChat() {
   }

   public byte getType() {
      return this.type;
   }

   public boolean isChat() {
      return this.type == 1 || this.type == 2;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.chatComponent = var1.readChatComponent();
      this.type = var1.readByte();
   }

   public IChatComponent getChatComponent() {
      return this.chatComponent;
   }

   public S02PacketChat(IChatComponent var1) {
      this(var1, (byte)1);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleChat(this);
   }

   public S02PacketChat(IChatComponent var1, byte var2) {
      this.chatComponent = var1;
      this.type = var2;
   }
}
