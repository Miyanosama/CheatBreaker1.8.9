package net.minecraft.client.gui;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.ContainerEnchantment$2;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0BPacketEntityAction$Action;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.optifine.util.LinkedList;

public class GuiSleepMP extends GuiChat {
   public S3APacketTabComplete field_0002;
   public ContainerEnchantment$2 field_0000;
   public LinkedList field_0001;

   @Override
   public void initGui() {
      super.initGui();
      this.n.add(new GuiButton(1, this.l / 2 - 100, this.m - 40, I18n.format("multiplayer.stopSleeping")));
   }

   @Override
   public void keyTyped(char var1, int var2) {
      if (var2 == 1) {
         this.wakeFromSleep();
      } else if (var2 != 28 && var2 != 156) {
         super.keyTyped(var1, var2);
      } else {
         String var3 = this.inputField.getText().trim();
         if (!var3.isEmpty()) {
            this.j.thePlayer.sendChatMessage(var3);
         }

         this.inputField.setText("");
         this.j.ingameGUI.getChatGUI().resetScroll();
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.k == 1) {
         this.wakeFromSleep();
      } else {
         super.actionPerformed(var1);
      }
   }

   public void wakeFromSleep() {
      NetHandlerPlayClient var1 = this.j.thePlayer.sendQueue;
      var1.addToSendQueue(new C0BPacketEntityAction(this.j.thePlayer, C0BPacketEntityAction$Action.STOP_SLEEPING));
   }
}
