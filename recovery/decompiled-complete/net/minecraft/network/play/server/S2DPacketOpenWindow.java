package net.minecraft.network.play.server;

import com.cheatbreaker.client.websocket.server.WSPacketFriendStatusUpdate;
import io.netty.util.ThreadDeathWatcher$Entry;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.biome.BiomeColorHelper$3;
import net.optifine.shaders.config.ShaderLine;

public class S2DPacketOpenWindow implements Packet<INetHandlerPlayClient> {
   public IChatComponent windowTitle;
   public ModelChicken field_0008;
   public WSPacketFriendStatusUpdate field_0004;
   public int windowId;
   public BiomeColorHelper$3 field_0001;
   public ChunkProviderClient field_0002;
   public ThreadDeathWatcher$Entry field_0009;
   public int slotCount;
   public String inventoryType;
   public ShaderLine field_0010;
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
   public void readPacketData(PacketBuffer var1) {
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
   public void writePacketData(PacketBuffer var1) {
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
