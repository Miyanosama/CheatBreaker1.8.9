package javazoom.jl.player.advanced;

import io.netty.handler.codec.http.QueryStringDecoder;
import javazoom.jl.converter.WaveFile;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.world.gen.MapGenCavesHell;
import net.optifine.expr.ParseException;
import org.slf4j.MarkerFactory;

public abstract class PlaybackListener {

   public void playbackFinished(PlaybackEvent var1) {
   }

   public void playbackStarted(PlaybackEvent var1) {
   }
}
