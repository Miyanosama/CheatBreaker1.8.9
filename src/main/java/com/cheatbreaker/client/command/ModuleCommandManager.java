package com.cheatbreaker.client.command;

import com.cheatbreaker.client.command.type.DecreaseWorldTimeCommand;

import com.cheatbreaker.client.command.type.IncreaseWorldTimeCommand;

import com.cheatbreaker.client.command.ModuleCommand;

import com.cheatbreaker.client.CheatBreaker;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ModuleCommandManager {
   public DecreaseWorldTimeCommand recoveredField290;
   public List<ModuleCommand> recoveredField291 = new ArrayList<>();
   public IncreaseWorldTimeCommand recoveredField292;

   public void method_13256(ChatComponentText var1) {
      if (!CheatBreaker.getInstance().getGlobalSettings().recoveredField490.method_08908()) {
         var1.method_07469(true);
         Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var1);
      }
   }

   public ModuleCommandManager() {
      this.recoveredField291.add(this.recoveredField292 = new IncreaseWorldTimeCommand());
      this.recoveredField291.add(this.recoveredField290 = new DecreaseWorldTimeCommand());
   }
}
