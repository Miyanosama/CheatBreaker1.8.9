package net.minecraft.network.play.server;

import com.cheatbreaker.client.ui.module.SomeRandomAssEnum;
import io.netty.buffer.DefaultByteBufHolder;
import net.minecraft.block.BlockPressurePlate$Sensitivity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.world.World;

public class S49PacketUpdateEntityNBT implements Packet<INetHandlerPlayClient> {
   public SomeRandomAssEnum field_0003;
   public BlockPressurePlate$Sensitivity field_0005;
   public ScorePlayerTeam field_0002;
   public NBTTagCompound tagCompound;
   public DefaultByteBufHolder field_0000;
   public int entityId;

   public NBTTagCompound getTagCompound() {
      return this.tagCompound;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityNBT(this);
   }

   public S49PacketUpdateEntityNBT(int var1, NBTTagCompound var2) {
      this.entityId = var1;
      this.tagCompound = var2;
   }

   public S49PacketUpdateEntityNBT() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeNBTTagCompoundToBuffer(this.tagCompound);
   }

   public Entity getEntity(World var1) {
      return var1.getEntityByID(this.entityId);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readVarIntFromBuffer();
      this.tagCompound = var1.readNBTTagCompoundFromBuffer();
   }
}
