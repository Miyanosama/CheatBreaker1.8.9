package net.minecraft.client.network;

import com.google.common.util.concurrent.FutureCallback;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;

public class LocalResourcePackLoadCallback implements FutureCallback<Object> {
   public NetHandlerPlayClient recoveredField903;
   public String recoveredField904;

   @Override
   public void onSuccess(Object var1) {
      this.recoveredField903.netManager
         .sendPacket(new C19PacketResourcePackStatus(this.recoveredField904, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
   }

   public LocalResourcePackLoadCallback(NetHandlerPlayClient var1, String var2) {
      this.recoveredField903 = var1;
      this.recoveredField904 = var2;
   }

   @Override
   public void onFailure(Throwable var1) {
      this.recoveredField903.netManager
         .sendPacket(new C19PacketResourcePackStatus(this.recoveredField904, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
   }
}
