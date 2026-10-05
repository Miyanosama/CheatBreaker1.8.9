package net.minecraft.item.crafting;

import io.netty.handler.codec.http.QueryStringEncoder$Param;
import java.util.Comparator;
import org.java_websocket.framing.ContinuousFrame;

public class CraftingManager$1 implements Comparator<IRecipe> {
   public ContinuousFrame field_0002;
   public QueryStringEncoder$Param field_0000;

   public CraftingManager$1(CraftingManager var1) {
      this.field_77582_a = var1;
      super();
   }

   public int compare(IRecipe var1, IRecipe var2) {
      return var1 instanceof ShapelessRecipes && var2 instanceof ShapedRecipes
         ? 1
         : (
            var2 instanceof ShapelessRecipes && var1 instanceof ShapedRecipes
               ? -1
               : (var2.getRecipeSize() < var1.getRecipeSize() ? -1 : (var2.getRecipeSize() > var1.getRecipeSize() ? 1 : 0))
         );
   }
}
