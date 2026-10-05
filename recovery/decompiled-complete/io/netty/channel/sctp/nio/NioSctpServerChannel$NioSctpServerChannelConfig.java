package io.netty.channel.sctp.nio;

import com.sun.nio.sctp.SctpServerChannel;
import io.netty.channel.sctp.DefaultSctpServerChannelConfig;
import net.minecraft.block.BlockButton;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.util.RegistrySimple;
import net.optifine.shaders.CustomTextureRaw;

public class NioSctpServerChannel$NioSctpServerChannelConfig extends DefaultSctpServerChannelConfig {
   public RegistrySimple __junk1621151944845728434;
   public CustomTextureRaw __junk8302326841314147998;
   public BlockButton __junk9015012797487512051;
   public S00PacketServerInfo __junk4753539743816669607;

   @Override
   public void autoReadCleared() {
      NioSctpServerChannel.access$100(this.this$0, false);
   }

   public NioSctpServerChannel$NioSctpServerChannelConfig(NioSctpServerChannel var1, NioSctpServerChannel var2, SctpServerChannel var3) {
      this.this$0 = var1;
      super(var2, var3);
   }
}
