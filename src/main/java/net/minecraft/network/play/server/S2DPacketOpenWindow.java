package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S2DPacketOpenWindow implements Packet<INetHandlerPlayClient> {
   public IChatComponent windowTitle;
   public int windowId;
   public int slotCount;
   public String inventoryType;
   public int entityId;

   public int getSlotCount() {
      return this.slotCount;
   }

   public S2DPacketOpenWindow(int var1, String var2, IChatComponent var3) {
      this(var1, var2, var3, 0);
   }

   public S2DPacketOpenWindow(int var1, String var2, IChatComponent var3, int var4, int var5) {
      this(var1, var2, var3, var4);
      this.entityId = var5;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.windowId = var1.readUnsignedByte();
      this.inventoryType = var1.readStringFromBuffer(32);
      this.windowTitle = var1.readChatComponent();
      this.slotCount = var1.readUnsignedByte();
      if (this.inventoryType.equals("EntityHorse")) {
         this.entityId = var1.readInt();
      }
   }

   public boolean hasSlots() {
      return this.slotCount > 0;
   }

   public IChatComponent getWindowTitle() {
      return this.windowTitle;
   }

   public S2DPacketOpenWindow() {
   }

   public String getGuiId() {
      return this.inventoryType;
   }

   public S2DPacketOpenWindow(int var1, String var2, IChatComponent var3, int var4) {
      this.windowId = var1;
      this.inventoryType = var2;
      this.windowTitle = var3;
      this.slotCount = var4;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleOpenWindow(this);
   }

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.windowId);
      var1.writeString(this.inventoryType);
      var1.writeChatComponent(this.windowTitle);
      var1.writeByte(this.slotCount);
      if (this.inventoryType.equals("EntityHorse")) {
         var1.writeInt(this.entityId);
      }
   }

   public int getWindowId() {
      return this.windowId;
   }
}
