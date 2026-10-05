package javazoom.jl.player.advanced;

import io.netty.handler.codec.http.QueryStringDecoder;
import javazoom.jl.converter.WaveFile;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.renderer.block.model.BlockFaceUV$Deserializer;
import net.minecraft.entity.passive.EntityRabbit$AIAvoidEntity;
import net.minecraft.world.gen.MapGenCavesHell;
import net.optifine.expr.ParseException;
import org.slf4j.MarkerFactory;

public abstract class PlaybackListener {
   public EntityRabbit$AIAvoidEntity __junk536294286336011863;
   public QueryStringDecoder __junk8519551642539504898;
   public MapGenCavesHell __junk4200146890762892078;
   public MarkerFactory __junk1301962198135533347;
   public BlockFaceUV$Deserializer __junk7466806026293866161;
   public ParseException __junk6991761778361908782;
   public AnvilConverterException __junk8218547638817300840;
   public WaveFile __junk5031102561729385616;

   public void playbackFinished(PlaybackEvent var1) {
   }

   public void playbackStarted(PlaybackEvent var1) {
   }
}
