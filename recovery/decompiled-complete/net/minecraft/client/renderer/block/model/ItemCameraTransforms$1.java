package net.minecraft.client.renderer.block.model;

import net.minecraft.client.model.TextureOffset;
import net.optifine.entity.model.ModelAdapterBiped;
import org.apache.log4j.varia.HUPNode;

// $VF: synthetic class
public class ItemCameraTransforms$1 {
   public ModelAdapterBiped field_0001;
   public TextureOffset field_0003;
   public HUPNode field_0000;

   static {
      try {
         field_181684_a[ItemCameraTransforms$TransformType.THIRD_PERSON.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_181684_a[ItemCameraTransforms$TransformType.FIRST_PERSON.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_181684_a[ItemCameraTransforms$TransformType.HEAD.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_181684_a[ItemCameraTransforms$TransformType.GUI.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_181684_a[ItemCameraTransforms$TransformType.GROUND.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_181684_a[ItemCameraTransforms$TransformType.FIXED.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
