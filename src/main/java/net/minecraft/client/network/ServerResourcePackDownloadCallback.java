package net.minecraft.client.network;

import com.google.common.util.concurrent.FutureCallback;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;

public class ServerResourcePackDownloadCallback implements FutureCallback<Object> {
   public NetHandlerPlayClient recoveredField38;
   public String recoveredField39;

   public ServerResourcePackDownloadCallback(NetHandlerPlayClient var1, String var2) {
      this.recoveredField38 = var1;
      this.recoveredField39 = var2;
   }

   @Override
   public void onFailure(Throwable var1) {
      this.recoveredField38.netManager
         .sendPacket(new C19PacketResourcePackStatus(this.recoveredField39, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
   }

   @Override
   public void onSuccess(Object var1) {
      this.recoveredField38.netManager
         .sendPacket(new C19PacketResourcePackStatus(this.recoveredField39, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
   }
}
