package net.minecraft.network.play.server;

import io.netty.channel.epoll.EpollDatagramChannelConfig;
import net.minecraft.client.renderer.GlStateManager$TexGen;
import org.apache.log4j.helpers.PatternParser$LocationPatternConverter;

public enum S38PacketPlayerListItem$Action {
   ADD_PLAYER,
   REMOVE_PLAYER,
   UPDATE_GAME_MODE,
   UPDATE_DISPLAY_NAME,
   UPDATE_LATENCY;

   public GlStateManager$TexGen field_0003;
   public EpollDatagramChannelConfig field_0006;
   public PatternParser$LocationPatternConverter field_0005;
}
