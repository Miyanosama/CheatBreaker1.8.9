package net.minecraft.network.play.server;

import com.cheatbreaker.client.util.LegacyClientCrashReporter;
import net.minecraft.client.renderer.texture.TextureAtlasSprite$1;
import net.minecraft.entity.Entity;
import net.minecraft.init.Bootstrap$16;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass1954;

public class S19PacketEntityStatus implements Packet<INetHandlerPlayClient> {
   public byte logicOpcode;
   public S31PacketWindowProperty field_0005;
   public int entityId;
   public Bootstrap$16 field_0004;
   public UnidentifiedClass1954 field_0000;
   public TextureAtlasSprite$1 field_0001;
   public LegacyClientCrashReporter field_0006;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.entityId);
      var1.writeByte(this.logicOpcode);
   }

   public byte getOpCode() {
      return this.logicOpcode;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityStatus(this);
   }

   public S19PacketEntityStatus(Entity var1, byte var2) {
      this.entityId = var1.F();
      this.logicOpcode = var2;
   }

   public S19PacketEntityStatus() {
   }

   public Entity getEntity(World var1) {
      return var1.getEntityByID(this.entityId);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readInt();
      this.logicOpcode = var1.readByte();
   }
}
