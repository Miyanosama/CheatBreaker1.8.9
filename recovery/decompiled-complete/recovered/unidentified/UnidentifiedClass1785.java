package recovered.unidentified;

import io.netty.handler.codec.http.HttpClientCodec$Decoder;
import io.netty.handler.traffic.GlobalTrafficShapingHandler$ToSend;
import java.io.IOException;
import net.minecraft.util.ChatComponentScore;
import org.newsclub.net.unix.AFUNIXServerSocket;
import org.newsclub.net.unix.NativeUnixSocket;

public class UnidentifiedClass1785 extends Thread {
   public GlobalTrafficShapingHandler$ToSend field_0001;
   public ChatComponentScore field_0003;
   public HttpClientCodec$Decoder field_0002;

   @Override
   public void run() {
      try {
         if (AFUNIXServerSocket.method_05705(this.field_0000) != null) {
            NativeUnixSocket.method_25798(AFUNIXServerSocket.method_05705(this.field_0000).method_01607());
         }
      } catch (IOException var2) {
      }
   }

   public UnidentifiedClass1785(AFUNIXServerSocket var1) {
      this.field_0000 = var1;
      super();
   }
}
