package com.cheatbreaker.client.util;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.server.ServerMappingLoader;
import com.cheatbreaker.client.util.server.ServerRestrictionAction;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;

public class DiscordPresenceManager {
   public boolean method_10920(String var1) {
      if (var1 == null) {
         return false;
      } else {
         String[] var2 = var1.split("\\.");
         int var3 = 0;

         for (String var7 : var2) {
            try {
               int var8 = Integer.parseInt(var7);
               if (var8 < 0 || var8 > 255) {
                  return false;
               }

               var3++;
            } catch (Exception var9) {
               return false;
            }
         }

         return var3 == 4;
      }
   }

   public String[] method_10921(String var1) {
      if (Minecraft.getMinecraft().isSingleplayer()) {
         return new String[]{"Singleplayer", "cb_7-27-2022", "Playing in Singleplayer", null};
      } else if (var1 != "" && var1 != null) {
         for (Entry var3 : CheatBreaker.getInstance().getGlobalSettings().method_02677().entrySet()) {
            for (String var7 : (String[])var3.getKey()) {
               if (var1.endsWith(var7.toLowerCase()) && ((String[])var3.getValue())[1].equals(String.valueOf(ServerRestrictionAction.WARN_STATUS))) {
                  return new String[]{"Unsafe Server", "unsafeserver", "Playing on an unsafe server", "Risking my security"};
               }
            }
         }

         String var8 = ServerMappingLoader.method_12436(var1, "name");
         if (var8 != null) {
            String var10 = var8.endsWith("Network") ? "the " : "";
            return new String[]{var8, ServerMappingLoader.method_12436(var1, "id"), "Playing on " + var10 + var8, null};
         } else {
            String var9 = "Private Server";
            if (var1.contains("localhost") || var1.equals("127.0.0.1") || var1.startsWith("192.168") || var1.equals("0") || var1.equals("0.0.0.0")) {
               var9 = "Local Server";
            } else if (this.method_10920(var1)) {
               var9 = "Numeric Server";
            }

            return new String[]{var9, "cb_7-27-2022", "Playing on a " + var9.toLowerCase(), null};
         }
      } else {
         return new String[]{"In Menus", "cb_7-27-2022", "In the Main Menu", null};
      }
   }
}
