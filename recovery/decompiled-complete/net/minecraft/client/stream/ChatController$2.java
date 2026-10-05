package net.minecraft.client.stream;

import javazoom.jl.converter.WaveFile$WaveFormat_ChunkData;
import net.minecraft.client.gui.ScaledResolution;
import tv.twitch.chat.ChatEvent;

// $VF: synthetic class
public class ChatController$2 {
   public WaveFile$WaveFormat_ChunkData field_0001;
   public ScaledResolution field_0000;

   static {
      try {
         field_0002[ChatEvent.TTV_CHAT_JOINED_CHANNEL.ordinal()] = 1;
      } catch (NoSuchFieldError var10) {
      }

      try {
         field_0002[ChatEvent.TTV_CHAT_LEFT_CHANNEL.ordinal()] = 2;
      } catch (NoSuchFieldError var9) {
      }

      field_175976_a = new int[ChatController$EnumChannelState.values().length];

      try {
         field_175976_a[ChatController$EnumChannelState.Connected.ordinal()] = 1;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_175976_a[ChatController$EnumChannelState.Connecting.ordinal()] = 2;
      } catch (NoSuchFieldError var7) {
      }

      try {
         field_175976_a[ChatController$EnumChannelState.Created.ordinal()] = 3;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_175976_a[ChatController$EnumChannelState.Disconnected.ordinal()] = 4;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_175976_a[ChatController$EnumChannelState.Disconnecting.ordinal()] = 5;
      } catch (NoSuchFieldError var4) {
      }

      field_0004 = new int[ChatController$EnumEmoticonMode.values().length];

      try {
         field_0004[ChatController$EnumEmoticonMode.None.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0004[ChatController$EnumEmoticonMode.Url.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0004[ChatController$EnumEmoticonMode.TextureAtlas.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
