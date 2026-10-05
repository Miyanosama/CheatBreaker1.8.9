package com.cheatbreaker.client.nethandler.client;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.server.ICBNetHandlerServer;
import java.util.UUID;
import junit.swingui.CounterPanel;
import net.minecraft.client.gui.GuiCustomizeSkin$1;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.inventory.Container;

public class PacketVoiceChannelSwitch extends Packet {
   public GuiCustomizeSkin$1 field_0000;
   public Container field_0001;
   public UUID switchingTo;
   public CommandBlockLogic field_0003;
   public CounterPanel field_0002;

   @Override
   public void read(ByteBufWrapper var1) {
      this.switchingTo = var1.readUUID();
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerServer)var1).handleVoiceChannelSwitch(this);
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.switchingTo);
   }

   public PacketVoiceChannelSwitch() {
   }

   public UUID getSwitchingTo() {
      return this.switchingTo;
   }

   public PacketVoiceChannelSwitch(UUID var1) {
      this.switchingTo = var1;
   }
}
