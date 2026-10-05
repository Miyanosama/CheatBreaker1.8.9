package net.minecraft.network.play.server;

import com.cheatbreaker.client.ui.module.CBProfileCreateGui;
import io.netty.channel.AbstractChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel$1;
import io.netty.handler.codec.http.DefaultHttpHeaders$HeaderIterator;
import io.netty.handler.codec.http.HttpContentEncoder$State;
import net.minecraft.command.CommandGameMode;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import org.apache.log4j.net.JMSAppender;

public class S00PacketKeepAlive implements Packet<INetHandlerPlayClient> {
   public CBProfileCreateGui field_0004;
   public int id;
   public JMSAppender field_0003;
   public NioSocketChannel$1 field_0006;
   public AbstractChannelHandlerContext field_0000;
   public DefaultHttpHeaders$HeaderIterator field_0001;
   public HttpContentEncoder$State field_0008;
   public CommandGameMode field_0005;
   public EnchantmentData field_0002;

   public int func_149134_c() {
      return this.id;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleKeepAlive(this);
   }

   public S00PacketKeepAlive(int var1) {
      this.id = var1;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.id);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.id = var1.readVarIntFromBuffer();
   }

   public S00PacketKeepAlive() {
   }
}
