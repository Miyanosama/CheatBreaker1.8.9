package net.minecraft.block.material;

public class MaterialLogic extends Material {
   @Override
   public boolean isSolid() {
      return false;
   }

   public MaterialLogic(MapColor var1) {
      super(var1);
      this.setAdventureModeExempt();
   }

   @Override
   public boolean blocksLight() {
      return false;
   }

   @Override
   public boolean blocksMovement() {
      return false;
   }
}
