package net.minecraft.network.play.server;

import io.netty.buffer.UnreleasableByteBuf;
import io.netty.handler.codec.spdy.SpdySessionHandler;
import java.util.List;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.client.gui.GuiStreamIndicator;
import net.minecraft.command.PlayerSelector$12;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S30PacketWindowItems implements Packet<INetHandlerPlayClient> {
   public int windowId;
   public BlockEndPortal field_0005;
   public SpdySessionHandler field_0002;
   public UnreleasableByteBuf field_0004;
   public ItemStack[] itemStacks;
   public GuiStreamIndicator field_0001;
   public PlayerSelector$12 field_0006;

   public S30PacketWindowItems() {
   }

   public S30PacketWindowItems(int var1, List<ItemStack> var2) {
      this.windowId = var1;
      this.itemStacks = new ItemStack[var2.size()];

      for (int var3 = 0; var3 < this.itemStacks.length; var3++) {
         ItemStack var4 = (ItemStack)var2.get(var3);
         this.itemStacks[var3] = var4 == null ? null : var4.copy();
      }
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.windowId = var1.readUnsignedByte();
      short var2 = var1.readShort();
      this.itemStacks = new ItemStack[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         this.itemStacks[var3] = var1.readItemStackFromBuffer();
      }
   }

   public int func_148911_c() {
      return this.windowId;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleWindowItems(this);
   }

   public ItemStack[] getItemStacks() {
      return this.itemStacks;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.windowId);
      var1.writeShort(this.itemStacks.length);

      for (ItemStack var5 : this.itemStacks) {
         var1.writeItemStackToBuffer(var5);
      }
   }
}
