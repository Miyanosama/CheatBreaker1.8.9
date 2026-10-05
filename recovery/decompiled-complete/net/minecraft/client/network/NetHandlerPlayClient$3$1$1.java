package net.minecraft.client.network;

import com.google.common.util.concurrent.FutureCallback;
import junit.awtui.ProgressBar;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.client.C19PacketResourcePackStatus$Action;
import net.optifine.shaders.ProgramStage;

public class NetHandlerPlayClient$3$1$1 implements FutureCallback<Object> {
   public C12PacketUpdateSign field_0002;
   public GuiDownloadTerrain field_0004;
   public ProgressBar field_0003;
   public ProgramStage field_0000;

   public void onFailure(Throwable var1) {
      NetHandlerPlayClient.access$000(this.field_178886_a.field_175395_a.field_178899_c)
         .sendPacket(new C19PacketResourcePackStatus(this.field_178886_a.field_175395_a.field_178900_a, C19PacketResourcePackStatus$Action.FAILED_DOWNLOAD));
   }

   public void onSuccess(Object var1) {
      NetHandlerPlayClient.access$000(this.field_178886_a.field_175395_a.field_178899_c)
         .sendPacket(new C19PacketResourcePackStatus(this.field_178886_a.field_175395_a.field_178900_a, C19PacketResourcePackStatus$Action.SUCCESSFULLY_LOADED));
   }

   public NetHandlerPlayClient$3$1$1(NetHandlerPlayClient$3$1 var1) {
      this.field_178886_a = var1;
      super();
   }
}
