package net.minecraft.network.play.server;

import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe;
import io.netty.handler.codec.marshalling.ThreadLocalMarshallerProvider;
import java.util.EnumSet;
import java.util.Set;
import net.minecraft.block.BlockRailBase$Rail;
import org.json.CDL;

public enum S08PacketPlayerPosLook$EnumFlags {
   Z(2),
   X_ROT(4),
   Y_ROT(3),
   X(0),
   Y(1);
   public int field_180058_f;
   public AbstractNioChannel$AbstractNioUnsafe field_0008;
   public CDL field_0004;
   // $VF: synthetic field
   public static S08PacketPlayerPosLook$EnumFlags[] $VALUES = new S08PacketPlayerPosLook$EnumFlags[]{
      S08PacketPlayerPosLook$EnumFlags.X,
      S08PacketPlayerPosLook$EnumFlags.Y,
      S08PacketPlayerPosLook$EnumFlags.Z,
      S08PacketPlayerPosLook$EnumFlags.Y_ROT,
      S08PacketPlayerPosLook$EnumFlags.X_ROT
   };
   public ThreadLocalMarshallerProvider field_0002;
   public BlockRailBase$Rail field_0010;

   public int func_180055_a() {
      return 1 << this.field_180058_f;
   }

   public boolean func_180054_b(int var1) {
      return (var1 & this.func_180055_a()) == this.func_180055_a();
   }

   public S08PacketPlayerPosLook$EnumFlags(int var3) {
      this.field_180058_f = var3;
   }

   public static int func_180056_a(Set<S08PacketPlayerPosLook$EnumFlags> var0) {
      int var1 = 0;

      for (S08PacketPlayerPosLook$EnumFlags var3 : var0) {
         var1 |= var3.func_180055_a();
      }

      return var1;
   }

   public static Set<S08PacketPlayerPosLook$EnumFlags> func_180053_a(int var0) {
      EnumSet var1 = EnumSet.noneOf(S08PacketPlayerPosLook$EnumFlags.class);

      for (S08PacketPlayerPosLook$EnumFlags var5 : values()) {
         if (var5.func_180054_b(var0)) {
            var1.add(var5);
         }
      }

      return var1;
   }
}
