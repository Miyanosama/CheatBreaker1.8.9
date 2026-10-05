package net.minecraft.network.play.server;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachKeyTask;
import java.util.Set;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import org.apache.log4j.net.DefaultEvaluator;

public class S08PacketPlayerPosLook implements Packet<INetHandlerPlayClient> {
   public double z;
   public double y;
   public float pitch;
   public DefaultEvaluator field_0006;
   public WorldGenTaiga2 field_0000;
   public float yaw;
   public Set<S08PacketPlayerPosLook$EnumFlags> field_179835_f;
   public ConcurrentHashMapV8$ForEachKeyTask field_0005;
   public S47PacketPlayerListHeaderFooter field_0002;
   public double x;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handlePlayerPosLook(this);
   }

   public float getPitch() {
      return this.pitch;
   }

   public S08PacketPlayerPosLook() {
   }

   public float getYaw() {
      return this.yaw;
   }

   public double getX() {
      return this.x;
   }

   public S08PacketPlayerPosLook(double var1, double var3, double var5, float var7, float var8, Set<S08PacketPlayerPosLook$EnumFlags> var9) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
      this.yaw = var7;
      this.pitch = var8;
      this.field_179835_f = var9;
   }

   public double getY() {
      return this.y;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeDouble(this.x);
      var1.writeDouble(this.y);
      var1.writeDouble(this.z);
      var1.writeFloat(this.yaw);
      var1.writeFloat(this.pitch);
      var1.writeByte(S08PacketPlayerPosLook$EnumFlags.func_180056_a(this.field_179835_f));
   }

   public Set<S08PacketPlayerPosLook$EnumFlags> func_179834_f() {
      return this.field_179835_f;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.x = var1.readDouble();
      this.y = var1.readDouble();
      this.z = var1.readDouble();
      this.yaw = var1.readFloat();
      this.pitch = var1.readFloat();
      this.field_179835_f = S08PacketPlayerPosLook$EnumFlags.func_180053_a(var1.readUnsignedByte());
   }

   public double getZ() {
      return this.z;
   }
}
