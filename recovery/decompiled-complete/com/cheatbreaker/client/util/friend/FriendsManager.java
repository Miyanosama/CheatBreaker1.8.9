package com.cheatbreaker.client.util.friend;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.overlay.friend.FriendRequest;
import io.netty.channel.embedded.EmbeddedChannel$LastInboundHandler;
import io.netty.handler.codec.serialization.ReferenceMap;
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
   public EmbeddedChannel$LastInboundHandler field_0003;
   public ReferenceMap field_0006;
   public Map<String, List<String>> field_0002;
   public Map<String, FriendRequest> field_0005;
   public DateTimeFormatter field_0000;
   public Map<String, Friend> field_0001 = new HashMap<>();
   public Map<String, List<String>> field_0007;
   public Map<String, List<String>> field_0004;

   public void method_26536(String var1, String var2) {
      Friend var3 = this.getFriend(var1);
      if (var3 != null) {
         if (!this.field_0004.containsKey(var1)) {
            this.field_0004.put(var1, new ArrayList<>());
         }

         this.field_0004.get(var1).add(var3.getName() + ": " + var2);
         String var4 = EnumChatFormatting.GRAY + LocalDateTime.now().format(this.field_0000);
         String var5 = EnumChatFormatting.AQUA + Minecraft.getMinecraft().getSession().getUsername() + EnumChatFormatting.RESET;
         String var6 = var4 + " " + var5 + ": " + var2;
         this.method_26533(var1, var6);
      }
   }

   public Map<String, Friend> getFriends() {
      return this.field_0001;
   }

   public Map<String, List<String>> method_26538() {
      return this.field_0004;
   }

   public Map<String, List<String>> method_26532() {
      return this.field_0002;
   }

   public void readMessages(String var1) {
      if (this.field_0007.containsKey(var1)) {
         List var2 = this.field_0007.get(var1);
         if (!this.field_0002.containsKey(var1)) {
            this.field_0002.put(var1, new ArrayList<>());
         }

         this.field_0002.get(var1).addAll(var2);
         this.field_0007.remove(var1);
      }
   }

   public Map<String, List<String>> method_26537() {
      return this.field_0007;
   }

   public FriendsManager() {
      this.field_0005 = new HashMap<>();
      this.field_0002 = new HashMap<>();
      this.field_0007 = new HashMap<>();
      this.field_0004 = new HashMap<>();
      this.field_0000 = DateTimeFormatter.ofPattern("HH:mm:ss");
      CheatBreaker.getInstance().field_0034.info(CheatBreaker.getInstance().field_0016 + "Created Friends Manager");
   }

   public Map<String, FriendRequest> getFriendRequests() {
      return this.field_0005;
   }

   public void method_26540(String var1, String var2) {
      Friend var3 = this.getFriend(var1);
      if (var3 != null) {
         if (!this.field_0007.containsKey(var1)) {
            this.field_0007.put(var1, new ArrayList<>());
         }

         String var4 = EnumChatFormatting.GRAY + LocalDateTime.now().format(this.field_0000);
         String var5 = EnumChatFormatting.GREEN + var3.getName() + EnumChatFormatting.RESET;
         String var6 = var4 + " " + var5 + ": " + var2;
         this.field_0007.get(var1).add(var6);
      }
   }

   public Friend getFriend(String var1) {
      Iterator var2 = this.field_0001.values().iterator();
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
         if (!this.field_0002.containsKey(var1)) {
            this.field_0002.put(var1, new ArrayList<>());
         }

         this.field_0002.get(var1).add(var2);
      }
   }
}
