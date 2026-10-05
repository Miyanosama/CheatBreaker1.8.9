package io.netty.util.internal.chmv8;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.world.gen.ChunkProviderSettings$Serializer;

public class ConcurrentHashMapV8$Segment<K, V> extends ReentrantLock implements Serializable {
   public float loadFactor;
   public BlockStateMapper __junk2782249500564988660;
   public static long serialVersionUID;
   public ChunkProviderSettings$Serializer __junk3321970962387996892;
   public PathNavigateClimber __junk8905263304418026721;

   public ConcurrentHashMapV8$Segment(float var1) {
      this.loadFactor = var1;
   }
}
