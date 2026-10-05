package net.minecraft.client.resources;

import io.netty.handler.codec.socks.SocksAddressType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenWorking;

public class ResourcePackRepository$2 implements Runnable {
   public SocksAddressType field_0001;

   public ResourcePackRepository$2(ResourcePackRepository var1, Minecraft var2, GuiScreenWorking var3) {
      this.field_0002 = var1;
      this.field_0003 = var2;
      this.field_0000 = var3;
      super();
   }

   @Override
   public void run() {
      this.field_0003.displayGuiScreen(this.field_0000);
   }
}
