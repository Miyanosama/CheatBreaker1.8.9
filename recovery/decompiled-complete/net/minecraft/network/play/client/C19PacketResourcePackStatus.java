package net.minecraft.network.play.client;

import io.netty.handler.traffic.TrafficCounter;
import javazoom.jl.decoder.Equalizer$EQFunction;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.entity.layers.LayerHeldItemWitch;
import net.minecraft.client.resources.FallbackResourceManager$InputStreamLeakedResourceLogger;
import net.minecraft.entity.ai.EntityAIFindEntityNearest$1;
import net.minecraft.inventory.ContainerEnchantment$3;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import recovered.unidentified.UnidentifiedClass0433;
import recovered.unidentified.UnidentifiedClass4110;

public class C19PacketResourcePackStatus implements Packet<INetHandlerPlayServer> {
   public TrafficCounter field_0005;
   public UnidentifiedClass0433 field_0008;
   public UnidentifiedClass4110 field_0004;
   public C19PacketResourcePackStatus$Action status;
   public FallbackResourceManager$InputStreamLeakedResourceLogger field_0001;
   public Equalizer$EQFunction field_0002;
   public EntityAIFindEntityNearest$1 field_0009;
   public LayerHeldItemWitch field_0006;
   public String hash;
   public ContainerEnchantment$3 field_0010;
   public GuiSelectWorld field_0000;

   public void processPacket(INetHandlerPlayServer var1) {
      var1.handleResourcePackStatus(this);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(this.hash);
      var1.writeEnumValue(this.status);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.hash = var1.readStringFromBuffer(40);
      this.status = var1.readEnumValue(C19PacketResourcePackStatus$Action.class);
   }

   public C19PacketResourcePackStatus(String var1, C19PacketResourcePackStatus$Action var2) {
      if (var1.length() > 40) {
         var1 = var1.substring(0, 40);
      }

      this.hash = var1;
      this.status = var2;
   }

   public C19PacketResourcePackStatus() {
   }
}
