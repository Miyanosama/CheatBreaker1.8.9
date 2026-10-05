package net.minecraft.client;

import io.netty.channel.ThreadPerChannelEventLoop$1;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpObjectDecoder$HeaderParser;
import io.netty.util.HashedWheelTimer;
import net.minecraft.client.renderer.texture.TextureMap$3;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class Minecraft$8 implements Runnable {
   public EntityPig field_0003;
   public ThreadPerChannelEventLoop$1 field_0006;
   public TextureMap$3 field_0002;
   public HttpObjectDecoder$HeaderParser field_0005;
   public DefaultHttpContent field_0000;
   public EnumPlayerModelParts field_0007;
   public HashedWheelTimer field_0004;

   public Minecraft$8(Minecraft var1) {
      this.field_152128_a = var1;
      super();
   }

   @Override
   public void run() {
      this.field_152128_a.refreshResources();
   }
}
