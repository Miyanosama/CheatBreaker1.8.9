package io.netty.channel.sctp.nio;

import com.cheatbreaker.client.module.type.ChatModule;
import com.cheatbreaker.client.module.type.IconTextHudModule;
import io.netty.channel.ChannelPromise;
import java.net.InetAddress;
import net.minecraft.client.gui.GuiSlider;
import net.optifine.shaders.config.ShaderOptionScreen;
import recovered.unidentified.UnidentifiedClass1394;

public class NioSctpChannel$2 implements Runnable {
   public GuiSlider __junk4469807271503164298;
   public ChatModule __junk9142967928090580440;
   public UnidentifiedClass1394 __junk623870118436961574;
   public ShaderOptionScreen __junk2244295885762222220;
   public IconTextHudModule __junk4024917837519800286;

   @Override
   public void run() {
      this.this$0.unbindAddress(this.val$localAddress, this.val$promise);
   }

   public NioSctpChannel$2(NioSctpChannel var1, InetAddress var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$localAddress = var2;
      this.val$promise = var3;
      super();
   }
}
