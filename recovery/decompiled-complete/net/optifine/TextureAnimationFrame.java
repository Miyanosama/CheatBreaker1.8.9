package net.optifine;

import io.netty.handler.ssl.SslHandler$LazyChannelPromise;
import junit.framework.Assert;
import net.minecraft.util.ObjectIntIdentityMap;
import net.optifine.shaders.config.ShaderPackParser;
import recovered.unidentified.UnidentifiedClass0611;

public class TextureAnimationFrame {
   public ShaderPackParser field_0003;
   public UnidentifiedClass0611 field_0006;
   public SslHandler$LazyChannelPromise field_0002;
   public ObjectIntIdentityMap field_0005;
   public int counter;
   public Assert field_0001;
   public int duration;
   public int index;

   public TextureAnimationFrame(int var1, int var2) {
      this.index = var1;
      this.duration = var2;
      this.counter = 0;
   }
}
