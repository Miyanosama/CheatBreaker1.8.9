package net.minecraft.client.renderer.texture;

import io.netty.channel.socket.oio.DefaultOioServerSocketChannelConfig;
import io.netty.handler.codec.http.HttpHeaderDateFormat$1;
import java.util.concurrent.Callable;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.inventory.ContainerPlayer;

public class TextureMap$2 implements Callable<String> {
   public HttpHeaderDateFormat$1 field_0003;
   public DefaultOioServerSocketChannelConfig field_0005;
   public ContainerPlayer field_0002;
   public PackMetadataSection field_0001;

   public String call() {
      return this.val$textureatlassprite1.getIconWidth() + " x " + this.val$textureatlassprite1.getIconHeight();
   }

   public TextureMap$2(TextureMap var1, TextureAtlasSprite var2) {
      this.this$0 = var1;
      this.val$textureatlassprite1 = var2;
      super();
   }
}
