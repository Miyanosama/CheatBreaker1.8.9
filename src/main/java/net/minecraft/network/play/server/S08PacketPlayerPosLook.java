package net.minecraft.network.play.server;

import java.util.EnumSet;
import java.util.Set;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S08PacketPlayerPosLook implements Packet<INetHandlerPlayClient> {
   public double z;
   public double y;
   public float pitch;
   public float yaw;
   public Set<S08PacketPlayerPosLook.EnumFlags> field_179835_f;
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

   public S08PacketPlayerPosLook(double var1, double var3, double var5, float var7, float var8, Set<S08PacketPlayerPosLook.EnumFlags> var9) {
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
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeDouble(this.x);
      var1.writeDouble(this.y);
      var1.writeDouble(this.z);
      var1.writeFloat(this.yaw);
      var1.writeFloat(this.pitch);
      var1.writeByte(S08PacketPlayerPosLook.EnumFlags.func_180056_a(this.field_179835_f));
   }

   public Set<S08PacketPlayerPosLook.EnumFlags> func_179834_f() {
      return this.field_179835_f;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.x = var1.readDouble();
      this.y = var1.readDouble();
      this.z = var1.readDouble();
      this.yaw = var1.readFloat();
      this.pitch = var1.readFloat();
      this.field_179835_f = S08PacketPlayerPosLook.EnumFlags.func_180053_a(var1.readUnsignedByte());
   }

   public double getZ() {
      return this.z;
   }

   public static enum EnumFlags {
      X(0),
      Y(1),
      Z(2),
      Y_ROT(3),
      X_ROT(4);
      public int field_180058_f;
      // $VF: synthetic field
      public static S08PacketPlayerPosLook.EnumFlags[] $VALUES = new S08PacketPlayerPosLook.EnumFlags[]{
         S08PacketPlayerPosLook.EnumFlags.X,
         S08PacketPlayerPosLook.EnumFlags.Y,
         S08PacketPlayerPosLook.EnumFlags.Z,
         S08PacketPlayerPosLook.EnumFlags.Y_ROT,
         S08PacketPlayerPosLook.EnumFlags.X_ROT
      };

      public int func_180055_a() {
         return 1 << this.field_180058_f;
      }

      public boolean func_180054_b(int var1) {
         return (var1 & this.func_180055_a()) == this.func_180055_a();
      }

      EnumFlags(int var3) {
         this.field_180058_f = var3;
      }

      public static int func_180056_a(Set<S08PacketPlayerPosLook.EnumFlags> var0) {
         int var1 = 0;

         for (S08PacketPlayerPosLook.EnumFlags var3 : var0) {
            var1 |= var3.func_180055_a();
         }

         return var1;
      }

      public static Set<S08PacketPlayerPosLook.EnumFlags> func_180053_a(int var0) {
         EnumSet var1 = EnumSet.noneOf(S08PacketPlayerPosLook.EnumFlags.class);

         for (S08PacketPlayerPosLook.EnumFlags var5 : values()) {
            if (var5.func_180054_b(var0)) {
               var1.add(var5);
            }
         }

         return var1;
      }
   }
}
