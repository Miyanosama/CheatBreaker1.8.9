package net.minecraft.client.resources.model;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.ITransformation;
import net.minecraftforge.client.model.TRSRTransformation;
import net.optifine.reflect.Reflector;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import net.minecraftforge.client.model.IModelPart;

public enum ModelRotation implements ITransformation, IModelState {
      X0_Y0(0, 0),
      X0_Y90(0, 90),
      X0_Y180(0, 180),
      X0_Y270(0, 270),
      X90_Y0(90, 0),
      X90_Y90(90, 90),
      X90_Y180(90, 180),
      X90_Y270(90, 270),
      X180_Y0(180, 0),
      X180_Y90(180, 90),
      X180_Y180(180, 180),
      X180_Y270(180, 270),
      X270_Y0(270, 0),
      X270_Y90(270, 90),
      X270_Y180(270, 180),
      X270_Y270(270, 270);
   public int quartersX;
   public static ModelRotation[] $VALUES = new ModelRotation[]{
      X0_Y0,
      X0_Y90,
      X0_Y180,
      X0_Y270,
      X90_Y0,
      X90_Y90,
      X90_Y180,
      ModelRotation.X90_Y270,
      X180_Y0,
      X180_Y90,
      X180_Y180,
      X180_Y270,
      ModelRotation.X270_Y0,
      ModelRotation.X270_Y90,
      X270_Y180,
      ModelRotation.X270_Y270
   };
   public Matrix4f matrix4d;
   public int combinedXY;
   public int quartersY;
   public static Map<Integer, ModelRotation> mapRotations = Maps.newHashMap();

   public Matrix4f getMatrix4d() {
      return this.matrix4d;
   }

   @Override
   public EnumFacing rotate(EnumFacing var1) {
      return this.rotateFace(var1);
   }

   @Override
   public javax.vecmath.Matrix4f getMatrix() {
      return Reflector.ForgeHooksClient_getMatrix.exists()
         ? (javax.vecmath.Matrix4f)Reflector.call(Reflector.ForgeHooksClient_getMatrix, this)
         : new javax.vecmath.Matrix4f();
   }

   public int rotateVertex(EnumFacing var1, int var2) {
      int var3 = var2;
      if (var1.getAxis() == EnumFacing.Axis.X) {
         var3 = (var2 + this.quartersX) % 4;
      }

      EnumFacing var4 = var1;

      for (int var5 = 0; var5 < this.quartersX; var5++) {
         var4 = var4.rotateAround(EnumFacing.Axis.X);
      }

      if (var4.getAxis() == EnumFacing.Axis.Y) {
         var3 = (var3 + this.quartersY) % 4;
      }

      return var3;
   }

   public static ModelRotation getModelRotation(int var0, int var1) {
      return mapRotations.get(combineXY(MathHelper.normalizeAngle(var0, 360), MathHelper.normalizeAngle(var1, 360)));
   }

   @Override
   public Optional<TRSRTransformation> apply(Optional<? extends IModelPart> var1) {
      return (Optional<TRSRTransformation>)Reflector.call(Reflector.ForgeHooksClient_applyTransform, this.getMatrix(), var1);
   }

   public EnumFacing rotateFace(EnumFacing var1) {
      EnumFacing var2 = var1;

      for (int var3 = 0; var3 < this.quartersX; var3++) {
         var2 = var2.rotateAround(EnumFacing.Axis.X);
      }

      if (var2.getAxis() != EnumFacing.Axis.Y) {
         for (int var4 = 0; var4 < this.quartersY; var4++) {
            var2 = var2.rotateAround(EnumFacing.Axis.Y);
         }
      }

      return var2;
   }

   ModelRotation(int var3, int var4) {
      this.combinedXY = combineXY(var3, var4);
      this.matrix4d = new Matrix4f();
      Matrix4f var5 = new Matrix4f();
      var5.setIdentity();
      Matrix4f.rotate(-var3 * (float) (Math.PI / 180.0), new Vector3f(1.0F, 0.0F, 0.0F), var5, var5);
      this.quartersX = MathHelper.abs_int(var3 / 90);
      Matrix4f var6 = new Matrix4f();
      var6.setIdentity();
      Matrix4f.rotate(-var4 * (float) (Math.PI / 180.0), new Vector3f(0.0F, 1.0F, 0.0F), var6, var6);
      this.quartersY = MathHelper.abs_int(var4 / 90);
      Matrix4f.mul(var6, var5, this.matrix4d);
   }

   @Override
   public int rotate(EnumFacing var1, int var2) {
      return this.rotateVertex(var1, var2);
   }

   static {
      for (ModelRotation var3 : values()) {
         mapRotations.put(var3.combinedXY, var3);
      }
   }

   public static int combineXY(int var0, int var1) {
      return var0 * 360 + var1;
   }
}
