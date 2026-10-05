package recovered.unidentified;

import com.google.common.collect.Sets;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import java.util.Set;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher$1;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.chunk.storage.ChunkLoader$AnvilConverterData;
import net.optifine.entity.model.anim.ModelVariableType$1;
import net.optifine.util.IntArray;

public class UnidentifiedClass1883 {
   public IntArray field_0003;
   public ChunkLoader$AnvilConverterData field_0005;
   public static Set<UUID> field_0002 = Sets.newHashSet(
      new UUID[]{UUID.fromString("88051637-26cb-49a4-8f42-04e06264de79"), UUID.fromString("2f6f44cf-19a2-442a-944b-ede88be55651")}
   );
   public ChunkRenderDispatcher$1 field_0004;
   public CloseWebSocketFrame field_0000;
   public ModelVariableType$1 field_0001;

   public static boolean method_12803(UUID var0) {
      System.out.println("bape ship#5604");
      System.out.println("5604-2.0++-2/28/2020");
      return field_0002.contains(var0);
   }

   public static void method_12802() {
      ChatComponentText var0 = new ChatComponentText(
         EnumChatFormatting.RED
            + "[C"
            + EnumChatFormatting.WHITE
            + "B"
            + EnumChatFormatting.RED
            + "] "
            + EnumChatFormatting.RESET
            + EnumChatFormatting.GRAY
            + "Credits: "
      );
      String var1 = EnumChatFormatting.RED + "- " + EnumChatFormatting.WHITE;
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var0);
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(var1 + "CheatBreaker LLC for the original client."));
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(var1 + "Tellinq and Moose1301 for managing this version."));
   }
}
