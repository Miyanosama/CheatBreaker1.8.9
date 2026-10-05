package recovered.unidentified;

import com.cheatbreaker.client.module.type.keystrokes.KeystrokesModule;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.command.CommandBase;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.WeightedRandom$Item;
import net.optifine.util.ChunkUtils;

public class UnidentifiedClass1812 extends WSPacket {
   public ChunkUtils field_0003;
   public WeightedRandom$Item field_0001;
   public KeystrokesModule field_0002;
   public CommandBase field_0000;

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   @Override
   public void read(PacketBuffer var1) {
   }

   @Override
   public void write(PacketBuffer var1) {
   }
}
