package com.cheatbreaker.client.util.cosmetic;

import com.cheatbreaker.client.util.ClientResourceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;

public class CosmeticsManager {
   public List<ClientResourceManager> recoveredField1254;
   public List<ClientResourceManager> recoveredField1255 = new ArrayList<>();

   public List<ClientResourceManager> method_27042() {
      return this.recoveredField1255;
   }

   public List<ClientResourceManager> method_27046() {
      ArrayList var1 = new ArrayList();

      for (ClientResourceManager var3 : this.recoveredField1254) {
         if (var3.method_20863().equals(Minecraft.getMinecraft().getSession().getPlayerID())) {
            var1.add(var3);
         }
      }

      for (ClientResourceManager var5 : this.recoveredField1255) {
         if (var5.method_20863().equals(Minecraft.getMinecraft().getSession().getPlayerID())) {
            var1.add(var5);
         }
      }

      return var1;
   }

   public ClientResourceManager method_27045(UUID var1) {
      for (ClientResourceManager var3 : this.method_27042()) {
         if (var3.method_20849() && var1.toString().equals(var3.method_20863())) {
            return var3;
         }
      }

      return null;
   }

   public CosmeticsManager() {
      this.recoveredField1254 = new ArrayList<>();
   }

   public ClientResourceManager method_27048(UUID var1) {
      for (ClientResourceManager var3 : this.method_27041()) {
         if (var3.method_20849() && var1.toString().equals(var3.method_20863())) {
            return var3;
         }
      }

      return null;
   }

   public void method_27043(String var1) {
      this.method_27042().removeIf(var1x -> var1x.method_20863().equals(var1));
      this.method_27041().removeIf(var1x -> var1x.method_20863().equals(var1));
   }

   public List<ClientResourceManager> method_27041() {
      return this.recoveredField1254;
   }
}
