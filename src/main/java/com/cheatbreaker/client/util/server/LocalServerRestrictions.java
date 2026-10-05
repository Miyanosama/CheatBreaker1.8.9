package com.cheatbreaker.client.util.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LocalServerRestrictions {
   public Map<String, List<String>> recoveredField875 = new HashMap<>();

   public boolean method_29290(String var1, String var2) {
      for (Entry var4 : this.recoveredField875.entrySet()) {
         if (var1.contains((CharSequence)var4.getKey())) {
            return ((List<String>)var4.getValue()).stream().filter(var1x -> var1x.contains(var2)).findFirst().orElse(null) != null;
         }
      }

      return false;
   }

   public void method_29289() {
      this.recoveredField875.put("hypixel", Arrays.asList("freelook", "auto text"));
   }

   public Map<String, List<String>> method_29291() {
      return this.recoveredField875;
   }
}
