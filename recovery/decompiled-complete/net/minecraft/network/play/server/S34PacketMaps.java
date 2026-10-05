package net.minecraft.network.play.server;

import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe$2;
import io.netty.util.internal.MpscLinkedQueueNode;
import java.util.Collection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.Vec4b;
import net.minecraft.world.storage.MapData;
import recovered.unidentified.UnidentifiedClass4157;

public class S34PacketMaps implements Packet<INetHandlerPlayClient> {
   public int mapMinY;
   public int mapMinX;
   public int mapMaxY;
   public int mapMaxX;
   public MpscLinkedQueueNode field_0001;
   public Vec4b[] mapVisiblePlayersVec4b;
   public byte mapScale;
   public byte[] mapDataBytes;
   public EpollSocketChannel$EpollSocketUnsafe$2 field_0003;
   public int mapId;
   public UnidentifiedClass4157 field_0000;

   public void setMapdataTo(MapData var1) {
      var1.scale = this.mapScale;
      var1.mapDecorations.clear();

      for (int var2 = 0; var2 < this.mapVisiblePlayersVec4b.length; var2++) {
         Vec4b var3 = this.mapVisiblePlayersVec4b[var2];
         var1.mapDecorations.put("icon-" + var2, var3);
      }

      for (int var4 = 0; var4 < this.mapMaxX; var4++) {
         for (int var5 = 0; var5 < this.mapMaxY; var5++) {
            var1.colors[this.mapMinX + var4 + (this.mapMinY + var5) * 128] = this.mapDataBytes[var4 + var5 * this.mapMaxX];
         }
      }
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.mapId = var1.readVarIntFromBuffer();
      this.mapScale = var1.readByte();
      this.mapVisiblePlayersVec4b = new Vec4b[var1.readVarIntFromBuffer()];

      for (int var2 = 0; var2 < this.mapVisiblePlayersVec4b.length; var2++) {
         short var3 = var1.readByte();
         this.mapVisiblePlayersVec4b[var2] = new Vec4b((byte)(var3 >> 4 & 15), var1.readByte(), var1.readByte(), (byte)(var3 & 15));
      }

      this.mapMaxX = var1.readUnsignedByte();
      if (this.mapMaxX > 0) {
         this.mapMaxY = var1.readUnsignedByte();
         this.mapMinX = var1.readUnsignedByte();
         this.mapMinY = var1.readUnsignedByte();
         this.mapDataBytes = var1.readByteArray();
      }
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleMaps(this);
   }

   public S34PacketMaps(int var1, byte var2, Collection<Vec4b> var3, byte[] var4, int var5, int var6, int var7, int var8) {
      this.mapId = var1;
      this.mapScale = var2;
      this.mapVisiblePlayersVec4b = var3.toArray(new Vec4b[var3.size()]);
      this.mapMinX = var5;
      this.mapMinY = var6;
      this.mapMaxX = var7;
      this.mapMaxY = var8;
      this.mapDataBytes = new byte[var7 * var8];

      for (int var9 = 0; var9 < var7; var9++) {
         for (int var10 = 0; var10 < var8; var10++) {
            this.mapDataBytes[var9 + var10 * var7] = var4[var5 + var9 + (var6 + var10) * 128];
         }
      }
   }

   public int getMapId() {
      return this.mapId;
   }

   public S34PacketMaps() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.mapId);
      var1.writeByte(this.mapScale);
      var1.writeVarIntToBuffer(this.mapVisiblePlayersVec4b.length);

      for (Vec4b var5 : this.mapVisiblePlayersVec4b) {
         var1.writeByte((var5.func_176110_a() & 15) << 4 | var5.func_176111_d() & 15);
         var1.writeByte(var5.func_176112_b());
         var1.writeByte(var5.func_176113_c());
      }

      var1.writeByte(this.mapMaxX);
      if (this.mapMaxX > 0) {
         var1.writeByte(this.mapMaxY);
         var1.writeByte(this.mapMinX);
         var1.writeByte(this.mapMinY);
         var1.writeByteArray(this.mapDataBytes);
      }
   }
}
