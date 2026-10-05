package recovered.unidentified;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.block.BlockLadder;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.server.CommandMessage;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$3;
import org.apache.log4j.net.SimpleSocketServer;
import org.apache.log4j.varia.Roller;
import org.java_websocket.framing.ControlFrame;

public class UnidentifiedClass0144 extends EventBus$Event {
   public Roller field_0005;
   public SimpleSocketServer field_0004;
   public ScaledResolution field_0001;
   public ControlFrame field_0006;
   public CommandMessage field_0000;
   public StructureStrongholdPieces$3 field_0002;
   public BlockLadder field_0003;

   public UnidentifiedClass0144(ScaledResolution var1) {
      this.field_0001 = var1;
   }

   public ScaledResolution method_01054() {
      return this.field_0001;
   }
}
