package io.netty.channel.sctp.oio;

import io.netty.channel.ChannelPromise;
import java.net.InetAddress;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.chunk.ChunkRenderWorker;
import net.minecraft.nbt.JsonToNBT$Compound;
import net.optifine.expr.ExpressionParser;

public class OioSctpServerChannel$1 implements Runnable {
   public ExpressionParser __junk4888256972533513444;
   public JsonToNBT$Compound __junk5945885424538697124;
   public ChunkRenderWorker __junk5307646058978698688;
   public BlockCrops __junk966733083631802759;

   @Override
   public void run() {
      this.this$0.bindAddress(this.val$localAddress, this.val$promise);
   }

   public OioSctpServerChannel$1(OioSctpServerChannel var1, InetAddress var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$localAddress = var2;
      this.val$promise = var3;
      super();
   }
}
