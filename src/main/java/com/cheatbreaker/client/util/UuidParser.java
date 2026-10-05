package com.cheatbreaker.client.util;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.NickHiderModule;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import com.cheatbreaker.client.util.player.PlayerNametagStyle;

public class UuidParser {
   public Map<UUID, PlayerNametagStyle> recoveredField1337 = new ConcurrentHashMap<>();

   public PlayerNametagStyle method_21031(String var1) {
      return this.method_21030().get(UUID.fromString(var1));
   }

   public Map<UUID, PlayerNametagStyle> method_21030() {
      return this.recoveredField1337;
   }

   public boolean method_21032(String var1, boolean var2) {
      NickHiderModule var3 = CheatBreaker.getInstance().getModuleManager().recoveredField1705;
      if (var2) {
         try {
            for (PlayerNametagStyle var9 : this.recoveredField1337.values()) {
               if (var1.contains(var9.method_26420()) || var3.recoveredField850.method_08908() && var1.contains(var3.recoveredField849.method_08874())) {
                  return true;
               }
            }
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      } else {
         try {
            for (UUID var5 : this.recoveredField1337.keySet()) {
               if (UUID.fromString(var1).equals(var5)) {
                  return true;
               }
            }
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

      return false;
   }

   public String method_21033(String var1) {
      return UUID.fromString(var1.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5")).toString();
   }
}
