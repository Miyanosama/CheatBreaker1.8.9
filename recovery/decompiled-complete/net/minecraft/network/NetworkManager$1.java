package net.minecraft.network;

import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.overlay.VoiceChatGui;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.nio.NioEventLoopGroup;
import net.minecraft.client.gui.GuiCustomizeSkin$1;
import net.minecraft.util.LazyLoadBase;
import recovered.unidentified.UnidentifiedClass1294;

public class NetworkManager$1 extends LazyLoadBase<NioEventLoopGroup> {
   public GuiCustomizeSkin$1 field_0001;
   public CosineFade field_0003;
   public UnidentifiedClass1294 field_0000;
   public VoiceChatGui field_0002;

   public NioEventLoopGroup load() {
      return new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Client IO #%d").setDaemon(true).build());
   }
}
