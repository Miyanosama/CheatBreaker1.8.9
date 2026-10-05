package net.minecraft.client.gui.inventory;

import io.netty.channel.oio.OioEventLoopGroup;
import net.minecraft.client.resources.I18n;
import org.java_websocket.util.NamedThreadFactory;

public class GuiBeacon$CancelButton extends GuiBeacon$Button {
   public NamedThreadFactory field_0002;
   public OioEventLoopGroup field_0000;

   public GuiBeacon$CancelButton(GuiBeacon var1, int var2, int var3, int var4) {
      this.field_146146_o = var1;
      super(var2, var3, var4, GuiBeacon.access$000(), 112, 220);
   }

   @Override
   public void drawButtonForegroundLayer(int var1, int var2) {
      GuiBeacon.access$300(this.field_146146_o, I18n.format("gui.cancel"), var1, var2);
   }
}
