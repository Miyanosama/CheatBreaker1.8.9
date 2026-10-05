package com.cheatbreaker.client.util.friend;

import io.netty.channel.nio.AbstractNioMessageChannel;
import java.awt.Color;
import java.util.Objects;
import net.minecraft.util.EnumChatFormatting;

public class Friend {
   public String field_0003;
   public String field_0006;
   public Status onlineStatus = Status.ONLINE;
   public String field_0005;
   public boolean online;
   public String field_0001;
   public AbstractNioMessageChannel field_0007;
   public long offlineSince;

   public void method_04078(String var1) {
      this.field_0005 = var1;
   }

   public long getOfflineSince() {
      return this.offlineSince;
   }

   public Friend(String var1, String var2, String var3, String var4, boolean var5, long var6, Status var8) {
      this.field_0006 = var1;
      this.field_0001 = var2;
      this.field_0005 = var3;
      this.field_0003 = var4;
      this.online = var5;
      this.offlineSince = var6;
      this.onlineStatus = var8;
   }

   public static int getStatusColor(Status var0) {
      if (var0 == null) {
         return -13158601;
      } else {
         int var1;
         switch (Friend$1.$SwitchMap$com$cheatbreaker$client$util$friend$Status[var0.ordinal()]) {
            case 1:
               var1 = new Color(-1722581).getRGB();
               break;
            case 2:
               var1 = new Color(-1758421).getRGB();
               break;
            case 3:
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
         if (this.field_0003 != null && !Objects.equals(this.field_0003, "")) {
            var1 = "Playing" + EnumChatFormatting.BOLD + " " + this.field_0003;
         } else {
            switch (Friend$1.$SwitchMap$com$cheatbreaker$client$util$friend$Status[this.onlineStatus.ordinal()]) {
               case 1:
                  var1 = "Away";
                  break;
               case 2:
                  var1 = "Busy";
                  break;
               default:
                  var1 = "Online";
            }
         }
      } else {
         long var2 = this.offlineSince;
         long var4 = -5808062763793432L & 1074293738L;
         long var6 = var4 * (5985936866573054270L & 1370623613L);
         long var8 = var6 * (168448574L & 7695394603721073853L);
         long var10 = var8 * (134219864L & -7685667817000467426L);
         long var12 = var2 / var10;
         long var18;
         long var14 = (var18 = var2 % var10) / var8;
         long var16 = (var2 = var18 % var8) / var6;
         var1 = var12 > (7494530898587681056L & -7494530900105601911L)
            ? "Offline for " + var12 + (var12 == (151257731L & 8053578906425047081L) ? " day" : " days")
            : (
               var14 > (1303187521L & 3857144983287136818L)
                  ? "Offline for " + var14 + (var14 == (-7681763471104212475L & 7681763469749700641L) ? " hour" : " hours")
                  : "Offline for " + var16 + (var16 == (275316745L & 538052689L) ? " minute" : " minutes")
            );
      }

      return var1;
   }

   public static Friend$Builder builder() {
      return new Friend$Builder();
   }

   public Status getOnlineStatus() {
      return this.onlineStatus;
   }

   public String method_04071() {
      return this.field_0003;
   }

   public void setServer(String var1) {
      this.field_0003 = var1;
   }

   public void method_04074(String var1) {
      this.field_0001 = var1;
   }

   public String getName() {
      return this.field_0001;
   }

   public String getPlayerId() {
      return this.field_0006;
   }

   public String method_04072() {
      return this.field_0005;
   }

   public void setOnlineStatus(Status var1) {
      this.onlineStatus = var1;
   }
}
