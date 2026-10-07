package net.minecraft.client.renderer.entity;

import java.nio.FloatBuffer;
import java.util.Arrays;
import junit.framework.TestCase;

public class LegacyGuiGlintTest extends TestCase {
   public void testTextureCoordinatesMatchNativeGuiOverlayAcrossBothPeriods() {
      FloatBuffer matrix = FloatBuffer.allocate(16);
      for (long time : new long[]{0, 1499, 2999, 3000, 4872, 4873, 100000}) {
         for (int pass = 0; pass < 2; pass++) {
            RenderItem.fillLegacyGlintMatrix(matrix, pass, time);
            int period = 3000 + pass * 1873;
            float offset = (float)(time % period) / period * 256.0F;
            float shear = pass == 0 ? 4.0F : -1.0F;
            // Coordinates relative to the native 20x20 overlay's upper-left corner.
            for (float x : new float[]{0, 2, 10, 18, 20}) {
               for (float y : new float[]{0, 2, 10, 18, 20}) {
                  float u = matrix.get(0) * x + matrix.get(4) * y + matrix.get(12);
                  float v = matrix.get(1) * x + matrix.get(5) * y + matrix.get(13);
                  assertEquals((offset + x + y * shear) / 256.0F, u, 0.000001F);
                  assertEquals(y / 256.0F, v, 0.0F);
               }
            }
         }
      }
   }

   public void testPotionLayersDoNotDuplicateGlintButDifferentDepthsRemain() {
      int[] liquid = face(0.46875F, 0);
      int[] bottle = face(0.46875F, 1);
      int[] rear = face(0.53125F, 2);
      int[] positions = RenderItem.uniqueGlintPositions(Arrays.asList(liquid, bottle, rear));
      assertEquals("One visible face and one distinct rear face", 24, positions.length);
      assertEquals(0.46875F, Float.intBitsToFloat(positions[2]), 0.0F);
      assertEquals(0.53125F, Float.intBitsToFloat(positions[14]), 0.0F);
   }

   private static int[] face(float z, int layer) {
      int[] data = new int[28];
      float[] xy = {0, 0, 1, 0, 1, 1, 0, 1};
      for (int vertex = 0; vertex < 4; vertex++) {
         int index = vertex * 7;
         data[index] = Float.floatToIntBits(xy[vertex * 2]);
         data[index + 1] = Float.floatToIntBits(xy[vertex * 2 + 1]);
         data[index + 2] = Float.floatToIntBits(z);
         data[index + 3] = -1;
         data[index + 4] = Float.floatToIntBits(layer / 4.0F);
         data[index + 5] = Float.floatToIntBits(vertex / 4.0F);
      }
      return data;
   }
}
