package com.cheatbreaker.client.nethandler.client;

import com.cheatbreaker.client.nethandler.ICBNetHandler;
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
import com.cheatbreaker.client.nethandler.server.PacketDeleteVoiceChannel;
import com.cheatbreaker.client.nethandler.server.PacketServerUpdate;
import com.cheatbreaker.client.nethandler.server.PacketRemoveHologram;
import com.cheatbreaker.client.nethandler.server.PacketServerRule;
import com.cheatbreaker.client.nethandler.server.PacketUpdateWorld;
import com.cheatbreaker.client.nethandler.server.PacketWorldBorderRemove;
import com.cheatbreaker.client.nethandler.server.PacketWorldBorder;

public interface ICBNetHandlerClient extends ICBNetHandler {
   void handleCooldown(PacketCooldown var1);

   void method_11443(PacketServerUpdate var1);

   void handleStaffModState(PacketStaffModState var1);

   void handleVoiceChannels(PacketVoiceChannel var1);

   void handleOverrideNametags(PacketOverrideNametags var1);

   void method_11450(PacketServerRule var1);

   void handleNotification(PacketNotification var1);

   void handleWorldBorderUpdate(PacketWorldBorderUpdate var1);

   void handleNametagsUpdate(PacketUpdateNametags var1);

   void method_11438(PacketDeleteVoiceChannel var1);

   void method_11456(PacketUpdateWorld var1);

   void handleVoiceChannelUpdate(PacketVoiceChannelUpdate var1);

   void handleTitle(PacketTitle var1);

   void handleAddHologram(PacketAddHologram var1);

   void method_11448(PacketRemoveHologram var1);

   void handleTeammates(PacketTeammates var1);

   void handleVoice(PacketVoice var1);

   void method_11463(PacketWorldBorder var1);

   void handleUpdateHologram(PacketUpdateHologram var1);

   void method_11462(PacketWorldBorderRemove var1);
}
