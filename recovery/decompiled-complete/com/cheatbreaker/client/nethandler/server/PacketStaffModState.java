package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import io.netty.buffer.PooledDirectByteBuf;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stones;

public class PacketStaffModState extends Packet {
   public PooledDirectByteBuf field_0000;
   public StructureStrongholdPieces$Stones field_0001;
   public String mod;
   public boolean state;
   public EntitySelectors field_0002;

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.mod);
      var1.buf().writeBoolean(this.state);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.mod = var1.readString();
      this.state = var1.buf().readBoolean();
   }

   public String getMod() {
      return this.mod;
   }

   public boolean isState() {
      return this.state;
   }

   public PacketStaffModState(String var1, boolean var2) {
      this.mod = var1;
      this.state = var2;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleStaffModState(this);
   }

   public PacketStaffModState() {
   }
}
