package recovered.unidentified;

import com.google.common.util.concurrent.FutureCallback;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.logging.InternalLogLevel;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.command.CommandSetPlayerTimeout;
import net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.client.C19PacketResourcePackStatus$Action;
import org.apache.log4j.SortedKeyEnumeration;

public class UnidentifiedClass4880 implements FutureCallback<Object> {
   public SortedKeyEnumeration field_0005;
   public EmptyArrays field_0002;
   public InternalLogLevel field_0004;
   public CommandSetPlayerTimeout field_0001;
   public C03PacketPlayer$C04PacketPlayerPosition field_0006;

   public void onSuccess(Object var1) {
      NetHandlerPlayClient.access$000(this.field_0003)
         .sendPacket(new C19PacketResourcePackStatus(this.field_0000, C19PacketResourcePackStatus$Action.SUCCESSFULLY_LOADED));
   }

   public UnidentifiedClass4880(NetHandlerPlayClient var1, String var2) {
      this.field_0003 = var1;
      this.field_0000 = var2;
      super();
   }

   public void onFailure(Throwable var1) {
      NetHandlerPlayClient.access$000(this.field_0003)
         .sendPacket(new C19PacketResourcePackStatus(this.field_0000, C19PacketResourcePackStatus$Action.FAILED_DOWNLOAD));
   }
}
