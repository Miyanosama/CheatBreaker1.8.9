package net.minecraft.network.status.server;

import com.cheatbreaker.client.ui.module.CBPositionEnum;
import junit.swingui.TestHierarchyRunView;
import net.minecraft.item.ItemLead;
import net.minecraft.network.NetworkSystem$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.world.chunk.NibbleArray;
import net.optifine.render.CloudRenderer;
import recovered.unidentified.UnidentifiedClass3463;

public class S01PacketPong implements Packet<INetHandlerStatusClient> {
   public TestHierarchyRunView field_0003;
   public UnidentifiedClass3463 field_0006;
   public CBPositionEnum field_0002;
   public ItemLead field_0005;
   public long clientTime;
   public NetworkSystem$1 field_0001;
   public NibbleArray field_0007;
   public CloudRenderer field_0004;

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.clientTime = var1.readLong();
   }

   public S01PacketPong(long var1) {
      this.clientTime = var1;
   }

   public S01PacketPong() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeLong(this.clientTime);
   }

   public void processPacket(INetHandlerStatusClient var1) {
      var1.handlePong(this);
   }
}
