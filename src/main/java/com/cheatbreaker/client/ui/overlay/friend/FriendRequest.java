package com.cheatbreaker.client.ui.overlay.friend;

public class FriendRequest {
   public boolean friend;
   public String username;
   public String playerId;

   public String getPlayerId() {
      return this.playerId;
   }

   public FriendRequest(String var1, String var2) {
      this.username = var1;
      this.playerId = var2;
   }

   public String getUsername() {
      return this.username;
   }

   public FriendRequest(String var1, String var2, boolean var3) {
      this.username = var1;
      this.playerId = var2;
      this.friend = var3;
   }

   public boolean isFriend() {
      return this.friend;
   }

   public void setFriend(boolean var1) {
      this.friend = var1;
   }
}
