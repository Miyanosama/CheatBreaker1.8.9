package net.minecraft.network.play.client;

import io.netty.channel.epoll.IovArray$1;
import io.netty.handler.codec.http.multipart.AbstractDiskHttpData;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.WeightedRandomFishable;
import net.optifine.shaders.MultiTexID;
import net.optifine.util.ResUtils;
import recovered.unidentified.UnidentifiedClass4788;

public class C09PacketHeldItemChange implements Packet<INetHandlerPlayServer> {
   public AbstractDiskHttpData field_0003;
   public IovArray$1 field_0005;
   public UnidentifiedClass4788 field_0002;
   public ResUtils field_0004;
   public int slotId;
   public MultiTexID field_0001;
   public WeightedRandomFishable field_0006;

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processHeldItemChange(this);
   }

   public int getSlotId() {
      return this.slotId;
   }

   public C09PacketHeldItemChange(int var1) {
      this.slotId = var1;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.slotId = var1.readShort();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeShort(this.slotId);
   }

   public C09PacketHeldItemChange() {
   }
}
