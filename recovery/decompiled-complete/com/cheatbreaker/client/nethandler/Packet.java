package com.cheatbreaker.client.nethandler;

import com.cheatbreaker.client.event.EventBus$Event;
import com.cheatbreaker.client.nethandler.client.PacketClientVoice;
import com.cheatbreaker.client.nethandler.client.PacketVoiceChannelSwitch;
import com.cheatbreaker.client.nethandler.client.PacketVoiceMute;
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
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import recovered.unidentified.UnidentifiedClass0433;
import recovered.unidentified.UnidentifiedClass0798;
import recovered.unidentified.UnidentifiedClass1104;
import recovered.unidentified.UnidentifiedClass1748;
import recovered.unidentified.UnidentifiedClass1927;
import recovered.unidentified.UnidentifiedClass3253;
import recovered.unidentified.UnidentifiedClass3884;
import recovered.unidentified.UnidentifiedClass4110;

public abstract class Packet {
   public static BiMap<Class<?>, Integer> field_0001 = HashBiMap.create();
   public UnidentifiedClass1104 field_0003;
   public Object attachment;
   public EventBus$Event field_0002;

   public abstract void process(ICBNetHandler var1);

   public void attach(Object var1) {
      this.attachment = var1;
   }

   public abstract void read(ByteBufWrapper var1);

   public byte[] readBlob(ByteBufWrapper var1) {
      short var2 = var1.buf().readShort();
      if (var2 >= 0) {
         byte[] var3 = new byte[var2];
         var1.buf().readBytes(var3);
         return var3;
      } else {
         System.out.println("Key was smaller than nothing!  Weird key!");
         return null;
      }
   }

   public static Packet handle(ICBNetHandler var0, byte[] var1) {
      return handle(var0, var1, null);
   }

   public Object getAttachment() {
      return this.attachment;
   }

   static {
      addPacket(0, PacketClientVoice.class);
      addPacket(1, PacketVoiceChannelSwitch.class);
      addPacket(2, PacketVoiceMute.class);
      addPacket(3, PacketCooldown.class);
      addPacket(4, PacketAddHologram.class);
      addPacket(5, PacketUpdateHologram.class);
      addPacket(6, UnidentifiedClass1748.class);
      addPacket(7, PacketOverrideNametags.class);
      addPacket(8, PacketUpdateNametags.class);
      addPacket(9, PacketNotification.class);
      addPacket(10, UnidentifiedClass1927.class);
      addPacket(11, UnidentifiedClass0798.class);
      addPacket(12, PacketStaffModState.class);
      addPacket(13, PacketTeammates.class);
      addPacket(14, PacketTitle.class);
      addPacket(15, UnidentifiedClass3253.class);
      addPacket(16, PacketVoice.class);
      addPacket(17, PacketVoiceChannel.class);
      addPacket(18, UnidentifiedClass0433.class);
      addPacket(19, PacketVoiceChannelUpdate.class);
      addPacket(20, UnidentifiedClass4110.class);
      addPacket(21, UnidentifiedClass3884.class);
      addPacket(22, PacketWorldBorderUpdate.class);
      addPacket(23, PacketAddWaypoint.class);
      addPacket(24, PacketRemoveWaypoint.class);
   }

   public abstract void write(ByteBufWrapper var1);

   public static byte[] getPacketData(Packet var0) {
      ByteBufWrapper var1 = new ByteBufWrapper(Unpooled.buffer());
      var1.writeVarInt((Integer)field_0001.get(var0.getClass()));

      try {
         var0.write(var1);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

      return var1.buf().array();
   }

   public static void addPacket(int var0, Class<?> var1) {
      if (field_0001.containsKey(var1)) {
         throw new IllegalArgumentException("Duplicate packet class (" + var1.getSimpleName() + "), already used by " + field_0001.get(var1));
      } else if (field_0001.containsValue(var0)) {
         throw new IllegalArgumentException("Duplicate packet ID (" + var0 + "), already used by" + ((Class)field_0001.inverse().get(var0)).getSimpleName());
      } else {
         field_0001.put(var1, var0);
      }
   }

   public void writeBlob(ByteBufWrapper var1, byte[] var2) {
      var1.buf().writeShort(var2.length);
      var1.buf().writeBytes(var2);
   }

   public static Packet handle(ICBNetHandler var0, byte[] var1, Object var2) {
      ByteBufWrapper var3 = new ByteBufWrapper(Unpooled.wrappedBuffer(var1));
      int var4 = var3.readVarInt();
      Class var5 = (Class)field_0001.inverse().get(var4);
      if (var5 != null) {
         try {
            Packet var6 = (Packet)var5.newInstance();
            if (var2 != null) {
               var6.attach(var2);
            }

            var6.read(var3);
            return var6;
         } catch (IllegalAccessException | InstantiationException | IOException var7) {
            var7.printStackTrace();
         }
      }

      return null;
   }
}
