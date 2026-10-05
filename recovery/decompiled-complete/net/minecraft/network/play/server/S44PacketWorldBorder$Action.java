package net.minecraft.network.play.server;

import net.minecraft.item.ItemLilyPad;

public enum S44PacketWorldBorder$Action {
   SET_WARNING_BLOCKS,
   SET_CENTER,
   INITIALIZE,
   SET_SIZE,
   LERP_SIZE,
   SET_WARNING_TIME;
   public ItemLilyPad field_0002;
   // $VF: synthetic field
   public static S44PacketWorldBorder$Action[] $VALUES = new S44PacketWorldBorder$Action[]{
      SET_SIZE, S44PacketWorldBorder$Action.LERP_SIZE, SET_CENTER, INITIALIZE, S44PacketWorldBorder$Action.SET_WARNING_TIME, SET_WARNING_BLOCKS
   };
}
