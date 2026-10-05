package com.cheatbreaker.client.nethandler.client;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.server.ICBNetHandlerServer;
import net.minecraft.client.gui.ChatLine;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$14;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$4;

public class PacketClientVoice extends Packet {
   public CategoryNodeEditor$4 field_0000;
   public ChatLine field_0001;
   public byte[] data;
   public LogBrokerMonitor$14 field_0002;

   public byte[] getData() {
      return this.data;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.data = this.readBlob(var1);
   }

   public PacketClientVoice(byte[] var1) {
      this.data = var1;
   }

   public PacketClientVoice() {
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerServer)var1).handleVoice(this);
   }

   @Override
   public void write(ByteBufWrapper var1) {
      this.writeBlob(var1, this.data);
   }
}
