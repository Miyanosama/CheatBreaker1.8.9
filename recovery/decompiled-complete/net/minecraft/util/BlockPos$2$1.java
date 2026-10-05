package net.minecraft.util;

import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.google.common.collect.AbstractIterator;
import net.minecraft.entity.monster.EntityEnderman$AIFindPlayer;
import net.minecraft.item.crafting.RecipesArmor;
import net.minecraft.world.gen.feature.WorldGenReed;

public class BlockPos$2$1 extends AbstractIterator<BlockPos$MutableBlockPos> {
   public ScrollableElement field_0003;
   public WorldGenReed field_0005;
   public EntityEnderman$AIFindPlayer field_0002;
   public BlockPos$MutableBlockPos theBlockPos;
   public RecipesArmor field_0001;

   public BlockPos$2$1(BlockPos$2 var1) {
      this.field_179315_a = var1;
      super();
      this.theBlockPos = null;
   }

   public BlockPos$MutableBlockPos computeNext() {
      if (this.theBlockPos == null) {
         this.theBlockPos = new BlockPos$MutableBlockPos(
            this.field_179315_a.field_179312_a.getX(), this.field_179315_a.field_179312_a.getY(), this.field_179315_a.field_179312_a.getZ()
         );
         return this.theBlockPos;
      } else if (this.theBlockPos.equals(this.field_179315_a.field_179311_b)) {
         return (BlockPos$MutableBlockPos)this.endOfData();
      } else {
         int var1 = this.theBlockPos.getX();
         int var2 = this.theBlockPos.getY();
         int var3 = this.theBlockPos.getZ();
         if (var1 < this.field_179315_a.field_179311_b.getX()) {
            var1++;
         } else if (var2 < this.field_179315_a.field_179311_b.getY()) {
            var1 = this.field_179315_a.field_179312_a.getX();
            var2++;
         } else if (var3 < this.field_179315_a.field_179311_b.getZ()) {
            var1 = this.field_179315_a.field_179312_a.getX();
            var2 = this.field_179315_a.field_179312_a.getY();
            var3++;
         }

         BlockPos$MutableBlockPos.access$002(this.theBlockPos, var1);
         BlockPos$MutableBlockPos.access$102(this.theBlockPos, var2);
         BlockPos$MutableBlockPos.access$202(this.theBlockPos, var3);
         return this.theBlockPos;
      }
   }
}
