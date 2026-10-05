package net.minecraft.network;

import io.netty.handler.codec.http.websocketx.WebSocket07FrameEncoder;
import net.minecraft.client.renderer.chunk.ListedRenderChunk;
import net.minecraft.entity.ai.EntityAISwimming;

public class PacketThreadUtil$1 implements Runnable {
   public ListedRenderChunk field_0002;
   public EntityAISwimming field_0004;
   public WebSocket07FrameEncoder field_0001;

   public PacketThreadUtil$1(Packet var1, INetHandler var2) {
      this.val$packetIn = var1;
      this.val$processor = var2;
      super();
   }

   @Override
   public void run() {
      PacketThreadUtil.clientPreProcessPacket(this.val$packetIn);
      this.val$packetIn.processPacket(this.val$processor);
   }
}
