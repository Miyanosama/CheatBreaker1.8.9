package recovered.unidentified;

import com.google.common.util.concurrent.FutureCallback;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.client.C19PacketResourcePackStatus$Action;

public class UnidentifiedClass3777 implements FutureCallback<Object> {
   public UnidentifiedClass3777(NetHandlerPlayClient var1, String var2) {
      this.field_0000 = var1;
      this.field_0001 = var2;
      super();
   }

   public void onFailure(Throwable var1) {
      NetHandlerPlayClient.access$000(this.field_0000)
         .sendPacket(new C19PacketResourcePackStatus(this.field_0001, C19PacketResourcePackStatus$Action.FAILED_DOWNLOAD));
   }

   public void onSuccess(Object var1) {
      NetHandlerPlayClient.access$000(this.field_0000)
         .sendPacket(new C19PacketResourcePackStatus(this.field_0001, C19PacketResourcePackStatus$Action.SUCCESSFULLY_LOADED));
   }
}
