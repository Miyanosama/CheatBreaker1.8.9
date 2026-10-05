package net.minecraft.util;

public class ChatComponentText extends ChatComponentStyle {
   public boolean recoveredField3553;
   public String text;

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ChatComponentText)) {
         return false;
      } else {
         ChatComponentText var2 = (ChatComponentText)var1;
         return this.text.equals(var2.method_07470()) && super.equals(var1);
      }
   }

   @Override
   public String toString() {
      return "TextComponent{text='" + this.text + '\'' + ", siblings=" + this.a + ", style=" + this.getChatStyle() + '}';
   }

   public ChatComponentText(String var1) {
      this.text = var1;
   }

   public boolean method_07468() {
      return this.recoveredField3553;
   }

   @Override
   public String getUnformattedTextForChat() {
      return this.text;
   }

   public String method_07470() {
      return this.text;
   }

   public ChatComponentText createCopy() {
      ChatComponentText var1 = new ChatComponentText(this.text);
      var1.setChatStyle(this.getChatStyle().createShallowCopy());

      for (IChatComponent var3 : this.getSiblings()) {
         var1.appendSibling(var3.createCopy());
      }

      return var1;
   }

   public void method_07469(boolean var1) {
      this.recoveredField3553 = var1;
   }
}
