package recovered.unidentified;

import com.cheatbreaker.client.event.EventBus$Event;
import io.netty.handler.codec.http.DefaultHttpHeaders$1;
import io.netty.handler.codec.socks.SocksAuthRequestDecoder$State;
import net.minecraft.client.network.LanServerDetector$LanServerList;
import net.minecraft.client.stream.ChatController$EnumEmoticonMode;
import org.apache.log4j.helpers.PatternParser;

public class UnidentifiedClass0026 extends EventBus$Event {
   public ChatController$EnumEmoticonMode field_0004;
   public PatternParser field_0003;
   public DefaultHttpHeaders$1 field_0001;
   public SocksAuthRequestDecoder$State field_0005;
   public LanServerDetector$LanServerList field_0000;
   public String field_0002;

   public String method_00228() {
      return this.field_0002;
   }

   public UnidentifiedClass0026(String var1) {
      this.field_0002 = var1;
   }
}
