package net.minecraft.world.chunk.storage;

import io.netty.channel.rxtx.RxtxChannel$RxtxUnsafe$1;
import java.io.ByteArrayOutputStream;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.network.play.server.S3APacketTabComplete;

public class RegionFile$ChunkBuffer extends ByteArrayOutputStream {
   public ItemFishingRod field_0003;
   public RxtxChannel$RxtxUnsafe$1 field_0005;
   public S3APacketTabComplete field_0002;
   public int chunkZ;
   public int chunkX;

   public RegionFile$ChunkBuffer(RegionFile var1, int var2, int var3) {
      this.field_76724_a = var1;
      super(8096);
      this.chunkX = var2;
      this.chunkZ = var3;
   }

   @Override
   public void close() {
      this.field_76724_a.write(this.chunkX, this.chunkZ, this.buf, this.count);
   }
}
