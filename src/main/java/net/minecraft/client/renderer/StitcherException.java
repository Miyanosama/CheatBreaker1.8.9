package net.minecraft.client.renderer;

import net.minecraft.client.renderer.texture.Stitcher;

public class StitcherException extends RuntimeException {
   public Stitcher.Holder recoveredField3227;

   public StitcherException(Stitcher.Holder var1, String var2) {
      super(var2);
      this.recoveredField3227 = var1;
   }
}
