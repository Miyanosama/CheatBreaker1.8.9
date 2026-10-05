package recovered.unidentified;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil$TransferEncodingMechanism;

public class UnidentifiedClass3253 extends Packet {
   public String field_0000;
   public HttpPostBodyUtil$TransferEncodingMechanism field_0001;

   public String method_20224() {
      return this.field_0000;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.field_0000 = var1.readString();
   }

   public UnidentifiedClass3253(String var1) {
      this.field_0000 = var1;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.field_0000);
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11456(this);
   }

   public UnidentifiedClass3253() {
   }
}
