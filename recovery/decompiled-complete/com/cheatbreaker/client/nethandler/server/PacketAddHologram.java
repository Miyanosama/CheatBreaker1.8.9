package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.vecmath.Matrix3f;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;

public class PacketAddHologram extends Packet {
   public UUID uuid;
   public ChunkRenderDispatcher field_0002;
   public Matrix3f field_0005;
   public double field_0004;
   public double field_0003;
   public List<String> lines;
   public double field_0000;

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).handleAddHologram(this);
   }

   public double getY() {
      return this.field_0000;
   }

   public double getX() {
      return this.field_0004;
   }

   public double getZ() {
      return this.field_0003;
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
      this.field_0004 = var3;
      this.field_0000 = var5;
      this.field_0003 = var7;
   }

   public PacketAddHologram() {
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.uuid);
      var1.buf().writeDouble(this.field_0004);
      var1.buf().writeDouble(this.field_0000);
      var1.buf().writeDouble(this.field_0003);
      var1.writeVarInt(this.lines.size());
      this.lines.forEach(var1::writeString);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.uuid = var1.readUUID();
      this.field_0004 = var1.buf().readDouble();
      this.field_0000 = var1.buf().readDouble();
      this.field_0003 = var1.buf().readDouble();
      int var2 = var1.readVarInt();
      this.lines = new ArrayList<>();

      for (int var3 = 0; var3 < var2; var3++) {
         this.lines.add(var1.readString());
      }
   }
}
