package net.optifine.render;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.util.AxisAlignedBB;

public class AabbFrame extends AxisAlignedBB {
   public boolean inFrustumFully;
   public int frameCount = -1;

   public boolean isBoundingBoxInFrustumFully(ICamera var1, int var2) {
      if (this.frameCount != var2) {
         this.inFrustumFully = var1 instanceof Frustum ? ((Frustum)var1).isBoxInFrustumFully(this.a, this.b, this.c, this.d, this.e, this.f) : false;
         this.frameCount = var2;
      }

      return this.inFrustumFully;
   }

   public AabbFrame(double var1, double var3, double var5, double var7, double var9, double var11) {
      super(var1, var3, var5, var7, var9, var11);
      this.inFrustumFully = false;
   }
}
