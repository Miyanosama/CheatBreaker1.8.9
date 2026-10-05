package net.minecraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;

public class StreamingConfirmCallback implements GuiYesNoCallback {
   public Minecraft recoveredField2294;

   @Override
   public void confirmClicked(boolean var1, int var2) {
      if (var1) {
         this.recoveredField2294.getTwitchStream().method_02395();
      }

      this.recoveredField2294.displayGuiScreen((GuiScreen)null);
   }

   public StreamingConfirmCallback(Minecraft var1) {
      this.recoveredField2294 = var1;
   }
}
