package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.handler.codec.marshalling.LimitingByteInput$TooBigObjectException;
import java.net.URISyntaxException;
import net.minecraft.block.BlockStone;
import net.minecraft.client.gui.GuiStreamIndicator;
import net.minecraft.client.particle.EntitySpellParticleFX$Factory;
import net.minecraft.command.server.CommandMessageRaw;

public class UnidentifiedClass0334 extends Thread {
   public EntitySpellParticleFX$Factory field_0002;
   public LimitingByteInput$TooBigObjectException field_0004;
   public GuiStreamIndicator field_0001;
   public CommandMessageRaw field_0003;
   public BlockStone field_0000;

   @Override
   public void run() {
      try {
         Thread.sleep(-4915279879678664199L & 4915279879212659626L);
         CheatBreaker.getInstance().method_19761();
      } catch (URISyntaxException | InterruptedException var2) {
         var2.printStackTrace();
      }
   }
}
