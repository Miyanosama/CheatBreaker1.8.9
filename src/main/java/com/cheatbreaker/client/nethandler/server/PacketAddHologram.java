package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacketAddHologram extends Packet {
   public UUID uuid;
   public double recoveredField1525;
   public double recoveredField1526;
   public List<String> lines;
   public double recoveredField1527;

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleAddHologram(this);
   }

   public double getY() {
      return this.recoveredField1527;
   }

   public double getX() {
      return this.recoveredField1525;
   }

   public double getZ() {
      return this.recoveredField1526;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public List<String> getLines() {
      return this.lines;
   }

   public PacketAddHologram(List<String> var1, UUID var2, double var3, double var5, double var7) {
      this.lines = var1;
      this.uuid = var2;
      this.recoveredField1525 = var3;
      this.recoveredField1527 = var5;
      this.recoveredField1526 = var7;
   }

   public PacketAddHologram() {
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.uuid);
      var1.buf().writeDouble(this.recoveredField1525);
      var1.buf().writeDouble(this.recoveredField1527);
      var1.buf().writeDouble(this.recoveredField1526);
      var1.writeVarInt(this.lines.size());
      this.lines.forEach(var1::writeString);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.uuid = var1.readUUID();
      this.recoveredField1525 = var1.buf().readDouble();
      this.recoveredField1527 = var1.buf().readDouble();
      this.recoveredField1526 = var1.buf().readDouble();
      int var2 = var1.readVarInt();
      this.lines = new ArrayList<>();

      for (int var3 = 0; var3 < var2; var3++) {
         this.lines.add(var1.readString());
      }
   }
}
