package com.cheatbreaker.client.util.voicechat;

import java.util.UUID;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.crafting.CraftingManager$1;
import recovered.unidentified.UnidentifiedClass3830;

public class VoiceUser {
   public UUID uuid;
   public UnidentifiedClass3830 field_0004;
   public String username;
   public ItemFirework field_0003;
   public CraftingManager$1 field_0000;

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
