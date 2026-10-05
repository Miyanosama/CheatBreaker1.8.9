package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.handler.codec.http.DefaultHttpRequest;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$DesertPyramid;

public class UnidentifiedClass1951 {
   public DefaultHttpRequest field_0002;
   public UnidentifiedClass4432 field_0004;
   public List<UnidentifiedClass0000> field_0001 = new ArrayList<>();
   public ComponentScatteredFeaturePieces$DesertPyramid field_0003;
   public UnidentifiedClass3838 field_0000;

   public void method_13256(ChatComponentText var1) {
      if (!CheatBreaker.getInstance().getGlobalSettings().field_0074.method_08908()) {
         var1.method_07469(true);
         Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var1);
      }
   }

   public UnidentifiedClass1951() {
      this.field_0001.add(this.field_0000 = new UnidentifiedClass3838());
      this.field_0001.add(this.field_0004 = new UnidentifiedClass4432());
   }
}
