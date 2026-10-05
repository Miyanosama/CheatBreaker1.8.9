package net.minecraft.client.renderer.culling;

import io.netty.buffer.PoolChunk;
import io.netty.util.internal.chmv8.ForkJoinTask$1;
import net.minecraft.util.AxisAlignedBB;

public class Frustum implements ICamera {
   public ClippingHelper clippingHelper;
   public PoolChunk field_0005;
   public double yPosition;
   public double xPosition;
   public double zPosition;
   public ForkJoinTask$1 field_0001;

   public boolean isBoxInFrustum(double var1, double var3, double var5, double var7, double var9, double var11) {
      return this.clippingHelper
         .isBoxInFrustum(
            var1 - this.xPosition, var3 - this.yPosition, var5 - this.zPosition, var7 - this.xPosition, var9 - this.yPosition, var11 - this.zPosition
         );
   }

   public boolean isBoxInFrustumFully(double var1, double var3, double var5, double var7, double var9, double var11) {
      return this.clippingHelper
         .isBoxInFrustumFully(
            var1 - this.xPosition, var3 - this.yPosition, var5 - this.zPosition, var7 - this.xPosition, var9 - this.yPosition, var11 - this.zPosition
         );
   }

   public Frustum(ClippingHelper var1) {
      this.clippingHelper = var1;
   }

   @Override
   public boolean isBoundingBoxInFrustum(AxisAlignedBB var1) {
      return this.isBoxInFrustum(var1.a, var1.b, var1.c, var1.d, var1.e, var1.f);
   }

   public Frustum() {
      this(ClippingHelperImpl.getInstance());
   }

   @Override
   public void setPosition(double var1, double var3, double var5) {
      this.xPosition = var1;
      this.yPosition = var3;
      this.zPosition = var5;
   }
}
