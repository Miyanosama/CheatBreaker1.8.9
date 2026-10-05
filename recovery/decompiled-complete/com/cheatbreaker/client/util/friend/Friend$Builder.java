package com.cheatbreaker.client.util.friend;

import net.minecraft.entity.ai.EntityAIPanic;

public class Friend$Builder {
   public Status onlineStatus;
   public boolean online;
   public String playerId;
   public EntityAIPanic field_0005;
   public String server;
   public String name;
   public String status;
   public long offlineSince;

   public Friend$Builder playerId(String var1) {
      this.playerId = var1;
      return this;
   }

   public Friend$Builder server(String var1) {
      this.server = var1;
      return this;
   }

   public Friend build() {
      return new Friend(this.playerId, this.name, this.status, this.server, this.online, this.offlineSince, this.onlineStatus);
   }

   public Friend$Builder online(boolean var1) {
      this.online = var1;
      return this;
   }

   public Friend$Builder onlineStatus(Status var1) {
      this.onlineStatus = var1;
      return this;
   }

   public Friend$Builder status(String var1) {
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

   public Friend$Builder name(String var1) {
      this.name = var1;
      return this;
   }

   public Friend$Builder offlineSince(long var1) {
      this.offlineSince = var1;
      return this;
   }
}
