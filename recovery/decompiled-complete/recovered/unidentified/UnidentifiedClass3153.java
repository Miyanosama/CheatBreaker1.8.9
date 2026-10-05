package recovered.unidentified;

import com.cheatbreaker.client.nethandler.server.PacketStaffModState;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import com.jagrosh.discordipc.entities.pipe.WindowsPipe;
import net.minecraft.item.crafting.RecipesMapExtending;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S20PacketEntityProperties$Snapshot;
import net.minecraft.world.chunk.Chunk$2;
import net.optifine.entity.model.ModelAdapterBook;

public class UnidentifiedClass3153 extends WSPacket {
   public WindowsPipe field_0005;
   public ModelAdapterBook field_0002;
   public Chunk$2 field_0004;
   public PacketStaffModState field_0000;
   public RecipesMapExtending field_0001;
   public S20PacketEntityProperties$Snapshot field_0006;
   public String field_0003;

   public UnidentifiedClass3153(String var1) {
      this.field_0003 = var1;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.field_0003);
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   public String method_19694() {
      return this.field_0003;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0003 = var1.readStringFromBuffer(52);
   }
}
