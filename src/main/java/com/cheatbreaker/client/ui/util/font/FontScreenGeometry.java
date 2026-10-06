package com.cheatbreaker.client.ui.util.font;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/** Maps orthographic, axis-aligned text coordinates to framebuffer pixels. */
final class FontScreenGeometry {
   private static final FloatBuffer MODEL = BufferUtils.createFloatBuffer(16);
   private static final FloatBuffer PROJECTION = BufferUtils.createFloatBuffer(16);
   private static final IntBuffer VIEWPORT = BufferUtils.createIntBuffer(16);
   private static final float[] MODEL_VALUES = new float[16], PROJECTION_VALUES = new float[16];
   private static final int[] VIEWPORT_VALUES = new int[16];
   static final FontScreenGeometry UNALIGNED = new FontScreenGeometry(0, 0, 0, 0);
   final double scaleX, scaleY, originX, originY;

   FontScreenGeometry(double scaleX, double scaleY, double originX, double originY) {
      this.scaleX = scaleX;
      this.scaleY = scaleY;
      this.originX = originX;
      this.originY = originY;
   }

   static FontScreenGeometry capture() {
      MODEL.clear(); PROJECTION.clear(); VIEWPORT.clear();
      GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, MODEL);
      GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, PROJECTION);
      GL11.glGetInteger(GL11.GL_VIEWPORT, VIEWPORT);
      MODEL.get(MODEL_VALUES); PROJECTION.get(PROJECTION_VALUES); VIEWPORT.get(VIEWPORT_VALUES);
      return fromMatrices(MODEL_VALUES, PROJECTION_VALUES, VIEWPORT_VALUES);
   }

   static FontScreenGeometry fromMatrices(float[] model, float[] projection, int[] viewport) {
      // Leave rotated labels and perspective rendering on their original path.
      if (Math.abs(model[1]) > 0.00001F || Math.abs(model[4]) > 0.00001F
         || Math.abs(model[2]) > 0.00001F || Math.abs(model[6]) > 0.00001F
         || Math.abs(projection[3]) > 0.00001F || Math.abs(projection[7]) > 0.00001F
         || Math.abs(projection[11]) > 0.00001F || Math.abs(projection[15] - 1) > 0.00001F) {
         return UNALIGNED;
      }
      double sx = model[0] * projection[0] * viewport[2] / 2.0;
      double sy = model[5] * projection[5] * viewport[3] / 2.0;
      // Minecraft rounds its scaled viewport dimensions, so the two axes may differ slightly.
      if (Math.abs(sx) < 0.01 || Math.abs(sy) < 0.01 || Math.abs(Math.abs(sx) - Math.abs(sy)) > Math.max(Math.abs(sx), Math.abs(sy)) * 0.01) {
         return UNALIGNED;
      }
      return new FontScreenGeometry(sx, sy,
         viewport[0] + (model[12] * projection[0] + projection[12] + 1) * viewport[2] / 2.0,
         viewport[1] + (model[13] * projection[5] + projection[13] + 1) * viewport[3] / 2.0);
   }

   double rasterScale() {
      // CBFontRenderer halves its font coordinates before rendering.
      return scaleX == 0 ? 1 : Math.max(0.125, Math.rint(Math.abs(scaleX) * 8) / 16.0);
   }

   double snapX(double x) { return snap(x, scaleX, originX); }
   double snapY(double y) { return snap(y, scaleY, originY); }

   static double snap(double coordinate, double scale, double origin) {
      return scale == 0 ? coordinate : (Math.rint(coordinate * scale + origin) - origin) / scale;
   }
}
