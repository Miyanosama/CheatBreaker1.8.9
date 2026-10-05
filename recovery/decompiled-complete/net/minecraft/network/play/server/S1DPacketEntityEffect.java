package net.minecraft.network.play.server;

import com.cheatbreaker.client.util.server.ServerRestrictionAction;
import io.netty.handler.codec.TooLongFrameException;
import net.minecraft.entity.Entity$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;
import net.optifine.expr.ExpressionFloatCached;
import net.optifine.shaders.SVertexAttrib;

public class S1DPacketEntityEffect implements Packet<INetHandlerPlayClient> {
   public int entityId;
   public byte amplifier;
   public byte effectId;
   public TooLongFrameException field_0007;
   public int duration;
   public SVertexAttrib field_0002;
   public ExpressionFloatCached field_0009;
   public S00PacketDisconnect field_0006;
   public byte hideParticles;
   public ServerRestrictionAction field_0010;
   public Entity$1 field_0000;

   public int getEntityId() {
      return this.entityId;
   }

   public byte getAmplifier() {
      return this.amplifier;
   }

   public int getDuration() {
      return this.duration;
   }

   public S1DPacketEntityEffect() {
   }

   public S1DPacketEntityEffect(int var1, PotionEffect var2) {
      this.entityId = var1;
      this.effectId = (byte)(var2.getPotionID() & 0xFF);
      this.amplifier = (byte)(var2.getAmplifier() & 0xFF);
      if (var2.getDuration() > 32767) {
         this.duration = 32767;
      } else {
         this.duration = var2.getDuration();
      }

      this.hideParticles = (byte)(var2.getIsShowParticles() ? 1 : 0);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readVarIntFromBuffer();
      this.effectId = var1.readByte();
      this.amplifier = var1.readByte();
      this.duration = var1.readVarIntFromBuffer();
      this.hideParticles = var1.readByte();
   }

   public byte getEffectId() {
      return this.effectId;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityEffect(this);
   }

   public boolean func_179707_f() {
      return this.hideParticles != 0;
   }

   public boolean func_149429_c() {
      return this.duration == 32767;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeByte(this.effectId);
      var1.writeByte(this.amplifier);
      var1.writeVarIntToBuffer(this.duration);
      var1.writeByte(this.hideParticles);
   }
}
