package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.dash.Station;
import io.netty.channel.AbstractChannelHandlerContext$6;
import io.netty.handler.codec.http.multipart.AbstractHttpData;
import io.netty.handler.codec.spdy.DefaultSpdyPingFrame;
import java.time.Duration;
import java.time.LocalDateTime;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.server.management.UserListOps;

public class UnidentifiedClass3436 extends Thread {
   public AbstractChannelHandlerContext$6 field_0003;
   public CompiledChunk field_0005;
   public ViewFrustum field_0002;
   public DefaultSpdyPingFrame field_0004;
   public AbstractHttpData field_0000;
   public UserListOps field_0001;

   @Override
   public void run() {
      while (true) {
         try {
            Station var1;
            if ((var1 = CheatBreaker.getInstance().getRadioManager().getCurrentStation()) != null
               && var1.method_05594() != null
               && Duration.between(var1.method_05594(), LocalDateTime.now()).toMillis() / (4856378249168169962L & -4856378249744886787L)
                  >= var1.getDuration() + 2) {
               var1.getData();
               Thread.sleep(-4722319971171766349L & 4722319970947108768L);
            }

            Thread.sleep(546322408L & 6842241166151535593L);
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }
   }
}
