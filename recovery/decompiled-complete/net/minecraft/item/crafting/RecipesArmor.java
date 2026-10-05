package net.minecraft.item.crafting;

import net.minecraft.block.state.BlockStateBase$1;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.ServerStatusResponse$MinecraftProtocolVersionIdentifier;
import net.minecraft.util.BlockPos$MutableBlockPos;
import org.apache.log4j.net.DefaultEvaluator;
import org.apache.log4j.or.sax.AttributesRenderer;
import recovered.unidentified.UnidentifiedClass4776;

public class RecipesArmor {
   public DefaultEvaluator field_0004;
   public Item[][] recipeItems;
   public ServerStatusResponse$MinecraftProtocolVersionIdentifier field_0003;
   public String[][] recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
   public BlockPos$MutableBlockPos field_0000;
   public RenderFireball field_0001;
   public BlockStateBase$1 field_0008;
   public UnidentifiedClass4776 field_0005;
   public AttributesRenderer field_0002;

   public RecipesArmor() {
      this.recipeItems = new Item[][]{
         {Items.leather, Items.iron_ingot, Items.diamond, Items.gold_ingot},
         {Items.leather_helmet, Items.iron_helmet, Items.diamond_helmet, Items.golden_helmet},
         {Items.leather_chestplate, Items.iron_chestplate, Items.diamond_chestplate, Items.golden_chestplate},
         {Items.leather_leggings, Items.iron_leggings, Items.diamond_leggings, Items.golden_leggings},
         {Items.leather_boots, Items.iron_boots, Items.diamond_boots, Items.golden_boots}
      };
   }

   public void addRecipes(CraftingManager var1) {
      for (int var2 = 0; var2 < this.recipeItems[0].length; var2++) {
         Item var3 = this.recipeItems[0][var2];

         for (int var4 = 0; var4 < this.recipeItems.length - 1; var4++) {
            Item var5 = this.recipeItems[var4 + 1][var2];
            var1.addRecipe(new ItemStack(var5), this.recipePatterns[var4], 'X', var3);
         }
      }
   }
}
