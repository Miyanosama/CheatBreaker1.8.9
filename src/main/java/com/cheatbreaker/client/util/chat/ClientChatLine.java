package com.cheatbreaker.client.util.chat;

import net.minecraft.client.gui.ChatLine;
import net.minecraft.util.IChatComponent;

public class ClientChatLine extends ChatLine {
   public boolean recoveredField2812;

   public void method_28279(boolean var1) {
      this.recoveredField2812 = var1;
   }

   public boolean method_28277() {
      return this.recoveredField2812;
   }

   public static ClientChatLine method_28278(ChatLine var0) {
      return new ClientChatLine(var0.getUpdatedCounter(), var0.getChatComponent(), var0.getChatLineID());
   }

   public ClientChatLine(int var1, IChatComponent var2, int var3) {
      super(var1, var2, var3);
   }
}
