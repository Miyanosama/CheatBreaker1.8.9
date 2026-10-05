package net.minecraft.client.network;

import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.init.Bootstrap$4;
import net.minecraft.inventory.ContainerWorkbench;

public class NetHandlerPlayClient$3 implements Runnable {
   public EntityWither field_0003;
   public ContainerWorkbench field_0004;
   public Bootstrap$4 field_0001;

   @Override
   public void run() {
      NetHandlerPlayClient.access$100(this.field_178899_c)
         .displayGuiScreen(
            new GuiYesNo(new NetHandlerPlayClient$3$1(this), I18n.format("multiplayer.texturePrompt.line1"), I18n.format("multiplayer.texturePrompt.line2"), 0)
         );
   }

   public NetHandlerPlayClient$3(NetHandlerPlayClient var1, String var2, String var3) {
      this.field_178899_c = var1;
      this.field_178900_a = var2;
      this.field_178898_b = var3;
      super();
   }
}
