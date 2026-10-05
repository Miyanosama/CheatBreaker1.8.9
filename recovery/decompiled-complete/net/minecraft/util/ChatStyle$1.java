package net.minecraft.util;

import io.netty.handler.codec.socks.SocksMessage;
import io.netty.util.Recycler$2;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import recovered.unidentified.UnidentifiedClass1161;
import recovered.unidentified.UnidentifiedClass1163;

public class ChatStyle$1 extends ChatStyle {
   public SocksMessage field_0002;
   public UnidentifiedClass1163 field_0003;
   public Recycler$2 field_0000;
   public UnidentifiedClass1161 field_0001;

   @Override
   public String getFormattingCode() {
      return "";
   }

   @Override
   public String getInsertion() {
      return null;
   }

   @Override
   public ChatStyle setChatHoverEvent(HoverEvent var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChatStyle createShallowCopy() {
      return this;
   }

   @Override
   public boolean getStrikethrough() {
      return false;
   }

   @Override
   public EnumChatFormatting getColor() {
      return null;
   }

   @Override
   public ChatStyle setParentStyle(ChatStyle var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChatStyle setUnderlined(Boolean var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChatStyle setChatClickEvent(ClickEvent var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public HoverEvent getChatHoverEvent() {
      return null;
   }

   @Override
   public boolean getUnderlined() {
      return false;
   }

   @Override
   public String toString() {
      return "Style.ROOT";
   }

   @Override
   public boolean getObfuscated() {
      return false;
   }

   @Override
   public ChatStyle setStrikethrough(Boolean var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean getItalic() {
      return false;
   }

   @Override
   public ChatStyle setItalic(Boolean var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChatStyle setBold(Boolean var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChatStyle createDeepCopy() {
      return this;
   }

   @Override
   public boolean getBold() {
      return false;
   }

   @Override
   public ClickEvent getChatClickEvent() {
      return null;
   }

   @Override
   public ChatStyle setColor(EnumChatFormatting var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChatStyle setObfuscated(Boolean var1) {
      throw new UnsupportedOperationException();
   }
}
