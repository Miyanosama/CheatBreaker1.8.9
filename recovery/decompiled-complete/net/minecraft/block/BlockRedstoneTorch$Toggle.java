package net.minecraft.block;

import io.netty.channel.ChannelFlushPromiseNotifier$DefaultFlushCheckpoint;
import io.netty.util.internal.RecyclableArrayList$1;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.network.NettyEncryptingDecoder;
import net.minecraft.util.BlockPos;
import recovered.unidentified.UnidentifiedClass3389;

public class BlockRedstoneTorch$Toggle {
   public ChannelFlushPromiseNotifier$DefaultFlushCheckpoint field_0003;
   public BlockPos pos;
   public ShaderGroup field_0002;
   public RecyclableArrayList$1 field_0004;
   public long time;
   public NettyEncryptingDecoder field_0001;
   public UnidentifiedClass3389 field_0006;

   public BlockRedstoneTorch$Toggle(BlockPos var1, long var2) {
      this.pos = var1;
      this.time = var2;
   }
}
