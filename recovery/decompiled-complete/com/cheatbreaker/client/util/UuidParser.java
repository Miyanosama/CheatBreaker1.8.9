package com.cheatbreaker.client.util;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.NickHiderModule;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javazoom.jl.decoder.LayerIDecoder$Subband;
import net.minecraft.client.model.ModelPlayer;
import recovered.unidentified.UnidentifiedClass4377;

public class UuidParser {
   public LayerIDecoder$Subband field_0001;
   public Map<UUID, UnidentifiedClass4377> field_0002 = new ConcurrentHashMap<>();
   public ModelPlayer field_0000;

   public UnidentifiedClass4377 method_21031(String var1) {
      return this.method_21030().get(UUID.fromString(var1));
   }

   public Map<UUID, UnidentifiedClass4377> method_21030() {
      return this.field_0002;
   }

   public boolean method_21032(String var1, boolean var2) {
      NickHiderModule var3 = CheatBreaker.getInstance().getModuleManager().field_0005;
      if (var2) {
         try {
            for (UnidentifiedClass4377 var9 : this.field_0002.values()) {
               if (var1.contains(var9.method_26420()) || var3.field_0000.method_08908() && var1.contains(var3.field_0003.method_08874())) {
                  return true;
               }
            }
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      } else {
         try {
            for (UUID var5 : this.field_0002.keySet()) {
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
