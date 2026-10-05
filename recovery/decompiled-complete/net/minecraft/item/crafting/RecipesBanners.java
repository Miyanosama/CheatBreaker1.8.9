package net.minecraft.item.crafting;

import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.gen.MapGenCaves;
import net.optifine.ChunkPosComparator;

public class RecipesBanners {
   public ItemShears field_0002;
   public ChunkPosComparator field_0004;
   public PipeStatus field_0001;
   public ChatComponentTranslation field_0003;
   public MapGenCaves field_0000;

   public void addRecipes(CraftingManager var1) {
      for (EnumDyeColor var5 : EnumDyeColor.values()) {
         var1.addRecipe(
            new ItemStack(Items.banner, 1, var5.getDyeDamage()), "###", "###", " | ", '#', new ItemStack(Blocks.wool, 1, var5.getMetadata()), '|', Items.stick
         );
      }

      var1.addRecipe(new RecipesBanners$RecipeDuplicatePattern(null));
      var1.addRecipe(new RecipesBanners$RecipeAddPattern(null));
   }
}
