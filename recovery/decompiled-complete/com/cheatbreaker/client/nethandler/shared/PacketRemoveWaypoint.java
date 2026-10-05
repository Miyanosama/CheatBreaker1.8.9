package com.cheatbreaker.client.nethandler.shared;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.event.ClickEvent;
import net.optifine.ConnectedTextures$1;

public class PacketRemoveWaypoint extends Packet {
   public String field_0000;
   public ConnectedTextures$1 field_0001;
   public String field_0004;
   public ClickEvent field_0003;
   public ModelZombieVillager field_0002;

   public PacketRemoveWaypoint() {
   }

   public String method_27970() {
      return this.field_0004;
   }

   public PacketRemoveWaypoint(String var1, String var2) {
      this.field_0000 = var1;
      this.field_0004 = var2;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.field_0000);
      var1.writeString(this.field_0004);
   }

   public String method_27969() {
      return this.field_0000;
   }

   @Override
   public void process(ICBNetHandler var1) {
      var1.handleRemoveWaypoint(this);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.field_0000 = var1.readString();
      this.field_0004 = var1.readString();
   }
}
