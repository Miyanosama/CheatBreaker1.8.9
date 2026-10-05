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
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import com.cheatbreaker.client.nethandler.server.PacketDeleteVoiceChannel;
import com.cheatbreaker.client.nethandler.server.PacketServerUpdate;
import com.cheatbreaker.client.nethandler.server.PacketRemoveHologram;
import com.cheatbreaker.client.nethandler.server.PacketServerRule;
import com.cheatbreaker.client.nethandler.server.PacketUpdateWorld;
import com.cheatbreaker.client.nethandler.server.PacketWorldBorderRemove;
import com.cheatbreaker.client.nethandler.server.PacketWorldBorder;
import com.cheatbreaker.client.event.type.WorldChangeEvent;

public class NetHandler implements ICBNetHandlerClient {
   public List<UUID> anotherUuidList;
   public String world;
   public VoiceChannel voiceChannel;
   public boolean recoveredField1472;
   public boolean recoveredField1473;
   public boolean recoveredField1474;
   public List<UUID> uuidList;
   public CheatBreaker recoveredField1475 = CheatBreaker.getInstance();
   public Map<UUID, List<String>> nametagsMap = new HashMap<>();
   public boolean recoveredField1476;
   public List<VoiceChannel> voiceChannels;
   public boolean recoveredField1477;
   public boolean recoveredField1478;
   public boolean recoveredField1479;

   public boolean method_11436() {
      return this.recoveredField1477;
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
   public void method_11456(PacketUpdateWorld var1) {
      this.method_11451("World Update: " + var1.method_20224());
      this.world = var1.method_20224();
   }

   public void sendPacketToQueue(Packet var1) {
      if (var1 != null && this.recoveredField1475.getGlobalSettings().recoveredField514) {
         Message.z(false, var1);
      }

      PacketBuffer var2 = new PacketBuffer(Unpooled.buffer());
      var2.writeBytes(Packet.getPacketData(Objects.requireNonNull(var1)));
      CustomPayloadSender var3 = new CustomPayloadSender(this.recoveredField1475.method_19766(), var2);
      Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(var3);
   }

   @Override
   public void handleAddWaypoint(PacketAddWaypoint var1) {
      int var2 = var1.getX();
      int var3 = var1.getY();
      int var4 = var1.getZ();
   }

   public CheatBreaker method_11434() {
      return this.recoveredField1475;
   }

   @Override
   public void handleNotification(PacketNotification var1) {
      this.recoveredField1475.getModuleManager().notifications.queueNotification(var1.getLevel(), var1.getMessage(), var1.getDurationMs());
   }

   public boolean method_11472() {
      return this.recoveredField1472;
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
   public void method_11462(PacketWorldBorderRemove var1) {
      this.recoveredField1475.getBorderManager().method_29501(var1.method_23476());
   }

   public void method_11464(PluginMessageEvent var1) {
      try {
         if (var1.method_26236().equals("REGISTER")) {
            String var2 = new String(var1.method_26237(), Charsets.UTF_8);
            this.recoveredField1477 = var2.contains(this.recoveredField1475.method_19746());
            if (!this.recoveredField1477) {
               this.recoveredField1473 = var2.contains(this.recoveredField1475.method_19804());
               if (!this.recoveredField1473) {
                  boolean var3 = var2.contains(this.recoveredField1475.method_19766());
                  this.recoveredField1473 = var3;
                  if (var3) {
                     this.recoveredField1472 = true;
                  }
               }
            }

            this.recoveredField1479 = var2.contains(this.recoveredField1475.method_19803());
            ArrayList var10 = new ArrayList();
            PacketBuffer var4 = new PacketBuffer(Unpooled.buffer());
            var10.add(this.recoveredField1475.method_19746());
            var10.add(this.recoveredField1472 ? this.recoveredField1475.method_19766() : this.recoveredField1475.method_19804());
            boolean var5 = false;

            for (String var7 : (Iterable<String>)(Iterable<?>)(var10)) {
               if (!var5) {
                  var4.writeBytes(new byte[]{0});
               }

               var4.writeBytes(var7.getBytes(StandardCharsets.UTF_8));
               var5 = true;
            }

            if (Minecraft.getMinecraft().getNetHandler() != null && this.recoveredField1477) {
               Minecraft.getMinecraft().getNetHandler().addToSendQueue(new CustomPayloadSender("REGISTER", var4));
            }

            this.method_11433();
         } else if (var1.method_26236().equals(this.recoveredField1475.method_19746())
            || var1.method_26236().equals(this.recoveredField1475.method_19766())
            || var1.method_26236().equals(this.recoveredField1475.method_19804())) {
            Packet var9 = Packet.handle(this, var1.method_26237());
            if (var9 != null) {
               var9.process(this);
            }

            if (this.recoveredField1475.getGlobalSettings().recoveredField514) {
               ChatComponentText var11 = new ChatComponentText(
                  EnumChatFormatting.GRAY + "Received: " + EnumChatFormatting.WHITE + var9.getClass().getSimpleName()
               );
               var11.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(new Gson().toJson(var9))));
               var11.method_07469(true);
               Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var11);
            }
         }
      } catch (Exception | AssertionError var8) {
         var8.printStackTrace();
      }
   }

   public boolean method_11430() {
      return this.recoveredField1479;
   }

   @Override
   public void method_11448(PacketRemoveHologram var1) {
      Hologram.method_28152().removeIf(var1x -> var1x.method_28149().equals(var1.method_12150()));
   }

   public Map<UUID, List<String>> getNametagsMap() {
      return this.nametagsMap;
   }

   @Override
   public void method_11450(PacketServerRule var1) {
      switch (var1.method_13129()) {
         case VOICE_ENABLED:
            this.method_11451("Voice is: " + (var1.method_13131() ? "enabled" : "disabled"));
            this.recoveredField1478 = var1.method_13131();
            break;
         case MINIMAP_STATUS:
            String var2 = var1.method_13130();
            switch (var2) {
               case "NEUTRAL":
                  MiniMapModule.state = ModuleRule.NEUTRAL;
                  return;
               case "FORCED_OFF":
                  MiniMapModule.state = ModuleRule.FORCED_OFF;
                  return;
               default:
                  return;
            }
         case SERVER_HANDLES_WAYPOINTS:
            this.recoveredField1476 = var1.method_13131();
            break;
         case COMPETITIVE_GAMEMODE:
            this.recoveredField1474 = var1.method_13131();
      }
   }

   public boolean method_11476() {
      return this.recoveredField1478;
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
      return this.recoveredField1473;
   }

   public void method_11451(String var1) {
      System.out.println("\u001b[31m[CheatBreaker]\u001b[0m " + var1);
   }

   public NetHandler() {
      this.uuidList = new ArrayList<>();
      this.anotherUuidList = new ArrayList<>();
      this.recoveredField1477 = false;
      this.recoveredField1479 = false;
      this.recoveredField1478 = false;
      this.world = "";
      this.recoveredField1473 = false;
      this.recoveredField1472 = false;
      CheatBreaker.getInstance().recoveredField1579.info(CheatBreaker.getInstance().recoveredField1553 + "Created Network Manager");
   }

   public boolean method_11432() {
      return this.recoveredField1476;
   }

   @Override
   public void handleUpdateHologram(PacketUpdateHologram var1) {
      Hologram.method_28152()
         .stream()
         .filter(var1x -> var1x.method_28149().equals(var1.getUuid()))
         .forEach(var1x -> var1x.method_28150(var1.getLines().toArray(new String[0])));
   }

   public boolean method_11477() {
      return this.recoveredField1474;
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
   public void method_11443(PacketServerUpdate var1) {
      this.method_11451("Retrieved " + var1.method_05486());
      this.recoveredField1475.method_19778(var1.method_05486());
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
         if (CheatBreaker.getInstance().method_19797().method_10217().method_06000(BuildBranch.DEVELOPMENT)) {
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
      for (AbstractModule var3 : this.recoveredField1475.getModuleManager().recoveredField1725) {
         if (var3.getName().equals(var1.getMod().replaceAll("_", "").toLowerCase())) {
            var3.setStaffModuleEnabled(var1.isState());
         }
      }
   }

   public void method_11433() {
      this.recoveredField1478 = false;
      this.recoveredField1476 = false;
      this.voiceChannels = null;
      this.voiceChannel = null;
      this.recoveredField1474 = false;
      this.world = "";
      this.anotherUuidList.clear();

      for (StaffModule var2 : this.recoveredField1475.getModuleManager().recoveredField1725) {
         var2.disableStaffModule();
      }

      this.recoveredField1475.getBorderManager().method_29496();
      this.recoveredField1475.method_19760().method_07638().clear();
      NametagsModule.method_27977(null);
      MiniMapModule.state = ModuleRule.FORCED_OFF;
      ScoreboardModule.rule = ModuleRule.NEUTRAL;
      this.recoveredField1475.getModuleManager().teammatesModule.method_01332().clear();
      Hologram.method_28152().clear();
   }

   public boolean doesVoiceChannelExist(UUID var1) {
      return this.getVoiceChannel(var1) != null;
   }

   @Override
   public void method_11463(PacketWorldBorder var1) {
      this.recoveredField1475
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
      if ((Boolean)this.recoveredField1475.getGlobalSettings().recoveredField564.getValue()
         && var2 != null
         && !var2.isEmpty()
         && (var2.size() != 1 || !var2.containsKey(Minecraft.getMinecraft().thePlayer.aK()))) {
         int var6 = 0;

         for (Entry var8 : (Iterable<Entry>)(Iterable<?>)(var2.entrySet())) {
            Teammate var9 = this.recoveredField1475.getModuleManager().teammatesModule.method_01337(((UUID)var8.getKey()).toString());
            if (var9 == null) {
               var9 = new Teammate(((UUID)var8.getKey()).toString(), var3 != null && var3.equals(var8.getKey()));
               this.recoveredField1475.getModuleManager().teammatesModule.method_01332().add(var9);
               Random var10 = new Random();
               if (var6 < this.recoveredField1475.getModuleManager().teammatesModule.method_01329().length) {
                  var9.method_04986(new Color(this.recoveredField1475.getModuleManager().teammatesModule.method_01329()[var6]));
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

         this.recoveredField1475.getModuleManager().teammatesModule.method_01332().removeIf(var1x -> !var2.containsKey(UUID.fromString(var1x.method_04983())));
      } else {
         this.recoveredField1475.getModuleManager().teammatesModule.method_01332().clear();
      }
   }

   @Override
   public void method_11438(PacketDeleteVoiceChannel var1) {
      this.method_11451("Deleted channel: " + var1.method_03211().toString());
      if (this.voiceChannels != null) {
         this.voiceChannels.removeIf(var1x -> var1x.getUUID().equals(var1.method_03211()));
      }

      if (this.voiceChannel != null && this.voiceChannel.getUUID().equals(var1.method_03211())) {
         this.voiceChannel = null;
      }
   }

   public void method_11465(WorldChangeEvent var1) {
      this.recoveredField1479 = false;
   }

   @Override
   public void handleVoice(PacketVoice var1) {
      this.recoveredField1475.getModuleManager().voiceChat.addUserToSpoken(var1.getUuid());
   }

   @Override
   public void handleWorldBorderUpdate(PacketWorldBorderUpdate var1) {
      this.recoveredField1475
         .getBorderManager()
         .method_29502(var1.getId(), var1.getMinX(), var1.getMinZ(), var1.getMaxX(), var1.getMaxZ(), var1.getDurationTicks());
   }

   public void method_11459(DisconnectEvent var1) {
      this.recoveredField1477 = false;
      this.recoveredField1479 = false;
   }

   @Override
   public void handleTitle(PacketTitle var1) {
      Title.TitleType var2 = Title.TitleType.SUBTITLE;
      if (var1.getType().equalsIgnoreCase("subtitle")) {
         var2 = Title.TitleType.TITLE;
      }

      this.recoveredField1475
         .method_19760()
         .method_07638()
         .add(new Title(var1.getMessage(), var2, var1.getScale(), var1.getDisplayTimeMs(), var1.getFadeInTimeMs(), var1.getFadeOutTimeMs()));
   }
}
