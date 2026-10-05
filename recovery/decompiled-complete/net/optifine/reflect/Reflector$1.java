package net.optifine.reflect;

import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class Reflector$1 implements IResolvable {
   public ItemCameraTransforms field_0002;
   public MusicTicker field_0000;

   @Override
   public void resolve() {
      Reflector.access$000().info("[OptiFine] " + this.val$msg);
   }

   public Reflector$1(String var1) {
      this.val$msg = var1;
      super();
   }
}
