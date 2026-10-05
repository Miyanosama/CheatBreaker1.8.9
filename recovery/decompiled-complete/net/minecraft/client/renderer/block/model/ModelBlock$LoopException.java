package net.minecraft.client.renderer.block.model;

import com.cheatbreaker.client.nethandler.server.PacketVoiceChannelUpdate;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.scoreboard.Score;
import org.apache.log4j.pattern.NameAbbreviator$PatternAbbreviator;

public class ModelBlock$LoopException extends RuntimeException {
   public NameAbbreviator$PatternAbbreviator field_0001;
   public Score field_0003;
   public EntityDiggingFX field_0000;
   public PacketVoiceChannelUpdate field_0002;
}
