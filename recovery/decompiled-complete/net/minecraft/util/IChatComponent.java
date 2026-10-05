package net.minecraft.util;

import java.util.List;

public interface IChatComponent extends Iterable<IChatComponent> {
   IChatComponent createCopy();

   List<IChatComponent> getSiblings();

   ChatStyle getChatStyle();

   String getFormattedText();

   String getUnformattedText();

   String getUnformattedTextForChat();

   IChatComponent appendText(String var1);

   IChatComponent setChatStyle(ChatStyle var1);

   IChatComponent appendSibling(IChatComponent var1);
}
