package recovered.unidentified;

import io.netty.handler.codec.http.HttpClientCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;

public class UnidentifiedClass5065 {
   public static Minecraft field_0001 = Minecraft.getMinecraft();
   public HttpClientCodec field_0002;
   public ModelPlayer field_0000;

   public static String method_30076(String var0, int var1) {
      if (var0.endsWith(".zip")) {
         var0 = var0.substring(0, var0.length() - 4);
      }

      if (field_0001.fontRendererObj.getStringWidth(var0) > var1) {
         var0 = field_0001.fontRendererObj.trimStringToWidth(var0, var1 - field_0001.fontRendererObj.getStringWidth("...")) + "...";
      }

      return var0;
   }
}
