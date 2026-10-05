package net.minecraft.client.gui.inventory;

import net.minecraft.client.resources.I18n;
import net.optifine.shaders.gui.GuiSlotShaders;
import org.apache.log4j.helpers.ThreadLocalMap;

public class GuiBeacon$ConfirmButton extends GuiBeacon$Button {
   public ThreadLocalMap field_0001;
   public GuiSlotShaders field_0000;

   public GuiBeacon$ConfirmButton(GuiBeacon var1, int var2, int var3, int var4) {
      this.field_146147_o = var1;
      super(var2, var3, var4, GuiBeacon.access$000(), 90, 220);
   }

   @Override
   public void drawButtonForegroundLayer(int var1, int var2) {
      GuiBeacon.access$200(this.field_146147_o, I18n.format("gui.done"), var1, var2);
   }
}
