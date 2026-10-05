package com.cheatbreaker.client.util.friend;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.overlay.friend.FriendRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

public class FriendsManager {
   public Map<String, List<String>> recoveredField1160;
   public Map<String, FriendRequest> recoveredField1161;
   public DateTimeFormatter recoveredField1162;
   public Map<String, Friend> recoveredField1163 = new HashMap<>();
   public Map<String, List<String>> recoveredField1164;
   public Map<String, List<String>> recoveredField1165;

   public void method_26536(String var1, String var2) {
      Friend var3 = this.getFriend(var1);
      if (var3 != null) {
         if (!this.recoveredField1165.containsKey(var1)) {
            this.recoveredField1165.put(var1, new ArrayList<>());
         }

         this.recoveredField1165.get(var1).add(var3.getName() + ": " + var2);
         String var4 = EnumChatFormatting.GRAY + LocalDateTime.now().format(this.recoveredField1162);
         String var5 = EnumChatFormatting.AQUA + Minecraft.getMinecraft().getSession().getUsername() + EnumChatFormatting.RESET;
         String var6 = var4 + " " + var5 + ": " + var2;
         this.method_26533(var1, var6);
      }
   }

   public Map<String, Friend> getFriends() {
      return this.recoveredField1163;
   }

   public Map<String, List<String>> method_26538() {
      return this.recoveredField1165;
   }

   public Map<String, List<String>> method_26532() {
      return this.recoveredField1160;
   }

   public void readMessages(String var1) {
      if (this.recoveredField1164.containsKey(var1)) {
         List var2 = this.recoveredField1164.get(var1);
         if (!this.recoveredField1160.containsKey(var1)) {
            this.recoveredField1160.put(var1, new ArrayList<>());
         }

         this.recoveredField1160.get(var1).addAll(var2);
         this.recoveredField1164.remove(var1);
      }
   }

   public Map<String, List<String>> method_26537() {
      return this.recoveredField1164;
   }

   public FriendsManager() {
      this.recoveredField1161 = new HashMap<>();
      this.recoveredField1160 = new HashMap<>();
      this.recoveredField1164 = new HashMap<>();
      this.recoveredField1165 = new HashMap<>();
      this.recoveredField1162 = DateTimeFormatter.ofPattern("HH:mm:ss");
      CheatBreaker.getInstance().recoveredField1579.info(CheatBreaker.getInstance().recoveredField1553 + "Created Friends Manager");
   }

   public Map<String, FriendRequest> getFriendRequests() {
      return this.recoveredField1161;
   }

   public void method_26540(String var1, String var2) {
      Friend var3 = this.getFriend(var1);
      if (var3 != null) {
         if (!this.recoveredField1164.containsKey(var1)) {
            this.recoveredField1164.put(var1, new ArrayList<>());
         }

         String var4 = EnumChatFormatting.GRAY + LocalDateTime.now().format(this.recoveredField1162);
         String var5 = EnumChatFormatting.GREEN + var3.getName() + EnumChatFormatting.RESET;
         String var6 = var4 + " " + var5 + ": " + var2;
         this.recoveredField1164.get(var1).add(var6);
      }
   }

   public Friend getFriend(String var1) {
      Iterator var2 = this.recoveredField1163.values().iterator();
      if (!var2.hasNext()) {
         return null;
      } else {
         Friend var3;
         for (var3 = (Friend)var2.next(); !var3.getPlayerId().equals(var1); var3 = (Friend)var2.next()) {
            if (!var2.hasNext()) {
               return null;
            }
         }

         return var3;
      }
   }

   public void method_26533(String var1, String var2) {
      Friend var3 = this.getFriend(var1);
      if (var3 != null) {
         if (!this.recoveredField1160.containsKey(var1)) {
            this.recoveredField1160.put(var1, new ArrayList<>());
         }

         this.recoveredField1160.get(var1).add(var2);
      }
   }
}
