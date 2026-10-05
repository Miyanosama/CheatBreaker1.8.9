package net.minecraft.network.play.client;

import io.netty.channel.DefaultChannelPipeline$4;
import net.minecraft.client.model.ModelBat;
import net.minecraft.scoreboard.ScoreObjective;
import org.slf4j.MDC$MDCCloseable;

public enum C0BPacketEntityAction$Action {
   OPEN_INVENTORY,
   STOP_SNEAKING,
   STOP_SLEEPING,
   RIDING_JUMP,
   START_SNEAKING,
   START_SPRINTING,
   STOP_SPRINTING;
   public MDC$MDCCloseable field_0005;
   // $VF: synthetic field
   public static C0BPacketEntityAction$Action[] $VALUES = new C0BPacketEntityAction$Action[]{
      C0BPacketEntityAction$Action.START_SNEAKING,
      STOP_SNEAKING,
      C0BPacketEntityAction$Action.STOP_SLEEPING,
      C0BPacketEntityAction$Action.START_SPRINTING,
      C0BPacketEntityAction$Action.STOP_SPRINTING,
      C0BPacketEntityAction$Action.RIDING_JUMP,
      OPEN_INVENTORY
   };
   public ScoreObjective field_0002;
   public ModelBat field_0010;
   public DefaultChannelPipeline$4 field_0011;
}
