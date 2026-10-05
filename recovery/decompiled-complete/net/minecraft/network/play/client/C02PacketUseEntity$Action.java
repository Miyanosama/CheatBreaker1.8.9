package net.minecraft.network.play.client;

import io.netty.handler.codec.MessageToMessageCodec;
import net.minecraft.block.BlockRailPowered;

public enum C02PacketUseEntity$Action {
   INTERACT,
   INTERACT_AT,
   ATTACK;

   public MessageToMessageCodec field_0005;
   public BlockRailPowered field_0000;
}
