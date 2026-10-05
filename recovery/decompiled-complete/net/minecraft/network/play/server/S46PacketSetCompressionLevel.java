package net.minecraft.network.play.server;

import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.client.resources.model.WeightedBakedModel$Builder;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.optifine.entity.model.anim.ModelVariableType;

public class S46PacketSetCompressionLevel implements Packet<INetHandlerPlayClient> {
   public ModelVariableType field_0001;
   public WeightedBakedModel$Builder field_0003;
   public int threshold;
   public RenderPig field_0002;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.threshold);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.threshold = var1.readVarIntFromBuffer();
   }

   public int getThreshold() {
      return this.threshold;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSetCompressionLevel(this);
   }
}
