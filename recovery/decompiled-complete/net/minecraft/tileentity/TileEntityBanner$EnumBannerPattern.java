package net.minecraft.tileentity;

import net.minecraft.block.BlockFlower$EnumFlowerType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public enum TileEntityBanner$EnumBannerPattern {
   STRIPE_LEFT("stripe_left", "ls", "#  ", "#  ", "#  "),
   DIAGONAL_LEFT("diagonal_left", "ld", "## ", "#  ", "   "),
   CROSS("cross", "cr", "# #", " # ", "# #"),
   HALF_VERTICAL("half_vertical", "vh", "## ", "## ", "## "),
   STRIPE_DOWNRIGHT("stripe_downright", "drs", "#  ", " # ", "  #"),
   SQUARE_TOP_RIGHT("square_top_right", "tr", "  #", "   ", "   "),
   MOJANG("mojang", "moj", new ItemStack(Items.golden_apple, 1, 1)),
   DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud", " ##", "  #", "   "),
   HALF_HORIZONTAL("half_horizontal", "hh", "###", "###", "   "),
   STRIPE_DOWNLEFT("stripe_downleft", "dls", "  #", " # ", "#  "),
   TRIANGLE_TOP("triangle_top", "tt", "# #", " # ", "   "),
   GRADIENT_UP("gradient_up", "gru", " # ", " # ", "# #"),
   BORDER("border", "bo", "###", "# #", "###"),
   STRIPE_SMALL("small_stripes", "ss", "# #", "# #", "   "),
   SQUARE_TOP_LEFT("square_top_left", "tl", "#  ", "   ", "   "),
   BRICKS("bricks", "bri", new ItemStack(Blocks.brick_block)),
   FLOWER("flower", "flo", new ItemStack(Blocks.red_flower, 1, BlockFlower$EnumFlowerType.OXEYE_DAISY.getMeta())),
   TRIANGLE_BOTTOM("triangle_bottom", "bt", "   ", " # ", "# #"),
   STRIPE_BOTTOM("stripe_bottom", "bs", "   ", "   ", "###"),
   SKULL("skull", "sku", new ItemStack(Items.skull, 1, 1)),
   CURLY_BORDER("curly_border", "cbo", new ItemStack(Blocks.vine)),
   HALF_VERTICAL_MIRROR("half_vertical_right", "vhr", " ##", " ##", " ##"),
   DIAGONAL_RIGHT("diagonal_up_right", "rd", "   ", "  #", " ##"),
   STRIPE_RIGHT("stripe_right", "rs", "  #", "  #", "  #"),
   HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb", "   ", "###", "###"),
   DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud", "   ", "#  ", "## "),
   STRIPE_CENTER("stripe_center", "cs", " # ", " # ", " # "),
   GRADIENT("gradient", "gra", "# #", " # ", " # "),
   SQUARE_BOTTOM_LEFT("square_bottom_left", "bl", "   ", "   ", "#  "),
   STRIPE_MIDDLE("stripe_middle", "ms", "   ", "###", "   "),
   STRAIGHT_CROSS("straight_cross", "sc", " # ", "###", " # "),
   SQUARE_BOTTOM_RIGHT("square_bottom_right", "br", "   ", "   ", "  #"),
   CIRCLE_MIDDLE("circle", "mc", "   ", " # ", "   "),
   BASE("base", "b"),
   TRIANGLES_TOP("triangles_top", "tts", " # ", "# #", "   "),
   STRIPE_TOP("stripe_top", "ts", "###", "   ", "   "),
   RHOMBUS_MIDDLE("rhombus", "mr", " # ", "# #", " # "),
   CREEPER("creeper", "cre", new ItemStack(Items.skull, 1, 4)),
   TRIANGLES_BOTTOM("triangles_bottom", "bts", "   ", "# #", " # ");
   public String[] craftingLayers = new String[3];
   public String patternID;
   // $VF: synthetic field
   public static TileEntityBanner$EnumBannerPattern[] $VALUES = new TileEntityBanner$EnumBannerPattern[]{
      TileEntityBanner$EnumBannerPattern.BASE,
      SQUARE_BOTTOM_LEFT,
      TileEntityBanner$EnumBannerPattern.SQUARE_BOTTOM_RIGHT,
      SQUARE_TOP_LEFT,
      SQUARE_TOP_RIGHT,
      STRIPE_BOTTOM,
      TileEntityBanner$EnumBannerPattern.STRIPE_TOP,
      STRIPE_LEFT,
      STRIPE_RIGHT,
      STRIPE_CENTER,
      STRIPE_MIDDLE,
      STRIPE_DOWNRIGHT,
      STRIPE_DOWNLEFT,
      STRIPE_SMALL,
      CROSS,
      TileEntityBanner$EnumBannerPattern.STRAIGHT_CROSS,
      TRIANGLE_BOTTOM,
      TRIANGLE_TOP,
      TileEntityBanner$EnumBannerPattern.TRIANGLES_BOTTOM,
      TileEntityBanner$EnumBannerPattern.TRIANGLES_TOP,
      DIAGONAL_LEFT,
      DIAGONAL_RIGHT,
      DIAGONAL_LEFT_MIRROR,
      DIAGONAL_RIGHT_MIRROR,
      TileEntityBanner$EnumBannerPattern.CIRCLE_MIDDLE,
      TileEntityBanner$EnumBannerPattern.RHOMBUS_MIDDLE,
      HALF_VERTICAL,
      HALF_HORIZONTAL,
      HALF_VERTICAL_MIRROR,
      HALF_HORIZONTAL_MIRROR,
      BORDER,
      CURLY_BORDER,
      TileEntityBanner$EnumBannerPattern.CREEPER,
      GRADIENT,
      GRADIENT_UP,
      BRICKS,
      SKULL,
      FLOWER,
      MOJANG
   };
   public String patternName;
   public ItemStack patternCraftingStack;

   public boolean hasCraftingStack() {
      return this.patternCraftingStack != null;
   }

   public String getPatternName() {
      return this.patternName;
   }

   public String[] getCraftingLayers() {
      return this.craftingLayers;
   }

   public ItemStack getCraftingStack() {
      return this.patternCraftingStack;
   }

   public static TileEntityBanner$EnumBannerPattern getPatternByID(String var0) {
      for (TileEntityBanner$EnumBannerPattern var4 : values()) {
         if (var4.patternID.equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   public String getPatternID() {
      return this.patternID;
   }

   public boolean hasValidCrafting() {
      return this.patternCraftingStack != null || this.craftingLayers[0] != null;
   }

   public TileEntityBanner$EnumBannerPattern(String var3, String var4, ItemStack var5) {
      this(var3, var4);
      this.patternCraftingStack = var5;
   }

   public TileEntityBanner$EnumBannerPattern(String var3, String var4, String var5, String var6, String var7) {
      this(var3, var4);
      this.craftingLayers[0] = var5;
      this.craftingLayers[1] = var6;
      this.craftingLayers[2] = var7;
   }

   public TileEntityBanner$EnumBannerPattern(String var3, String var4) {
      this.patternName = var3;
      this.patternID = var4;
   }
}
