package recovered.unidentified;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.optifine.CustomLoadingScreen;

public class UnidentifiedClass3620 {
   public CustomLoadingScreen field_0000;

   public static void method_22169(String var0) {
      Minecraft var1 = Minecraft.getMinecraft();
      if (var1 != null && var1.ingameGUI != null && var1.ingameGUI.getChatGUI() != null) {
         ChatComponentText var2 = new ChatComponentText(var0);
         var2.method_07469(true);
         var1.ingameGUI.getChatGUI().printChatMessage(var2);
      }
   }
}
