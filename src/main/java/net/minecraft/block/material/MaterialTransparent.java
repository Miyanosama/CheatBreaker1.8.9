package net.minecraft.block.material;

public class MaterialTransparent extends Material {
   @Override
   public boolean isSolid() {
      return false;
   }

   @Override
   public boolean blocksLight() {
      return false;
   }

   @Override
   public boolean blocksMovement() {
      return false;
   }

   public MaterialTransparent(MapColor var1) {
      super(var1);
      this.i();
   }
}
