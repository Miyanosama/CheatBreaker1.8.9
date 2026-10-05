package net.minecraft.network.play.server;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;

public class S37PacketStatistics implements Packet<INetHandlerPlayClient> {
   public Map<StatBase, Integer> field_148976_a;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleStatistics(this);
   }

   public Map<StatBase, Integer> func_148974_c() {
      return this.field_148976_a;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.field_148976_a.size());

      for (Entry var3 : this.field_148976_a.entrySet()) {
         var1.writeString(((StatBase)var3.getKey()).statId);
         var1.writeVarIntToBuffer((Integer)var3.getValue());
      }
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      int var2 = var1.readVarIntFromBuffer();
      this.field_148976_a = Maps.newHashMap();

      for (int var3 = 0; var3 < var2; var3++) {
         StatBase var4 = StatList.getOneShotStat(var1.readStringFromBuffer(32767));
         int var5 = var1.readVarIntFromBuffer();
         if (var4 != null) {
            this.field_148976_a.put(var4, var5);
         }
      }
   }

   public S37PacketStatistics() {
   }

   public S37PacketStatistics(Map<StatBase, Integer> var1) {
      this.field_148976_a = var1;
   }
}
