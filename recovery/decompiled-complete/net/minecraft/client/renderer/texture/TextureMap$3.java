package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import net.minecraft.world.gen.MapGenCavesHell;

public class TextureMap$3 implements Callable<String> {
   public MapGenCavesHell field_0000;

   public String call() {
      return this.val$textureatlassprite1.getFrameCount() + " frames";
   }

   public TextureMap$3(TextureMap var1, TextureAtlasSprite var2) {
      this.this$0 = var1;
      this.val$textureatlassprite1 = var2;
      super();
   }
}
