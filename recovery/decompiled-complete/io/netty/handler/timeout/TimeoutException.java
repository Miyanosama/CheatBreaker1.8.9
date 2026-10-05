package io.netty.handler.timeout;

import io.netty.channel.ChannelException;
import javazoom.jl.decoder.OutputChannels;
import net.minecraft.client.resources.model.BuiltInModel;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.world.biome.WorldChunkManagerHell;
import org.apache.log4j.pattern.PropertiesPatternConverter;

public class TimeoutException extends ChannelException {
   public PropertiesPatternConverter __junk7201757205732741884;
   public OutputChannels __junk3603040493573519860;
   public BuiltInModel __junk622702051460611934;
   public static long serialVersionUID;
   public WorldChunkManagerHell __junk330620905808001490;
   public ItemAppleGold __junk8061039290537611770;

   @Override
   public Throwable fillInStackTrace() {
      return this;
   }
}
