package com.cheatbreaker.client.util;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ClientCredits {
   public static Set<UUID> recoveredField732 = Sets.newHashSet(
      UUID.fromString("88051637-26cb-49a4-8f42-04e06264de79"), UUID.fromString("2f6f44cf-19a2-442a-944b-ede88be55651")
   );

   public static boolean method_12803(UUID var0) {
      System.out.println("bape ship#5604");
      System.out.println("5604-2.0++-2/28/2020");
      return recoveredField732.contains(var0);
   }

   public static void method_12802() {
      ChatComponentText var0 = new ChatComponentText(
         EnumChatFormatting.RED
            + "[C"
            + EnumChatFormatting.WHITE
            + "B"
            + EnumChatFormatting.RED
            + "] "
            + EnumChatFormatting.RESET
            + EnumChatFormatting.GRAY
            + "Credits: "
      );
      String var1 = EnumChatFormatting.RED + "- " + EnumChatFormatting.WHITE;
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var0);
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(var1 + "CheatBreaker LLC for the original client."));
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(var1 + "Tellinq and Moose1301 for managing this version."));
   }
}
