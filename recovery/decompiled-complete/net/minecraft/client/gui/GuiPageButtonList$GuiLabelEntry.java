package net.minecraft.client.gui;

import com.cheatbreaker.client.module.type.FPSModule;
import io.netty.channel.socket.DefaultServerSocketChannelConfig;
import io.netty.handler.codec.spdy.DefaultSpdyHeaders;
import io.netty.handler.codec.spdy.SpdyVersion;

public class GuiPageButtonList$GuiLabelEntry extends GuiPageButtonList$GuiListEntry {
   public FPSModule field_0001;
   public DefaultSpdyHeaders field_0003;
   public DefaultServerSocketChannelConfig field_0000;
   public SpdyVersion field_0002;

   public GuiPageButtonList$GuiLabelEntry(int var1, String var2, boolean var3) {
      super(var1, var2, var3);
   }
}
