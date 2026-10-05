package net.minecraft.nbt;

import io.netty.channel.nio.NioEventLoop;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;

public class NBTSizeTracker {
   public long read;
   public long max;
   public NioEventLoop field_0002;
   public S26PacketMapChunkBulk field_0004;
   public BlockOldLeaf field_0000;
   public static NBTSizeTracker INFINITE = new NBTSizeTracker$1(880805928L & -7066844761800965120L);

   public void read(long var1) {
      this.read += var1 / (1078480920L & 139791L);
      if (this.read > this.max) {
         throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.read + "bytes where max allowed: " + this.max);
      }
   }

   public NBTSizeTracker(long var1) {
      this.max = var1;
   }
}
