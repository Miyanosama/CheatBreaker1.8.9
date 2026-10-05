package io.netty.handler.traffic;

import io.netty.channel.ChannelPromise;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.network.play.server.S46PacketSetCompressionLevel;
import recovered.unidentified.UnidentifiedEnum4019;

public class GlobalTrafficShapingHandler$ToSend {
   public long date;
   public Object toSend;
   public S46PacketSetCompressionLevel __junk8105812129235985647;
   public UnidentifiedEnum4019 __junk6388594243669074358;
   public ChannelPromise promise;
   public EntityAIFleeSun __junk4862788631537302363;
   public LayerSpiderEyes __junk1140177047594915764;

   public GlobalTrafficShapingHandler$ToSend(long var1, Object var3, ChannelPromise var4) {
      this.date = System.currentTimeMillis() + var1;
      this.toSend = var3;
      this.promise = var4;
   }
}
