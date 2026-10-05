package net.minecraft.creativetab;

import java.util.List;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class CreativeTabs {
   public static CreativeTabs[] creativeTabArray = new CreativeTabs[12];
   public String tabLabel;
   public static CreativeTabs tabBlock = new CreativeTabs(0, "buildingBlocks") {
      @Override
      public Item getTabIconItem() {
         return Item.getItemFromBlock(Blocks.brick_block);
      }
   };
   public boolean hasScrollbar;
   public boolean drawTitle;
   public int tabIndex;
   public static CreativeTabs tabDecorations = new CreativeTabs(1, "decorations") {
      @Override
      public int getIconItemDamage() {
         return BlockDoublePlant.EnumPlantType.PAEONIA.getMeta();
      }

      @Override
      public Item getTabIconItem() {
         return Item.getItemFromBlock(Blocks.double_plant);
      }
   };
   public static CreativeTabs tabRedstone = new CreativeTabs(2, "redstone") {
      @Override
      public Item getTabIconItem() {
         return Items.redstone;
      }
   };
   public static CreativeTabs tabTransport = new CreativeTabs(3, "transportation") {
      @Override
      public Item getTabIconItem() {
         return Item.getItemFromBlock(Blocks.golden_rail);
      }
   };
   public static CreativeTabs tabMisc = (new CreativeTabs(4, "misc") {
      @Override
      public Item getTabIconItem() {
         return Items.lava_bucket;
      }
   }).setRelevantEnchantmentTypes(EnumEnchantmentType.ALL);
   public ItemStack iconItemStack;
   public static CreativeTabs tabAllSearch = (new CreativeTabs(5, "search") {
      @Override
      public Item getTabIconItem() {
         return Items.compass;
      }
   }).setBackgroundImageName("item_search.png");
   public String theTexture = "items.png";
   public static CreativeTabs tabFood = new CreativeTabs(6, "food") {
      @Override
      public Item getTabIconItem() {
         return Items.apple;
      }
   };
   public static CreativeTabs tabTools = (new CreativeTabs(7, "tools") {
      @Override
      public Item getTabIconItem() {
         return Items.iron_axe;
      }
   }).setRelevantEnchantmentTypes(EnumEnchantmentType.DIGGER, EnumEnchantmentType.FISHING_ROD, EnumEnchantmentType.BREAKABLE);
   public static CreativeTabs tabCombat = (new CreativeTabs(8, "combat") {
         @Override
         public Item getTabIconItem() {
            return Items.golden_sword;
         }
      })
      .setRelevantEnchantmentTypes(
         EnumEnchantmentType.ARMOR,
         EnumEnchantmentType.ARMOR_FEET,
         EnumEnchantmentType.ARMOR_HEAD,
         EnumEnchantmentType.ARMOR_LEGS,
         EnumEnchantmentType.ARMOR_TORSO,
         EnumEnchantmentType.BOW,
         EnumEnchantmentType.WEAPON
      );
   public static CreativeTabs tabBrewing = new CreativeTabs(9, "brewing") {
      @Override
      public Item getTabIconItem() {
         return Items.potionitem;
      }
   };
   public static CreativeTabs tabMaterials = new CreativeTabs(10, "materials") {
      @Override
      public Item getTabIconItem() {
         return Items.stick;
      }
   };
   public static CreativeTabs tabInventory = (new CreativeTabs(11, "inventory") {
      @Override
      public Item getTabIconItem() {
         return Item.getItemFromBlock(Blocks.chest);
      }
   }).setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
   public EnumEnchantmentType[] enchantmentTypes;

   public int getTabIndex() {
      return this.tabIndex;
   }

   public CreativeTabs setBackgroundImageName(String var1) {
      this.theTexture = var1;
      return this;
   }

   public String getTranslatedTabLabel() {
      return "itemGroup." + this.getTabLabel();
   }

   public int getIconItemDamage() {
      return 0;
   }

   public EnumEnchantmentType[] getRelevantEnchantmentTypes() {
      return this.enchantmentTypes;
   }

   public void displayAllReleventItems(List<ItemStack> var1) {
      for (Item var3 : Item.itemRegistry) {
         if (var3 != null && var3.getCreativeTab() == this) {
            var3.getSubItems(var3, this, var1);
         }
      }

      if (this.getRelevantEnchantmentTypes() != null) {
         this.addEnchantmentBooksToList(var1, this.getRelevantEnchantmentTypes());
      }
   }

   public String getTabLabel() {
      return this.tabLabel;
   }

   public boolean shouldHidePlayerInventory() {
      return this.hasScrollbar;
   }

   public boolean drawInForegroundOfTab() {
      return this.drawTitle;
   }

   public abstract Item getTabIconItem();

   public boolean isTabInFirstRow() {
      return this.tabIndex < 6;
   }

   public String getBackgroundImageName() {
      return this.theTexture;
   }

   public CreativeTabs setNoTitle() {
      this.drawTitle = false;
      return this;
   }

   public CreativeTabs setRelevantEnchantmentTypes(EnumEnchantmentType... var1) {
      this.enchantmentTypes = var1;
      return this;
   }

   public ItemStack getIconItemStack() {
      if (this.iconItemStack == null) {
         this.iconItemStack = new ItemStack(this.getTabIconItem(), 1, this.getIconItemDamage());
      }

      return this.iconItemStack;
   }

   public void addEnchantmentBooksToList(List<ItemStack> var1, EnumEnchantmentType... var2) {
      for (Enchantment var6 : Enchantment.enchantmentsBookList) {
         if (var6 != null && var6.type != null) {
            boolean var7 = false;

            for (int var8 = 0; var8 < var2.length && !var7; var8++) {
               if (var6.type == var2[var8]) {
                  var7 = true;
               }
            }

            if (var7) {
               var1.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(var6, var6.getMaxLevel())));
            }
         }
      }
   }

   public boolean hasRelevantEnchantmentType(EnumEnchantmentType var1) {
      if (this.enchantmentTypes == null) {
         return false;
      } else {
         for (EnumEnchantmentType var5 : this.enchantmentTypes) {
            if (var5 == var1) {
               return true;
            }
         }

         return false;
      }
   }

   public CreativeTabs setNoScrollbar() {
      this.hasScrollbar = false;
      return this;
   }

   public int getTabColumn() {
      return this.tabIndex % 6;
   }

   public CreativeTabs(int var1, String var2) {
      this.hasScrollbar = true;
      this.drawTitle = true;
      this.tabIndex = var1;
      this.tabLabel = var2;
      creativeTabArray[var1] = this;
   }
}
