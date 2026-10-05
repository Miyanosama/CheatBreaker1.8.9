package net.minecraft.client.gui;

import net.minecraft.util.IChatComponent;

public class ChatLine {
   public int chatLineID;
   public int updateCounterCreated;
   public IChatComponent lineString;

   public IChatComponent getChatComponent() {
      return this.lineString;
   }

   public int getChatLineID() {
      return this.chatLineID;
   }

   public int getUpdatedCounter() {
      return this.updateCounterCreated;
   }

   public ChatLine(int var1, IChatComponent var2, int var3) {
      this.lineString = var2;
      this.updateCounterCreated = var1;
      this.chatLineID = var3;
   }
}
