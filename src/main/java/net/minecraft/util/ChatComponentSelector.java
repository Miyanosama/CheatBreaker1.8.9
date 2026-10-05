package net.minecraft.util;

public class ChatComponentSelector extends ChatComponentStyle {
   public String selector;

   public ChatComponentSelector createCopy() {
      ChatComponentSelector var1 = new ChatComponentSelector(this.selector);
      var1.setChatStyle(this.getChatStyle().createShallowCopy());

      for (IChatComponent var3 : this.getSiblings()) {
         var1.appendSibling(var3.createCopy());
      }

      return var1;
   }

   @Override
   public String toString() {
      return "SelectorComponent{pattern='" + this.selector + '\'' + ", siblings=" + this.a + ", style=" + this.getChatStyle() + '}';
   }

   public String method_29641() {
      return this.selector;
   }

   @Override
   public String getUnformattedTextForChat() {
      return this.selector;
   }

   public ChatComponentSelector(String var1) {
      this.selector = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ChatComponentSelector)) {
         return false;
      } else {
         ChatComponentSelector var2 = (ChatComponentSelector)var1;
         return this.selector.equals(var2.selector) && super.equals(var1);
      }
   }
}
