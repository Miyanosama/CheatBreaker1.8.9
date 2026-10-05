package net.minecraft.block;

import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing$Axis;
import net.optifine.util.ChunkUtils;

// $VF: synthetic class
public class BlockLog$1 {
   public ChunkUtils field_0003;
   public S29PacketSoundEffect field_0000;
   public TileEntityLockable field_0002;

   static {
      try {
         field_180167_a[EnumFacing$Axis.X.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_180167_a[EnumFacing$Axis.Y.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_180167_a[EnumFacing$Axis.Z.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
