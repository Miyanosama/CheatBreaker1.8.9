package recovered.unidentified;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import com.cheatbreaker.client.nethandler.obj.ServerRule;
import io.netty.handler.codec.socks.SocksAuthResponseDecoder$State;
import io.netty.handler.timeout.IdleStateHandler$WriterIdleTimeoutTask;
import io.netty.util.concurrent.DefaultPromise$1;

public class UnidentifiedClass0798 extends Packet {
   public IdleStateHandler$WriterIdleTimeoutTask field_0000;
   public ServerRule field_0001;
   public DefaultPromise$1 field_0004;
   public SocksAuthResponseDecoder$State field_0003;
   public String field_0002;

   public UnidentifiedClass0798(String var1) {
      this.field_0002 = var1;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11443(this);
   }

   public UnidentifiedClass0798() {
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.field_0002 = var1.readString();
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.field_0002);
   }

   public String method_05486() {
      return this.field_0002;
   }
}
