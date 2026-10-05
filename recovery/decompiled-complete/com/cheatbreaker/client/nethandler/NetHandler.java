package com.cheatbreaker.client.nethandler;

import com.cheatbreaker.client.BuildBranch;
import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.DisconnectEvent;
import com.cheatbreaker.client.event.type.PluginMessageEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.ModuleRule;
import com.cheatbreaker.client.module.staff.NametagsModule;
import com.cheatbreaker.client.module.staff.StaffModule;
import com.cheatbreaker.client.module.type.MiniMapModule;
import com.cheatbreaker.client.module.type.ScoreboardModule;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import com.cheatbreaker.client.nethandler.client.PacketVoiceChannelSwitch;
import com.cheatbreaker.client.nethandler.server.PacketAddHologram;
import com.cheatbreaker.client.nethandler.server.PacketCooldown;
import com.cheatbreaker.client.nethandler.server.PacketNotification;
import com.cheatbreaker.client.nethandler.server.PacketOverrideNametags;
import com.cheatbreaker.client.nethandler.server.PacketStaffModState;
import com.cheatbreaker.client.nethandler.server.PacketTeammates;
import com.cheatbreaker.client.nethandler.server.PacketTitle;
import com.cheatbreaker.client.nethandler.server.PacketUpdateHologram;
import com.cheatbreaker.client.nethandler.server.PacketUpdateNametags;
import com.cheatbreaker.client.nethandler.server.PacketVoice;
import com.cheatbreaker.client.nethandler.server.PacketVoiceChannel;
import com.cheatbreaker.client.nethandler.server.PacketVoiceChannelUpdate;
import com.cheatbreaker.client.nethandler.server.PacketWorldBorderUpdate;
import com.cheatbreaker.client.nethandler.shared.PacketAddWaypoint;
import com.cheatbreaker.client.nethandler.shared.PacketRemoveWaypoint;
import com.cheatbreaker.client.network.CustomPayloadSender;
import com.cheatbreaker.client.network.messages.Message;
import com.cheatbreaker.client.util.hologram.Hologram;
import com.cheatbreaker.client.util.teammates.Teammate;
import com.cheatbreaker.client.util.title.Title;
import com.cheatbreaker.client.util.title.Title$TitleType;
import com.cheatbreaker.client.util.voicechat.VoiceChannel;
import com.cheatbreaker.client.util.voicechat.VoiceUser;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import io.netty.buffer.Unpooled;
import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent$Action;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.biome.BiomeEndDecorator;
import recovered.unidentified.UnidentifiedClass0433;
import recovered.unidentified.UnidentifiedClass0798;
import recovered.unidentified.UnidentifiedClass1748;
import recovered.unidentified.UnidentifiedClass1927;
import recovered.unidentified.UnidentifiedClass3253;
import recovered.unidentified.UnidentifiedClass3884;
import recovered.unidentified.UnidentifiedClass4110;
import recovered.unidentified.UnidentifiedClass4396;

public class NetHandler implements ICBNetHandlerClient {
   public List<UUID> anotherUuidList;
   public String world;
   public VoiceChannel voiceChannel;
   public boolean field_0011;
   public boolean field_0001;
   public boolean field_0002;
   public List<UUID> uuidList;
   public CheatBreaker field_0009 = CheatBreaker.getInstance();
   public Map<UUID, List<String>> nametagsMap = new HashMap<>();
   public boolean field_0014;
   public List<VoiceChannel> voiceChannels;
   public BiomeEndDecorator field_0007;
   public boolean field_0008;
   public boolean field_0004;
   public boolean field_0010;

   public boolean method_11436() {
      return this.field_0008;
   }

   @Override
   public void handleNametagsUpdate(PacketUpdateNametags var1) {
      if (var1.getPlayersMap() != null) {
         NametagsModule.method_27977(new HashMap<>());

         for (Entry var3 : var1.getPlayersMap().entrySet()) {
            NametagsModule.method_27976().put(UUID.fromString(((UUID)var3.getKey()).toString()), (List<String>)var3.getValue());
         }
      } else {
         NametagsModule.method_27977(null);
      }
   }

   public void addUsers(List<VoiceUser> var1) {
      for (VoiceUser var3 : var1) {
         if (var3 != null && this.uuidList.contains(var3.getUUID()) && !this.anotherUuidList.contains(var3.getUUID())) {
            this.anotherUuidList.add(var3.getUUID());
            this.sendPacketToQueue(new PacketVoiceChannelSwitch(var3.getUUID()));
         }
      }
   }

   public List<UUID> getAnotherUuidList() {
      return this.anotherUuidList;
   }

   @Override
   public void method_11456(UnidentifiedClass3253 var1) {
      this.method_11451("World Update: " + var1.method_20224());
      this.world = var1.method_20224();
   }

   public void sendPacketToQueue(Packet var1) {
      if (var1 != null && this.field_0009.getGlobalSettings().field_0062) {
         Message.z(false, var1);
      }

      PacketBuffer var2 = new PacketBuffer(Unpooled.buffer());
      var2.writeBytes(Packet.getPacketData(Objects.requireNonNull(var1)));
      CustomPayloadSender var3 = new CustomPayloadSender(this.field_0009.method_19766(), var2);
      Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(var3);
   }

   @Override
   public void handleAddWaypoint(PacketAddWaypoint var1) {
      int var2 = var1.getX();
      int var3 = var1.getY();
      int var4 = var1.getZ();
   }

   public CheatBreaker method_11434() {
      return this.field_0009;
   }

   @Override
   public void handleNotification(PacketNotification var1) {
      this.field_0009.getModuleManager().notifications.queueNotification(var1.getLevel(), var1.getMessage(), var1.getDurationMs());
   }

   public boolean method_11472() {
      return this.field_0011;
   }

   public VoiceChannel getVoiceChannel(UUID var1) {
      if (this.voiceChannels == null) {
         return null;
      } else {
         Iterator var3 = this.voiceChannels.iterator();

         while (var3.hasNext()) {
            VoiceChannel var2;
            if ((var2 = (VoiceChannel)var3.next()).getUUID().equals(var1)) {
               return var2;
            }
         }

         return null;
      }
   }

   @Override
   public void method_11462(UnidentifiedClass3884 var1) {
      this.field_0009.getBorderManager().method_29501(var1.method_23476());
   }

   public void method_11464(PluginMessageEvent var1) {
      try {
         if (var1.method_26236().equals("REGISTER")) {
            String var2 = new String(var1.method_26237(), Charsets.UTF_8);
            this.field_0008 = var2.contains(this.field_0009.method_19746());
            if (!this.field_0008) {
               this.field_0001 = var2.contains(this.field_0009.method_19804());
               if (!this.field_0001) {
                  boolean var3 = var2.contains(this.field_0009.method_19766());
                  this.field_0001 = var3;
                  if (var3) {
                     this.field_0011 = true;
                  }
               }
            }

            this.field_0010 = var2.contains(this.field_0009.method_19803());
            ArrayList var10 = new ArrayList();
            PacketBuffer var4 = new PacketBuffer(Unpooled.buffer());
            var10.add(this.field_0009.method_19746());
            var10.add(this.field_0011 ? this.field_0009.method_19766() : this.field_0009.method_19804());
            boolean var5 = false;

            for (String var7 : var10) {
               if (!var5) {
                  var4.writeBytes(new byte[]{0});
               }

               var4.writeBytes(var7.getBytes(StandardCharsets.UTF_8));
               var5 = true;
            }

            if (Minecraft.getMinecraft().getNetHandler() != null && this.field_0008) {
               Minecraft.getMinecraft().getNetHandler().addToSendQueue(new CustomPayloadSender("REGISTER", var4));
            }

            this.method_11433();
         } else if (var1.method_26236().equals(this.field_0009.method_19746())
            || var1.method_26236().equals(this.field_0009.method_19766())
            || var1.method_26236().equals(this.field_0009.method_19804())) {
            Packet var9 = Packet.handle(this, var1.method_26237());
            if (var9 != null) {
               var9.process(this);
            }

            if (this.field_0009.getGlobalSettings().field_0062) {
               ChatComponentText var11 = new ChatComponentText(
                  EnumChatFormatting.GRAY + "Received: " + EnumChatFormatting.WHITE + var9.getClass().getSimpleName()
               );
               var11.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent$Action.SHOW_TEXT, new ChatComponentText(new Gson().toJson(var9))));
               var11.method_07469(true);
               Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var11);
            }
         }
      } catch (Exception | AssertionError var8) {
         var8.printStackTrace();
      }
   }

   public boolean method_11430() {
      return this.field_0010;
   }

   @Override
   public void method_11448(UnidentifiedClass1748 var1) {
      Hologram.method_28152().removeIf(var1x -> var1x.method_28149().equals(var1.method_12150()));
   }

   public Map<UUID, List<String>> getNametagsMap() {
      return this.nametagsMap;
   }

   @Override
   public void method_11450(UnidentifiedClass1927 var1) {
      switch (var1.method_13129()) {
         case field_0007:
            this.method_11451("Voice is: " + (var1.method_13131() ? "enabled" : "disabled"));
            this.field_0004 = var1.method_13131();
            break;
         case field_0003:
            String var2 = var1.method_13130();
            switch (var2) {
               case "NEUTRAL":
                  MiniMapModule.state = ModuleRule.MINIMAP_NOT_ALLOWED;
                  return;
               case "FORCED_OFF":
                  MiniMapModule.state = ModuleRule.field_0003;
                  return;
               default:
                  return;
            }
         case field_0000:
            this.field_0014 = var1.method_13131();
            break;
         case field_0005:
            this.field_0002 = var1.method_13131();
      }
   }

   public boolean method_11476() {
      return this.field_0004;
   }

   public VoiceUser getVoiceUser(UUID var1) {
      if (this.voiceChannels != null && this.voiceChannel != null) {
         Iterator var3 = this.voiceChannel.getUsers().iterator();

         while (var3.hasNext()) {
            VoiceUser var2;
            if ((var2 = (VoiceUser)var3.next()).getUUID().equals(var1)) {
               return var2;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   @Override
   public void handleAddHologram(PacketAddHologram var1) {
      Hologram var2 = new Hologram(var1.getUuid(), var1.getX(), var1.getY(), var1.getZ());
      Hologram.method_28152().add(var2);
      var2.method_28150(var1.getLines().toArray(new String[0]));
   }

   public boolean method_11470() {
      return this.field_0001;
   }

   public void method_11451(String var1) {
      System.out.println("\u001b[31m[CheatBreaker]\u001b[0m " + var1);
   }

   public NetHandler() {
      this.uuidList = new ArrayList<>();
      this.anotherUuidList = new ArrayList<>();
      this.field_0008 = false;
      this.field_0010 = false;
      this.field_0004 = false;
      this.world = "";
      this.field_0001 = false;
      this.field_0011 = false;
      CheatBreaker.getInstance().field_0034.info(CheatBreaker.getInstance().field_0016 + "Created Network Manager");
   }

   public boolean method_11432() {
      return this.field_0014;
   }

   @Override
   public void handleUpdateHologram(PacketUpdateHologram var1) {
      Hologram.method_28152()
         .stream()
         .filter(var1x -> var1x.method_28149().equals(var1.getUuid()))
         .forEach(var1x -> var1x.method_28150(var1.getLines().toArray(new String[0])));
   }

   public boolean method_11477() {
      return this.field_0002;
   }

   @Override
   public void handleVoiceChannels(PacketVoiceChannel var1) {
      this.method_11451("Voice Channel Received: " + var1.getName());
      this.method_11451("Channel has " + var1.getPlayers().size() + " members");
      if (!this.doesVoiceChannelExist(var1.getUuid())) {
         if (this.voiceChannels == null) {
            this.voiceChannels = new ArrayList<>();
         }

         VoiceChannel var2 = new VoiceChannel(var1.getUuid(), var1.getName());
         this.voiceChannels.add(var2);
         ArrayList var3 = new ArrayList();

         for (Entry var5 : var1.getPlayers().entrySet()) {
            this.method_11451("Added member [" + (String)var5.getValue() + "]");
            VoiceUser var6 = var2.getOrCreateVoiceUser((UUID)var5.getKey(), (String)var5.getValue());
            if (var6 != null) {
               var3.add(var6);
            }
         }

         this.addUsers(var3);

         for (Entry var8 : var1.getListening().entrySet()) {
            this.method_11451("Added listener [" + (String)var8.getValue() + "]");
            var2.addToListening((UUID)var8.getKey(), (String)var8.getValue());
         }
      }
   }

   @Override
   public void method_11443(UnidentifiedClass0798 var1) {
      this.method_11451("Retrieved " + var1.method_05486());
      this.field_0009.method_19778(var1.method_05486());
   }

   public List<UUID> getUuidList() {
      return this.uuidList;
   }

   @Override
   public void handleCooldown(PacketCooldown var1) {
      CheatBreaker.getInstance().getModuleManager().cooldowns.method_28310(var1.getMessage(), var1.getDurationMs(), var1.getIconId());
   }

   @Override
   public void handleOverrideNametags(PacketOverrideNametags var1) {
      if (var1.getTags() == null) {
         this.nametagsMap.remove(var1.getPlayer());
      } else {
         Collections.reverse(var1.getTags());
         if (CheatBreaker.getInstance().method_19797().method_10217().method_06000(BuildBranch.field_0007)) {
            System.out.println("playerId=" + var1.getPlayer() + ", Tags=" + var1.getTags());
         }

         this.nametagsMap.put(var1.getPlayer(), var1.getTags());
      }
   }

   public VoiceChannel getVoiceChannel() {
      return this.voiceChannel;
   }

   @Override
   public void handleRemoveWaypoint(PacketRemoveWaypoint var1) {
   }

   @Override
   public void handleVoiceChannelUpdate(PacketVoiceChannelUpdate var1) {
      this.method_11451("Channel Update: " + var1.getName() + " (" + var1.getStatus() + ")");
      if (this.voiceChannels != null) {
         VoiceChannel var2 = this.getVoiceChannel(var1.getChannelUuid());
         if (var2 == null) {
            this.method_11451(var1.getChannelUuid().toString());
         } else {
            switch (var1.getStatus()) {
               case 0:
                  VoiceUser var3 = var2.getOrCreateVoiceUser(var1.getUuid(), var1.getName());
                  if (var3 != null) {
                     this.addUsers(ImmutableList.of(var3));
                  }
                  break;
               case 1:
                  var2.method_03536(var1.getUuid());
                  break;
               case 2:
                  if (!var1.getUuid().toString().equals(Minecraft.getMinecraft().getSession().getPlayerID())) {
                     if (this.voiceChannel == var2) {
                        ChatComponentText var8 = new ChatComponentText(
                           EnumChatFormatting.AQUA
                              + var1.getName()
                              + EnumChatFormatting.AQUA
                              + " joined "
                              + var2.method_03528()
                              + " channel. Press 'Unbound'!"
                              + EnumChatFormatting.RESET
                        );
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var8);
                     }
                  } else {
                     this.voiceChannel = var2;

                     for (VoiceChannel var5 : this.voiceChannels) {
                        var5.method_03534(var1.getUuid());
                     }

                     ChatComponentText var7 = new ChatComponentText(
                        EnumChatFormatting.AQUA + "Joined " + var2.method_03528() + " channel. Press 'Unbound' to talk!" + EnumChatFormatting.RESET
                     );
                     Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var7);
                  }

                  var2.addToListening(var1.getUuid(), var1.getName());
                  break;
               case 3:
                  if (this.voiceChannel == var2 && !var1.getUuid().toString().equals(Minecraft.getMinecraft().getSession().getPlayerID())) {
                     ChatComponentText var4 = new ChatComponentText(
                        EnumChatFormatting.AQUA
                           + var1.getName()
                           + EnumChatFormatting.AQUA
                           + " left "
                           + var2.method_03528()
                           + " channel. Press 'Unbound'!"
                           + EnumChatFormatting.RESET
                     );
                     Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var4);
                  }

                  var2.method_03534(var1.getUuid());
            }
         }
      }
   }

   public String getWorld() {
      return this.world;
   }

   @Override
   public void handleStaffModState(PacketStaffModState var1) {
      for (AbstractModule var3 : this.field_0009.getModuleManager().field_0049) {
         if (var3.getName().equals(var1.getMod().replaceAll("_", "").toLowerCase())) {
            var3.setStaffModuleEnabled(var1.isState());
         }
      }
   }

   public void method_11433() {
      this.field_0004 = false;
      this.field_0014 = false;
      this.voiceChannels = null;
      this.voiceChannel = null;
      this.field_0002 = false;
      this.world = "";
      this.anotherUuidList.clear();

      for (StaffModule var2 : this.field_0009.getModuleManager().field_0049) {
         var2.disableStaffModule();
      }

      this.field_0009.getBorderManager().method_29496();
      this.field_0009.method_19760().method_07638().clear();
      NametagsModule.method_27977(null);
      MiniMapModule.state = ModuleRule.field_0003;
      ScoreboardModule.rule = ModuleRule.MINIMAP_NOT_ALLOWED;
      this.field_0009.getModuleManager().teammatesModule.method_01332().clear();
      Hologram.method_28152().clear();
   }

   public boolean doesVoiceChannelExist(UUID var1) {
      return this.getVoiceChannel(var1) != null;
   }

   @Override
   public void method_11463(UnidentifiedClass4110 var1) {
      this.field_0009
         .getBorderManager()
         .method_29503(
            var1.method_24753(),
            var1.method_24756(),
            var1.method_24754(),
            var1.method_24759(),
            var1.method_24757(),
            var1.method_24755(),
            var1.method_24760(),
            var1.method_24758(),
            var1.method_24761()
         );
   }

   public List<VoiceChannel> getVoiceChannels() {
      return this.voiceChannels;
   }

   @Override
   public void handleTeammates(PacketTeammates var1) {
      Map var2 = var1.getPlayers();
      UUID var3 = var1.getLeader();
      long var4 = var1.getLastMs();
      if ((Boolean)this.field_0009.getGlobalSettings().field_0067.getValue()
         && var2 != null
         && !var2.isEmpty()
         && (var2.size() != 1 || !var2.containsKey(Minecraft.getMinecraft().thePlayer.aK()))) {
         int var6 = 0;

         for (Entry var8 : var2.entrySet()) {
            Teammate var9 = this.field_0009.getModuleManager().teammatesModule.method_01337(((UUID)var8.getKey()).toString());
            if (var9 == null) {
               var9 = new Teammate(((UUID)var8.getKey()).toString(), var3 != null && var3.equals(var8.getKey()));
               this.field_0009.getModuleManager().teammatesModule.method_01332().add(var9);
               Random var10 = new Random();
               if (var6 < this.field_0009.getModuleManager().teammatesModule.method_01329().length) {
                  var9.method_04986(new Color(this.field_0009.getModuleManager().teammatesModule.method_01329()[var6]));
               } else {
                  float var11 = var10.nextFloat();
                  float var12 = var10.nextFloat();
                  float var13 = var10.nextFloat() / 2.0F;
                  var9.method_04986(new Color(var11, var12, var13));
               }
            }

            try {
               double var17 = (Double)((Map)var8.getValue()).get("x");
               double var18 = (Double)((Map)var8.getValue()).get("y") + 2.0;
               double var14 = (Double)((Map)var8.getValue()).get("z");
               var9.method_04984(var17, var18, var14, var4);
            } catch (Exception var16) {
               var16.printStackTrace();
            }

            var6++;
         }

         this.field_0009.getModuleManager().teammatesModule.method_01332().removeIf(var1x -> !var2.containsKey(UUID.fromString(var1x.method_04983())));
      } else {
         this.field_0009.getModuleManager().teammatesModule.method_01332().clear();
      }
   }

   @Override
   public void method_11438(UnidentifiedClass0433 var1) {
      this.method_11451("Deleted channel: " + var1.method_03211().toString());
      if (this.voiceChannels != null) {
         this.voiceChannels.removeIf(var1x -> var1x.getUUID().equals(var1.method_03211()));
      }

      if (this.voiceChannel != null && this.voiceChannel.getUUID().equals(var1.method_03211())) {
         this.voiceChannel = null;
      }
   }

   public void method_11465(UnidentifiedClass4396 var1) {
      this.field_0010 = false;
   }

   @Override
   public void handleVoice(PacketVoice var1) {
      this.field_0009.getModuleManager().voiceChat.addUserToSpoken(var1.getUuid());
   }

   @Override
   public void handleWorldBorderUpdate(PacketWorldBorderUpdate var1) {
      this.field_0009.getBorderManager().method_29502(var1.getId(), var1.getMinX(), var1.getMinZ(), var1.getMaxX(), var1.getMaxZ(), var1.getDurationTicks());
   }

   public void method_11459(DisconnectEvent var1) {
      this.field_0008 = false;
      this.field_0010 = false;
   }

   @Override
   public void handleTitle(PacketTitle var1) {
      Title$TitleType var2 = Title$TitleType.field_0007;
      if (var1.getType().equalsIgnoreCase("subtitle")) {
         var2 = Title$TitleType.field_0000;
      }

      this.field_0009
         .method_19760()
         .method_07638()
         .add(new Title(var1.getMessage(), var2, var1.getScale(), var1.getDisplayTimeMs(), var1.getFadeInTimeMs(), var1.getFadeOutTimeMs()));
   }
}
