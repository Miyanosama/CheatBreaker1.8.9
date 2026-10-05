package net.minecraft.client.network;

import io.netty.util.internal.TypeParameterMatcher$ReflectiveMatcher;
import net.minecraft.network.play.server.S38PacketPlayerListItem$Action;
import net.minecraft.network.play.server.S45PacketTitle$Type;
import net.minecraft.util.StatCollector;

// $VF: synthetic class
public class NetHandlerPlayClient$4 {
   public TypeParameterMatcher$ReflectiveMatcher field_0003;
   public StatCollector field_0000;

   static {
      try {
         field_178884_b[S38PacketPlayerListItem$Action.ADD_PLAYER.ordinal()] = 1;
      } catch (NoSuchFieldError var7) {
      }

      try {
         field_178884_b[S38PacketPlayerListItem$Action.UPDATE_GAME_MODE.ordinal()] = 2;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_178884_b[S38PacketPlayerListItem$Action.UPDATE_LATENCY.ordinal()] = 3;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_178884_b[S38PacketPlayerListItem$Action.UPDATE_DISPLAY_NAME.ordinal()] = 4;
      } catch (NoSuchFieldError var4) {
      }

      field_178885_a = new int[S45PacketTitle$Type.values().length];

      try {
         field_178885_a[S45PacketTitle$Type.TITLE.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_178885_a[S45PacketTitle$Type.SUBTITLE.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_178885_a[S45PacketTitle$Type.RESET.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
