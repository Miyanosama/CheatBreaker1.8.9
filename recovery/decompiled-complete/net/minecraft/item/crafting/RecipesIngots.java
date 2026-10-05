package net.minecraft.item.crafting;

import io.netty.channel.socket.nio.NioSocketChannel;
import junit.swingui.FailureRunView$1;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeColorHelper$2;

public class RecipesIngots {
   public BiomeColorHelper$2 field_0001;
   public Object[][] recipeItems = new Object[][]{
      {Blocks.gold_block, new ItemStack(Items.gold_ingot, 9)},
      {Blocks.iron_block, new ItemStack(Items.iron_ingot, 9)},
      {Blocks.diamond_block, new ItemStack(Items.diamond, 9)},
      {Blocks.emerald_block, new ItemStack(Items.emerald, 9)},
      {Blocks.lapis_block, new ItemStack(Items.dye, 9, EnumDyeColor.BLUE.getDyeDamage())},
      {Blocks.redstone_block, new ItemStack(Items.redstone, 9)},
      {Blocks.coal_block, new ItemStack(Items.coal, 9, 0)},
      {Blocks.hay_block, new ItemStack(Items.wheat, 9)},
      {Blocks.slime_block, new ItemStack(Items.slime_ball, 9)}
   };
   public NioSocketChannel field_0000;
   public FailureRunView$1 field_0002;

   public void addRecipes(CraftingManager var1) {
      for (int var2 = 0; var2 < this.recipeItems.length; var2++) {
         Block var3 = (Block)this.recipeItems[var2][0];
         ItemStack var4 = (ItemStack)this.recipeItems[var2][1];
         var1.addRecipe(new ItemStack(var3), "###", "###", "###", '#', var4);
         var1.addRecipe(var4, "#", '#', var3);
      }

      var1.addRecipe(new ItemStack(Items.gold_ingot), "###", "###", "###", '#', Items.gold_nugget);
      var1.addRecipe(new ItemStack(Items.gold_nugget, 9), "#", '#', Items.gold_ingot);
   }
}
