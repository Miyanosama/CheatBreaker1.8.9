package com.cheatbreaker.client.util.voicechat;

import io.netty.util.concurrent.GlobalEventExecutor$PurgeTask;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumChatFormatting;

public class VoiceChannel {
   public List<VoiceUser> voiceUsers = new ArrayList<>();
   public NBTUtil field_0005;
   public GlobalEventExecutor$PurgeTask field_0002;
   public List<UUID> listeningList = new ArrayList<>();
   public String string;
   public UUID uuid;

   public boolean isInChannel(UUID var1) {
      return this.voiceUsers.stream().anyMatch(var1x -> var1x.getUUID().equals(var1));
   }

   public String method_03528() {
      return this.string;
   }

   public List<VoiceUser> getUsers() {
      return this.voiceUsers;
   }

   public void addToListening(UUID var1, String var2) {
      if (this.isInChannel(var1)) {
         this.listeningList.add(var1);
      }
   }

   public boolean method_03536(UUID var1) {
      return this.voiceUsers.removeIf(var1x -> var1x.getUUID().equals(var1));
   }

   public List<UUID> method_03526() {
      return this.listeningList;
   }

   public UUID getUUID() {
      return this.uuid;
   }

   public boolean method_03527(UUID var1) {
      return this.listeningList.stream().anyMatch(var1x -> var1x.equals(var1));
   }

   public VoiceChannel(UUID var1, String var2) {
      this.uuid = var1;
      this.string = var2;
   }

   public boolean method_03534(UUID var1) {
      return this.listeningList.removeIf(var1x -> var1x.equals(var1));
   }

   public VoiceUser getOrCreateVoiceUser(UUID var1, String var2) {
      VoiceUser var3 = new VoiceUser(var1, EnumChatFormatting.getTextWithoutFormattingCodes(var2));
      if (!this.isInChannel(var1)) {
         this.voiceUsers.add(var3);
      }

      return var3;
   }
}
