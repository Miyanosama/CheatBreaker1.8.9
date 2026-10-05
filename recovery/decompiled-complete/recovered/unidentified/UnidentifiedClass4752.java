package recovered.unidentified;

import io.netty.bootstrap.Bootstrap$2;
import io.netty.channel.epoll.EpollEventLoopGroup;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Map.Entry;
import net.minecraft.block.BlockFence;
import net.optifine.shaders.SVertexAttrib;
import org.apache.log4j.helpers.DateLayout;
import org.json.JSONObject;

public class UnidentifiedClass4752 {
   public DateLayout field_0002;
   public BlockFence field_0004;
   public EpollEventLoopGroup field_0001;
   public Bootstrap$2 field_0003;
   public SVertexAttrib field_0000;

   public static Properties method_28478(JSONObject var0) {
      Properties var1 = new Properties();
      if (var0 != null) {
         for (Entry var3 : var0.method_07199()) {
            Object var4 = var3.getValue();
            if (!JSONObject.field_0003.equals(var4)) {
               var1.put(var3.getKey(), var4.toString());
            }
         }
      }

      return var1;
   }

   public static JSONObject method_28479(Properties var0) {
      JSONObject var1 = new JSONObject(var0 == null ? 0 : var0.size());
      if (var0 != null && !var0.isEmpty()) {
         Enumeration var2 = var0.propertyNames();

         while (var2.hasMoreElements()) {
            String var3 = (String)var2.nextElement();
            var1.put(var3, var0.getProperty(var3));
         }
      }

      return var1;
   }
}
