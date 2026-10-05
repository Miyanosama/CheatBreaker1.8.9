package net.minecraft.util;

import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.item.crafting.RecipesFood;

public class BlockPos$MutableBlockPos extends BlockPos {
   public ClientBrandRetriever field_0001;
   public RecipesFood field_0002;
   public int y;
   public int x;
   public int z;

   @Override
   public int getY() {
      return this.y;
   }

   public BlockPos$MutableBlockPos() {
      this(0, 0, 0);
   }

   public BlockPos$MutableBlockPos set(int var1, int var2, int var3) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
      return this;
   }

   public BlockPos$MutableBlockPos(int var1, int var2, int var3) {
      super(0, 0, 0);
      this.x = var1;
      this.y = var2;
      this.z = var3;
   }

   @Override
   public int getX() {
      return this.x;
   }

   @Override
   public int getZ() {
      return this.z;
   }
}
