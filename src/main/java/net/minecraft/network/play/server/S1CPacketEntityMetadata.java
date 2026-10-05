package net.minecraft.network.play.server;

import java.util.List;
import net.minecraft.entity.DataWatcher;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1CPacketEntityMetadata implements Packet<INetHandlerPlayClient> {
   public int entityId;
   public List<DataWatcher.WatchableObject> field_149378_b;

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityId);
      DataWatcher.writeWatchedListToPacketBuffer(this.field_149378_b, var1);
   }

   public S1CPacketEntityMetadata() {
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityMetadata(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityId = var1.readVarIntFromBuffer();
      this.field_149378_b = DataWatcher.readWatchedListFromPacketBuffer(var1);
   }

   public List<DataWatcher.WatchableObject> func_149376_c() {
      return this.field_149378_b;
   }

   public S1CPacketEntityMetadata(int var1, DataWatcher var2, boolean var3) {
      this.entityId = var1;
      if (var3) {
         this.field_149378_b = var2.getAllWatched();
      } else {
         this.field_149378_b = var2.getChanged();
      }
   }
}
