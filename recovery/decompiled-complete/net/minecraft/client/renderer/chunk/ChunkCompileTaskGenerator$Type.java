package net.minecraft.client.renderer.chunk;

import io.netty.channel.epoll.EpollServerSocketChannel;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.entity.ai.EntityAIDoorInteract;

public enum ChunkCompileTaskGenerator$Type {
   REBUILD_CHUNK,
   RESORT_TRANSPARENCY;
   public VboChunkFactory field_0003;
   public EpollServerSocketChannel field_0005;
   public ModelHorse field_0004;
   // $VF: synthetic field
   public static ChunkCompileTaskGenerator$Type[] $VALUES = new ChunkCompileTaskGenerator$Type[]{
      REBUILD_CHUNK, ChunkCompileTaskGenerator$Type.RESORT_TRANSPARENCY
   };
   public EntityAIDoorInteract field_0001;
}
