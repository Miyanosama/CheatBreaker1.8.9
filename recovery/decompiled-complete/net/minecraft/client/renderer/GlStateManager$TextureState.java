package net.minecraft.client.renderer;

import javax.vecmath.Matrix4d;
import net.minecraft.block.BlockCauldron;
import net.minecraft.command.CommandWeather;
import net.minecraft.command.server.CommandTestForBlock;

public class GlStateManager$TextureState {
   public int textureName;
   public BlockCauldron field_0005;
   public Matrix4d field_0002;
   public CommandWeather field_0004;
   public GlStateManager$BooleanState texture2DState = new GlStateManager$BooleanState(3553);
   public CommandTestForBlock field_0001;

   public GlStateManager$TextureState() {
      this.textureName = 0;
   }
}
