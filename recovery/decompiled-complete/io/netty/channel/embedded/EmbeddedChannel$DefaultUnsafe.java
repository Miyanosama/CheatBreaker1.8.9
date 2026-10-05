package io.netty.channel.embedded;

import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.ChannelPromise;
import java.net.SocketAddress;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.pattern.BlockPattern$CacheLoader;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.world.gen.structure.StructureVillagePieces$Road;

public class EmbeddedChannel$DefaultUnsafe extends AbstractChannel$AbstractUnsafe {
   public PositionedSoundRecord __junk9090227046818958745;
   public BlockPattern$CacheLoader __junk413701079522108116;
   public StructureVillagePieces$Road __junk3149874388174774514;
   public BlockSlab __junk8126494360025870579;

   @Override
   public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      this.safeSetSuccess(var3);
   }

   public EmbeddedChannel$DefaultUnsafe(EmbeddedChannel var1) {
      this.this$0 = var1;
      super(var1);
   }
}
