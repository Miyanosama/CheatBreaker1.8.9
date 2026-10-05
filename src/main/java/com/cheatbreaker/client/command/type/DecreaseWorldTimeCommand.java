package com.cheatbreaker.client.command.type;

import com.cheatbreaker.client.command.ModuleCommand;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.EnvironmentModule;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class DecreaseWorldTimeCommand extends ModuleCommand {
   @Override
   public void method_00001() {
      long var1 = Integer.parseInt(((EnvironmentModule)this.recoveredField2810).recoveredField2030.getValue().toString())
         - ((EnvironmentModule)this.recoveredField2810).recoveredField2027.method_08912();
      if (((EnvironmentModule)this.recoveredField2810).recoveredField2038.method_08874().equalsIgnoreCase("Static")) {
         if (Minecraft.getMinecraft().theWorld != null) {
            if (var1 < -22880L) {
               return;
            }

            ((EnvironmentModule)this.recoveredField2810)
               .recoveredField2030
               .setValue(
                  Integer.parseInt(((EnvironmentModule)this.recoveredField2810).recoveredField2030.getValue().toString())
                     - ((EnvironmentModule)this.recoveredField2810).recoveredField2027.method_08912()
               );
            Minecraft.getMinecraft().theWorld.setWorldTime(var1);
            String var3 = "Decreased time by " + CheatBreaker.getInstance().getModuleManager().recoveredField1726.recoveredField2027.method_08912() + ".";
            CheatBreaker.getInstance().method_19756().method_13256(new ChatComponentText(var3));
         }
      } else {
         String var4 = EnumChatFormatting.RED + "This mod command only works with the \"Static\" time type.";
         CheatBreaker.getInstance().method_19756().method_13256(new ChatComponentText(var4));
      }
   }

   public DecreaseWorldTimeCommand() {
      super(CheatBreaker.getInstance().getModuleManager().recoveredField1726, "/cb_decrease_time");
   }
}
