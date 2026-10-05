package net.optifine.render;

import junit.swingui.TestHierarchyRunView$1;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.util.AxisAlignedBB;
import net.optifine.shaders.SVertexBuilder;
import recovered.unidentified.UnidentifiedClass4197;

public class AabbFrame extends AxisAlignedBB {
   public boolean inFrustumFully;
   public int frameCount = -1;
   public SVertexBuilder field_0002;
   public TestHierarchyRunView$1 field_0003;
   public UnidentifiedClass4197 field_0001;

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
