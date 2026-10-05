package net.minecraft.realms;

import com.jagrosh.discordipc.entities.Packet$OpCode;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.optifine.gui.GuiOptionButtonOF;

public class RealmsVertexFormatElement {
   public C12PacketUpdateSign field_0001;
   public VertexFormatElement v;
   public Packet$OpCode field_0000;
   public GuiOptionButtonOF field_0002;

   public int method_00513() {
      return this.v.getSize();
   }

   @Override
   public int hashCode() {
      return this.v.hashCode();
   }

   public RealmsVertexFormatElement(VertexFormatElement var1) {
      this.v = var1;
   }

   @Override
   public boolean equals(Object var1) {
      return this.v.equals(var1);
   }

   public int method_00515() {
      return this.v.getElementCount();
   }

   public boolean isPosition() {
      return this.v.isPositionElement();
   }

   @Override
   public String toString() {
      return this.v.toString();
   }

   public VertexFormatElement getVertexFormatElement() {
      return this.v;
   }

   public int method_00509() {
      return this.v.getIndex();
   }
}
