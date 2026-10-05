package net.minecraft.util;

import com.cheatbreaker.client.ui.CompetitiveLeaveWarningGui;
import javax.vecmath.Tuple3i;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.nbt.NBTTagCompound$1;

public class ChatStyle {
   public HoverEvent chatHoverEvent;
   public Boolean underlined;
   public EnumChatFormatting color;
   public CompetitiveLeaveWarningGui field_0010;
   public ChatStyle parentStyle;
   public String insertion;
   public ClickEvent chatClickEvent;
   public Boolean obfuscated;
   public Boolean strikethrough;
   public Boolean bold;
   public Boolean italic;
   public Tuple3i field_0007;
   public static ChatStyle rootStyle = new ChatStyle$1();
   public NBTTagCompound$1 field_0004;

   @Override
   public int hashCode() {
      int var1 = this.color.hashCode();
      var1 = 31 * var1 + this.bold.hashCode();
      var1 = 31 * var1 + this.italic.hashCode();
      var1 = 31 * var1 + this.underlined.hashCode();
      var1 = 31 * var1 + this.strikethrough.hashCode();
      var1 = 31 * var1 + this.obfuscated.hashCode();
      var1 = 31 * var1 + this.chatClickEvent.hashCode();
      var1 = 31 * var1 + this.chatHoverEvent.hashCode();
      return 31 * var1 + this.insertion.hashCode();
   }

   public ChatStyle createShallowCopy() {
      ChatStyle var1 = new ChatStyle();
      var1.bold = this.bold;
      var1.italic = this.italic;
      var1.strikethrough = this.strikethrough;
      var1.underlined = this.underlined;
      var1.obfuscated = this.obfuscated;
      var1.color = this.color;
      var1.chatClickEvent = this.chatClickEvent;
      var1.chatHoverEvent = this.chatHoverEvent;
      var1.parentStyle = this.parentStyle;
      var1.insertion = this.insertion;
      return var1;
   }

   @Override
   public String toString() {
      return "Style{hasParent="
         + (this.parentStyle != null)
         + ", color="
         + this.color
         + ", bold="
         + this.bold
         + ", italic="
         + this.italic
         + ", underlined="
         + this.underlined
         + ", obfuscated="
         + this.obfuscated
         + ", clickEvent="
         + this.getChatClickEvent()
         + ", hoverEvent="
         + this.getChatHoverEvent()
         + ", insertion="
         + this.getInsertion()
         + '}';
   }

   public ChatStyle setChatClickEvent(ClickEvent var1) {
      this.chatClickEvent = var1;
      return this;
   }

   public ChatStyle setInsertion(String var1) {
      this.insertion = var1;
      return this;
   }

   public HoverEvent getChatHoverEvent() {
      return this.chatHoverEvent == null ? this.getParent().getChatHoverEvent() : this.chatHoverEvent;
   }

   public ChatStyle setChatHoverEvent(HoverEvent var1) {
      this.chatHoverEvent = var1;
      return this;
   }

   public boolean isEmpty() {
      return this.bold == null
         && this.italic == null
         && this.strikethrough == null
         && this.underlined == null
         && this.obfuscated == null
         && this.color == null
         && this.chatClickEvent == null
         && this.chatHoverEvent == null;
   }

   public ChatStyle getParent() {
      return this.parentStyle == null ? rootStyle : this.parentStyle;
   }

   public EnumChatFormatting getColor() {
      return this.color == null ? this.getParent().getColor() : this.color;
   }

   public ChatStyle setBold(Boolean var1) {
      this.bold = var1;
      return this;
   }

   public boolean getStrikethrough() {
      return this.strikethrough == null ? this.getParent().getStrikethrough() : this.strikethrough;
   }

   public String getFormattingCode() {
      if (this.isEmpty()) {
         return this.parentStyle != null ? this.parentStyle.getFormattingCode() : "";
      } else {
         StringBuilder var1 = new StringBuilder();
         if (this.getColor() != null) {
            var1.append(this.getColor());
         }

         if (this.getBold()) {
            var1.append(EnumChatFormatting.BOLD);
         }

         if (this.getItalic()) {
            var1.append(EnumChatFormatting.ITALIC);
         }

         if (this.getUnderlined()) {
            var1.append(EnumChatFormatting.UNDERLINE);
         }

         if (this.getObfuscated()) {
            var1.append(EnumChatFormatting.field_0000);
         }

         if (this.getStrikethrough()) {
            var1.append(EnumChatFormatting.field_0010);
         }

         return var1.toString();
      }
   }

   public boolean getObfuscated() {
      return this.obfuscated == null ? this.getParent().getObfuscated() : this.obfuscated;
   }

   public boolean getUnderlined() {
      return this.underlined == null ? this.getParent().getUnderlined() : this.underlined;
   }

   public ChatStyle setStrikethrough(Boolean var1) {
      this.strikethrough = var1;
      return this;
   }

   public ChatStyle setObfuscated(Boolean var1) {
      this.obfuscated = var1;
      return this;
   }

   public ChatStyle createDeepCopy() {
      ChatStyle var1 = new ChatStyle();
      var1.setBold(this.getBold());
      var1.setItalic(this.getItalic());
      var1.setStrikethrough(this.getStrikethrough());
      var1.setUnderlined(this.getUnderlined());
      var1.setObfuscated(this.getObfuscated());
      var1.setColor(this.getColor());
      var1.setChatClickEvent(this.getChatClickEvent());
      var1.setChatHoverEvent(this.getChatHoverEvent());
      var1.setInsertion(this.getInsertion());
      return var1;
   }

   public ChatStyle setColor(EnumChatFormatting var1) {
      this.color = var1;
      return this;
   }

   public boolean getBold() {
      return this.bold == null ? this.getParent().getBold() : this.bold;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ChatStyle)) {
         return false;
      } else {
         ChatStyle var3 = (ChatStyle)var1;
         return this.getBold() == var3.getBold()
            && this.getColor() == var3.getColor()
            && this.getItalic() == var3.getItalic()
            && this.getObfuscated() == var3.getObfuscated()
            && this.getStrikethrough() == var3.getStrikethrough()
            && this.getUnderlined() == var3.getUnderlined()
            && (this.getChatClickEvent() != null ? this.getChatClickEvent().equals(var3.getChatClickEvent()) : var3.getChatClickEvent() == null)
            && (this.getChatHoverEvent() != null ? this.getChatHoverEvent().equals(var3.getChatHoverEvent()) : var3.getChatHoverEvent() == null)
            && (this.getInsertion() != null ? this.getInsertion().equals(var3.getInsertion()) : var3.getInsertion() == null);
      }
   }

   public ChatStyle setItalic(Boolean var1) {
      this.italic = var1;
      return this;
   }

   public boolean getItalic() {
      return this.italic == null ? this.getParent().getItalic() : this.italic;
   }

   public ChatStyle setParentStyle(ChatStyle var1) {
      this.parentStyle = var1;
      return this;
   }

   public String getInsertion() {
      return this.insertion == null ? this.getParent().getInsertion() : this.insertion;
   }

   public ClickEvent getChatClickEvent() {
      return this.chatClickEvent == null ? this.getParent().getChatClickEvent() : this.chatClickEvent;
   }

   public ChatStyle setUnderlined(Boolean var1) {
      this.underlined = var1;
      return this;
   }
}
