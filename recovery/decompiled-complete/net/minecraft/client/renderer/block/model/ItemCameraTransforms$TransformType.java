package net.minecraft.client.renderer.block.model;

import net.minecraft.util.Matrix4f;
import net.minecraft.util.Util;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;

public enum ItemCameraTransforms$TransformType {
   NONE,
   FIXED,
   THIRD_PERSON,
   GUI,
   GROUND,
   HEAD,
   FIRST_PERSON;

   public GenLayerAddMushroomIsland field_0008;
   public Util field_0004;
   // $VF: synthetic field
   public static ItemCameraTransforms$TransformType[] $VALUES = new ItemCameraTransforms$TransformType[]{
      NONE, THIRD_PERSON, ItemCameraTransforms$TransformType.FIRST_PERSON, HEAD, GUI, GROUND, FIXED
   };
   public Matrix4f field_0000;
}
