package recovered.unidentified;

import io.netty.handler.codec.http.HttpObjectDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.gui.GuiDownloadTerrain;

public class UnidentifiedClass4913 {
   public GuiDownloadTerrain field_0001;
   public Map<String, List<String>> field_0002 = new HashMap<>();
   public HttpObjectDecoder field_0000;

   public boolean method_29290(String var1, String var2) {
      for (Entry var4 : this.field_0002.entrySet()) {
         if (var1.contains((CharSequence)var4.getKey())) {
            return ((List)var4.getValue()).stream().filter(var1x -> var1x.contains(var2)).findFirst().orElse(null) != null;
         }
      }

      return false;
   }

   public void method_29289() {
      this.field_0002.put("hypixel", Arrays.asList("freelook", "auto text"));
   }

   public Map<String, List<String>> method_29291() {
      return this.field_0002;
   }
}
