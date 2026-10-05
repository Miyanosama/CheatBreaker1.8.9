package com.cheatbreaker.client.util.friend;

import java.awt.Color;
import java.util.Objects;
import net.minecraft.util.EnumChatFormatting;

public class Friend {
   public String recoveredField1858;
   public String recoveredField1859;
   public Status onlineStatus = Status.ONLINE;
   public String recoveredField1860;
   public boolean online;
   public String recoveredField1861;
   public long offlineSince;

   public void method_04078(String var1) {
      this.recoveredField1860 = var1;
   }

   public long getOfflineSince() {
      return this.offlineSince;
   }

   public Friend(String var1, String var2, String var3, String var4, boolean var5, long var6, Status var8) {
      this.recoveredField1859 = var1;
      this.recoveredField1861 = var2;
      this.recoveredField1860 = var3;
      this.recoveredField1858 = var4;
      this.online = var5;
      this.offlineSince = var6;
      this.onlineStatus = var8;
   }

   public static int getStatusColor(Status var0) {
      if (var0 == null) {
         return -13158601;
      } else {
         int var1;
         switch (var0) {
            case AWAY:
               var1 = new Color(-1722581).getRGB();
               break;
            case BUSY:
               var1 = new Color(-1758421).getRGB();
               break;
            case OFFLINE:
               var1 = new Color(-13158601).getRGB();
               break;
            default:
               var1 = -13369549;
         }

         return var1;
      }
   }

   public void setOnline(boolean var1) {
      this.online = var1;
   }

   public boolean isOnline() {
      return this.online;
   }

   public void setOfflineSince(long var1) {
      this.offlineSince = var1;
   }

   public String method_04073() {
      String var1;
      if (this.online) {
         if (this.recoveredField1858 != null && !Objects.equals(this.recoveredField1858, "")) {
            var1 = "Playing" + EnumChatFormatting.BOLD + " " + this.recoveredField1858;
         } else {
            switch (this.onlineStatus) {
               case AWAY:
                  var1 = "Away";
                  break;
               case BUSY:
                  var1 = "Busy";
                  break;
               default:
                  var1 = "Online";
            }
         }
      } else {
         long var2 = this.offlineSince;
         long var4 = 1000L;
         long var6 = var4 * 60L;
         long var8 = var6 * 60L;
         long var10 = var8 * 24L;
         long var12 = var2 / var10;
         long var18;
         long var14 = (var18 = var2 % var10) / var8;
         long var16 = (var2 = var18 % var8) / var6;
         var1 = var12 > 0L
            ? "Offline for " + var12 + (var12 == 1L ? " day" : " days")
            : (var14 > 0L ? "Offline for " + var14 + (var14 == 1L ? " hour" : " hours") : "Offline for " + var16 + (var16 == 1L ? " minute" : " minutes"));
      }

      return var1;
   }

   public static Friend.Builder builder() {
      return new Friend.Builder();
   }

   public Status getOnlineStatus() {
      return this.onlineStatus;
   }

   public String method_04071() {
      return this.recoveredField1858;
   }

   public void setServer(String var1) {
      this.recoveredField1858 = var1;
   }

   public void method_04074(String var1) {
      this.recoveredField1861 = var1;
   }

   public String getName() {
      return this.recoveredField1861;
   }

   public String getPlayerId() {
      return this.recoveredField1859;
   }

   public String method_04072() {
      return this.recoveredField1860;
   }

   public void setOnlineStatus(Status var1) {
      this.onlineStatus = var1;
   }

   public static class Builder {
      public Status onlineStatus;
      public boolean online;
      public String playerId;
      public String server;
      public String name;
      public String status;
      public long offlineSince;

      public Friend.Builder playerId(String var1) {
         this.playerId = var1;
         return this;
      }

      public Friend.Builder server(String var1) {
         this.server = var1;
         return this;
      }

      public Friend build() {
         return new Friend(this.playerId, this.name, this.status, this.server, this.online, this.offlineSince, this.onlineStatus);
      }

      public Friend.Builder online(boolean var1) {
         this.online = var1;
         return this;
      }

      public Friend.Builder onlineStatus(Status var1) {
         this.onlineStatus = var1;
         return this;
      }

      public Friend.Builder status(String var1) {
         this.status = var1;
         return this;
      }

      @Override
      public String toString() {
         return "Friend.FriendBuilder(playerId="
            + this.playerId
            + ", name="
            + this.name
            + ", status="
            + this.status
            + ", server="
            + this.server
            + ", online="
            + this.online
            + ", offlineSince="
            + this.offlineSince
            + ", onlineStatus="
            + this.onlineStatus
            + ")";
      }

      public Friend.Builder name(String var1) {
         this.name = var1;
         return this;
      }

      public Friend.Builder offlineSince(long var1) {
         this.offlineSince = var1;
         return this;
      }
   }
}
