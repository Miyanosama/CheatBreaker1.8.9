package com.cheatbreaker.client.ui.element.type;

import junit.framework.TestCase;

public class NumericSliderElementTest extends TestCase {
   public void testIntegerReachableWithPixelMouseAtDifferentScales() {
      for (float scale : new float[] {0.5F, 1.0F, 1.5F, 2.0F}) {
         float integerX = (37.0F + 181.25F + 130.0F * (1.0F - 0.25F) / 1.75F) * scale;
         assertEquals(1.0F, value(Math.round(integerX), scale, true), 0.0F);
         assertEquals(1.0F, value(integerX + 2.0F * scale, scale, true), 0.0F);
      }
   }

   public void testFractionalValuesRemainAvailableOutsideSnapRadius() {
      float mouseX = 37.0F + 181.25F + 130.0F * (1.25F - 0.25F) / 1.75F;
      assertEquals(1.25F, value(mouseX, 1.0F, true), 0.0F);
   }

   public void testOtherSettingsDoNotSnapToIntegers() {
      float mouseX = 37.0F + 181.25F + 130.0F * (0.99F - 0.25F) / 1.75F;
      assertEquals(0.99F, value(mouseX, 1.0F, false), 0.0F);
   }

   public void testBoundsAndThumbCoordinates() {
      assertEquals(0.25F, value(37.0F + 181.25F, 1.0F, true), 0.0F);
      assertEquals(2.0F, value(37.0F + 181.25F + 130.0F, 1.0F, true), 0.0F);
      assertEquals(0.25F, value(-1000.0F, 1.0F, true), 0.0F);
      assertEquals(2.0F, value(1000.0F, 1.0F, true), 0.0F);
   }

   private float value(float mouseX, float scale, boolean snapIntegers) {
      return NumericSliderElement.valueForMouseX(mouseX, 37.0F, scale, 130.0, 0.25F, 2.0F, snapIntegers);
   }
}
