package io.netty.handler.timeout;

import io.netty.channel.ChannelException;
import javazoom.jl.decoder.OutputChannels;
import net.minecraft.client.resources.model.BuiltInModel;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.world.biome.WorldChunkManagerHell;
import org.apache.log4j.pattern.PropertiesPatternConverter;

public class TimeoutException extends ChannelException {
   public static final long serialVersionUID = 4673641882869672533L;

   @Override
   public Throwable fillInStackTrace() {
      return this;
   }
}
