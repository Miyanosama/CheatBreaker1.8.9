package io.netty.handler.ssl;

import com.cheatbreaker.client.module.type.cooldowns.CooldownRenderer;
import io.netty.channel.Channel;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import net.minecraft.client.main.IlllllIIllIllIIllIIlIIIII;
import net.minecraft.client.renderer.block.model.BlockPart$Deserializer;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;

public class SslHandler$LazyChannelPromise extends DefaultPromise<Channel> {
   public IlllllIIllIllIIllIIlIIIII __junk8794099683770250950;
   public BlockPart$Deserializer __junk4957015427945600989;
   public CooldownRenderer __junk7547327940647595143;
   public MapGenScatteredFeature __junk3941965004758313623;

   public SslHandler$LazyChannelPromise(SslHandler var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public EventExecutor executor() {
      if (SslHandler.access$300(this.this$0) == null) {
         throw new IllegalStateException();
      } else {
         return SslHandler.access$300(this.this$0).executor();
      }
   }
}
