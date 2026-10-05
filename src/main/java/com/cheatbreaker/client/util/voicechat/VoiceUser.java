package com.cheatbreaker.client.util.voicechat;

import java.util.UUID;

public class VoiceUser {
   public UUID uuid;
   public String username;

   public VoiceUser(UUID var1, String var2) {
      this.uuid = var1;
      this.username = var2;
   }

   public String getUsername() {
      return this.username;
   }

   public UUID getUUID() {
      return this.uuid;
   }
}
