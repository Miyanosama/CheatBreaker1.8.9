package net.minecraft.block.material;

public class MaterialLiquid extends Material {
   @Override
   public boolean isLiquid() {
      return true;
   }

   @Override
   public boolean blocksMovement() {
      return false;
   }

   @Override
   public boolean isSolid() {
      return false;
   }

   public MaterialLiquid(MapColor var1) {
      super(var1);
      this.i();
      this.setNoPushMobility();
   }
}
