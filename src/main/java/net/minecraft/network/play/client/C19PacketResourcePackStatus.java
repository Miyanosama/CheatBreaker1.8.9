package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C19PacketResourcePackStatus implements Packet<INetHandlerPlayServer> {
   public C19PacketResourcePackStatus.Action status;
   public String hash;

   public void processPacket(INetHandlerPlayServer var1) {
      var1.handleResourcePackStatus(this);
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeString(this.hash);
      var1.writeEnumValue(this.status);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.hash = var1.readStringFromBuffer(40);
      this.status = var1.readEnumValue(C19PacketResourcePackStatus.Action.class);
   }

   public C19PacketResourcePackStatus(String var1, C19PacketResourcePackStatus.Action var2) {
      if (var1.length() > 40) {
         var1 = var1.substring(0, 40);
      }

      this.hash = var1;
      this.status = var2;
   }

   public C19PacketResourcePackStatus() {
   }

   public static enum Action {
      SUCCESSFULLY_LOADED,
      DECLINED,
      FAILED_DOWNLOAD,
      ACCEPTED;
      // $VF: synthetic field
      public static C19PacketResourcePackStatus.Action[] $VALUES = new C19PacketResourcePackStatus.Action[]{
         SUCCESSFULLY_LOADED, DECLINED, FAILED_DOWNLOAD, C19PacketResourcePackStatus.Action.ACCEPTED
      };
   }
}
