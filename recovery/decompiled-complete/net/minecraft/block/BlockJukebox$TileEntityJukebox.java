package net.minecraft.block;

import io.netty.handler.codec.socks.SocksSubnegotiationVersion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MessageDeserializer;
import org.java_websocket.handshake.HandshakeImpl1Client;

public class BlockJukebox$TileEntityJukebox extends TileEntity {
   public ItemStack record;
   public SocksSubnegotiationVersion field_0003;
   public MessageDeserializer field_0000;
   public HandshakeImpl1Client field_0002;

   public void setRecord(ItemStack var1) {
      this.record = var1;
      this.markDirty();
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      if (var1.hasKey("RecordItem", 10)) {
         this.setRecord(ItemStack.loadItemStackFromNBT(var1.getCompoundTag("RecordItem")));
      } else if (var1.getInteger("Record") > 0) {
         this.setRecord(new ItemStack(Item.getItemById(var1.getInteger("Record")), 1, 0));
      }
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      if (this.getRecord() != null) {
         var1.setTag("RecordItem", this.getRecord().writeToNBT(new NBTTagCompound()));
      }
   }

   public ItemStack getRecord() {
      return this.record;
   }
}
