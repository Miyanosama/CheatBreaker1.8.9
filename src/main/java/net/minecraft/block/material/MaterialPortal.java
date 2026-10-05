package net.minecraft.block.material;

public class MaterialPortal extends Material {
   @Override
   public boolean isSolid() {
      return false;
   }

   @Override
   public boolean blocksLight() {
      return false;
   }

   public MaterialPortal(MapColor var1) {
      super(var1);
   }

   @Override
   public boolean blocksMovement() {
      return false;
   }
}
