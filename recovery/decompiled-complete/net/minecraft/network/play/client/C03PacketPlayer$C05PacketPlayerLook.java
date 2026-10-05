package net.minecraft.network.play.client;

import io.netty.buffer.PooledUnsafeDirectByteBuf$1;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil$1;
import net.minecraft.world.gen.feature.WorldGenBigTree$FoliageCoordinates;

public class C03PacketPlayer$C05PacketPlayerLook extends C03PacketPlayer {
   public PacketThreadUtil$1 field_0000;
   public WorldGenBigTree$FoliageCoordinates field_0001;
   public PooledUnsafeDirectByteBuf$1 field_0002;

   public C03PacketPlayer$C05PacketPlayerLook(float var1, float var2, boolean var3) {
      this.d = var1;
      this.e = var2;
      this.f = var3;
      this.h = true;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.d = var1.readFloat();
      this.e = var1.readFloat();
      super.readPacketData(var1);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeFloat(this.d);
      var1.writeFloat(this.e);
      super.writePacketData(var1);
   }

   public C03PacketPlayer$C05PacketPlayerLook() {
      this.h = true;
   }
}
