package net.minecraft.tileentity;

public class TileEntityDropper extends TileEntityDispenser {
   @Override
   public String z_() {
      return this.u_() ? this.a : "container.dropper";
   }

   @Override
   public String getGuiID() {
      return "minecraft:dropper";
   }
}
