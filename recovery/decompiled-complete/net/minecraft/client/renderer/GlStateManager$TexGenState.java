package net.minecraft.client.renderer;

import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$1;
import net.minecraft.command.CommandEntityData;
import net.optifine.shaders.uniform.ShaderUniform4f;
import recovered.unidentified.UnidentifiedClass1118;

public class GlStateManager$TexGenState {
   public GlStateManager$TexGenCoord s = new GlStateManager$TexGenCoord(8192, 3168);
   public GlStateManager$TexGenCoord r;
   public ShaderUniform4f field_0002;
   public GlStateManager$TexGenCoord t = new GlStateManager$TexGenCoord(8193, 3169);
   public HttpPostRequestDecoder$1 field_0000;
   public GlStateManager$TexGenCoord q;
   public CommandEntityData field_0007;
   public UnidentifiedClass1118 field_0004;

   public GlStateManager$TexGenState() {
      this.r = new GlStateManager$TexGenCoord(8194, 3170);
      this.q = new GlStateManager$TexGenCoord(8195, 3171);
   }
}
