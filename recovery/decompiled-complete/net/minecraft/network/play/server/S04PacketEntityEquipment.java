package net.minecraft.network.play.server;

import io.netty.handler.codec.compression.JdkZlibEncoder$3;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.resources.SkinManager$3$1;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S04PacketEntityEquipment implements Packet<INetHandlerPlayClient> {
   public SkinManager$3$1 field_0003;
   public int entityID;
   public int equipmentSlot;
   public JdkZlibEncoder$3 field_0004;
   public GuiCreateFlatWorld field_0000;
   public ItemStack itemStack;

   public int getEquipmentSlot() {
      return this.equipmentSlot;
   }

   public int getEntityID() {
      return this.entityID;
   }

   public S04PacketEntityEquipment(int var1, int var2, ItemStack var3) {
      this.entityID = var1;
      this.equipmentSlot = var2;
      this.itemStack = var3 == null ? null : var3.copy();
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityID = var1.readVarIntFromBuffer();
      this.equipmentSlot = var1.readShort();
      this.itemStack = var1.readItemStackFromBuffer();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityID);
      var1.writeShort(this.equipmentSlot);
      var1.writeItemStackToBuffer(this.itemStack);
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public S04PacketEntityEquipment() {
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityEquipment(this);
   }
}
