package net.minecraft.client.renderer.block.model;

import net.minecraft.client.renderer.GlStateManager$AlphaState;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.EnumFacing$Axis;
import org.lwjgl.util.vector.Vector3f;

public class BlockPartRotation {
   public EnumFacing$Axis axis;
   public float angle;
   public RandomPositionGenerator field_0002;
   public Vector3f origin;
   public boolean rescale;
   public GlStateManager$AlphaState field_0001;

   public BlockPartRotation(Vector3f var1, EnumFacing$Axis var2, float var3, boolean var4) {
      this.origin = var1;
      this.axis = var2;
      this.angle = var3;
      this.rescale = var4;
   }
}
