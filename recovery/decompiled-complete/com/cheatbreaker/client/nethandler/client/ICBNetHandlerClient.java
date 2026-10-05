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
import recovered.unidentified.UnidentifiedClass0433;
import recovered.unidentified.UnidentifiedClass0798;
import recovered.unidentified.UnidentifiedClass1748;
import recovered.unidentified.UnidentifiedClass1927;
import recovered.unidentified.UnidentifiedClass3253;
import recovered.unidentified.UnidentifiedClass3884;
import recovered.unidentified.UnidentifiedClass4110;

public interface ICBNetHandlerClient extends ICBNetHandler {
   void handleCooldown(PacketCooldown var1);

   void method_11443(UnidentifiedClass0798 var1);

   void handleStaffModState(PacketStaffModState var1);

   void handleVoiceChannels(PacketVoiceChannel var1);

   void handleOverrideNametags(PacketOverrideNametags var1);

   void method_11450(UnidentifiedClass1927 var1);

   void handleNotification(PacketNotification var1);

   void handleWorldBorderUpdate(PacketWorldBorderUpdate var1);

   void handleNametagsUpdate(PacketUpdateNametags var1);

   void method_11438(UnidentifiedClass0433 var1);

   void method_11456(UnidentifiedClass3253 var1);

   void handleVoiceChannelUpdate(PacketVoiceChannelUpdate var1);

   void handleTitle(PacketTitle var1);

   void handleAddHologram(PacketAddHologram var1);

   void method_11448(UnidentifiedClass1748 var1);

   void handleTeammates(PacketTeammates var1);

   void handleVoice(PacketVoice var1);

   void method_11463(UnidentifiedClass4110 var1);

   void handleUpdateHologram(PacketUpdateHologram var1);

   void method_11462(UnidentifiedClass3884 var1);
}
