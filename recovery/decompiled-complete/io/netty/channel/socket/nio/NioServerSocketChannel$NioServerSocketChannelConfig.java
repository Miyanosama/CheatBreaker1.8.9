package io.netty.channel.socket.nio;

import io.netty.channel.socket.DefaultServerSocketChannelConfig;
import java.net.ServerSocket;
import junit.runner.SimpleTestCollector;
import net.minecraft.item.ItemBow;
import net.minecraft.network.play.server.S14PacketEntity$S17PacketEntityLookMove;

public class NioServerSocketChannel$NioServerSocketChannelConfig extends DefaultServerSocketChannelConfig {
   public ItemBow __junk7932686918712397577;
   public S14PacketEntity$S17PacketEntityLookMove __junk5818924280072990943;
   public SimpleTestCollector __junk3067042727719282525;

   public NioServerSocketChannel$NioServerSocketChannelConfig(NioServerSocketChannel var1, NioServerSocketChannel var2, ServerSocket var3) {
      this.this$0 = var1;
      super(var2, var3);
   }

   @Override
   public void autoReadCleared() {
      NioServerSocketChannel.access$100(this.this$0, false);
   }
}
