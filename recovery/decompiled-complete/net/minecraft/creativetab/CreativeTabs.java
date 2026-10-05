package net.minecraft.creativetab;

import io.netty.handler.codec.socks.SocksAuthRequestDecoder$State;
import io.netty.handler.stream.ChunkedWriteHandler$PendingWrite;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager$StencilFunc;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.realms.RealmsScreen;
import net.optifine.gui.GuiScreenOF;
import org.apache.log4j.net.SMTPAppender$1;

public abstract class CreativeTabs {
   public static CreativeTabs tabDecorations = new CreativeTabs$5(1, "decorations");
   public SocksAuthRequestDecoder$State field_0023;
   public String tabLabel;
   public GlStateManager$StencilFunc field_0020;
   public static CreativeTabs[] creativeTabArray = new CreativeTabs[12];
   public RealmsScreen field_0007;
   public boolean hasScrollbar;
   public boolean drawTitle;
   public int tabIndex;
   public static CreativeTabs tabAllSearch = new CreativeTabs$9(5, "search").setBackgroundImageName("item_search.png");
   public static CreativeTabs tabFood = new CreativeTabs$10(6, "food");
   public ChunkedWriteHandler$PendingWrite field_0013;
   public static CreativeTabs tabRedstone = new CreativeTabs$6(2, "redstone");
   public GuiScreenOF field_0010;
   public static CreativeTabs tabBlock = new CreativeTabs$1(0, "buildingBlocks");
   public ItemStack iconItemStack;
   public static CreativeTabs tabInventory = new CreativeTabs$4(11, "inventory").setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
   public String theTexture = "items.png";
   public static CreativeTabs tabCombat = new CreativeTabs$12(8, "combat")
      .setRelevantEnchantmentTypes(
         EnumEnchantmentType.ARMOR,
         EnumEnchantmentType.ARMOR_FEET,
         EnumEnchantmentType.ARMOR_HEAD,
         EnumEnchantmentType.ARMOR_LEGS,
         EnumEnchantmentType.ARMOR_TORSO,
         EnumEnchantmentType.BOW,
         EnumEnchantmentType.WEAPON
      );
   public static CreativeTabs tabBrewing = new CreativeTabs$2(9, "brewing");
   public static CreativeTabs tabTools = new CreativeTabs$11(7, "tools")
      .setRelevantEnchantmentTypes(EnumEnchantmentType.DIGGER, EnumEnchantmentType.FISHING_ROD, EnumEnchantmentType.BREAKABLE);
   public SMTPAppender$1 field_0001;
   public static CreativeTabs tabMaterials = new CreativeTabs$3(10, "materials");
   public static CreativeTabs tabTransport = new CreativeTabs$7(3, "transportation");
   public static CreativeTabs tabMisc = new CreativeTabs$8(4, "misc").setRelevantEnchantmentTypes(EnumEnchantmentType.ALL);
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
