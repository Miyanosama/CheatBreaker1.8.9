package net.minecraft.network.status.client;

import net.minecraft.entity.passive.EntityVillager$ListEnchantedBookForEmeralds;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusServer;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$2;
import net.optifine.reflect.FieldLocatorName;
import recovered.unidentified.UnidentifiedClass1117;

public class C00PacketServerQuery implements Packet<INetHandlerStatusServer> {
   public UnidentifiedClass1117 field_0002;
   public EntityVillager$ListEnchantedBookForEmeralds field_0004;
   public FieldLocatorName field_0001;
   public EntityDamageSourceIndirect field_0003;
   public StructureStrongholdPieces$2 field_0000;

   @Override
   public void readPacketData(PacketBuffer var1) {
   }

   public void processPacket(INetHandlerStatusServer var1) {
      var1.processServerQuery(this);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
   }
}
