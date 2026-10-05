package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.nethandler.shared.PacketRemoveWaypoint;
import java.util.TimerTask;
import javazoom.jl.converter.Converter;
import net.minecraft.client.Minecraft;

public class UnidentifiedClass3624 extends TimerTask {
   public Converter field_0000;
   public PacketRemoveWaypoint field_0001;

   @Override
   public void run() {
      CheatBreaker var1 = CheatBreaker.getInstance();
      if (var1 != null && (var1.getModuleManager() != null || Minecraft.getMinecraft().thePlayer != null)) {
         if (var1.getModuleManager().field_0001.isEnabled()
            && var1.getModuleManager().field_0001.field_0012.method_08908()
            && Minecraft.getMinecraft().getCurrentServerData() != null
            && Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("hypixel")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/tip all");
         }
      }
   }
}
