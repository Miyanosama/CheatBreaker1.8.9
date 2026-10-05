package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityBanner extends TileEntity {
   public int baseColor;
   public List<EnumDyeColor> colorList;
   public List<TileEntityBanner.EnumBannerPattern> patternList;
   public boolean field_175119_g;
   public String patternResourceLocation;
   public NBTTagList patterns;

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      setBaseColorAndPatterns(var1, this.baseColor, this.patterns);
   }

   public List<TileEntityBanner.EnumBannerPattern> getPatternList() {
      this.initializeBannerData();
      return this.patternList;
   }

   public static int method_24268(ItemStack var0) {
      NBTTagCompound var1 = var0.getSubCompound("BlockEntityTag", false);
      return var1 != null && var1.hasKey("Base") ? var1.getInteger("Base") : var0.getMetadata();
   }

   public static int getPatterns(ItemStack var0) {
      NBTTagCompound var1 = var0.getSubCompound("BlockEntityTag", false);
      return var1 != null && var1.hasKey("Patterns") ? var1.getTagList("Patterns", 10).tagCount() : 0;
   }

   public static void removeBannerData(ItemStack var0) {
      NBTTagCompound var1 = var0.getSubCompound("BlockEntityTag", false);
      if (var1 != null && var1.hasKey("Patterns", 9)) {
         NBTTagList var2 = var1.getTagList("Patterns", 10);
         if (var2.tagCount() > 0) {
            var2.removeTag(var2.tagCount() - 1);
            if (var2.hasNoTags()) {
               var0.getTagCompound().removeTag("BlockEntityTag");
               if (var0.getTagCompound().hasNoTags()) {
                  var0.setTagCompound((NBTTagCompound)null);
               }
            }
         }
      }
   }

   public List<EnumDyeColor> getColorList() {
      this.initializeBannerData();
      return this.colorList;
   }

   public NBTTagList getPatterns() {
      return this.patterns;
   }

   public static void setBaseColorAndPatterns(NBTTagCompound var0, int var1, NBTTagList var2) {
      var0.setInteger("Base", var1);
      if (var2 != null) {
         var0.setTag("Patterns", var2);
      }
   }

   public void setItemValues(ItemStack var1) {
      this.patterns = null;
      if (var1.hasTagCompound() && var1.getTagCompound().hasKey("BlockEntityTag", 10)) {
         NBTTagCompound var2 = var1.getTagCompound().getCompoundTag("BlockEntityTag");
         if (var2.hasKey("Patterns")) {
            this.patterns = (NBTTagList)var2.getTagList("Patterns", 10).copy();
         }

         if (var2.hasKey("Base", 99)) {
            this.baseColor = var2.getInteger("Base");
         } else {
            this.baseColor = var1.getMetadata() & 15;
         }
      } else {
         this.baseColor = var1.getMetadata() & 15;
      }

      this.patternList = null;
      this.colorList = null;
      this.patternResourceLocation = "";
      this.field_175119_g = true;
   }

   public String getPatternResourceLocation() {
      this.initializeBannerData();
      return this.patternResourceLocation;
   }

   public void initializeBannerData() {
      if (this.patternList == null || this.colorList == null || this.patternResourceLocation == null) {
         if (!this.field_175119_g) {
            this.patternResourceLocation = "";
         } else {
            this.patternList = Lists.newArrayList();
            this.colorList = Lists.newArrayList();
            this.patternList.add(TileEntityBanner.EnumBannerPattern.BASE);
            this.colorList.add(EnumDyeColor.byDyeDamage(this.baseColor));
            this.patternResourceLocation = "b" + this.baseColor;
            if (this.patterns != null) {
               for (int var1 = 0; var1 < this.patterns.tagCount(); var1++) {
                  NBTTagCompound var2 = this.patterns.getCompoundTagAt(var1);
                  TileEntityBanner.EnumBannerPattern var3 = TileEntityBanner.EnumBannerPattern.getPatternByID(var2.getString("Pattern"));
                  if (var3 != null) {
                     this.patternList.add(var3);
                     int var4 = var2.getInteger("Color");
                     this.colorList.add(EnumDyeColor.byDyeDamage(var4));
                     this.patternResourceLocation = this.patternResourceLocation + var3.getPatternID() + var4;
                  }
               }
            }
         }
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.baseColor = var1.getInteger("Base");
      this.patterns = var1.getTagList("Patterns", 10);
      this.patternList = null;
      this.colorList = null;
      this.patternResourceLocation = null;
      this.field_175119_g = true;
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      return new S35PacketUpdateTileEntity(this.c, 6, var1);
   }

   public int getBaseColor() {
      return this.baseColor;
   }

   public static enum EnumBannerPattern {
      BASE("base", "b"),
      SQUARE_BOTTOM_LEFT("square_bottom_left", "bl", "   ", "   ", "#  "),
      SQUARE_BOTTOM_RIGHT("square_bottom_right", "br", "   ", "   ", "  #"),
      SQUARE_TOP_LEFT("square_top_left", "tl", "#  ", "   ", "   "),
      SQUARE_TOP_RIGHT("square_top_right", "tr", "  #", "   ", "   "),
      STRIPE_BOTTOM("stripe_bottom", "bs", "   ", "   ", "###"),
      STRIPE_TOP("stripe_top", "ts", "###", "   ", "   "),
      STRIPE_LEFT("stripe_left", "ls", "#  ", "#  ", "#  "),
      STRIPE_RIGHT("stripe_right", "rs", "  #", "  #", "  #"),
      STRIPE_CENTER("stripe_center", "cs", " # ", " # ", " # "),
      STRIPE_MIDDLE("stripe_middle", "ms", "   ", "###", "   "),
      STRIPE_DOWNRIGHT("stripe_downright", "drs", "#  ", " # ", "  #"),
      STRIPE_DOWNLEFT("stripe_downleft", "dls", "  #", " # ", "#  "),
      STRIPE_SMALL("small_stripes", "ss", "# #", "# #", "   "),
      CROSS("cross", "cr", "# #", " # ", "# #"),
      STRAIGHT_CROSS("straight_cross", "sc", " # ", "###", " # "),
      TRIANGLE_BOTTOM("triangle_bottom", "bt", "   ", " # ", "# #"),
      TRIANGLE_TOP("triangle_top", "tt", "# #", " # ", "   "),
      TRIANGLES_BOTTOM("triangles_bottom", "bts", "   ", "# #", " # "),
      TRIANGLES_TOP("triangles_top", "tts", " # ", "# #", "   "),
      DIAGONAL_LEFT("diagonal_left", "ld", "## ", "#  ", "   "),
      DIAGONAL_RIGHT("diagonal_up_right", "rd", "   ", "  #", " ##"),
      DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud", "   ", "#  ", "## "),
      DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud", " ##", "  #", "   "),
      CIRCLE_MIDDLE("circle", "mc", "   ", " # ", "   "),
      RHOMBUS_MIDDLE("rhombus", "mr", " # ", "# #", " # "),
      HALF_VERTICAL("half_vertical", "vh", "## ", "## ", "## "),
      HALF_HORIZONTAL("half_horizontal", "hh", "###", "###", "   "),
      HALF_VERTICAL_MIRROR("half_vertical_right", "vhr", " ##", " ##", " ##"),
      HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb", "   ", "###", "###"),
      BORDER("border", "bo", "###", "# #", "###"),
      CURLY_BORDER("curly_border", "cbo", new ItemStack(Blocks.vine)),
      CREEPER("creeper", "cre", new ItemStack(Items.skull, 1, 4)),
      GRADIENT("gradient", "gra", "# #", " # ", " # "),
      GRADIENT_UP("gradient_up", "gru", " # ", " # ", "# #"),
      BRICKS("bricks", "bri", new ItemStack(Blocks.brick_block)),
      SKULL("skull", "sku", new ItemStack(Items.skull, 1, 1)),
      FLOWER("flower", "flo", new ItemStack(Blocks.red_flower, 1, BlockFlower.EnumFlowerType.OXEYE_DAISY.getMeta())),
      MOJANG("mojang", "moj", new ItemStack(Items.golden_apple, 1, 1));
      public String[] craftingLayers = new String[3];
      public String patternID;
      // $VF: synthetic field
      public static TileEntityBanner.EnumBannerPattern[] $VALUES = new TileEntityBanner.EnumBannerPattern[]{
         TileEntityBanner.EnumBannerPattern.BASE,
         SQUARE_BOTTOM_LEFT,
         TileEntityBanner.EnumBannerPattern.SQUARE_BOTTOM_RIGHT,
         SQUARE_TOP_LEFT,
         SQUARE_TOP_RIGHT,
         STRIPE_BOTTOM,
         TileEntityBanner.EnumBannerPattern.STRIPE_TOP,
         STRIPE_LEFT,
         STRIPE_RIGHT,
         STRIPE_CENTER,
         STRIPE_MIDDLE,
         STRIPE_DOWNRIGHT,
         STRIPE_DOWNLEFT,
         STRIPE_SMALL,
         CROSS,
         TileEntityBanner.EnumBannerPattern.STRAIGHT_CROSS,
         TRIANGLE_BOTTOM,
         TRIANGLE_TOP,
         TileEntityBanner.EnumBannerPattern.TRIANGLES_BOTTOM,
         TileEntityBanner.EnumBannerPattern.TRIANGLES_TOP,
         DIAGONAL_LEFT,
         DIAGONAL_RIGHT,
         DIAGONAL_LEFT_MIRROR,
         DIAGONAL_RIGHT_MIRROR,
         TileEntityBanner.EnumBannerPattern.CIRCLE_MIDDLE,
         TileEntityBanner.EnumBannerPattern.RHOMBUS_MIDDLE,
         HALF_VERTICAL,
         HALF_HORIZONTAL,
         HALF_VERTICAL_MIRROR,
         HALF_HORIZONTAL_MIRROR,
         BORDER,
         CURLY_BORDER,
         TileEntityBanner.EnumBannerPattern.CREEPER,
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

      public static TileEntityBanner.EnumBannerPattern getPatternByID(String var0) {
         for (TileEntityBanner.EnumBannerPattern var4 : values()) {
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

      EnumBannerPattern(String var3, String var4, ItemStack var5) {
         this(var3, var4);
         this.patternCraftingStack = var5;
      }

      EnumBannerPattern(String var3, String var4, String var5, String var6, String var7) {
         this(var3, var4);
         this.craftingLayers[0] = var5;
         this.craftingLayers[1] = var6;
         this.craftingLayers[2] = var7;
      }

      EnumBannerPattern(String var3, String var4) {
         this.patternName = var3;
         this.patternID = var4;
      }
   }
}
